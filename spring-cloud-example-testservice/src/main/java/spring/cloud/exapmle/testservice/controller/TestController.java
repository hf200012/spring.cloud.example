package spring.cloud.exapmle.testservice.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.exapmle.testservice.model.Plus;
import spring.cloud.exapmle.testservice.model.Result;

@RestController
public class TestController {

    @Value("${spring.application.name}")
    private String serviceName;

    @Value("${app.ip}")
    private String address;

    @Value("${server.port}")
    private String port;

    @RequestMapping("/")
    public Object index() {
        Result result = new Result();
        result.setServiceName(serviceName);
        result.setHost(String.format("%s:%s", address, port));
        result.setMessage("hello");
        return result;
    }

    @RequestMapping("/plus")
    public Object plus(Plus plus) {
        Result result = new Result();
        result.setServiceName(serviceName);
        result.setHost(String.format("%s:%s", address, port));
        result.setMessage("success");
        result.setContent(plus.getNumA() + plus.getNumB());
        return result;
    }
}
