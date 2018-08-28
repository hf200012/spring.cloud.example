package spring.cloud.exapmle.testservice.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.cloud.exapmle.testservice.dao.UserMapper;
import spring.cloud.exapmle.testservice.model.User;

import java.util.List;

@Controller
@RequestMapping("/rest/user")
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/querybyid")
    @ResponseBody
    User queryById(int id) {
        return userMapper.queryById(id);
    }

    @RequestMapping("/queryall")
    @ResponseBody
    List<User> queryAll() {
        return userMapper.queryAll();
    }

    @RequestMapping("/add")
    @ResponseBody
    String add(User user) {
        return userMapper.add(user) == 1 ? "success" : "failed";
    }

    @RequestMapping("/updatebyid")
    @ResponseBody
    String updateById(User user) {
        return userMapper.updateById(user) == 1 ? "success" : "failed";
    }

    @RequestMapping("/delbyid")
    @ResponseBody
    String delById(int id) {
        return userMapper.delById(id) == 1 ? "success" : "failed";
    }
}
