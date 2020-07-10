## 插件

> https://umijs.org/zh-CN/plugins/api

Umi 会自动检测 `dependencies` 和 `devDependencies` 里的 umi 插件

## @umijs/plugin-request

基于 [umi-request](https://github.com/umijs/umi-request) 和 [ahooks](http://ahooks.js.org/hooks) 的 `useRequest` 提供了一套统一的网络请求和错误处理方案

#### 1. 接口格式

只是用于错误处理，不会影响最终传递给页面的数据格式

```typescript
# app.ts
import { RequestConfig } from 'umi';

export const request: RequestConfig = {
  timeout: 1000,
  errorConfig: {
    adaptor: (resData) => {
      return {
        ...resData,
        success: resData.meta?.success,
        errorMessage: resData.meta?.message
      }
    }
  },
};
```

#### 2. 基础API

```
import { useRequest } from 'umi';

const { data, error, loading } = useRequest(service.xxx)
```

#### 3. 扩展用法

集成请求库

```
如果 service 是 string 、 object 、 (...args)=> string|object
会自动使用 umi-request 来发送网络请求

useRequest('/api/userInfo');

useRequest({
  url: '/api/changeUsername',
  method: 'post',
});

useRequest((userId)=> `/api/userInfo/${userId}`);
```



## @umijs/plugin-initial-state

全局共享的数据，必须搭配 `@umijs/plugin-model`

#### 1. 启用

有 `src/app.ts` 并且导出 `getInitialState` 方法

```typescript
export async function getInitialState() {
  const data = await fetchXXX();
  return data;
}
```

#### 2. API

```jsx
const { initialState, loading, error, refresh, setInitialState } = useModel('@@initialState')

initialState:	getInitialState 的返回值
refresh：		重新执行 getInitialState 方法，并获取新数据
setInitialState：手动设置 initialState 的值，手动设置完毕会将 loading 置为 false.
```



## @umijs/plugin-model

基于 `hooks` 简易数据流（部分场景可以取代 `dva`），通常用于中台项目的全局共享数据

#### 1. 启用

在 `src/models` 目录下的文件为项目定义的 model 文件。每个文件需要默认导出一个 function

```
export default () => {
  return {
    myName: 'zhang'
  }
}
```

#### 2. API

```
import { useModel } from 'umi'

// 文件名 zhang.ts
const { myName } = useModel('zhang')
```
