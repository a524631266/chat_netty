package org.example.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.log4j.Log4j2;
import org.example.codec.PacketCodeC;
import org.example.codec.model.Packet;

import java.util.List;

@Log4j2
public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Packet decode = PacketCodeC.getInstance().decode(in);
        log.info(decode);
        out.add(decode);
    }
}
