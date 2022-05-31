package org.example.client.command;

import io.netty.channel.Channel;
import org.example.codec.model.communicate.PointToPointCommunicateRequestPacket;

import java.util.Scanner;

public class SendToUserCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
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
