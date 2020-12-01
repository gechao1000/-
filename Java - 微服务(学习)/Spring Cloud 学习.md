#### 学习资源


```
基于SpringCloud2.0的微服务开发脚手架
https://github.com/zhoutaoo/SpringCloud.git

【录制视频】Java微服务架构Alibaba篇_Spring Cloud Alibaba
https://pan.baidu.com/s/19sxQmA6pMOdLZ9S0BYLD5g 提取码：z7j6
【录制视频】2019Java微服务实战Spring Cloud Alibaba For MyShop
https://pan.baidu.com/s/1i5Q-yPfbONGTiMPDGa9IZg 提取码：kbny
【录制视频】2019Java微服务架构2.0~最前沿技术精选【千锋】https://pan.baidu.com/s/1CB9A71OUOT16NUUuN1vQ8g 提取码：1n7c

【录制视频】2019千锋Java教程-Java微服务架构(SpringBoot+Dubbo+Zookeeper) https://pan.baidu.com/s/1zrRwWn11jOQ9EkaXaZ-ldQ 提取码：31lk
【录制视频】千锋Java教程-cas单点登录解决方案 https://pan.baidu.com/s/167q2mTSr0A3k5TnRkbFKIA 提取码：e7ad
【录制视频】千锋Java教程-dubbo+zookeeper分布式系统架构基础 https://pan.baidu.com/s/13w5gtlMfkVurZIasYS2IiQ 提取码：lqfw
```

#### Spring Cloud Alibaba

> https://github.com/alibaba/spring-cloud-alibaba

```xml
<spring-cloud.version>Greenwich.SR3</spring-cloud.version>
<spring-cloud-alibaba.version>2.1.0.RELEASE</spring-cloud-alibaba.version>

<dependencyManagement>
    <dependencies>
        <!-- Spring Cloud -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>${spring-cloud.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-alibaba-dependencies</artifactId>
            <version>${spring-cloud-alibaba.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

#### Nacos Discovery

> https://nacos.io/zh-cn/docs/quick-start-docker.html

```
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
 
 spring.application.name=service-provider
 server.port=8000
 spring.cloud.nacos.discovery.server-addr=127.0.0.1:8848
 management.endpoints.web.exposure.include=*
 
@EnableFeignClients
@EnableDiscoveryClient
```

#### Sentinel熔断器模式

```
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
</dependency>

feign.sentinel.enabled=true

@FeignClient(value = "nacos-provider", fallback = EchoServiceFallback.class)
```

* jdk8接口default参数，是否可以实现熔断器fallback

#### Sentilen 控制台

1. 编译运行

```shell
git clone https://github.com/alibaba/Sentinel.git

mvn clean package

cd sentinel-dashboard\target

java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar
```

2. 客户端配置

```properties
# 客户端配置
spring.cloud.sentinel.transport.dashboard=127.0.0.1:8080
spring.cloud.sentinel.eager=true
```

#### API网关：Spring Cloud Gateway

> Zuul替代方案，异步非阻塞，WebFlux

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
</dependency>

spring.cloud.gateway.discovery.locator.enabled=true
spring.cloud.gateway.routes[0].id=route-consumer
spring.cloud.gateway.routes[0].uri=lb://service-consumer
spring.cloud.gateway.routes[0].predicates[0].name=Path
#spring.cloud.gateway.routes[0].predicates[0].args[pattern]=/nacos/**
```

> 全局过滤器GlobalFilter

```
class MyFilter implements GlobalFilter, Ordered

String token = exchange.getRequest().getQueryParams().getFirst("token");
if (token == null || token.isEmpty()) {
	ServerHttpResponse response = exchange.getResponse();

	// 封装错误信息
	Map<String, Object> responseData = Maps.newHashMap();
	responseData.put("code", 401);
	responseData.put("message", "非法请求");
	responseData.put("cause", "Token is empty");

	try {
		// 将信息转换为 JSON
		ObjectMapper objectMapper = new ObjectMapper();
		byte[] data = objectMapper.writeValueAsBytes(responseData);

		// 输出错误信息到页面
		DataBuffer buffer = response.bufferFactory().wrap(data);
		response.setStatusCode(HttpStatus.UNAUTHORIZED);
		response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
		return response.writeWith(Mono.just(buffer));
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
}
return chain.filter(exchange);
```

