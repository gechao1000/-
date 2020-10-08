#### 环境

> Sample：https://github.com/gradle/kotlin-dsl-samples

```
# 本地环境，gradle-wrapper.properties
distributionUrl=file:///D:/Gradle/gradle-6.6.1-all.zip

# 镜像
maven("https://maven.aliyun.com/repository/public/")

# android sdk
C:\Users\gecha\AppData\Local\Android\Sdk

# 创建项目
mkdir demo && cd demo
gradle init
```

#### 常用插件

> https://docs.gradle.org/current/userguide/plugin_reference.html

```
plugin 是 task 合集

plugins {
	kotlin("jvm") version "1.4.10"
	application
}

# kotlin 依赖
implementation(kotlin("stdlib"))
```

#### Task

```
// 自定义任务
task("xxx") {
	doFirst { ... }
}.dependsOn("yyy")

// 扩展任务
task("mydelete", Delete::class) {
	// Delete API
    setDelete("src/main/tmp")
}
```

