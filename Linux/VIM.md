
#### 编译 VIM

> https://github.com/vim/vim
>
> https://vim.fandom.com/wiki/Building_Vim

```
# 依赖
yum install ncurses-devel
sudo apt install libncurses5-dev libncursesw5-dev

# 安装目录/usr/local/bin
./configure --with-features=huge 
make
make install
```
#### 准备

```
# 安装
yum install -y vim-enhanced
sudo apt install vim

# 下载plug-vim，放在autoload目录
wget https://raw.githubusercontent.com/junegunn/vim-plug/master/plug.vim

# 安装插件
:PlugInstall
:PlugStatus
:PlugClean

# 别名 (可选)
```


#### VIM配置

```
mkdir -p .vim/autoload

# 配置文件 ~/.vimrc
call plug#begin('~/.vim/plugged')
Plug 'ajmwagar/vim-deus'
Plug 'tpope/vim-surround'
Plug 'gcmt/wildfire.vim'
Plug 'mg979/vim-visual-multi'
call plug#end()
```

### NVIM配置

```
mkdir -p .config/nvim/autoload

# 配置文件 ~/.config/nvim/init.vim
call plug#begin('~/.config/nvim/plugged')
Plug 'ajmwagar/vim-deus'
Plug 'tpope/vim-surround'
Plug 'gcmt/wildfire.vim'
Plug 'mg979/vim-visual-multi'
call plug#end()
```

#### 通用配置

```
syntax on
filetype plugin indent on

set autoindent
set expandtab

set tabstop=4
set shiftwidth=4

set backspace=2

colorscheme deus



# backspace不能删除
set nocompatible
set backspace=2
```



### 可行方案

https://github.com/neoclide/coc.nvim

```
Neovim + tabnine + coc + defx（文件浏览器）
```



### SpaceVim

https://github.com/SpaceVim/SpaceVim

```
curl -sLf https://spacevim.org/install.sh | bash

docker pull spacevim/spacevim
docker run -it --rm spacevim/spacevim nvim
```

