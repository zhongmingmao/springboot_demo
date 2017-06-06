package me.zhongmingmao;

import me.zhongmingmao.task.ASyncTask;
import me.zhongmingmao.task.SyncTask;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncApplicationTests {
    
    private Logger logger = Logger.getLogger(AsyncApplicationTests.class);
    
    @Autowired
    private SyncTask syncTask;
    
    @Autowired
    private ASyncTask asyncTask;
    
    @Ignore("take too much time")
    @Test
    public void syncTaskTask() throws InterruptedException {
        LocalDateTime start = LocalDateTime.now();
        long tenSecondsTaskTime = syncTask.tenSecondsTask();
        long twentySecondsTaskTime = syncTask.twentySecondsTask();
        long thirtySecondsTaskTime = syncTask.thirtySecondsTask();
        LocalDateTime end = LocalDateTime.now();
        long realTotalTime = Duration.between(start, end).toMillis();
        long fakeTotalTime = tenSecondsTaskTime + twentySecondsTaskTime + thirtySecondsTaskTime;
        logger.info(String.format("syncTaskTask realTotalTime : %sms", realTotalTime));
        logger.info(String.format("syncTaskTask fakeTotalTime : %sms", fakeTotalTime));
        assertTrue(realTotalTime / (fakeTotalTime + 0.0) > 1);
    }
    
    @Ignore("take too much time")
    @Test
    public void asyncTaskTask() throws InterruptedException, ExecutionException {
        LocalDateTime start = LocalDateTime.now();
        Future<Long> tenSecondsTask = asyncTask.tenSecondsTask();
        Future<Long> twentySecondsTask = asyncTask.twentySecondsTask();
        Future<Long> thirtySecondsTask = asyncTask.thirtySecondsTask();
        while (true) {
            if (tenSecondsTask.isDone() && twentySecondsTask.isDone() && thirtySecondsTask.isDone()) {
                break;
            }
            Thread.sleep(10);
        }
        LocalDateTime end = LocalDateTime.now();
        long realTotalTime = Duration.between(start, end).toMillis();
        long fakeTotalTime = tenSecondsTask.get() + twentySecondsTask.get() + thirtySecondsTask.get();
        logger.info(String.format("asyncTaskTask realTotalTime : %sms", realTotalTime));
        logger.info(String.format("asyncTaskTask fakeTotalTime : %sms", fakeTotalTime));
        assertTrue(realTotalTime / (fakeTotalTime + 0.0) < 1); // 速度大幅提升
    }
    
    
}
