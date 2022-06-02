package org.example.client.command;

import com.google.common.base.Splitter;
import io.netty.channel.Channel;
import org.example.model.creategroup.CreateGroupRequestPacket;

import java.util.Scanner;

public class CreateGroupCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入需要验证的消息: 【多个以逗号分隔】 > ");
        Scanner sc = new Scanner(System.in);
        String userIds = sc.nextLine();
        CreateGroupRequestPacket packet = new CreateGroupRequestPacket();
        packet.setUserIds(Splitter.on(',').splitToList(userIds));
        channel.writeAndFlush(packet);
    }
}
