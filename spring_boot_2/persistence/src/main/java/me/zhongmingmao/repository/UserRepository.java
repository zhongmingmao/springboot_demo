package me.zhongmingmao.repository;

import me.zhongmingmao.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserRepository {
    
    private final ConcurrentHashMap<Integer, User> repository = new ConcurrentHashMap<>();
    
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger();
    
    public boolean save(User user) {
        Integer id = ID_GENERATOR.incrementAndGet();
        user.setId(id);
        return repository.put(id, user) == null;
    }
    
    public Collection<User> findAll() {
        return repository.values();
    }
}