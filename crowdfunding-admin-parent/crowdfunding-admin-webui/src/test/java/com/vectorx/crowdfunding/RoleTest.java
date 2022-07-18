package com.vectorx.crowdfunding;

import com.vectorx.crowdfunding.entity.Role;
import com.vectorx.crowdfunding.mapper.RoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class RoleTest
{
    private Logger logger = LoggerFactory.getLogger(CrowdFundingTest.class);

    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void testBatchInsert() {
        for (int i = 0; i < 250; i++) {
            roleMapper.insert(new Role(null, "role" + i));
        }
    }
}
