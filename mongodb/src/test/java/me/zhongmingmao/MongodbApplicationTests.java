package me.zhongmingmao;

import me.zhongmingmao.domain.User;
import me.zhongmingmao.repository.UserRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbApplicationTests {
    
    @Autowired
    private UserRepository userRepository;
    
    @Before
    public void setUp() throws IOException {
        userRepository.deleteAll();
    }
    
    @Ignore(value = "outside mongod up")
    @Test
    public void userRepositoryTest() {
        userRepository.save(new User(1L, "zhongmingmao"));
        userRepository.save(new User(2L, "zhongmingwu"));
        assertEquals(2L, userRepository.count());
        
        User user = userRepository.findOne(1L);
        assertNotNull(user);
        assertEquals(Long.valueOf(1L), user.getId());
        assertEquals("zhongmingmao", user.getName());
        
        userRepository.delete(1L);
        assertEquals(1L, userRepository.count());
    }
}
