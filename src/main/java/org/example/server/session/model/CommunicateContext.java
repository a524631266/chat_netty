package org.example.server.session.model;

import io.netty.channel.Channel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommunicateContext {
    Session session;
    Channel channel;
}
