package me.zhongmingmao.repository;

import me.zhongmingmao.domain.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User, Long> {
    
    
    @Cacheable
    User findOne(Long id);
    
    // 各个方法的 Key 逻辑定义应该一致
    @CachePut(key = "#p0.id")
    User save(User user);
    
    @CacheEvict()
    void delete(Long aLong);
    
    @CacheEvict(allEntries = true)
    void deleteAll();
}
