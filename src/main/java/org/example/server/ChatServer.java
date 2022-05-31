package org.example.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.example.client.handler.splicing.LoginRequestSplicingHandler;
import org.example.common.ChatConfiguration;

import org.example.monitor.ClientConnectMonitor;
import org.example.server.handler.lifecycle.LifeCycleTestHandler;
import org.example.server.handler.purity.*;
import org.example.server.handler.simple.LoginServerHandler;
import org.example.server.handler.simple.MessageServerHandler;
import org.example.server.handler.splicing.LoginResponseSplicingHandler;
import org.example.server.handler.splicing.MyselfSpliter;

import java.nio.charset.StandardCharsets;


/**
 *
 */
public class ChatServer {
    public static void main(String[] args) {
        ServerBootstrap chatBootstrap = new ServerBootstrap();

        NioEventLoopGroup acceptor = new NioEventLoopGroup(1);
        NioEventLoopGroup workers = new NioEventLoopGroup(1);

        chatBootstrap.group(acceptor, workers)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024) // 1024k
                .attr(AttributeKey.newInstance("serverName"), "chatServer")
                .childOption(ChannelOption.SO_KEEPALIVE, true) // 开启tcp底层心跳机制
                .childOption(ChannelOption.TCP_NODELAY, true) //无延迟，有数据马上发送
                .childAttr(AttributeKey.newInstance("clientName"), "chatClient")
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
//                        // 方法一
//                        simpleMethod(ch);


                        // 方法二
                        purityMethod(ch);

                        // 粘包演示
//                        splicingMethod(ch);

//                        ch.pipeline().addLast(new ClientConnectHandler());
//                        ch.pipeline().addLast(new FirstServerHandler());
//                        ch.pipeline().addLast(new StringDecoder());
//                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
//                            @Override
//                            protected void channelRead0(ChannelHandlerContext cx, String msg) throws Exception {
//                                System.out.println(msg);
//                            }
//                        });

                        //
                    }
                });
        autoIncBind(chatBootstrap);
    }

    private static void splicingMethod(NioSocketChannel ch) {
//        ch.pipeline().addLast(new FixedLengthFrameDecoder(40));


//        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer(0, 1);
//        byteBuf.writeBytes("\n".getBytes(StandardCharsets.UTF_8));
//        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE,byteBuf));
        ch.pipeline().addLast(new LifeCycleTestHandler());
        ch.pipeline().addLast(new MyselfSpliter());
        ch.pipeline().addLast(new LoginResponseSplicingHandler());
    }

    private static void simpleMethod(NioSocketChannel ch) {
        ch.pipeline().addLast(new LoginServerHandler());
        ch.pipeline().addLast(new PacketDecoder());
        ch.pipeline().addLast(new MessageServerHandler());
    }

    private static void purityMethod(NioSocketChannel ch) {
        ch.pipeline().addLast(new LifeCycleTestHandler());
        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
        ch.pipeline().addLast(new PacketDecoder());
        ch.pipeline().addLast(new PacketEncoder());
        ch.pipeline().addLast(new SimpleLoginRequestHandler());
        ch.pipeline().addLast(new SimpleMessageRequestHandler());
        ch.pipeline().addLast(new PointToPointCommunicateRequestHandler());
    }

    /**
     * 自动递增聊天
     * @param chatBootstrap
     */
    private static void autoIncBind(ServerBootstrap chatBootstrap) {
        chatBootstrap.bind(ChatConfiguration.ChatServerIp, ChatConfiguration.ChatServerPort)
                .addListener(new GenericFutureListener<Future<? super Void>>() {
                    @Override
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if (future.isSuccess()) {
                            System.out.println("启动成功:" + ChatConfiguration.ChatServerPort);
                            // 启动一个自定义监控链接
                            new ClientConnectMonitor().start();
                        } else {
                            // 不用 + 1
                            autoIncBind(chatBootstrap);
                        }
                    }
                });
    }
}
