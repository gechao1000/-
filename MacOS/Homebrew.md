## 安装

> 官网	https://brew.sh/index_zh-cn
>
> 软件加速下载	https://www.newbe.pro/Others/All-Mirror/index.html

###### CommandLineTools

```
# 直接安装XCode

# 在线安装
xcode-select --install

# 下载安装包
https://developer.apple.com/download/more/
```

###### 国内安装脚本

```shell
/bin/zsh -c "$(curl -fsSL https://gitee.com/cunkai/HomebrewCN/raw/master/Homebrew.sh)"

# 可修改脚本，使用git clone --depth=1减小下载体积
```

###### ohmyzsh国内镜像（终端还是很丑）

```shell
# https://touka.dev/tech/oh-my-zsh-china-mirror/
wget https://gitee.com/mirrors/oh-my-zsh/raw/master/tools/install.sh

# 修改install.sh
REPO=${REPO:-mirrors/oh-my-zsh}
REMOTE=${REMOTE:-https://gitee.com/${REPO}.git}

# 运行install.sh

# 修改仓库地址
cd ~/.oh-my-zsh
git remote set-url origin https://gitee.com/mirrors/oh-my-zsh.git
git pull
```



## 使用

###### 基础命令

```shell
# 提示
brew 
# 在线搜索软件包
https://formulae.brew.sh/

brew serach wget
brew install wget
# 查看安装路径
brew list wget

# 安装Casks
brew cask install xxx 
```

###### 安装 brave浏览器

```
https://brave.com/zh/

brew search brave
brew cask install brave-browser
```

###### 环境

```shell
brew install rust # 编译需要gcc，下载非常慢，或者Command Line Tools
brew install node
brew cask install github
brew cask install staruml
brew cask install another-redis-desktop-manager
brew cask install postman

# 编辑器
brew install vim # 默认已安装
brew cask install kindle # 下载失败，在AppStore安装
brew cask install typora
brew cask install yuque

# Java
brew install openjdk@11
brew cask install intellij-idea
brew cask install docker
brew install maven
brew install gradle
brew install kotlin

# 数据库工具
idea自带工具
brew cask install tableplus
brew cask install querious
brew cask install sequel-pro

# 欧路词典
brew cask install eudic

# 微信， QQ
brew cask install wechat
brew cask install qq

# 播放器
brew cask install qqplayer # 不能运行，兼容问题
brew cask install iina

# 游戏
brew cask install battle-net
brew cask install steam
```



