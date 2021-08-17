### 接口关联

上一个接口的返回值作为下一个接口的参数

* Json 提取器（Tests模块）

```
console.log(responseBody)
var access_token = JSON.parse(responseBody).access_token
# 设置全局变量，右侧面板
pm.globals.set("access_token", "access_token");

# 右上角图标，查看全局变量
# 右侧 Environment 面板，删除全局变量
{{access_token}} 使用全局变量
```

* 正则表达式提取（Tests模块）

```
var match = responseBody.match(new RegExp('"access_token":"(.+?)"'))
# 设置全局变量
```

* Cookie 提取



### 动态参数

* 内置

```
{{$timestamp}} 时间戳
{{$randInt}} 0-1000
{{$guid}} 
```

* 自定义

```
{{xxx}}
pm.globals.set("xxx", "123")

pm.globals.get("xxx")
```



### 断言

自动化测试：预期结果，实际结果

* 右侧面板添加代码块

```
判断状态码（必须有）
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

检查json
pm.test("Your test name", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.access_token).not.eql(undefined);
});

检查字符串
pm.test("Body matches success", function () {
    pm.expect(pm.response.text()).to.include("Success");
});

响应时间小于200ms
```

* 状态断言可以放在全局

```
Collections -> 右键Edit -> Test模块
```



### 环境变量

右上角选择环境

```
调用 {{xxx}}
```

