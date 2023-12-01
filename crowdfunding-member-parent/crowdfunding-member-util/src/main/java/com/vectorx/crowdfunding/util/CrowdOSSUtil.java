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

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CrowdOSSUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CrowdOSSUtil.class);

    /**
     * 上传文件
     *
     * @param endpoint 断点
     * @param accessKeyId 访问 ID
     * @param accessKeySecret 访问密钥密钥
     * @param securityToken 安全令牌
     * @param bucketName bucket 名称
     * @param bucketDomain bucket 域
     * @param originalFileName 原始文件名
     * @param content 文件内容流
     * @return {@link ResultEntity}<{@link String}>
     */
    public static ResultEntity<String> uploadFile(String endpoint, String accessKeyId, String accessKeySecret, String securityToken, String bucketName, String bucketDomain,
                                                  String originalFileName, InputStream content) {
        final String folderPath = new SimpleDateFormat("yyyyMMdd").format(new Date());
        final String fileName = UUID.randomUUID().toString().replace("-", "");
        final String extensionName = originalFileName.substring(originalFileName.lastIndexOf("."));
        final String objectName = folderPath + "/" + fileName + extensionName;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, securityToken);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, content);

        try {
            final PutObjectResult result = ossClient.putObject(putObjectRequest);
            final ResponseMessage response = result.getResponse();
            if (response == null) {
                final String data = bucketDomain + "/" + objectName;
                LOGGER.info(data);
                return ResultEntity.successWithData(data);
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
