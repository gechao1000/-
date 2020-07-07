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

# 使用dispatch
const UserInfo = ({users,dispatch}) => {}

export default connect(({ users }) => ({ users }))(UserInfo)
```

#### 3. Service 定义

> request文档：https://github.com/umijs/umi-request/blob/master/README_zh-CN.md

```
import { request } from 'umi'
export const getRemoteList = async (params) => { ... }

# 代理服务器，跨域问题（只在开发阶段有效）
config -> proxy
```

model中调用

```
# 只能在effects中
import { getRemoteList } from './service'
const data = yield call(getRemoteList)
```

#### 4. Modal、Form 陷阱

```
# 问题：在 Modal 中调用 form 控制台会报错
<Modal> 设置 forceRender 属性

# Table问题：没有key值
<Table> 设置 rowKey="字段名"
```

form 赋值问题

```
const [form] = Form.useForm();

// 延迟赋值
useEffect(() => {
  if (record) {
    form.setFieldsValue(record)
  } else {
    form.resetFields();
  }
}, [record]);

<Form name="basic" form={form} >
```
form 提交

```
// 绑定到 Modal 确定按钮
<Modal onOk={() => form.submit()}

// 父组件传入 onSubmit 方法，Function(values)
<Form onFinish={handleSubmit}

dispatch({
  type: 'users/formSubmit',
  payload: values
})
```

Table 加载状态
```
<Table loading={loading} />

export default connect(({ users, loading }) => ({
  users,
  loading: loading.models.users,
}))(Login);
```

#### 4. TypeScript 静态类型检查

函数组件

```jsx
import { FC } from 'react'
import { Dispatch, Loading } from 'umi'

interface UserPageProps {
  users: any,
  dispatch: Dispatch,
  loading: boolean
}

const UserListPage: FC<UserPageProps> = ({ users, dispatch, loading }) => {...}

// Loading 类型
connect(({ users, loading })
        
// useState 使用泛型
useState<SingleUserType | undefined>(undefined);
```

定义data.d.ts

```typescript
export interface SingleUserType {
  id: number;
  name: string;
  email: string;
  create_time: string;
  update_time: string;
  status: number;
}
export interface FormValues {
  [name: string]: any;
}
export interface UserState {
  data: SingleUserType[],
  meta: {
    total: number,
    per_page: number,
    page: number
  }
}
```



#### 5. ProTable

> https://pro.ant.design/blog/protable-cn

```
// request 直接请求接口，不调用store
<ProTable request={requestHandler}

const requestHandler = async ({pageSize, current}) => {
  let result =  await getRemoteList();
  return {
    data: result.data,
    success: true,
    total: result.meta.total
  }
}

# 关闭搜索表单 search={false}
```
替换默认分页 <Pagination /> （意义不大）
```
# 关闭默认分页： pagination={false}

不使用request属性请求，通过dispatch调用effect
```

