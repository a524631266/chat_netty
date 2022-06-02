package org.example.server.handler.purity;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;
import org.example.model.getuserinfos.GlobalUserInfoRequestPacket;
import org.example.model.getuserinfos.GlobalUserInfoResponsePacket;
import org.example.model.getuserinfos.UserInfo;
import org.example.server.session.SessionManage;
import org.example.server.session.model.Session;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
public class GlobalUserInfosRequestHandler extends SimpleChannelInboundHandler<GlobalUserInfoRequestPacket> {
    /**
     * 请求命令
     *
     * @param ctx    上下文
     * @param packet 指定的点对点请求体
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GlobalUserInfoRequestPacket packet) {

        List<Session> sessions = SessionManage.getSessions();
        GlobalUserInfoResponsePacket response = new GlobalUserInfoResponsePacket();
        response.setUserInfo(getUserInfo(sessions));

        ctx.channel().writeAndFlush(response);
    }

    private List<UserInfo> getUserInfo(List<Session> sessions) {
        if (Objects.isNull(sessions)) {
            return null;
        }
        return sessions.stream()
                .map(a -> new UserInfo(a.getUserId(), a.getUserName()))
                .collect(Collectors.toList());

    }
}
