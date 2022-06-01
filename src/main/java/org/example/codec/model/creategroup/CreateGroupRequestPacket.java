package org.example.codec.model.creategroup;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.codec.model.Command;
import org.example.codec.model.Packet;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateGroupRequestPacket extends Packet {
    List<String> userIds;
    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
