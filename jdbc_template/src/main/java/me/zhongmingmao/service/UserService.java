package me.zhongmingmao.service;

import me.zhongmingmao.domain.User;

public interface UserService {
    
    void createUser(Long id, String name);
    
    User getUserById(Long id);
    
    Integer getUserCount();
    
    void deleteUserById(Long id);
    
    void deleteAllUsers();
}
