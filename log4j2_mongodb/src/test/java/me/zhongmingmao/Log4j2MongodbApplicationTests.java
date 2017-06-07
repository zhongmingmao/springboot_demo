package me.zhongmingmao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @see http://blog.csdn.net/z69183787/article/details/53611830
 * @see http://www.cnblogs.com/leo-lsw/p/log4j2tutorial.html
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Log4j2MongodbApplicationTests {
    
    private static Logger consoleLogger = LogManager.getLogger(Log4j2MongodbApplicationTests.class);
    private static Logger mongodbLogger = LogManager.getLogger("mongodbLogger");
    
    @Ignore("need mongodb up")
    @Test
    public void logTest() {
        consoleLogger.info("zhongmingmao");
        mongodbLogger.info("zhongmingwu");
    }
}
