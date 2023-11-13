package com.vectorx.crowdfunding.service.impl;

import com.vectorx.crowdfunding.entity.po.MemberPO;
import com.vectorx.crowdfunding.entity.po.MemberPOExample;
import com.vectorx.crowdfunding.mapper.MemberPOMapper;
import com.vectorx.crowdfunding.service.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 开启只读事务
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService
{

    @Autowired
    private MemberPOMapper memberPOMapper;

    @Override
    public MemberPO getMemberPOByLoginAcct(String loginacct) {
        final MemberPOExample memberPOExample = new MemberPOExample();
        final MemberPOExample.Criteria criteria = memberPOExample.createCriteria();
        criteria.andLoginacctEqualTo(loginacct);
        final List<MemberPO> memberPOList = memberPOMapper.selectByExample(memberPOExample);
        return memberPOList.get(0);
    }
}