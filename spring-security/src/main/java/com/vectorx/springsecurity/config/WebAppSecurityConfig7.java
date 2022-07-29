package com.vectorx.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * @Description 6、自定义403页面
 * @Author VectorX
 * @Date 2022/7/28 22:23
 * @Version V1.0
 **/
@Configuration
@EnableWebSecurity
public class WebAppSecurityConfig7 extends WebSecurityConfigurerAdapter
{
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        System.out.println(dataSource.getConnection());
        security.authorizeRequests()
                .antMatchers("/layui/**", "/index.jsp")
                .permitAll()
                .antMatchers("/level1/**")
                .hasRole("学徒")
                .antMatchers("/level2/**")
                .hasRole("大师")
                .antMatchers("/level3/**")
                .hasRole("宗师")
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
                .logout()
                .logoutUrl("/do/logout.html")
                .logoutSuccessUrl("/index.jsp")
                .and()
                .exceptionHandling()                        // 异常处理器
                //.accessDeniedPage("/to/no/auth/page.html")  // 访问被拒绝是前往的页面
                .accessDeniedHandler(new AccessDeniedHandler()
                {
                    @Override
                    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                            AccessDeniedException e) throws IOException, ServletException {
                        httpServletRequest.setAttribute("message", "不要不识好歹");
                        httpServletRequest.getRequestDispatcher("/WEB-INF/views/no_auth.jsp")
                                .forward(httpServletRequest, httpServletResponse);
                    }
                })
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("tom").password("1234")
                .roles("ADMIN", "学徒", "宗师")
                .and()
                .withUser("jerry").password("1234")
                .authorities("SAVE", "EDIT")
        ;
    }
}
