package com.vectorx.crowdfunding.mvc.handler;

import com.vectorx.crowdfunding.entity.Auth;
import com.vectorx.crowdfunding.entity.Role;
import com.vectorx.crowdfunding.service.api.AuthService;
import com.vectorx.crowdfunding.service.api.RoleService;
import com.vectorx.crowdfunding.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/assign")
public class AssignHandler
{
    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    // ============跳转至assign-role页面============
    @GetMapping("/to/assign/role/page.html")
    public String toAssignPage(@RequestParam("adminId") Integer adminId, ModelMap modelMap) {
        List<Role> unAssignRoleList = roleService.getUnAssignRoleList(adminId);
        List<Role> assignRoleList = roleService.getAssignRoleList(adminId);
        modelMap.addAttribute("unAssignRoleList", unAssignRoleList);
        modelMap.addAttribute("assignRoleList", assignRoleList);
        return "admin/assign-role";
    }

    // ============admin分配role============
    @PostMapping("/do/assign/role.html")
    public String doAssignRole(@RequestParam("adminId") Integer adminId, @RequestParam("pageNum") Integer pageNum,
            @RequestParam("keyword") String keyword,
            @RequestParam(value = "roleIdList", required = false) List<Integer> roleIdList) {
        roleService.saveAdminRoleRelationship(adminId, roleIdList);
        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }

    // ============role分配auth============
    @ResponseBody
    @PostMapping("/get/all/auth.json")
    public ResultEntity<List<Auth>> getAllAuth() {
        List<Auth> authList = authService.getAllAuth();
        return ResultEntity.success(authList);
    }

    @ResponseBody
    @PostMapping("get/auth/id/by/role/id.json")
    public ResultEntity<List<Integer>> getAuthIdByRoleId(@RequestParam("roleId") Integer roleId) {
        List<Integer> authIdList = authService.getAuthIdByRoleId(roleId);
        return ResultEntity.success(authIdList);
    }

    @ResponseBody
    @PostMapping("do/assign/auth.json")
    public ResultEntity<Object> doAssignAuth(@RequestBody Map<String, List<Integer>> map) {
        Integer roleId = map.get("roleId").get(0);
        List<Integer> authIdList = map.get("authIdList");
        authService.saveAuthIdByRoleId(roleId, authIdList);
        return ResultEntity.success();
    }
}
