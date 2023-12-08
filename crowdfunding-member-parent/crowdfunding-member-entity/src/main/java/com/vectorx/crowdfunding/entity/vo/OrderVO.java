package com.vectorx.crowdfunding.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO implements Serializable
{
    private static final long serialVersionUID = 860580506118142641L;

    // 订单号
    private String orderNum;

    // 支付宝流水号
    private String payOrderNum;

    // 订单金额
    private Long orderAmount;

    // 是否需要发票
    private Integer invoice;

    // 发票抬头
    private String invoiceTitle;

    // 备注
    private String orderRemark;

    // 收货地址ID
    private Integer addressId;

    // 项目回报信息
    private OrderProjectVO orderProjectVO;
}
