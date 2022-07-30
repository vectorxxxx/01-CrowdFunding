package com.vectorx.springsecurity.service;

import com.vectorx.springsecurity.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: AppUserDetailService
 * @Author: VectorX
 * @Date: 2022/7/30 14:59
 * @Version: V1.0
 */
@Service
public class AppUserDetailsService2 implements UserDetailsService
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String sql = "select id,loginacct,userpswd,username,email,createtime from t_admin where username=?";
        Admin admin = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Admin.class), username);
        String loginacct = admin.getLoginacct();
        String userpswd = admin.getUserpswd();
        final String preffix = "ROLE_";
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(preffix + "学徒", preffix + "宗师");
        return new User(loginacct, userpswd, authorityList);
    }
}
