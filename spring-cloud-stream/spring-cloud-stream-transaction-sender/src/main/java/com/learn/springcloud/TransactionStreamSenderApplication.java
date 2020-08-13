package com.learn.springcloud;

import com.learn.springcloud.service.RocketMqTestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author zhangyugu
 * @Date 2020/8/13 3:44 下午
 * @Version 1.0
 */
@SpringBootApplication
public class TransactionStreamSenderApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TransactionStreamSenderApplication.class, args);
        RocketMqTestService service = context.getBean(RocketMqTestService.class);
        service.testTransaction();
    }
}
