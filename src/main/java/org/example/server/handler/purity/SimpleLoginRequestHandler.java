package org.example.server.handler.purity;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;
import org.example.codec.model.LoginRequestPacket;
import org.example.codec.model.LoginResponsePacket;

@Log4j2
public class SimpleLoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        LoginRequestPacket request = (LoginRequestPacket) msg;
        LoginResponsePacket response = new LoginResponsePacket();
        response.setUserId(request.getUserId());
        response.setPassword(request.getPassword());
        response.setUsername(request.getUsername());
        response.setSuccess(true);
        response.setReason("成功登陆");
        log.info("响应请求: " + response);
        ctx.channel().writeAndFlush(response);
    }
}
