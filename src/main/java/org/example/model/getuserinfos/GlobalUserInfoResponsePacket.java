package org.example.model.getuserinfos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.model.Command;
import org.example.model.Packet;

import java.util.List;
/**
 * 简单的全局用户信息表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GlobalUserInfoResponsePacket extends Packet {
    List<UserInfo> userInfo;
    @Override
    public Byte getCommand() {
        return Command.GLOBAL_USER_INFO_MESSAGE_RESPONSE;
    }
}
