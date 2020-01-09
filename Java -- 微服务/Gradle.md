#### plugins  代码块

>  https://plugins.gradle.org/ 

```
# Gradle官方插件仓库
plugins {
    id 'java-library'
    id 'maven-publish'
    id "com.google.protobuf" version "0.8.10"
}

# 使用“buildscript {}”块指定第三方库作为Gradle插件
apply plugin: "com.google.protobuf"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
或
sourceCompatibility = 1.8
targetCompatibility = 1.8
```

#### repository  代码块

>  https://developer.aliyun.com/mirror/maven 

```
mavenLocal()
mavenCentral()

maven { url 'https://maven.aliyun.com/repository/public' }
```

#### dependencies  代码块

1. ###### implementation、api 和 compile 区别

    -  在 3.0 后，废弃了 compile，使用 `implementation` 和 `api` 来代替 
    -  `api` 等同于 `compile`，意思是：我会把你暴露给第三方 
    -  `implementation` 意思是：我不会把你暴露给第三方 
    
    比如： 有三个 module，app、lib1、lib2，app 依赖于 lib1，lib1 依赖于 lib2 

    ```
    # 在 app 中是无法访问 lib2 的代码的

    // app/build.gradle
    implementation project(":lib1")

    // lib1/build.gradle
    implementation project(":lib2")
    ```

    ```
    # 在 app 中就可以访问 lib2 的代码了

    // app/build.gradle
    implementation project(":lib1")

    // lib1/build.gradle
    api project(":lib2")
    ```

2. lombok 依赖

    ```
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'

    # Idea 配置
    "Settings > Build > Compiler > Annotation Processors"
    ```

3. BOM 配置

    ```
    implementation(platform("org.springframework.boot:spring-boot-dependencies:2.1.4.RELEASE"))
    或
    implementation(enforcedPlatform("org.springframework.boot:spring-boot-dependencies:2.1.4.RELEASE"))
    ```

#### buildscript  代码块

> 声明是gradle脚本自身需要使用的资源。可以声明的资源包括依赖项、第三方插件、maven仓库地址等

1. 比如：解析 csv文件

```groovy
buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        maven { url "https://plugins.gradle.org/m2/" }
    }

    dependencies {
        classpath 'org.apache.commons:commons-csv:1.7'
    }
}

import org.apache.commons.csv.CSVFormat

task printCSV() {
    doLast {
        def records = CSVFormat.EXCEL.parse(new FileReader('config/sample.csv'))
        for (item in records) {
            print item.get(0) + ' '
            println item.get(1)
        }

    }
}
```

####  **allprojects**  代码块

>  用于多项目构建，为所有项目提供共同所需依赖包 

```
allprojects {
    repositories {
    	...
    }
    dependencies {
    	...
    }
}

subprojects {
	...
}
```

