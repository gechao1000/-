#### 修改时区

```shell
ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
```

#### 关闭SELINUX

```shell
vi /etc/sysconfig/selinux
SELINUX=disabled
:wq
```

#### 配置网卡(静态IP)

```shell
vim /etc/sysconfig/network-scripts/ifcfg-enp0s3
ONBOOT=yes
IPADDR=192.168.80.129
GATEWAY=192.168.80.2
DNS1=114.114.114.114
DNS2=8.8.8.8
NETMASK=255.255.255.0
:wq
service network restart

# 联网后安装ifconfig
yum -y install net-tools

# ntp服务器
yum -y install ntp ntpdate
ntpdate cn.pool.ntp.org
```

#### vim 	

```shell
vim /etc/vimrc 

# tab键默认4空格
set ts=4
set expandtab
set autoindent
```

#### firewalld

```shell
# 开启端口
firewall-cmd --zone=public --add-port=80/tcp --permanent

命令含义：
--zone #作用域
--add-port=80/tcp  #添加端口，格式为：端口/通讯协议
--permanent  #永久生效，没有此参数重启后失效

# 重启防火墙
firewall-cmd --reload

# 查看状态
firewall-cmd --state
```

#### 编译环境

> https://www.cnblogs.com/jixiaohua/p/11732225.html

```
# 基本环境
yum groupinstall "Development Tools"

# 升级gcc
sudo yum install centos-release-scl
sudo yum install devtoolset-8-gcc*
scl enable devtoolset-8 bash
```

```
# 编译安装gcc
make
make install PREFIX=/usr/local/redis 

cp redis.conf /usr/local/redis
ln -s /usr/local/redis/bin/redis-server /usr/local/bin
ln -s /usr/local/redis/bin/redis-cli /usr/local/bin

redis-server
redis-cli -h 地址 -p 端口 -a 密码
```

