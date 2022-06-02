package org.example.server.handler.purity;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;
import org.example.model.group.querygroup.GroupInfo;
import org.example.model.group.querygroup.QueryAllGroupRequestPacket;
import org.example.model.group.querygroup.QueryAllGroupResponsePacket;
import org.example.server.session.SessionManage;

import java.util.List;

@Log4j2
public class QueryAllGroupRequestHandler extends SimpleChannelInboundHandler<QueryAllGroupRequestPacket> {
    /**
     * 请求命令
     *
     * @param ctx    上下文
     * @param packet 指定的点对点请求体
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QueryAllGroupRequestPacket packet) {

        List<GroupInfo> groupInfos = SessionManage.queryAllGroups();
        // 2.构造返回体
        QueryAllGroupResponsePacket response = new QueryAllGroupResponsePacket();

        response.setGroupInfo(groupInfos);
        ctx.channel().writeAndFlush(response);
    }
}
