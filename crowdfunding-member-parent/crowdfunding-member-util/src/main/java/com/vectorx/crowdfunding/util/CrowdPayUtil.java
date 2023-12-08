package com.vectorx.crowdfunding.util;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

public class CrowdPayUtil
{
    /**
     * 使用支付宝网关进行支付
     *
     * @param outTradeNo    商家订单号
     * @param subject       订单标题
     * @param totalAmount   订单总金额
     * @param body          订单描述
     * @param gatewayUrl    支付宝网关URL
     * @param appId         商户APP ID
     * @param merchantPrivateKey    商户私钥
     * @param charset       编码格式
     * @param alipayPublicKey    支付宝公钥
     * @param signType      签名类型
     * @param returnUrl     支付成功后返回的URL
     * @param notifyUrl     支付结果通知的URL
     * @return 支付结果信息
     * @throws AlipayApiException    支付宝接口调用异常
     */
    public static String doAliPay(String outTradeNo, String subject, String totalAmount, String body, String gatewayUrl, String appId, String merchantPrivateKey, String charset,
                                  String alipayPublicKey, String signType, String returnUrl, String notifyUrl) throws AlipayApiException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, appId, merchantPrivateKey, "json", charset, alipayPublicKey, signType);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(returnUrl);
        alipayRequest.setNotifyUrl(notifyUrl);

        //构建请求参数
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", outTradeNo);
        bizContent.put("total_amount", totalAmount);
        bizContent.put("subject", subject);
        bizContent.put("body", body);
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
        alipayRequest.setBizContent(bizContent.toJSONString());

        //请求
        return alipayClient.pageExecute(alipayRequest).getBody();
    }
}
