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
```



### IDEA中文乱码问题

> https://discuss.kotliner.cn/t/topic/1152/5

```
Help -> Edit Custom VM Options…
输入 ：-Dfile.encoding=UTF-8
```

