package org.tang.jsj.biz.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Service(version = "1.0.0",timeout = 10000,interfaceClass = HelloService.class)
@Component
@Slf4j
public class HelloServiceImpl implements HelloService{

    @Override
    public String sayHello(String name) {
        return "hello "+name;
    }
}
