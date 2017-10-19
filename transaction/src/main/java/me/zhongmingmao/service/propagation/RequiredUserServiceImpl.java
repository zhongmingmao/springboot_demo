package me.zhongmingmao.service.propagation;

import me.zhongmingmao.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("REQUIRED")
public class RequiredUserServiceImpl extends AbstractUserService {
    
    @Override
    // 默认的事务传播行为
    // 如果当前存在事务，则加入该事务
    // 如果当前没有事务，则创建一个新的事务
    @Transactional(propagation = Propagation.REQUIRED) // 其他事务传播行为后续再研究
    public void save(User user) {
        userRepository.save(user);
    }
}