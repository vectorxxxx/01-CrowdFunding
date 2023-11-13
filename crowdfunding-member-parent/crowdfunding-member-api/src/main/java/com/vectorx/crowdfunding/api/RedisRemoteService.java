package com.vectorx.crowdfunding.api;

import com.vectorx.crowdfunding.entity.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.TimeUnit;

@FeignClient("vectorx-crowdfunding-redis")
public interface RedisRemoteService
{
    @RequestMapping("/set/redis/key/value/remote")
    ResultEntity<String> setRedisKeyValueRemote(
            @RequestParam("key")
                    String key,
            @RequestParam("value")
                    String value);

    @RequestMapping("/set/redis/key/value/with/time/remote")
    ResultEntity<String> setRedisKeyValueWithTimeRemote(
            @RequestParam("key")
                    String key,
            @RequestParam("value")
                    String value,
            @RequestParam("time")
                    int time,
            @RequestParam("timeUnit")
                    TimeUnit timeUnit);

    @RequestMapping("/get/redis/value/remote")
    ResultEntity<String> getRedisValueRemote(
            @RequestParam("key")
                    String key);

    @RequestMapping("/remove/redis/key/remote")
    ResultEntity<String> removeRedisKeyRemote(
            @RequestParam("key")
                    String key);
}
