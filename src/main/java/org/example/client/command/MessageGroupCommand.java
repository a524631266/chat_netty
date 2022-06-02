package org.example.client.command;


import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import org.example.client.mananger.MessageStateManager;
import org.example.common.TimeUtils;
import org.example.model.group.message.MessageGroupRequestPacket;
import org.example.model.group.message.MessageGroupResponsePacket;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MessageGroupCommand implements ConsoleCommand {


    private static String exit = "exit";
    public static String chooseGroup = "请选择组id： [输入" + exit + "退出群]";
    public static String prompt = "》》》》》 ";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print(chooseGroup);
        Scanner sc = new Scanner(System.in);
        String groupId = sc.nextLine();


        while (getState(groupId, channel)) {
            System.out.print(prompt);
            sc = new Scanner(System.in);
            String message = sc.nextLine();
            if (exit.equals(message)) {
                break;
            }
            MessageGroupRequestPacket packet = new MessageGroupRequestPacket();
            packet.setGroupId(groupId);
            packet.setMessage(message);
            channel.writeAndFlush(packet);
            TimeUtils.sleep(1, TimeUnit.SECONDS);
        }
        System.out.println("跳出组 " + groupId);
    }

    /**
     * 获取是否可以正常执行的state
     * 线默认假设全部都可以登陆
     * @param groupId 查询当前组id的状态
     * @return 默认返回true TODO
     */
    private boolean getState(String groupId, Channel channel) {
        return true;
    }


}
