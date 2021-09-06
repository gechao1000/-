基础 https://blog.csdn.net/ClaireSy/article/details/108422945
核心 https://blog.csdn.net/ClaireSy/article/details/108423047
提高 https://blog.csdn.net/ClaireSy/article/details/108423061



#### win10 wsl ubuntu（端口2222）

```
wsl -l -v
wsl --set-default-version 2 

https://www.jetbrains.com/help/clion/2021.1/how-to-use-wsl-development-environment-in-product.html#wsl-tooclhain

sudo apt update
sudo apt install  build-essential gdb cmake

sudo apt-get install cmake gcc clang gdb build-essential


wget https://raw.githubusercontent.com/JetBrains/clion-wsl/master/ubuntu_setup_env.sh && bash ubuntu_setup_env.sh
```

#### 编译过程

```
预处理（展开include、宏）
g++ -E test.cpp -o test.i
编译（汇编语言）
g++ -S test.cpp -o test.s
汇编（机器语言）
g++ -c test.s -o test.o
链接(可执行文件)
g++ test.o -o test

// 汇总
g++ test.cpp -o test
```

#### 重要参数

```
-g 带调试信息
-O[n] 优化源码
-l 指定库文件
-L 指定库文件路径
-I 指定头文件目录，/usr/include不需要
-std=c++11 编译标准
-o 输出文件名，默认a.out
-D 定义宏

time ./xxx 计算指令耗时
g++ -O2 test.cpp
g++ -lglog test.cpp 链接glog库，在/lib或/usr/lib或/usr/local/lib目录
g++ -L/home/xx -lmytest test.cpp 链接/home/xx/mytest库
g++ -I/myinclude test.cpp 
g++ test.cpp -o test
g++ -DDEBUG main.cpp 定义DEBUG宏

man gcc

----------- 实战
main.cpp
include/swap.h
sr/swap.cpp

----------- 可执行文件
g++ main.cpp src/swap.cpp -Iinclude

----------- 静态库
cd src
g++ swap.cpp -c -I../include //汇编，生成swap.o
ar rs libswap.a swap.o  //归档，libswap.a
cd ..
g++ main.cpp -Iinclude -Lsrc -lswap -o staticmain //调用

----------- 动态库（具有可执行权限）
cd src
g++ swap.cpp -I../include -fPIC -shared -o libswap.so //生成libswap.so
cd ..
g++ main.cpp -Iinclude -Lsrc -lswap -o sharemain //调用

-----运行
./staticmain
LD_LIBRARY_PATH=src ./sharemain 指定动态库路径
```



#### GDB调试器

```
IDE集成了调试器
vscode使用GDB调试

gdb [file]

help|h
run|r
start 停在第一行
list|l 查看源码
next|n 单步调试（跳过函数）
step|s 单步调试（进入函数）
finish 结束当前函数
info|i 查看函数局部变量
continue|c 继续运行
print|p x 打印变量x值
display x 
quit|q 退出gdb

break|b [n] 第n行加断点
info|i breakpints|b 查看所有断点


Enter 执行上一次命令
```



#### VSCode 插件

```
LR 换行\n，Linux
CRLF 换行\r\n, Windows

C/C++
CMake
CMake Tools
```





#### CMake

```
指令不区分大小写，变量区分
指令(参数1 参数2)

变量
set(HELLO hello.cpp) 
add_executable(hello main.cpp ${HELLO})
```

重要指令

```
# cmake 最小版本
cmake_minimum_required(VERSION 2.8.3)

# 工程名
project(HELLO)

# 定义变量SRC=h1.cpp h2.cpp
set(SRC h1.cpp h2.cpp)

# 头文件路径，相当于-I./include -I/home/xx
include_directories(./include /home/xx)

# 库文件路径，相当于-L
link_directories(./lib /home/lib)

# 生成库文件 libhello.so
add_library(hello SHARED ${SRC})

# 生成可执行文件
add_executable(main main.cpp)

# 添加共享库，相当于-l
target_link_libraries(main hello)

# 目录所有的源码存放到变量
aux_source_directory(. SRC)
```

构建

```
mkdir build
cd build
cmake ..  // 只需要执行一次
make	// cpp有改动，重复执行
```

最小 CMakeLists.txt

> g++ main.cpp -o main

```
cmake_minimum_required(VERSION 3.19)
project(main)

add_executable(main main.cpp)
```

标准 CMakeLists.txt

> g++ main.cpp src/swap.cpp -Iinclude -o main

```
cmake_minimum_required(VERSION 3.19)
project(main)

include_directories(include)
add_executable(main main.cpp src/swap.cpp)
```

