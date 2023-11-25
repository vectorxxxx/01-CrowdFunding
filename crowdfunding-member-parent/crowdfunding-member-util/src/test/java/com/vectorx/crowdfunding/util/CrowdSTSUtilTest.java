// This file is auto-generated, don't edit it. Thanks.
package com.vectorx.crowdfunding.util;

import com.aliyun.sts20150401.models.AssumeRoleResponse;
import com.aliyun.sts20150401.models.AssumeRoleResponseBody;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CrowdSTSUtilTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CrowdSTSUtilTest.class);

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    private com.aliyun.sts20150401.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // Endpoint 请参考 https://api.aliyun.com/product/Sts
        config.endpoint = "sts.cn-shanghai.aliyuncs.com";
        return new com.aliyun.sts20150401.Client(config);
    }

    @Test
    public void generateCredentials() throws Exception {
        // 请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_ID 和 ALIBABA_CLOUD_ACCESS_KEY_SECRET。
        // 工程代码泄露可能会导致 AccessKey 泄露，并威胁账号下所有资源的安全性。以下代码示例使用环境变量获取 AccessKey 的方式进行调用，仅供参考，建议使用更安全的 STS 方式，更多鉴权访问方式请参见：https://help.aliyun.com/document_detail/378657.html
        com.aliyun.sts20150401.Client client = createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
        com.aliyun.sts20150401.models.AssumeRoleRequest assumeRoleRequest = new com.aliyun.sts20150401.models.AssumeRoleRequest().setRoleArn(
                "acs:ram::1837851101083631:role/crowdfunding-vectorx").setRoleSessionName("crowdfunding-vectorx");
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            final AssumeRoleResponse assumeRoleResponse = client.assumeRoleWithOptions(assumeRoleRequest, runtime);
            final AssumeRoleResponseBody.AssumeRoleResponseBodyCredentials credentials = assumeRoleResponse.getBody().getCredentials();

            LOGGER.info("==========");
            LOGGER.info(credentials.getAccessKeyId());
            LOGGER.info("==========");
            LOGGER.info(credentials.getAccessKeySecret());
            LOGGER.info("==========");
            LOGGER.info(credentials.getSecurityToken());
            LOGGER.info("==========");
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
