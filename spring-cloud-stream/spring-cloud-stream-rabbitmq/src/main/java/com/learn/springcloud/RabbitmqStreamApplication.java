package com.learn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @Author zhangyugu
 * @Date 2020/8/13 2:08 下午
 * @Version 1.0
 */
@SpringBootApplication
@EnableBinding(RabbitmqStreamApplication.DemoSender.class)
public class RabbitmqStreamApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(RabbitmqStreamApplication.class, args);
        DemoSender demoSender = applicationContext.getBean(DemoSender.class);
        demoSender.output().send(MessageBuilder.withPayload("fda").build());
    }

    interface DemoSender {
        String DEMO_OUTPUT = "demo_output";

        @Output(DEMO_OUTPUT)
        MessageChannel output();
    }
}
