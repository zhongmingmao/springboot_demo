package me.zhongmingmao;

import me.zhongmingmao.domain.User;
import me.zhongmingmao.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class MybatisApplicationTests {
    
    @Autowired
    private UserMapper userMapper;
    
    @Before
    public void setUp() {
        userMapper.deleteAll();
    }
    
    @Test
    public void findAllTest() {
        assertEquals(0, userMapper.countAll());
        userMapper.insertByUser(new User(1L, "zhongmingmao"));
        userMapper.insertByUser(new User(2L, "zhongmingwu"));
        userMapper.findAll().stream().forEach(user -> {
            assertNotNull(user);
            assertNull(user.getId());
            assertNotNull(user.getName());
        });
    }
    
    @Test
    public void insertTest() {
        assertEquals(0, userMapper.countAll());
        String name = "zhongmingmao";
        userMapper.insert(1L, name);
        User user = userMapper.findById(1L);
        assertEquals(name, user.getName());
        assertEquals(1, userMapper.countAll());
    }
    
    @Test
    public void insertByMapTest() {
        assertEquals(0, userMapper.countAll());
        String name = "zhongmingmao";
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1L);
        map.put("name", name);
        userMapper.insertByMap(map);
        User user = userMapper.findById(1L);
        assertEquals(name, user.getName());
        assertEquals(1, userMapper.countAll());
    }
    
    @Test
    public void insertByUserTest() {
        assertEquals(0, userMapper.countAll());
        User user = new User(1L, "zhongmingmao");
        userMapper.insertByUser(user);
        assertEquals(user, userMapper.findById(1L));
        assertEquals(1, userMapper.countAll());
    }
    
    @Test
    public void updateTest() {
        assertEquals(0, userMapper.countAll());
        User user = new User(1L, "zhongmingmao");
        userMapper.insertByUser(user);
        user.setName("zhongmingwu");
        userMapper.update(user);
        assertEquals(user, userMapper.findById(1L));
        assertEquals(1, userMapper.countAll());
    }
    
    @Test
    public void deleteTest() {
        assertEquals(0, userMapper.countAll());
        User user = new User(1L, "zhongmingmao");
        userMapper.insertByUser(user);
        userMapper.delete(user.getId());
        assertEquals(0, userMapper.countAll());
    }
    
}