#### 配置中心

> 配置文件统一管理

```
<dependency>
     <groupId>com.alibaba.cloud</groupId>
     <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
 </dependency>

# bootstrap.properties
spring.application.name=nacos-config-example
spring.cloud.nacos.config.server-addr=127.0.0.1:8848
#spring.cloud.nacos.config.file-extension=properties

@RefreshScope 打开动态刷新功能

是否开启监听和自动刷新|spring.cloud.nacos.config.refresh.enabled|true|
```

> 多环境配置，dev，test，prod

```
application-dev.yml

spring.profiles.active=dev

idea: Active profiles: dev
```

#### SkyWalking分布式链路追踪

> APM 工具有: Cat、Zipkin、Pinpoint、SkyWalking
>
> 多语言自动探针，Java，.NET Core 和 Node.JS
>
> Agent, Collector, Storage

##### 1. 存储方案 ElasticSearch

```
version: '3.3'
services:
  elasticsearch:
    image: wutang/elasticsearch-shanghai-zone:6.3.2
    container_name: elasticsearch
    restart: always
    ports:
      - 9200:9200
      - 9300:9300
    environment:
      cluster.name: elasticsearch
```

##### 2. 本地启动 SkyWalking

> http://skywalking.apache.org/zh/downloads/

```
配置文件 apache-skywalking-apm-incubating/config/application.yml
- 注释 H2 存储方案
- 启用 ElasticSearch 存储方案
- 修改 ElasticSearch 服务器地址

默认的用户名密码为：admin/admin
```

IDEA 部署探针

> 修改项目的 VM 运行参数

```
-javaagent:D:\Workspace\Others\hello-spring-cloud-alibaba\hello-spring-cloud-external-skywalking\agent\skywalking-agent.jar
-Dskywalking.agent.service_name=nacos-provider
-Dskywalking.collector.backend_service=localhost:11800
```

#### Assembly 打包

pom.xml

```
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-assembly-plugin</artifactId>
            <executions>
                <!-- 配置执行器 -->
                <execution>
                    <id>make-assembly</id>
                    <!-- 绑定到 package 生命周期阶段上 -->
                    <phase>package</phase>
                    <goals>
                        <!-- 只运行一次 -->
                        <goal>single</goal>
                    </goals>
                    <configuration>
                        <finalName>skywalking</finalName>
                        <descriptors>
                            <!-- 配置描述文件路径 -->
                            <descriptor>package.xml</descriptor>
                        </descriptors>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

package.xml

```
<assembly>
    <id>6.4.0</id>
    <formats>
        <!-- 打包的文件格式，支持 zip、tar.gz、tar.bz2、jar、dir、war -->
        <format>tar.gz</format>
    </formats>
    <!-- tar.gz 压缩包下是否生成和项目名相同的根目录，有需要请设置成 true -->
    <includeBaseDirectory>false</includeBaseDirectory>
    <dependencySets>
        <dependencySet>
            <!-- 是否把本项目添加到依赖文件夹下，有需要请设置成 true -->
            <useProjectArtifact>false</useProjectArtifact>
            <outputDirectory>lib</outputDirectory>
            <!-- 将 scope 为 runtime 的依赖包打包 -->
            <scope>runtime</scope>
        </dependencySet>
    </dependencySets>
    <fileSets>
        <fileSet>
            <!-- 设置需要打包的文件路径 -->
            <directory>agent</directory>
            <!-- 打包后的输出路径 -->
            <outputDirectory></outputDirectory>
        </fileSet>
    </fileSets>
