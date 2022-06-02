package org.example.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JSONSerializer implements Serializer{
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON2;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
//        JSON.parseObject(bytes, new TypeReference<clazz>(){})
        Object o = JSONObject.parseObject(bytes, clazz);
        return JSONObject.parseObject(bytes, clazz);
    }
}
