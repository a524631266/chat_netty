package org.example.client.handler.purity;

import com.google.common.base.Joiner;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;
import org.example.model.group.creategroup.CreateGroupResponsePacket;

import java.util.Locale;


@Log4j2
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket msg) throws Exception {
        if (msg.getSuccess()) {
            System.out.println("创建成功 , 组号: [" + msg.getGroupId() + "], 用户信息： [" + Joiner.on(":").join(msg.getUserNames()).toLowerCase(Locale.ROOT) + "]");
        } else {
            System.out.println("创建失败, 原因是: " + msg.getReason());
        }
    }
}
