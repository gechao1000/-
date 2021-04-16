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

### Java 加载 dll

```
1.引入jna依赖，自定义目录保存test.dll

2.新建接口
public interface RustLib extends Library {
    RustLib INSTANCE = Native.load("r2", RustLib.class);
    void lib_test();
}

3.测试
String dir = System.getProperty("user.dir");
System.load(Paths.get(dir,"jna_lib/test.dll").toString());
RustLib.INSTANCE.lib_test();
```

### Java native方法

> https://github.com/astonbitecode/j4rs-java-call-rust