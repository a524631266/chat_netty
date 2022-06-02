package org.example.codec.line;

import io.netty.channel.Channel;
import org.example.client.command.CommandEnum;
import org.example.client.command.ConsoleCommand;
import org.example.client.command.ConsoleCommandManager;
import org.example.model.login.LoginRequestPacket;
import org.example.config.ChatConfiguration;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CommunicateCommandShell {
    public static void startThread(Channel channel) {
        new Thread(() -> {

            showCommandInfo();

            while (!Thread.interrupted() && channel.isActive()) {
                if (!ChatConfiguration.hasLogin(channel)) {
                    System.out.print("输入用户名: > ");
                    Scanner sc = new Scanner(System.in);
                    String userName = sc.nextLine();
                    System.out.print("输入密码: > ");
                    sc = new Scanner(System.in);
                    String password = sc.nextLine();
                    LoginRequestPacket packet = new LoginRequestPacket();
                    packet.setUsername(userName);
                    packet.setPassword(password);
                    channel.writeAndFlush(packet);
                    tryToSleep();
                } else {
                    ConsoleCommandManager commandManager = new ConsoleCommandManager();
                    commandManager.exec(new Scanner(System.in), channel);
                }
            }
        }).start();
    }

    private static void showCommandInfo() {
        Map<CommandEnum, ConsoleCommand> consoleCommandMap = new ConsoleCommandManager().getConsoleCommandMap();
        for (Map.Entry<CommandEnum, ConsoleCommand> entry : consoleCommandMap.entrySet()) {
            CommandEnum key = entry.getKey();
            System.out.printf("指令: [%s], 功能 [ %s ]%n", key.getCommand(), key.getName());
        }
    }

    private static void tryToSleep() {

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
