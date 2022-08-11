package com.vectorx.crowdfunding.mvc.config;

import com.vectorx.crowdfunding.constant.CrowdConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.file.AccessDeniedException;

/**
 * @Description: WebAppSecurityConfig
 * @Author: VectorX
 * @Date: 2022/8/2 22:02
 * @Version: V1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启全局方法全局控制
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        //auth.inMemoryAuthentication()
        //        .withUser("tom").password("1234").roles("ADMIN")
        //;
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        //super.configure(security);
        security.authorizeRequests() // 1、放行首页和资源
                .antMatchers("/admin/to/login/page.html").permitAll()
                .antMatchers("/bootstrap/**", "/crowd/**", "/css/**", "/fonts/**", "/img/**", "/jquery/**", "/layer/**",
                        "/script/**", "/ztree/**").permitAll()
                .antMatchers("/admin/get/page.html")
                //.hasRole("经理")
                //.hasAuthority("user:get")
                .access("hasRole('经理') OR hasAuthority('user:get')")
                .anyRequest().authenticated()
                .and()
                .csrf().disable() // 禁用CSRF
                .formLogin() // 2、登录
                .loginPage("/admin/to/login/page.html").loginProcessingUrl("/security/do/login.html")
                .defaultSuccessUrl("/admin/to/main/page.html").usernameParameter("loginAcct")
                .passwordParameter("userPswd")
                .and()      // 3、退出登录
                .logout().logoutUrl("/security/do/logout.html").logoutSuccessUrl("/admin/to/main/page.html")
                .and()      // 8、异常处理
                .exceptionHandling().accessDeniedHandler((request, response, accessDeniedException) -> {
                    request.setAttribute("exception", new AccessDeniedException(CrowdConstant.MSG_ACCESS_DENIED));
                    request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
                })
        ;
    }
}
