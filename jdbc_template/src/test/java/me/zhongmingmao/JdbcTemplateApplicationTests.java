package me.zhongmingmao;

import me.zhongmingmao.domain.User;
import me.zhongmingmao.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcTemplateApplicationTests {
    
    @Autowired
    private UserService userService;
    
    @Before
    public void setUp() {
        userService.deleteAllUsers();
        assertEquals(Integer.valueOf(0), userService.getUserCount());
    }
    
    @Test
    public void createUserTest() {
        userService.createUser(1L, "zhongmingmao");
        assertEquals(Integer.valueOf(1), userService.getUserCount());
    }
    
    @Test
    public void getUserById() {
        userService.createUser(1L, "zhongmingmao");
        User user = userService.getUserById(1L);
        assertNotNull(user);
        assertEquals(Long.valueOf(1L), user.getId());
        assertEquals("zhongmingmao", user.getName());
    }
    
    @Test
    public void getUserCountTest() {
        userService.createUser(1L, "zhongmingmao");
        userService.createUser(2L, "zhongmingwu");
        assertEquals(Integer.valueOf(2), userService.getUserCount());
    }
    
    @Test
    public void deleteUserByIdTest() {
        userService.createUser(1L, "zhongmingmao");
        userService.createUser(2L, "zhongmingwu");
        assertEquals(Integer.valueOf(2), userService.getUserCount());
        userService.deleteUserById(1L);
        assertEquals(Integer.valueOf(1), userService.getUserCount());
    }
    
    @Test
    public void deleteAllUsersTest() {
        userService.createUser(1L, "zhongmingmao");
        userService.createUser(2L, "zhongmingwu");
        assertEquals(Integer.valueOf(2), userService.getUserCount());
        userService.deleteAllUsers();
        assertEquals(Integer.valueOf(0), userService.getUserCount());
    }
    
}