</assembly>
```

#### 消息队列 RocketMQ

1. docker-compose.yml

```
version: '3.5'
services:
  rmqnamesrv:
    image: foxiswho/rocketmq:server
    container_name: rmqnamesrv
    ports:
      - 9876:9876
    volumes:
      - ./data/logs:/opt/logs
      - ./data/store:/opt/store
    networks:
        rmq:
          aliases:
            - rmqnamesrv

  rmqbroker:
    image: foxiswho/rocketmq:broker
    container_name: rmqbroker
    ports:
      - 10909:10909
      - 10911:10911
    volumes:
      - ./data/logs:/opt/logs
      - ./data/store:/opt/store
      - ./data/brokerconf/broker.conf:/etc/rocketmq/broker.conf
    environment:
        NAMESRV_ADDR: "rmqnamesrv:9876"
        JAVA_OPTS: " -Duser.home=/opt"
        JAVA_OPT_EXT: "-server -Xms128m -Xmx128m -Xmn128m"
    command: mqbroker -c /etc/rocketmq/broker.conf
    depends_on:
      - rmqnamesrv
    networks:
      rmq:
        aliases:
          - rmqbroker

  rmqconsole:
    image: styletang/rocketmq-console-ng
    container_name: rmqconsole
    ports:
      - 8080:8080
    environment:
        JAVA_OPTS: "-Drocketmq.namesrv.addr=rmqnamesrv:9876 -Dcom.rocketmq.sendMessageWithVIPChannel=false"
    depends_on:
      - rmqnamesrv
    networks:
      rmq:
        aliases:
          - rmqconsole

networks:
  rmq:
    name: rmq
    driver: bridge
```

2. 配置文件 `./data/brokerconf/broker.conf`

```
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License.


# 所属集群名字
brokerClusterName=DefaultCluster

# broker 名字，注意此处不同的配置文件填写的不一样，如果在 broker-a.properties 使用: broker-a,
# 在 broker-b.properties 使用: broker-b
brokerName=broker-a

# 0 表示 Master，> 0 表示 Slave
brokerId=0

# nameServer地址，分号分割
# namesrvAddr=rocketmq-nameserver1:9876;rocketmq-nameserver2:9876

# 启动IP,如果 docker 报 com.alibaba.rocketmq.remoting.exception.RemotingConnectException: connect to <192.168.0.120:10909> failed
# 解决方式1 加上一句 producer.setVipChannelEnabled(false);，解决方式2 brokerIP1 设置宿主机IP，不要使用docker 内部IP
# brokerIP1=192.168.0.253

# 在发送消息时，自动创建服务器不存在的topic，默认创建的队列数
defaultTopicQueueNums=4

# 是否允许 Broker 自动创建 Topic，建议线下开启，线上关闭 ！！！这里仔细看是 false，false，false
autoCreateTopicEnable=true

# 是否允许 Broker 自动创建订阅组，建议线下开启，线上关闭
autoCreateSubscriptionGroup=true

# Broker 对外服务的监听端口
listenPort=10911

# 删除文件时间点，默认凌晨4点
deleteWhen=04

# 文件保留时间，默认48小时
fileReservedTime=120

# commitLog 每个文件的大小默认1G
mapedFileSizeCommitLog=1073741824

# ConsumeQueue 每个文件默认存 30W 条，根据业务情况调整
mapedFileSizeConsumeQueue=300000

# destroyMapedFileIntervalForcibly=120000
# redeleteHangedFileInterval=120000
# 检测物理文件磁盘空间
diskMaxUsedSpaceRatio=88
# 存储路径
# storePathRootDir=/home/ztztdata/rocketmq-all-4.1.0-incubating/store
# commitLog 存储路径
# storePathCommitLog=/home/ztztdata/rocketmq-all-4.1.0-incubating/store/commitlog
# 消费队列存储
# storePathConsumeQueue=/home/ztztdata/rocketmq-all-4.1.0-incubating/store/consumequeue
# 消息索引存储路径
# storePathIndex=/home/ztztdata/rocketmq-all-4.1.0-incubating/store/index
# checkpoint 文件存储路径
# storeCheckpoint=/home/ztztdata/rocketmq-all-4.1.0-incubating/store/checkpoint
# abort 文件存储路径
# abortFile=/home/ztztdata/rocketmq-all-4.1.0-incubating/store/abort
# 限制的消息大小
maxMessageSize=65536

# flushCommitLogLeastPages=4
# flushConsumeQueueLeastPages=2
# flushCommitLogThoroughInterval=10000
# flushConsumeQueueThoroughInterval=60000

# Broker 的角色
# - ASYNC_MASTER 异步复制Master
# - SYNC_MASTER 同步双写Master
# - SLAVE
brokerRole=ASYNC_MASTER

