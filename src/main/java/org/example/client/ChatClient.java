package org.example.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.example.client.handler.LoginClientHandler;
import org.example.common.ChatConfiguration;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ChatClient {
    public static int MAX_RETRY = 5;

    public static void main(String[] args) {
        Bootstrap chatBootstrap = new Bootstrap();
        NioEventLoopGroup workers = new NioEventLoopGroup();

        chatBootstrap.group(workers)
                .channel(NioSocketChannel.class)
                .attr(AttributeKey.newInstance("clientName"), "chatClient01")

                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
//                        ch.pipeline().addLast(new StringDecoder());
//                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
//                            @Override
//                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
//                                System.out.println(msg);
//                                System.out.println(msg + 1);
//                                // 无效果
//                                 ctx.fireChannelRead(msg);
//                            }
//                        });
//                        ch.pipeline().addLast(new FirstClientHandler());
                        ch.pipeline().addLast(new LoginClientHandler());
                    }
                });

        retryConnect(chatBootstrap, 2);
    }

    private static void retryConnect(Bootstrap chatBootstrap, int retry) {
        chatBootstrap.connect(ChatConfiguration.ChatServerIp, ChatConfiguration.ChatServerPort)
                .addListener(new GenericFutureListener<Future<? super Void>>() {
                    @Override
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if (future.isSuccess()) {
                            System.out.println("连接成功+");
                            Channel channel = ((ChannelFuture) future).channel();
                            startThread(channel);
                        }else if (retry == 0) {
                            System.out.println("stop ");
                        } else {
                            // 指数退避法则
                            int order = MAX_RETRY - retry + 1;
                            int sleepSec = 1 << order;
                            System.out.println("sleepSec: " + sleepSec);
                            // 优化
                            chatBootstrap.config().group().schedule(() -> retryConnect(chatBootstrap, retry - 1), sleepSec, TimeUnit.SECONDS);
                            System.out.println("失败");
                        }
                    }
                });
    }

    private static void startThread(Channel channel) {
        new Thread(() -> {
            while(!Thread.interrupted()){
                if(ChatConfiguration.hasLogin(channel)){
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();
                };
                System.out.println("aaas");
            }
        }).start();
    }
}
