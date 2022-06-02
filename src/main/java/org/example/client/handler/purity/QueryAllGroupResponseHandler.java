package org.example.client.handler.purity;

import com.google.common.base.Joiner;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;
import org.example.model.group.querygroup.QueryAllGroupResponsePacket;

import java.util.Locale;


@Log4j2
public class QueryAllGroupResponseHandler extends SimpleChannelInboundHandler<QueryAllGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QueryAllGroupResponsePacket msg) throws Exception {

        System.out.println("创建成功 , 用户组信息： [" + Joiner.on(":").join(msg.getGroupInfo()).toLowerCase(Locale.ROOT) + "]");

    }
}
