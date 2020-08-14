package com.learn.springcloud.controller;

import com.learn.springcloud.service.impl.SentinelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhangyugu
 * @Date 2020/7/18 10:30 上午
 * @Version 1.0
 */
@RestController
public class TestController {

    @Autowired
    SentinelService sentinelService;

    @GetMapping("/hello")
    public String hello(){
        sentinelService.doSomeThing("haha");
        return "hello";
    }
}
