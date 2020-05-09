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

#### 2. 语法

```
import Taro, {  useState } from '@tarojs/taro'
import { View, Text } from '@tarojs/components'

# 页面跳转
Taro.navigateTo({url:'/pages/index/index'})

# 参数
this.$router.params.blogTitle

# 网络请求
Taro.request({url:''}).then(res => console.log(res))
```

#### 3. taro ui

```
yarn add taro-ui

# config/index.js
h5: {
  esnextModules: ['taro-ui']
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

#### 4. taro redux

```
tyarn add redux @tarojs/redux @tarojs/redux-h5 redux-thunk redux-logger

import { connect, useSelector, useDispatch } from '@tarojs/redux'

// useSelector代替connect，(不完善，h5不能用)
const counter = useSelector(state => state.counter)
let dispatch = useDispatch()
```

