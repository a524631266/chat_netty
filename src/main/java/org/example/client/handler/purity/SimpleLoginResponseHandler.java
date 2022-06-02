package org.example.client.handler.purity;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;
import org.example.model.LoginResponsePacket;
import org.example.config.ChatConfiguration;

import java.util.UUID;

@Log4j2
public class SimpleLoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    public static String password = "12345";
    public static String userName = "zll";
    public static Integer userId = UUID.randomUUID().hashCode();

//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        LoginRequestPacket packet = new LoginRequestPacket();
//        packet.setPassword(password);
//        packet.setUserId(userId);
//        packet.setUsername(userName);
//        ByteBuf buffer = PacketCodeC.getInstance().encode(ctx.alloc().ioBuffer(),packet);
//        ctx.channel().writeAndFlush(buffer);
//    }

    /**
     * 有且只有一次。在验证阶段过程中
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        log.info(msg);
        ChatConfiguration.loginAttr(ctx.channel());
    }
}
