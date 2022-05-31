package org.example.client.handler.purity;

import com.google.common.base.Objects;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;
import org.example.codec.model.communicate.PointToPointCommunicateRequestPacket;
import org.example.codec.model.communicate.PointToPointCommunicateResponsePacket;
import org.example.server.session.SessionManage;
import org.example.server.session.model.CommunicateContext;

@Log4j2
public class PointToPointCommunicateResponseHandler extends SimpleChannelInboundHandler<PointToPointCommunicateResponsePacket> {
    /**
     * 请求命令
     * @param ctx
     * @param packet
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PointToPointCommunicateResponsePacket packet) throws Exception {
        String toUserId = packet.getFromUserId();
        System.out.println(String.format("用户 [ %s ] 说: %s" , packet.getFromUserName() , packet.getMessage()) );
    }
}
