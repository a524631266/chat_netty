package org.example.model.sendToUser;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.model.Command;
import org.example.model.Packet;
@EqualsAndHashCode(callSuper = true)
@Data
public class PointToPointCommunicateRequestPacket extends Packet {
    String toUserId;
    String message;
    @Override
    public Byte getCommand() {
        return Command.POINT_TO_POINT_MESSAGE_REQUEST;
    }
}
