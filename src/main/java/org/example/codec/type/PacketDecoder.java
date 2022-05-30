package org.example.codec.type;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.example.codec.PacketCodeC;
import org.example.codec.model.Packet;


import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    /**
     * 特性是什么？
     *
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 添加到out中，会自动往下传送
        Packet packet = PacketCodeC.getInstance().decode(in);
        System.out.println("decoder: " + packet);
        out.add(packet);
    }
}
