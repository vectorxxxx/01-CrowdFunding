package com.vectorx.crowdfunding.service.api;

import com.vectorx.crowdfunding.entity.vo.DetailProjectVO;
import com.vectorx.crowdfunding.entity.vo.PortalTypeVO;
import com.vectorx.crowdfunding.entity.vo.ProjectVO;

import java.text.ParseException;
import java.util.List;

public interface ProjectService
{
    /**
     * 保存项目信息
     *
     * @param projectVO 项目信息
     * @param memberId 会员ID
     */
    void saveProjectVO(ProjectVO projectVO, Integer memberId);

    /**
     * 获取首页分类集合
     *
     * @return {@link List}<{@link PortalTypeVO}>
     */
    List<PortalTypeVO> getPortalTypeVOList();

    DetailProjectVO getDetailProjectVO(Integer projectId) throws ParseException;
}
