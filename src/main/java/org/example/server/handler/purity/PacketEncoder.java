package org.example.server.handler.purity;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.log4j.Log4j2;
import org.example.codec.PacketCodeC;
import org.example.codec.model.Packet;

/**
 * 可以同时支持 login response 和 MessageRespPacket的总出口
 */
@Log4j2
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        log.info(msg);
        PacketCodeC.getInstance().encode(out, msg);
    }
}
