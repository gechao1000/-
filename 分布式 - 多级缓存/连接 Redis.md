

```lua
local redis = require "resty.redis"
--定义一个对象
local lredis = {}
--创建set（）添加数据方法
function lredis.set(key,value)
    local rds = redis:new()
    rds:set_timeout(1000)
    local ok,err = rds:connect('192.168.115.1', 6379)
    if not ok then 
        ngx.say('connect redis failed: ', err)
        rds:close()
        return
    end 
    rds:set(key, value)
    rds:close()
end
--创建查询数据get（）
function lredis.get(key)
    local rds = redis:new()
    rds:set_timeout(1000)
    local ok,err = rds:connect('192.168.115.1', 6379)
    if not ok then 
        ngx.say('connect redis failed: ', err)
        rds:close()
        return
    end 
    local result = rds:get(key)
    rds:close()
    return result
end
return lredis
```
