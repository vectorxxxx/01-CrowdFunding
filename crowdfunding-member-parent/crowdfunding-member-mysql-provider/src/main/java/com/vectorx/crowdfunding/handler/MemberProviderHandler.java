package com.vectorx.crowdfunding.handler;

import com.vectorx.crowdfunding.entity.ResultEntity;
import com.vectorx.crowdfunding.entity.po.MemberPO;
import com.vectorx.crowdfunding.service.api.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 以json/xml方式返回数据
@RestController
public class MemberProviderHandler
{
    private Logger logger = LoggerFactory.getLogger(MemberProviderHandler.class);

    @Autowired
    private MemberService memberService;

    @RequestMapping("/get/memberpo/by/login/acct/remote")
    public ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct) {
        try {
            final MemberPO memberPO = memberService.getMemberPOByLoginAcct(loginacct);
            return ResultEntity.successWithData(memberPO);
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/save/memberpo/remote")
    public ResultEntity<String> saveMemberPORemote(@RequestBody MemberPO memberPO) {
        try {
            memberService.saveMemberPO(memberPO);
            return ResultEntity.successWithoutData();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResultEntity.failed(e.getMessage());
        }
    }
}
