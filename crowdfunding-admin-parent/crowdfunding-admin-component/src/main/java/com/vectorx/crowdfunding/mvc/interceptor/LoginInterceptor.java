package com.vectorx.crowdfunding.mvc.interceptor;

import com.vectorx.crowdfunding.constant.CrowdConstant;
import com.vectorx.crowdfunding.entity.Admin;
import com.vectorx.crowdfunding.exception.AccessForbiddenException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @description 登录拦截器
 * @author VectorX
 * @date 2022/8/5 22:01
 * @version V1.0
 **/
@Deprecated
public class LoginInterceptor extends HandlerInterceptorAdapter
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute(CrowdConstant.ATTR_NAME_ADMIN_LOGIN);
        if (admin == null) {
            throw new AccessForbiddenException(CrowdConstant.MSG_ACCESS_FORBIDDEN);
        }
        return true;
    }
}
