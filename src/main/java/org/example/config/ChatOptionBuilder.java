package org.example.config;

import org.example.config.type.NullType;

import java.util.Objects;

public class ChatOptionBuilder {
    public String key;
    public ChatOptionBuilder(String key) {
        this.key = key;
    }
    public <T>  ChatOption<T> defaultValue(T v){
        return new ChatOption<T>(key, 
                Objects.isNull(v) ? NullType.class : v.getClass(),
                v, ChatOption.emptyDescription);
    }

    public static ChatOptionBuilder builder(String key){
        return new ChatOptionBuilder(key);
    }
}
