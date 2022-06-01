package org.example.client.handler.purity;

import com.google.common.base.Joiner;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;
import org.example.codec.model.creategroup.CreateGroupResponsePacket;
import org.example.codec.model.getuserinfos.GlobalUserInfoResponsePacket;
import org.example.codec.model.getuserinfos.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Log4j2
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket msg) throws Exception {
        if (msg.getSuccess()) {
            System.out.println("创建成功 ");
        } else {
            System.out.println("创建失败, 原因是: " + msg.getReason());
        }
    }
}
