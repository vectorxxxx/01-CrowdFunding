package com.vectorx.crowdfunding.mvc.config;

import com.google.gson.Gson;
import com.vectorx.crowdfunding.constant.CrowdConstant;
import com.vectorx.crowdfunding.exception.AccessForbiddenException;
import com.vectorx.crowdfunding.exception.LoginAcctAlreadyInUseEditException;
import com.vectorx.crowdfunding.exception.LoginAcctAlreadyInUseException;
import com.vectorx.crowdfunding.exception.LoginFailedException;
import com.vectorx.crowdfunding.util.CrowdUtil;
import com.vectorx.crowdfunding.util.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class CrowdExceptionResolver
{
    // ========================账户被使用异常========================
    @ExceptionHandler(LoginAcctAlreadyInUseException.class)
    public ModelAndView resolveLoginAcctAlreadyInUseException(LoginAcctAlreadyInUseException exception, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        return commonResolve("admin/admin-add", exception, request, response);
    }

    @ExceptionHandler(LoginAcctAlreadyInUseEditException.class)
    public ModelAndView resolveLoginAcctAlreadyInUseEditException(LoginAcctAlreadyInUseEditException exception, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        //return commonResolve("admin-edit", exception, request, response);
        return commonResolve("error", exception, request, response);
    }

    // ========================登陆失败异常========================
    @ExceptionHandler(LoginFailedException.class)
    public ModelAndView resolveLoginFailedException(LoginFailedException exception, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        return commonResolve("admin/admin-login", exception, request, response);
    }

    @ExceptionHandler(AccessForbiddenException.class)
    public ModelAndView resolveAccessForbiddenException(AccessForbiddenException exception, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        return commonResolve("admin/admin-login", exception, request, response);
    }

    // ========================算数异常========================
    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView resolveArithmeticException(ArithmeticException exception, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        return commonResolve("error", exception, request, response);
    }

    // ========================空指针异常========================
    @ExceptionHandler(NullPointerException.class)
    public ModelAndView resolveNullPointerException(NullPointerException exception, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        return commonResolve("error", exception, request, response);
    }

    private ModelAndView commonResolve(String viewName, Exception exception, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        // Ajax请求
        if(CrowdUtil.judgeAjax(request)){
            ResultEntity<Object> failed = ResultEntity.failed(exception.getCause().getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(failed);
            response.getWriter().write(json);
            return null;
        }
        // 普通请求
        ModelAndView mav = new ModelAndView();
        mav.addObject(CrowdConstant.ATTR_NAME_EXCEPTION, exception);
        mav.setViewName(viewName);
        return mav;
    }
}

