package com.vectorx.crowdfunding.service.api;

import com.vectorx.crowdfunding.entity.vo.ProjectVO;

public interface ProjectService
{
    void saveProjectVO(ProjectVO projectVO, Integer memberId);
}
