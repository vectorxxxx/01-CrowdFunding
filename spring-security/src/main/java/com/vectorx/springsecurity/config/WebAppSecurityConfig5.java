//package com.vectorx.springsecurity.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @Description 5、基于角色或权限进行权限控制
// * @Author VectorX
// * @Date 2022/7/28 22:23
// * @Version V1.0
// **/
//@Configuration
//@EnableWebSecurity
//public class WebAppSecurityConfig5 extends WebSecurityConfigurerAdapter
//{
//    @Override
//    protected void configure(HttpSecurity security) throws Exception {
//        security.authorizeRequests()
//                .antMatchers("/layui/**", "/index.jsp")
//                .permitAll()
//                .antMatchers("/level1/**")  // 学徒才能访问level1下资源
//                .hasRole("学徒")
//                .antMatchers("/level2/**")  // 大师才能访问level2下资源
//                .hasRole("大师")
//                .antMatchers("/level3/**")  // 宗师才能访问level3下资源
//                .hasRole("宗师")
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/index.jsp")
//                .loginProcessingUrl("/do/login.html")
//                .usernameParameter("loginacct")
//                .passwordParameter("credential")
//                .defaultSuccessUrl("/main.html")
//                .and()
//                .logout()
//                .logoutUrl("/do/logout.html")
//                .logoutSuccessUrl("/index.jsp");
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("tom").password("1234")
//                .roles("ADMIN", "学徒", "宗师")
//                .and()
//                .withUser("jerry").password("1234")
//                .authorities("SAVE", "EDIT");
//    }
//}
