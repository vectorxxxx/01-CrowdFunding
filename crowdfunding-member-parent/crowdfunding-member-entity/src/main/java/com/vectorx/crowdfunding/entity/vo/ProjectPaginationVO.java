package com.vectorx.crowdfunding.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPaginationVO
{
    // 项目主键
    private Integer projectId;
    // 项目名称
    private String projectName;
    // 目标金额
    private Integer targetMoney;
    // 发起日期
    private String initiationDate;
    // 支持人数
    private Integer supporterNum;
    // 达成进度
    private Integer reachedProgress;
    // 头图路径
    private String headerPicturePath;
}
