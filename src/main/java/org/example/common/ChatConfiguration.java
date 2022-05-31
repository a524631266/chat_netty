package org.example.common;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

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
}
