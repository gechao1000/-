1. 环境
```plain
# 安装RabbitMQ
docker run -d -p 5672:5672 -p 15672:15672 --name rabbitmq rabbitmq:management
# 依赖
spring-cloud-starter-stream-rabbit
# 连接MQ
spring.rabbitmq.addresses=amqp://192.168.115.128:5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```
2. 生产者
```plain
# 定义Output
public interface MessageProducer {
@Output("producerChannel")
MessageChannel  producerChannel();
}
# 启动类
@EnableBinding(MessageProducer.class)
# 配置文件（指定目的地，对于MQ就是exchange）
spring.cloud.stream.bindings.producerChannel.destination=itheimaBind
# 发送消息
@Resource private MessageProducer messageProducer;
messageProducer.producerChannel().send(MessageBuilder.withPayload("aaa").build());
```
3. 消费者
```plain
# 定义Input
public interface MessageConsumer {
@Input("producerChannel")
SubscribableChannel  producerChannel();
}
# 配置文件
spring.cloud.stream.bindings.producerChannel.destination=itheimaBind
spring.cloud.stream.bindings.producerChannel.group=itheimaBind-group
# 接收消息
@EnableBinding(MessageConsumer.class)
public class MessageConsumerListener {
@StreamListener(target ="producerChannel" )
public void getMessage(String msg){
System.out.println(">>>>>>>>>>>>>>>>>>>>"+msg);
}
}
```

