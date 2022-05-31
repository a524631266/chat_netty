package org.example.server.session;

import io.netty.channel.Channel;
import lombok.extern.log4j.Log4j2;

import org.example.common.ChatConfiguration;
import org.example.server.session.model.CommunicateContext;
import org.example.server.session.model.Session;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Log4j2
public class SessionManage {
    /**
     * 用户uid与前面的映射
     */
    public static final Map<String, CommunicateContext> relationMap = Collections.synchronizedMap(new HashMap());

    /**
     * updateSession 取名应该更关注语义，需要用bind
     *
     * @param channel
     * @param session
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
     * @param channel
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

    public static Integer randomUid() {
        int maxTray = 5;
        int i = UUID.randomUUID().hashCode();
        while (maxTray >= 0 && relationMap.containsKey(String.valueOf(i))) {
            maxTray -= 1;
        }
        return i;
    }
}
