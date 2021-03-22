1. Swaager url地址，json文件，yaml文件
2. 生成RestTemplate
3. 生成Retrofit Client
4. 用户名密码登录，ip登录





参考资料

```
https://zhuanlan.zhihu.com/p/139145504
https://petstore.swagger.io/v2/swagger.json
https://mvnrepository.com/artifact/io.swagger/swagger-codegen-cli/2.4.19
```





测试

> https://github.com/swagger-api/swagger-codegen/releases

```shell
docker run --rm -v ${PWD}:/local swaggerapi/swagger-codegen-cli-v3:latest generate \
 -i /local/resources/3_0_0/petstore-with-fake-endpoints-models-for-testing.yaml \
 -l java \
 -c /local/bin/java-petstore-feign.json \
 -o /local/samples/client/petstore/java/feign \
 -DhideGenerationTimestamp=true

java -jar swagger-codegen-cli.jar generate \
 -i resources/3_0_0/petstore-with-fake-endpoints-models-for-testing.yaml \
 -l java \
 -c bin/java-petstore-feign.json \
 -o samples/client/petstore/java/feign \
 -DhideGenerationTimestamp=true
```

Maven 插件

> https://github.com/swagger-api/swagger-codegen/tree/master/modules/swagger-codegen-maven-plugin
>

使用方式：mvn clean compile，生成的代码在${project.build.directory}/generated-sources/swagger

```xml
<plugin>
    <groupId>io.swagger.codegen.v3</groupId>
    <artifactId>swagger-codegen-maven-plugin</artifactId>
    <version>3.0.0</version>
    <executions>
        <execution>
            <goals>
                <goal>generate</goal>
            </goals>
            <configuration>
                <inputSpec>src/main/resources/api.yaml</inputSpec>
                <language>java</language>
                <configOptions>
                   <sourceFolder>src/gen/java/main</sourceFolder>
                   <!-- 默认使用jackson.threetenbp -->
                   <dateLibrary>joda</dateLibrary>
                </configOptions>
                <library>jersey2</library>
            </configuration>
        </execution>
    </executions>
</plugin>
```

必须依赖

```xml
<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-annotations</artifactId>
    <version>1.5.20</version>
</dependency>
或者
<dependency>
    <groupId>io.swagger.core.v3</groupId>
    <artifactId>swagger-annotations</artifactId>
    <version>2.0.2</version>
</dependency>
```

序列化，可以选择jackson、gson

```xml
<jackson-version>2.9.5</jackson-version>
<jodatime-version>2.7</jodatime-version>

<!-- JSON processing: jackson -->
<dependency>
    <groupId>com.fasterxml.jackson.jaxrs</groupId>
    <artifactId>jackson-jaxrs-base</artifactId>
    <version>${jackson-version}</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
    <version>${jackson-version}</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-annotations</artifactId>
    <version>${jackson-version}</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>${jackson-version}</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.jaxrs</groupId>
    <artifactId>jackson-jaxrs-json-provider</artifactId>
    <version>${jackson-version}</version>
</dependency>

<!-- Joda time: if you use it -->
<dependency>
    <groupId>com.fasterxml.jackson.datatype</groupId>
    <artifactId>jackson-datatype-joda</artifactId>
    <version>${jackson-version}</version>
</dependency>
<dependency>
    <groupId>joda-time</groupId>
    <artifactId>joda-time</artifactId>
    <version>${jodatime-version}</version>
</dependency>
```



可选依赖，取决于http-client

```xml
<spring-web-version>4.3.9.RELEASE</spring-web-version>

<!-- HTTP client: Spring RestTemplate -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-web</artifactId>
    <version>${spring-web-version}</version>
</dependency>
<dependency>
    <groupId>javax.annotation</groupId>
    <artifactId>javax.annotation-api</artifactId>
    <version>1.3.2</version>
</dependency>
```

替换

```
import io.swagger.client.

import com.example.demo.ecp.
```