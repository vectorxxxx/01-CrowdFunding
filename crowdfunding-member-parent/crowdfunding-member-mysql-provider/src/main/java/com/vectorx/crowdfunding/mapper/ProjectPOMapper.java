package com.vectorx.crowdfunding.mapper;

import com.vectorx.crowdfunding.entity.po.ProjectPO;
import com.vectorx.crowdfunding.entity.po.ProjectPOExample;
import com.vectorx.crowdfunding.entity.vo.DetailProjectVO;
import com.vectorx.crowdfunding.entity.vo.PortalTypeVO;
import com.vectorx.crowdfunding.entity.vo.ProjectPaginationVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectPOMapper
{
    long countByExample(ProjectPOExample example);

    int deleteByExample(ProjectPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectPO record);

    int insertSelective(ProjectPO record);

    List<ProjectPO> selectByExample(ProjectPOExample example);

    ProjectPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(
            @Param("record")
                    ProjectPO record,
            @Param("example")
                    ProjectPOExample example);

    int updateByExample(
            @Param("record")
                    ProjectPO record,
            @Param("example")
                    ProjectPOExample example);

    int updateByPrimaryKeySelective(ProjectPO record);

    int updateByPrimaryKey(ProjectPO record);

    /**
     * 插入类型关联表
     *
     * @param typeIdList 类型集合
     * @param projectId 项目ID
     */
    void insertTypeRelationship(
            @Param("typeIdList")
                    List<Integer> typeIdList,
            @Param("projectId")
                    Integer projectId);

    /**
     * 插入标签关联表
     *
     * @param tagIdList 标签集合
     * @param projectId 项目ID
     */
    void insertTagRelationship(
            @Param("tagIdList")
                    List<Integer> tagIdList,
            @Param("projectId")
                    Integer projectId);

    /**
     * 查询首页内容
     *
     * @return {@link List}<{@link PortalTypeVO}>
     */
    List<PortalTypeVO> selectPortalTypeVOList();

    /**
     * 查询项目详情信息
     *
     * @param projectId 项目ID
     * @return {@link DetailProjectVO}
     */
    DetailProjectVO selectDetailProjectVO(
            @Param("projectId")
                    Integer projectId);

    /**
     * 查询项目分页集合
     *
     * @param pageNum
     * @param pageSize
     * @param typeId
     * @param status
     * @param sortType
     * @param searchContent
     * @return {@link List}<{@link ProjectPaginationVO}>
     */
    List<ProjectPaginationVO> selectProjectPaginationVO(
            @Param("offset")
                    Integer offset,
            @Param("rows")
                    Integer rows,
            @Param("typeId")
                    Integer typeId,
            @Param("status")
                    Integer status,
            @Param("sortType")
                    Integer sortType,
            @Param("searchContent")
                    String searchContent);

    /**
     * 查询项目分页总数
     *
     * @param typeId
     * @param status
     * @param sortType
     * @param searchContent
     * @return {@link Integer}
     */
    Integer countProjectPaginationVO(
            @Param("typeId")
                    Integer typeId,
            @Param("status")
                    Integer status,
            @Param("sortType")
                    Integer sortType,
            @Param("searchContent")
                    String searchContent);
}
