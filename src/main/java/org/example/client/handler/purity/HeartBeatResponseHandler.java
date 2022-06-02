package org.example.client.handler.purity;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.log4j.Log4j2;
import org.example.model.heartbeat.HeartBeatRequestPacket;
import org.example.model.heartbeat.HeartBeatResponsePacket;

import java.util.concurrent.TimeUnit;


@Log4j2
public class HeartBeatResponseHandler extends ChannelInboundHandlerAdapter {
    // 每隔5秒
    public static final int HeartBeatInterval = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx);
        // 继续向下传递
        super.channelActive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()) {
                ctx.writeAndFlush(new HeartBeatRequestPacket());
                scheduleSendHeartBeat(ctx);
            }
        }, HeartBeatInterval, TimeUnit.SECONDS);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(!(msg instanceof HeartBeatResponsePacket)) super.channelRead(ctx, msg);
    }
}
