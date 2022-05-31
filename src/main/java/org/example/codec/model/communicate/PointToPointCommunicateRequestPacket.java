package org.example.codec.model.communicate;

import lombok.Data;
import org.example.codec.model.Command;
import org.example.codec.model.Packet;
@Data
public class PointToPointCommunicateRequestPacket extends Packet {
    String toUserId;
    String message;
    @Override
    public Byte getCommand() {
        return Command.POINT_TO_POINT_MESSAGE_REQUEST;
    }
}
