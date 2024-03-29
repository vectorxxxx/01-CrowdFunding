package com.vectorx.crowdfunding.mapper;

import com.vectorx.crowdfunding.entity.po.AddressPO;
import com.vectorx.crowdfunding.entity.po.AddressPOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressPOMapper
{
    long countByExample(AddressPOExample example);

    int deleteByExample(AddressPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AddressPO record);

    int insertSelective(AddressPO record);

    List<AddressPO> selectByExample(AddressPOExample example);

    AddressPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(
            @Param("record")
                    AddressPO record,
            @Param("example")
                    AddressPOExample example);

    int updateByExample(
            @Param("record")
                    AddressPO record,
            @Param("example")
                    AddressPOExample example);

    int updateByPrimaryKeySelective(AddressPO record);

    int updateByPrimaryKey(AddressPO record);
}
