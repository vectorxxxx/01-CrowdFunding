package com.vectorx.crowdfunding.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrowdWebMvcConfig implements WebMvcConfigurer
{
    /**
     * Configure simple automated controllers pre-configured with the response
     * status code and/or a view to render the response body. This is useful in
     * cases where there is no need for custom controller logic -- e.g. render a
     * home page, perform simple site URL redirects, return a 404 status with
     * HTML content, a 204 with no content, and more.
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 同意协议
        registry.addViewController("/agree/protocol/page").setViewName("project-agree");
        // 发起项目
        registry.addViewController("/launch/project/page").setViewName("project-launch");
        // 回报信息
        registry.addViewController("/return/info/page").setViewName("project-return");
        // 确认信息
        registry.addViewController("/create/confirm/page").setViewName("project-confirm");
        // 完成
        registry.addViewController("/create/success/page").setViewName("project-success");
    }
}
