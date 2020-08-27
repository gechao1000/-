示例

```
https://github.com/apache/dubbo-spring-boot-project.git

https://github.com/aliyun/alibabacloud-microservice-demo.git
```

版本控制（可选）

```
<dependency>
    <groupId>org.apache.dubbo</groupId>
    <artifactId>dubbo-bom</artifactId>
    <version>2.7.8</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>

或
dubbo-dependencies-bom
```

起步依赖

```
<dependency>
    <groupId>org.apache.dubbo</groupId>
    <artifactId>dubbo-spring-boot-starter</artifactId>
    <version>2.7.8</version>
</dependency>

-- nacos 客户端
<dependency>
	<groupId>com.alibaba.nacos</groupId>
	<artifactId>nacos-client</artifactId>
</dependency>
或
<dependency>
    <groupId>org.apache.dubbo</groupId>
    <artifactId>dubbo-registry-nacos</artifactId>
    <version>2.7.8</version>
</dependency>
```

### Provider 项目

1. 配置文件

```
spring:
  application:
    name: nacos-sample-provider

dubbo:
  scan:
    base-packages: com.example.provider.service
  protocol:
    name: dubbo
    port: -1
  registry:
    address: nacos://127.0.0.1:8848
```

2. 注册服务

```
@DubboService(version = "${demo.service.version}")
public class DefaultDemoService implements DemoService
```

### Consumer 项目

1. 配置文件

```
spring:
  application:
    name: nacos-sample-consumer

dubbo:
  registry:
    address: nacos://127.0.0.1:8848
```

2. 调用服务

```
@DubboReference(version = "${demo.service.version}")
private DemoService demoService;
```

