### Docker 安装

https://hub.docker.com/r/sonatype/nexus3
https://www.jianshu.com/p/edf57ba6a159

```
docker pull sonatype/nexus3
docker run -d -p 8081:8081 --name nexus sonatype/nexus3

用户名：admin
初始密码：/nexus-data/admin.password
```

### CentOS 安装

https://help.sonatype.com/repomanager3/download

```
$ vi  /etc/security/limits.conf
root soft     nproc          65536
root hard     nproc          65536
root soft     nofile         65536
root hard     nofile         65536

前台启动
./nexus run

后台启动
./nexus start
./nexus stop
```



### 添加阿里云maven代理

```
settings -> Repository -> Repositories
点击Create Repository
选择maven2(proxy)
Name字段aliyun-maven
URL 字段http://maven.aliyun.com/nexus/content/groups/public/
重新配置maven-public组，包含aliyun-maven，并且上移到第一位

<mirror>
    <id>huaweicloud</id>
    <mirrorOf>*</mirrorOf>
    <url>https://repo.huaweicloud.com/repository/maven/</url>
</mirror>
```



### 配置 maven

全局 settings.xml

```
<server>
	<id>maven-releases</id>
	<username>admin</username>
	<password>abcd1234</password>
</server>
<server>
	<id>maven-public</id>
	<username>admin</username>
	<password>abcd1234</password>
</server>


<mirror>
  <id>maven-public</id>
  <name>maven-public</name>
	<url>http://localhost:8081/repository/maven-public/</url>
  <mirrorOf>*</mirrorOf>
</mirror>
```

单个项目

```
<repositories>
	<repository>
		<id>maven-public</id>
		<name>maven-public</name>
		<url>http://10.168.4.75:8081/repository/maven-public/</url>
		<layout>default</layout>
		<releases>
			<enabled>true</enabled>
		</releases>
		<snapshots>
			<enabled>false</enabled>
		</snapshots>
	</repository>
</repositories>
```



### 配置 gradle

```
repositories {
    maven {
        allowInsecureProtocol = true
        url = "http://localhost:8081/repository/maven-public/"
    }
    mavenCentral()
}
```





#### 重复 publish 问题

```
默认不允许重复deploy

方法1：删除之前的Directory
方法2：maven-release设置Deployment policy 为 Allow redeploy
```



#### 强制更新 dependencies

```
implementation ('org.example:demo-lib:1.0') { changing = true }

configurations.all {
    // Check for updates every build
    resolutionStrategy.cacheChangingModulesFor 0, 'seconds'
}
```

