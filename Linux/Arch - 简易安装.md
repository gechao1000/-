#### 完成流程

```
https://ywnz.com/linuxaz/6753.html

1. 检查网络，修改mirror
2. 安装wget

wget archfi.sf.net/archfi
sh archfi


注意事项
1. 不理解选项什么意思就默认
2. 一定要选择vim和dhcpcd
```


#### 镜像源，网络，时区

```
> vim /etc/pacman.d/mirrorlist
Server = http://mirrors.aliyun.com/archlinux/$repo/os/$arch

> vim /etc/hosts
199.232.68.133 raw.githubusercontent.com

> vim /etc/resolv.conf
nameserver 223.5.5.5
nameserver 223.6.6.6

> pacman -S ntp
ntpdate ntp.aliyun.com
```

#### 配置ssh

```
> pacman -S openssh

# 允许root远程登录
> vim /etc/ssh/sshd_config
PermitRootLogin yes
```


