package org.example.model.group.querygroup;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.model.Command;
import org.example.model.Packet;

import java.util.List;

/**
 * 简单的全局用户信息表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryAllGroupResponsePacket extends Packet {
    List<GroupInfo> groupInfo;

    @Override
    public Byte getCommand() {
        return Command.QUERY_ALL_GROUP_RESPONSE;
    }
}
