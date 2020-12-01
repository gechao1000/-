1. 安装
```plain
opm get SkyLothar/lua-resty-jwt
```
1. 声明 token.lua
```plain
local jwt = require("resty.jwt")
--截取 token
local _, _, token = string.find(auth_header, "Bearer%s+(.+)")
--校验令牌
local jwt_obj = jwt:verify(secret, token)
--失败
jwt_obj.verified == false
-- 成功
jwt_obj.body.payload.username
```
1. 使用
```plain
local jwttoken = require "token"
local auth_header = ngx.var.http_Authorization
local secret = "5pil6aOO5YaN576O5Lmf5q+U5LiN5LiK5bCP6ZuF55qE56yR"
--通过token.lua中的check方法校验
local result = jwttoken.check(auth_header,secret)

```

