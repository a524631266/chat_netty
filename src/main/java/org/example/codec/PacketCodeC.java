package org.example.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.example.codec.line.model.MessageReqPacket;
import org.example.codec.line.model.MessageRespPacket;
import org.example.codec.model.*;

public class PacketCodeC {

    public volatile static PacketCodeC INSTANCE;

    /**
     * double check 获取实例
     * @return
     */
    public static PacketCodeC getInstance(){
        if(INSTANCE == null) {
            synchronized (PacketCodeC.class){
                if(INSTANCE == null) {
                    INSTANCE = new PacketCodeC();
                }
            }
        }
        return INSTANCE;
    }

    public static final int MAGIC_NUMBER = 0x12345678;

    /**
     * 一个对象序列化为一个bytebuf对象
     *
     * @param packet
     * @return
     */
    public ByteBuf encode(Packet packet) {
        // 创建一个byteBuf 对象 【io是什么？？】
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        return encode(byteBuf, packet);
    }

    /**
     * 一个对象序列化为一个bytebuf对象
     *
     * @param packet
     * @return
     */
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        byte[] serialize = Serializer.DEFAULT.serialize(packet);
        // 编码过程方式
        byteBuf.writeInt(MAGIC_NUMBER); // 1
        byteBuf.writeByte(packet.getVersion()); // 2
        byteBuf.writeByte(Serializer.JSON_SERIALIZER);
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(serialize.length);
        byteBuf.writeBytes(serialize);
        return byteBuf.slice();
    }

    ;

    /**
     * 解码
     *
     * @param byteBuf
     * @return
     */
    public Packet decode(ByteBuf byteBuf) throws IllegalAccessException {
        //
        // 编码过程
        int magic = byteBuf.readInt();
        if (magic != MAGIC_NUMBER) {
            throw new IllegalAccessException("不是自定义的模数");
        }

        byte version = byteBuf.readByte();
        byte serializerAlgorithm = byteBuf.readByte();
        byte command = byteBuf.readByte();

        int dataLen = byteBuf.readInt();
        byte[] body = new byte[dataLen];
        byteBuf.readBytes(body);

        Serializer serializer = getSerializer(serializerAlgorithm);
        Class<? extends Packet> requestType = getCommand(command);
        if(requestType != null && serializer != null) {
            Packet packet = serializer.deserialize(requestType, body);
            packet.setVersion(version);
            return packet;
        }
        return null;

    }

    /**
     * 通过命令方式返回，不用 if else 判断
     * @param command
     * @return
     */
    private Class<? extends Packet> getCommand(byte command) {
        if(command == Command.LOGIN_REQUEST) {
            return LoginRequestPacket.class;
        } else if (command == Command.LOGIN_RESPONSE) {
            return LoginResponsePacket.class;
        } else if (command == Command.MESSAGE_REQUEST) {
            return MessageReqPacket.class;
        } else if (command == Command.MESSAGE_RESPONSE) {
            return MessageRespPacket.class;
        }
        // 返回 null 表示过滤
        return null;
    }

    private Serializer getSerializer(byte serializerAlgorithm) throws IllegalAccessException {
        if(serializerAlgorithm == Serializer.JSON_SERIALIZER) {
            return Serializer.DEFAULT;
        }
        // 返回 null 表示过滤
        return null;
    }


}
