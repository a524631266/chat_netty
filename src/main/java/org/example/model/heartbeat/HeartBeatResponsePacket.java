package org.example.model.heartbeat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.model.Command;
import org.example.model.Packet;

/**
 * 简单的全局用户信息表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HeartBeatResponsePacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_RESPONSE;
    }
}
