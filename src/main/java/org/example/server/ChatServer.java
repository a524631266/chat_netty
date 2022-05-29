package org.example.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.example.common.ChatConfiguration;
import org.example.server.handler.FirstServerHandler;

import java.util.Date;


/**
 *
 */
public class ChatServer {
    public static void main(String[] args) {
        ServerBootstrap chatBootstrap = new ServerBootstrap();

        NioEventLoopGroup acceptor = new NioEventLoopGroup(1);
        NioEventLoopGroup workers = new NioEventLoopGroup(4);

        chatBootstrap.group(acceptor, workers)
                .channel(NioServerSocketChannel.class)
                .attr(AttributeKey.newInstance("serverName"), "chatServer")
                .childOption(ChannelOption.SO_KEEPALIVE, true) // 开启tcp底层心跳机制
                .childOption(ChannelOption.TCP_NODELAY, true) //无延迟，有数据马上发送
                .childAttr(AttributeKey.newInstance("clientName"), "chatClient")
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new FirstServerHandler());
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext cx, String msg) throws Exception {
                                System.out.println(msg);
                            }
                        });
                    }
                });
        autoIncBind(chatBootstrap);
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
                        } else {
                            // 不用 + 1
                            autoIncBind(chatBootstrap);
                        }
                    }
                });
    }
}
