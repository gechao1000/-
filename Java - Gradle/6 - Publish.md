https://docs.gradle.org/current/userguide/publishing_maven.html

https://blog.csdn.net/w_monster/article/details/117440340

https://docs.gradle.org/current/samples/sample_building_kotlin_libraries.html

https://developerlife.com/2021/02/06/publish-kotlin-library-as-gradle-dep/



```
plugins {
    id 'java-library'
    id 'maven-publish'
}

group 'org.example'
//version '1.0-SNAPSHOT'
version '1.0-RELEASE'

repositories {
    mavenCentral()
}

dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
    api 'net.java.dev.jna:jna:5.9.0'
}

test {
    useJUnitPlatform()
}

compileJava {
    sourceCompatibility = 1.8
    targetCompatibility = 1.8
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


publishing {
    publications {
        maven(MavenPublication) {
            artifactId = 'g1-lib'
            from components.java
        }
    }
    repositories {
        maven {
//            url = layout.buildDirectory.dir('repo')
            allowInsecureProtocol = true
            url = "http://localhost:8081/repository/maven-releases/"

            credentials {
                username = "admin"
                password = "admin"
            }
        }

    }
}
```



问题

```
如果是 implement 'net.java.dev.jna:jna:5.9.0'

在g2中导入g1-lib，只有runtime可以调用jna
```



javadoc 中文乱码

```
tasks.withType(JavaCompile) {
    options.encoding = "UTF-8"
}

tasks.withType(Javadoc) {
    options.encoding = "UTF-8"
}
```



