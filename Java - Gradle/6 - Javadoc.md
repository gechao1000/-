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

