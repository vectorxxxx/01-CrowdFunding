package com.vectorx.crowdfunding.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailProjectVO
{
    // 项目主键
    private Integer projectId;
    // 项目名称
    private String projectName;
    // 项目描述
    private String projectDescription;
    // 关注人数
    private Integer followerNum;
    // 项目状态
    private Integer status;
    private String statusText;
    // 目标金额
    private Integer targetMoney;
    // 已筹资金
    private Integer fundedMoney;
    // 达成进度
    private Integer reachedProgress;
    // 发起日期
    private String initiationDate;
    // 筹集天数
    private Integer raiseDay;
    // 剩余天数
    private Integer remainingDay;
    // 支持人数
    private Integer supporterNum;
    // 头图路径
    private String headerPicturePath;
    // 详情图片路径集合
    private List<String> detailPicturePathList;
    // 回报详情集合
    private List<DetailReturnVO> detailReturnVOList;
}
