https://mirrors.tuna.tsinghua.edu.cn/help/homebrew/

环境变量

```shell
if [[ "$(uname -s)" == "Linux" ]]; then BREW_TYPE="linuxbrew"; else BREW_TYPE="homebrew"; fi
export HOMEBREW_BREW_GIT_REMOTE="https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/brew.git"
export HOMEBREW_CORE_GIT_REMOTE="https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/${BREW_TYPE}-core.git"
export HOMEBREW_BOTTLE_DOMAIN="https://mirrors.tuna.tsinghua.edu.cn/${BREW_TYPE}-bottles"
```

安装

```
# 从本镜像下载安装脚本并安装 Homebrew / Linuxbrew
git clone --depth=1 https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/install.git brew-install
/bin/bash brew-install/install.sh
rm -rf brew-install

# 也可从 GitHub 获取官方安装脚本安装 Homebrew / Linuxbrew
/bin/bash -c "$(curl -fsSL https://github.com/Homebrew/install/raw/master/install.sh)"
```

替换现有仓库上游

```
git -C "$(brew --repo homebrew/core)" remote set-url origin https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/homebrew-core.git
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

brew search wget
brew install wget
# 查看安装路径
brew list wget

# 安装Casks
brew cask install xxx 
```

###### 安装 brave浏览器（一般）

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

# 数据库工具
brew cask install tableplus
brew cask install querious
brew cask install sequel-pro

# QT
https://www.jianshu.com/p/5b2387137e3a
```



