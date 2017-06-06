package me.zhongmingmao;

import me.zhongmingmao.domain.Person;
import me.zhongmingmao.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate; // 使用了内置的StringRedisSerializer
    
    @Autowired
    private RedisTemplate<String, Person> personRedisTemplate; // 使用了内置的Jackson2JsonRedisSerializer
    
    @Autowired
    private RedisTemplate<String, User> userRedisTemplate; // 使用了自定义的RedisObjectSerializer
    
    @Test
    public void stringRedisTemplateTest() {
        Long id = 1L;
        String name = "zhongmingmao";
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set("name", name);
        assertEquals(name, valueOperations.get("name"));
    }
    
    @Test
    public void personRedisTemplateTest() {
        Long id = 1L;
        String name = "zhongmingmao";
        ValueOperations<String, Person> personValueOperations = personRedisTemplate.opsForValue();
        Person person = new Person(1L, name);
        personValueOperations.set(name, person);
        assertEquals(person, personValueOperations.get(name));
    }
    
    @Test
    public void userRedisTemplateTest() {
        Long id = 1L;
        String name = "zhongmingmao";
        ValueOperations<String, User> userValueOperations = userRedisTemplate.opsForValue();
        User user = new User(1L, name);
        userValueOperations.set(name, user);
        assertEquals(user, userValueOperations.get(name));
    }
}
