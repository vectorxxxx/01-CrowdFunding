package com.vectorx.crowdfunding.service.impl;

import com.vectorx.crowdfunding.entity.po.MemberConfirmInfoPO;
import com.vectorx.crowdfunding.entity.po.MemberLaunchInfoPO;
import com.vectorx.crowdfunding.entity.po.ProjectPO;
import com.vectorx.crowdfunding.entity.po.ReturnPO;
import com.vectorx.crowdfunding.entity.vo.*;
import com.vectorx.crowdfunding.mapper.*;
import com.vectorx.crowdfunding.service.api.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Transactional(readOnly = true)
@Service
public class ProjectServiceImpl implements ProjectService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Autowired
    private ProjectPOMapper projectPOMapper;

    @Autowired
    private ProjectItemPicPOMapper projectItemPicPOMapper;

    @Autowired
    private MemberLaunchInfoPOMapper memberLaunchInfoPOMapper;

    @Autowired
    private MemberConfirmInfoPOMapper memberConfirmInfoPOMapper;

    @Autowired
    private ReturnPOMapper returnPOMapper;

    /**
     * 保存项目信息
     *
     * @param projectVO
     * @param memberId
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW,
                   rollbackFor = Exception.class)
    @Override
    public void saveProjectVO(ProjectVO projectVO, Integer memberId) {
        // 1、保存 ProjectPO
        ProjectPO projectPO = new ProjectPO();
        projectPO.setMemberid(memberId);
        BeanUtils.copyProperties(projectVO, projectPO);
        projectPO.setStatus(0);
        projectPO.setCreatedate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        projectPOMapper.insertSelective(projectPO);
        final Integer projectId = projectPO.getId();
        LOGGER.info("projectId = " + projectId);

        // 2、typeIdList
        final List<Integer> typeIdList = projectVO.getTypeIdList();
        projectPOMapper.insertTypeRelationship(typeIdList, projectId);

        // 3、tagIdList
        final List<Integer> tagIdList = projectVO.getTagIdList();
        projectPOMapper.insertTagRelationship(tagIdList, projectId);

        // 4、detailPicturePathList
        final List<String> detailPicturePathList = projectVO.getDetailPicturePathList();
        projectItemPicPOMapper.insertPathList(detailPicturePathList, projectId);

        // 5、MemberLaunchInfo
        final MemberLaunchInfoVO memberLaunchInfoVO = projectVO.getMemberLaunchInfoVO();
        MemberLaunchInfoPO memberLaunchInfoPO = new MemberLaunchInfoPO();
        memberLaunchInfoPO.setProjectId(projectId);
        memberLaunchInfoPO.setMemberid(memberId);
        BeanUtils.copyProperties(memberLaunchInfoVO, memberLaunchInfoPO);
        memberLaunchInfoPOMapper.insertSelective(memberLaunchInfoPO);

        // 6、MemberConfirmInfo
        final MemberConfirmInfoVO memberConfirmInfoVO = projectVO.getMemberConfirmInfoVO();
        MemberConfirmInfoPO memberConfirmInfoPO = new MemberConfirmInfoPO();
        memberConfirmInfoPO.setProjectId(projectId);
        memberConfirmInfoPO.setMemberid(memberId);
        BeanUtils.copyProperties(memberConfirmInfoVO, memberConfirmInfoPO);
        memberConfirmInfoPOMapper.insertSelective(memberConfirmInfoPO);

        // 7、ReturnPO
        final List<ReturnVO> returnVOList = projectVO.getReturnVOList();
        List<ReturnPO> returnPOList = new ArrayList<>();
        for (ReturnVO returnVO : returnVOList) {
            ReturnPO returnPO = new ReturnPO();
            returnPO.setProjectid(projectId);
            BeanUtils.copyProperties(returnVO, returnPO);
            returnPOList.add(returnPO);
        }
        returnPOMapper.insertBatch(returnPOList, projectId);
    }

    /**
     * 获取首页分类集合
     *
     * @return {@link List}<{@link PortalTypeVO}>
     */
    @Override
    public List<PortalTypeVO> getPortalTypeVOList() {
        return projectPOMapper.selectPortalTypeVOList();
    }

    @Override
    public DetailProjectVO getDetailProjectVO(Integer projectId) {
        DetailProjectVO detailProjectVO = projectPOMapper.selectDetailProjectVO(projectId);

        // '0-即将开始，1-众筹中，2-众筹成功，3-众筹失败'
        final Integer status = detailProjectVO.getStatus();
        String statusText = "";
        switch (status) {
            case 0:
                statusText = "即将开始";
                break;
            case 1:
                statusText = "众筹中";
                break;
            case 2:
                statusText = "众筹成功";
                break;
            case 3:
                statusText = "众筹失败";
                break;
            default:
                break;
        }
        detailProjectVO.setStatusText(statusText);

        // 剩余天数
        try {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            final long initiationDateTimeStamp = simpleDateFormat.parse(detailProjectVO.getInitiationDate()).getTime();
            final long currentTimeStamp = new Date().getTime();
            final int passedDay = (int) ((currentTimeStamp - initiationDateTimeStamp) / 1000 / 60 / 60 / 24);
            final int remainingDay = detailProjectVO.getRaiseDay() - passedDay;
            detailProjectVO.setRemainingDay(remainingDay);
        }
        catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }

        // 支持人数
        Random random = new Random();
        final List<DetailReturnVO> detailReturnVOList = detailProjectVO.getDetailReturnVOList();
        for (DetailReturnVO detailReturnVO : detailReturnVOList) {
            final int supporterNum = random.nextInt(100);
            detailReturnVO.setSupporterNum(String.valueOf(supporterNum));
        }

        return detailProjectVO;
    }

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
    @Override
    public List<ProjectPaginationVO> getProjectPaginationVOList(Integer pageNum, Integer pageSize, Integer typeId, Integer status, Integer sortType, String searchContent) {
        final List<ProjectPaginationVO> projectPaginationVOList = projectPOMapper.selectProjectPaginationVO(pageNum * 12, pageSize, typeId, status, sortType, searchContent);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            for (ProjectPaginationVO projectPaginationVO : projectPaginationVOList) {
                projectPaginationVO.setInitiationDate(simpleDateFormat.format(simpleDateFormat.parse(projectPaginationVO.getInitiationDate())));
            }
        }
        catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return projectPaginationVOList;
    }

    /**
     * 获取项目分页总数
     *
     * @param typeId
     * @param status
     * @param sortType
     * @param searchContent
     * @return {@link Integer}
     */
    @Override
    public Integer getProjectPaginationVOCount(Integer typeId, Integer status, Integer sortType, String searchContent) {
        return projectPOMapper.countProjectPaginationVO(typeId, status, sortType, searchContent);
    }
}
