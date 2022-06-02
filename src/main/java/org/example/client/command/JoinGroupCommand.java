package org.example.client.command;

import io.netty.channel.Channel;
import org.example.model.group.joingroup.JoinGroupRequestPacket;

import java.util.Scanner;

public class JoinGroupCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入要加入的组id > ");
        Scanner sc = new Scanner(System.in);
        String groupId = sc.nextLine();
        JoinGroupRequestPacket packet = new JoinGroupRequestPacket();
        packet.setGroupId(groupId);
        channel.writeAndFlush(packet);
    }
}
