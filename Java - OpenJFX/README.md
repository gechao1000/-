教程

https://dev.to/cherrychain/javafx-jlink-and-jpackage-h9

示例

https://gitlab.com/lucaguada/treefx.

`jlink` 和 `jpackage` 

https://www.devdungeon.com/content/use-jpackage-create-native-java-app-installers




基础镜像 ubuntu + jdk + mvn

```
docker volume create m2
docker inspect m2
# 复制 settings.xml 到 m2实际目录
sudo cp ./settings.xml /var/lib/docker/volumes/m2/_data
# 查看 volume 实际大小
sudo du -h /var/lib/docker/volumes/m2/_data

---
下载 openjdk 和 maven
docker build -t gg .
```



构建环境

```
docker build -t myapp .

---
docker run -it --rm -v m2:/root/.m2 -v "$(pwd)":/dist myapp

mvn clean compile javafx:jlink jpackage:jpackage

复制deb到/dist目录
```



清除有问题的 image 和 container

```
docker system prune
```



java11 模块

```
java --list-modules
```





---

注意

JDK 最低 11

jpackage 打包 app-image失败





---

树莓派

https://pi4j.com/getting-started/





---

swing

https://www.guru99.com/java-swing-gui.html