package com.vectorx.crowdfunding.service.impl;

import com.vectorx.crowdfunding.entity.vo.OrderProjectVO;
import com.vectorx.crowdfunding.mapper.OrderProjectPOMapper;
import com.vectorx.crowdfunding.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class OrderServiceImpl implements OrderService
{
    @Autowired
    private OrderProjectPOMapper orderProjectPOMapper;

    /**
     * 获取订单项目VO对象
     *
     * @param projectId 项目ID
     * @param returnId 回报ID
     * @return {@link OrderProjectVO}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW,
                   rollbackFor = Exception.class)
    @Override
    public OrderProjectVO getOrderProjectVO(Integer projectId, Integer returnId) {
        return orderProjectPOMapper.selectOrderProjectVO(projectId, returnId);
    }
}
