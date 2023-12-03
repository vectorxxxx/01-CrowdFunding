package com.vectorx.crowdfunding.handler;

import com.vectorx.crowdfunding.api.MySQLRemoteService;
import com.vectorx.crowdfunding.entity.ResultEntity;
import com.vectorx.crowdfunding.entity.constant.CrowdConstant;
import com.vectorx.crowdfunding.entity.vo.PortalTypeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// 开启feign客户端功能，才能实现远程方法的声明式调用
@Controller
public class PortalHandler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(PortalHandler.class);

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    @RequestMapping("/")
    public String showPortalPage(ModelMap modelMap) {
        // 加载数据
        final ResultEntity<List<PortalTypeVO>> portalTypeProjectDataResultEntity = mySQLRemoteService.getPortalTypeProjectDataRemote();
        if (ResultEntity.SUCCESS.equals(portalTypeProjectDataResultEntity.getResult())) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_PORTAL_DATA, portalTypeProjectDataResultEntity.getData());
        }
        return "portal";
    }
}
