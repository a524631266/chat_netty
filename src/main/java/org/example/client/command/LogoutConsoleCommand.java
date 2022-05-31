package org.example.client.command;

import io.netty.channel.Channel;
import org.example.codec.model.communicate.PointToPointCommunicateRequestPacket;

import java.util.Scanner;

public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.exit(0);
    }
}
