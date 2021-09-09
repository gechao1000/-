https://services.gradle.org/

https://mirrors.huaweicloud.com/home



#### 镜像（全局） **~/.gradle/init.gradle**

```
allprojects{
	repositories {
		maven {
			url 'https://repo.huaweicloud.com/repository/maven/'
		}
	}
	buildscript {
		repositories {
			maven {
				url 'https://repo.huaweicloud.com/repository/maven/'
			}
		}
	}
}
```



#### 镜像（项目）

```
repositories {
    maven("https://maven.aliyun.com/repository/public/")
    mavenCentral()
}

repositories {
    maven {
        allowInsecureProtocol true
        url "http://10.120.130.165:8081/repository/maven-public/"
    }
    mavenCentral()
}
```



#### IDEA中文乱码问题

https://discuss.kotliner.cn/t/topic/1152/5

```
Help -> Edit Custom VM Options…
输入 ：-Dfile.encoding=UTF-8
```



#### 排除依赖

```
implementation('net.cnki.webx:webx-hfs:1.1.0') {
	exclude group: "net.java.dev.jna"
}
```



#### 引入本地 jar

```
implementation files("lib/webx-hfs-1.0.0.jar")
```



#### 打包为 shadowjar

```
plugins {
    id 'java'
    id 'application'
    id 'com.github.johnrengelman.shadow' version "7.0.0"
}

mainClassName = 'example.App'

compileJava {
    sourceCompatibility = '1.8'
    targetCompatibility = '1.8'
}
```

 