## 大型项目和快速开发：视图层框架 + 数据层框架

React  视图层框架

Redux 数据层框架

#### 工作流程

```
React Components 
Action Creators -dispatch(action)-> Store
Store <-action,state-> Reducers 
Store -state-> React Components 
```



#### `Ant Design` UI框架

> https://ant.design/index-cn

```
npm install -save antd

import 'antd/dist/antd.css';
import { Input, Button, List } from 'antd';

<Input placeholder='jspang' style={{ width:'250px'}}/>
<Button type="primary">增加</Button>
<List bordered
    dataSource={data}
    renderItem={item => <List.Item>{item}</List.Item>} />
```

