Gradle 由插件组成，Plugin 可以复用



定义

```groovy
class MyAwesomePlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.task("hello") {
            println "hello 123"

            doLast {
                println "do last"
            }
        }
    }
}
```



使用

```
apply plugin: MyAwesomePlugin

# 其它服务器
apply plugin: 'http://myserver.com/my-script'
```



本地 buildSrc

```
--- 创建buildSrc模块
定义 MyPlugin.java

--- 自动编译
apply plugin: Mylugin
```



第三方插件

```
buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
    	// 引入依赖
        classpath 'org.apache.commons:commons-csv:1.7'
    }
}

--- 使用
apply plugin: 'xx.yy.zz'

--- 如何查找 apply plugin: 'java'
C:\Program Files\Java\gradle-7.1\lib\plugins\gradle-plugins-7.1.jar
\META-INF\gradle-plugins\org.gradle.java.properties
implementation-class=org.gradle.api.plugins.JavaPlugin
```

