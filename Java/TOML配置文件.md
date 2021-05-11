https://github.com/LongTengDao/TOML



【官方】

https://toml.io/cn/

https://github.com/toml-lang/toml

【中文】

https://zhuanlan.zhihu.com/p/50412485





Java 解析

```
<dependency>
    <groupId>org.tomlj</groupId>
    <artifactId>tomlj</artifactId>
    <version>1.0.0</version>
</dependency>

Path source = Paths.get("src/main/resources","Journal.toml");
TomlParseResult result = Toml.parse(source);
System.out.println(result);
```

