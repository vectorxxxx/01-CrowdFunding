package com.vectorx.crowdfunding.service.impl;

import com.vectorx.crowdfunding.entity.po.OrderPO;
import com.vectorx.crowdfunding.entity.po.OrderProjectPO;
import com.vectorx.crowdfunding.entity.vo.OrderProjectVO;
import com.vectorx.crowdfunding.entity.vo.OrderVO;
import com.vectorx.crowdfunding.mapper.OrderPOMapper;
import com.vectorx.crowdfunding.mapper.OrderProjectPOMapper;
import com.vectorx.crowdfunding.service.api.OrderService;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private OrderPOMapper orderPOMapper;

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

    /**
     * 保存订单实体
     *
     * @param orderVO
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW,
                   rollbackFor = Exception.class)
    @Override
    public void saveOrderVO(OrderVO orderVO) {
        // 保存 Order 实体
        OrderPO orderPO = new OrderPO();
        BeanUtils.copyProperties(orderVO, orderPO);
        orderPOMapper.insert(orderPO);

        // 拿到订单ID
        // ！！！特别注意：不在 OrderPOMapper.xml 中 insert 语句写 useGeneratedKeys="true" keyProperty="id" 拿不到ID的
        final Integer orderId = orderPO.getId();

        // 保存 OrderProject 实体
        OrderProjectVO orderProjectVO = orderVO.getOrderProjectVO();
        OrderProjectPO orderProjectPO = new OrderProjectPO();
        BeanUtils.copyProperties(orderProjectVO, orderProjectPO);
        orderProjectPO.setOrderId(orderId);
        orderProjectPOMapper.insert(orderProjectPO);
    }
}
