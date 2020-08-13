package com.learn.springcloud.controller;

import com.learn.springcloud.service.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhangyugu
 * @Date 2020/8/13 9:06 上午
 * @Version 1.0
 */
@RestController
public class TestController {

    @DubboReference(version = "1.0.0")
    DemoService demoService;

    @GetMapping("/hello")
    public String hello(String name) {
        return demoService.hello(name);
    }
}
