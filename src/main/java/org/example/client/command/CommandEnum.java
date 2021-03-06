package org.example.client.command;

import javax.annotation.Nullable;

public enum CommandEnum {
    SEND_TO_USER("sendToUser", "单聊"),
    LOGOUT("exit", "登出"),
    GET_USER("getUser", "获取用户信息"),
    CREATE_GROUP("createGroup", "创建组"),
    QUERY_ALL_GROUP("queryAllGroup", "查询组"),
    JOIN_GROUP("joinGroup", "加入组"),
    TALK_GROUP("talkGroup", "组内通话"),
//    CREATE_GROUP("uniGroup", "关联组"),
    ;
    String command;
    String name;
    CommandEnum(String command, String name) {
        this.command = command;
        this.name = name;
    }

    @Nullable
    public static CommandEnum codeOf(String command) {
        for (CommandEnum commandEnum : CommandEnum.values()) {
            if(commandEnum.command.equals(command)){
                return commandEnum;
            }
        }
        return null;
    }

    public String getCommand() {
        return command;
    }

    public String getName() {
        return name;
    }
}
