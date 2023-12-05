package com.vectorx.crowdfunding.service.api;

import com.vectorx.crowdfunding.entity.vo.DetailProjectVO;
import com.vectorx.crowdfunding.entity.vo.PortalTypeVO;
import com.vectorx.crowdfunding.entity.vo.ProjectPaginationVO;
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

    /**
     * 获取项目详情信息
     *
     * @param projectId
     * @return {@link DetailProjectVO}
     * @throws ParseException
     */
    DetailProjectVO getDetailProjectVO(Integer projectId) throws ParseException;

    /**
     * 获取项目分页信息
     *
     * @param pageNum
     * @param pageSize
     * @param typeId
     * @param status
     * @param sortType
     * @param searchContent
     * @return {@link List}<{@link ProjectPaginationVO}>
     */
    List<ProjectPaginationVO> getProjectPaginationVOList(Integer pageNum, Integer pageSize, Integer typeId, Integer status, Integer sortType, String searchContent);

    /**
     * 获取项目分页总数
     *
     * @param typeId
     * @param status
     * @param sortType
     * @param searchContent
     * @return {@link Integer}
     */
    Integer getProjectPaginationVOCount(Integer typeId, Integer status, Integer sortType, String searchContent);
}
