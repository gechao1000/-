本地环境

```plain
export JAVA_HOME=/opt/jdk1.8.0_261
export MAVEN_HOME=/opt/apache-maven-3.6.3
export PATH=${JAVA_HOME}/bin:${MAVEN_HOME}/bin:$PATH
```
安装
```plain
# 配置文件
vim /etc/sysconfig/jenkins
JENKINS_USER="root"
JENKINS_PORT="8888"
# 启动
systemctl start jenkins
```
管理凭证
```plain
# Credentials Binding插件
系统管理 -> 安全 -> 凭据
全局凭据 -> 添加凭据
用户名密码 / SSH Keys
ssh-keygen -t rsa
gitlab保存公钥，jenkins保存私钥
```
创建任务（自由风格）
```plain
选择git 源码管理，选择凭据
应用，保存
立即构建，查看控制台输出
# 源码存放目录
/var/lib/jenkins/workspace
# 构建一个Maven项目
# 源码管理，选择git
# 指定pom.xml
# 命令 clean package docker:build
```
配置Maven和JDK
```plain
# 系统管理 -> 全局工具配置
新增JDK, 不勾选自动安装
新增MAVEN, 不勾选自动安装
# 系统管理 -> 系统配置 -> 全局属性
添加环境变量
JAVA_HOME=/opt/jdk1.8.0_261
MAVEN_HOME=/opt/apache-maven-3.6.3
PATH+EXTRA=$MAVEN_HOME/bin
```
测试MAVEN构建
```plain
当前任务 -> 配置 -> 构建
增加构建步骤 -> 执行shell
mvn clean package
mvn clean package -Dmaven.test.skip=true
立即构建
```
