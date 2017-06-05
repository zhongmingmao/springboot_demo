package me.zhongmingmao.multi_source;

import me.zhongmingmao.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiDataSourceTests {
    
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;
    
    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate secondaryJdbcTemplate;
    
    @Before
    public void setUp() {
        primaryJdbcTemplate.update("DROP TABLE USER IF EXISTS ");
        primaryJdbcTemplate.update("CREATE TABLE USER (ID INT , NAME VARCHAR(50))");
        secondaryJdbcTemplate.update("DROP TABLE USER IF EXISTS ");
        secondaryJdbcTemplate.update("CREATE TABLE USER (ID INT , NAME VARCHAR(50))");
    }
    
    @Test
    public void jdbcTemplateTest() {
        primaryJdbcTemplate.update("INSERT INTO user (ID , NAME) VALUES (? , ?)", 1, "zhongmingmao");
        secondaryJdbcTemplate.update("INSERT INTO user (ID , NAME) VALUES (? , ?)", 2, "zhongmingwu");
        
        User user1 = primaryJdbcTemplate.queryForObject(
                "SELECT * FROM USER WHERE ID = ?",
                // Java 8 Lambda
                (resultSet, i) -> new User(resultSet.getLong("id"), resultSet.getString("name")),
                1L);
        assertNotNull(user1);
        assertEquals(Long.valueOf(1L), user1.getId());
        assertEquals("zhongmingmao", user1.getName());
        
        User user2 = secondaryJdbcTemplate.queryForObject(
                "SELECT * FROM USER WHERE ID = ?",
                // Java 8 Lambda
                (resultSet, i) -> new User(resultSet.getLong("id"), resultSet.getString("name")),
                2L);
        assertNotNull(user2);
        assertEquals(Long.valueOf(2L), user2.getId());
        assertEquals("zhongmingwu", user2.getName());
    }
}
