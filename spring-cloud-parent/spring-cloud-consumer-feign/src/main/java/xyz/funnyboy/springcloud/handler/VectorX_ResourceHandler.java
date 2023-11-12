package xyz.funnyboy.springcloud.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.funnyboy.springcloud.api.VectorX_EmployeeRemoteService;
import xyz.funnyboy.springcloud.entity.Employee;
import xyz.funnyboy.springcloud.entity.ResultEntity;

@RestController
public class VectorX_ResourceHandler
{
    @Autowired
    private VectorX_EmployeeRemoteService employeeRemoteService;

    @RequestMapping("/consumer/circul/breaker/get/emp")
    public ResultEntity<Employee> getEmployee(@RequestParam("signal") String signal) throws InterruptedException {
        return employeeRemoteService.getEmployeeRemote(signal);
    }

    @RequestMapping("/consumer/get/employee")
    public Employee getEmployee() {
        return employeeRemoteService.getEmployeeRemote();
    }
}
