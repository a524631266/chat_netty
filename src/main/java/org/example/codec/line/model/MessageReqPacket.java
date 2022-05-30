package org.example.codec.line.model;

import lombok.Data;
import org.example.codec.model.Command;
import org.example.codec.model.Packet;

@Data
public class MessageReqPacket extends Packet {
    private String message;
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
