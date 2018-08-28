package spring.cloud.exapmle.ribbon.client.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 增加注解 @HystrixCommand并通过fallbackMethod参数指定断路后执行的方法
     * @return
     */
    @HystrixCommand(fallbackMethod = "indexError")
    public Object index() {
        return restTemplate.getForObject("http://testservice", String.class);
    }

    public Object plus(int numA, int numB) {
        String url = String.format("http://testservice/plus?numA=%s&numB=%s", numA, numB);
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * 定义断路处理方法，返回服务/操作断路后的提示
     * @return
     */
    public Object indexError() {
        return "{\"code\": 999,\"message\": \"服务断路\"}";
    }
}
