私有云 OpenMediaVault

> https://github.com/sjf0213/rpi/blob/master/OpenMediaVault/

```
wget -O - https://github.com/OpenMediaVault-Plugin-Developers/installScript/raw/master/install | sudo bash


首次登陆wen管理用户名和密码不是安装时输入的root账户密码，root账户用在控制台。
默认web管理密码
用户名：admin
密码：openmediavault
```

镜像

> https://mirror.tuna.tsinghua.edu.cn/help/raspbian/

```
> vim /etc/apt/sources.list
deb http://mirrors.tuna.tsinghua.edu.cn/raspbian/raspbian/ buster main non-free contrib rpi
deb-src http://mirrors.tuna.tsinghua.edu.cn/raspbian/raspbian/ buster main non-free contrib rpi

> vim /etc/apt/sources.list.d/raspi.list
deb http://mirrors.tuna.tsinghua.edu.cn/raspberrypi/ buster main ui
```

配置

```
sudo raspi-config

# 查看cpu型号
lscpu

# 时区
ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

# 设置 Locale
> vim /etc/locale.gen （取消注释）
en_US.UTF-8 UTF-8
zh_CN.UTF-8 UTF-8
zh_TW.UTF-8 UTF-8
> locale-gen
> vim /etc/locale.conf
LANG=zh_CN.UTF-8

> vim /etc/hosts

> vim /etc/resolv.conf
nameserver 223.5.5.5
nameserver 223.6.6.6
```

