package com.vectorx.crowdfunding.test;

import com.vectorx.crowdfunding.entity.po.MemberPO;
import com.vectorx.crowdfunding.mapper.MemberPOMapper;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisTest
{
    private Logger logger = LoggerFactory.getLogger(MyBatisTest.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MemberPOMapper memberPOMapper;

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
        logger.debug("Connection: " + connection.toString());
    }
}
