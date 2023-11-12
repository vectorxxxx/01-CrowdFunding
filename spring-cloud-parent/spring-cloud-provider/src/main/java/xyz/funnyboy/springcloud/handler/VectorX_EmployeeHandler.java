package xyz.funnyboy.springcloud.handler;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.funnyboy.springcloud.entity.Employee;
import xyz.funnyboy.springcloud.entity.ResultEntity;

@RestController
public class VectorX_EmployeeHandler
{
    @HystrixCommand(fallbackMethod = "getEmployeeRemoteBackup")
    @RequestMapping("/provider/circul/breaker/get/emp")
    public ResultEntity<Employee> getEmployeeRemote(@RequestParam("signal") String signal) throws InterruptedException {
        if ("ping".equals(signal)) {
            throw new RuntimeException();
        }
        if ("pong".equals(signal)) {
            Thread.sleep(5000);
        }
        return ResultEntity.successWithData(new Employee(555, signal, 555.55));
    }

    public ResultEntity<Employee> getEmployeeRemoteBackup(@RequestParam("signal") String signal) {
        return ResultEntity.failed("circuit break workded,with signal=" + signal);
    }

    @RequestMapping("/provider/get/employee")
    public Employee getEmployeeRemote() {
        return new Employee(555, "tom555", 555.55);
    }

    // @RequestMapping("/provider/get/employee")
    // public Employee getEmployeeRemote(HttpServletRequest request) {
    //     final int serverPort = request.getServerPort();
    //     return new Employee(555, "tom555-" + serverPort, 555.55);
    // }
}
