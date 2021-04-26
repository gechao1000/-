https://rustcc.cn/article?id=98b96e69-7a5f-4bba-a38e-35bdd7a0a7dd



### 创建新工程

```
// 可执行工程，入口src/main.rs
cargo new foobar

// 库工程，入口src/lib.rs
cargo new --lib foobar
```

```toml
[lib]
crate-type = ["cdylib", "rlib"]
```

### crate type

```
rustc --help|grep crate-type

--crate-type [bin|lib|rlib|dylib|cdylib|staticlib|proc-macro]

# bin
二进制可执行文件，必须要有 main 函数作为入口

# lib
库，并不是一种具体的库，默认是rlib
```

* rlib

```
Rust Library 特定静态中间库格式。在 Rust 代码项目之间的依赖和调用
平台无关

[lib]
name = "foobar"
crate-type = ["rlib"]
```

* dylib

```
动态库。只能被 Rust 写的程序调用
平台有关，Linux 上为 .so, MacOS 上为 .dylib, Windows 上为 .dll

[lib]
name = "foobar"
crate-type = ["dylib"]
```

* cdylib

```
C规范动态库。可以被其它语言调用
与 dylib 类似，也会生成 .so, .dylib 或 .dll 文件

[lib]
name = "foobar"
crate-type = ["cdylib"]
```

* staticlib

```
静态库。
编译会生成 .a 文件（在 Linux 和 MacOS 上），或 .lib 文件（在 Windows 上）。

[lib]
name = "foobar"
crate-type = ["staticlib"]
```

* proc-macro

```
过程宏。可以被其它 crate 引用。
```

