package me.zhongmingmao.config;

import me.zhongmingmao.event.Events;
import me.zhongmingmao.state.States;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.OnTransitionEnd;
import org.springframework.statemachine.annotation.OnTransitionStart;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    
    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states.withStates()
                .initial(States.UNPAID) // 状态机-初始状态
                .states(EnumSet.allOf(States.class)); // 状态机-所有状态
    }
    
    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        // 状态迁移路径
        transitions
                .withExternal()
                .source(States.UNPAID).target(States.WAITING_FOR_RECEIVE).event(Events.PAY)
                .and()
                .withExternal()
                .source(States.WAITING_FOR_RECEIVE).target(States.DONE).event(Events.RECEIVE);
    }

//    @Override
//    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
//        // 状态监听器
//        config.withConfiguration().listener(listener());
//    }
//
//    @Bean
//    public StateMachineListener<States, Events> listener() {
//        // 具体的业务逻辑
//        return new StateMachineListenerAdapter<States, Events>() {
//            @Override
//            public void transition(Transition<States, Events> transition) {
//                if (States.UNPAID == transition.getTarget().getId()) {
//                    logger.info("订单创建，待支付");
//                    return;
//                }
//                if (States.UNPAID == transition.getSource().getId() &&
//                        States.WAITING_FOR_RECEIVE == transition.getTarget().getId()) {
//                    logger.info("用户完成支付，待收货");
//                    return;
//                }
//                if (States.WAITING_FOR_RECEIVE == transition.getSource().getId() &&
//                        States.DONE == transition.getTarget().getId()) {
//                    logger.info("用户已收货，订单完成");
//                    return;
//                }
//            }
//        };
//    }
    
    
    @WithStateMachine // 用注解实现上面注释实现的逻辑，更优雅
    public static class EventConfig {
        
        Logger logger = LoggerFactory.getLogger(this.getClass());
        
        @OnTransition(target = "UNPAID")
        public void create() {
            logger.info("订单创建，待支付");
        }
        
        @OnTransitionStart(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
        public void payStart() {
            logger.info("用户完成支付，待收货: 开始");
        }
        
        @OnTransition(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
        public void pay() {
            logger.info("用户完成支付，待收货");
        }
        
        @OnTransitionEnd(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
        public void payEnd() {
            logger.info("用户完成支付，待收货: 结束");
        }
        
        @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
        public void receive() {
            logger.info("用户已收货，订单完成");
        }
    }
}
