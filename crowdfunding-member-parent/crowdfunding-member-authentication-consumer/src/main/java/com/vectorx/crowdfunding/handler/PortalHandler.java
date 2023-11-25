package com.vectorx.crowdfunding.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 开启feign客户端功能，才能实现远程方法的声明式调用
@Controller
public class PortalHandler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(PortalHandler.class);

    @RequestMapping("/")
    public String showPortalPage() {
        return "portal";
    }
}
