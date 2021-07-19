https://www.electronjs.org/

```
# Electron
BrowserWindow，进程通信，Dialog模块，Menu模块，remote模块，打包

# React
基础语法，useState，useEffect，自定义Hooks

# Nodejs
fs模块，path模块，Axios

# 原生js
Fontawesome，Bootstrap
```



开发环境

```
https://github.com/electron/electron-quick-start
https://hub.fastgit.org/electron/electron-quick-start.git

# electron-forge    https://www.electronforge.io/
yarn create electron-app myapp

# 镜像
ELECTRON_MIRROR=https://cdn.npm.taobao.org/dist/electron npm install electron -D
npx electron -v
npx electron

# windows报错
set-ExecutionPolicy RemoteSigned

# 镜像2
npm config set registry https://registry.npm.taobao.org
npm config set electron_mirror https://npm.taobao.org/mirrors/electron/

华为electron_mirror少文件，安装失败
https://mirrors.huaweicloud.com/
npm config set registry https://repo.huaweicloud.com/repository/npm/
npm cache clean -f
```



Process进程，Thread线程

```
# 主进程 Main Process
只有一个，程序入口
系统API：创建菜单，上传文件
创建渲染进程
支持Nodejs

# 渲染进程 Render Process
每个tab一个
支持Nodejs和DOM API
```



辅助工具

```
npm install nodemon -D
监控main.js变化，不需要每次重启
"start": "nodemon --watch main.js --exec \"electron .\""
```


