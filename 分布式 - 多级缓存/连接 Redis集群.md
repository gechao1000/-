1. 安装配置
```plain
git clone https://github.com/cuiweixie/lua-resty-redis-cluster.git
cp lib/redis_slot.c /usr/local/openresty/lualib/
cp lib/resty/rediscluster.lua /usr/local/openresty/lualib/resty
# 编译 redsi_slot.c
yum install -y lua-devel
gcc redis_slot.c -fPIC -shared -o libredis_slot.so
```
2. 连接集群
```plain
--操作Redis集群，封装成一个模块
--引入依赖库
local redis_cluster = require "resty.rediscluster"
--配置Redis集群链接信息
local config = {
name = "test",
serv_list = {
{ip="192.168.115.128", port = 7001},
{ip="192.168.115.128", port = 7002},
{ip="192.168.115.128", port = 7003},
{ip="192.168.115.128", port = 7004},
{ip="192.168.115.128", port = 7005},
{ip="192.168.115.128", port = 7006},
},
idle_timeout    = 1000,
pool_size       = 10000,
}
--定义一个对象
local lredis = {}
--创建set（）添加数据方法
function lredis.set(key,value)
--1)打开链接
local red = redis_cluster:new(config)
red:init_pipeline()
--2)执行命令【set】
red:set(key,value)
red:commit_pipeline()
--3)关闭链接
red:close()
end

--创建查询数据get（）
function lredis.get(key)
--1)打开链接
local red = redis_cluster:new(config)
red:init_pipeline()
--2)执行命令【set】
red:get(key)
local result = red:commit_pipeline()
--3)关闭链接
red:close()

--4)返回结果集
return result
end
return lredis
```
