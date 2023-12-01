package com.vectorx.crowdfunding.handler;

import com.vectorx.crowdfunding.entity.ResultEntity;
import com.vectorx.crowdfunding.entity.constant.CrowdConstant;
import com.vectorx.crowdfunding.entity.vo.ProjectVO;
import com.vectorx.crowdfunding.entity.vo.ReturnVO;
import com.vectorx.crowdfunding.properties.OSSProperties;
import com.vectorx.crowdfunding.util.CrowdOSSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /**
     * 创建回报设置信息
     *
     * @param returnVO
     * @param session
     * @return {@link ResultEntity}<{@link String}>
     */
    @RequestMapping("/project/create/return/info.json")
    public ResultEntity<String> saveReturnInfo(ReturnVO returnVO, HttpSession session) {
        if (StringUtils.isEmpty(returnVO.getDescribPicPath())) {
            return ResultEntity.failed(CrowdConstant.MSG_RETURN_PICTURE_EMPTY);
        }
        session.setAttribute(CrowdConstant.ATTR_NAME_TEMP_RETURN, returnVO);
        return ResultEntity.successWithData(CrowdConstant.MSG_RETURN_PICTURE_UPLOAD_SUCCESS);
    }

    /**
     * 创建回报描述图片
     *
     * @param returnVO
     * @param returnPicture
     * @param session
     * @return {@link ResultEntity}<{@link String}>
     */
    @RequestMapping("/create/upload/return/picture.json")
    public ResultEntity<String> saveReturnPicture(ReturnVO returnVO, MultipartFile returnPicture, HttpSession session) {
        if (returnPicture.isEmpty()) {
            return ResultEntity.failed(CrowdConstant.MSG_RETURN_PICTURE_EMPTY);
        }
        final ResultEntity<String> returnPictureResultEntity = uploadFile(returnPicture);
        if (ResultEntity.FAILED.equals(returnPictureResultEntity.getResult())) {
            return ResultEntity.failed(CrowdConstant.MSG_RETURN_PICTURE_UPLOAD_FAILED);
        }
        final String describPicPath = returnPictureResultEntity.getData();
        returnVO.setDescribPicPath(describPicPath);
        session.setAttribute(CrowdConstant.ATTR_NAME_TEMP_RETURN, returnVO);
        return ResultEntity.successWithData(CrowdConstant.MSG_RETURN_PICTURE_UPLOAD_SUCCESS);
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
        // Step1、上传头图
        if (headerPicture.isEmpty()) {
            session.setAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_HEADER_PICTURE_EMPTY);
            return CrowdConstant.PROJECT_LAUNCH;
        }
        final ResultEntity<String> headerPictureResultEntity = uploadFile(headerPicture);
        if (ResultEntity.FAILED.equals(headerPictureResultEntity.getResult())) {
            session.setAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_HEADER_PICTURE_UPLOAD_FAILED);
            return CrowdConstant.PROJECT_LAUNCH;
        }
        final String headerPicturePath = headerPictureResultEntity.getData();
        projectVO.setHeaderPicturePath(headerPicturePath);

        // Step2、上传详情图片
        if (CollectionUtils.isEmpty(detailPictureList)) {
            session.setAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_DETAIL_PICTURE_EMPTY);
            return CrowdConstant.PROJECT_LAUNCH;
        }
        List<String> detailPicturePathList = new ArrayList<>();
        for (MultipartFile detailPicture : detailPictureList) {
            if (detailPicture.isEmpty()) {
                session.setAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_DETAIL_PICTURE_EMPTY);
                return CrowdConstant.PROJECT_LAUNCH;
            }
            final ResultEntity<String> detailPictureResultEntity = uploadFile(detailPicture);
            if (ResultEntity.FAILED.equals(detailPictureResultEntity.getResult())) {
                session.setAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_DETAIL_PICTURE_UPLOAD_FAILED);
                return CrowdConstant.PROJECT_LAUNCH;
            }
            final String detailPicturePath = detailPictureResultEntity.getData();
            detailPicturePathList.add(detailPicturePath);
        }
        projectVO.setDetailPicturePathList(detailPicturePathList);

        // Step3、projectVO 存入 session 域
        session.setAttribute(CrowdConstant.ATTR_NAME_TEMP_PROJECT, projectVO);
        return CrowdConstant.REDIRECT_HTTP_LOCALHOST + "/project/return/info/page";
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
