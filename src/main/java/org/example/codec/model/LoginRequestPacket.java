package org.example.codec.model;

import lombok.Data;

@Data
public class LoginRequestPacket extends Packet{
    private Integer userId;
    private String username;
    private String password;
    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
