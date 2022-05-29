package org.example.codec.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

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
     * @return
     */
    public abstract Byte getCommand();
}
