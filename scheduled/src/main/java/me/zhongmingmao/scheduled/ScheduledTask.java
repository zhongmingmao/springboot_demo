package me.zhongmingmao.scheduled;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTask {
    
    Logger logger = Logger.getLogger(ScheduledTask.class);
    
    @Scheduled(fixedRate = 3000)
    public void currentTime() {
        // Java 8
        logger.info(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}

