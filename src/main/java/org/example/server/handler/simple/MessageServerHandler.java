package org.example.server.handler.simple;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.example.codec.PacketCodeC;
import org.example.codec.line.model.MessageReqPacket;
import org.example.codec.line.model.MessageRespPacket;
import org.example.codec.model.LoginResponsePacket;
import org.example.codec.model.Packet;
import org.example.common.ChatConfiguration;

import java.util.Date;

@Log4j2
public class MessageServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 出现粘包和拆包问题！！！！！
        log.info("messageServer" + msg);
        if(msg instanceof ByteBuf) {
            ByteBuf message = (ByteBuf) msg;
            Packet request = PacketCodeC.getInstance().decode(message);
            if(request instanceof MessageReqPacket) {
                MessageReqPacket target = (MessageReqPacket) request;
                System.out.println("[" + new Date() + "]: " +  target.getMessage());

                MessageRespPacket packet = new MessageRespPacket();
                packet.setMessage("服务器接收到: " + target.getMessage());
                // 编码返回信息：
                ByteBuf buf = PacketCodeC.getInstance().encode(ctx.alloc().buffer(), packet);
                ctx.writeAndFlush(buf);
            } else {
                ctx.fireChannelRead(msg);
            }
        }

    }

}
