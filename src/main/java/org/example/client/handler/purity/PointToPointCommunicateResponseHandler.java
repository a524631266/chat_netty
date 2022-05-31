package org.example.client.handler.purity;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;
import org.example.codec.model.communicate.PointToPointCommunicateResponsePacket;


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
        System.out.println(String.format("用户 [ %s (%s)] 说: %s" ,
                packet.getFromUserName() ,
                packet.getFromUserId(),
                packet.getMessage()) );
    }
}
