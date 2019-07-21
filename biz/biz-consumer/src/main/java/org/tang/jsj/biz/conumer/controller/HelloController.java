package org.tang.jsj.biz.conumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tang.jsj.biz.dto.UserDTO;
import org.tang.jsj.biz.provider.service.HelloService;
import org.tang.jsj.biz.provider.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Hello服务API
 * </p>
 */
@RestController
@Slf4j
public class HelloController {
    @Reference(version = "1.0.0")
    private HelloService helloService;

    @Reference(version = "1.0.0")
    private UserService userService;

    @CrossOrigin(origins = {"http://localhost:3000", "null"})
    @GetMapping("/api/getlunbo")
    public Object getlunbo() {
       List result = new ArrayList();
        Map m = new HashMap<>();

        m.put("url","http://pic39.nipic.com/20140320/12795880_110914420143_2.jpg");
        m.put("img","http://pic39.nipic.com/20140320/12795880_110914420143_2.jpg");

        Map m1 = new HashMap<>();

        m1.put("url","http://img.redocn.com/sheji/20141219/zhongguofengdaodeliyizhanbanzhijing_3744115.jpg");
        m1.put("img","http://img.redocn.com/sheji/20141219/zhongguofengdaodeliyizhanbanzhijing_3744115.jpg");

        result.add(m);
        result.add(m1);

        return result;
    }

    @GetMapping("/sayHello")
    public String sayHello(@RequestParam(defaultValue = "tang") String name) {
        return helloService.sayHello(name);
    }


    @GetMapping("/queryUser")
    public UserDTO queryUser(@RequestParam(defaultValue = "1") String id, String orgId) {
        log.info("queryUser");
        return userService.selectUserOne(id,orgId);
    }



//    @GetMapping("/addUser")
//    public void addUser(@RequestParam UserDTO user) {
//        log.info("add......");
//        userService.addUser(user);
//    }
}
