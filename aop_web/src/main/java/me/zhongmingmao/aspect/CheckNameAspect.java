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

@Aspect
@Component
@Order(20)
public class CheckNameAspect {
    
    private Logger logger = LoggerFactory.getLogger(CheckNameAspect.class);
    
    @Pointcut("execution(public * me.zhongmingmao.web..*.*(..))")
    public void webLog() {
    }
    
    @Before("webLog()") // 10 -> 20
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        
        logger.info(String.format("CheckNameAspect name : %s", request.getParameter("name")));
    }
    
    @AfterReturning(returning = "ret", pointcut = "webLog()") // 20 -> 10
    public void afterRetruning(Object ret) {
        logger.info(String.format("CheckNameAspect RESPONSE : %s", ret));
    }
}
