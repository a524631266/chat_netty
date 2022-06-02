package org.example.client.handler.purity;

import com.google.common.base.Joiner;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;
import org.example.model.group.message.MessageGroupResponsePacket;

import java.util.Locale;


@Log4j2
public class MessageGroupResponseHandler extends SimpleChannelInboundHandler<MessageGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageGroupResponsePacket msg) throws Exception {

        System.out.println("组id: [" + msg.getGroupId() + "] : " + msg.getMessage());

    }
}
