package org.example.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.example.codec.PacketCodeC;
import org.example.codec.model.LoginRequestPacket;
import org.example.codec.model.LoginResponsePacket;
import org.example.codec.model.Packet;

public class LoginServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf loginBuf = (ByteBuf) msg;

        Packet decode = PacketCodeC.getInstance().decode(loginBuf);
        if (decode == null) {
            // 如果为空则返回下i个pipeline handler
            ctx.fireChannelRead(msg);
        }
        if (decode instanceof LoginRequestPacket) {
            LoginRequestPacket request = (LoginRequestPacket) decode;
            LoginResponsePacket response = new LoginResponsePacket();
            response.setUserId(request.getUserId());
            response.setPassword(request.getPassword());
            response.setUsername(request.getUsername());
            if (valid(request)) { // 校验是否成功
                System.out.println("校验成功， 登陆账号:" + decode);
                response.setSuccess(true);
            } else {
                response.setSuccess(false);
                System.out.println("校验失败， 登陆账号确定好账号和密码");
            }
            ctx.channel().writeAndFlush(PacketCodeC.getInstance().encode(ctx.alloc().buffer(), response));
        }

    }

    private boolean valid(LoginRequestPacket decode) {
        return true;
    }
}
