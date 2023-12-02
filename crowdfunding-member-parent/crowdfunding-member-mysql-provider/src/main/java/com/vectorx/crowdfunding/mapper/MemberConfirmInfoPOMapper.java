package com.vectorx.crowdfunding.mapper;

import com.vectorx.crowdfunding.entity.po.MemberConfirmInfoPO;
import com.vectorx.crowdfunding.entity.po.MemberConfirmInfoPOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberConfirmInfoPOMapper
{
    long countByExample(MemberConfirmInfoPOExample example);

    int deleteByExample(MemberConfirmInfoPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberConfirmInfoPO record);

    int insertSelective(MemberConfirmInfoPO record);

    List<MemberConfirmInfoPO> selectByExample(MemberConfirmInfoPOExample example);

    MemberConfirmInfoPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberConfirmInfoPO record, @Param("example") MemberConfirmInfoPOExample example);

    int updateByExample(@Param("record") MemberConfirmInfoPO record, @Param("example") MemberConfirmInfoPOExample example);

    int updateByPrimaryKeySelective(MemberConfirmInfoPO record);

    int updateByPrimaryKey(MemberConfirmInfoPO record);
}
