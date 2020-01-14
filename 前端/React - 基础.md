#### 开发环境

>  https://create-react-app.dev/docs/getting-started/ 

```
# vscode插件
Simple React Snippets，Web Template Studio

# chrome插件 (开启 highlight Updates)
React Developer Tools
```

#### 引入React

```js
import React from 'react'
import ReactDOM from 'react-dom'
import App from './App'

ReactDOM.render(<App />, document.getElementById('root'))
```

#### JSX简介

> JSX = javascript + xml，由React发明，利用HTML语法来创建虚拟DOM
>
> 遇到`<`，JSX就当作HTML解析，遇到`{`就当JavaScript解析

```jsx
import React, { Component } from 'react'

// 自定义组件首字母必须大写，区分html标签
class App extends Component {
    render() {
        return (
            <ul className="my-list">
                <li>001</li>
                <li>002</li>
                <li>{false ? '003' : '004'}</li>
            </ul>
        )
    }
}
```

#### 数据绑定

> `React`不建议你直接操作`DOM`元素,而是要通过数据进行驱动，改变界面中的效果
>
> `React`是单向数据流

```jsx
constructor(props){
    super(props) //调用父类的构造函数，固定写法
    this.state={
        inputValue:'' , // input中的值
        list:[]    //服务列表
    }
}

<input value={this.state.inputValue} /> 
```

#### 绑定事件

```jsx
<input value={this.state.inputValue} onChange={this.onChange.bind(this)} />

onChange(e) {
	this.setState({
        inputValue: e.target.value
    })
}
```

### 陷阱

```
# class样式
<input className="aaa" />

# label 标签
<label htmlFor="aaa">添加</label>
<input id="aaa" />
```

#### 组件传值

> 单向数据流，函数式编程

```
# 父组件向子组件传值，属性传值
<XiaojiejieItem content={item} />
<div>{this.props.content}</div>

# PropTypes校验传递值
import PropTypes from 'prop-types';
XiaojiejieItem.propTypes = {
	value: PropTypes.string.isRequired,
    content: PropTypes.string,
    index: PropTypes.number,
    handleClick: PropTypes.func
}

# defaultProps默认值
```

#### 生命周期函数

> 生命周期函数指在某一个时刻组件会自动调用执行的函数
>
> 改善程序性能

1. `Initialization`:初始化阶段
2. `Mounting`: 挂在阶段
3. `Updation`: 更新阶段
4. `Unmounting`: 销毁阶段

```
componentWillMount----组件将要挂载到页面的时刻执行(只在页面刷新时执行一次)
render----页面state或props发生变化时执行
componentDidMount----组件挂载完成的时刻执行(只在页面刷新时执行一次)

shouldComponentUpdate-----组件发生改变前执行(返回bool，false不再往下执行)
componentWillUpdate----组件更新前，shouldComponentUpdate函数之后执行
render
componentDidUpdate----组件更新之后执行

componentWillReceiveProps----子组件接收到父组件传递过来的参数，父组件render函数重新被执行
* 也就是说这个组件第一次存在于Dom中，函数是不会被执行的
* 如果已经存在于Dom中，函数才会被执行

componentWillUnmount----组件从页面中删除的时候执行
```

#### ` shouldComponentUpdate ` 改善程序性能

> 快捷键：`scu`

```
shouldComponentUpdate(nextProps,nextState) {
    if(nextProps.content !== this.props.content){
        return true
    } else {
        return false
    }
}
```

#### Axios

> 在`componentDidMount`生命周期函数里请求ajax

```
npm install -save axios
或
yarn add axios

import axios from 'axios'

axios.get('改为你自己的接口URL')
        .then((res)=>{})
        .catch((error)=>{})
```

