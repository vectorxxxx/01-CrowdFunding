package com.vectorx.crowdfunding.service.api;

import com.github.pagehelper.PageInfo;
import com.vectorx.crowdfunding.entity.Role;

import java.util.List;

public interface RoleService
{
    PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword);

    void saveRole(Role role);

    void updateRole(Role role);

    void removeRole(List<Integer> roleIdList);

    List<Role> getUnAssignRoleList(Integer adminId);

    List<Role> getAssignRoleList(Integer adminId);

    void saveAdminRoleRelationship(Integer adminId, List<Integer> roleList);
}
