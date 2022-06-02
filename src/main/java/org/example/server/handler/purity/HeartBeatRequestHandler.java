package org.example.server.handler.purity;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;
import org.example.model.heartbeat.HeartBeatRequestPacket;
import org.example.model.heartbeat.HeartBeatResponsePacket;

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
