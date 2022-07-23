package com.vectorx.crowdfunding.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vectorx.crowdfunding.entity.Role;
import com.vectorx.crowdfunding.entity.RoleExample;
import com.vectorx.crowdfunding.mapper.RoleMapper;
import com.vectorx.crowdfunding.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService
{
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> roleList = roleMapper.selectByKeyword(keyword);
        return new PageInfo<>(roleList);
    }

    @Override
    public void saveRole(Role role) {
        roleMapper.insertSelective(role);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public void removeRole(List<Integer> roleIdList) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andIdIn(roleIdList);
        roleMapper.deleteByExample(roleExample);
    }

    @Override
    public List<Role> getUnAssignRoleList(Integer adminId) {
        return roleMapper.selectUnAssignRoleByAdminId(adminId);
    }

    @Override
    public List<Role> getAssignRoleList(Integer adminId) {
        return roleMapper.selectAssignRoleByAdminId(adminId);
    }

    @Override
    public void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList) {
        roleMapper.deleteAdminRoleByAdminId(adminId);
        if (roleIdList != null && !roleIdList.isEmpty()) {
            roleMapper.insertAdminRole(adminId, roleIdList);
        }
    }
}
