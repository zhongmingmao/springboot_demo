package me.zhongmingmao.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;

@Aspect
@Component
@Order(10)
public class WebLogAspect {
    
    private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
    
    ThreadLocal<LocalDateTime> startTime = new ThreadLocal<>();
    
    @Pointcut("execution(public * me.zhongmingmao.web..*.*(..))")
    public void webLog() {
    }
    
    @Before("webLog()") // 10 -> 20
    public void before(JoinPoint joinPoint) {
        startTime.set(LocalDateTime.now());
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        logger.info(String.format("WebLogAspect URL : %s", request.getRequestURL().toString()));
        logger.info(String.format("WebLogAspect HTTP METHOD : %s", request.getMethod()));
        logger.info(String.format("WebLogAspect REMOTE IP : %s", request.getRemoteAddr()));
        logger.info(String.format("WebLogAspect LOCAL IP : %s", request.getLocalAddr()));
    }
    
    @AfterReturning(returning = "ret", pointcut = "webLog()") // 20 -> 10
    public void afterRetruning(Object ret) {
        logger.info(String.format("WebLogAspect RESPONSE : %s", ret));
        long nanos = Duration.between(startTime.get(), LocalDateTime.now()).toNanos();
        logger.info(String.format("WebLogAspect DURATION : %sns", nanos));
    }
}
