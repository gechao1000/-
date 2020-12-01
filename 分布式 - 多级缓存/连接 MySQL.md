```plain
--MySQL查询操作，封装成一个模块
--Java操作MySqL
--导入依赖包
local mysql = require "resty.mysql"
--配置数据源链接
local props = {
host = "192.168.115.128",
port = 3306,
database = "test",
user = "root",
password = "root"
}
--创建一个对象
local mysqldb = {}

--查询数据库
function mysqldb.query(sql)
--创建链接
local db = mysql:new()
--设置超时时间
db:set_timeout(10000)
db:connect(props)
--配置编码格式
db:query("SET NAMES utf8")
--查询数据库 "select * from activity_info where id=1"
local result = db:query(sql)

--关闭链接
db:close()
--返回结果集
return result
end
return mysqldb
```

