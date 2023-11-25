package com.vectorx.crowdfunding.handler;

import com.vectorx.crowdfunding.CrowdUtil;
import com.vectorx.crowdfunding.api.MySQLRemoteService;
import com.vectorx.crowdfunding.api.RedisRemoteService;
import com.vectorx.crowdfunding.entity.ResultEntity;
import com.vectorx.crowdfunding.entity.ShortMessageProperties;
import com.vectorx.crowdfunding.entity.constant.CrowdConstant;
import com.vectorx.crowdfunding.entity.po.MemberPO;
import com.vectorx.crowdfunding.entity.vo.MemberCenterVO;
import com.vectorx.crowdfunding.entity.vo.MemberLoginVO;
import com.vectorx.crowdfunding.entity.vo.MemberRegisterVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.concurrent.TimeUnit;

// 开启feign客户端功能，才能实现远程方法的声明式调用
@Controller
public class PortalHandler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(PortalHandler.class);
    private static final String MEMBER_LOGIN = "member-login";
    private static final String MEMBER_REG = "member-reg";

    @Autowired
    private ShortMessageProperties shortMessageProperties;

    @Autowired
    private RedisRemoteService redisRemoteService;

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    @RequestMapping("/auth/member/do/loginout")
    public String doLoginout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/member/to/login/page";
    }

    @RequestMapping("/auth/member/do/login")
    public String doLogin(MemberLoginVO memberLoginVO, ModelMap modelMap, HttpSession session) {
        // 账号为空
        final String loginacct = memberLoginVO.getLoginacct();
        if (loginacct == null || "".equals(loginacct)) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_LOGIN_ACCT_NOT_NULL);
            return MEMBER_LOGIN;
        }
        // 密码为空
        final String userpswd = memberLoginVO.getUserpswd();
        if (userpswd == null || "".equals(userpswd)) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_PASS_WORD_NOT_NULL);
            return MEMBER_LOGIN;
        }
        // 查询账户信息
        final ResultEntity<MemberPO> memberPOResultEntity = mySQLRemoteService.getMemberPOByLoginAcctRemote(loginacct);
        // 查询出错
        if (ResultEntity.FAILED.equals(memberPOResultEntity.getResult())) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, memberPOResultEntity.getMessage());
            return MEMBER_LOGIN;
        }
        // 账户不存在
        final MemberPO memberPO = memberPOResultEntity.getData();
        if (memberPO == null) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_LOGIN_INVALID);
            return MEMBER_LOGIN;
        }
        // 账户密码错误
        final String correctUserpswd = memberPO.getUserpswd();
        if (!new BCryptPasswordEncoder().matches(userpswd, correctUserpswd)) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_LOGIN_FAILED);
            return MEMBER_LOGIN;
        }
        // 登录成功将 MemberCenterVO 存入 Session 域
        MemberCenterVO memberCenterVO = new MemberCenterVO(memberPO.getId(), memberPO.getLoginacct(), memberPO.getUsername(), memberPO.getEmail());
        session.setAttribute(CrowdConstant.ATTR_NAME_MEMBER, memberCenterVO);
        return "redirect:/auth/member/to/center/page";
    }

    @RequestMapping("/auth/member/do/register")
    public String doRegister(MemberRegisterVO memberRegisterVO, ModelMap modelMap) {
        // ============校验输入============
        // 登录账号为空
        final String loginacct = memberRegisterVO.getLoginacct();
        if (loginacct == null || "".equals(loginacct)) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_LOGIN_ACCT_NOT_NULL);
            return MEMBER_REG;
        }
        // 登录密码为空
        final String userpswd = memberRegisterVO.getUserpswd();
        if (userpswd == null || "".equals(userpswd)) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_PASS_WORD_NOT_NULL);
            return MEMBER_REG;
        }
        // 用户昵称为空
        final String username = memberRegisterVO.getUsername();
        if (username == null || "".equals(username)) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_USER_NAME_NOT_NULL);
            return MEMBER_REG;
        }
        // 邮箱地址为空
        final String email = memberRegisterVO.getEmail();
        if (email == null || "".equals(email)) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_EMAIL_ADDR_NOT_NULL);
            return MEMBER_REG;
        }
        // 手机号为空
        final String phoneNum = memberRegisterVO.getPhoneNum();
        if (phoneNum == null || "".equals(phoneNum)) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_PHONE_NUM_NOT_NULL);
            return MEMBER_REG;
        }
        // 验证码为空
        final String captcha = memberRegisterVO.getCaptcha();
        if (captcha == null || "".equals(captcha)) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_CAPTCHA_NOT_NULL);
            return MEMBER_REG;
        }

        // ============校验验证码============
        final String captchaRedisKey = CrowdConstant.REDIS_KEY_PREFIX + phoneNum;
        final ResultEntity<String> redisResultEntity = redisRemoteService.getRedisValueRemote(captchaRedisKey);
        if (ResultEntity.FAILED.equals(redisResultEntity.getResult())) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, redisResultEntity.getMessage());
            return MEMBER_REG;
        }
        // 验证码已过期
        final String correctCaptcha = redisResultEntity.getData();
        if (correctCaptcha == null || "".equals(correctCaptcha)) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_CAPTCHA_EXPIRED);
            return MEMBER_REG;
        }
        // 验证码不正确
        if (!correctCaptcha.equals(memberRegisterVO.getCaptcha())) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_CAPTCHA_INVALID);
            return MEMBER_REG;
        }

        // ============保存用户信息============
        MemberPO memberPO = new MemberPO();
        BeanUtils.copyProperties(memberRegisterVO, memberPO);
        // 加密密码
        final String encodePwd = new BCryptPasswordEncoder().encode(memberRegisterVO.getUserpswd());
        memberPO.setUserpswd(encodePwd);
        final ResultEntity<String> memberPOResultEntity = mySQLRemoteService.saveMemberPORemote(memberPO);
        // 保存失败
        if (ResultEntity.FAILED.equals(memberPOResultEntity.getResult())) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, memberPOResultEntity.getMessage());
            return MEMBER_REG;
        }

        // ============删除Redis缓存============
        redisRemoteService.removeRedisKeyRemote(captchaRedisKey);
        return "redirect:/auth/member/to/login/page";
    }

    @ResponseBody
    @RequestMapping("/auth/member/send/short/message.json")
    public ResultEntity<String> sendMessage(@RequestParam("phoneNum") String phoneNum) {
        // ===============发送短信===============
        // 请求参数
        String url = shortMessageProperties.getUrl();
        String signName = shortMessageProperties.getSignName();
        String accessKeyId = shortMessageProperties.getAccessKeyId();
        String accessKeySecret = shortMessageProperties.getAccessKeySecret();
        String templateCode = shortMessageProperties.getTemplateCode();
        String captcha = String.valueOf(100000 + new Random().nextInt(900000));
        // 发送短信
        ResultEntity<String> resultEntity;
        try {
            resultEntity = CrowdUtil.sendMessage(url, signName, templateCode, accessKeyId, accessKeySecret, phoneNum, captcha);
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultEntity.failed("请求出错");
        }
        // 短信接口失败返回错误信息
        if (ResultEntity.FAILED.equals(resultEntity.getResult())) {
            return resultEntity;
        }

        // ===============存储Redis===============
        // 成功存入Redis混存
        final String key = CrowdConstant.REDIS_KEY_PREFIX + phoneNum;
        final ResultEntity<String> resultEntityRedis = redisRemoteService.setRedisKeyValueWithTimeRemote(key, captcha, 5, TimeUnit.MINUTES);
        // Redis接口失败返回错误信息
        if (ResultEntity.FAILED.equals(resultEntityRedis.getResult())) {
            return ResultEntity.failed("请求错误");
        }
        return resultEntity;
    }

    @RequestMapping("/")
    public String showPortalPage() {
        return "portal";
    }
}
