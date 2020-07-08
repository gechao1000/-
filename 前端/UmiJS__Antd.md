# ProTable

> https://pro.ant.design/blog/protable-cn

快速搭建 CRUD 的利器

 在 antd 的 table 上进行了一层封装

#### 1. 安装

```
# 安装
yarn add @ant-design/pro-table

# 引入
import ProTable, { ProColumns, ActionType } from '@ant-design/pro-table';
// 图标
import { PlusOutlined } from '@ant-design/icons';
```

#### 2. 直接获取 dataSource

request 直接请求接口，不调用store，格式固定｛success, total, data｝

```
<ProTable request={requestHandler}

const requestHandler = async ({pageSize, current}) => {
  let result =  await getRemoteList();
  return {
    data: result.data,
    success: true,
    total: result.meta.total
  }
}
```

#### 3. 配置

```
# 关闭搜索表单 
search={false}

# Column 在查询表单中不展示此项
{ hideInSearch:true }

# Column 格式化列
{ valueType: 'dateTime' }

# 渲染工具栏
toolBarRender={() => [
  <Button key="3" type="primary">
    <PlusOutlined />
    新建
  </Button>,
]}
```

#### 4. 刷新

基于 request 属性

```
import { useRef } from 'react'
import { ActionType } from '@ant-design/pro-table'

const actionRef = useRef<ActionType>();

<ProTable actionRef={actionRef} />;

// 刷新
ref.current.reload();
```

