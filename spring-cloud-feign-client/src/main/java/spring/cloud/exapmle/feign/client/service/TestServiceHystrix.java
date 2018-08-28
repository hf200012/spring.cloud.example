package spring.cloud.exapmle.feign.client.service;

import org.springframework.stereotype.Component;
import spring.cloud.exapmle.feign.client.models.Plus;
import spring.cloud.exapmle.feign.client.models.Result;

/**
 * TestServiceHystrix会作为TestService的断路处理实现
 */
@Component
public class TestServiceHystrix implements TestService {
    @Override
    public String indexService() {
        return "{\"code\": 999,\"message\": \"服务断路\"}";
    }

    @Override
    public Result plusService(int numA, int numB) {
        Result result = new Result();
        result.setCode(999);
        result.setMessage("服务断路");
        return new Result();
    }

    @Override
    public Result plusabService(Plus plus) {
        Result result = new Result();
        result.setCode(999);
        result.setMessage("服务断路");
        return new Result();
    }

    @Override
    public Result plus2Service(Plus plus) {
        Result result = new Result();
        result.setCode(999);
        result.setMessage("服务断路");
        return new Result();
    }
}
