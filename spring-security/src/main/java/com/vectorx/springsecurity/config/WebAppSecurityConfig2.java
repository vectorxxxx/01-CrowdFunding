package com.vectorx.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description 2、指定登录页面
 * @Author VectorX
 * @Date 2022/7/27 22:02
 * @Version V1.0
 **/
//@Configuration
//@EnableWebSecurity
public class WebAppSecurityConfig2 extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests()
                .antMatchers("/layui/**", "/index.jsp")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()                           // 开启表单登录
                .loginPage("/index.jsp")               // 指定登录页面
                .loginProcessingUrl("/do/login.html"); // 指定登录请求url
    }
}
