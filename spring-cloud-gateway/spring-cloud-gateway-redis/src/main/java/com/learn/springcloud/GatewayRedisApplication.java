package com.learn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @Author zhangyugu
 * @Date 2020/9/20 7:43 上午
 * @Version 1.0
 */
@SpringBootApplication
@Configuration
public class GatewayRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayRedisApplication.class, args);
    }

    @Bean
    KeyResolver userKeyResolver() {
        return exchange -> {
            System.out.println(exchange);
            return Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
        };
    }

}
