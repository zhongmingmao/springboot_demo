package me.zhongmingmao.primary.repository;

import me.zhongmingmao.primary.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
