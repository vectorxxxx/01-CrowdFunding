package com.vectorx.crowdfunding.config;

import com.vectorx.crowdfunding.entity.constant.CrowdConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer
{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(CrowdConstant.TO_REGISTER_PAGE).setViewName(CrowdConstant.MEMBER_REG);
        registry.addViewController(CrowdConstant.TO_LOGIN_PAGE).setViewName(CrowdConstant.MEMBER_LOGIN);
        registry.addViewController(CrowdConstant.TO_CENTER_PAGE).setViewName(CrowdConstant.MEMBER_CENTER);
    }
}
