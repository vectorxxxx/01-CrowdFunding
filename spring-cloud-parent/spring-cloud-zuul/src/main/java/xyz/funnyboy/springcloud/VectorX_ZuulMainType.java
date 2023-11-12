package xyz.funnyboy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class VectorX_ZuulMainType
{
    public static void main(String[] args) {
        SpringApplication.run(VectorX_ZuulMainType.class, args);
    }
}
