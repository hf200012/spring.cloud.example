package spring.cloud.exapmle.feign.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.exapmle.feign.client.models.Plus;
import spring.cloud.exapmle.feign.client.service.TestService;
import spring.cloud.exapmle.feign.client.service.TestServiceZuul;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private TestServiceZuul testServiceZuul;

    @RequestMapping("/ti-zuul")
    public Object ti_zuul() {
        return testServiceZuul.indexService();
    }

    @RequestMapping("/")
    public Object index() {
        return "feign client";
    }

    @RequestMapping("/ti")
    public Object ti() {
        return testService.indexService();
    }

    @RequestMapping("/plus")
    public Object plus(@RequestParam("numa") int numA, @RequestParam("numb") int numB) {
        return testService.plusService(numA, numB);
    }

    @RequestMapping("/plusab")
    public Object plusA(@RequestParam("numa") int numA, @RequestParam("numb") int numB) {
        Plus plus = new Plus();
        plus.setNumA(numA);
        plus.setNumB(numB);
        return testService.plusabService(plus);
    }

    @RequestMapping("/plus2")
    public Object plus2(Plus plus) {
        return testService.plus2Service(plus);
    }


}
