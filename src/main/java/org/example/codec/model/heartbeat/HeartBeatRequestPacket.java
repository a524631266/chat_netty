package org.example.codec.model.heartbeat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.codec.model.Command;
import org.example.codec.model.Packet;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_REQUEST;
    }
}
