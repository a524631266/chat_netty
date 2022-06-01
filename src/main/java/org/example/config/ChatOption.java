package org.example.config;

/**
 * 需要提供什么样的能力呢？
 *
 * @param <T>
 */
public class ChatOption<T> {
    String key;
    Class<?> aClass;
    T defaultValue;
    String description;
    public static String emptyDescription = "";

    public ChatOption(String key, Class<?> aClass, T defaultValue, String description) {
        this.key = key;
        this.aClass = aClass;
        this.defaultValue = defaultValue;
        this.description = description;
    }

    public String key() {
        return key;
    }

    public Class<?> aClass() {
        return aClass;
    }

    public T defaultValue() {
        return defaultValue;
    }

    public String description() {
        return description;
    }
}
