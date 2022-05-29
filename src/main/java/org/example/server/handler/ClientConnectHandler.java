package org.example.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

public class ClientConnectHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[" + new Date() + "]初始化一个client:");

        // 回复给client，表示自己以及建立了链接
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "你好， 小 client, 后续由本大神给你提供服务哈".getBytes(Charset.forName("utf-8"));
        buffer.writeBytes(bytes);

        ctx.writeAndFlush(buffer);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
