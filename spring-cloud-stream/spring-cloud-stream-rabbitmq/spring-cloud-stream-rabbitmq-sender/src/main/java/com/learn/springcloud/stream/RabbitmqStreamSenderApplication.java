package com.learn.springcloud.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @Author zhangyugu
 * @Date 2020/8/14 4:01 下午
 * @Version 1.0
 */
@SpringBootApplication
@EnableBinding(RabbitmqStreamSenderApplication.DemoSender.class)
public class RabbitmqStreamSenderApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(RabbitmqStreamSenderApplication.class, args);
        DemoSender demoSender = applicationContext.getBean(DemoSender.class);
        demoSender.output().send(MessageBuilder.withPayload(new MessageDTO(3, "test")).build());
    }

    interface DemoSender {
        String DEMO_OUTPUT = "demo_output";

        @Output(DEMO_OUTPUT)
        MessageChannel output();
    }
}
