package me.zhongmingmao.config;

import me.zhongmingmao.bean.lifecycle.BeanLifeCycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Config {
    
    @Bean(initMethod = "init_method",
            destroyMethod = "destroy_method")
    public BeanLifeCycle lifeCycle() {
        return new BeanLifeCycle();
    }
}