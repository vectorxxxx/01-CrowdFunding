package com.vectorx.crowdfunding.service.api;

import com.vectorx.crowdfunding.entity.vo.OrderProjectVO;

public interface OrderService
{
    /**
     * 获取订单项目VO对象
     *
     * @param projectId
     * @param returnId
     * @return {@link OrderProjectVO}
     */
    OrderProjectVO getOrderProjectVO(Integer projectId, Integer returnId);
}
