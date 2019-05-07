package org.tang.jsj.biz.conumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tang.jsj.biz.dto.UserDTO;
import org.tang.jsj.biz.provider.service.HelloService;
import org.tang.jsj.biz.provider.service.UserService;

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

    @GetMapping("/sayHello")
    public String sayHello(@RequestParam(defaultValue = "tang") String name) {
        log.info("i'm ready to call someone......");
        return helloService.sayHello(name);
    }


    @GetMapping("/queryUser")
    public UserDTO queryUser(@RequestParam(defaultValue = "1") String id) {
        log.info("query......");
        return userService.selectUserOne(id);
    }

    @GetMapping("/queryUserFromSlave")
    public UserDTO queryUserFromSlave(@RequestParam(defaultValue = "1") String id) {
        log.info("query......");
        return userService.selectUserOneFromSlave(id);
    }

//    @GetMapping("/addUser")
//    public void addUser(@RequestParam UserDTO user) {
//        log.info("add......");
//        userService.addUser(user);
//    }
}
