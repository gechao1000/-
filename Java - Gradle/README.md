https://services.gradle.org/

https://mirrors.huaweicloud.com/home



### 镜像（全局设置）

**~/.gradle/init.gradle**

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



### 镜像（项目设置）

```
repositories {
    maven("https://maven.aliyun.com/repository/public/")
    mavenCentral()
}

repositories {
    mavenCentral()
    maven {
        allowInsecureProtocol true
        url "http://10.120.130.165:8081/repository/maven-public/"
    }
}
```



### IDEA中文乱码问题

> https://discuss.kotliner.cn/t/topic/1152/5

```
Help -> Edit Custom VM Options…
输入 ：-Dfile.encoding=UTF-8
```



#### Java版本

```
compileJava {
    sourceCompatibility = '1.8'
    targetCompatibility = '1.8'
}
```



排除依赖

```
implementation('net.cnki.webx:webx-hfs:1.1.0') {
	exclude group: "net.java.dev.jna"
}
```

