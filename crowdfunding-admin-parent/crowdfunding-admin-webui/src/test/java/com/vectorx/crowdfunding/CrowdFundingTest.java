package com.vectorx.crowdfunding;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class CrowdFundingTest
{
    private Logger logger = LoggerFactory.getLogger(CrowdFundingTest.class);

    @Autowired
    private DataSource dataSource;

    /**
     * 测试数据源
     *
     * @param
     * @return void
     * @throws
     * @author vectorx
     */
    @Test
    public void testDataSource() throws SQLException {
        Connection connection = dataSource.getConnection();
        //System.out.println(connection);
        logger.trace(connection + "");
        logger.debug(connection + "");
        logger.info(connection + "");
        logger.warn(connection + "");
        logger.error(connection + "");
    }
}
