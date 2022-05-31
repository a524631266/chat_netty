package org.example.client.handler.simple;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.example.codec.PacketCodeC;
import org.example.codec.line.model.MessageReqPacket;
import org.example.codec.line.model.MessageRespPacket;
import org.example.codec.model.LoginRequestPacket;
import org.example.codec.model.LoginResponsePacket;
import org.example.codec.model.Packet;
import org.example.common.ChatConfiguration;

import java.util.Date;
import java.util.UUID;

@Log4j2
public class MessageClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 出现粘包和拆包问题！！！！！
        log.info(msg);
        if(msg instanceof ByteBuf) {
            ByteBuf message = (ByteBuf) msg;
            Packet request = PacketCodeC.getInstance().decode(message);
            if(request instanceof MessageRespPacket) {
                MessageRespPacket response = (MessageRespPacket) request;
                log.info("服务器返回数据:" + response.getMessage());
            } else {
                ctx.fireChannelRead(msg);
            }
        }

    }

}
