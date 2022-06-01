package org.example.config;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import org.example.server.session.model.CommunicateContext;

import java.util.HashMap;

public abstract class ChatConfiguration extends BaseConfiguration{
//    public static String ChatServerIp = "192.168.0.102";
//    public static String ChatServerIp = "183.158.71.64";
//    public static String ChatServerIp = "192.168.8.172";
//    public static String ChatServerIp = "localhost";
    public static String ChatServerIp = "test.zll0428.fun";
    public static Integer ChatServerPort = 8082;
    public static String modeStr = "client";


    public static ChatOption<String> empty = ChatOptionBuilder.builder(null).defaultValue(null);
    public static ChatOption<String> serverIp = ChatOptionBuilder.builder("sc.ip").defaultValue(ChatServerIp);
    public static ChatOption<Integer> serverPort = ChatOptionBuilder.builder("sc.port").defaultValue(ChatServerPort);
    public static ChatOption<String> runMode = ChatOptionBuilder.builder("mode").defaultValue(modeStr);

    public static AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");


    public ChatConfiguration() {
        super(new HashMap<>());
    }



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

    /**
     * 初始化每个子类的
     */
    public abstract ChatConfiguration init();
}
