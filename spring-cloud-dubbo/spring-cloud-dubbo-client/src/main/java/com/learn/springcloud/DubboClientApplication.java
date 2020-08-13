package com.learn.springcloud;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author zhangyugu
 * @Date 2020/8/13 9:00 上午
 * @Version 1.0
 */
@SpringBootApplication
@EnableDubbo
public class DubboClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboClientApplication.class, args);
    }
}
