#### PS: 文件校验码

```
certutil -hashfile .\HELP.md MD5
certutil -hashfile .\HELP.md SHA1
certutil -hashfile .\HELP.md SHA256
```



#### PS：批量重命名

```powershell
Get-ChildItem | Rename-Item -NewName {...}

# 简单使用
'test_' + $_.name
$_.name -replace '\.txt', '.pdf'

# 正则匹配：aa(bb).cc --> bb.cc
$_.name -replace '.*\((.*)\)\.', '$1.'
```

#### CMD：查杀进程

```powershell
# 查看端口占用
netstat - ano | findstr 8888

tasklist | findstr 6244
taskkill /F /PID 6244

PS: Stop-Process -ID 6244 -Force
```

#### CMD：Idea连接android模拟器

```
D:\dev\Nox\bin\nox_adb.exe connect 127.0.0.1:62001
```

#### 代理

```
# Linux
export http_proxy="http://127.0.0.1:9999"
export https_proxy="http://127.0.0.1:9999"

# CMD （不成功）
set http_proxy=http://127.0.0.1:9999 & set https_proxy=http://127.0.0.1:9999

# PowerShell （不成功）
$Env:http_proxy="http://127.0.0.1:9999";$Env:https_proxy="http://127.0.0.1:9999"
```

#### Scoop

```
# 卸载
scoop uninstall scoop


scoop bucket add extras
scoop bucket add java
scoop bucket add nerd-fonts
```

#### Wiz 笔记

> https://www.wiz.cn/zh-cn/docker

```
docker pull  wiznote/wizserver

mkdir wizdata

docker run --name wiz --restart=always -it -d -v  ~/wizdata:/wiz/storage -v  /etc/localtime:/etc/localtime -p 80:80 -p 9269:9269/udp  wiznote/wizserver
```



#### Git 约定式提交

https://www.conventionalcommits.org/en/v1.0.0/