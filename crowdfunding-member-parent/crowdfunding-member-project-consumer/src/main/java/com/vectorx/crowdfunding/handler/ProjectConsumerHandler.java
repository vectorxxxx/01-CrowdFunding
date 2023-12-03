package com.vectorx.crowdfunding.handler;

import com.vectorx.crowdfunding.api.MySQLRemoteService;
import com.vectorx.crowdfunding.entity.ResultEntity;
import com.vectorx.crowdfunding.entity.constant.CrowdConstant;
import com.vectorx.crowdfunding.entity.vo.*;
import com.vectorx.crowdfunding.properties.OSSProperties;
import com.vectorx.crowdfunding.util.CrowdOSSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class ProjectConsumerHandler
{
    private Logger LOGGER = LoggerFactory.getLogger(ProjectConsumerHandler.class);

    @Autowired
    private OSSProperties ossProperties;

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    /**
     * 获取项目详情信息
     *
     * @param projectId 项目ID
     * @param model 模型
     * @return {@link String}
     */
    @RequestMapping("/get/project/detail/info/{projectId}")
    public String getProjectDetailInfo(@PathVariable("projectId") Integer projectId, Model model) {
        final ResultEntity<DetailProjectVO> detailProjectVOResultEntity = mySQLRemoteService.getDetailProjectDataRemote(projectId);
        if (ResultEntity.SUCCESS.equals(detailProjectVOResultEntity.getResult())) {
            final DetailProjectVO detailProjectVO = detailProjectVOResultEntity.getData();
            model.addAttribute("detailProjectVO", detailProjectVO);
        }
        return CrowdConstant.PROJECT_DETAIL;
    }

    @ResponseBody
    @RequestMapping("/save/confirm/draft.json")
    public ResultEntity<String> saveConfirmDraft(MemberConfirmInfoVO confirmInfoVO, HttpSession session) {
        // Step0、获取临时ProjectVO对象
        ProjectVO sessionProjectVO = (ProjectVO) session.getAttribute(CrowdConstant.ATTR_NAME_TEMP_PROJECT);
        if (sessionProjectVO == null) {
            return ResultEntity.failed(CrowdConstant.MSG_TEMP_PROJECT_MISSING);
        }

        // Step1、设置确认信息
        sessionProjectVO.setMemberConfirmInfoVO(confirmInfoVO);
        session.setAttribute(CrowdConstant.ATTR_NAME_TEMP_PROJECT, sessionProjectVO);

        // Step2、返回成功消息
        return ResultEntity.successWithoutData();
    }

    @RequestMapping("/create/confirm/information")
    public String saveConfirmInfo(ModelMap modelMap, MemberConfirmInfoVO confirmInfoVO, HttpSession session) {
        // Step0、保存草稿
        final ResultEntity<String> confirmDraftResultEntity = saveConfirmDraft(confirmInfoVO, session);
        if (ResultEntity.FAILED.equals(confirmDraftResultEntity.getResult())) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, confirmDraftResultEntity.getMessage());
            return CrowdConstant.PROJECT_CONFIRM;
        }

        // Step1、获取临时ProjectVO对象
        ProjectVO sessionProjectVO = (ProjectVO) session.getAttribute(CrowdConstant.ATTR_NAME_TEMP_PROJECT);

        // Step2、查询会员ID
        final MemberCenterVO memberCenterVO = (MemberCenterVO) session.getAttribute(CrowdConstant.ATTR_NAME_MEMBER);
        final Integer memberId = memberCenterVO.getId();

        // Step3、存入数据库
        ResultEntity<String> resultEntity = mySQLRemoteService.saveProjectVORemote(sessionProjectVO, memberId);
        if (ResultEntity.FAILED.equals(resultEntity.getResult())) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, resultEntity.getMessage());
            return CrowdConstant.PROJECT_CONFIRM;
        }

        // Step4、移除 Session 中的临时 ProjectVO 对象并跳转至完成页面
        session.removeAttribute(CrowdConstant.ATTR_NAME_TEMP_PROJECT);
        return CrowdConstant.REDIRECT_HTTP_LOCALHOST + "/project/create/success/page";
    }

    /**
     * 创建回报设置信息
     *
     * AJAX 请求需要 @ResponseBody
     *
     * @param returnVO
     * @param session
     * @return {@link ResultEntity}<{@link String}>
     */
    @ResponseBody
    @RequestMapping("/create/return/info.json")
    public ResultEntity<String> saveReturnInfo(ReturnVO returnVO, HttpSession session) {
        // Step0、获取临时ProjectVO对象
        ProjectVO sessionProjectVO = (ProjectVO) session.getAttribute(CrowdConstant.ATTR_NAME_TEMP_PROJECT);
        if (sessionProjectVO == null) {
            return ResultEntity.failed(CrowdConstant.MSG_TEMP_PROJECT_MISSING);
        }

        // Step1、没上传描述图片
        if (StringUtils.isEmpty(returnVO.getDescribPicPath())) {
            return ResultEntity.failed(CrowdConstant.MSG_RETURN_PICTURE_EMPTY);
        }

        // Step2、设置回报信息
        List<ReturnVO> returnVOList = sessionProjectVO.getReturnVOList();
        if (CollectionUtils.isEmpty(returnVOList)) {
            returnVOList = new ArrayList<>();
            sessionProjectVO.setReturnVOList(returnVOList);
        }
        returnVOList.add(returnVO);

        // Step3、放回Session域
        session.setAttribute(CrowdConstant.ATTR_NAME_TEMP_PROJECT, sessionProjectVO);

        // Step4、清除错误信息并返回成功消息
        session.removeAttribute(CrowdConstant.ATTR_NAME_MESSAGE);
        return ResultEntity.successWithoutData();
    }

    /**
     * 创建回报描述图片
     *
     * @param returnPicture returnPicture
     * @return {@link ResultEntity}<{@link String}>
     */
    @ResponseBody
    @RequestMapping("/create/upload/return/picture.json")
    public ResultEntity<String> saveReturnPicture(@RequestParam("returnPicture") MultipartFile returnPicture) {
        if (returnPicture.isEmpty()) {
            return ResultEntity.failed(CrowdConstant.MSG_RETURN_PICTURE_EMPTY);
        }
        return uploadFile(returnPicture);
    }

    /**
     * 创建项目即发起人信息
     *
     * @param projectVO
     * @param headerPicture
     * @param detailPictureList
     * @param session
     * @return {@link String}
     */
    @RequestMapping("/create/project/information")
    public String saveProjectBasicInfo(ProjectVO projectVO, MultipartFile headerPicture, List<MultipartFile> detailPictureList, HttpSession session) {
        // Step0、取出Session域中ProjectVO对象
        ProjectVO sessionProjectVO = (ProjectVO) session.getAttribute(CrowdConstant.ATTR_NAME_TEMP_PROJECT);
        if (sessionProjectVO == null) {
            sessionProjectVO = new ProjectVO();
        }

        // Step1、上传头图
        // 当前没上传
        if (headerPicture.isEmpty()) {
            // 之前也没上传
            if (StringUtils.isEmpty(sessionProjectVO.getHeaderPicturePath())) {
                saveProjectVO(projectVO, sessionProjectVO, session);
                session.setAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_HEADER_PICTURE_EMPTY);
                return CrowdConstant.PROJECT_LAUNCH;
            }
        }
        // 当前上传了
        else {
            // 存下OSS
            final ResultEntity<String> headerPictureResultEntity = uploadFile(headerPicture);
            if (ResultEntity.FAILED.equals(headerPictureResultEntity.getResult())) {
                saveProjectVO(projectVO, sessionProjectVO, session);
                session.setAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_HEADER_PICTURE_UPLOAD_FAILED);
                return CrowdConstant.PROJECT_LAUNCH;
            }
            final String headerPicturePath = headerPictureResultEntity.getData();
            sessionProjectVO.setHeaderPicturePath(headerPicturePath);
        }

        // Step2、上传详情图片
        // 当前没上传
        if (CollectionUtils.isEmpty(detailPictureList) || (detailPictureList.size() == 1 && detailPictureList.get(0).isEmpty())) {
            // 之前也没上传
            if (CollectionUtils.isEmpty(sessionProjectVO.getDetailPicturePathList())) {
                saveProjectVO(projectVO, sessionProjectVO, session);
                session.setAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_DETAIL_PICTURE_EMPTY);
                return CrowdConstant.PROJECT_LAUNCH;
            }
        }
        // 当前上传了
        else {
            // 存下OSS
            List<String> detailPicturePathList = new ArrayList<>();
            for (MultipartFile detailPicture : detailPictureList) {
                if (detailPicture.isEmpty()) {
                    saveProjectVO(projectVO, sessionProjectVO, session);
                    session.setAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_DETAIL_PICTURE_EMPTY);
                    return CrowdConstant.PROJECT_LAUNCH;
                }
                final ResultEntity<String> detailPictureResultEntity = uploadFile(detailPicture);
                if (ResultEntity.FAILED.equals(detailPictureResultEntity.getResult())) {
                    saveProjectVO(projectVO, sessionProjectVO, session);
                    session.setAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_DETAIL_PICTURE_UPLOAD_FAILED);
                    return CrowdConstant.PROJECT_LAUNCH;
                }
                final String detailPicturePath = detailPictureResultEntity.getData();
                detailPicturePathList.add(detailPicturePath);
            }
            sessionProjectVO.setDetailPicturePathList(detailPicturePathList);
        }

        // Step3、projectVO 存入 session 域
        saveProjectVO(projectVO, sessionProjectVO, session);

        // Step4、清除错误信息并跳转
        session.removeAttribute(CrowdConstant.ATTR_NAME_MESSAGE);
        return CrowdConstant.REDIRECT_HTTP_LOCALHOST + "/project/return/info/page";
    }

    private void saveProjectVO(ProjectVO projectVO, ProjectVO sessionProjectVO, HttpSession session) {
        sessionProjectVO.setTypeIdList(projectVO.getTypeIdList());
        sessionProjectVO.setTagIdList(projectVO.getTagIdList());
        sessionProjectVO.setProjectName(projectVO.getProjectName());
        sessionProjectVO.setProjectDescription(projectVO.getProjectDescription());
        sessionProjectVO.setMoney(projectVO.getMoney());
        sessionProjectVO.setDay(projectVO.getDay());
        sessionProjectVO.setCreatedate(projectVO.getCreatedate());
        sessionProjectVO.setMemberLaunchInfoVO(projectVO.getMemberLaunchInfoVO());
        session.setAttribute(CrowdConstant.ATTR_NAME_TEMP_PROJECT, sessionProjectVO);
    }

    /**
     * 上传multipart文件
     *
     * @param multipartFile multipart文件
     * @return {@link ResultEntity}<{@link String}>
     */
    private ResultEntity<String> uploadFile(MultipartFile multipartFile) {
        final String endpoint = ossProperties.getEndpoint();
        final String accessKeyId = ossProperties.getAccessKeyId();
        final String accessKeySecret = ossProperties.getAccessKeySecret();
        final String securityToken = ossProperties.getSecurityToken();
        final String bucketName = ossProperties.getBucketName();
        final String bucketDomain = ossProperties.getBucketDomain();
        try {
            final String fileName = Objects.requireNonNull(multipartFile.getOriginalFilename());
            final InputStream content = multipartFile.getInputStream();
            return CrowdOSSUtil.uploadFile(endpoint, accessKeyId, accessKeySecret, securityToken, bucketName, bucketDomain, fileName, content);
        }
        catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return ResultEntity.failed(e.getMessage());
        }
    }
}
