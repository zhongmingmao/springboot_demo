package me.zhongmingmao.service;

import me.zhongmingmao.domain.User;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.JVM)// 按测试用例定义的顺序来进行单元测试，并不优雅，测试用例之间并非独立
public class RequiredUserServiceTests {
    
    private List<User> users = Arrays.asList(
            new User(1L, "aaa"),
            new User(2L, "bbb"),
            new User(3L, "cccccc"),
            new User(4L, "ddd"));
    
    @Autowired
    @Qualifier("REQUIRED")
    private UserService requiredUserService;
    
    @Test(expected = DataIntegrityViolationException.class)
    public void transactionTest() {
        requiredUserService.deleteAll();
        // transactionalBatchSave为REQUIRED，save为REQUIRED，所有save操作在同一个事务内，save操作异常全部回滚
        requiredUserService.transactionalBatchSave(users);
    }
    
    @Test
    public void checkTransactionTest() {
        assertEquals(0, requiredUserService.count());
    }
    
    @Test(expected = DataIntegrityViolationException.class)
    public void commonTest() {
        requiredUserService.deleteAll();
        // commonBatchSave无事务管理，save为REQUIRED，每一个save操作在创建一个事务，save操作异常仅仅回滚对应的事务
        requiredUserService.commonBatchSave(users);
    }
    
    @Test
    public void checkCommonTest() {
        assertEquals(2, requiredUserService.count());
    }
}
