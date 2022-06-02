package org.example.model;

import lombok.Data;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Jacksonized
public abstract class Packet {
    /**
     * 协议。 默认为1 不进行扩展
     */
    private Byte version = 1;

    /**
     *
     * @return 返回指令编号
     */
    public abstract Byte getCommand();
}
