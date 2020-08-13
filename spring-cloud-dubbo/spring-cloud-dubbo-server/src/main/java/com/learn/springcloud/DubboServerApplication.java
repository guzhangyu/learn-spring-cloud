package com.learn.springcloud;

import com.learn.springcloud.service.DemoService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author zhangyugu
 * @Date 2020/8/13 8:05 上午
 * @Version 1.0
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.learn.springcloud.service.impl")
public class DubboServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(DubboServerApplication.class, args);
        DemoService demoService = applicationContext.getBean(DemoService.class);
        System.out.println(demoService.hello("dd"));
    }
}
