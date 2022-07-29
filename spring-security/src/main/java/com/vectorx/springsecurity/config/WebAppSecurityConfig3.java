//package com.vectorx.springsecurity.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @Description 3、设置登录账号密码
// * @Author VectorX
// * @Date 2022/7/27 22:34
// * @Version V1.0
// **/
//@Configuration
//@EnableWebSecurity
//public class WebAppSecurityConfig3 extends WebSecurityConfigurerAdapter
//{
//    @Override
//    protected void configure(HttpSecurity security) throws Exception {
//        security.authorizeRequests()
//                .antMatchers("/layui/**", "/index.jsp")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/index.jsp")
//                .loginProcessingUrl("/do/login.html")
//                .usernameParameter("loginacct")   // 指定登录用户名参数
//                .passwordParameter("credential")  // 指定登录密码参数
//                .defaultSuccessUrl("/main.html"); // 默认登录成功页面
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("tom").password("1234")  // 指定账号密码
//                .roles("ADMIN")                    // 指定角色
//                .and()
//                .withUser("jerry").password("1234")
//                .authorities("SAVE", "EDIT");      // 授权
//    }
//}
