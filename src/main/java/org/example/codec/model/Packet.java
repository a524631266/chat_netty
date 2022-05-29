package org.example.codec.model;

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
