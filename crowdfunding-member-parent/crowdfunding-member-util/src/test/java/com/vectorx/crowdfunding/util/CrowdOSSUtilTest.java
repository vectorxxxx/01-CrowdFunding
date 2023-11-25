package com.vectorx.crowdfunding.util;

import com.vectorx.crowdfunding.entity.ResultEntity;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
public class CrowdOSSUtilTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CrowdOSSUtil.class);

    @Test
    public void test() {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        final String endpoint = "https://oss-cn-shanghai.aliyuncs.com";
        // 从环境变量中获取步骤5生成的临时访问密钥AccessKey ID和AccessKey Secret，非阿里云账号AccessKey ID和AccessKey Secret。
        final String accessKeyId = "STS.NStHayK3goN2JruR4vXUiwa7m";
        final String accessKeySecret = "5Cpkkeby5w8QPs3rBq52hFqctJ7h9oVCbKoPeunv67f5";
        // 从环境变量中获取步骤5生成的安全令牌SecurityToken。
        final String securityToken = "CAIShwJ1q6Ft5B2yfSjIr5DBA9vNpuxG2IzZSFTEtjQjVNpFmKScjzz2IHhMfHNoAu4evvg2nW9R7fcYlr14UZhaSULZa8dx6JsS7w6qf43bq5Q0BijSIMT3d1KIAjvXgeXwAYygPv6/F96pb1fb7FwRpZLxaTSlWXG8LJSNkuQJR98LXw6+H1EkbZUsUWkEksIBMmbLPvuAKwPjhnGqbHBloQ1hk2hym8/dq4++kkOO1wKql7ZM/92pcsP6NJBWUc0hA4vv7otfbbHc1SNc0R9O" + "+ZptgbZMkTW95YnFWwMJuEveY7aNq4I2d14jfNQxAalboPbhk/F5vOOWiYn81xFXLfHS1NJH7BhpqxqAAXS0T12I9iXlkKx2mTDObXEspWILdPiGsAKITzKhnCV3w5Yuw48eQvWQHjHmc1Fgqf4KGjLvAJVUPsvl6I2NkQkYR7fKLlGe0zlgRNrs/EoppkkYFIbgcvRCi30zDCL8gz/HsHnSXuzX8OlNEZULFGuEVYZmzAU7I1Lt1ZyQ76tWIAA=";
        final String bucketName = "crowdfunding-vectorx";
        final String bucketDomain = "https://crowdfunding-vectorx.oss-cn-shanghai.aliyuncs.com";
        final File file = new File("C:\\Users\\uxiah\\Pictures\\1.png");
        final ResultEntity<String> resultEntity = CrowdOSSUtil.uploadFile(endpoint, accessKeyId, accessKeySecret, securityToken, bucketName, bucketDomain, file);
        if (ResultEntity.SUCCESS.equals(resultEntity.getResult())) {
            LOGGER.info(resultEntity.getData());
        }
        else {
            LOGGER.error(resultEntity.getMessage());
        }
    }
}
