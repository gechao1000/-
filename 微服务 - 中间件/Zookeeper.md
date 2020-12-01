1. 安装
```plain
docker run --name zk -p 2181:2181 -d zookeeper
# 指定网络
docker network create --subnet=172.18.0.0/16 docker_network
docker run --name zookeeper --network=docker_network --ip=172.18.0.5 -p 2181:2181  --privileged=true -d zookeeper
# ui
docker run --rm -p 9090:9090 -e ZK_SERVER=zookeeper:2181 juris/zkui
# ui 本地构建
https://github.com/DeemOpen/zkui
```
