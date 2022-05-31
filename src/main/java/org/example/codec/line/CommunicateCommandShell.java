package org.example.codec.line;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.example.codec.PacketCodeC;
import org.example.codec.line.model.MessageReqPacket;
import org.example.codec.model.LoginRequestPacket;
import org.example.codec.model.communicate.PointToPointCommunicateRequestPacket;
import org.example.common.ChatConfiguration;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CommunicateCommandShell {
    public static void startThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!ChatConfiguration.hasLogin(channel)) {
                    System.out.print("输入用户名:");
                    Scanner sc = new Scanner(System.in);
                    String userName = sc.nextLine();
                    System.out.print("输入密码:");
                    sc = new Scanner(System.in);
                    String password = sc.nextLine();
                    LoginRequestPacket packet = new LoginRequestPacket();
                    packet.setUsername(userName);
                    packet.setPassword(password);
                    channel.writeAndFlush(packet);
                    tryToSleep();
                } else {
                    System.out.print("输入对方的用户id:  > ");
                    Scanner sc = new Scanner(System.in);
                    String toUserId = sc.nextLine();
                    System.out.print("输入消息: >  ");
                    sc = new Scanner(System.in);
                    String message = sc.nextLine();
                    PointToPointCommunicateRequestPacket packet = new PointToPointCommunicateRequestPacket();
                    packet.setToUserId(toUserId);
                    packet.setMessage(message);
                    channel.writeAndFlush(packet);
                }
            }
        }).start();
    }

    private static void tryToSleep() {

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
