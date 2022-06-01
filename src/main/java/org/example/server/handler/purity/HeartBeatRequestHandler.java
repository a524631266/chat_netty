package org.example.server.handler.purity;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import lombok.extern.log4j.Log4j2;
import org.example.codec.model.creategroup.CreateGroupRequestPacket;
import org.example.codec.model.creategroup.CreateGroupResponsePacket;
import org.example.codec.model.heartbeat.HeartBeatRequestPacket;
import org.example.codec.model.heartbeat.HeartBeatResponsePacket;
import org.example.server.session.SessionManage;
import org.example.server.session.model.GroupContext;

import java.util.Objects;

@Log4j2
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {
    /**
     * 请求命令
     *
     * @param ctx    上下文
     * @param packet 指定的点对点请求体
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket packet) {
        //
        ctx.writeAndFlush(new HeartBeatResponsePacket());
    }
}
