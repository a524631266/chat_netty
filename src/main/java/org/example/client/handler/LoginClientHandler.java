package org.example.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.example.codec.PacketCodeC;
import org.example.codec.model.LoginRequestPacket;
import org.example.codec.model.Packet;

import java.util.UUID;

public class LoginClientHandler extends ChannelInboundHandlerAdapter {
    public static String password = "12345";
    public static String userName = "zll";
    public static Integer userId = UUID.randomUUID().hashCode();
    /**
     * 登陆请求
     *         // 三种等价的分配器功能
     *         //  ctx.channel().  [config().getAllocator()]
     *  （1）       ctx.channel().alloc().buffer();
     *   （2）      ctx.channel().config().getAllocator()
     *   （3）      ctx.alloc().buffer();
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setPassword(password);
        packet.setUserId(userId);
        packet.setUsername(userName);

        ByteBuf buffer = PacketCodeC.getInstance().encode(ctx.alloc().buffer(),packet);

        ctx.channel().writeAndFlush(buffer);
        // 在 writeAndFlush过程之后会自动release() 所以不需要release
    }
}
