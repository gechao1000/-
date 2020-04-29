## 介绍

 高性能的开源 Java 内存缓存库 

 不是分布式缓存 

#### 流行的缓存

1.  `EhCache`：一个**纯Java的进程**内缓存框架，具有**快速、精干**等特点。因为它是纯Java进程的，所以也是基于本地缓存的。**（注意：EhCache2.x和EhCache3.x差异巨大且不兼容）** 
2.  `Hazelcast`：**基于内存**的数据网格。虽然它基于内存，但是**分布式应用程序**可以使用`Hazelcast`进行**分布式缓存**、同步、集群、处理、发布/订阅消息等。（如果你正在寻找**基于内存的**、高速的、**可弹性扩展**的、**支持分布式的**、对开发者友好的`NoSQL`，`Hazelcast`是一个很棒的选择，它的理念是用应用服务的内存换取效率，成本较高） 
3.  `Infinispan`：基于Apache 2.0协议的分布式键值存储系统，可以以`普通java lib`或者**独立服务**的方式提供服务，支持各种协议(`Hot Rod, REST, WebSockets`)。支持的高级特性包括：事务、事件通知、高级查询、分布式处理、off-heap及故障迁移。  它按照署模式分为**嵌入式(Embedded)模式（基于本地内存）** 
4.  `Couchbase`：是一个非[关系型数据库](https://cloud.tencent.com/product/cdb-overview?from=10680)，它实际上是由`couchdb+membase`组成，所以它既能像`couchdb`那样存储`json文档`（类似`MongoDB`），也能像`membase`那样高速存储键值对。（新一代的NoSql数据库，国外挺火的） 
5.  `Redis`：熟悉得不能再熟悉的**分布式缓存**，只有Client-Server(C\S)模式，单线程让它天生具有线程安全的特性。Java一般使用`Jedis/Luttuce`来操纵~ 
6.  **`Caffeine(咖啡因)`**：Caffeine是使用Java8对Guava缓存的重写版本，一个接近最佳的的缓存库（号称性能最好）。**`Spring5已经放弃guava，拥抱caffeine`**，它的API保持了近乎和`guava`一致，但是性能上碾压它 

## `Caffeine`和Spring Cache整合

#### 1. 依赖

> spring-context-support 模块，实现CacheManager

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-cache</artifactId>
</dependency>
<dependency>
	<groupId>com.github.ben-manes.caffeine</groupId>
	<artifactId>caffeine</artifactId>
</dependency>
```

#### 2. 配置文件

###### application.properties

```
spring.cache.cache-names=IZUUL
spring.cache.caffeine.spec=initialCapacity=50,maximumSize=500,expireAfterWrite=5s
spring.cache.type=caffeine
```

###### 配置类

```java
@EnableCaching
@Configuration
public class CacheConfig extends CachingConfigurerSupport {

    @Bean
    @Override
    public CacheManager cacheManager() {
        CaffeineCacheManager cm = new CaffeineCacheManager();

        cm.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .initialCapacity(100));

        return cm;
    }
}
```

###### 配置说明

```
initialCapacity=[integer]: 初始的缓存空间大小
maximumSize=[long]: 缓存的最大条数
maximumWeight=[long]: 缓存的最大权重
expireAfterAccess=[duration]: 最后一次写入或访问后经过固定时间过期
expireAfterWrite=[duration]: 最后一次写入后经过固定时间过期
refreshAfterWrite=[duration]: 创建缓存或者最近一次更新缓存后经过固定的时间间隔，刷新缓存
weakKeys: 打开key的弱引用
weakValues：打开value的弱引用
softValues：打开value的软引用
recordStats：开发统计功能


expireAfterWrite和expireAfterAccess同事存在时，以expireAfterWrite为准。
maximumSize和maximumWeight不可以同时使用
weakValues和softValues不可以同时使用
```

#### 3. 使用

###### 注解

```
@Cacheable(cacheNames = "demoCache", key = "#id")
public Object getFromDB(Integer id) {
	System.out.println("模拟去db查询~~~" + id);
	return "hello cache...";
}
```

###### 其它

```
@Resource
CacheManager cacheManager;

Cache demoCache = cacheManager.getCache("demoCache");
demoCache.get(key, String.class)
```

## `Ehcache`和Spring Cache整合

#### 1. 依赖

> spring-context-support 默认支持 EhCache2.x

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-cache</artifactId>
</dependency>
<dependency>
	<groupId>javax.cache</groupId>
	<artifactId>cache-api</artifactId>
</dependency>
<dependency>
	<groupId>org.ehcache</groupId>
	<artifactId>ehcache</artifactId>
</dependency>
```

#### 2. 配置文件

###### 配置类

```java
@Configuration
@EnableCaching
public class CacheConfig {}
```

###### 监听类

```java
public class CacheEventLogger implements CacheEventListener<Object, Object> {

    private static final Logger log = LoggerFactory.getLogger(CacheEventLogger.class);

    @Override
    public void onEvent(CacheEvent<? extends Object, ? extends Object> cacheEvent) {
        log.info("Cache event {} for item with key {}. Old value = {}, New value = {}", cacheEvent.getType(), cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
    }

}
```

###### application.properties

```properties
spring.cache.jcache.config=classpath:ehcache.xml
```

###### ehcache.xml

```xml
<config xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns="http://www.ehcache.org/v3"
    xmlns:jsr107="http://www.ehcache.org/v3/jsr107"
    xsi:schemaLocation="
            http://www.ehcache.org/v3 http://www.ehcache.org/schema/ehcache-core-3.0.xsd
            http://www.ehcache.org/v3/jsr107 http://www.ehcache.org/schema/ehcache-107-ext-3.0.xsd">

    <cache alias="squareCache">
        <key-type>java.lang.Long</key-type>
        <value-type>java.math.BigDecimal</value-type>
        <expiry>
            <ttl unit="seconds">30</ttl>
        </expiry>

        <listeners>
            <listener>
                <class>com.baeldung.cachetest.config.CacheEventLogger</class>
                <event-firing-mode>ASYNCHRONOUS</event-firing-mode>
                <event-ordering-mode>UNORDERED</event-ordering-mode>
                <events-to-fire-on>CREATED</events-to-fire-on>
                <events-to-fire-on>EXPIRED</events-to-fire-on>
            </listener>
        </listeners>

        <resources>
            <heap unit="entries">2</heap>
            <offheap unit="MB">10</offheap>
        </resources>
    </cache>

</config>
```

#### 3. 使用

```
@Cacheable(value = "squareCache", key = "#number", condition = "#number>10")
public BigDecimal square(Long number) {
	BigDecimal square = BigDecimal.valueOf(number)
		.multiply(BigDecimal.valueOf(number));
	log.info("square of {} is {}", number, square);
	return square;
}
```

