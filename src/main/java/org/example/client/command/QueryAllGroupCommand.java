package org.example.client.command;

import io.netty.channel.Channel;
import org.example.model.group.querygroup.QueryAllGroupRequestPacket;

import java.util.Scanner;

public class QueryAllGroupCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        QueryAllGroupRequestPacket packet = new QueryAllGroupRequestPacket();
        channel.writeAndFlush(packet);
    }
}
