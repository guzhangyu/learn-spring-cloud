本项目是一个spring cloud的学习项目，按照使用的功能划分为不同的子项目。

现在有如下模块:

   - [config nacos](https://github.com/guzhangyu/learn-spring-cloud/tree/master/spring-cloud-config)，包含 extension-configs 和 普通 ${spring.cloud.nacos.config.prefix}-${spring.cloud.nacos.config.group}.${spring.cloud.nacos.config.file-extension} 两种形式的配置
   - [dubbo](https://github.com/guzhangyu/learn-spring-cloud/tree/master/spring-cloud-dubbo) 
   - stream 
        [rabbitmq](https://github.com/guzhangyu/learn-spring-cloud/tree/master/spring-cloud-stream/spring-cloud-stream-rabbitmq) 消息队列的实践
        [rocketmq](https://github.com/guzhangyu/learn-spring-cloud/tree/master/spring-cloud-stream/spring-cloud-stream-transaction-sender) 事务性消息
    
   - [sentinel](https://github.com/guzhangyu/learn-spring-cloud/tree/master/spring-cloud-sentinel) 流控、降级
