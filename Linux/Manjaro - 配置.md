#### windows必备软件

* git, Github Desktop
* chrome, email, dropbox, qq(tim), dropbox
* vscode, idea, eclipse, postman
* 五笔输入法, 网易云音乐, Typora
* 数据库: redis, mongodb, mysql
* jdk, python, nodejs, nginx
* 下载工具, rar, office, ssh客户端, 西游vpn
* Soop工具: busybox, gow, ssh-copy-id, openssh, lazygit

## 环境搭建

##### 仓库

```
# 修改为国内镜像
sudo pacman-mirrors -i -c China -m rank

# 添加archlinuxcn源 /etc/pacman.conf
[archlinuxcn]
Server = https://mirrors.tuna.tsinghua.edu.cn/archlinuxcn/$arch

# https://github.com/archlinuxcn/mirrorlist-repo (所有国内镜像)
sudo pacman -S archlinuxcn-keyring

# AUR(Arch User Repository)助手：yay 
## 修改 aururl 
yay --aururl "https://aur.tuna.tsinghua.edu.cn" --save
## 修改的配置文件位于 ~/.config/yay/config.json ，还可通过以下命令查看修改过的配置
yay -P -g
```


##### 字体，输入法

```
# 中文字体
sudo pacman -S wqy-microhei wqy-zenhei

# 输入法 ibus (不好用)
sudo pacman -S ibus-rime

export GTK_IM_MODULE=ibus
export XMODIFIERS=@im=ibus
export QT_IM_MODULE=ibus
ibus-daemon -x -d
------------------------------------

# 输入法 fcitx
sudo pacman -S fcitx-im
sudo pacman -S fcitx-configtool
sudo pacman -S fcitx-googlepinyin

vim ~/.profile
export GTK_IM_MODULE=fcitx
export XMODIFIERS=@im=fcitx
export QT_IM_MODULE=fcitx

# 合计
sudo pacman -S wqy-microhei wqy-zenhei fcitx-im fcitx-configtool

echo -e "\nexport GTK_IM_MODULE=fcitx\nexport XMODIFIERS=@im=fcitx\nexport QT_IM_MODULE=fcitx\n" | tee -a ~/.profile
```

##### oh-my-zh

>  https://ohmyz.sh/ 

```
# 查看当前环境shell
echo $SHELL

# 查看系统自带哪些shell
cat /etc/shells

# 将zsh设置为默认shell
chsh -s /bin/zsh

# 自动安装
sh -c "$(wget https://raw.github.com/ohmyzsh/ohmyzsh/master/tools/install.sh -O -)"

# 手动安装
git clone git://github.com/robbyrussell/oh-my-zsh.git ~/.oh-my-zsh
cp ~/.oh-my-zsh/templates/zshrc.zsh-template ~/.zshrc

# 插件 oh-my-zsh/plugin/
git clone https://github.com/zsh-users/zsh-syntax-highlighting.git
vim ~/.zshrc
plugins=(其他的插件 zsh-syntax-highlighting)

# 主题 .oh-my-zsh/themes/
echo $ZSH_THEME
```

##### 常用软件

> 推荐： https://alim0x.gitbooks.io/awesome-linux-software-zh_cn/content/#arch-linux 

```
浏览器：firefox，chromuim，google-chrome，opera
下载工具：uget uget-integrator，qbittorrent
typora (导出需要pandoc)
obs-studio
file-roller unrar unzip p7zip 
ktouch (打字练习)
goldendict (词典)
steam-manjaro
calibre (电子书管理)
pdf阅读器: qpdfview(默认已安装)，foxit
git客户端: GitKraken(收费), Github Desktop
音乐播放器：foobar2000, audacious(windows有乱码)
视频播放器: mpv,vlc,SMPlayer
邮件: Evolution, Thunderbird
deepin-screenshot (深度截图) deepin-screen-capture

# 软件库没有
Freeplane (思维导图)
anoise (闭上眼睛听雨、听海、听自然)

# tim，不是原生
ysy -S deepin-wine-tim

# 微信AppImage (运行报错)
https://github.com/eNkru/freechat


# wps office（有bug）
sudo pacman -S wps-office 
sudo pacman -S ttf-wps-fonts ttf-ms-fonts wps-office-fonts wps-office-mime
```


##### Python (手动安装pip)

```
curl https://bootstrap.pypa.io/get-pip.py -o get-pip.py 
sudo python get-pip.py

# 升级 pip
python -m pip install --upgrade pip

# jupyter notebook配置
jupyter notebook --generate-config
修改默认存储目录 c.NotebookApp.notebook_dir = 'D:\\Projects\\Jupyter'
右键jupyter属性，去掉%USERPROFILE%参数
```

#### sdkman.io(似乎不需要)

```
# 默认安装目录$HOME/.sdkman
curl -s "https://get.sdkman.io" | bash

# 自定义安装目录
export SDKMAN_DIR="/usr/local/sdkman" && curl -s "https://get.sdkman.io" | bash

source "$HOME/.sdkman/bin/sdkman-init.sh"

sdk version

sdk install java
sdk install maven
```

dwm （动态窗口管理器，装逼用）

>  https://dwm.suckless.org/ 
>
> 与 xfce 冲突

```
git clone https://git.suckless.org/dwm
```

manjaro 安装 虚拟机

```
安装 libvirt, QEMU, OVMF, Virtual Machine Manager

systemctl start libvirtd
systemctl enable libvirtd

1. 大部分配置文件
git clone https://github.com/PassthroughPOST/Hackintosh-KVM.git

2. 复制文件(OVMF_CODE.fd	OVMF_VARS.fd)
https://github.com/kholia/OSX-KVM.git

[可选]
3. Mac上使用High Sierra Patcher工具，下载和创建安装盘
4. create_iso_highsierra.sh 在mac上执行，生成iso文件

5. 修改 Example-XML-Files(两个配置文件对应两个平台)
- 复制到上一级目录，可以重命名为hackintosh.xml
- 删除不需要的配置，<qemu:arg value='-object'/>	
- <loader readonly='yes' type='pflash'> <nvram> 改为本地目录
- <name> 改为 Hackintosh

6. 执行 virsh define hackintosh.xml，打开 Virtual Machine Manager
```


