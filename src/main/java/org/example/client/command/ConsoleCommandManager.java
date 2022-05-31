package org.example.client.command;

import io.netty.channel.Channel;
import org.example.codec.model.communicate.PointToPointCommunicateRequestPacket;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleCommandManager implements ConsoleCommand {


    private Map<CommandEnum, ConsoleCommand> consoleCommandMap = new HashMap<>();
    public ConsoleCommandManager() {
        consoleCommandMap.put(CommandEnum.SEND_TO_USER, new SendToUserCommand());
        consoleCommandMap.put(CommandEnum.LOGOUT, new LogoutConsoleCommand());
    }
    private ConsoleCommand getConsoleCommand(String command) {
       return consoleCommandMap.get(CommandEnum.codeOf(command));
    }
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("设置指令: > ");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        ConsoleCommand consoleCommand = getConsoleCommand(command);

        if(consoleCommand != null) {
            consoleCommand.exec(scanner, channel);
        } else {
            System.err.println("无法识别 [" + command + "] 指令，请重新输入");
        }
    }

    public Map<CommandEnum, ConsoleCommand> getConsoleCommandMap() {
        return Collections.unmodifiableMap(consoleCommandMap);
    }
}
