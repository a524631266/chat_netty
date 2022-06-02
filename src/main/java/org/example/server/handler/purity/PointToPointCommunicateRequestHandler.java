package org.example.server.handler.purity;

import com.google.common.base.Objects;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;
import org.example.model.sendToUser.PointToPointCommunicateRequestPacket;
import org.example.model.sendToUser.PointToPointCommunicateResponsePacket;
import org.example.server.session.SessionManage;
import org.example.server.session.model.CommunicateContext;

@Log4j2
public class PointToPointCommunicateRequestHandler extends SimpleChannelInboundHandler<PointToPointCommunicateRequestPacket> {
    /**
     * 请求命令
     * @param ctx 上下文
     * @param packet 指定的点对点请求体
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PointToPointCommunicateRequestPacket packet) {
        String toUserId = packet.getToUserId();
        CommunicateContext context = SessionManage.queryToUserId(toUserId);

        PointToPointCommunicateResponsePacket response = new PointToPointCommunicateResponsePacket();
        if(!Objects.equal(context, null)) {
            response.setMessage(packet.getMessage());
            CommunicateContext sessionContext = SessionManage.getSessionContext(ctx.channel());
            response.setFromUserId(sessionContext.getSession().getUserId());
            response.setFromUserName(sessionContext.getSession().getUserName());
            response.setMessage(packet.getMessage());
            if(!Objects.equal(context.getChannel(), null)){
                Channel toUserChannel = context.getChannel();
                toUserChannel.writeAndFlush(response);
            }
        }
    }
}
