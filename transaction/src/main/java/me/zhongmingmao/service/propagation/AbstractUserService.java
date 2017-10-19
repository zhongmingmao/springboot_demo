package me.zhongmingmao.service.propagation;

import me.zhongmingmao.domain.User;
import me.zhongmingmao.repository.UserRepository;
import me.zhongmingmao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class AbstractUserService implements UserService {
    
    @Autowired
    protected UserRepository userRepository;
    
    @Override
    @Transactional // 默认事务传播行为是Propagation.REQUIRED
    public void transactionalBatchSave(List<User> users) {
        commonBatchSave(users);
    }
    
    @Override
    public void commonBatchSave(List<User> users) {
        users.stream().forEach(user -> save(user));
    }
    
    @Override
    public long count() {
        return userRepository.count();
    }
    
    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}