[https://github.com/spotify/docker-maven-plugin](https://github.com/spotify/docker-maven-plugin)

```plain
<build>
<finalName>app</finalName>
<plugins>
<plugin>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-maven-plugin</artifactId>
</plugin>
<plugin>
<groupId>com.spotify</groupId>
<artifactId>docker-maven-plugin</artifactId>
<version>1.2.2</version>
<configuration>
<imageName>192.168.115.128:5000/${project.artifactId}:${project.version}</imageName>
<baseImage>openjdk:11-jre-slim</baseImage>
<entryPoint>["java", "-jar", "/${project.build.finalName}.jar"]</entryPoint>
<!-- copy the service's jar file from target into the root directory of the image -->
<resources>
<resource>
<targetPath>/</targetPath>
<directory>${project.build.directory}</directory>
<include>${project.build.finalName}.jar</include>
</resource>
</resources>
<dockerHost>http://192.168.115.128:2375</dockerHost>
</configuration>
</plugin>
</plugins>
</build>
```
打包上传
```plain
mvn install
mvn docker:build
mvn docker:push
```

