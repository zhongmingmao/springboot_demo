package me.zhongmingmao.service;

import me.zhongmingmao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate; // 注入的是defaultJdbcTemplate，与Spring Boot自动配置一致
    
    @Override
    public void createUser(Long id, String name) {
        jdbcTemplate.update("INSERT INTO USER (ID , NAME) VALUES (? , ?)", id, name);
    }
    
    @Override
    public User getUserById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM USER WHERE ID = ?",
                // Java 8 Lambda
                (resultSet, i) -> new User(resultSet.getLong("id"), resultSet.getString("name")),
                id);
    }
    
    @Override
    public Integer getUserCount() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER", Integer.class);
    }
    
    @Override
    public void deleteUserById(Long id) {
        jdbcTemplate.update("DELETE FROM USER WHERE ID = ?", id);
    }
    
    @Override
    public void deleteAllUsers() {
        jdbcTemplate.update("DELETE FROM USER");
    }
}
