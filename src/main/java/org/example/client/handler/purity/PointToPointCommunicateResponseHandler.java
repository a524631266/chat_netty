package org.example.client.handler.purity;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;
import org.example.model.sendToUser.PointToPointCommunicateResponsePacket;


@Log4j2
public class PointToPointCommunicateResponseHandler extends SimpleChannelInboundHandler<PointToPointCommunicateResponsePacket> {
    /**
     * 请求命令
     * @param ctx 上下文
     * @param packet 点对点返回体
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PointToPointCommunicateResponsePacket packet) {
        System.out.printf("用户 [ %s (%s)] 说: %s%n",
                packet.getFromUserName() ,
                packet.getFromUserId(),
                packet.getMessage());
    }
}
