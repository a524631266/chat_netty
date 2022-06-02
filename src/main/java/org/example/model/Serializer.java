package org.example.model;

public interface Serializer {
    /**
     * 设置 默认的 序列化工具
     */
    byte JSON_SERIALIZER = 1;
    Serializer DEFAULT = new JSONSerializer();
    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();
    /**
     * Java 对象序列化成二进制对象
     */
    byte[] serialize(Object object);
    /**
     * Java 反序列化
     */
    <T> T deserialize(Class<T> clazz , byte[] bytes);
}
