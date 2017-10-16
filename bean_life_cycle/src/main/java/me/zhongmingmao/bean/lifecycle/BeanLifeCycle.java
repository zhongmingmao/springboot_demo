package me.zhongmingmao.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 示例Spring Bean的生命周期
 *
 * @author zhongmingmao zhongmingmao0625@gmail.com
 */
public class BeanLifeCycle implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, InitializingBean, DisposableBean {
    private Person person;
    
    public BeanLifeCycle() {
        System.out.println("01. Constructor");
    }
    
    @Autowired
    public void setPerson(Person person) {
        System.out.println("02. Setter");
    }
    
    @Override
    public void setBeanName(String name) {
        System.out.println("03. BeanNameAware.setBeanName");
    }
    
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("04. BeanFactoryAware.setBeanFactory");
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        System.out.println("05. ApplicationContextAware.setApplicationContext");
    }
    
    @PostConstruct
    public void postConstruct() {
        System.out.println("07. - @PostConstruct");
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("08. - InitializingBean.afterPropertiesSet");
    }
    
    public void init_method() {
        System.out.println("09. - @Bean.init_method");
    }
    
    @PreDestroy
    public void preDestroy() {
        System.out.println("11. - @PreDestroy");
    }
    
    @Override
    public void destroy() throws Exception {
        System.out.println("12. - DisposableBean.destroy");
    }
    
    public void destroy_method() {
        System.out.println("13. - @Bean.destroy_method");
    }
}
/*
输出
01. Constructor
02. Setter

03. BeanNameAware.setBeanName
04. BeanFactoryAware.setBeanFactory
05. ApplicationContextAware.setApplicationContext

06. == BeanPostProcessor.postProcessBeforeInitialization
07. - @PostConstruct
08. - InitializingBean.afterPropertiesSet
09. - @Bean.init_method
10. == BeanPostProcessor.postProcessAfterInitialization

11. - @PreDestroy
12. - DisposableBean.destroy
13. - @Bean.destroy_method
 */

/**
 * Bean的后向处理器，对容器中所有的Bean进行拦截
 *
 * @author zhongmingmao zhongmingmao0625@gmail.com
 */
@Component
class MyBeanPostProcessor implements BeanPostProcessor {
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        if (bean instanceof BeanLifeCycle) {
            System.out.println("06. == BeanPostProcessor.postProcessBeforeInitialization");
        }
        return bean;
    }
    
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        if (bean instanceof BeanLifeCycle) {
            System.out.println("10. == BeanPostProcessor.postProcessAfterInitialization");
        }
        return bean;
    }
}

@Component
class Person {
}