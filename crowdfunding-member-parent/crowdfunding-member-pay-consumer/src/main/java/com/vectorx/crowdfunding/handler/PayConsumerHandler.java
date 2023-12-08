package com.vectorx.crowdfunding.handler;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.vectorx.crowdfunding.api.MySQLRemoteService;
import com.vectorx.crowdfunding.entity.ResultEntity;
import com.vectorx.crowdfunding.entity.constant.CrowdConstant;
import com.vectorx.crowdfunding.entity.vo.OrderProjectVO;
import com.vectorx.crowdfunding.entity.vo.OrderVO;
import com.vectorx.crowdfunding.properties.PayProperties;
import com.vectorx.crowdfunding.util.CrowdPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class PayConsumerHandler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(PayConsumerHandler.class);

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    @Autowired
    private PayProperties payProperties;

    @ResponseBody
    @RequestMapping("/notify")
    public String getNotifyMsg(HttpServletRequest request) throws AlipayApiException {
        // 获取配置信息
        final String charset = payProperties.charset;
        final String alipayPublicKey = payProperties.alipayPublicKey;
        final String signType = payProperties.signType;

        // 获取支付宝POST过来反馈信息
        Map<String, String> params = getParams(request);
        // 验签
        boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayPublicKey, charset, signType);
        if (signVerified) {
            // 商户订单号
            String outTradeNo = new String(request.getParameter("out_trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            // 支付宝交易号
            String tradeNo = new String(request.getParameter("trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            // 交易状态
            String tradeStatus = new String(request.getParameter("trade_status").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            LOGGER.info("success");
            return "outTradeNo: " + outTradeNo + "<br/>tradeNo: " + tradeNo + "<br/>tradeStatus: " + tradeStatus;
        }

        LOGGER.error("fail");
        return "";
    }

    @ResponseBody
    @RequestMapping("/return")
    public String getReturnMsg(HttpServletRequest request) throws AlipayApiException {
        // 获取配置信息
        final String charset = payProperties.charset;
        final String alipayPublicKey = payProperties.alipayPublicKey;
        final String signType = payProperties.signType;

        // 获取支付宝POST过来反馈信息
        Map<String, String> params = getParams(request);
        // 验签
        boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayPublicKey, charset, signType);
        if (signVerified) {
            // 商户订单号
            String outTradeNo = new String(request.getParameter("out_trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            // 支付宝交易号
            String tradeNo = new String(request.getParameter("trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            // 交易金额
            String totalAmount = new String(request.getParameter("total_amount").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            // 装填订单数据，保存到数据库中
            HttpSession session = request.getSession();
            saveOrder(tradeNo, session);

            return "outTradeNo: " + outTradeNo + "<br/>tradeNo: " + tradeNo + "<br/>totalAmount: " + totalAmount;
        }

        LOGGER.error("fail");
        return "";
    }

    private void saveOrder(String tradeNo, HttpSession session) {
        final Object attribute = session.getAttribute(CrowdConstant.ATTR_NAME_ORDER_VO);
        if (attribute == null) {
            LOGGER.info("订单数据为空，或已支付完成！");
            return;
        }
        final OrderVO orderVO = (OrderVO) attribute;
        orderVO.setPayOrderNum(tradeNo);
        ResultEntity<String> resultEntity = mySQLRemoteService.saveOrderVORemote(orderVO);
        if (ResultEntity.FAILED.equals(resultEntity.getResult())) {
            LOGGER.error(resultEntity.getMessage());
        }
        else {
            session.removeAttribute(CrowdConstant.ATTR_NAME_ORDER_VO);
        }
    }

    /**
     * 获取请求参数
     *
     * @param request 穷求
     * @return {@link Map}<{@link String}, {@link String}>
     */
    private Map<String, String> getParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            params.put(name, valueStr);
        }
        return params;
    }

    @ResponseBody
    @RequestMapping("/generate/order")
    public String generateOrder(OrderVO orderVO, HttpSession session) {
        // 获取项目回报信息
        final OrderProjectVO orderProjectVO = (OrderProjectVO) session.getAttribute(CrowdConstant.ATTR_NAME_ORDER_PROJECT_VO);
        final Integer supportUnitPrice = orderProjectVO.getSupportUnitPrice();
        final Integer returnCount = orderProjectVO.getReturnCount();
        final Integer deliveryCharge = orderProjectVO.getDeliveryCharge();

        // 商户订单号，商户网站订单系统中唯一订单号，必填
        final String outTradeNo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().replace("-", "");
        // 订单名称，必填
        final String subject = orderProjectVO.getProjectName();
        // 付款金额，必填
        final String totalAmount = String.valueOf(supportUnitPrice * returnCount + deliveryCharge);
        // 商品描述，可空
        String body = orderProjectVO.getReturnContent();

        // 存入 session 域中
        orderVO.setOrderNum(outTradeNo);
        orderVO.setOrderAmount(Long.valueOf(totalAmount));
        orderVO.setOrderProjectVO(orderProjectVO);
        session.setAttribute(CrowdConstant.ATTR_NAME_ORDER_VO, orderVO);

        // 获取配置信息
        final String gatewayUrl = payProperties.gatewayUrl;
        final String appId = payProperties.appId;
        final String merchantPrivateKey = payProperties.merchantPrivateKey;
        final String charset = payProperties.charset;
        final String alipayPublicKey = payProperties.alipayPublicKey;
        final String signType = payProperties.signType;
        final String returnUrl = payProperties.returnUrl;
        final String notifyUrl = payProperties.notifyUrl;

        // 进行支付请求
        try {
            return CrowdPayUtil.doAliPay(outTradeNo, subject, totalAmount, body, gatewayUrl, appId, merchantPrivateKey, charset, alipayPublicKey, signType, returnUrl, notifyUrl);
        }
        catch (AlipayApiException e) {
            LOGGER.error(e.getMessage(), e);
            return e.getMessage();
        }
    }

}
