#### 准备

```
# 修改 /etc/hosts（防止DNS污染）
199.232.68.133 raw.githubusercontent.com

# 下载plug-vim，放在autoload目录
wget https://raw.githubusercontent.com/junegunn/vim-plug/master/plug.vim

# 安装插件
:PlugInstall
:PlugStatus
:PlugClean

# 别名 (可选)
```

#### VIM

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

### NVIM

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
```