## 分布式任务调度平台XXL-JOB

> https://www.xuxueli.com/xxl-job/、https://github.com/xuxueli/xxl-job

XXL-JOB是一个分布式任务调度平台，其核心设计目标是开发迅速、学习简单、轻量级、易扩展。现已开放源代码并接入多家公司线上产品线，开箱即用


#### 1. 源码

###### 1. 1 目录结构

```
- /doc :文档资料
- /db :“调度数据库”建表脚本
- /xxl-job-admin :调度中心，项目源码
- /xxl-job-core :公共Jar依赖
- /xxl-job-executor-samples :执行器，Sample示例项目
```

###### 1.2 数据库  `/xxl-job/doc/db/tables_xxl_job.sql`

```
- xxl_job_lock：任务调度锁表；
- xxl_job_group：执行器信息表，维护任务执行器信息；
- xxl_job_info：调度扩展信息表： 用于保存XXL-JOB调度任务的扩展信息，如任务分组、任务名、机器地址、执行器、执行入参和报警邮件等等；
- xxl_job_log：调度日志表： 用于保存XXL-JOB任务调度的历史信息，如调度结果、执行结果、调度入参、调度机器和执行器等等；
- xxl_job_log_report：调度日志报表：用户存储XXL-JOB任务调度日志的报表，调度中心报表功能页面会用到；
- xxl_job_logglue：任务GLUE日志：用于保存GLUE更新历史，用于支持GLUE的版本回溯功能；
- xxl_job_registry：执行器注册表，维护在线的执行器和调度中心机器地址信息；
- xxl_job_user：系统用户表；
```

#### 2. 配置部署”调度中心“

统一管理任务调度平台上调度任务，负责触发调度执行，并且提供任务管理平台

###### 2.1 编译源码

```
修改配置文件，jdbc、邮箱、日志
/xxl-job/xxl-job-admin/src/main/resources/application.properties

http://localhost:8080/xxl-job-admin 
默认登录账号 “admin/123456
```

###### 2.2 docker

```
docker pull xuxueli/xxl-job-admin:2.2.0

docker run -p 8080:8080 -v /tmp:/data/applogs --name xxl-job-admin  -d xuxueli/xxl-job-admin:2.2.0

# 自定义 mysql
docker run -e PARAMS="--spring.datasource.url=jdbc:mysql://127.0.0.1:3306/xxl_job?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=Asia/Shanghai" -p 8080:8080 -v /tmp:/data/applogs --name xxl-job-admin  -d xuxueli/xxl-job-admin:2.2.0
```

###### 2.3 docker build

```
mvn clean package

docker build -t xuxueli/xxl-job-admin ./xxl-job-admin

docker run --name xxl-job-admin -p 8080:8080 -d xuxueli/xxl-job-admin
```

#### 3. 配置部署“执行器项目”

###### 3.1 依赖，springboot版本：`2.2.x`

```
<dependency>
  <groupId>com.xuxueli</groupId>
  <artifactId>xxl-job-core</artifactId>
  <version>2.2.0</version>
</dependency>
```

###### 3.2 配置文件

```
### 调度中心部署跟地址 [选填]：如调度中心集群部署存在多个地址则用逗号分隔。执行器将会使用该地址进行"执行器心跳注册"和"任务结果回调"；为空则关闭自动注册；
xxl.job.admin.addresses=http://127.0.0.1:8080/xxl-job-admin
### 执行器通讯TOKEN [选填]：非空时启用；
xxl.job.accessToken=
### 执行器AppName [选填]：执行器心跳注册分组依据；为空则关闭自动注册
xxl.job.executor.appname=xxl-job-executor-sample
### 执行器注册 [选填]：优先使用该配置作为注册地址，为空时使用内嵌服务 ”IP:PORT“ 作为注册地址。从而更灵活的支持容器类型执行器动态IP和动态映射端口问题。
xxl.job.executor.address=
### 执行器IP [选填]：默认为空表示自动获取IP，多网卡时可手动设置指定IP，该IP不会绑定Host仅作为通讯实用；地址信息用于 "执行器注册" 和 "调度中心请求并触发任务"；
xxl.job.executor.ip=
### 执行器端口号 [选填]：小于等于0则自动获取；默认端口为9999，单机部署多个执行器时，注意要配置不同执行器端口；
xxl.job.executor.port=9999
### 执行器运行日志文件存储磁盘路径 [选填] ：需要对该路径拥有读写权限；为空则使用默认路径；
xxl.job.executor.logpath=/data/applogs/xxl-job/jobhandler
### 执行器日志文件保存天数 [选填] ： 过期日志自动清理, 限制值大于等于3时生效; 否则, 如-1, 关闭自动清理功能；
xxl.job.executor.logretentiondays=30
```

###### 3.3 组件配置

```
@Bean
public XxlJobSpringExecutor xxlJobExecutor() {
    logger.info(">>>>>>>>>>> xxl-job config init.");
    XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
    xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
    xxlJobSpringExecutor.setAppname(appname);
    xxlJobSpringExecutor.setIp(ip);
    xxlJobSpringExecutor.setPort(port);
    xxlJobSpringExecutor.setAccessToken(accessToken);
    xxlJobSpringExecutor.setLogPath(logPath);
    xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
    return xxlJobSpringExecutor;
}
```

#### 4. 任务”运行模式“

###### 4.1 GLUE模式（Java）

```
- 任务以源码方式维护在调度中心
- 实际上是一段继承自IJobHandler的Java类代码，并 "groovy" 源码方式维护
- 可使用@Resource/@Autowire注入执行器里中的其他服务
```

###### 4.2 Bean模式

```
- 任务以JobHandler方式维护在执行器端
- 基于类，继承IJobHandler，手动通过如下方式注入到执行器容器
XxlJobExecutor.registJobHandler("demoJobHandler", new DemoJobHandler())
- 基于方法，每个任务只需要开发一个方法，并添加”@XxlJob”注解，方法签名固定
public ReturnT<String> execute(String param)
```



## Docker 部署

#### 1. 镜像

```
docker pull percona:5.7
docker pull xuxueli/xxl-job-admin:2.2.0
```

#### 2. 部署

```
# 先配置数据库，初始化表，再启动admin项目
https://github.com/xuxueli/xxl-job/blob/master/doc/db/tables_xxl_job.sql

# docker容器访问宿主机，直接使用IP
environment:
  PARAMS: |
	--spring.datasource.url=jdbc:mysql://172.17.0.1:3306/xxl_job?Unicode=true&characterEncoding=UTF-8
	--spring.datasource.username=root
	--spring.datasource.password=123456
```

#### 3.自定义任务

> 问题：无法根据容器名解析ip

```shell
docker create --name sample --restart always --network xxl_job_default bmcp/job-sample

xxl.job.adminAddresses=http://xxl_job:8080/xxl-job-admin
```

解决方案

```
# 手动解析hostname
InetAddress host = InetAddress.getByName("xxl_job");
System.out.println(host.getHostAddress());
```

