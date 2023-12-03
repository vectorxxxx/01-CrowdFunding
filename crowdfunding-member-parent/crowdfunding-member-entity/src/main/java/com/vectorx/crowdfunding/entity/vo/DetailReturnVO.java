package com.vectorx.crowdfunding.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailReturnVO
{
    // 回报信息主键
    private Integer returnId;
    // 支持金额
    private Integer supportMoney;
    // 是否限制单笔购买数量，0 表示不限购，1 表示限购
    private Integer signalPurchaseLimit;
    // 如果单笔限购，那么具体的限购数量
    private Integer purchaseLimit;
    // 支持人数
    private String supporterNum;
    // 运费，“0”为包邮
    private Integer freight;
    // 众筹结束后返还回报物品天数
    private Integer returnDay;
    // 回报内容介绍
    private String content;
}
