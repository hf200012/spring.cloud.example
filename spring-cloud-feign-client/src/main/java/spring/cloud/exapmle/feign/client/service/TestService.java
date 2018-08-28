package spring.cloud.exapmle.feign.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import spring.cloud.exapmle.feign.client.models.Plus;
import spring.cloud.exapmle.feign.client.models.Result;

/**
 * 指定fallback类
 */
@FeignClient(value = "testservice",fallback = TestServiceHystrix.class)
public interface TestService {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String indexService();

    @RequestMapping(value = "/plus", method = RequestMethod.GET)
    Result plusService(@RequestParam(name = "numA") int numA, @RequestParam(name = "numB") int numB);

    @RequestMapping(value = "/plus", method = RequestMethod.POST, consumes = "application/json")
    Result plusabService(Plus plus);

    @RequestMapping(value = "/plus2", method = RequestMethod.POST)
    Result plus2Service(@RequestBody Plus plus);


}