package com.vectorx.crowdfunding;

import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.vectorx.crowdfunding.entity.ResultEntity;

public class CrowdUtil
{
    public static ResultEntity<String> sendMessage(String url, String signName, String templateCode, String accessKeyId, String accessKeySecret, String phoneNum, String captcha)
            throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // Endpoint 请参考 https://api.aliyun.com/product/Dysmsapi
        config.endpoint = url;
        com.aliyun.dysmsapi20170525.Client client = new com.aliyun.dysmsapi20170525.Client(config);
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                // 短信签名名称
                .setSignName(signName)
                // 短信模板Code
                .setTemplateCode(templateCode)
                // 接收短信的手机号码。
                .setPhoneNumbers(phoneNum)
                // 短信模板变量对应的实际值。支持传入多个参数
                .setTemplateParam("{\"code\":\"" + captcha + "\"}");
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        com.aliyun.dysmsapi20170525.models.SendSmsResponse resp = client.sendSmsWithOptions(sendSmsRequest, runtime);
        com.aliyun.teaconsole.Client.log(com.aliyun.teautil.Common.toJSONString(resp));

        // 处理请求结果
        final SendSmsResponseBody responseBody = resp.getBody();
        if (resp.getStatusCode() != 200 || !"OK".equals(responseBody.getCode())) {
            return ResultEntity.failed(responseBody.getMessage());
        }
        final String code = responseBody.getCode();
        return ResultEntity.successWithData(code);
    }
}
