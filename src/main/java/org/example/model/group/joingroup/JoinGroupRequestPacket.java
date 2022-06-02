package org.example.model.group.joingroup;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.model.Command;
import org.example.model.Packet;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class JoinGroupRequestPacket extends Packet {
    String groupId;
    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_REQUEST;
    }
}
