package com.vectorx.crowdfunding.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.vectorx.crowdfunding.entity.constant.CrowdConstant;
import com.vectorx.crowdfunding.util.CrowdAssessPassUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CrowdAssessPassFilter extends ZuulFilter
{

    /**
     * to classify a filter by type. Standard types in Zuul are "pre" for pre-routing filtering,
     * "route" for routing to an origin, "post" for post-routing filters, "error" for error handling.
     * We also support a "static" type for static responses see  StaticResponseFilter.
     * Any filterType made be created or added and run by calling FilterProcessor.runFilters(type)
     *
     * @return A String representing that type
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filterOrder() must also be defined for a filter. Filters may have the same  filterOrder if precedence is not
     * important for a filter. filterOrders do not need to be sequential.
     *
     * @return the int order of a filter
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * a "true" return from this method means that the run() method should be invoked
     *
     * @return true if the run() method should be invoked. false will not invoke the run() method
     */
    @Override
    public boolean shouldFilter() {
        final String servletPath = RequestContext.getCurrentContext().getRequest().getServletPath();
        final boolean whetherAssessPass = CrowdAssessPassUtil.judgeWhetherAssessPass(servletPath);
        return !whetherAssessPass;
    }

    /**
     * if shouldFilter() is true, this method will be invoked. this method is the core method of a ZuulFilter
     *
     * @return Some arbitrary artifact may be returned. Current implementation ignores it.
     * @throws ZuulException if an error occurs during execution.
     */
    @Override
    public Object run() throws ZuulException {
        final RequestContext currentContext = RequestContext.getCurrentContext();
        final HttpSession session = currentContext.getRequest().getSession();
        final Object memberValue = session.getAttribute(CrowdConstant.ATTR_NAME_MEMBER);
        if (memberValue == null) {
            try {
                final HttpServletResponse response = currentContext.getResponse();
                session.setAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MSG_ACCESS_FORBIDDEN);
                response.sendRedirect(CrowdConstant.TO_LOGIN_PAGE);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
