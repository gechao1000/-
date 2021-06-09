原型图，敏捷开发，工作时长



1. 设计UI
2. 划分组件



FileList / FileItem

TabList / TabItem



项目文件结构（不要过度思考，只要不过度设计就行，5分钟弄完，以后会重构）

```
components
	- MyDoc.jsx
  - MyDoc.css
hooks
	- useXXX.js
utils
	- xxx.js
```



Electron 整合 React脚手架

```
cnpm  i -D electron electron-is-dev

--- 项目入口main.js
初始化BrowserWindow

--- 判断环境
const isDev = require("electron-is-dev")
if (isDev) win.loadURL("http://localhost:3000/")

--- package.json启动脚本
yarn add concurrently --dev  // 运行多条命令，跨平台
yarn add wait-on --dev	// 先启动react，再启动electron
yarn add cross-env --dev	//跨平台设置环境变量，禁止react-script打开浏览器
"dev": "concurrently \"wait-on http://localhost:3000/ && electron .\"  \"cross-env BROWSER=none yarn start\""
```



样式库 Bootstrap

```
yarn add bootstrap
import 'bootstrap/dist/css/bootstrap.min.css' // 全局引入

Grid 栅格布局
    <div className="container-fluid">
      <div className="row">
        <div className="col-8 bg-primary">col-8</div>
        <div className="col-4 bg-danger">col-4</div>
      </div>
    </div>
    
Flex 布局（左右居中，垂直居中）
        <div className="d-flex justify-content-between align-items-center">
```



图标库 Fontawersome（基于svg，之前的fonticon 需要下载很大的字体文件）

https://fontawesome.com/

```
yarn add @fortawesome/fontawesome-free

import '@fortawesome/fontawesome-free/css/all.css'

<button className="btn btn-info"><i class="fas fa-coffee fa-xs"></i></button>
```

