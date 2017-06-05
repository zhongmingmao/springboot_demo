package me.zhongmingmao;

import me.zhongmingmao.primary.domain.User;
import me.zhongmingmao.primary.repository.UserRepository;
import me.zhongmingmao.secondary.domain.Person;
import me.zhongmingmao.secondary.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataJpaMultiDatasourceApplicationTests {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PersonRepository personRepository;
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void multiDataSourceTest() throws Exception {
        
        userRepository.save(new User(3L, "zhongmingmao"));
        Assert.assertEquals(3, userRepository.findAll().size());
        
        personRepository.save(new Person(4L, "zhongmingwu"));
        Assert.assertEquals(3, personRepository.findAll().size());
    }
    
}
