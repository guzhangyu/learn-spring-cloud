package com.learn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author zhangyugu
 * @Date 2020/9/20 7:44 上午
 * @Version 1.0
 */
@SpringBootApplication
public class GatewayHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayHystrixApplication.class, args);
    }
}
