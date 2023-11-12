package xyz.funnyboy.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyZuulFilter extends ZuulFilter
{
    @Override
    public String filterType() {
        // "pre" for pre-routing filtering
        // "route" for routing to an origin
        // "post" for post-routing filters
        // "error" for error handling
        // "static" for static responses see StaticResponseFilter
        return "prefix";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        final RequestContext currentContext = RequestContext.getCurrentContext();
        final HttpServletRequest request = currentContext.getRequest();
        System.out.println(request.getServletPath());

        // true if the run() method should be invoked
        // false will not invoke the run() method
        if ("hello".equals(request.getParameter("signal"))) {
            return true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("run().......");
        return null;
    }
}
