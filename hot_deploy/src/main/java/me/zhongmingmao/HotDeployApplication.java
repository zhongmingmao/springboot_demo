package me.zhongmingmao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class HotDeployApplication extends SpringBootServletInitializer {
    
    public static void main(String[] args) {
        SpringApplication.run(HotDeployApplication.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HotDeployApplication.class);
    }
}
