https://hub.docker.com/r/sonatype/nexus3
https://www.jianshu.com/p/edf57ba6a159

```
docker pull sonatype/nexus3
docker run -d -p 8081:8081 --name nexus sonatype/nexus3

用户名：admin
初始密码：/nexus-data/admin.password
```



添加阿里云maven代理

```
settings -> Repository -> Repositories
点击Create Repository
选择maven2(proxy)
Name字段aliyun-maven
URL 字段http://maven.aliyun.com/nexus/content/groups/public/
重新配置maven-public组，包含aliyun-maven，并且上移到第一位
```



## 配置 maven

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



## Gradle推送

https://docs.gradle.org/current/userguide/publishing_maven.html

https://blog.csdn.net/w_monster/article/details/117440340

https://docs.gradle.org/current/samples/sample_building_kotlin_libraries.html

https://developerlife.com/2021/02/06/publish-kotlin-library-as-gradle-dep/