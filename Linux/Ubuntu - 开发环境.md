
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

https://packages.debian.org/sid/build-essential

```
// 包含gcc g++ make, 查看依赖apt depends build-essential
sudo apt install build-essential gdb 

// 最新版cmake
sudo apt install cmake


sudo apt install openssh-server
sudo apt install openjdk-11-jdk


// make 和 cmake 区别
https://www.cnblogs.com/milanleon/p/13608875.html
```



#### 安装 Nodejs

> https://nodejs.org/en/download/package-manager/

```
curl -fsSL https://deb.nodesource.com/setup_16.x | sudo -E bash -
sudo apt-get install -y nodejs


npx create-electron-app my-app
npm start
npm make
```

#### 安装 pip

```
sudo apt install python-pip
sudo apt install python3-pip
pip --version
pip3 --version
```



#### 配置 VSCode

https://www.alphr.com/vs-code-how-to-change-font/

https://code.visualstudio.com/docs/cpp/cmake-linux

设置 => 用户代码片段 => cmake.json

```
{
	"template": {
		"prefix": "cmake_min",
		"body": [
			"cmake_minimum_required(VERSION 3.10)",
			"set(CMAKE_CXX_STANDARD 17)",
			"set(CMAKE_CXX_STANDARD_REQUIRED ON)",
			"\n",
			"project(hello VERSION 0.1.0)",
			"add_executable(hello main.cpp)",
		]
	}
}
```

插件

```
C/C++
C++ Intellisense
CMake
CMake Tools
```

不需要配置task和launch.json