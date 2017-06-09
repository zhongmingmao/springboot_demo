package me.zhongmingmao.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class Receiver {
    
    Logger logger = LoggerFactory.getLogger(Receiver.class);
    
    @RabbitHandler
    public void process(String msg) {
        logger.info(String.format("Receive Message[%s] FROM QUEUE[%s]", msg, "hello"));
    }
}
