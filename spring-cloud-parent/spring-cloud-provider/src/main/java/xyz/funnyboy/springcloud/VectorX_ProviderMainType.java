package xyz.funnyboy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class VectorX_ProviderMainType
{
    public static void main(String[] args) {
        SpringApplication.run(VectorX_ProviderMainType.class, args);
    }
}
