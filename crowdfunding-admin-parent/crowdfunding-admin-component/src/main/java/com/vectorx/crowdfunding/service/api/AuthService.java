package com.vectorx.crowdfunding.service.api;

import com.vectorx.crowdfunding.entity.Auth;

import java.util.List;

public interface AuthService
{
    List<Auth> getAllAuth();

    List<Integer> getAuthIdByRoleId(Integer roleId);

    void saveAuthIdByRoleId(Integer roleId, List<Integer> authIdList);

    List<String> getAuthNameByAdminId(Integer adminId);
}