# 刷盘方式
# - ASYNC_FLUSH 异步刷盘
# - SYNC_FLUSH 同步刷盘
flushDiskType=ASYNC_FLUSH

# 发消息线程池数量
# sendMessageThreadPoolNums=128
# 拉消息线程池数量
# pullMessageThreadPoolNums=128
```

3. 生产者

```
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-stream-rocketmq</artifactId>
</dependency>

spring.cloud.stream.rocketmq.binder.name-server=127.0.0.1:9876

spring.cloud.stream.bindings.output.destination=test-topic
spring.cloud.stream.bindings.output.content-type=application/json
#spring.cloud.stream.rocketmq.bindings.output.producer.group=binder-group
#spring.cloud.stream.rocketmq.bindings.output.producer.sync=tru

@EnableBinding({Source.class})

@Autowired
private MessageChannel output;

output.send(MessageBuilder.withPayload(message).build());
```

4. 消费者

```
spring.cloud.stream.rocketmq.binder.name-server=127.0.0.1:9876
spring.cloud.stream.rocketmq.bindings.input.consumer.orderly=true

spring.cloud.stream.bindings.input.destination=test-topic
spring.cloud.stream.bindings.input.content-type=text/plain
spring.cloud.stream.bindings.input.group=test-group

@StreamListener("input")
public void receiveInput(String message) {
	System.out.println("Receive input: " + message);
}
```

#### Dubbo

高性能、轻量级的开源 Java RPC 分布式服务框架

三大核心能力：面向接口的远程方法调用，智能容错和负载均衡，以及服务自动注册和发现

> http://dubbo.apache.org/zh-cn/

1. 依赖管理

```xml
<dubbo.version>2.7.3</dubbo.version>

<!-- Apache Dubbo -->
<dependency>
	<groupId>org.apache.dubbo</groupId>
	<artifactId>dubbo</artifactId>
	<version>${dubbo.version}</version>
</dependency>
<dependency>
	<groupId>org.apache.dubbo</groupId>
	<artifactId>dubbo-configcenter-nacos</artifactId>
	<version>${dubbo.version}</version>
</dependency>
<dependency>
	<groupId>org.apache.dubbo</groupId>
	<artifactId>dubbo-registry-nacos</artifactId>
	<version>${dubbo.version}</version>
</dependency>
<dependency>
    <groupId>org.apache.dubbo</groupId>
    <artifactId>dubbo-serialization-kryo</artifactId>
    <version>${dubbo.version}</version>
</dependency>
```

2. （可选）log4j.properties

```properties
log4j.rootLogger=info, stdout
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=[%d{dd/MM/yy hh:mm:ss:sss z}] %t %5p %c{2}: %m%n
```

3. 服务提供者

```
dubbo.application.name=nacos-sample-provider
dubbo.registry.address=nacos://${nacos.address:localhost}:8848
dubbo.protocol.name=dubbo
dubbo.protocol.port=20880
dubbo.protocol.serialization=kryo

## DemoService version
demo.service.version=1.0.0

----
# 启动类
@EnableDubbo(scanBasePackages = "com.example.providersample.service")
// new CountDownLatch(1).await();

# 通过 org.apache.dubbo 包下的 @Service 注解将服务暴露出去
@Service(version = "${demo.service.version}")
public class DefaultEchoService implements EchoService {}
```

4. 服务消费者

```
dubbo.application.name=nacos-demo-consumer
dubbo.registry.address=nacos://${nacos.address:localhost}:8848
dubbo.consumer.timeout=3000

## DemoService version
demo.service.version=1.0.0

----

@Reference(version = "${demo.service.version}")
private EchoService echoService;
```

5. **LoadBalance**  配置负载均衡

```
dubbo.provider.loadbalance: roundrobin

# random(默认), 随机:按权重设置随机概率
# roundrobin, 轮循:按公约后的权重设置轮询比率，存在慢的提供者累积请求的问题
# leastactive, 最少活跃调用数:同活跃数的随机，使慢的提供者收到更少请求
# consistenthash, 一致性 Hash:相同参数的请求总是发到同一提供者
```

