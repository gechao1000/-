#### windows必备软件

* git, Github Desktop
* chrome, email, dropbox, qq(tim), dropbox
* vscode, idea, eclipse, postman
* 五笔输入法, 网易云音乐, Typora
* 数据库: redis, mongodb, mysql
* jdk, python, nodejs, nginx
* 下载工具, rar, office, ssh客户端, 西游vpn

## 环境搭建

##### 仓库

```
# 修改为国内镜像
sudo pacman-mirrors -i -c China -m rank

# 添加archlinuxcn源
echo -e "\n[archlinuxcn]\nSigLevel = TrustAll\nServer = https://mirrors.tuna.tsinghua.edu.cn/archlinuxcn/\$arch\n\n[antergos]\nSigLevel = TrustAll\nServer = https://mirrors.tuna.tsinghua.edu.cn/antergos/\$repo/\$arch\n" | sudo tee -a /etc/pacman.conf

# 卸载libreoffice
sudo pacman -Rc libreoffice-still

# 更新软件列表和软件
sudo pacman -Syy
sudo pacman -Syu

# 安装密钥
sudo pacman -S archlinuxcn-keyring antergos-keyring

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

# 输入法
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

##### Python

```
curl https://bootstrap.pypa.io/get-pip.py -o get-pip.py 
sudo python get-pip.py

python -m pip install --upgrade pip
pip config set global.index-url https://pypi.tuna.tsinghua.edu.cn/simple

sudo pacman -S pycharm

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

##### Java

```
sudo pacman -S jdk8-openjdk
sudo pacman -S intellij-idea-ultimate-edition
sudo pacman -S eclipse
```

##### 常用软件

```
sudo pacman -S typora (导出需要pandoc)
sudo pacman -S google-chrome
sudo pacman -S deepin-screenshot
sudo pacman -S wps-office ttf-wps-fonts
sudo pacman -S netease-cloud-music
sudo pacman -S file-roller unrar unzip p7zip 
ysy -S deepin-wine-tim

pdf阅读器: foxit
git客户端: GitKraken(收费), Github Desktop
视频播放器: mpv,vlc,SMPlayer
邮件: Evolution, Thunderbird

vscode插件: Bracket Pair Colorizer, One Monokai Theme, Visual Studio IntelliCode, 
	Sublime Importer for VS Code, Web Template Studio
```

数据库postgres

```
使用命令sudo pacman -S postgresql 安装

使用psql postgres 进入superuser

使用\password postgres 设定密码（用户名：postgres；密码：12345678）。

\l 查看现有数据库

客户端：pgAdmin4, DataGrip
```

