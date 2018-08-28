package spring.cloud.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试示例
 *
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "123";
    }

    @RequestMapping("/users")
    public String getUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("当前登录的用户是 ： "+authentication.getPrincipal().toString());
        return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
                "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
    }
}
