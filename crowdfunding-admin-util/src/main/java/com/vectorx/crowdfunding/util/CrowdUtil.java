package com.vectorx.crowdfunding.util;

import com.vectorx.crowdfunding.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CrowdUtil
{
    /**
     * md5加密
     * @author vectorx
     * @param source
     * @throws
     * @return java.lang.String
     */
    public static String md5(String source){
        if(source == null || source.length() == 0){
            throw new RuntimeException(CrowdConstant.MSG_STRING_INVALIDATE);
        }
        final String algorithm = "md5";
        final int radix = 16;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] input = source.getBytes(StandardCharsets.UTF_8);
            byte[] output = md.digest(input);
            BigInteger bigInteger = new BigInteger(1, output);
            return bigInteger.toString(radix).toUpperCase();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断是否是Ajax请求
     * @author vectorx
     * @param request
     * @throws
     * @return java.lang.Boolean
     */
    public static Boolean judgeAjax(HttpServletRequest request){
        String accept = request.getHeader("Accept");
        String xRequestedWith = request.getHeader("X-Requested-With");
        return (accept != null && accept.contains("application/json")) ||
                (xRequestedWith != null && xRequestedWith.equals("XMLHttpRequest"));
    }
}
