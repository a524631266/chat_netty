package org.example.model.group.querygroup;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.model.Command;
import org.example.model.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
public class QueryGroupRequestPacket extends Packet {
    String groupId;
    @Override
    public Byte getCommand() {
        return Command.QUERY_GROUP_REQUEST;
    }
}
