package me.zhongmingmao;

import me.zhongmingmao.rabbitmq.Sender;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {
    
    
    @Autowired
    private Sender sender;
    
    @Ignore
    @Test
    public void sendTest() {
        sender.send();
    }
    
}
