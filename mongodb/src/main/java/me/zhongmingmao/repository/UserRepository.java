package me.zhongmingmao.repository;

import me.zhongmingmao.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {
}
