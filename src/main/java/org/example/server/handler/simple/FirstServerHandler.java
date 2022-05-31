package org.example.server.handler.simple;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("message type of :" + msg.getClass().getName());
        ByteBuf response = (ByteBuf) msg;
        System.out.println("[" + new Date() + "]: 服务器读到数据 -> " + response.toString(Charset.forName("utf-8")) );

        //  可以向下传播消息，如果不设置
        ctx.fireChannelRead(msg);
    }
}
