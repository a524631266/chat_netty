package org.example.server.handler.purity;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;
import org.example.model.group.joingroup.JoinGroupRequestPacket;
import org.example.model.group.joingroup.JoinGroupResponsePacket;
import org.example.server.session.SessionManage;
import org.example.server.session.model.GroupContext;

import java.util.Objects;


@Log4j2
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    /**
     * 请求命令
     *
     * @param ctx    上下文
     * @param packet 指定的点对点请求体
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket packet) {

        // 1、 查询组，并加入组。 可能考虑会有重复加入
        // todo try catch
        GroupContext context = SessionManage.queryAndJoinGroup(ctx.channel(), packet.getGroupId());
        // 2.构造返回体
        JoinGroupResponsePacket response = new JoinGroupResponsePacket();

        // 如果为空，则直接返回客户消息
        if(Objects.isNull(context)){
            response.setUsers(context.getUsers());
            response.setGroupId(context.getGroupId());
            response.setReason("返回错误 -> 可以自定义一些请求体");
            response.setSuccess(false);
            // 成功给组内成员发送消息
            ctx.channel().writeAndFlush(response);
            return;
        }

        // todo 这里需要判断核心的加入逻辑
        response.setUsers(context.getUsers());
        response.setGroupId(context.getGroupId());
        response.setReason("加入成功");
        response.setSuccess(true);
        // 成功给组内成员发送消息
        context.getGroup().writeAndFlush(response);
    }
}
