package org.example.common;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import org.example.server.session.model.CommunicateContext;

public class ChatConfiguration {
//    public static String ChatServerIp = "192.168.0.102";
    public static String ChatServerIp = "localhost";
    public static Integer ChatServerPort = 8080;
    public static AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");



    public static void loginAttr(Channel channel) {
        channel.attr(LOGIN).set(true);
    }

    public static Boolean hasLogin(Channel channel) {
        return channel.attr(LOGIN).get() != null;
    }


    public static AttributeKey<CommunicateContext> SERVER_SIDE_LOGIN = AttributeKey.newInstance("serverSideLogin");

    public static void serverSideLogin(Channel channel, CommunicateContext context) {
        channel.attr(SERVER_SIDE_LOGIN).set(context);
    }

    public static Boolean serverSideHasLogin(Channel channel) {
        return channel.attr(SERVER_SIDE_LOGIN).get() != null;
    }

    public static CommunicateContext getSessionContext(Channel channel) {
        return channel.attr(SERVER_SIDE_LOGIN).get();
    }
}
