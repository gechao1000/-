[https://www.programiz.com/kotlin-programming](https://www.programiz.com/kotlin-programming)


[https://learnxinyminutes.com/docs/zh-cn/kotlin-cn/](https://learnxinyminutes.com/docs/zh-cn/kotlin-cn/)



### 序列化

https://github.com/Kotlin/kotlinx.serialization

```kotlin
plugins {
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.10"
}
dependencies {
	implementation "org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1"
}
```



### Kotlin 文档注释

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

Java 文档注释

```
/**
 * obtaining the absolute value (or magnitude) of a number
 * @param number 整数
 * @return 非负数
 */
int abs(int number);
    
IDEA工具栏 -> Tools -> Generate Javadoc
设置参数（Other command line arguments）：-encoding utf-8 -charset utf-8
```



### 案例

1. todo-app

传统 MVP  

https://github.com/android/architecture-samples

2. ReKotlin

https://github.com/ReKotlin/ReKotlin

3. Kotlin 核心编程 源码

https://github.com/DiveIntoKotlin/DiveIntoKotlinSamples

4. Spring5 响应式

https://github.com/godpan/reactive-spring-kotlin-app