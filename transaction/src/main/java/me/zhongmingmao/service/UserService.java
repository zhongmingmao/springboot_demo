package me.zhongmingmao.service;

import me.zhongmingmao.domain.User;

import java.util.List;

public interface UserService {
    
    void save(User user);
    
    /**
     * 事务批量插入
     */
    void transactionalBatchSave(List<User> users);
    
    /**
     * 非事务批量插入
     */
    void commonBatchSave(List<User> users);
    
    long count();
    
    void deleteAll();
}
