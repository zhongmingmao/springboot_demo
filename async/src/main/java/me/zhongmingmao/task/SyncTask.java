package me.zhongmingmao.task;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.BiFunction;

@Component
public class SyncTask {
    
    private Logger logger = Logger.getLogger(SyncTask.class);
    
    // Java 8 Lambda
    private BiFunction<Long, String, Long> syncTaskFunction = (millis, taskName) -> {
        LocalDateTime start = LocalDateTime.now();
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            logger.info(String.format("%s interrupted", taskName), e);
        }
        LocalDateTime end = LocalDateTime.now();
        long takeTime = Duration.between(start, end).toMillis();
        logger.info(String.format("%s take %sms", taskName, takeTime));
        return takeTime;
    };
    
    public long tenSecondsTask() throws InterruptedException {
        return syncTaskFunction.apply(TaskCommon.TEN_SECONDS, "tenSecondsTask");
    }
    
    public long twentySecondsTask() throws InterruptedException {
        return syncTaskFunction.apply(TaskCommon.TWENTY_SECONDS, "twentySecondsTask");
    }
    
    public long thirtySecondsTask() throws InterruptedException {
        return syncTaskFunction.apply(TaskCommon.THIRTY_SECONDS, "thirtySecondsTask");
    }
}
