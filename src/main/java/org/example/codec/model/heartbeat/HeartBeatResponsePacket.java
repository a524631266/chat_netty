package org.example.codec.model.heartbeat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.codec.model.Command;
import org.example.codec.model.Packet;
import org.example.server.session.model.Session;

import java.util.List;

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
