package org.example.codec.line;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.example.codec.PacketCodeC;
import org.example.codec.line.model.MessageReqPacket;
import org.example.codec.line.model.MessageRespPacket;
import org.example.common.ChatConfiguration;

import java.util.Scanner;

public class LineCommandShell {
    public static void startThread(Channel channel) {
        new Thread(() -> {
            while(!Thread.interrupted()){
                if(ChatConfiguration.hasLogin(channel)){
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();
                    MessageReqPacket packet = new MessageReqPacket();
                    packet.setMessage(line);
                    ByteBuf buf = PacketCodeC.getInstance().encode(channel.alloc().buffer(), packet);
                    channel.writeAndFlush(buf);
                };
            }
        }).start();
    }
}
