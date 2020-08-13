package com.learn.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhangyugu
 * @Date 2020/7/18 10:30 上午
 * @Version 1.0
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
