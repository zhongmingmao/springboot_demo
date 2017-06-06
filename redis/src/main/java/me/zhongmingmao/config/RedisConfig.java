package me.zhongmingmao.config;

import me.zhongmingmao.domain.Person;
import me.zhongmingmao.domain.User;
import me.zhongmingmao.redis.serializer.RedisObjectSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }
    
    @Bean
    public RedisTemplate<String, User> userRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, User> userRedisTemplate = new RedisTemplate<>();
        userRedisTemplate.setConnectionFactory(factory);
        userRedisTemplate.setKeySerializer(new StringRedisSerializer()); // 内置的StringRedisSerializer
        userRedisTemplate.setValueSerializer(new RedisObjectSerializer()); // 自定义的RedisObjectSerializer
        return userRedisTemplate;
    }
    
    
    @Bean
    public RedisTemplate<String, Person> personRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Person> personRedisTemplate = new RedisTemplate<>();
        personRedisTemplate.setConnectionFactory(factory);
        personRedisTemplate.setKeySerializer(new StringRedisSerializer()); // 内置的StringRedisSerializer
        personRedisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(Person.class)); // 内置的Jackson2JsonRedisSerializer
        return personRedisTemplate;
    }
}
