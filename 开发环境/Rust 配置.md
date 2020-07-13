### 国内源

> https://mirrors.sjtug.sjtu.edu.cn/#/

编辑 `~/.cargo/config`

```toml
[source]

[source.mirror]
registry = "https://mirrors.sjtug.sjtu.edu.cn/git/crates.io-index/"

[source.crates-io]
replace-with = "mirror"
```

