package me.zhongmingmao.config;

import me.zhongmingmao.domain.User;
import me.zhongmingmao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import java.util.Collection;

/**
 * 路由器函数 配置
 */
@Configuration
public class RouterFunctionConfiguration {
    
    /**
     * Flux是0~N个对象集合
     * Mono是0~1个对象集合
     * Reactive中的Flux或者Mono是异步处理（非阻塞）
     * 集合对象是同步处理（阻塞）
     * Flux或者Mono都是Publisher
     */
    @Bean
    @Autowired
    public RouterFunction<ServerResponse> userFindAll(UserRepository userRepository) {
        return RouterFunctions.route(RequestPredicates.GET("/person/find/all"), request -> {
            Collection<User> users = userRepository.findAll();
            Flux<User> userFlux = Flux.fromIterable(users);
            return ServerResponse.ok().body(userFlux, User.class);
        });
    }
}