package com.vectorx.crowdfunding.service.impl;

import com.vectorx.crowdfunding.entity.po.AddressPO;
import com.vectorx.crowdfunding.entity.po.AddressPOExample;
import com.vectorx.crowdfunding.entity.po.MemberPO;
import com.vectorx.crowdfunding.entity.po.MemberPOExample;
import com.vectorx.crowdfunding.entity.vo.AddressVO;
import com.vectorx.crowdfunding.mapper.AddressPOMapper;
import com.vectorx.crowdfunding.mapper.MemberPOMapper;
import com.vectorx.crowdfunding.service.api.MemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

// 开启只读事务
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService
{

    @Autowired
    private MemberPOMapper memberPOMapper;

    @Autowired
    private AddressPOMapper addressPOMapper;

    @Override
    public MemberPO getMemberPOByLoginAcct(String loginacct) {
        final MemberPOExample memberPOExample = new MemberPOExample();
        final MemberPOExample.Criteria criteria = memberPOExample.createCriteria();
        criteria.andLoginacctEqualTo(loginacct);
        final List<MemberPO> memberPOList = memberPOMapper.selectByExample(memberPOExample);
        if (memberPOList == null || memberPOList.size() == 0) {
            return null;
        }
        return memberPOList.get(0);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,
                   rollbackFor = Exception.class)
    @Override
    public void saveMemberPO(MemberPO memberPO) {
        memberPOMapper.insertSelective(memberPO);
    }

    /**
     * 通过会员ID获取收货地址
     *
     * @param memberId 会员ID
     * @return {@link List}<{@link AddressVO}>
     */
    @Override
    public List<AddressVO> getAddressInfoByMemberId(Integer memberId) {
        // 查询数据
        AddressPOExample example = new AddressPOExample();
        final AddressPOExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo(memberId);
        final List<AddressPO> addressPOList = addressPOMapper.selectByExample(example);

        // PO ==> VO
        List<AddressVO> addressVOList = new ArrayList<>();
        for (AddressPO addressPO : addressPOList) {
            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(addressPO, addressVO);
            addressVOList.add(addressVO);
        }
        return addressVOList;
    }

    /**
     * 保存收货地址信息
     *
     * @param addressVO 收货地址信息
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW,
                   rollbackFor = Exception.class)
    @Override
    public void saveAddressInfo(AddressVO addressVO) {
        AddressPO addressPO = new AddressPO();
        BeanUtils.copyProperties(addressVO, addressPO);
        addressPOMapper.insertSelective(addressPO);
    }
}
