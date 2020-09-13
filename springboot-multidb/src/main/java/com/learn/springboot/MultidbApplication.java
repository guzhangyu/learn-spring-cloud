package com.learn.springboot;

import com.learn.springboot.datasource.DynamicDataSourceRegister;
import com.learn.springboot.service.TestTransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * @Author zhangyugu
 * @Date 2020/9/12 5:32 下午
 * @Version 1.0
 */
@Import(DynamicDataSourceRegister.class)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MultidbApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MultidbApplication.class, args);
        TestTransactionService testTransactionService = applicationContext.getBean(TestTransactionService.class);

        testTransactionService.test();
    }
}
