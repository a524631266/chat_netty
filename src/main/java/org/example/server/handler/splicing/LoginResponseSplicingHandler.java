package org.example.server.handler.splicing;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.log4j.Log4j2;

import java.nio.charset.Charset;

@Log4j2
public class LoginResponseSplicingHandler extends ChannelInboundHandlerAdapter {

    /**
     * 如何解决拆包半包问题呢？
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("服务端读到数据 ->" + byteBuf.toString(Charset.forName("utf-8")));
    }
}
