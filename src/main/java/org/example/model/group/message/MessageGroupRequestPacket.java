package org.example.model.group.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.model.Command;
import org.example.model.Packet;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class MessageGroupRequestPacket extends Packet {
    String groupId;
    String message;
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_GROUP_REQUEST;
    }
}
