## 愚蠢的机器

开发者账号

```
gechao1001@gmail.com
mscp2020
```

```
AppID(小程序ID)： wx5d8470850ba6338b
AppSecret(小程序密钥)：1cfd9e7cd54e953a27a394b4eb7eb29a
```

## tarojs

##### 1. 开发环境

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

##### 2. 语法

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

##### 3. taro ui

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

##### 4. taro redux

```
tyarn add redux @tarojs/redux @tarojs/redux-h5 redux-thunk redux-logger

import { connect, useSelector, useDispatch } from '@tarojs/redux'

// useSelector代替connect
const counter = useSelector(state => state.counter)
let dispatch = useDispatch()
```

###### store/index.js

```js
import { createStore, applyMiddleware, compose } from 'redux'
import thunkMiddleware from 'redux-thunk'
import rootReducer from '../reducers'

const composeEnhancers =
  typeof window === 'object' &&
  window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ ?   
    window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__({
      // Specify extension’s options like name, actionsBlacklist, actionsCreators, serialize...
    }) : compose

const middlewares = [
  thunkMiddleware
]

if (process.env.NODE_ENV === 'development' && process.env.TARO_ENV !== 'quickapp') {
  middlewares.push(require('redux-logger').createLogger())
}

const enhancer = composeEnhancers(
  applyMiddleware(...middlewares),
  // other store enhancers if any
)

export default function configStore () {
  const store = createStore(rootReducer, enhancer)
  return store
}
```

###### reducer/index.js

```js
import { combineReducers } from 'redux'
import counter from './counter'

export default combineReducers({
  counter
})
```

###### app.jsx

```jsx
import { Provider } from '@tarojs/redux'
import configStore from './store'

const store = configStore()

<Provider store={store}>
	<Index />
</Provider>
```

