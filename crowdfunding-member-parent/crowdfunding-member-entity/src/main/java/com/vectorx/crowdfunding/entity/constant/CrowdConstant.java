package com.vectorx.crowdfunding.entity.constant;

public class CrowdConstant
{
    public static final String MSG_LOGIN_INVALID = "账户不存在！";
    public static final String MSG_LOGIN_FAILED = "账号密码有误！";
    public static final String MSG_LOGIN_ALREADY_IN_USE = "账号已被使用！";
    public static final String MSG_ACCESS_FORBIDDEN = "请登录后访问！";
    public static final String MSG_STRING_INVALIDATE = "非法字符串！";
    public static final String MSG_DATA_ERROR = "数据异常！";
    public static final String MSG_DELETE_FORBIDDEN = "不允许删除当前用户！";
    public static final String MSG_ACCESS_DENIED = "没有权限！";
    public static final String MSG_CAPTCHA_INVALID = "验证码不正确！请检查并重新输入";
    public static final String MSG_CAPTCHA_EXPIRED = "验证码已过期！请重新获取验证码";
    public static final String MSG_LOGIN_ACCT_NOT_NULL = "登录账号不能为空！";
    public static final String MSG_PASS_WORD_NOT_NULL = "登录密码不能为空！";
    public static final String MSG_USER_NAME_NOT_NULL = "用户昵称不能为空！";
    public static final String MSG_EMAIL_ADDR_NOT_NULL = "邮箱地址不能为空！";
    public static final String MSG_PHONE_NUM_NOT_NULL = "手机号不能为空！";
    public static final String MSG_CAPTCHA_NOT_NULL = "验证码不能为空！";

    public static final String ATTR_NAME_EXCEPTION = "exception";
    public static final String ATTR_NAME_ADMIN_LOGIN = "adminLogin";
    public static final String ATTR_NAME_PAGE_INFO = "pageInfo";
    public static final String ATTR_NAME_USER = "user";
    public static final String ATTR_NAME_MESSAGE = "message";
    public static final String ATTR_NAME_MEMBER = "member";

    public static final String REDIS_KEY_PREFIX = "REDIS_KEY_PREFIX_";

    public static final String REDIRECT_TO_REGISTER_PAGE = "redirect:/auth/member/to/reg/page";
    public static final String REDIRECT_TO_LOGIN_PAGE = "redirect:/auth/member/to/login/page";
    public static final String REDIRECT_TO_CENTER_PAGE = "redirect:/auth/member/to/center/page";
}