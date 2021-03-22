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

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <version>2.4.3</version>
    <configuration>
        <!-- put your configurations here -->
        <transformers>
            <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                <mainClass>org.sonatype.haven.HavenCli</mainClass>
            </transformer>
        </transformers>
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



方式4：maven-jar-plugin,  mave-shade-plugin



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

