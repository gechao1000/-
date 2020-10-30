#### 插件

```
Auto Rename Tag
Bracket Pair Colorizer 2
ES7 React/Redux/GraphQL/React-Native snippets
Live Server
Prettier - Code formatter
Sublime Text Keymap and Settings Importer
Visual Studio IntelliCode
Web Template Studio (staging-weekly)
koroFileHeader

# 主题
One Monokai Theme
GitHub Theme

# Lua插件
Lua
Code Runner

# vue开发
Vue VS Code Extension Pack
Element UI Snippets
Form Generator Plugin
```

#### 接口测试

> REST Client 插件，类似 Idea Tools => HTTP Client

```
# 源码
https://github.com/Huachao/vscode-restclient
```

#### 远程开发

> Remote Development插件，支持 SSH、Container(容器)、WSL

```
# ssh连接远程机器
ssh gexc@192.168.80.90

# ssh免密码登录
1. 生成 id_rsa 和 id_rsa.pub 两个秘钥文件
ssh-keygen -C "gexc@budata.com"

-t 指定密钥类型，包括 RSA 和 DSA 两种密钥，默认是 rsa ，可以省略。
-C 设置注释文字，比如邮箱。
-f 指定密钥文件存储文件名。

2. 将客户端公钥发送个服务端（方法一）
远程机器执行ssh-keygen，新建文件authorized_keys，保存本地公钥 

2. 将客户端公钥发送个服务端（方法二）
ssh-copy-id gexc@192.168.80.90
公钥追加到服务端$HOME/.ssh/authorized_keys文件
```
