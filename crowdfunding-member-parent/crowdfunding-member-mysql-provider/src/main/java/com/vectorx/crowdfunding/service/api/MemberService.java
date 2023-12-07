package com.vectorx.crowdfunding.service.api;

import com.vectorx.crowdfunding.entity.po.MemberPO;
import com.vectorx.crowdfunding.entity.vo.AddressVO;

import java.util.List;

public interface MemberService
{
    MemberPO getMemberPOByLoginAcct(String loginacct);

    void saveMemberPO(MemberPO memberPO);

    /**
     * 通过会员ID获取收货地址
     *
     * @param memberId 会员ID
     * @return {@link List}<{@link AddressVO}>
     */
    List<AddressVO> getAddressInfoByMemberId(Integer memberId);

    /**
     * 保存收货地址信息
     *
     * @param addressVO 收货地址信息
     */
    void saveAddressInfo(AddressVO addressVO);
}
