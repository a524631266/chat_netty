package org.example.model.group.querygroup;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.model.Command;
import org.example.model.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
public class QueryAllGroupRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.QUERY_ALL_GROUP_REQUEST;
    }
}
