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
    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
