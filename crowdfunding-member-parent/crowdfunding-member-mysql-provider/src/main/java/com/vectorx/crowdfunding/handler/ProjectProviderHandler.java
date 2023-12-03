package com.vectorx.crowdfunding.handler;

import com.vectorx.crowdfunding.entity.ResultEntity;
import com.vectorx.crowdfunding.entity.vo.DetailProjectVO;
import com.vectorx.crowdfunding.entity.vo.PortalTypeVO;
import com.vectorx.crowdfunding.entity.vo.ProjectVO;
import com.vectorx.crowdfunding.service.api.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectProviderHandler
{
    private Logger LOGGER = LoggerFactory.getLogger(ProjectProviderHandler.class);

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/get/detail/project/data/remote")
    public ResultEntity<DetailProjectVO> getDetailProjectDataRemote(@RequestParam("projectId") Integer projectId) {
        try {
            final DetailProjectVO detailProjectVO = projectService.getDetailProjectVO(projectId);
            return ResultEntity.successWithData(detailProjectVO);
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultEntity.failed(e.getMessage());
        }
    }

    /**
     * 获取首页分类数据
     *
     * @return {@link ResultEntity}<{@link List}<{@link PortalTypeVO}>>
     */
    @RequestMapping("/get/protal/type/project/data/remote")
    public ResultEntity<List<PortalTypeVO>> getPortalTypeProjectDataRemote() {
        try {
            List<PortalTypeVO> portalTypeVOList = projectService.getPortalTypeVOList();
            return ResultEntity.successWithData(portalTypeVOList);
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultEntity.failed(e.getMessage());
        }
    }

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
