测试

```plain
repositories {
maven("https://maven.aliyun.com/repository/public/")
mavenCentral()
}
dependencies {
implementation(kotlin("stdlib-jdk8"))
testImplementation(platform("org.junit:junit-bom:5.7.0"))
testImplementation("org.junit.jupiter:junit-jupiter")
}
tasks.test {
useJUnitPlatform()
testLogging {
events("passed", "skipped", "failed")
}
}
```
序列化[https://github.com/Kotlin/kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization)
```plain
plugins {
kotlin("jvm") version "1.4.10"
kotlin("plugin.serialization") version "1.4.10"
}
dependencies {
implementation "org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1"
}
```
