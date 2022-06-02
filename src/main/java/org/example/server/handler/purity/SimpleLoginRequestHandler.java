package org.example.server.handler.purity;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;
import org.example.model.login.LoginRequestPacket;
import org.example.model.login.LoginResponsePacket;
import org.example.server.session.SessionManage;
import org.example.server.session.model.Session;

@Log4j2
public class SimpleLoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket request) throws Exception {

        LoginResponsePacket response = new LoginResponsePacket();
        Integer userId = request.getUserId() == null ? randomUid() : request.getUserId();
        response.setUserId(userId);
        response.setPassword(request.getPassword());
        response.setUsername(request.getUsername());
        response.setSuccess(true);
        response.setReason("成功登陆");
        log.info("你好:{} ,", response.getUsername());

        // 绑定当前的session
        SessionManage.bindSession(ctx.channel(),
                Session.builder()
                        .userId(String.valueOf(userId))
                        .userName(request.getUsername())
                        .build()
        );

        ctx.channel().writeAndFlush(response);

    }

    private Integer randomUid() {
        return SessionManage.randomUid();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
//        super.channelInactive(ctx);
        log.info("login 删除");
        SessionManage.unbindSession(ctx.channel());
    }
}
