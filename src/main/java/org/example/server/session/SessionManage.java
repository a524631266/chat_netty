package org.example.server.session;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.log4j.Log4j2;

import org.example.config.ChatConfiguration;
import org.example.server.session.model.CommunicateContext;
import org.example.server.session.model.GroupContext;
import org.example.server.session.model.Session;

import java.util.*;
import java.util.stream.Collectors;

@Log4j2
public class SessionManage {
    /**
     * 用户uid与前面的映射
     */
    public static final Map<String, CommunicateContext> relationMap = Collections
            .synchronizedMap(new HashMap<>());

    /**
     * 用户groupId与成员的映射
     */
    public static final Map<String, GroupContext> groupIdMap = Collections
            .synchronizedMap(new HashMap<>());

    /**
     * updateSession 取名应该更关注语义，需要用bind
     *
     * @param channel 绑定session
     * @param session session信息
     */
    public static void bindSession(Channel channel, Session session) {
        CommunicateContext context = CommunicateContext.builder()
                .session(session)
                .channel(channel)
                .build();
        relationMap.put(session.getUserId(), context);
        log.info("update session {}", session);
        // 标记为登陆状态
        ChatConfiguration.serverSideLogin(channel, context);
    }

    /**
     * 取消绑定
     *
     * @param channel 通过管道来取消绑定session
     */
    public static void unbindSession(Channel channel) {
        if (ChatConfiguration.serverSideHasLogin(channel)) {
            CommunicateContext sessionContext = getSessionContext(channel);
            Session removedSession = sessionContext.getSession();
            CommunicateContext remove = relationMap.remove(removedSession.getUserId());
            log.info("delete session {}", remove);
        }
    }

    public static CommunicateContext getSessionContext(Channel channel) {
        return ChatConfiguration.getSessionContext(channel);
    }

    public static CommunicateContext queryToUserId(String toUserId) {
        return relationMap.get(toUserId);
    }

    public static List<Session> getSessions() {
        return relationMap.values()
                .stream()
                .map(CommunicateContext::getSession).collect(Collectors.toList());
    }

    public static Integer randomUid() {
        int maxTray = 5;
        int i = UUID.randomUUID().hashCode();
        while (maxTray >= 0 && relationMap.containsKey(String.valueOf(i))) {
            maxTray -= 1;
        }
        return i;
    }

    public static Integer randomGroupId() {
        int maxTray = 5;
        int i = UUID.randomUUID().hashCode();
        while (maxTray >= 0 && groupIdMap.containsKey(String.valueOf(i))) {
            maxTray -= 1;
        }
        return i;
    }


    /**
     * bindGroup 绑定一个小群
     *
     * @param userIds 绑定根据用户id列表来初始化一个小组
     * @param channelGroup 用来绑定一个channel通道
     * @return 返回groupId
     */
    public static GroupContext bindGroup(List<String> userIds, ChannelGroup channelGroup) {

        if (Objects.isNull(userIds) || userIds.isEmpty()) {
            return null;
        }

        // 创建一个用户组id GroupContext
        Integer groupId = randomGroupId();

        // 获取相关的用户列表
        List<CommunicateContext> infos = userIds.stream().map(userId -> {
            CommunicateContext context = queryToUserId(userId);
            if(Objects.isNull(context)) return null;
            return context;
        }).filter(a -> !Objects.isNull(a)).collect(Collectors.toList());

        GroupContext groupContext = GroupContext.builder().group(channelGroup)
                .users(infos.stream().map(CommunicateContext::getSession).collect(Collectors.toList()))
                .build();

        // 跟个新用户信息
        if(!infos.isEmpty()) {
            infos.forEach(a -> channelGroup.add(a.getChannel()));

            groupContext.setGroupId(String.valueOf(groupId));
            groupIdMap.put(String.valueOf(groupId), groupContext);
        }
        log.info("update group : {}, {}", groupId, infos);
        return groupContext;
    }

    /**
     *
     * 删除群聊
     */
    public static void unbindGroup(String groupId) {
        if (groupIdMap.containsKey(groupId)) {
            GroupContext remove = groupIdMap.remove(groupId);
            log.info("delete group {}", remove.getGroupId());
        }
    }


}
