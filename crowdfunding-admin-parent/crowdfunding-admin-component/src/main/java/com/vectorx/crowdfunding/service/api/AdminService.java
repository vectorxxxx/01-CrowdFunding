package com.vectorx.crowdfunding.service.api;

import com.github.pagehelper.PageInfo;
import com.vectorx.crowdfunding.entity.Admin;

import java.util.List;

public interface AdminService
{
    List<Admin> getAllAdmin();

    Admin getAdminByLoginAcct(String loginAcct, String userPswd);

    Admin getAdminById(Integer adminId);

    PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

    void saveAdmin(Admin admin);

    void removeAdminById(Integer adminId);
}
