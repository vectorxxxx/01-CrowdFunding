package com.vectorx.crowdfunding.handler;

import com.google.gson.Gson;
import com.vectorx.crowdfunding.entity.ResultEntity;
import com.vectorx.crowdfunding.entity.vo.DetailProjectVO;
import com.vectorx.crowdfunding.entity.vo.PortalTypeVO;
import com.vectorx.crowdfunding.entity.vo.ProjectPaginationVO;
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

    /**
     * 获取项目分页总数
     *
     * @param typeId
     * @param status
     * @param sortType
     * @param searchContent
     * @return {@link ResultEntity}<{@link Integer}>
     */
    @RequestMapping("/get/project/pagination/total/count/remote")
    public ResultEntity<Integer> getProjectPaginationTotalCountRemote(
            @RequestParam(value = "typeId",
                          required = false)
                    Integer typeId,
            @RequestParam(value = "status",
                          required = false)
                    Integer status,
            @RequestParam(value = "sortType",
                          defaultValue = "0")
                    Integer sortType,
            @RequestParam(value = "searchContent",
                          required = false)
                    String searchContent) {
        try {
            final Integer count = projectService.getProjectPaginationVOCount(typeId, status, sortType, searchContent);
            return ResultEntity.successWithData(count);
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultEntity.failed(e.getMessage());
        }
    }

    /**
     * 获取项目分页信息
     *
     * @param pageNum 页码
     * @param pageSize 每页数
     * @param typeId
     *          1,科技
     *          2,设计
     *          3,公益
     *          4,农业
     * @param status '0-即将开始，1-众筹中，2-众筹成功，3-众筹失败'
     * @param sortType -- 0-综合排序，1-最新上线，2-金额最多，3-支持最多
     * @param searchContent 搜索内容
     * @return {@link ResultEntity}<{@link List}<{@link ProjectPaginationVO}>>
     */
    @RequestMapping("/get/project/pagination/data/remote")
    public ResultEntity<List<ProjectPaginationVO>> getProjectPaginationDataRemote(
            @RequestParam("pageNum")
                    Integer pageNum,
            @RequestParam(value = "pageSize",
                          defaultValue = "12")
                    Integer pageSize,
            @RequestParam(value = "typeId",
                          required = false)
                    Integer typeId,
            @RequestParam(value = "status",
                          required = false)
                    Integer status,
            @RequestParam(value = "sortType",
                          defaultValue = "0")
                    Integer sortType,
            @RequestParam(value = "searchContent",
                          required = false)
                    String searchContent) {
        try {
            final List<ProjectPaginationVO> projectPaginationVOList = projectService.getProjectPaginationVOList(pageNum, pageSize, typeId, status, sortType, searchContent);
            LOGGER.info(new Gson().toJson(projectPaginationVOList));
            return ResultEntity.successWithData(projectPaginationVOList);
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/get/detail/project/data/remote")
    public ResultEntity<DetailProjectVO> getDetailProjectDataRemote(
            @RequestParam("projectId")
                    Integer projectId) {
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
    public ResultEntity<String> saveProjectVORemote(
            @RequestBody
                    ProjectVO projectVO,
            @RequestParam("memberId")
                    Integer memberId) {
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
