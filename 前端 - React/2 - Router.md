###### 环境

```
yarn add react-router-dom

# 只需要在 index.jsx 引入
import { BrowserRouter } from "react-router-dom"

ReactDOM.render(
    <BrowserRouter>
      <App />
    </BrowserRouter>,
  document.getElementById("root")
);
```

######  最简路由

```
<React.Fragment>
    <NavBar />
    <Switch>
        <Route exact path = "/" component = { Blank } />
        <Route path = "/Grid" component = { Grid } />
    </Switch>
    <Footer />
</React.Fragment>

# html标签, https://www.w3school.com.cn/tags/tag_main.asp
<nav>
<main>
<footer>
```

###### `<Switch>` 标签

```
- 避免重复匹配
- <Route>在路径相同的情况下，只匹配第一个
```

###### exact 属性

```
精确匹配，一般用于 index 页面
其他写法：exact={true}
```

###### 动态传值

```
Route:	path="/list/:id"
Link:	to="/list/123"
接受值:  this.props.match.params.id
```

###### 重定向

```
方法1：<Redirect to="/home/" />
方法2：this.props.history.push("/home/");  
```

