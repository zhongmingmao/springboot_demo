package me.zhongmingmao;

import me.zhongmingmao.event.Events;
import me.zhongmingmao.state.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class StateMachineApplication implements CommandLineRunner {
    
    public static void main(String[] args) {
        SpringApplication.run(StateMachineApplication.class, args);
    }
    
    @Autowired
    private StateMachine<States, Events> stateMachine;
    
    @Override
    public void run(String... strings) throws Exception {
        stateMachine.start();
        stateMachine.sendEvent(Events.PAY);
        stateMachine.sendEvent(Events.RECEIVE);
    }
}
