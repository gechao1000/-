
#### WSL安装

1. 启用Windows功能

```
* 虚拟机平台
* 适用于Linux的Windows子系统
```

2. 更新wsl2内核

```
https://docs.microsoft.com/zh-cn/windows/wsl/wsl2-kernel
> wsl -l -v

WIN10商店安装ubuntu，安装前在控制台执行
> wsl --set-default-version 2
```

3. vscode插件

```
插件Remote WSL，左菜单连接ubuntu（默认是docker）
插件C/C++
插件C/C++ Themes
插件Code Runner
整合包C/C++ Extension Pack
```

#### 镜像源

1. 备份

```
sudo cp -a /etc/apt/sources.list /etc/apt/sources.list.bak
```

2. 修改

```
// 阿里
https://developer.aliyun.com/mirror/ubuntu

// 华为
https://mirrors.huaweicloud.com/
```

3. 更新索引

```
sudo apt update
```

#### 编译器

```
// 包含gcc g++ make, 查看依赖apt depends build-essential
sudo apt install build-essential
sudo apt install gdb
sudo apt install cmake


// make 和 cmake 区别
https://www.cnblogs.com/milanleon/p/13608875.html
```



#### 安装 Nodejs

> https://nodejs.org/en/download/package-manager/

```
curl -fsSL https://deb.nodesource.com/setup_16.x | sudo -E bash -
sudo apt-get install -y nodejs

npm config set electron_mirror https://npm.taobao.org/mirrors/electron/

npx create-electron-app my-app
npm start
npm make
```

