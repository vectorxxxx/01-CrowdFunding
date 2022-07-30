package com.vectorx.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: AppUserDetailService
 * @Author: VectorX
 * @Date: 2022/7/30 14:59
 * @Version: V1.0
 */
//@Service
public class AppUserDetailsService implements UserDetailsService
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String sql = "select id,loginacct,userpswd,username,email,createtime from t_admin where username=?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, username);
        String loginacct = map.get("loginacct").toString();
        String userpswd = map.get("userpswd").toString();
        final String preffix = "ROLE_";
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(preffix + "学徒"));
        authorityList.add(new SimpleGrantedAuthority(preffix + "宗师"));
        return new User(loginacct, userpswd, authorityList);
    }
}
