package org.example.client.command;

import io.netty.channel.Channel;
import org.example.codec.model.getuserinfos.GlobalUserInfoRequestPacket;
import org.example.codec.model.sendToUser.PointToPointCommunicateRequestPacket;

import java.util.Scanner;

public class GlobalUserInfoCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        GlobalUserInfoRequestPacket packet = new GlobalUserInfoRequestPacket();
        channel.writeAndFlush(packet);

    }
}
