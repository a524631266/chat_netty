package org.example.server.handler.purity;

import com.google.common.base.Strings;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;
import org.example.codec.model.LoginRequestPacket;
import org.example.codec.model.LoginResponsePacket;
import org.example.server.session.SessionManage;
import org.example.server.session.model.Session;

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
        log.info("你好:{} ,", response.getUsername());

        // 绑定当前的session
        SessionManage.bindSession(ctx.channel(),
                Session.builder()
                        .userId(String.valueOf(request.getUserId()))
                        .userName(request.getUsername())
                        .build()
                );

        ctx.channel().writeAndFlush(response);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        super.channelInactive(ctx);
        log.info("login 删除");
        SessionManage.unbindSession(ctx.channel());
    }
}
