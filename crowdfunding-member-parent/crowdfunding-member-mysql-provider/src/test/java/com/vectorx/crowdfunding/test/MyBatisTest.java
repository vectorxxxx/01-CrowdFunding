package com.vectorx.crowdfunding.test;

import com.google.gson.Gson;
import com.vectorx.crowdfunding.entity.po.MemberPO;
import com.vectorx.crowdfunding.entity.vo.DetailProjectVO;
import com.vectorx.crowdfunding.entity.vo.ProjectPaginationVO;
import com.vectorx.crowdfunding.mapper.MemberPOMapper;
import com.vectorx.crowdfunding.mapper.ProjectPOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisTest
{
    private Logger LOGGER = LoggerFactory.getLogger(MyBatisTest.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MemberPOMapper memberPOMapper;

    @Autowired
    private ProjectPOMapper projectPOMapper;

    @Test
    public void testSelectProjectPaginationVO() {
        final List<ProjectPaginationVO> projectPaginationVOList = projectPOMapper.selectProjectPaginationVO(0, 6, 2, 0, 0, "");
        LOGGER.info(new Gson().toJson(projectPaginationVOList));
    }

    @Test
    public void testSelectDetailProjectVO() {
        final DetailProjectVO detailProjectVO = projectPOMapper.selectDetailProjectVO(13);
        LOGGER.info(new Gson().toJson(detailProjectVO));
    }

    @Test
    public void testMapper() {
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String encode = encoder.encode("123123");
        MemberPO memberPO = new MemberPO(null, "jack", encode, " 杰 克 ", "jack@qq.com", 1, 1, "杰克", "123123", 2);
        memberPOMapper.insert(memberPO);
    }

    @Test
    public void testConnection() throws SQLException {
        final Connection connection = dataSource.getConnection();
        LOGGER.debug("Connection: " + connection.toString());
    }
}
