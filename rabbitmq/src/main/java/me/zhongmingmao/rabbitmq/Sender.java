package me.zhongmingmao.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Sender {
    
    Logger logger = LoggerFactory.getLogger(Sender.class);
    
    @Autowired
    private AmqpTemplate amqpTemplate;
    
    public void send() {
        String msg = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String routingKey = "hello";
        amqpTemplate.convertAndSend(routingKey, msg);
        logger.info(String.format("Send Message[%s] To Queue[%s]", msg, routingKey));
    }
}
