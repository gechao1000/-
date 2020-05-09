#### 1. 脚手架 `create-umi`

- **project**，通用项目脚手架，支持选择是否启用 TypeScript，以及 [umi-plugin-react](https://umijs.org/zh/plugin/umi-plugin-react.html) 包含的功能
- **ant-design-pro**，仅包含 [ant-design-pro](https://github.com/ant-design/ant-design-pro) 布局的脚手架，具体页面可通过 [umi block](https://umijs.org/zh/guide/block.html) 添加
- **block**，区块脚手架
- **plugin**，插件脚手架
- **library**，依赖（组件）库脚手架，基于 [umi-plugin-library](https://github.com/umijs/umi-plugin-library)

```
$ mkdir myapp && cd myapp
$ yarn create umi
```

#### 2. 目录

/mock， 此目录下所有的 `.js` 文件（包括 `_` 前缀的）都会被解析为 mock 文件 

```
# /mock/users.js
export default {
  '/api/users': ['a', 'b'],
};

# curl http://localhost:8000/api/users
```

/src， 源码目录 

```
# 全局布局
src/layouts/index.js

# pages
约定 pages 下所有的 js、jsx、ts 和 tsx 文件即路由

# 覆盖默认的html模板
src/pages/document.ejs

# 全局样式
src/global.(css|less|sass|scss)

# 运行时配置文件
src/app.js
```

配置文件

```
.umirc.(js|ts) 和 config/config.(js|ts)

编译时配置文件，.umirc.js 优先
```

#### 3. 路由

>  umi 会根据 `pages` 目录自动生成路由配置 

动态路由， 带 `$` 前缀的目录或文件为动态路由 

```
+ pages/
  + $post/
    - index.js
    - comments.js
  + users/
    $id.js
  - index.js
```

嵌套路由， 目录下有 `_layout.js` 时会生成嵌套路由，以 `_layout.js` 为该目录的 layout  

```
+ pages/
  + users/
    - _layout.js
    - $id.js
    - index.js
```

配置式路由

```
# 配置文件，不会对 src/pages 目录做约定式的解析
export default {
  routes: [
    { path: '/', component: './a' },
    { path: '/list', component: './b', Routes: ['./routes/PrivateRoute.js'] },
    { path: '/users', component: './users/_layout',
      routes: [
        { path: '/users/detail', component: './users/detail' },
        { path: '/users/:id', component: './users/id' }
      ]
    },
  ],
};
```

#### 4. 页面间跳转

声明式，作为 React 组件

```
import Link from 'umi/link';

export default () => (
  <Link to="/list">Go to list page</Link>
);
```

命令式，事件处理中被调用

```
import router from 'umi/router';

function goToListPage() {
  router.push('/list');
}
```

#### 5. Mock

 mock 文件夹下的文件或者 page(s) 文件夹下的 _mock 文件 

```
export default {
  // 支持值为 Object 和 Array
  'GET /api/users': { users: [1, 2] },

  // GET POST 可省略
  '/api/users/1': { id: 1 },

  // 支持自定义函数，API 参考 express@4
  'POST /api/users/create': (req, res) => { res.end('OK'); },
};
```

引入 Mock.js

```
import mockjs from 'mockjs';

export default {
  // 使用 mockjs 等三方库
  'GET /api/tags': mockjs.mock({
    'list|100': [{ name: '@city', 'value|1-100': 50, 'type|0-2': 1 }],
  }),
};
```

#### 6. dva 

> https://dvajs.com/ 
>
> dva =  React-Router + Redux + Redux-saga

```
# yarn add umi-plugin-react

export default {
  plugins: [
    [
      'umi-plugin-react',
      {
      	dva: true,
      	//或者：开启 dva-immer 以简化 reducer 编写
        dva: {
          immer: true
        }
      }
    ],
  ],
};
```

model

```
# 全局model,所有页面都可以引用
/src/models/

# 页面model，不能被其它页面引用
/src/pages/**/models/**/*.js

# 约定 model.js 为单文件 model，解决只有一个 model 时不需要建 models 目录的问题
# 有 model.js 则不去找 models/**/*.js
```

