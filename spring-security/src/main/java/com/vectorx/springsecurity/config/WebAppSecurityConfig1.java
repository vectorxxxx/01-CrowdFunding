//package com.vectorx.springsecurity.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @Description 1、放行首页和静态资源
// * @Author VectorX
// * @Date 2022/7/27 21:57
// * @Version V1.0
// **/
//@Configuration
//@EnableWebSecurity
//public class WebAppSecurityConfig1 extends WebSecurityConfigurerAdapter
//{
//    @Override
//    protected void configure(HttpSecurity security) throws Exception {
//        security.authorizeRequests()
//                .antMatchers("/index.jsp") // 放行首页
//                .permitAll()
//                .antMatchers("/layui/**")  // 放行静态资源
//                .permitAll()
//                .and()
//                .authorizeRequests()       // 其他请求需认证
//                .anyRequest()
//                .authenticated();
//    }
//}
