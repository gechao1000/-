# 愚蠢的机器

```
AppID: wx5d8470850ba6338b
AppSecret: 1cfd9e7cd54e953a27a394b4eb7eb29a
```

开发者账号

```
gechao1001@gmail.com
mscp2020
```

# Taro

#### 1. 开发环境

> https://taro.aotu.io/

```
yarn global add @tarojs/cli

taro init myapp

// 项目目录
taro update self
taro update project

yarn dev:weapp

// 快速创建新页面
taro create --name blog
```

#### 2. 目录结构

```
├── dist                   编译结果目录
├── config                 配置目录
|   ├── dev.js             开发时配置
|   ├── index.js           默认配置
|   └── prod.js            打包时配置
├── src                    源码目录
|   ├── pages              页面文件目录
|   |   ├── index          index 页面目录
|   |   |   ├── index.js   index 页面逻辑
|   |   |   └── index.css  index 页面样式
|   ├── app.css            项目总通用样式
|   └── app.js             项目入口文件
└── package.json
```

#### 3. 语法

```
import Taro, {  useState } from '@tarojs/taro'
import { View, Text, Image } from '@tarojs/components'

# 页面跳转
Taro.navigateTo({url:'/pages/index/index'})
redirectTo，switchTab，navigateBack

# 参数
this.$router.params.blogTitle

# 图片引入
<Image src={require('../../static/newbbd0001.jpg')} />

# 网络请求
Taro.request({url:''}).then(res => console.log(res))
```

#### 4. taro ui

```
yarn add taro-ui

# config/index.js
h5: {
  esnextModules: ['taro-ui'],
}

// page.js
import { AtButton } from 'taro-ui'

// app.js 全局引入
import 'taro-ui/dist/style/index.scss' 

// app.scss 全局引入
@import "~taro-ui/dist/style/index.scss";

// 按需引入
@import "~taro-ui/dist/style/components/button.scss";
```

#### 5. taro redux

```
tyarn add redux @tarojs/redux @tarojs/redux-h5 redux-thunk redux-logger

import { connect, useSelector, useDispatch } from '@tarojs/redux'

// useSelector代替connect，(不完善，h5不能用)
const counter = useSelector(state => state.counter)
let dispatch = useDispatch()
```

#### 6. 编译配置  

defineConstants，常量

编译期间替换, 配置方式类似  [Webpack DefinePlugin](https://webpack.js.org/plugins/define-plugin/)

```
defineConstants: {
	BASE_URL: JSON.stringify('http://localhost:3721/')
},

# 使用
console.log(BASE_URL)
```

alias，目录别名

使用 `@/` 开头而非仅用 `@` 开头，避免冲突（如：[@tarojs/taro](https://npm.im/@tarojs/taro), [@babel/core](https://npm.im/@babel/core)）

```
alias: {
  '@/components': path.resolve(__dirname, '..', 'src/components'),
  '@/store': path.resolve(__dirname, '..', "src/store"),
}

# 使用
import A from '@/components/A'
```

#### 7. tabbar

```
// app.js
tabBar: {
  list: [
	{
	  pagePath: "pages/home/index",
	  text: "首页",
	  iconPath: "./assets/tab_home.png",
	  selectedIconPath: "./assets/tab_home_f.png"
	},
	{
	  pagePath: "pages/index/index",
	  text: "测试",
	  iconPath: "./assets/tab_me.png",
	  selectedIconPath: "./assets/tab_me_f.png"
	}
  ],
  color: "#a6a6a6",
  selectedColor: "#78a4fa",
  backgroundColor: "#ffffff",
  borderStyle: "black"
}
```

#### 8. 网络请求

Taro.request 没有文档，使用 ` taro-axios`

> https://github.com/fjc0k/taro-axios

```
# 安装
yarn add taro-axios			(Taro 3)
yarn add taro-axios@0.7.0	(Taro 1、Taro 2)

# 引用
import { axios } from 'taro-axios'
import axios from 'taro-axios'
import { axios, PostData, FileData } from 'taro-axios'

# 基本使用
axios.get('https://jsonplaceholder.typicode.com/todos')
	.then(res => console.log(res))
	.catch(err => console.error(err))
  
# 上传文件
import { axios, PostData, FileData } from 'taro-axios'

async function uploadImage() {
  const { tempFilePaths } = await Taro.chooseImage({ count: 1 })
  Taro.showLoading({ title: '图片上传中...' })
  const res = await axios.post(
    'https://sm.ms/api/upload',
    new PostData({
      smfile: new FileData(tempFilePaths[0]),
      ssl: true,
      format: 'json',
    }),
  )
  Taro.hideLoading()
  Taro.showModal({
    title: '返回结果',
    content: JSON.stringify(res.data),
  })
}
```

