package org.example.model;

import org.example.model.getuserinfos.GlobalUserInfoRequestPacket;
import org.example.model.getuserinfos.GlobalUserInfoResponsePacket;
import org.example.model.group.creategroup.CreateGroupRequestPacket;
import org.example.model.group.creategroup.CreateGroupResponsePacket;
import org.example.model.group.joingroup.JoinGroupRequestPacket;
import org.example.model.group.message.MessageGroupRequestPacket;
import org.example.model.group.message.MessageGroupResponsePacket;
import org.example.model.group.querygroup.QueryGroupRequestPacket;
import org.example.model.group.querygroup.QueryGroupResponsePacket;
import org.example.model.heartbeat.HeartBeatRequestPacket;
import org.example.model.heartbeat.HeartBeatResponsePacket;
import org.example.model.login.LoginRequestPacket;
import org.example.model.login.LoginResponsePacket;
import org.example.model.message.MessageReqPacket;
import org.example.model.message.MessageRespPacket;
import org.example.model.sendToUser.PointToPointCommunicateRequestPacket;
import org.example.model.sendToUser.PointToPointCommunicateResponsePacket;

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

    Byte HEARTBEAT_REQUEST = 11; // 心跳包保证系统稳定
    Byte HEARTBEAT_RESPONSE = 12; // 心跳包保证系统稳定

    Byte JOIN_GROUP_REQUEST = 13; // 请求登陆group id
    Byte JOIN_GROUP_RESPONSE = 14; // 返回登陆

    Byte QUERY_GROUP_REQUEST = 15; // 请求登陆group id
    Byte QUERY_GROUP_RESPONSE = 16; // 返回登陆

    Byte MESSAGE_GROUP_REQUEST = 17; // message请求指令
    Byte MESSAGE_GROUP_RESPONSE = 18; // message响应指令


    static Class<? extends Packet> getCommand(byte command) {
        if (command == Command.LOGIN_REQUEST) {
            return LoginRequestPacket.class;
        } else if (command == Command.LOGIN_RESPONSE) {
            return LoginResponsePacket.class;
        } else if (command == Command.MESSAGE_REQUEST) {
            return MessageReqPacket.class;
        } else if (command == Command.MESSAGE_RESPONSE) {
            return MessageRespPacket.class;
        } else if (command == Command.POINT_TO_POINT_MESSAGE_RESPONSE) {
            return PointToPointCommunicateResponsePacket.class;
        } else if (command == Command.POINT_TO_POINT_MESSAGE_REQUEST) {
            return PointToPointCommunicateRequestPacket.class;
        } else if (command == Command.GLOBAL_USER_INFO_MESSAGE_RESPONSE) {
            return GlobalUserInfoResponsePacket.class;
        } else if (command == Command.GLOBAL_USER_INFO_MESSAGE_REQUEST) {
            return GlobalUserInfoRequestPacket.class;
        } else if (command == Command.CREATE_GROUP_RESPONSE) {
            return CreateGroupResponsePacket.class;
        } else if (command == Command.CREATE_GROUP_REQUEST) {
            return CreateGroupRequestPacket.class;
        } else if (command == Command.HEARTBEAT_RESPONSE) {
            return HeartBeatResponsePacket.class;
        } else if (command == Command.HEARTBEAT_REQUEST) {
            return HeartBeatRequestPacket.class;
        } else if (command == Command.JOIN_GROUP_RESPONSE) {
            return JoinGroupRequestPacket.class;
        } else if (command == Command.JOIN_GROUP_REQUEST) {
            return JoinGroupRequestPacket.class;
        } else if (command == Command.QUERY_GROUP_RESPONSE) {
            return QueryGroupResponsePacket.class;
        } else if (command == Command.QUERY_GROUP_REQUEST) {
            return QueryGroupRequestPacket.class;
        } else if (command == Command.MESSAGE_GROUP_REQUEST) {
            return MessageGroupRequestPacket.class;
        } else if (command == Command.MESSAGE_GROUP_RESPONSE) {
            return MessageGroupResponsePacket.class;
        }
        throw new RuntimeException("需要设置命令转换逻辑");
    }
}

