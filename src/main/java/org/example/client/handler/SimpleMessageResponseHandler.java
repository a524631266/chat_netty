package org.example.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;
import org.example.codec.line.model.MessageRespPacket;

@Log4j2
public class SimpleMessageResponseHandler extends SimpleChannelInboundHandler<MessageRespPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRespPacket msg) throws Exception {
        log.info(msg);
    }
}
