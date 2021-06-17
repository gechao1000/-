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

# 联网后安装
yum -y install net-tools
netstat -anultp | grep kbase
```

#### epel（软件版本太旧了，不推荐，不如用源码安装）

```
wget -O /etc/yum.repos.d/epel.repo http://mirrors.aliyun.com/repo/epel-7.repo
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

#### NTP

```
yum -y install ntpdate
date	# 查看当前时间

# 同步
ntpdate ntp.aliyun.com
# 写入硬盘
hwclock -w
```

#### RPM软件包

```
rpm -ivh aaa.rpm
rpm -e aaa

rpm -qa | grep aaa
rpm -ql aaa
```

#### 安装最新版 Git

> https://computingforgeeks.com/how-to-install-latest-version-of-git-git-2-x-on-centos-7/

```
yum remove git*

yum -y install https://packages.endpoint.com/rhel/7/os/x86_64/endpoint-repo-1.7-1.x86_64.rpm

yum install git
```

#### 安装 Nginx

> https://www.nginx.com/resources/wiki/start/topics/tutorials/install/

```
$ /etc/yum.repos.d/nginx.repo
[nginx]
name=nginx repo
baseurl=https://nginx.org/packages/centos/$releasever/$basearch/
gpgcheck=0
enabled=1
```



#### 编译环境

> https://www.cnblogs.com/jixiaohua/p/11732225.html

```
# 默认环境（gcc版本4.8）
yum groupinstall "Development Tools"

# 高版本 (安装目录/opt/rh/devtoolset-*)
yum install -y centos-release-scl
yum install -y devtoolset-8
scl enable devtoolset-8 bash	(本次会话有效)
```

#### 编译安装redis（需要高版本gcc）
```
wget https://download.redis.io/releases/redis-6.0.9.tar.gz

# 编译安装
make
make install PREFIX=/usr/local/redis 

cp redis.conf /usr/local/redis
ln -s /usr/local/redis/bin/redis-server /usr/local/bin
ln -s /usr/local/redis/bin/redis-cli /usr/local/bin

redis-server
redis-cli -h 地址 -p 端口 -a 密码
```

#### 编译安装vim（没成功过）

```
# yum 安装
yum install vim-enhanced

# 源码安装
https://www.cnblogs.com/yxhblogs/p/8971964.html
## 依赖
yum -y remove vim*  （谨慎操作，sudo命令依赖vim-minimal）
yum -y install ncurses-devel 
## 编译
./configure --prefix=/opt/vim8
make
make install 
## 环境变量
export PATH=/opt/vim8/bin:$PATH
```

#### 编译openresty

```
# 编译lua (可选)，https://www.lua.org/download.html
make linux test
make install

# 源码
wget https://openresty.org/download/openresty-1.17.8.2.tar.gz

# 依赖
yum install -y pcre-devel openssl-devel
# opm依赖
yum install -y perl-Digest-MD5

# 编译安装，默认安装到/usr/local/openresty
./configure
make
make install

# 环境变量
export PATH=/usr/local/openresty/bin:$PATH
```

#### 安装AppImage软件

```
# 使用FUSE（依赖 epel-release）
yum install fuse fuse-libs ack -y

# 不使用FUSE
./nvim.appimage --appimage-extract
./squashfs-root/AppRun --version
```

