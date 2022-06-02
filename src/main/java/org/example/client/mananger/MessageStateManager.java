package org.example.client.mananger;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import org.example.model.group.message.MessageGroupResponsePacket;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class MessageStateManager {
    private static String keyPatternFormatter = "%s_login";
    public static Map<String , AttributeKey<Boolean>> groupSessionMap;
    static {
        groupSessionMap = new HashMap<>();
    }

    /**
     * 同步操作
     */
    @Nullable
    private static synchronized AttributeKey<Boolean> cacheKey(String groupId) {
        String key = String.format(keyPatternFormatter, groupId);
        return groupSessionMap.getOrDefault(key, null);
    }

//    public static void serverSideLogin(Channel channel, MessageGroupResponsePacket packet) {
//        channel.attr(SERVER_SIDE_LOGIN).set(context);
//    }

//    public static Boolean hasConnectToGroup(Channel channel, String groupId) {
//        cacheKey(groupId)
//        return channel.attr(SERVER_SIDE_LOGIN).get() != null;
//    }
}
