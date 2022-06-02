package org.example.server.handler.purity;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import lombok.extern.log4j.Log4j2;
import org.example.model.creategroup.CreateGroupRequestPacket;
import org.example.model.creategroup.CreateGroupResponsePacket;
import org.example.server.session.SessionManage;
import org.example.server.session.model.GroupContext;

import java.util.Objects;

@Log4j2
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    /**
     * 请求命令
     *
     * @param ctx    上下文
     * @param packet 指定的点对点请求体
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket packet) {

        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        // 绑定用户组信息
        GroupContext bindGroup = SessionManage.bindGroup(packet.getUserIds(), channelGroup);

        // 2.构造返回体
        CreateGroupResponsePacket response = new CreateGroupResponsePacket();

        if(Objects.isNull(bindGroup)) {
            response.setSuccess(false);
            response.setReason("用户列表为空");
        } else if(bindGroup.getUsers().isEmpty()){
            response.setSuccess(false);
            response.setReason("创建失败查不到用户信息");
        } else {
            response.setSuccess(true);
            response.setReason("创建成功");
            response.setGroupId(bindGroup.getGroupId());
            response.setUserNames(bindGroup.getUsers());
        }

        channelGroup.writeAndFlush(response);
    }
}
