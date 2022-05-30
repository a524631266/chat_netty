package org.example.codec.model;

public interface Command {
    Byte LOGIN_REQUEST = 1; // 请求指令
    Byte LOGIN_RESPONSE = 2; // 响应指令

    Byte MESSAGE_REQUEST = 3; // message请求指令
    Byte MESSAGE_RESPONSE = 4; // message响应指令
}

