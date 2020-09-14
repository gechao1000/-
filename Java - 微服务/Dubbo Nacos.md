#### 依赖

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-dubbo</artifactId>
</dependency>
```

#### Provider

1. 定义服务

```java
import org.apache.dubbo.config.annotation.Service;

@Service
public class XXXService implements XXXApi {...}
```

2. 配置文件

```yaml
server:
  port: 18000

spring:
  application:
    name: sample-provider
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
  main:
    allow-bean-definition-overriding: true

dubbo:
  scan:
    base-packages: com.example.service
  protocol:
    name: dubbo
  cloud:
    subscribed-services: ""
  registry:
    address: spring-cloud://127.0.0.1
```

#### Consumer

1. 调用服务

```java
import org.apache.dubbo.config.annotation.Reference;

@Reference
private OrderService orderService;
```

2. 配置文件

```yaml
server:
  port: 18080

spring:
  application:
    name: sample-consumer
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
  main:
    allow-bean-definition-overriding: true

dubbo:
  protocol:
    name: dubbo
    port: -1
  cloud:
    subscribed-services: sample-provider
  registry:
    address: spring-cloud://127.0.0.1

```

