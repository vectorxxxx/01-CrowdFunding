package com.vectorx.crowdfunding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.vectorx.crowdfunding.mapper")
@SpringBootApplication
public class CrowdMainClass
{
    public static void main(String[] args) {
        SpringApplication.run(CrowdMainClass.class, args);
    }
}
