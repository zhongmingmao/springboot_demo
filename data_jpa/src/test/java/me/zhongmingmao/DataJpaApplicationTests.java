package me.zhongmingmao;

import me.zhongmingmao.domain.User;
import me.zhongmingmao.repository.UserReposity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.BiFunction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataJpaApplicationTests {
    
    @Autowired
    private UserReposity userReposity;
    
    @Test
    public void findByIdTest() {
        assertEquals(2L, userReposity.count());
        
        BiFunction<Long, String, User> userSupplier = User::new;
        userReposity.save(userSupplier.apply(3L, "Mary"));
        userReposity.save(userSupplier.apply(4L, "Lili"));
        assertEquals(4L, userReposity.count());
        
        User user = userReposity.findById(3L);
        assertNotNull(user);
        assertEquals(Long.valueOf(3L), user.getId());
        assertEquals("Mary", user.getName());
    }
    
    @Test
    public void findUserTest() {
        User user = userReposity.findUser(1L);
        assertNotNull(user);
        assertEquals(Long.valueOf(1L), user.getId());
        assertEquals("zhongmingmao", user.getName());
    }
    
    
}
