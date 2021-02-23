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
# 编辑 `/etc/apt/sources.list` 文件，删除原文件所有内容，用以下内容取代：
deb http://mirrors.tuna.tsinghua.edu.cn/raspbian/raspbian/ buster main non-free contrib rpi
deb-src http://mirrors.tuna.tsinghua.edu.cn/raspbian/raspbian/ buster main non-free contrib rpi

# 编辑 `/etc/apt/sources.list.d/raspi.list` 文件，删除原文件所有内容，用以下内容取代：
deb http://mirrors.tuna.tsinghua.edu.cn/raspberrypi/ buster main ui
```

