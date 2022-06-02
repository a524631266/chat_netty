package org.example.client.handler.purity;

import com.google.common.base.Joiner;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;
import org.example.model.group.joingroup.JoinGroupResponsePacket;

import java.util.Locale;


@Log4j2
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket msg) throws Exception {
        if (msg.getSuccess()) {
            System.out.println("成功加入 组: [" + msg.getGroupId() + "], 用户信息： [" + Joiner.on(":").join(msg.getUsers()).toLowerCase(Locale.ROOT) + "]");
        } else {
            System.out.println("加入组失败, 原因是: " + msg.getReason());
        }
    }
}
