1. 安装
>lua 连接rabbitmq 需要使用STOMP插件的61613端口
```plain
docker run -d -p 5672:5672 -p 15672:15672 -p 61613:61613 --name rabbitmq rabbitmq:management
# 安装STOMP插件
docker exec -ti rabbitmq bash
rabbitmq-plugins enable rabbitmq_stomp
```
1. 安装
```plain
docker run -d -p 5672:5672 -p 15672:15672 --name rabbitmq rabbitmq:management
docker exec -it rabbitmq bash
rabbitmq-server -detached           # 启动erlang虚拟机  ，并且启动rabbitmq服务
rabbitmqctl status                  # 查看rabbitmq的启动状态
# 添加用户
rabbitmqctl add_user admin admin
rabbitmqctl list_users
rabbitmqctl set_user_tags admin administrator
```
