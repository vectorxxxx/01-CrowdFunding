package com.vectorx.crowdfunding.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest
{
    private Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedis() {
        final ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("foo", "bar");
    }

    @Test
    public void testRedis2() {
        final ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        final String value = operations.get("foo");
        logger.info(value);
    }
}
