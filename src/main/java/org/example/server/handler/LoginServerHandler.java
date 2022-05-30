package org.example.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.example.codec.PacketCodeC;
import org.example.codec.model.Packet;

public class LoginServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf loginBuf = (ByteBuf) msg;

        Packet decode = PacketCodeC.getInstance().decode(loginBuf);
        if(decode == null) {
            // 如果为空则返回下i个pipeline handler
            ctx.fireChannelRead(msg);
        }
        System.out.println("登陆账号:" + decode);
    }
}
