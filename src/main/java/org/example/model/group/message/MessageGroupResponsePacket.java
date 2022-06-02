package org.example.model.group.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.model.Command;
import org.example.model.Packet;
import org.example.server.session.model.Session;

import java.util.List;

/**
 * 简单的全局用户信息表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageGroupResponsePacket extends Packet {
    Boolean success;
    String reason;
    String groupId;
    List<Session> userNames;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_GROUP_RESPONSE;
    }
}
