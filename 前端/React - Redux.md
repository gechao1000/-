## state状态管理

> https://www.redux.org.cn/，   http://redux.js.org/
>

应用中所有的 state 都以一个对象树的形式储存在一个单一的 *store* 中。 

惟一改变 state 的办法是触发 *action*，一个描述发生什么的对象。 

为了描述 action 如何改变 state 树，你需要编写 *reducers*

#### 1. 安装

```
yarn add redux react-redux
yarn add -D redux-devtools
```

#### 2. Reducer

* 形式为 (state, action) => state 的纯函数
* 描述了 action 如何把 state 转变成下一个 state
* 当 state 变化时需要返回全新的对象，而不是修改传入的参数


###### **永远不要**在 reducer 里做这些操作

* 修改传入参数;
* 执行有副作用的操作，如 API 请求和路由跳转;
* 调用非纯函数，如 `Date.now()` 或 `Math.random()`。

###### 有副作用的操作，如 API 调用或路由跳转。这些应该在 dispatch action 前发生

```
function counter(state = 0, action) {
  switch (action.type) {
  case 'INCREMENT':
    return state + 1;
  case 'DECREMENT':
    return state - 1;
  default:
    return state;
  }
}
```


#### 3. store

* 提供 [`getState()`](https://www.redux.org.cn/docs/api/Store.html#getState) 方法获取 state；
* 提供 [`dispatch(action)`](https://www.redux.org.cn/docs/api/Store.html#dispatch) 方法更新 state；
* 通过 [`subscribe(listener)`](https://www.redux.org.cn/docs/api/Store.html#subscribe) 注册监听器;
* 通过 [`subscribe(listener)`](https://www.redux.org.cn/docs/api/Store.html#subscribe) 返回的函数注销监听器;

```
// 第二个参数是可选的, 用于设置 state 初始状态
let store = createStore(todoApp, window.STATE_FROM_SERVER)
```

#### 4. 异步 action

redux-thunk 中间件





## 示例 Taro

##### 1. 依赖

```
tyarn add redux @tarojs/redux @tarojs/redux-h5 redux-thunk redux-logger

import { connect, useSelector, useDispatch } from '@tarojs/redux'
```

##### 2. 目录结构

```
store 
  -index.js
  -rootReducer.js
  -counter(模块名)
    -action.js
    -action-type.js
    -reducer.js
```

##### 3. store/index.js

```js
import { createStore, applyMiddleware, compose } from 'redux'
import thunkMiddleware from 'redux-thunk'
import rootReducer from './rootReducer'

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

##### 4. store/rootReducer.js

```js
import { combineReducers } from 'redux'
import counter from './counter/reducer'

export default combineReducers({
  counter
})
```

##### 5. app.js

```js
import { Provider } from '@tarojs/redux'
import configStore from './store'

const store = configStore()

<Provider store={store}>
	<Index />
</Provider>
```

##### 6. connect 简写

```
export default connect(
  ({ counter }) => ({
    counter
  }),
  {
    add,
    minus,
    asyncAdd
  }
)(Index);

export default connect(
  mapStateToProps,
  { add }
)(Index)
```

