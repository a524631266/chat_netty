package org.example.client.handler.purity;

import com.google.common.base.Joiner;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;
import org.example.codec.model.getuserinfos.GlobalUserInfoResponsePacket;
import org.example.codec.model.getuserinfos.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Log4j2
public class GlobalUserInfoResponseHandler extends SimpleChannelInboundHandler<GlobalUserInfoResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GlobalUserInfoResponsePacket msg) throws Exception {
        System.out.println(Joiner.on(":").join(msg.getUserInfo()).toLowerCase(Locale.ROOT));
    }

    public static void main(String[] args) {
        List<UserInfo> users  = new ArrayList<>();
        users.add(new UserInfo("123", "zll"));
        users.add(new UserInfo("1232", "zll2"));
        String result = Joiner.on(":").join(users).toLowerCase(Locale.ROOT);
        System.out.println(result);
    }
}
