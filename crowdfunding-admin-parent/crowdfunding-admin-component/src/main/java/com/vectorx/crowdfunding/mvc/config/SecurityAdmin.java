package com.vectorx.crowdfunding.mvc.config;

import com.vectorx.crowdfunding.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * @description: SecurityAdmin
 * @author: VectorX
 * @date: 2022/8/7 17:36
 * @version: V1.0
 */
public class SecurityAdmin extends User
{
    private Admin originAdmin;

    public SecurityAdmin(Admin originAdmin, List<GrantedAuthority> authorities) {
        super(originAdmin.getLoginAcct(), originAdmin.getUserPswd(), authorities);
        this.originAdmin = originAdmin;
        // 擦除原始admin对象密码，避免密码泄露
        this.originAdmin.setUserPswd(null);
    }

    public Admin getOriginAdmin() {
        return originAdmin;
    }

    public void setOriginAdmin(Admin originAdmin) {
        this.originAdmin = originAdmin;
    }
}
