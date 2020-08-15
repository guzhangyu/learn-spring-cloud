本项目是一个spring cloud的学习项目，按照使用的功能划分为不同的子项目。

现在有如下模块:

   - config nacos，包含 extension-configs 和 普通 ${application.name}.${file-extension} 两种形式的配置
   - dubbo 
   - stream 
        rabbitmq 一般的消息队列
        rocketmq 事务性消息
    
   - sentinel 流控、降级