基于MySQL增量日志解析

>[https://github.com/alibaba/canal/wiki/QuickStart](https://github.com/alibaba/canal/wiki/QuickStart)
1. 修改mysql配置
```plain
# 修改配置文件 /etc/mysql/mysql.conf.d/mysqld.cnf
log-bin=mysql-bin
binlog-format=ROW
server-id=1 # 不要和 canal 的 slaveId 重复
# 创建用户
create user canal@'%' IDENTIFIED by 'canal';
GRANT SELECT, REPLICATION SLAVE, REPLICATION CLIENT,SUPER ON *.* TO 'canal'@'%';
FLUSH PRIVILEGES;
```
2. 手动配置
```plain
docker pull canal/canal-server
docker run --name canal -p 11111:11111 -d canal/canal-server
# 配置文件 /home/admin/canal-server/conf/
## example/instance.properties
canal.instance.master.address=192.168.115.128:3306  // 指定数据库地址
canal.instance.filter.regex=redpackage\\..*     // 数据库和表
docker restart canal
```
3. 脚本启动
```plain
# 下载脚本
wget https://raw.githubusercontent.com/alibaba/canal/master/docker/run.sh
# 构建一个destination name为test的队列
sh run.sh -e canal.auto.scan=false \
-e canal.destinations=test \
-e canal.instance.master.address=192.168.115.128:3306  \
-e canal.instance.dbUsername=canal  \
-e canal.instance.dbPassword=canal  \
-e canal.instance.connectionCharset=UTF-8 \
-e canal.instance.tsdb.enable=true \
-e canal.instance.gtidon=false  \
-e canal.instance.filter.regex=redpackage\\..*
```
4. SpringBoot客户端
>[https://github.com/TonyLuo/canal-client](https://github.com/TonyLuo/canal-client)
```plain
<dependency>
<groupId>top.javatool</groupId>
<artifactId>canal-spring-boot-starter</artifactId>
<version>1.2.1-RELEASE</version>
</dependency>
```

