package org.example.codec.model.communicate;

import lombok.Data;
import org.example.codec.model.Command;
import org.example.codec.model.Packet;

import org.example.codec.model.Packet;
@Data
public class PointToPointCommunicateResponsePacket extends Packet {
    String fromUserId;
    String fromUserName;
    String message;

    @Override
    public Byte getCommand() {
        return Command.POINT_TO_POINT_MESSAGE_RESPONSE;
    }
}
