package com.vectorx.springsecurity.config;

import com.vectorx.springsecurity.util.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * @Description 10、密码加密
 * @Author VectorX
 * @Date 2022/7/28 22:23
 * @Version V1.0
 **/
//@Configuration
//@EnableWebSecurity
public class WebAppSecurityConfig10 extends WebSecurityConfigurerAdapter
{
    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MyPasswordEncoder myPasswordEncoder;

    @Bean("jdbcTokenRepository")
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
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
                .exceptionHandling()
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
                .and()
                .rememberMe()
                //.tokenRepository(persistentTokenRepository())
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(myPasswordEncoder) // 密码加密
        ;
    }
}
