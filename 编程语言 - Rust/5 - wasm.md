```rust
cargo install cargo-generate

// 新建项目
wasm-pack new hello-wasm

// 安装wasm-pack（Build会自动安装）
https://rustwasm.github.io/wasm-pack/installer/


// 在pkg目录
wasm-pack build
wasm-pack build --target web
```

Html调用

```html
<script type="module">
    import { default as wasm, greet } from "./pkg/myapp.js";
    wasm().then((module) => {
        // The boiler plate project comes with a `greet` function that calls:
        // `alert("Hello, hello-wasm!");`
        greet();
    });
</script>
```



https://blog.logrocket.com/getting-started-with-webassembly-and-rust/

https://rustwasm.github.io/docs/wasm-pack/tutorials/npm-browser-packages/index.html