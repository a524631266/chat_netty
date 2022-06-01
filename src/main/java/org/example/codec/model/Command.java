package org.example.codec.model;

public interface Command {
    Byte LOGIN_REQUEST = 1; // 请求指令
    Byte LOGIN_RESPONSE = 2; // 响应指令

    Byte MESSAGE_REQUEST = 3; // message请求指令
    Byte MESSAGE_RESPONSE = 4; // message响应指令

    Byte POINT_TO_POINT_MESSAGE_REQUEST = 5; // 点对点消息发送请求
    Byte POINT_TO_POINT_MESSAGE_RESPONSE = 6; // 点对点消息发送响应

    Byte GLOBAL_USER_INFO_MESSAGE_REQUEST = 7; // 全局用户信息  请求指令
    Byte GLOBAL_USER_INFO_MESSAGE_RESPONSE = 8; // 全局用户信息 返回包

    Byte CREATE_GROUP_REQUEST = 9; // 全局用户信息  请求指令
    Byte CREATE_GROUP_RESPONSE = 10; // 全局用户信息 返回包
}

