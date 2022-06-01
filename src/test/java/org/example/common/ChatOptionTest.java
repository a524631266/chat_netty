package org.example.common;

import org.example.config.ChatOption;
import org.example.config.ChatOptionBuilder;
import org.junit.Test;

public class ChatOptionTest {
    @Test
    public void test(){
        ChatOption<Integer> portConfig = ChatOptionBuilder.builder("port")
                .defaultValue(1);
        System.out.println(portConfig.defaultValue());

        ChatOption<String> ipConfig = ChatOptionBuilder.builder("ip")
                .defaultValue("123.123.123.123");

    }
}