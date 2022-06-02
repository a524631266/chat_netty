package org.example.server.handler.purity;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import lombok.extern.log4j.Log4j2;
import org.example.model.group.creategroup.CreateGroupRequestPacket;
import org.example.model.group.creategroup.CreateGroupResponsePacket;
import org.example.model.group.message.MessageGroupRequestPacket;
import org.example.model.group.message.MessageGroupResponsePacket;
import org.example.server.session.SessionManage;
import org.example.server.session.model.GroupContext;

import java.util.Objects;

@Log4j2
public class MessageGroupRequestHandler extends SimpleChannelInboundHandler<MessageGroupRequestPacket> {
    /**
     * 请求命令
     *
     * @param ctx    上下文
     * @param packet 指定的点对点请求体
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageGroupRequestPacket packet) {

        // 绑定用户组信息
        GroupContext groupContext = SessionManage.queryGroup(packet.getGroupId());

        if(Objects.isNull(groupContext) || Objects.isNull(groupContext.getGroup())){
            MessageGroupResponsePacket response = new MessageGroupResponsePacket();
            response.setMessage("不能发送消息");
            response.setGroupId(packet.getGroupId());
            ctx.channel().writeAndFlush(response);
            return;
        }
        // 2.构造返回体
        MessageGroupResponsePacket response = new MessageGroupResponsePacket();
        response.setMessage(packet.getMessage());
        response.setGroupId(packet.getGroupId());

        groupContext.getGroup().writeAndFlush(response);
    }
}
