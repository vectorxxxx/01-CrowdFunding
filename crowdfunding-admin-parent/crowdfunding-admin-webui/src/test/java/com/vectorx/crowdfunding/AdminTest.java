package com.vectorx.crowdfunding;

import com.vectorx.crowdfunding.entity.Admin;
import com.vectorx.crowdfunding.mapper.AdminMapper;
import com.vectorx.crowdfunding.service.api.AdminService;
import com.vectorx.crowdfunding.util.CrowdUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class AdminTest
{
    private Logger logger = LoggerFactory.getLogger(AdminTest.class);

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    /**
     * 测试添加数据
     *
     * @param
     * @return void
     * @throws
     * @author vectorx
     */
    @Test
    public void testInsertAdmin() {
        Admin admin = new Admin();
        admin.setLoginAcct("vector2");
        admin.setUserName("VectorX2");
        admin.setUserPswd("123456");
        admin.setEmail("vectorx@qq.com");
        admin.setCreateTime(null);
        int insert = adminMapper.insert(admin);
        System.out.println(insert);
    }

    @Test
    public void testSaveAdmin() {
        Admin admin = new Admin();
        admin.setLoginAcct("admin");
        admin.setUserName("Admin");
        admin.setUserPswd("123456");
        admin.setEmail("admin@qq.com");
        admin.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        adminService.saveAdmin(admin);
    }

    @Test
    public void testBatchInsert() {
        String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String pwd = CrowdUtil.md5("123456");
        for (int i = 0; i < 250; i++) {
            Admin admin = new Admin();
            admin.setLoginAcct("vector" + i);
            admin.setUserName("Vector" + i);
            admin.setUserPswd(pwd);
            admin.setEmail("vectorx" + i + "@qq.com");
            admin.setCreateTime(createTime);
            adminMapper.insert(admin);
        }
    }
}
