本项目是一个spring cloud的学习项目，按照使用的功能划分为不同的子项目。

现在有如下模块:

   - [config nacos](https://github.com/guzhangyu/learn-spring-cloud/tree/master/spring-cloud-config)，包含 extension-configs 和 普通 ${spring.cloud.nacos.config.prefix}-${spring.cloud.nacos.config.group}.${spring.cloud.nacos.config.file-extension} 两种形式的配置
   - [dubbo](https://github.com/guzhangyu/learn-spring-cloud/tree/master/spring-cloud-dubbo) 
   - stream 
        [rabbitmq](https://github.com/guzhangyu/learn-spring-cloud/tree/master/spring-cloud-stream/spring-cloud-stream-rabbitmq) 消息队列的实践
        [rocketmq](https://github.com/guzhangyu/learn-spring-cloud/tree/master/spring-cloud-stream/spring-cloud-stream-transaction-sender) 事务性消息
    
   - [sentinel](https://github.com/guzhangyu/learn-spring-cloud/tree/master/spring-cloud-sentinel) 流控、降级
   - [quartz](https://github.com/guzhangyu/learn-spring-cloud/tree/master/springboot-quartz) 多数据源存在时，指定某一个数据源给quartz用
   - [multiDb](https://github.com/guzhangyu/learn-spring-cloud/tree/master/springboot-multidb) 多数据源事务，在mapper层控制使用的数据源，在service层控制哪几个数据源要加上事务
