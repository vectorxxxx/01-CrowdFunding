package com.vectorx.crowdfunding.service.impl;

import com.vectorx.crowdfunding.entity.po.MemberConfirmInfoPO;
import com.vectorx.crowdfunding.entity.po.MemberLaunchInfoPO;
import com.vectorx.crowdfunding.entity.po.ProjectPO;
import com.vectorx.crowdfunding.entity.po.ReturnPO;
import com.vectorx.crowdfunding.entity.vo.MemberConfirmInfoVO;
import com.vectorx.crowdfunding.entity.vo.MemberLaunchInfoVO;
import com.vectorx.crowdfunding.entity.vo.ProjectVO;
import com.vectorx.crowdfunding.entity.vo.ReturnVO;
import com.vectorx.crowdfunding.mapper.*;
import com.vectorx.crowdfunding.service.api.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional(readOnly = true)
@Service
public class ProjectServiceImpl implements ProjectService
{
    private Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);

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
        memberLaunchInfoPO.setMemberid(memberId);
        BeanUtils.copyProperties(memberLaunchInfoVO, memberLaunchInfoPO);
        memberLaunchInfoPOMapper.insertSelective(memberLaunchInfoPO);

        // 6、MemberConfirmInfo
        final MemberConfirmInfoVO memberConfirmInfoVO = projectVO.getMemberConfirmInfoVO();
        MemberConfirmInfoPO memberConfirmInfoPO = new MemberConfirmInfoPO();
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
}
