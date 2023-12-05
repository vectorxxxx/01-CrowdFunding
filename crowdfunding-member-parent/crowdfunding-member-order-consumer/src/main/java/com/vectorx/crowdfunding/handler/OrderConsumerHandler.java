package com.vectorx.crowdfunding.handler;

import com.vectorx.crowdfunding.api.MySQLRemoteService;
import com.vectorx.crowdfunding.entity.ResultEntity;
import com.vectorx.crowdfunding.entity.constant.CrowdConstant;
import com.vectorx.crowdfunding.entity.vo.OrderProjectVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class OrderConsumerHandler
{
    private Logger LOGGER = LoggerFactory.getLogger(OrderConsumerHandler.class);

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    @RequestMapping("/confirm/return/info/{projectId}/{returnId}")
    public String getConfirmReturnInfo(
            @PathVariable("projectId")
                    Integer projectId,
            @PathVariable("returnId")
                    Integer returnId, HttpSession session) {
        final ResultEntity<OrderProjectVO> orderProjectVOResultEntity = mySQLRemoteService.getOrderProjectDataRemote(projectId, returnId);
        if (ResultEntity.SUCCESS.equals(orderProjectVOResultEntity.getResult())) {
            final OrderProjectVO orderProjectVO = orderProjectVOResultEntity.getData();
            session.setAttribute("orderProjectVO", orderProjectVO);
        }
        return CrowdConstant.CONFIRM_PROJECT;
    }
}
