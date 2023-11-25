package com.vectorx.crowdfunding.service.api;

import com.vectorx.crowdfunding.entity.po.MemberPO;

public interface MemberService
{
    MemberPO getMemberPOByLoginAcct(String loginacct);

    void saveMemberPO(MemberPO memberPO);
}
