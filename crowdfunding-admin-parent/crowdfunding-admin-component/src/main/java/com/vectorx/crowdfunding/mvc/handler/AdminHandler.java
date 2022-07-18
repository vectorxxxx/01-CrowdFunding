package com.vectorx.crowdfunding.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.vectorx.crowdfunding.constant.CrowdConstant;
import com.vectorx.crowdfunding.entity.Admin;
import com.vectorx.crowdfunding.exception.DeleteException;
import com.vectorx.crowdfunding.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminHandler
{
    @Autowired
    private AdminService adminService;

    // ========================登录相关========================
    @GetMapping("/to/login/page.html")
    public String login(HttpSession session) {
        Admin admin = (Admin) session.getAttribute(CrowdConstant.ATTR_NAME_ADMIN_LOGIN);
        if (admin != null) {
            return "redirect:/admin/to/main/page.html";
        }
        return "admin/admin-login";
    }

    @PostMapping("/do/login.html")
    public String login(@RequestParam(value = "loginAcct", required = false) String loginAcct,
            @RequestParam(value = "userPswd", required = false) String userPswd, HttpSession session) {
        Admin admin = (Admin) session.getAttribute(CrowdConstant.ATTR_NAME_ADMIN_LOGIN);
        if (admin != null) {
            return "redirect:/admin/to/main/page.html";
        }
        if (loginAcct == null || userPswd == null) {
            return "redirect:/admin/to/login/page.html";
        }

        admin = adminService.getAdminByLoginAcct(loginAcct, userPswd);
        session.setAttribute(CrowdConstant.ATTR_NAME_ADMIN_LOGIN, admin);
        return "redirect:/admin/to/main/page.html";
    }

    @GetMapping("/do/logout.html")
    public String logout(HttpSession session) {
        session.removeAttribute(CrowdConstant.ATTR_NAME_ADMIN_LOGIN);
        return "redirect:/admin/to/login/page.html";
    }

    // ========================查询相关========================
    @RequestMapping("/get/page.html")
    public String getPage(@RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, ModelMap modelMap) {
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);
        return "admin/admin-page";
    }

    // ========================删除相关========================
    @GetMapping("/remove/{adminId}.html")
    public String removeAdmin(@PathVariable("adminId") Integer adminId, @RequestParam("pageNum") Integer pageNum,
            @RequestParam("keyword") String keyword, HttpSession session) {
        Admin admin = (Admin) session.getAttribute(CrowdConstant.ATTR_NAME_ADMIN_LOGIN);
        if (adminId.equals(admin.getId())) {
            throw new DeleteException(CrowdConstant.MSG_DELETE_FORBIDDEN);
        }
        adminService.removeAdminById(adminId);
        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }

    // ========================新增相关========================
    @GetMapping("/add.html")
    public String addAdmin(Admin admin) {
        adminService.saveAdmin(admin);
        return "redirect:/admin/get/page.html?pageNum=" + Integer.MAX_VALUE;
    }

    // ========================修改相关========================
    @GetMapping("/to/edit/page/{adminId}.html")
    public String toEditPage(@PathVariable("adminId") Integer adminId, ModelMap modelMap) {
        Admin admin = adminService.getAdminById(adminId);
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_USER, admin);
        return "admin/admin-edit";
    }

    @PostMapping("/edit.html")
    public String editAdmin(Admin admin, @RequestParam("pageNum") Integer pageNum,
            @RequestParam("keyword") String keyword) {
        adminService.saveAdmin(admin);
        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }

}
