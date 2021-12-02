
mvn 插件 javafx-maven-plugin

https://github.com/openjfx/javafx-maven-plugin



```
--- 默认配置
mvn clean javafx:jlink
./java -m com.example.demo/com.example.demo.HelloApplication

--- 自定义配置
<configuration>
  <launcher>start</launcher>
  <compress>2</compress>
  <jlinkZipName>gol-sim</jlinkZipName>
  <jlinkImageName>gol-sim</jlinkImageName>
  <noManPages>true</noManPages>
  <noHeaderFiles>true</noHeaderFiles>
</configuration>
```







https://plugins.gradle.org/plugin/org.beryx.jlink

https://badass-jlink-plugin.beryx.org/releases/latest/