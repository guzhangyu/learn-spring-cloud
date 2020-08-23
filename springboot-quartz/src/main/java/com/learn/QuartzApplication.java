package com.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author zhangyugu
 * @Date 2020/8/21 9:06 上午
 * @Version 1.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class, scanBasePackages={"com.learn.springboot.quartz", "com.learn.springcloud"})
public class QuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }
}
