package org.tang.jsj.biz.conumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.tang.jsj.biz.conumer.utils.ApiBaseAction;
import org.tang.jsj.biz.conumer.vo.MenuVo;
import org.tang.jsj.biz.conumer.vo.UserVo;
import org.tang.jsj.biz.dto.UserDTO;
import org.tang.jsj.biz.provider.service.HelloService;
import org.tang.jsj.biz.provider.service.UserService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static java.util.Arrays.asList;

/**
 * <p>
 * Hello服务API
 * </p>
 */
@RestController
@Slf4j
public class HelloController extends ApiBaseAction {
    @Reference(version = "1.0.0")
    private HelloService helloService;

    @Reference(version = "1.0.0")
    private UserService userService;

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


    @GetMapping("/api/getnewslist")
    public Object getNewLists() {
        List result = new ArrayList();
        Map m = new HashMap<>();
        m.put("id",1);
        m.put("title","hello");
        m.put("img","http://pic39.nipic.com/20140320/12795880_110914420143_2.jpg");
        m.put("add_time",LocalDate.now().toString()+" "+LocalTime.now().toString());
        m.put("click",11);

        Map m1 = new HashMap<>();
        m1.put("id",2);
        m1.put("title","world");
        m1.put("img","http://img.redocn.com/sheji/20141219/zhongguofengdaodeliyizhanbanzhijing_3744115.jpg");
        m1.put("add_time",LocalDate.now().toString()+" "+LocalTime.now().toString());
        m1.put("click",22);
        result.add(m);
        result.add(m1);

        return result;
    }


    @GetMapping("/api/getcomments/{id}")
    public Object getcomments(@PathVariable String id ,int pageindex) {
        List result = new ArrayList();
        Map m = new HashMap<>();
        m.put("id",1);
        m.put("title","hello");
        m.put("img","http://pic39.nipic.com/20140320/12795880_110914420143_2.jpg");
        m.put("add_time",LocalDate.now().toString()+" "+LocalTime.now().toString());
        m.put("content","what your problem");

        Map m1 = new HashMap<>();
        m1.put("id",2);
        m1.put("title","world");
        m1.put("img","http://img.redocn.com/sheji/20141219/zhongguofengdaodeliyizhanbanzhijing_3744115.jpg");
        m1.put("add_time",LocalDate.now().toString()+" "+LocalTime.now().toString());
        m1.put("content","美丽新世界");
        result.add(m);
        result.add(m1);

        return result;
    }




    @CrossOrigin(origins = {"http://localhost:3000", "null"})
    @GetMapping("/api/getnew/{id}")
    public Object getNewInfo(@PathVariable String id) {
        Map m = new HashMap<>();
        m.put("id",id);
        m.put("title","hello");
        m.put("img","http://pic39.nipic.com/20140320/12795880_110914420143_2.jpg");
        m.put("add_time",LocalDate.now().toString()+" "+LocalTime.now().toString());
        m.put("click",new Random().nextInt());
        m.put("content","今天的天气挺不错的，就是热的很哒哒哒哒哒哒多多多多多多多多多多多多多多多多多多");

        return m;
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

    @RequestMapping("/login")
    public Map login(String phone, String pwd) {
        log.info("login:phone={},pwd={}",phone,pwd);
        UserVo result = UserVo.builder().name("张三").phone(phone).sex("男").token(UUID.randomUUID().toString()).build();
        return toResponsSuccess(result);
    }


    @RequestMapping("/getMenu")
    public Map getMenu(String token) {
        log.info("getMenu token::",token);

        MenuVo menuVo1 = MenuVo.builder().code("notice").name("通知").iconUrl("notice").url("/notice").hasNewMsg(false).isShow(1).build();
        MenuVo menuVo2 = MenuVo.builder().code("wallet").name("钱包").iconUrl("wallet").url("/wallet").hasNewMsg(false).isShow(1).build();

        List<MenuVo> data =  new ArrayList<>(asList(menuVo1,menuVo2));
        return toResponsSuccess(data);
    }


//    @GetMapping("/addUser")
//    public void addUser(@RequestParam UserDTO user) {
//        log.info("add......");
//        userService.addUser(user);
//    }
}
