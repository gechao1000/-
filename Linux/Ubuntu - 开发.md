
#### WSL安装

1. 更新wsl2内核

```
https://docs.microsoft.com/zh-cn/windows/wsl/wsl2-kernel
> wsl -l -v
> wsl --set-default-version 2
```

2. WIN10商店安装ubuntu

```
vscode 插件 Remote WSL
```

#### 镜像源

1. 备份

```
sudo cp -a /etc/apt/sources.list /etc/apt/sources.list.bak
```

2. 修改

```
sudo sed -i "s@http://.*archive.ubuntu.com@http://mirrors.huaweicloud.com@g" /etc/apt/sources.list
sudo sed -i "s@http://.*security.ubuntu.com@http://mirrors.huaweicloud.com@g" /etc/apt/sources.list
```

3. 更新索引

```
sudo apt update
```

#### 编译器

```
sudo apt install build-essential

--vscode remote
插件Remote WSL，左菜单连接ubuntu（默认是docker）
插件C/C++
插件C/C++ Themes
插件Code Runner
整合包C/C++ Extension Pack

```


