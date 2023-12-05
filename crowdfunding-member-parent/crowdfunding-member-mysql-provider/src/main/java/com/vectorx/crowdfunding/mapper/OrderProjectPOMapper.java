package com.vectorx.crowdfunding.mapper;

import com.vectorx.crowdfunding.entity.po.OrderProjectPO;
import com.vectorx.crowdfunding.entity.po.OrderProjectPOExample;
import com.vectorx.crowdfunding.entity.vo.OrderProjectVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProjectPOMapper
{
    long countByExample(OrderProjectPOExample example);

    int deleteByExample(OrderProjectPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderProjectPO record);

    int insertSelective(OrderProjectPO record);

    List<OrderProjectPO> selectByExample(OrderProjectPOExample example);

    OrderProjectPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(
            @Param("record")
                    OrderProjectPO record,
            @Param("example")
                    OrderProjectPOExample example);

    int updateByExample(
            @Param("record")
                    OrderProjectPO record,
            @Param("example")
                    OrderProjectPOExample example);

    int updateByPrimaryKeySelective(OrderProjectPO record);

    int updateByPrimaryKey(OrderProjectPO record);

    /**
     * 查询订单项目信息
     *
     * @param projectId
     * @param returnId
     * @return {@link OrderProjectPO}
     */
    OrderProjectVO selectOrderProjectVO(
            @Param("projectId")
                    Integer projectId,
            @Param("returnId")
                    Integer returnId);
}
