package org.example.server.session.model;

import io.netty.channel.group.ChannelGroup;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GroupContext {
    List<Session> users;
    ChannelGroup group;
    String groupId;

    public synchronized void add(Session user) {
        String userId = user.getUserId();
        long count = users.stream().filter(a -> a.getUserId().equals(userId)).count();
        if(count == 0) {
            users.add(user);
        }
    }
}
