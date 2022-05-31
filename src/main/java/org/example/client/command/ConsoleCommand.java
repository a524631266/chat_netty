package org.example.client.command;

import io.netty.channel.Channel;

import java.util.Scanner;

public interface ConsoleCommand<Request, Response> {

    void exec(Scanner scanner, Channel channel);
}
