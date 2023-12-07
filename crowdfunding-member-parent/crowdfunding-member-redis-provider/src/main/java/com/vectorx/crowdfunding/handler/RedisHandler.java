package com.vectorx.crowdfunding.handler;

import com.vectorx.crowdfunding.entity.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class RedisHandler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisHandler.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/set/redis/key/value/remote")
    public ResultEntity<String> setRedisKeyValueRemote(
            @RequestParam("key")
                    String key,
            @RequestParam("value")
                    String value) {
        try {
            final ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            return ResultEntity.successWithoutData();
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/set/redis/key/value/with/time/remote")
    public ResultEntity<String> setRedisKeyValueWithTimeRemote(
            @RequestParam("key")
                    String key,
            @RequestParam("value")
                    String value,
            @RequestParam("time")
                    int time,
            @RequestParam("timeUnit")
                    TimeUnit timeUnit) {
        try {
            final ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, value, time, timeUnit);
            return ResultEntity.successWithoutData();
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/get/redis/value/remote")
    public ResultEntity<String> getRedisValueRemote(
            @RequestParam("key")
                    String key) {
        try {
            final ValueOperations<String, String> operations = redisTemplate.opsForValue();
            final String value = operations.get(key);
            return ResultEntity.successWithData(value);
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/remove/redis/key/remote")
    public ResultEntity<String> removeRedisKeyRemote(
            @RequestParam("key")
                    String key) {
        try {
            redisTemplate.delete(key);
            return ResultEntity.successWithoutData();
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultEntity.failed(e.getMessage());
        }
    }
}
