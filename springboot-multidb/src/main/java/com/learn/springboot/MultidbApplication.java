package com.learn.springboot;

import com.learn.springboot.mapper.IdentifyScoreMapper;
import com.learn.springboot.service.TestTransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author zhangyugu
 * @Date 2020/9/12 5:32 下午
 * @Version 1.0
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@EnableTransactionManagement
public class MultidbApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MultidbApplication.class, args);
//        IdentifyScoreMapper mapper = applicationContext.getBean(IdentifyScoreMapper.class);
//        mapper.updateScore(8);
        TestTransactionService testTransactionService = applicationContext.getBean(TestTransactionService.class);

        testTransactionService.test();
    }
}
