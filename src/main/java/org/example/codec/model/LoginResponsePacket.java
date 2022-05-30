package org.example.codec.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

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
