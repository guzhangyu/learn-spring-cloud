package com.learn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author zhangyugu
 * @Date 2020/8/13 7:27 上午
 * @Version 1.0
 */
@SpringBootApplication
public class ConfigApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ConfigApplication.class, args);
        System.out.println(applicationContext.getBean(ConfigConfiguration.class).getTestProp());
    }
}
