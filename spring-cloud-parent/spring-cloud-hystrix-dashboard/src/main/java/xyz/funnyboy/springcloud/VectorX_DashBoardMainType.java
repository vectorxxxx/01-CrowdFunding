package xyz.funnyboy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringBootApplication
public class VectorX_DashBoardMainType
{
    public static void main(String[] args) {
        SpringApplication.run(VectorX_DashBoardMainType.class, args);
    }
}
