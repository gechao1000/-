[https://github.com/spotify/dockerfile-maven](https://github.com/spotify/dockerfile-maven)

参考示例

[http://blog.geekidentity.com/java/maven/dockerfile-maven-cn/](http://blog.geekidentity.com/java/maven/dockerfile-maven-cn/)

[https://www.cnblogs.com/zyon/p/11266952.html](https://www.cnblogs.com/zyon/p/11266952.html)

```plain
# 集成到Maven构建过程中
mvn clean package  # 本地构建image
mvn deploy           # 推送image
# 单独使用
mvn dockerfile:build    -> package阶段
mvn dockerfile:tag      -> package阶段
mvn dockerfile:push     -> deploy阶段
# 默认push地址
~/.docker/config.json文件中的配置
```
1. pom.xml配置
```plain
<docker.image.prefix>bmcp</docker.image.prefix>
<!-- 镜像构建、推送插件-->
<plugin>
<groupId>com.spotify</groupId>
<artifactId>dockerfile-maven-plugin</artifactId>
<version>1.4.13</version>
<executions>
<execution>
<id>default</id>
<goals>
<goal>build</goal>
<goal>push</goal>
</goals>
</execution>
</executions>
<configuration>
<repository>${docker.image.prefix}/${project.artifactId}</repository>
<tag>${project.version}</tag>
<buildArgs>
<JAR_FILE>${project.build.finalName}.jar</JAR_FILE>
</buildArgs>
</configuration>
</plugin>
```
说明

```plain
tag元素指定镜像的tag，这里使用Maven模块的版本号
可以使用dockerfile元素指定Dockerfile的位置，上例中为和POM在同级目录
buildArgs元素指定了传递给Dockerfile的参数
```
1. Dockerfile
```plain
FROM openjdk:8-jre-slim
MAINTAINER David Flemström <dflemstr@spotify.com>
ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ARG JAR_FILE
ADD target/${JAR_FILE} /app.jar
# Add Maven dependencies (not shaded into the artifact; Docker-cached)
# ADD target/lib           /usr/share/myservice/lib
# Add the service itself
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
# ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /app.jar"]
```
