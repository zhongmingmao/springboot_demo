package me.zhongmingmao.repository;

import me.zhongmingmao.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserReposity extends JpaRepository<User, Long> {
    
    User findById(Long id);
    
    @Query("FROM User u WHERE u.id=:id")
    User findUser(@Param("id") Long id);
}
