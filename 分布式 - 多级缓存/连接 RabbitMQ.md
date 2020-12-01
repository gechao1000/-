1. RabbitMQ 需要开启STOMP
```plain
docker run -d -p 5672:5672 -p 15672:15672 -p 61613:61613 --name rabbitmq rabbitmq:management
# 安装STOMP插件
docker exec -ti rabbitmq bash
rabbitmq-plugins enable rabbitmq_stomp
```
1. 安装依赖
```plain
opm get DevonStrawn/lua-resty-rabbitmqstomp
```
1. 声明 mq.lua

[https://github.com/wingify/lua-resty-rabbitmqstomp](https://github.com/wingify/lua-resty-rabbitmqstomp)

```plain
local rabbitmq = require "resty.rabbitmqstomp"
```
