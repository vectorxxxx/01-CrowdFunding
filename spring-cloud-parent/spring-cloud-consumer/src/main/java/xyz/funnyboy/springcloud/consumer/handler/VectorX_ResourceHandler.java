package xyz.funnyboy.springcloud.consumer.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import xyz.funnyboy.springcloud.provider.Employee;

@RestController
public class VectorX_ResourceHandler
{
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/ribbon/get/employee")
    public Employee getEmployee() {
        // String host = "http://localhost:1000";
        String host = "http://vectorx-provider";
        String url = "/provider/get/employee";
        return restTemplate.getForObject(host + url, Employee.class);
    }
}
