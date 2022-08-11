package com.vectorx.crowdfunding.service.impl;

import com.vectorx.crowdfunding.entity.Admin;
import com.vectorx.crowdfunding.entity.Role;
import com.vectorx.crowdfunding.mvc.config.SecurityAdmin;
import com.vectorx.crowdfunding.service.api.AdminService;
import com.vectorx.crowdfunding.service.api.AuthService;
import com.vectorx.crowdfunding.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: SecurityUserDetailService
 * @author: VectorX
 * @date: 2022/8/7 17:40
 * @version: V1.0
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService
{
    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorityList = new ArrayList<>();

        Admin admin = adminService.getAdminByLoginAcct(username);

        List<Role> roleNameList = roleService.getAssignRoleList(admin.getId());
        for (Role role : roleNameList) {
            authorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }

        List<String> authNameList = authService.getAuthNameByAdminId(admin.getId());
        for (String authName : authNameList) {
            authorityList.add(new SimpleGrantedAuthority(authName));
        }

        return new SecurityAdmin(admin, authorityList);
    }
}
