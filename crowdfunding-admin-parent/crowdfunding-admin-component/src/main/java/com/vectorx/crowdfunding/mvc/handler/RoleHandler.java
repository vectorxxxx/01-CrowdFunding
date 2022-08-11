package com.vectorx.crowdfunding.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.vectorx.crowdfunding.entity.Role;
import com.vectorx.crowdfunding.service.api.RoleService;
import com.vectorx.crowdfunding.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleHandler
{
    @Autowired
    private RoleService roleService;

    private static final Logger logger = LoggerFactory.getLogger(RoleHandler.class);

    // ========================查询相关========================
    @PreAuthorize("hasRole('部长')")
    @RequestMapping("/get/page/info.json")
    public ResultEntity<PageInfo<Role>> getPageInfo(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        try {
            PageInfo<Role> rolePageInfo = roleService.getPageInfo(pageNum, pageSize, keyword);
            return ResultEntity.success(rolePageInfo);
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResultEntity.failed("获取角色信息异常");
        }
    }

    // ========================新增相关========================
    @RequestMapping("/save.json")
    public ResultEntity<Object> saveRole(Role role) {
        try {
            roleService.saveRole(role);
            return ResultEntity.success();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResultEntity.failed("新增角色失败");
        }
    }

    // ========================修改相关========================
    @RequestMapping("/update.json")
    public ResultEntity<Object> updateRole(Role role) {
        try {
            roleService.updateRole(role);
            return ResultEntity.success();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResultEntity.failed("修改角色失败");
        }
    }

    // ========================删除相关========================
    @PreAuthorize("hasAuthority('role:delete')")
    @RequestMapping("/remove.json")
    //public ResultEntity<Object> removeRole(@RequestParam("ids") String roleIds) {
    public ResultEntity<Object> removeRole(@RequestBody List<Integer> roleIdList) {
        try {
            //List<Integer> roleIdList = new Gson().fromJson(roleIds, new TypeToken<List<Integer>>(){}.getType());
            roleService.removeRole(roleIdList);
            return ResultEntity.success();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResultEntity.failed("删除角色失败");
        }
    }
}
