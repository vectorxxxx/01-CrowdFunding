package com.vectorx.crowdfunding.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.vectorx.crowdfunding.entity.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CrowdOSSUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CrowdOSSUtil.class);

    public static ResultEntity<String> uploadFile(String endpoint, String accessKeyId, String accessKeySecret, String securityToken, String bucketName, String bucketDomain,
                                                  File file) {
        final String folderPath = new SimpleDateFormat("yyyyMMdd").format(new Date());
        final String fileName = UUID.randomUUID().toString().replace("-", "");
        final String extensionName = file.getName().substring(file.getName().lastIndexOf("."));
        final String objectName = folderPath + "/" + fileName + extensionName;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, securityToken);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, file);

        try {
            final PutObjectResult result = ossClient.putObject(putObjectRequest);
            final ResponseMessage response = result.getResponse();
            if (response == null) {
                return ResultEntity.successWithData(bucketDomain + "/" + objectName);
            }
            else {
                return ResultEntity.failed(response.getStatusCode() + ": " + response.getErrorResponseAsString());
            }
        }
        catch (OSSException oe) {
            LOGGER.info("Caught an OSSException, which means your request made it to OSS, but was rejected with an error response for some reason.");
            LOGGER.info("Error Message:" + oe.getErrorMessage());
            LOGGER.info("Error Code:" + oe.getErrorCode());
            LOGGER.info("Request ID:" + oe.getRequestId());
            LOGGER.info("Host ID:" + oe.getHostId());
            return ResultEntity.failed(oe.getErrorCode() + ": " + oe.getErrorMessage());
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultEntity.failed(e.getMessage());
        }
        finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
