package org.example.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class LoginRequestPacket extends Packet{
//    @JSONField
    private Integer userId;
//    @JSONField
    private String username;
    @JSONField
    private String password;
    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
