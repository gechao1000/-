###  初始化

```
git clone https://hub.fastgit.org/gechao1000/-.git

# 修改为国内镜像（清华源）
sudo pacman-mirrors -i -c China -m rank

# 添加archlinuxcn源
sudo pacman -S archlinuxcn-keyring
```

###### 配置文件 `/etc/pacman.d/mirrorlist`

```
## Country : China
Server = https://mirrors.tuna.tsinghua.edu.cn/manjaro/stable/$repo/$arch

## Country : China
Server = https://mirrors.tuna.tsinghua.edu.cn/manjaro/stable/$repo/$arch
```

###### 配置文件 `/etc/pacman.conf`

```
[archlinuxcn]
Server = https://mirrors.tuna.tsinghua.edu.cn/archlinuxcn/$arch
```



### 开发环境

> https://mirrors.huaweicloud.com/

```
sudo pacman -S base-devel

# 卸载自带的JDK
sudo pacman -R jdk8-openjdk
sudo pacman -R jre8-openjdk
sudo pacman -R jre8-openjdk-headless

# JAVA
sudo pacman -S jdk-openjdk
sudo pacman -S jdk8-openjdk
sudo pacman -S gradle maven

# Rust
sudo pacman -S rust
sudo pacman -S gcc automake autoconf libtool make cmake

# Nodejs
sudo pacman -S nodejs npm 
或
sudo pacman -S yarn

# Python
sudo pacman -S python python-pip
pip config set global.index-url https://pypi.tuna.tsinghua.edu.cn/simple

# 打包
sudo pacman -S dpkg rpm-tools
```



### 常用软件

```
sudo pacman -S which nerd-fonts

sudo pacman -S neofetch typora gitg
sudo pacman -S visual-studio-code-bin

# 虚拟机
sudo pacman -S virtualbox linux510-virtualbox-host-modules
sudo gpasswd -a $USER vboxusers

sudo pacman -S thunar-shares-plugin
sudo pacman -S docker docker-compose

# VIM
git config --global url."https://hub.fastgit.org/".insteadOf "https://github.com/"
git clone https://github.com/chxuan/vimplus.git ~/.vimplus

# 词典
sudo pacman -S goldendict （需要手动添加词典）
https://blog.yuanbin.me/posts/2013-01/2013-01-31_23-07-00/

sudo pacman -S gimp （默认安装）

AUR 安装 albert （启动器，手动绑定F12）
```



### 输入法

https://zhuanlan.zhihu.com/p/341637818

```
sudo pacman -S fcitx5-im fcitx5-chinese-addons fcitx5-pinyin-zhwiki
sudo pacman -S manjaro-asian-input-support-fcitx5（环境变量，必要）
sudo pacman -S fcitx5-material-color（皮肤，不必要）

切换输入法：Ctrl + Space
```



### QT

https://zhuanlan.zhihu.com/p/343705305		https://wiki.archlinux.org/title/Qt_(简体中文)

```
sudo pacman -S qtcreator
# 安装模块（报错Qt5WebSockets）
sudo pacman -S qt5-websockets

# 版本
Qt 5.15.2
Qt Creator 4.15.0
GCC 11.1.0
Cmake 3.20.3

# 项目
https://github.com/wang-bin/QtAV
https://github.com/WizTeam/WizQTClient
https://github.com/xmuli/QtExamples

# Deepin 配置
https://xmuli.blog.csdn.net/article/details/112239518

# 中文输入问题
https://forum.qt.io/topic/99146/can-t-input-chinese-in-qplaintextedit/8
pacman -Qi fcitx5-qt
pacman -Qi fcitx-qt5
```



### Pacman 命令

> https://baijiahao.baidu.com/s?id=1607067777258136232&wfr=spider&for=pc

```
# 更新软件列表
sudo pacman -Syy

# 更新软件列表和软件
sudo pacman -Syu

# 搜索，安装
pacman -Ss XXX
pacman -Si XXX
sudo pacman -S XXX

# 查看已安装
pacman -Q
pacman -Qi XXX
pacman -Ql XXX

# 卸载
sudo pacman -Rs XXX


# 打包
makepkg
# 安装
sudo pacman -U xxx.pkg.tar.xz
```