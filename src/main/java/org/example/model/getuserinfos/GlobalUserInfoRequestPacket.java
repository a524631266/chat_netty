package org.example.model.getuserinfos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.model.Command;
import org.example.model.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
public class GlobalUserInfoRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.GLOBAL_USER_INFO_MESSAGE_REQUEST;
    }
}
