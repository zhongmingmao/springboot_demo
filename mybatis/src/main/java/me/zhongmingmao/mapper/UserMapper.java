package me.zhongmingmao.mapper;

import me.zhongmingmao.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    
    // ----- Select -----
    @Select("SELECT * FROM USER WHERE ID = #{id}")
    User findById(@Param("id") Long id);
    
    @Select("SELECT COUNT(*) C FROM USER")
    int countAll();
    
    @Results({
            @Result(property = "name", column = "NAME")
    })
    @Select("SELECT NAME FROM USER")
    List<User> findAll();
    
    // ----- Insert -----
    @Insert("INSERT INTO USER (ID , NAME) VALUES (#{id} , #{name})")
    int insert(@Param("id") Long id, @Param("name") String name);
    
    @Insert("INSERT INTO USER (ID , NAME) VALUES (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR})")
    int insertByMap(Map<String, Object> map);
    
    // 推荐使用
    @Insert("INSERT INTO USER (ID , NAME) VALUES (#{id}, #{name})")
    int insertByUser(User user);
    
    // ----- Update -----
    @Update("UPDATE user SET name=#{name} WHERE id=#{id}")
    void update(User user);
    
    // ----- Delete -----
    @Delete("DELETE FROM USER")
    void deleteAll();
    
    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Long id);
    
    
}
