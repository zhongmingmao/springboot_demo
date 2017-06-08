package me.zhongmingmao.repository;

import me.zhongmingmao.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CacheManager cacheManager; // Debug观察，默认是使用ConcurrentMapCacheManager
    
    @Before
    public void setUp() {
        userRepository.deleteAll();
    }
    
    @Test
    public void saveAndFindTest() {
        User user = new User(1L, "zhongmingmao");
        userRepository.save(user);
        Cache cache = cacheManager.getCache("users");
        User cacheUser = cache.get(Long.valueOf(1L), User.class);
        assertEquals(user, cacheUser);
    }
    
    @Test
    public void deleteTest() {
        User user = new User(2L, "zhongmingwu");
        userRepository.save(user);
        Cache cache = cacheManager.getCache("users");
        User cacheUser = cache.get(Long.valueOf(2L), User.class);
        assertEquals(user, cacheUser);
        
        userRepository.delete(2L);
        User nullUser = cache.get(Long.valueOf(2L), User.class);
        assertNull(nullUser);
    }
    
    
}
