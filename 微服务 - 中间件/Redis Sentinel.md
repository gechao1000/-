1. 准备
```plain
mkdir -p /home/redis-sentinel
wget http://download.redis.io/redis-stable/sentinel.conf
# 创建三个目录 8001、8002、8003
# 复制sentinel.conf
```
2. 修改配置文件
```plain
port 8001
# 后台运行
daemonize yes
# 监听节点
sentinel monitor mymaster 192.168.115.128 7001 2
```
3. 创建容器
```plain
docker run -it --name sentinel-8001 -p 8001:8001 -v /home/redis-sentinel/8001/sentinel.conf:/sentinel.conf -d redis /bin/bash
# 进入启动sentinel
docker exec -it sentinel-8001 redis-sentinel /sentinel.conf
# 查看启动情况
docker exec -it sentinel-8001 redis-cli -p 8001
sentinel master mymaster 或 sentinel slaves mymaster
```
4. 启动方式（了解）
```plain
redis-sentinel /path/to/sentinel.conf
redis-server /path/to/sentinel.conf --sentinel
```

