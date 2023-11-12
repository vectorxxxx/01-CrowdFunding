package xyz.funnyboy.springcloud.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.funnyboy.springcloud.entity.Employee;
import xyz.funnyboy.springcloud.entity.ResultEntity;
import xyz.funnyboy.springcloud.factory.MyFallBackFactory;

@FeignClient(name = "vectorx-provider",
             fallbackFactory = MyFallBackFactory.class)
public interface VectorX_EmployeeRemoteService
{
    @RequestMapping("/provider/circul/breaker/get/emp")
    ResultEntity<Employee> getEmployeeRemote(@RequestParam("signal") String signal) throws InterruptedException;

    @RequestMapping("/provider/get/employee")
    Employee getEmployeeRemote();
}
