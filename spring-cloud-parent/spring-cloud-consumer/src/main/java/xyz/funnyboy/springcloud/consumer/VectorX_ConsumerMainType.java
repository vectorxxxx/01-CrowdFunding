package xyz.funnyboy.springcloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class VectorX_ConsumerMainType
{
    public static void main(String[] args) {
        SpringApplication.run(VectorX_ConsumerMainType.class, args);
    }
}
