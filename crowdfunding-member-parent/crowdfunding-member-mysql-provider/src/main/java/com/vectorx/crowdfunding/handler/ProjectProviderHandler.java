package com.vectorx.crowdfunding.handler;

import com.vectorx.crowdfunding.entity.ResultEntity;
import com.vectorx.crowdfunding.entity.vo.ProjectVO;
import com.vectorx.crowdfunding.service.api.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectProviderHandler
{
    private Logger LOGGER = LoggerFactory.getLogger(ProjectProviderHandler.class);

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/save/projectvo/remote")
    public ResultEntity<String> saveProjectVORemote(@RequestBody ProjectVO projectVO, @RequestParam("memberId") Integer memberId) {
        try {
            projectService.saveProjectVO(projectVO, memberId);
            return ResultEntity.successWithoutData();
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultEntity.failed(e.getMessage());
        }
    }
}
