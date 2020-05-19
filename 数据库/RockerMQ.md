#### 消息中间件

>  http://rocketmq.apache.org/ 



###### 核心概念

producer:  生产者

consumer: 消费者，PUSH(服务端推送)，PULL(客户端定时拉取)

broker: 负责消息发送、接收、高可用，定时发送自身情况到nameserver

nameserver: 保存broker元数据，协调，不负责消息处理

topic: 逻辑概念，消息类型（如User、Order），Message Queue存储消息

#### Java 客户端

```
<dependency>
  <groupId>org.apache.rocketmq</groupId>
  <artifactId>rocketmq-client</artifactId>
  <version>4.6.1</version>
</dependency>
```

