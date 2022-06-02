package org.example.model.group.joingroup;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.model.Command;
import org.example.model.Packet;
import org.example.server.session.model.Session;
import org.example.server.session.model.StateSession;

import java.util.List;

/**
 * 简单的全局用户信息表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JoinGroupResponsePacket extends Packet {
    Boolean success;
    String reason;
    String groupId;
    List<StateSession> users;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }
}
