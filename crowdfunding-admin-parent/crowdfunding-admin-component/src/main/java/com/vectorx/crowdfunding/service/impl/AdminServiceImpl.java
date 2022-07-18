package com.vectorx.crowdfunding.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vectorx.crowdfunding.constant.CrowdConstant;
import com.vectorx.crowdfunding.entity.Admin;
import com.vectorx.crowdfunding.entity.AdminExample;
import com.vectorx.crowdfunding.exception.LoginAcctAlreadyInUseEditException;
import com.vectorx.crowdfunding.exception.LoginAcctAlreadyInUseException;
import com.vectorx.crowdfunding.exception.LoginFailedException;
import com.vectorx.crowdfunding.mapper.AdminMapper;
import com.vectorx.crowdfunding.service.api.AdminService;
import com.vectorx.crowdfunding.util.CrowdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService
{
    @Autowired
    private AdminMapper adminMapper;

    private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Override
    public List<Admin> getAllAdmin() {
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        List<Admin> adminList = adminMapper.selectByExample(example);

        if (adminList == null || adminList.isEmpty()) {
            throw new LoginFailedException(CrowdConstant.MSG_LOGIN_FAILED);
        }
        if (adminList.size() > 1) {
            throw new LoginFailedException(CrowdConstant.MSG_DATA_ERROR);
        }

        Admin admin = adminList.get(0);
        String pswdDB = admin.getUserPswd();
        String pswdIn = CrowdUtil.md5(userPswd);
        if (!Objects.equals(pswdDB, pswdIn)) {
            throw new LoginFailedException(CrowdConstant.MSG_LOGIN_FAILED);
        }

        return admin;
    }

    @Override
    public Admin getAdminById(Integer adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> adminList = adminMapper.selectByKeyword(keyword);
        return new PageInfo<>(adminList);
    }

    @Override
    public void saveAdmin(Admin admin) {
        if(admin.getId() == null) {
            addAdmin(admin);
        }
        else {
            updateAdmin(admin);
        }
    }

    private void addAdmin(Admin admin) {
        String userPswd = CrowdUtil.md5(admin.getUserPswd());
        String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        admin.setUserPswd(userPswd);
        admin.setCreateTime(createTime);
        try {
            adminMapper.insert(admin);
        }
        catch (DuplicateKeyException e) {
            throw new LoginAcctAlreadyInUseException(CrowdConstant.MSG_LOGIN_ALREADY_IN_USE, e);
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void updateAdmin(Admin admin) {
        try {
            adminMapper.updateByPrimaryKeySelective(admin);
        }
        catch (DuplicateKeyException e) {
            throw new LoginAcctAlreadyInUseEditException(CrowdConstant.MSG_LOGIN_ALREADY_IN_USE, e);
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void removeAdminById(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }
}
