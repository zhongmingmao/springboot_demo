package me.zhongmingmao.redis.serializer;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class RedisObjectSerializer implements RedisSerializer<Object> {
    
    private static final byte[] EMPTY_ARRAY = new byte[0];
    
    private Converter<Object, byte[]> serializer = new SerializingConverter();
    private Converter<byte[], Object> deserializer = new DeserializingConverter();
    
    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if (null == o) {
            return EMPTY_ARRAY;
        }
        try {
            return serializer.convert(o);
        } catch (Exception e) {
            return EMPTY_ARRAY;
        }
        
    }
    
    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (null == bytes || bytes.length == 0) {
            return EMPTY_ARRAY;
        }
        try {
            return deserializer.convert(bytes);
        } catch (Exception e) {
            return EMPTY_ARRAY;
        }
    }
}
