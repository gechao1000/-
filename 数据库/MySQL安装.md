## win7 X64位安装mysql-5.7


* 官网下载https://dev.mysql.com/downloads/file/?id=482487
	解压目录E:\DB\mysql-5.7.25
	bin添加到环境变量
* 修改my.ini

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

* 安装mysql服务
  1. 管理员身份打开cmd窗口后，将目录切换到你解压文件的bin目录
  2. mysqld install
  3. mysqld --initialize-insecure --user=mysql
  	建一个data文件夹，并且建好默认数据库，登录的用户名为root，密码为空
  4. net start mysql 启动服务

```mysql
grant all privileges on *.* to root@'%'identified by '123456';

flush privileges;

set global validate_password_policy=0;
set global validate_password_length=6;

select @@validate_password_length;

SHOW VARIABLES LIKE 'validate_password%';

set password=password("123456");
```

