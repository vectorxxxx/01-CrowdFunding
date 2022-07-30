package com.vectorx.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description 4、用户注销
 * @Author VectorX
 * @Date 2022/7/28 22:23
 * @Version V1.0
 **/
//@Configuration
//@EnableWebSecurity
public class WebAppSecurityConfig4 extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests()
                .antMatchers("/layui/**", "/index.jsp")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/index.jsp")
                .loginProcessingUrl("/do/login.html")
                .usernameParameter("loginacct")
                .passwordParameter("credential")
                .defaultSuccessUrl("/main.html")
                .and()
                //.csrf()                          // 禁用CSRF
                //.disable()
                .logout()                        // 开启注销功能
                .logoutUrl("/do/logout.html")    // 指定注销请求url
                .logoutSuccessUrl("/index.jsp"); // 指定注销请求成功后的url
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("tom").password("1234")
                .roles("ADMIN")
                .and()
                .withUser("jerry").password("1234")
                .authorities("SAVE", "EDIT");
    }
}
