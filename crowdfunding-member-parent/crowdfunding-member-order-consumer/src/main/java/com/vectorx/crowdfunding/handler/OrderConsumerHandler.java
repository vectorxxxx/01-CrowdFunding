package com.vectorx.crowdfunding.handler;

import com.vectorx.crowdfunding.api.MySQLRemoteService;
import com.vectorx.crowdfunding.entity.ResultEntity;
import com.vectorx.crowdfunding.entity.constant.CrowdConstant;
import com.vectorx.crowdfunding.entity.vo.AddressVO;
import com.vectorx.crowdfunding.entity.vo.MemberCenterVO;
import com.vectorx.crowdfunding.entity.vo.OrderProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderConsumerHandler
{
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
            session.setAttribute(CrowdConstant.ATTR_NAME_ORDER_PROJECT_VO, orderProjectVO);
        }
        return CrowdConstant.CONFIRM_RETURN;
    }

    @RequestMapping("/confirm/order/info/{returnCount}")
    public String getConfirmOrderInfo(
            @PathVariable("returnCount")
                    Integer returnCount, HttpSession session, ModelMap modelMap) {
        // 查询session中的确认回报内容
        final OrderProjectVO orderProjectVO = (OrderProjectVO) session.getAttribute(CrowdConstant.ATTR_NAME_ORDER_PROJECT_VO);
        if (orderProjectVO == null) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_CONFIRM_RETURN_MISSING);
            return CrowdConstant.CONFIRM_ORDER;
        }

        // 设置回报数量，并放回session域
        orderProjectVO.setReturnCount(returnCount);
        session.setAttribute(CrowdConstant.ATTR_NAME_ORDER_PROJECT_VO, orderProjectVO);

        // 查询当前会员ID
        final MemberCenterVO memberCenterVO = (MemberCenterVO) session.getAttribute(CrowdConstant.ATTR_NAME_MEMBER);
        final Integer memberId = memberCenterVO.getId();

        // 查询地址信息
        ResultEntity<List<AddressVO>> addressVOListResultEntity = mySQLRemoteService.getAddressInfoByMemberIdRemote(memberId);
        if (ResultEntity.SUCCESS.equals(addressVOListResultEntity.getResult())) {
            session.setAttribute(CrowdConstant.ATTR_NAME_ADDRESS_VO_LIST, addressVOListResultEntity.getData());
        }

        // 跳转确认订单页面
        return CrowdConstant.CONFIRM_ORDER;
    }

    @RequestMapping("/add/new/address")
    public String saveAddressInfo(AddressVO addressVO, HttpSession session, ModelMap modelMap) {
        // 查询当前会员ID
        final MemberCenterVO memberCenterVO = (MemberCenterVO) session.getAttribute(CrowdConstant.ATTR_NAME_MEMBER);
        final Integer memberId = memberCenterVO.getId();

        // 保存新地址信息
        addressVO.setMemberId(memberId);
        ResultEntity<String> resultEntity = mySQLRemoteService.saveAddressInfoRemote(addressVO);
        if (ResultEntity.FAILED.equals(resultEntity.getResult())) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_SAVE_NEW_ADDRESS_FAILED);
        }

        // 查询所有地址信息
        ResultEntity<List<AddressVO>> addressVOListResultEntity = mySQLRemoteService.getAddressInfoByMemberIdRemote(memberId);
        if (ResultEntity.SUCCESS.equals(addressVOListResultEntity.getResult())) {
            session.setAttribute(CrowdConstant.ATTR_NAME_ADDRESS_VO_LIST, addressVOListResultEntity.getData());
        }

        // 订单号
        // 支付宝流水号
        // 订单金额

        // 跳转确认订单页面
        return CrowdConstant.CONFIRM_ORDER;
    }
}
