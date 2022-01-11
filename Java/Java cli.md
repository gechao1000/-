普通 jar 包

```
--- 没有依赖
java -cp starter.jar example.App


--- 有依赖
java -cp "starter-1.0-SNAPSHOT.jar;C:\Users\gecha\.m2\repository\com\vdurmont\emoji-java\5.1.1\emoji-java-5.1.1.jar;C:\Users\gecha\.m2\repository\org\json\json\20170516\json-20170516.jar" example.App
```



Maven 对项目打包

https://www.jianshu.com/p/14bcb17b99e0

| **plugin**            | **function**                                   |
| --------------------- | ---------------------------------------------- |
| maven-jar-plugin      | maven 默认打包插件，用来创建 project jar       |
| maven-shade-plugin    | 用来打可执行包，executable(fat) jar            |
| maven-assembly-plugin | 支持定制化打包方式，例如 apache 项目的打包方式 |



纯命令行

https://github.com/remkop/picocli

https://segmentfault.com/a/1190000039730744



方式1：SpringBoot  

```
public class DemoApplication implements CommandLineRunner
```



方式2：Airline

```xml
https://github.com/airlift/airline
<dependency>
    <groupId>io.airlift</groupId>
    <artifactId>airline</artifactId>
    <version>0.8</version>
</dependency>


https://rvesse.github.io/airline/guide/
> forked from airlift/airline
<dependency>
    <groupId>com.github.rvesse</groupId>
    <artifactId>airline</artifactId>
    <version>2.8.1</version>
</dependency>
```



方式3：  maven-shade-plugin 

> https://www.jianshu.com/p/7a0e20b30401
>
> https://cloud.tencent.com/developer/article/1622207
>
> https://run-zheng.github.io/2019/11/06/maven-shade-plugin/

```xml
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-shade-plugin</artifactId>
	<version>2.4.3</version>
	<configuration>
		<!-- put your configurations here -->
		<transformers>
			<transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
				<mainClass>cli.CheckSum</mainClass>
			</transformer>
		</transformers>
		<artifactSet>
			<includes>
				<include>*:*</include>
			</includes>
			<excludes>
				<exclude>org.projectlombok:lombok</exclude>
			</excludes>
		</artifactSet>
	</configuration>
	<executions>
		<execution>
			<phase>package</phase>
			<goals>
				<goal>shade</goal>
			</goals>
		</execution>
	</executions>
</plugin>
```



方式4：maven-jar-plugin,  mave-shade-plugin		（效果和方式3相同）

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-jar-plugin</artifactId>
    <version>3.2.0</version>
    <configuration>
        <archive>
            <manifest>
                <mainClass>org.sonatype.haven.HavenCli</mainClass>
            </manifest>
        </archive>
    </configuration>
</plugin>

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <version>2.4.3</version>
    <configuration>
        <!-- put your configurations here -->
    </configuration>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>shade</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```



方式5：maven-assembly-plugin		（效果和方式3相同，可以使用xml 配置）

> https://maven.apache.org/plugins/maven-assembly-plugin/usage.html
>
> https://ktor.io/docs/maven-assembly-plugin.html   （Maven + Gradle）

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-assembly-plugin</artifactId>
    <version>3.3.0</version>
    <configuration>
        <descriptorRefs>
            <descriptorRef>jar-with-dependencies</descriptorRef>
        </descriptorRefs>
        <archive>
            <manifest>
                <mainClass>com.example.ApplicationKt</mainClass>
            </manifest>
        </archive>
    </configuration>
    <executions>
        <execution>
            <id>make-assembly</id>
            <phase>package</phase>
            <goals>
                <goal>single</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

