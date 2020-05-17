## 搭建本地 mock 服务

> http://mockjs.com/examples.html
>
> https://github.com/jaywcjlove/mocker-api

常见的 mock 平台有 EasyMock、rap2 等，不过这些网站有时候响应较慢，调试起来也不太方便，因此在本地搭建一个 mock 服务器是更好的选择。

##### 依赖

```
yarn add mocker-api mockjs --dev

# package.json, script
"mock": "mocker ./mock"

# 测试
http://localhost:3721/api/user
```

##### 目录

```
└── mock
    ├── index.js
    └── user.mock.js
```

##### index.js

```js
const delay = require('mocker-api/lib/delay')
const user = require('./user.mock')

const proxy = {
  ...user,
  'GET /repos/hello': (req, res) => {
    return res.json({
      text: 'this is from mock server'
    });
  },
  'GET /api/:first': (req, res) => {
    console.log(req.params); // { first: 'something' }
    return res.json({ test: false });
  },
  'DELETE /api/user/:id': (req, res) => {
    console.log('---->', req.body)
    console.log('---->', req.params.id)
    res.send({ status: 'ok', message: '删除成功！' });
  },
}

// module.exports = proxy
module.exports = delay(proxy, 1000)
```

##### user.mock.js

```js
const BASE_URL = '/api/userinfo';

const release = {
  [`GET ${BASE_URL}/:id`]: (req, res) => {
    console.log('---->', req.params)
    return res.json({
      id: 1,
      username: 'kenny',
      sex: 6
    });
  }
}

module.exports = release
```

##### Mock.js

```
import Mock from 'mockjs'
或
const Mock = require('mockjs')
```

