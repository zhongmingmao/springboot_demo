package me.zhongmingmao.config;

import org.springframework.context.annotation.Configuration;

@Configuration // 属性在application.properties中配置
public class DataSourceConfig {
    
//    @Primary
//    @Bean
//    @Qualifier("primaryDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.primary")
//    public DataSource primaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }
    
//    @Bean
//    @Qualifier("secondaryDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.secondary")
//    public DataSource secondaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }
}
