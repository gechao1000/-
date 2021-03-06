## state状态管理

> https://www.redux.org.cn/，   http://redux.js.org/
>

应用中所有的 state 都以一个对象树的形式储存在一个单一的 *store* 中。 

惟一改变 state 的办法是触发 *action*，一个描述发生什么的对象。 

为了描述 action 如何改变 state 树，你需要编写 *reducers*

#### 1. 安装

```
yarn add redux react-redux redux-thunk
yarn add -D redux-devtools

# redux
import { createStore, applyMiddleware, compose } from 'redux'
import thunkMiddleware from 'redux-thunk'
import { combineReducers } from "redux";

# react-redux
import { Provider } from 'react-redux'
import { connect } from 'react-redux'
```

#### 2. Reducer

* 形式为 (state, action) => state 的纯函数
* 描述了 action 如何把 state 转变成下一个 state
* 当 state 变化时需要返回全新的对象，而不是修改传入的参数

###### 有副作用的操作，如 API 调用或路由跳转。这些应该在 dispatch action 前发生

###### **永远不要**在 reducer 里做这些操作

* 修改传入参数;
* 执行有副作用的操作，如 API 请求和路由跳转;
* 调用非纯函数，如 `Date.now()` 或 `Math.random()`。


#### 3. store

* 提供 [`getState()`](https://www.redux.org.cn/docs/api/Store.html#getState) 方法获取 state；
* 提供 [`dispatch(action)`](https://www.redux.org.cn/docs/api/Store.html#dispatch) 方法更新 state；
* 通过 [`subscribe(listener)`](https://www.redux.org.cn/docs/api/Store.html#subscribe) 注册监听器;
* 通过 [`subscribe(listener)`](https://www.redux.org.cn/docs/api/Store.html#subscribe) 返回的函数注销监听器;

```
// 第二个参数是可选的, 用于设置 state 初始状态
let store = createStore(todoApp, window.STATE_FROM_SERVER)
```

#### 4. 示例

快捷键 `rxaction` `rxreducer` `rxconst`

```
// action.js
export const actionName = (payload) => ({
  type: type,
  payload
})

// reducer.js
const initialState = {};

export default (state = initialState, { type, payload }) => {
  switch (type) {
    case typeName:
      return { ...state, ...payload };

    default:
      return state;
  }
};

```

#### 5. 异步 action

redux-thunk，异步过程放在 action 级别

```jsx
// 错误
export const xxx = (payload) => {
  setTimeout(() => {
    return {
      type: 'XXX',
      payload
    }
  }, 1000);
}

// 正确
export const xxx = (payload) => (dispatch) => {
  setTimeout(() => {
    dispatch({
      type: "XXX",
      payload,
    });
  }, 1000);
};

// 正确
export const getNewBooks = () => {
  // 返回函数，异步dispatch
  return async dispatch => {
    let result = await API.get("/books/new");
    dispatch({
      type: HOME.GET_NEW_BOOK,
      books: result
    });
  };
};
```



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

基本

```
import { createStore, applyMiddleware } from "redux";
import thunk from "redux-thunk";
import reducers from "./reducer";

const store = createStore(reducers, applyMiddleware(thunk));

export default store;
```

完整

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

