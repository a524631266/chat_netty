package org.example.model.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.model.Command;
import org.example.model.Packet;
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageReqPacket extends Packet {
    private String message;
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
