package com.learn.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhangyugu
 * @Date 2020/9/17 5:54 上午
 * @Version 1.0
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping("test")
    public String test() throws InterruptedException {
        log.info("test pre");
        Thread.sleep(6000L);
        log.info("test after");
        return "test";
    }

    @GetMapping("normalTest")
    public String normalTest() {
        log.info("normalTest");
        return "normalTest";
    }
}
