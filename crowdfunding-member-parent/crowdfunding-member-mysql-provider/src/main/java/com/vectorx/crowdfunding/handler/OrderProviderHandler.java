package com.vectorx.crowdfunding.handler;

import com.vectorx.crowdfunding.entity.ResultEntity;
import com.vectorx.crowdfunding.entity.vo.OrderProjectVO;
import com.vectorx.crowdfunding.service.api.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderProviderHandler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProviderHandler.class);

    @Autowired
    private OrderService orderService;

    /**
     * 获取订单项目数据
     *
     * @param projectId
     * @param returnId
     * @return {@link ResultEntity}<{@link OrderProjectVO}>
     */
    @RequestMapping("/get/order/project/data/remote")
    public ResultEntity<OrderProjectVO> getOrderProjectDataRemote(
            @RequestParam("projectId")
                    Integer projectId,
            @RequestParam("returnId")
                    Integer returnId) {
        try {
            OrderProjectVO orderProjectVO = orderService.getOrderProjectVO(projectId, returnId);
            return ResultEntity.successWithData(orderProjectVO);
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultEntity.failed(e.getMessage());
        }
    }
}
