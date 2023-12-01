package com.vectorx.crowdfunding.util;

import com.aliyun.tea.utils.StringUtils;
import com.vectorx.crowdfunding.entity.constant.CrowdConstant;

import java.util.HashSet;
import java.util.Set;

public class CrowdAssessPassUtil
{
    private static final Set<String> PAGE_RESOURCE = new HashSet<>();

    static {
        PAGE_RESOURCE.add(CrowdConstant.TO_REGISTER_PAGE);
        PAGE_RESOURCE.add(CrowdConstant.TO_LOGIN_PAGE);
        PAGE_RESOURCE.add(CrowdConstant.DO_REGISTER);
        PAGE_RESOURCE.add(CrowdConstant.DO_LOGIN);
        PAGE_RESOURCE.add(CrowdConstant.DO_LOGIN_OUT);
        PAGE_RESOURCE.add(CrowdConstant.SEND_SHORT_MESSAGE);
    }

    private static final Set<String> STATIC_RESOURCE = new HashSet<>();

    static {
        STATIC_RESOURCE.add("/bootstrap");
        STATIC_RESOURCE.add("/css");
        STATIC_RESOURCE.add("/fonts");
        STATIC_RESOURCE.add("/img");
        STATIC_RESOURCE.add("/jquery");
        STATIC_RESOURCE.add("/layer");
        STATIC_RESOURCE.add("/script");
        STATIC_RESOURCE.add("/ztree");
    }

    /**
     * 判断请求是否需要过滤
     *
     * @param servletPath  请求路径
     * @return boolean
     */
    public static boolean judgeWhetherAssessPass(String servletPath) {
        if (StringUtils.isEmpty(servletPath) || "/".equals(servletPath)) {
            return true;
        }
        return isAssessPassResource(PAGE_RESOURCE, servletPath) || isAssessPassResource(STATIC_RESOURCE, servletPath);
    }

    /**
     * 判断请求是否为页面资源
     *
     * @param servletPath  请求路径
     * @return boolean
     */
    public static boolean judgeWhetherPageResource(String servletPath) {
        return isAssessPassResource(PAGE_RESOURCE, servletPath);
    }

    /**
     * 是否访问放行的资源
     *
     * @param assessPassResourceSet 允许访问放行的资源集合
     * @param requestResourcePath 请求资源路径
     * @return boolean
     */
    private static boolean isAssessPassResource(Set<String> assessPassResourceSet, String requestResourcePath) {
        boolean result = false;
        for (String path : assessPassResourceSet) {
            if (requestResourcePath.startsWith(path)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
