https://www.baeldung.com/java-jna-dynamic-libraries

https://github.com/ly3too/java-jna-jni



### CPP 创建 dll

```
项目类型：shared library

------------ CMakeLists.txt ------------
cmake_minimum_required(VERSION 3.19)
project(h1)

set(CMAKE_CXX_STANDARD 14)

add_library(h1 SHARED library.cpp library.h)


------------ 构建 ------------
mkdir build && cd build
cmake ..
make
```

header

```
#ifndef H1_LIBRARY_H
#define H1_LIBRARY_H

#ifdef _WIN32
#define JNA_EXPORT __declspec(dllexport)
#else
#define JNA_EXPORT
#endif

#ifdef __cplusplus
extern "C" {
#endif
JNA_EXPORT int add(int a, int b);

#ifdef __cplusplus
}
#endif
#endif //H1_LIBRARY_H

```

```
被 extern "C" 修饰的变量和函数是按照 C 语言方式编译和链接的
__declspec(dllexport)用于Windows中的动态库中，声明导出函数、类、对象等供外面调用，省略给出.def文件。即将函数、类等声明为导出函数，供其它程序调用，作为动态库的对外接口函数、类等。

extern "C"
{
    __declspec(dllexport) int add(int a, int b);
}
```



### Rust 创建 dll

> https://gist.github.com/CoolOppo/67b452c125bb0db3212a9fbc44c84245

```
1. 创建lib项目，修改Cargo.toml
[lib]
crate-type = ["cdylib"]

2. 修改lib.rs
#[no_mangle]
pub extern fn add(a:i32, b:i32) -> i32 {
    a + b
}

3.构建
cargo build [--release]
cargo clean
```
