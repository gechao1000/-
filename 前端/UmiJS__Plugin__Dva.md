#### 前提

```
React基础
ES6基础
TypeScript基础

阅读Dva文档
阅读Umi文档
```

#### 目标

看懂60%以上 Ant Design Pro v4 代码



## Dva

> https://dvajs.com/ 
>
> 是一个基于 [redux](https://github.com/reduxjs/redux) 和 [redux-saga](https://github.com/redux-saga/redux-saga) 的数据流方案

#### 1. 介绍

```
同步：reducer
异步：effect

reducer是唯一可以修改 state 的地方。由 action 触发

订阅：快捷方法，页面调用

页面调用reducer或effect：dispatch
reducer传递给页面：connect
effect调用reducer：put
```

定义service：调用后台接口

```
在effect中调用：call

页面也可以直接调用
```

#### 2. Model定义

```
import { Reducer, Effect, Subscription } from 'umi';

interface UserModelType {
  namespace: 'users';	// 固定值
  state: {
      data: []
  };
  reducers: {
    getList: Reducer
  };
  effects: {
    query: Effect
  };
  subscriptions: {
    setup: Subscription;
  };
}
```

setup函数，进入页面的时候触发action

```
setup({ dispatch, history }) {
  return history.listen(({ pathname }) => {
	if (pathname === '/users') {
	  dispatch({
		type: 'getList',
	  });
	}
  });
},
```

页面使用connect

```
import { connect } from 'umi'

const UserInfo = ({users}) => {}

const mapStateToProps = ({users}) => ({
    users
})

export default connect(
  mapStateToProps
)(Index)
```

