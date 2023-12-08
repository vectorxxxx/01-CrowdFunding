package com.vectorx.crowdfunding.service.api;

import com.vectorx.crowdfunding.entity.vo.OrderProjectVO;
import com.vectorx.crowdfunding.entity.vo.OrderVO;

public interface OrderService
{
    /**
     * 获取订单项目VO对象
     *
     * @param projectId 项目ID
     * @param returnId 回报ID
     * @return {@link OrderProjectVO}
     */
    OrderProjectVO getOrderProjectVO(Integer projectId, Integer returnId);

    /**
     * 保存订单实体
     *
     * @param orderVO
     */
    void saveOrderVO(OrderVO orderVO);
}
