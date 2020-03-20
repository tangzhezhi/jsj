package org.tang.jsj.biz.conumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.tang.jsj.biz.conumer.utils.ApiBaseAction;
import org.tang.jsj.biz.conumer.vo.*;
import org.tang.jsj.biz.dto.UserDTO;
import org.tang.jsj.biz.provider.service.HelloService;
import org.tang.jsj.biz.provider.service.UserService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

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

    @RequestMapping("/getUserInfo")
    public Map getUserInfo(String token) {
        log.info("token:token={}",token);
        UserVo result = UserVo.builder().name("张三").phone("1111111111").sex("男").token(token).build();
        return toResponsSuccess(result);
    }




    @RequestMapping("/getMenu")
    public Map getMenu(String token) {
        log.info("getMenu token={}",token);

        MenuVo menuVo1 = MenuVo.builder().code("notice").name("通知").iconUrl("notice").url("/notice").hasNewMsg(false).isShow(1).build();
        MenuVo menuVo2 = MenuVo.builder().code("wallet").name("钱包").iconUrl("wallet").url("/wallet").hasNewMsg(false).isShow(1).build();

        MenuVo menuVo3 = MenuVo.builder().code("safe").name("平安校园").iconUrl("campus").url("/safe").hasNewMsg(false).isShow(1).build();

        MenuVo menuVo4 = MenuVo.builder().code("wallet").name("家庭作业").iconUrl("homework").url("/homework").hasNewMsg(false).isShow(1).build();

        MenuVo menuVo5 = MenuVo.builder().code("wallet").name("请假").iconUrl("leave").url("/leave").hasNewMsg(false).isShow(1).build();

        MenuVo menuVo6 = MenuVo.builder().code("wallet").name("卡务").iconUrl("card").url("/card").hasNewMsg(false).isShow(1).build();



        List<MenuVo> data =  new ArrayList<>(asList(menuVo1,menuVo2,menuVo3,menuVo4,menuVo5,menuVo6));
        return toResponsSuccess(data);
    }

    @CrossOrigin(origins = {"*", "null"})
    @RequestMapping("/getNotice")
    public Map getNotice(String token) {
        log.info("getNotice token={}",token);
        NoticeVo vo1 = NoticeVo.builder().msgId("1").publisher("阿汤").publishTime("2020-01-21").thumbImg("").title("通知").summary("春节放假时间表").build();
        NoticeVo vo2 = NoticeVo.builder().msgId("2").publisher("汤汤").publishTime("2020-01-22").thumbImg("").title("请注意").summary("防火防盗注意用电安全是冬季适得府君书东风街是打发打发斯蒂芬斯蒂芬").build();
        NoticeVo vo3 = NoticeVo.builder().msgId("3").publisher("汤汤").publishTime("2020-01-22").thumbImg("").title("请注意1").summary("防火防盗注意用电安全是冬季适得府君书东风街沙发的沙发萨法地方撒发顺丰撒发顺丰撒").build();
        NoticeVo vo4 = NoticeVo.builder().msgId("4").publisher("汤汤").publishTime("2020-01-22").thumbImg("").title("请注意2").summary("防火防盗注意用电安全是冬季适得府君书东风街").build();
        NoticeVo vo5 = NoticeVo.builder().msgId("5").publisher("汤汤").publishTime("2020-01-22").thumbImg("").title("请注意3").summary("防火防盗注意用电安全是冬季适得府君书东风街").build();
        NoticeVo vo6 = NoticeVo.builder().msgId("6").publisher("汤汤").publishTime("2020-01-22").thumbImg("").title("请注意4").summary("防火防盗注意用电安全是冬季适得府君书东风街").build();
        NoticeVo vo7 = NoticeVo.builder().msgId("7").publisher("汤汤").publishTime("2020-01-22").thumbImg("").title("请注意5").summary("防火防盗注意用电安全是冬季适得府君书东风街").build();
        NoticeVo vo8 = NoticeVo.builder().msgId("8").publisher("汤汤").publishTime("2020-01-22").thumbImg("").title("请注意6").summary("防火防盗注意用电安全是冬季适得府君书东风街").build();
        NoticeVo vo9 = NoticeVo.builder().msgId("9").publisher("汤汤").publishTime("2020-01-22").thumbImg("").title("请注意7").summary("防火防盗注意用电安全是冬季适得府君书东风街").build();
        NoticeVo vo10 = NoticeVo.builder().msgId("10").publisher("汤汤").publishTime("2020-01-22").thumbImg("").title("请注意8").summary("防火防盗注意用电安全是冬季适得府君书东风街").build();
        NoticeVo vo11 = NoticeVo.builder().msgId("11").publisher("汤汤").publishTime("2020-01-22").thumbImg("").title("请注意9").summary("防火防盗注意用电安全是冬季适得府君书东风街").build();
        NoticeVo vo12 = NoticeVo.builder().msgId("12").publisher("汤汤").publishTime("2020-01-22").thumbImg("").title("请注意10").summary("防火防盗注意用电安全是冬季适得府君书东风街").build();
        NoticeVo vo13 = NoticeVo.builder().msgId("13").publisher("汤汤").publishTime("2020-01-22").thumbImg("").title("请注意11").summary("防火防盗注意用电安全是冬季适得府君书东风街").build();

        List<NoticeVo> data =  new ArrayList<>(asList(vo1,vo2,vo3,vo4,vo5,vo6));
        return toResponsSuccess(data);
    }

    @RequestMapping("/getNoticeDetail")
    public Map getNoticeDetail(String token,String id) {
        log.info("getNoticeDetail id={}",id);
        NoticeDetailVo vo1 = NoticeDetailVo.builder().msgId("1").title("通知").content("春节放假时间表水电费老师的机房里睡大觉法律上的机房里睡大觉").build();
        if(id.equals("2")){
            vo1.setContent("dsfsjdfsldjflsdjflsdjflsdjfskldfjlsjdflsdjfl");
        }
        return toResponsSuccess(vo1);
    }


    @RequestMapping("/delNotice")
    public Map delNotice(String token,String msgId) {
        log.info("delNotice msgId={}",msgId);
        return toResponsMsgSuccess("成功");
    }


    @RequestMapping("/contact/get")
    public Map getContact(String token) {
        log.info("getContact token={}");

        ContactVo c1 = ContactVo.builder().firstPinyin("A").departName("1班").name("阿克苏机场").sex("女").phone("18975451234").pinyin("AKeSu").build();
        ContactVo c11 = ContactVo.builder().firstPinyin("A").departName("1班").name("阿拉山口机场").sex("女").phone("18975451234").pinyin("ALaShanKou").build();
        ContactVo c12 = ContactVo.builder().firstPinyin("A").departName("1班").name("阿勒泰机场").sex("女").phone("18975451234").pinyin("ALeTai").build();
        ContactVo c13 = ContactVo.builder().firstPinyin("A").departName("1班").name("阿汤3").sex("女").phone("18975451234").pinyin("Atang3").build();
        ContactVo c14 = ContactVo.builder().firstPinyin("A").departName("1班").name("阿汤4").sex("女").phone("18975451234").pinyin("Atang4").build();
        ContactVo c15 = ContactVo.builder().firstPinyin("A").departName("1班").name("阿汤5").sex("女").phone("18975451234").pinyin("Atang5").build();
        ContactVo c16 = ContactVo.builder().firstPinyin("A").departName("1班").name("阿汤6").sex("女").phone("18975451234").pinyin("Atang6").build();
        ContactVo c17 = ContactVo.builder().firstPinyin("A").departName("1班").name("阿汤7").sex("女").phone("18975451234").pinyin("Atang7").build();
        ContactVo c18 = ContactVo.builder().firstPinyin("A").departName("1班").name("阿汤8").sex("女").phone("18975451234").pinyin("Atang8").build();
        ContactVo c19 = ContactVo.builder().firstPinyin("A").departName("1班").name("阿汤9").sex("女").phone("18975451234").pinyin("Atang9").build();
        ContactVo c10 = ContactVo.builder().firstPinyin("A").departName("1班").name("阿汤0").sex("女").phone("18975451234").pinyin("Atang0").build();

        ContactVo c2 = ContactVo.builder().firstPinyin("S").departName("1班").name("石头").sex("男").phone("18975451231").pinyin("ShiTou").build();
        ContactVo c21 = ContactVo.builder().firstPinyin("S").departName("1班").name("石2").sex("男").phone("18975451231").pinyin("Shi2").build();
        ContactVo c22 = ContactVo.builder().firstPinyin("S").departName("1班").name("石3").sex("男").phone("18975451231").pinyin("Shi3").build();
        ContactVo c3 = ContactVo.builder().firstPinyin("T").departName("1班").name("糖糖").sex("男").phone("18975451232").pinyin("TangTang").build();

        List<ContactVo> list = new ArrayList();
        list.add(c11);list.add(c12);list.add(c13);
// list.add(c14);list.add(c15);list.add(c16);list.add(c17);list.add(c18);list.add(c19);list.add(c10);
        list.add(c1);
        list.add(c2);
        list.add(c21);
        list.add(c22);
        list.add(c3);

        Map<String,List<ContactVo>> map =  list.stream().collect(Collectors.groupingBy(ContactVo::getFirstPinyin));
        return toResponsSuccess(map);
    }


//    @GetMapping("/addUser")
//    public void addUser(@RequestParam UserDTO user) {
//        log.info("add......");
//        userService.addUser(user);
//    }
}
