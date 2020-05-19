## MySQL衍生版本 percona

https://www.percona.com/software/mysql-database 

提升了高负载下InnoDB的性能，性能诊断工具

```
docker pull percona:5.7

./data 需要w权限
```



## windows

##### 1. 下载安装包

https://dev.mysql.com/downloads/file/?id=482487

解压目录E:\DB\mysql-5.7.25，bin添加到环境变量

##### 2. 修改my.ini

```
[mysql]  

#设置mysql客户端默认字符集  
default-character-set=utf8  

[mysqld]  

#设置3306端口  
port = 3306   

#设置mysql的安装目录  
basedir=E:\DB\mysql-5.7.25

#设置mysql数据库的数据的存放目录  
datadir=E:\DB\mysql-5.7.25\data  

#允许最大连接数  
max_connections=200  

#服务端使用的字符集默认为8比特编码的latin1字符集  
character-set-server=utf8  

#创建新表时将使用的默认存储引擎  
default-storage-engine=INNODB  

#允许执行导入导出命令
secure_file_priv=''
```

##### 3. 安装mysql服务

管理员身份打开cmd窗口后，切换到bin目录

```
mysqld install

# 建一个data文件夹，并且建好默认数据库，登录的用户名为root，密码为空
mysqld --initialize-insecure --user=mysql

# 启动服务
net start mysql 
```

##### 4. 修改密码

```mysql
grant all privileges on *.* to root@'%'identified by '123456';

flush privileges;

set global validate_password_policy=0;
set global validate_password_length=6;

select @@validate_password_length;

SHOW VARIABLES LIKE 'validate_password%';

set password=password("123456");
```

