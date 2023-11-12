package xyz.funnyboy.springcloud.factory;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import xyz.funnyboy.springcloud.api.VectorX_EmployeeRemoteService;
import xyz.funnyboy.springcloud.entity.Employee;
import xyz.funnyboy.springcloud.entity.ResultEntity;

@Component
public class MyFallBackFactory implements FallbackFactory<VectorX_EmployeeRemoteService>
{
    @Override
    public VectorX_EmployeeRemoteService create(Throwable throwable) {
        return new VectorX_EmployeeRemoteService()
        {

            @Override
            public ResultEntity<Employee> getEmployeeRemote(String signal) throws InterruptedException {
                return ResultEntity.failed("降级机制启动：" + throwable.getMessage());
            }

            @Override
            public Employee getEmployeeRemote() {
                return null;
            }
        };
    }
}
