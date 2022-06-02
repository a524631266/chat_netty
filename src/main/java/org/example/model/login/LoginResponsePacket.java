package org.example.model.login;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.model.Command;
import org.example.model.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponsePacket extends Packet {
    private Integer userId;
    //    @JSONField
    private String username;

    private String password;

    private Boolean success;

    /**
     * 原因
     */
    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
