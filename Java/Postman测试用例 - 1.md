## 接口架构

接口本质：函数

* 输入，输出（数据交互，请求、相应）
* 异常（错误码）
* 权限控制



返回格式：json，xml，html

* Restful结构：http协议，接口地址http://
* SOAP架构：webservice协议，接口地址结尾?wsdl

* RPC架构：dubbo协议，接口地址dubbo://



请求行（方式，地址，协议版本），请求头，请求正文

```
Accept: application/json 客户端接受的数据格式
User-Agent: 浏览器名称
Content-Type：正文格式 x-www-form-urlencode 表单方式
Cookie
```

响应行（状态码），响应头（Set-Cookie），响应正文



面试题：Cookie，Session，Token 相同点和不同点

```
相同点：都是服务器产生的

Cookie: 保存在客户端
Session：保存在服务器内存
Token：保存在数据库
```



API文档：showdoc, swagger

1个接口20-30个测试用例

```
正例：100	0.1		0.01	100.02
反例1：无鉴权，过期，鉴权码错误
反例2：非数字，负数 0.001，空白，特殊字符

状态断言：200
业务断言：
```



接口测试工具

postman，Jmeter，soupui（测试webservice），apipost，fidder，charles



项目集成阶段进行接口测试