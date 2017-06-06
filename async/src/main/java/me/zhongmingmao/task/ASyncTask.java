package me.zhongmingmao.task;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Future;
import java.util.function.BiFunction;

@Component
public class ASyncTask {
    
    private Logger logger = Logger.getLogger(ASyncTask.class);
    
    // Java 8 Lambda
    private BiFunction<Long, String, AsyncResult<Long>> asyncTaskFunction = (millis, taskName) -> {
        LocalDateTime start = LocalDateTime.now();
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            logger.info(String.format("%s interrupted", taskName), e);
        }
        LocalDateTime end = LocalDateTime.now();
        long takeTime = Duration.between(start, end).toMillis();
        logger.info(String.format("%s take %sms", taskName, takeTime));
        return new AsyncResult<>(takeTime);
    };
    
    @Async // 同步函数 -> 异步函数
    public Future<Long> tenSecondsTask() throws InterruptedException {
        return asyncTaskFunction.apply(TaskCommon.TEN_SECONDS, "tenSecondsTask");
    }
    
    @Async
    public Future<Long> twentySecondsTask() throws InterruptedException {
        return asyncTaskFunction.apply(TaskCommon.TWENTY_SECONDS, "twentySecondsTask");
    }
    
    @Async
    public Future<Long> thirtySecondsTask() throws InterruptedException {
        return asyncTaskFunction.apply(TaskCommon.THIRTY_SECONDS, "thirtySecondsTask");
    }
}
