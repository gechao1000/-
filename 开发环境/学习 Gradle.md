### Repository

1. ###### aliyun 镜像

    ```
    maven { url 'https://maven.aliyun.com/repository/public' }
    mavenLocal()
mavenCentral()
    
    maven { url 'https://maven.aliyun.com/repository/google' }
    maven { url 'https://maven.aliyun.com/repository/spring/'}
    ```

### Dependencies

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

