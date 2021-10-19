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

/**
 * obtaining the absolute value (or magnitude) of a number
 * @param number 整数
 * @return 非负数
 */
int abs(int number);
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

https://github.com/Kotlin/dokka

```kotlin
plugins {
    kotlin("jvm") version "1.5.10"
    application
    id("org.jetbrains.dokka") version "1.4.30"
}

tasks.dokkaHtml.configure {
    outputDirectory.set(buildDir.resolve("dokka"))
}

/**
 * HFS 文件描述
 *
 * @property name 文件名
 * @property type 后缀名
 * @property size 文件大小
 * @property dirname 目录
 * @property cDate 创建日期
 * @property mDate 修改日期
 * @constructor Create empty File info
 */
data class FileInfo (
    val name: String,
    val type: String,
    val size: Long,
    val dirname: String,
    val cDate: String,
    val mDate: String,
)
```

