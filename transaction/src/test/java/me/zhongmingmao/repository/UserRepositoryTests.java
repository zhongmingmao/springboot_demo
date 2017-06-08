package me.zhongmingmao.repository;

import me.zhongmingmao.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // 单元测试都被事务管理
@Rollback // 保证单元测试的独立性，单元测试后回滚
public class UserRepositoryTests {
    
    @Autowired
    private UserRepository userRepository;
    
    @Test(expected = DataIntegrityViolationException.class)
    public void rollbackTransactionTest() {
        userRepository.save(new User(1L, "aaa"));
        userRepository.save(new User(2L, "bbb"));
        userRepository.save(new User(3L, "zhongmingmao")); // RollBack
        userRepository.save(new User(4L, "ccc")); // will not be excuted
    }
    
    @Test
    public void commitTransactionTest() {
        userRepository.save(new User(1L, "aaa")); // RollBack
    }
}
