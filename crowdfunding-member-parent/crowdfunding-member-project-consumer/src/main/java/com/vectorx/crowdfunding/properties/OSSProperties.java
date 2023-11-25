package com.vectorx.crowdfunding.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OSSProperties
{
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String securityToken;
    private String bucketName;
    private String bucketDomain;
}
