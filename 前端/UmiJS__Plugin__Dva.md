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

```typescript
import { Reducer, Effect, Subscription } from 'umi';

// 数据行的类型BasicListItemDataType 在data.d.ts中定义
export interface StateType {
  list: BasicListItemDataType[];
}

export interface ModelType {
  namespace: string;
  state: StateType;
  effects: {
    fetch: Effect;
    appendFetch: Effect;
    submit: Effect;
  };
  reducers: {
    queryList: Reducer<StateType>;
    appendList: Reducer<StateType>;
  };
  subscriptions: {
    setup: Subscription;
  };
}
```

进入页面的时候触发action

```
// 方法1，使用 Subscription
setup({ dispatch, history }) {
  return history.listen(({ pathname }) => {
	if (pathname === '/users') {
	  dispatch({
		type: 'getList',
	  });
	}
  });
},

// 方法2：在页面使用 useEffect + dispatch
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
import request from 'umi-request';

export async function queryList (params?: TableListParams) { 
	return request(...)
}

# 代理服务器，跨域问题（只在开发阶段有效）
config -> proxy
```

model中调用

```
# 只能在effects中
import { queryList } from './service'
const data = yield call(queryList)
```

#### 4. Modal、Form 陷阱

```
# 问题：在 Modal 中调用 form 控制台会报错
<Modal> 设置 forceRender 属性

# Table问题：没有key值
<Table> 设置 rowKey="字段名"

# List向Form传值，Partial表示每一个属性都可选
const [current, setCurrent] = useState<Partial<BasicListItemDataType> | undefined>(undefined);

# 子组件需要的属性
const { done, visible, current, onDone, onCancel, onSubmit } = props;
```

form 赋值问题

```
const [form] = Form.useForm();

// 重置表单
useEffect(() => {
	if (form && !visible) {
		form.resetFields();
	}
}, [visible])

// 延迟赋值
useEffect(() => {
  if (current) {
    form.setFieldsValue({
    	...current,
    	createdAt: current.createdAt ? moment(current.createdAt) : null,
    });
  }
}, [current]);

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

form 组件传值

```
# DatePicker组件，接收moment类型数据
{ create_time: moment(record.create_time) }

# Switch组件，不是使用value属性，接收boolean类型数据
<Form.Item label="状态" name="status" valuePropName="checked">
  <Switch />
</Form.Item>
```

form 固定宽度

```
# 24等分
const layout = {
  labelCol: { span: 4 },
  wrapperCol: { span: 20 },
};

<Form {...layout} >
```

加载状态

```
# Table 查询状态
<Table loading={loading} />

export default connect(({ users, loading }) => ({
  users,
  loading: loading.models.users,
}))(Login);

# Modal 确认按钮 加载状态
const [confirmLoading, setConfirmLoading] = useState(false);
<Modal confirmLoading={confirmLoading} >
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

const UserListPage: FC<UserPageProps> = (props) => {...}

// Loading 类型
connect(({ users, loading })
        
// useState 使用泛型
useState<SingleUserType | undefined>(undefined);
```

定义data.d.ts

```typescript
export interface TableListItem {
  id: number;
  name: string;
  email: string;
  create_time: string;
  update_time: string;
  status: number;
}

export interface TableListParams {
  sorter?: string;
  status?: string;
  name?: string;
  desc?: string;
  key?: number;
  pageSize?: number;
  currentPage?: number;
}
```
