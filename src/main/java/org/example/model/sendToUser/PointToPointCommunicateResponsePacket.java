package org.example.model.sendToUser;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.model.Command;
import org.example.model.Packet;

@EqualsAndHashCode(callSuper = true)
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
