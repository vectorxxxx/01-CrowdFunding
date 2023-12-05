package com.vectorx.crowdfunding.mapper;

import com.vectorx.crowdfunding.entity.po.OrderPO;
import com.vectorx.crowdfunding.entity.po.OrderPOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderPOMapper
{
    long countByExample(OrderPOExample example);

    int deleteByExample(OrderPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderPO record);

    int insertSelective(OrderPO record);

    List<OrderPO> selectByExample(OrderPOExample example);

    OrderPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(
            @Param("record")
                    OrderPO record,
            @Param("example")
                    OrderPOExample example);

    int updateByExample(
            @Param("record")
                    OrderPO record,
            @Param("example")
                    OrderPOExample example);

    int updateByPrimaryKeySelective(OrderPO record);

    int updateByPrimaryKey(OrderPO record);
}
