引入

```kotlin
allprojects {
    repositories {
        maven {
            isAllowInsecureProtocol = true
//            setUrl("http://192.168.80.80:8081/repository/maven-public/")
            url = uri("http://192.168.80.80:8081/repository/maven-public/")
        }
    }
}
```



发布

https://docs.gradle.org/current/userguide/publishing_maven.html

```kotlin
plugins {
    kotlin("jvm") version "1.6.10"
    `java-library`
    `maven-publish`
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "hfs-jna"
            from(components["java"])
        }
    }
    repositories {
        val releasesRepoUrl = "http://192.168.80.80:8081/repository/maven-releases/"
        val snapshotsRepoUrl = "http://192.168.80.80:8081/repository/maven-snapshots/"

        maven {
            isAllowInsecureProtocol = true
//            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
            url = uri(if (project.hasProperty("release")) releasesRepoUrl else snapshotsRepoUrl)
            credentials {
                username = "admin"
                password = "admin"
            }
        }
    }
}
```



