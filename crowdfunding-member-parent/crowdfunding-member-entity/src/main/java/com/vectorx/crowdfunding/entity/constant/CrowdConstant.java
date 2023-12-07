package com.vectorx.crowdfunding.entity.constant;

public class CrowdConstant
{
    // ==================消息内容==================
    public static final String MSG_LOGIN_INVALID = "账户不存在！";
    public static final String MSG_LOGIN_FAILED = "账号密码有误！";
    public static final String MSG_LOGIN_ALREADY_IN_USE = "账号已被使用！";
    public static final String MSG_ACCESS_FORBIDDEN = "请登录后访问！";
    public static final String MSG_STRING_INVALIDATE = "非法字符串！";
    public static final String MSG_DATA_ERROR = "数据异常！";
    public static final String MSG_DELETE_FORBIDDEN = "不允许删除当前用户！";
    public static final String MSG_ACCESS_DENIED = "没有权限！";
    // 登录注册
    public static final String MSG_CAPTCHA_INVALID = "验证码不正确！请检查并重新输入";
    public static final String MSG_CAPTCHA_EXPIRED = "验证码已过期！请重新获取验证码";
    public static final String MSG_LOGIN_ACCT_NOT_NULL = "登录账号不能为空！";
    public static final String MSG_PASS_WORD_NOT_NULL = "登录密码不能为空！";
    public static final String MSG_USER_NAME_NOT_NULL = "用户昵称不能为空！";
    public static final String MSG_EMAIL_ADDR_NOT_NULL = "邮箱地址不能为空！";
    public static final String MSG_PHONE_NUM_NOT_NULL = "手机号不能为空！";
    public static final String MSG_CAPTCHA_NOT_NULL = "验证码不能为空！";
    // 发起项目
    public static final String MSG_HEADER_PICTURE_EMPTY = "头图不可为空！";
    public static final String MSG_HEADER_PICTURE_UPLOAD_FAILED = "头图上传失败！";
    public static final String MSG_DETAIL_PICTURE_EMPTY = "详情图片不可为空！";
    public static final String MSG_DETAIL_PICTURE_UPLOAD_FAILED = "详情图片上传失败！";
    public static final String MSG_TEMP_PROJECT_MISSING = "项目及发起人信息丢失！";
    // 回报信息
    public static final String MSG_RETURN_PICTURE_EMPTY = "回报说明图片不可为空！";
    // 订单确认
    public static final String MSG_CONFIRM_RETURN_MISSING = "确认回报内容丢失！";
    public static final String MSG_SAVE_NEW_ADDRESS_FAILED = "确认配送信息失败！";

    // ==================属性名称==================
    public static final String ATTR_NAME_EXCEPTION = "exception";
    public static final String ATTR_NAME_ADMIN_LOGIN = "adminLogin";
    public static final String ATTR_NAME_PAGE_INFO = "pageInfo";
    public static final String ATTR_NAME_USER = "user";
    public static final String ATTR_NAME_MESSAGE = "message";
    public static final String ATTR_NAME_MEMBER = "member";
    public static final String ATTR_NAME_TEMP_PROJECT = "temp_project";
    public static final String ATTR_NAME_PORTAL_DATA = "portal_data";

    public static final String REDIS_KEY_PREFIX = "REDIS_KEY_PREFIX_";

    // ==================视图名称==================
    public static final String MEMBER_LOGIN = "member-login";
    public static final String MEMBER_REG = "member-reg";
    public static final String MEMBER_CENTER = "member-center";
    public static final String MEMBER_CROWD = "member-crowd";
    public static final String PROJECT_LAUNCH = "project-launch";
    public static final String PROJECT_CONFIRM = "project-confirm";
    public static final String PROJECT_DETAIL = "project-detail";
    public static final String PROJECT_OVERVIEW = "project-overview";
    public static final String CONFIRM_RETURN = "confirm_return";
    public static final String CONFIRM_ORDER = "confirm_order";
    // ==================跳转页面==================
    public static final String TO_REGISTER_PAGE = "/auth/member/to/reg/page";
    public static final String TO_LOGIN_PAGE = "/auth/member/to/login/page";
    public static final String TO_CENTER_PAGE = "/auth/member/to/center/page";
    public static final String TO_MY_CROWD_PAGE = "/auth/member/to/my/crowd/page";
    // ==================请求处理==================
    public static final String DO_REGISTER = "/auth/member/do/register";
    public static final String DO_LOGIN = "/auth/member/do/login";
    public static final String DO_LOGIN_OUT = "/auth/member/do/loginout";
    public static final String SEND_SHORT_MESSAGE = "/auth/member/send/short/message.json";

    public static final String REDIRECT_HTTP_LOCALHOST = "redirect:http://localhost";
}
