package com.vectorx.crowdfunding.handler;

import com.vectorx.crowdfunding.entity.ResultEntity;
import com.vectorx.crowdfunding.entity.po.MemberPO;
import com.vectorx.crowdfunding.entity.vo.AddressVO;
import com.vectorx.crowdfunding.service.api.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 以json/xml方式返回数据
@RestController
public class MemberProviderHandler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberProviderHandler.class);

    @Autowired
    private MemberService memberService;

    /**
     * 保存收货地址信息
     *
     * @param addressVO 收货地址信息
     * @return {@link ResultEntity}<{@link String}>
     */
    @RequestMapping("/save/address/info/remote")
    public ResultEntity<String> saveAddressInfoRemote(
            @RequestBody
                    AddressVO addressVO) {
        try {
            memberService.saveAddressInfo(addressVO);
            return ResultEntity.successWithoutData();
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultEntity.failed(e.getMessage());
        }
    }

    /**
     * 通过会员ID获取收货地址
     *
     * @param memberId 会员ID
     * @return {@link ResultEntity}<{@link List}<{@link AddressVO}>>
     */
    @RequestMapping("/get/address/info/by/member/id/remote")
    public ResultEntity<List<AddressVO>> getAddressInfoByMemberIdRemote(
            @RequestParam("memberId")
                    Integer memberId) {
        try {
            List<AddressVO> addressVOList = memberService.getAddressInfoByMemberId(memberId);
            return ResultEntity.successWithData(addressVOList);
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultEntity.failed(e.getMessage());
        }
    }

    /**
     * 通过登录账号获取会员对象
     *
     * @param loginacct 登录账号
     * @return {@link ResultEntity}<{@link MemberPO}>
     */
    @RequestMapping("/get/memberpo/by/login/acct/remote")
    public ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(
            @RequestParam("loginacct")
                    String loginacct) {
        try {
            final MemberPO memberPO = memberService.getMemberPOByLoginAcct(loginacct);
            return ResultEntity.successWithData(memberPO);
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultEntity.failed(e.getMessage());
        }
    }

    /**
     * 保存会员对象
     *
     * @param memberPO 会员对象
     * @return {@link ResultEntity}<{@link String}>
     */
    @RequestMapping("/save/memberpo/remote")
    public ResultEntity<String> saveMemberPORemote(
            @RequestBody
                    MemberPO memberPO) {
        try {
            memberService.saveMemberPO(memberPO);
            return ResultEntity.successWithoutData();
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultEntity.failed(e.getMessage());
        }
    }
}
