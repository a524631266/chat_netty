package org.example.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.example.client.handler.purity.*;
import org.example.client.handler.security.AuthHandler;
import org.example.client.handler.simple.LoginClientHandler;
import org.example.client.handler.simple.MessageClientHandler;
import org.example.client.handler.splicing.LoginRequestSplicingHandler;
import org.example.codec.line.CommunicateCommandShell;
import org.example.config.ChatConfiguration;
import org.example.server.handler.purity.HeartBeatRequestHandler;
import org.example.server.handler.purity.PacketDecoder;
import org.example.server.handler.purity.PacketEncoder;
import org.example.stable.IMIdleStateHandler;

import java.util.concurrent.TimeUnit;

public class ChatClient {
    public static int MAX_RETRY = 5;

    public void start(ChatConfiguration config) {
        Bootstrap chatBootstrap = new Bootstrap();
        NioEventLoopGroup workers = new NioEventLoopGroup();

        chatBootstrap.group(workers)
                .channel(NioSocketChannel.class)
                .attr(AttributeKey.newInstance("clientName"), "chatClient01")

                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
//                        ch.pipeline().addLast(new StringDecoder());
//                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
//                            @Override
//                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
//                                System.out.println(msg);
//                                System.out.println(msg + 1);
//                                // ?????????
//                                 ctx.fireChannelRead(msg);
//                            }
//                        });
//                        ch.pipeline().addLast(new FirstClientHandler());
                        // ??????1
//                        simpleMethod(ch);
                        // ??????2
                        purityMethod(ch);
                        // ????????????
//                        splicingMethod(ch);
                    }
                });

        ChannelFuture channelFuture = retryConnect(chatBootstrap, config, 2);

        waitToClose(channelFuture, workers);
    }

    /**
     * ????????????channel
     *
     */
    private void waitToClose(ChannelFuture channelFuture, NioEventLoopGroup workers) {
        try {
            // ???????????????????????????????????????????????????????????????????????? channel().close()???????????????
            channelFuture.channel().closeFuture().sync();
            // ??????????????????
            workers.shutdownGracefully();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void purityMethod(SocketChannel ch) {
        ch.pipeline().addLast(new IMIdleStateHandler());
        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
        ch.pipeline().addLast(new PacketDecoder());
        ch.pipeline().addLast(new PacketEncoder());
        ch.pipeline().addLast(new SimpleLoginResponseHandler());

        // ??????????????????????????????
        ch.pipeline().addLast(new HeartBeatResponseHandler());

        ch.pipeline().addLast(new AuthHandler());
        ch.pipeline().addLast(new SimpleMessageResponseHandler());
        ch.pipeline().addLast(new PointToPointCommunicateResponseHandler());
        ch.pipeline().addLast(new GlobalUserInfoResponseHandler());
        // ?????????
        ch.pipeline().addLast(new CreateGroupResponseHandler());
        ch.pipeline().addLast(new JoinGroupResponseHandler());
        ch.pipeline().addLast(new MessageGroupResponseHandler());
        ch.pipeline().addLast(new QueryAllGroupResponseHandler());
    }

    private static void simpleMethod(SocketChannel ch) {
        ch.pipeline().addLast(new LoginClientHandler());
        ch.pipeline().addLast(new SimpleLoginClientHandler());
        ch.pipeline().addLast(new MessageClientHandler());
    }

    private static void splicingMethod(SocketChannel ch) {
        ch.pipeline().addLast(new FixedLengthFrameDecoder(40));
        ch.pipeline().addLast(new LoginRequestSplicingHandler());
    }

    private static ChannelFuture retryConnect(Bootstrap chatBootstrap, ChatConfiguration config, int retry) {
        String ip = config.getRawValueFromOption(ChatConfiguration.serverIp);// ip ??????
        Integer port = config.getRawValueFromOption(ChatConfiguration.serverPort);
        ChannelFuture connect = chatBootstrap.connect(ip, port);// port ??????
        connect.addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("????????????+");
                Channel channel = ((ChannelFuture) future).channel();
//                            LineCommandShell.startThread(channel);
                CommunicateCommandShell.startThread(channel);
            } else if (retry == 0) {
                System.out.println("????????????????????????" + MAX_RETRY);
                connect.channel().close();
            } else {
                // ??????????????????
                int order = MAX_RETRY - retry + 1;
                int sleepSec = 1 << order;
                System.out.println("sleepSec: " + sleepSec);
                // ??????
                chatBootstrap.config().group().schedule(() -> retryConnect(chatBootstrap, config, retry - 1), sleepSec, TimeUnit.SECONDS);
                System.out.println("??????");
            }
        });
        return connect;
    }


}
