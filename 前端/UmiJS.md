# UmiJS

> https://umijs.org/zh-CN

插件化的企业级前端应用框架

以路由为基础的，同时支持配置式路由和约定式路由，保证路由的功能完备，并以此进行功能扩展

#### 1. 开发环境

```
mkdir myapp && cd myapp
# 脚手架 create-umi（生产级别，可以选择antd模版）
tyarn create umi
# 脚手架 create-umi-app（入门级别）
tyarn create @umijs/umi-app

tyarn global add umi

# vscode 设置
勾选 JavaScript › Implicit Project Config: Check JS
```

#### 2. 目录结构

如果配置比较复杂需要拆分，可以放到 `config/config.ts` 中

```
.
├── package.json
├── .umirc.ts	// 配置文件,包含 umi 内置功能和插件的配置
├── .env	// 环境变量
├── dist	// 输出
├── mock	// 所有 js 和 ts 文件会被解析为 mock 文件
├── public	// 所有文件会被 copy 到输出路径
└── src
    ├── .umi	// 临时文件目录, 自动生成
    ├── layouts/index.tsx	// 约定式路由时的全局布局文件
    ├── pages
        ├── index.less
        └── index.tsx
    └── app.ts	//运行时配置文件, 可以修改路由
```

#### 3. 路由

单页应用，不需要请求服务器获取html，页面切换就是不同组件切换

```
routes: [
	{ path: '/login', component: 'login' },
	{
	  path: '/',
	  component: '@/layouts/index',
	  routes: [
		{ path: '/list', component: 'list' },
		{ path: '/admin', component: 'admin' },
	  ],
	}, 
],

# component属性
React组件路径，相对路径从src/pages开始找，@指向src目录

# 子路由
layout中需要指定 props.children
```

#### 4. 页面跳转

```
# 声明式，作为 React 组件使用
import { Link } from 'umi'
<Link to="/users">Users Page</Link>

# 命令式，在事件处理中被调用
import { history } from 'umi'
history.push('/list')
```

#### 5. Mock数据

```js
# 关闭 Mock
export default {
  mock: false,
};

# 支持自定义函数
# https://www.expressjs.com.cn/4x/api.html#res
import { Request, Response } from 'express';

# 示例 /mock/xxx.js
export default {
  // 支持值为 Object 和 Array
  'GET /api/users': { users: [1, 2] },
  // GET 可忽略
  '/api/users/1': { id: 1 },
  // 支持自定义函数，API 参考 express@4
  'POST /api/users/create': (req, res) => {
    // 添加跨域请求头
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.end('ok');
  },
  'POST /api/forms': (req: Request, res: Response) => {
    cosnt { username } = req.body;
    res.send({ message: 'Ok' });
  },
}
```

使用 mockjs

```js
import mockjs from 'mockjs'

export default {
  'GET /api/tags': mockjs.mock({
    'list|100': [{ name: '@city', 'value|1-100': 50, 'type|0-2': 1 }],
  }),
  'GET /api/list': (req, res) => {
    const { current = 1, pageSize = 10 } = req.query
    let data = mockjs.mock({
      total: 55,
      [`list|${pageSize}`]: [{
        id: '@guid',
        name: '@cname',
        'gender|1': ['male', 'female'],
        email: '@email',
        disabled: false
      }],
    })
    res.json(data)
  }
};
```

#### 6. 样式和资源

全局样式  `src/global.css`

```
Umi 内置支持 less，不支持 sass 和 stylus

# 引入ant样式（可选）
@import '~antd/dist/antd.css';
```

使用图片

```
# 通过 require 引用相对路径的图片，@指向 src 目录
<img src={require('./foo.png')} />
<img src={require('@/foo.png')} />

# 如果图片小于 10K，会被编译为 Base64，否则会被构建为独立的图片文件
inlineLimit=10000 (10k)
```

#### 7. Umi UI

不能独立使用

```
yarn add @umijs/preset-ui -D
```

