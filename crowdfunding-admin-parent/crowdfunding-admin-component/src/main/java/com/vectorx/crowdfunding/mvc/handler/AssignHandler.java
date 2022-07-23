package com.vectorx.crowdfunding.mvc.handler;

import com.vectorx.crowdfunding.entity.Role;
import com.vectorx.crowdfunding.service.api.AdminService;
import com.vectorx.crowdfunding.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/assign")
public class AssignHandler
{
    @Autowired
    private RoleService roleService;

    // 跳转之assign-role页面
    @GetMapping("/to/assign/role/page.html")
    public String toAssignPage(@RequestParam("adminId") Integer adminId, ModelMap modelMap) {
        List<Role> unAssignRoleList = roleService.getUnAssignRoleList(adminId);
        List<Role> assignRoleList = roleService.getAssignRoleList(adminId);
        modelMap.addAttribute("unAssignRoleList", unAssignRoleList);
        modelMap.addAttribute("assignRoleList", assignRoleList);
        return "assign/assign-role";
    }

    // admin分配role
    @PostMapping("/do/assign/role.html")
    public String doAssignRole(@RequestParam("adminId") Integer adminId, @RequestParam("pageNum") Integer pageNum,
            @RequestParam("keyword") String keyword,
            @RequestParam(value = "roleIdList", required = false) List<Integer> roleIdList) {
        roleService.saveAdminRoleRelationship(adminId, roleIdList);
        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }
}
