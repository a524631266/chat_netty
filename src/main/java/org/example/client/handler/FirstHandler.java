package org.example.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Date;


public class FirstHandler extends ChannelInboundHandlerAdapter {
    /**
     * 1 在链路初始化过程中激活。
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + "客户端开始写数据");

        // 开始给当前上下文的buffer创建数据
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "你好，服务器".getBytes(StandardCharsets.UTF_8);
        buffer.writeBytes(bytes);
        // 并发送数据
        ctx.channel().writeAndFlush(bytes);
    }
}
