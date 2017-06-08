package me.zhongmingmao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public enum ROLE {ADMIN, USER}
    
    @Override // 配置认证信息
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("zhongmingmao").password("123").roles(ROLE.ADMIN.name());
    }
    
    @Override // 配置Spring Security的Filter链
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
    
    @Override // 通过拦截器拦截请求
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .regexMatchers("/403").authenticated()
                .anyRequest().permitAll();
        
    }
}
