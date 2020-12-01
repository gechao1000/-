## 自由风格软件项目 FreeStyle

>拉取项目 -> 编译 -> 打包 -> 部署
```plain
# 安装Deploy to container插件，实现远程部署Tomcat
添加tomcat凭证
# 当前项目 -> 构建后操作 -> Deploy war/jar to container
target/*.war
添加container, 选择凭证，输入http://192.168.115.141:8080/
立刻构建
```
## Maven 项目

```plain
# 安装 Maven Integration 插件
拉取代码和远程部署参考freestyle
# 构建部分
指定pom.xml
maven命令，不需要写mvn：clean package
```
## 流水线项目 （最灵活）

>工作流框架，编排任务
