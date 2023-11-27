package com.vectorx.crowdfunding.mapper;

import com.vectorx.crowdfunding.entity.po.ProjectPO;
import com.vectorx.crowdfunding.entity.po.ProjectPOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectPOMapper
{
    long countByExample(ProjectPOExample example);

    int deleteByExample(ProjectPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectPO record);

    int insertSelective(ProjectPO record);

    List<ProjectPO> selectByExample(ProjectPOExample example);

    ProjectPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectPO record, @Param("example") ProjectPOExample example);

    int updateByExample(@Param("record") ProjectPO record, @Param("example") ProjectPOExample example);

    int updateByPrimaryKeySelective(ProjectPO record);

    int updateByPrimaryKey(ProjectPO record);
}
