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

### Java jna

* 依赖

```
 <dependency>
	<groupId>net.java.dev.jna</groupId>
	<artifactId>jna-platform</artifactId>
	<version>5.8.0</version>
</dependency>
```

* 创建接口

```
-------------- LibC.java --------------
public interface LibC extends Library {
    LibC INSTANCE = (LibC)
            Native.load((Platform.isWindows() ? "msvcrt" : "c"),
                    LibC.class);

    void printf(String format, Object... args);
}

-------------- LibH.java --------------
public interface LibH extends Library {
    LibH INSTANCE = (LibH) Native.load("h1", LibH.class);

    int add(int a, int b);
}
```

* 测试调用

```
System.setProperty("jna.library.path", "C:\\jna");
System.setProperty("jna.debug_load", "true");
LibH.INSTANCE.add(1, 2)
```

### Java native方法

> https://github.com/astonbitecode/j4rs-java-call-rust







-Djna.nosys=true

https://github.com/java-native-access/jna/issues/384



### 加载顺序

```
 System.setProperty("jna.library.path", "C:\\jna");
 System.setProperty("jna.debug_load", "true");
 
 --- 加载h1.dll
 寻找jna.library.path：h1.dll
 寻找system path：h1.dll
 寻找lib- prefix：libh1.dll
 寻找classpath：kbase-lib/target/classes/win32-x86-64/h1.dll
```

