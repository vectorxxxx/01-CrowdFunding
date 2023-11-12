package xyz.funnyboy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class VectorX_ConsumerFeignMainType
{
    public static void main(String[] args) {
        SpringApplication.run(VectorX_ConsumerFeignMainType.class, args);
    }
}
