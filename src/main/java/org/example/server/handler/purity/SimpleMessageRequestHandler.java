package org.example.server.handler.purity;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;
import org.example.codec.line.model.MessageReqPacket;
import org.example.codec.line.model.MessageRespPacket;
import org.example.codec.model.LoginRequestPacket;
import org.example.codec.model.LoginResponsePacket;
@Log4j2
public class SimpleMessageRequestHandler extends SimpleChannelInboundHandler<MessageReqPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageReqPacket msg) throws Exception {

        MessageRespPacket packet = new MessageRespPacket();
        packet.setMessage("服务器接收到: " + msg.getMessage());
        log.info("响应请求: " + packet);
        ctx.channel().writeAndFlush(packet);
    }
}
