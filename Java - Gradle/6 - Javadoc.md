使用详情

https://blog.csdn.net/vbirdbest/article/details/80296136

http://www.drjava.org/docs/user/ch10.html



gradle 集成

```
plugins {
    id 'java-library'
}

java {
    withSourcesJar()
    withJavadocJar()
}

// javadoc 中文乱码问题
tasks.withType(JavaCompile) {
    options.encoding = "UTF-8"
}

tasks.withType(Javadoc) {
    options.encoding = "UTF-8"
}
```



IDEA 生成 javadoc  

```
【Tools】 => Generate Javadoc
选择输出路径
Other command line arguments：-encoding utf-8 -charset utf-8
```



IDEA 插件 javadoc

```
# Java
https://plugins.jetbrains.com/plugin/7157-javadoc
https://plugins.jetbrains.com/plugin/12977-easy-javadoc	

# Kotlin
https://plugins.jetbrains.com/plugin/14778-kdoc-er--kotlin-doc-generator
```



Kotlin 生成文档

```
https://github.com/Kotlin/dokka

id("org.jetbrains.dokka") version "1.4.30"
```

