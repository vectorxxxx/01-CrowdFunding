package com.vectorx.crowdfunding.mvc.handler;

import com.vectorx.crowdfunding.entity.Admin;
import com.vectorx.crowdfunding.entity.vo.ParamData;
import com.vectorx.crowdfunding.entity.vo.Student;
import com.vectorx.crowdfunding.service.api.AdminService;
import com.vectorx.crowdfunding.util.CrowdUtil;
import com.vectorx.crowdfunding.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TestHandler
{
    @Autowired
    private AdminService adminService;

    private static final Logger logger = LoggerFactory.getLogger(TestHandler.class);
    
    @RequestMapping("/test/ssm.html")
    public String testSSM(ModelMap modelMap, HttpServletRequest request) {
        logger.info("judgeAjax：" + CrowdUtil.judgeAjax(request));
        //StringBuilder a = null;
        //System.out.println(a.length());

        List<Admin> adminList = adminService.getAllAdmin();
        modelMap.addAttribute("adminList", adminList);
        int i = 10/0;
        return "target";
    }

    @ResponseBody
    @RequestMapping("/send/array/one.html")
    public String sendArray1(@RequestParam("array[]") List<String> arrayList) {
        logger.info(String.valueOf(arrayList));
        return "success";
    }

    @ResponseBody
    @RequestMapping("/send/array/two.html")
    public String sendArray2(ParamData paramData) {
        logger.info(String.valueOf(paramData.getArray()));
        return "success";
    }

    @ResponseBody
    @RequestMapping("/send/array/three.html")
    public String sendArray3(@RequestBody List<String> arrayList) {
        logger.info(String.valueOf(arrayList));
        return "success";
    }

    @ResponseBody
    @RequestMapping("/send/compose/subject.json")
    //@RequestMapping("/send/compose/subject.html")
    //@RequestMapping(value = "/send/compose/subject.html", produces = "text/html;charset=utf-8")
    public ResultEntity<Student> sendComposeSubject(@RequestBody Student student, HttpServletRequest request){
        logger.info("judgeAjax：" + CrowdUtil.judgeAjax(request));
        StringBuilder a = null;
        System.out.println(a.length());

        logger.info(student.toString());
        return ResultEntity.success(student);
    }
}
