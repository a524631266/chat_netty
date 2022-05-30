package org.example.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import org.example.codec.PacketCodeC;
import org.example.codec.model.LoginRequestPacket;
import org.example.codec.model.LoginResponsePacket;
import org.example.codec.model.Packet;
import org.example.common.ChatConfiguration;

import java.util.Date;

public class LoginServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf loginBuf = (ByteBuf) msg;
        // 如果下游还是要重新读取的话得mark，再重新设置
        ((ByteBuf) msg).markReaderIndex();
        Packet decode = PacketCodeC.getInstance().decode(loginBuf);

        if (decode instanceof LoginRequestPacket) {
            LoginRequestPacket request = (LoginRequestPacket) decode;
            LoginResponsePacket response = new LoginResponsePacket();
            response.setUserId(request.getUserId());
            response.setPassword(request.getPassword());
            response.setUsername(request.getUsername());
            if (valid(request)) { // 校验是否成功
                System.out.println("[" + new Date() + "] : 校验成功， 登陆账号:" + decode + " :: channel" + ctx.channel());
                ChatConfiguration.loginAttr(ctx.channel());

                response.setSuccess(true);
            } else {
                response.setSuccess(false);
                System.out.println("[" + new Date() + "] : 校验失败， 登陆账号确定好账号和密码");
            }
            ctx.channel().writeAndFlush(PacketCodeC.getInstance().encode(ctx.alloc().buffer(), response));
        }else {
            ((ByteBuf) msg).resetReaderIndex();
            ctx.fireChannelRead(msg);
        }

    }

    private boolean valid(LoginRequestPacket decode) {
        return true;
    }

    /**
     * 客户端断链之后会在server端获取到当前的状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[" + new Date() + "] : 关闭链接" + ctx.attr(ChatConfiguration.LOGIN));
    }
}
