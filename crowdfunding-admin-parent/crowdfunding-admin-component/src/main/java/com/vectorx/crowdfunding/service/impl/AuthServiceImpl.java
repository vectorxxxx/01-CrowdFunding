package com.vectorx.crowdfunding.service.impl;

import com.vectorx.crowdfunding.entity.Auth;
import com.vectorx.crowdfunding.entity.AuthExample;
import com.vectorx.crowdfunding.mapper.AuthMapper;
import com.vectorx.crowdfunding.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService
{
    @Autowired
    private AuthMapper authMapper;

    @Override
    public List<Auth> getAllAuth() {
        return authMapper.selectByExample(new AuthExample());
    }

    @Override
    public List<Integer> getAuthIdByRoleId(Integer roleId) {
        return authMapper.selectAuthIdByRoleId(roleId);
    }

    @Override
    public void saveAuthIdByRoleId(Integer roleId, List<Integer> authIdList) {
        authMapper.deleteByRoleId(roleId);
        if (authIdList != null && !authIdList.isEmpty()) {
            authMapper.insertRoleAuth(roleId, authIdList);
        }
    }

    @Override
    public List<String> getAuthNameByAdminId(Integer adminId) {
        return authMapper.selectAuthNameByAdminId(adminId);
    }
}
