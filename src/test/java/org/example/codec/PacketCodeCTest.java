package org.example.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import org.example.model.LoginRequestPacket;
import org.example.model.Packet;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class PacketCodeCTest {
    public static String password = "12345";
    public static String userName = "zll";
    @Test
    public void encode() throws IllegalAccessException {
        PacketCodeC codec = new PacketCodeC();
        Integer userId = UUID.randomUUID().hashCode();
        System.out.println("userid" + userId);
        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setPassword(password);
        packet.setUserId(userId);
        packet.setUsername(userName);
        ByteBuf byteBuf = codec.encode(packet);

        Packet decode = codec.decode(byteBuf);

        Assert.assertTrue(decode instanceof LoginRequestPacket);
        LoginRequestPacket decode2 = (LoginRequestPacket) decode;
        Assert.assertEquals(password, decode2.getPassword());
        Assert.assertEquals(userId, decode2.getUserId());
        Assert.assertEquals(userName, decode2.getUsername());

        byteBuf.release();
    }

    @Test
    public void decode() {
        Integer userId = UUID.randomUUID().hashCode();
        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setPassword(password);
        packet.setUserId(userId);
        packet.setUsername(userName);

        byte[] bytes = JSON.toJSONBytes(packet);


        Object o = JSON.parseObject(bytes, LoginRequestPacket.class);
        System.out.println(o);
    }
}