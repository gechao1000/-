## link

> 此方法对容器创建的顺序有要求，如果集群内部多个容器要互访，使用就不太方便


```
# 包含最常用 linux 命令和工具
docker run -it --rm --name b1 busybox
docker run -it --rm --name b2 --link b1 busybox
```

## network

> 若访问容器中服务，可以使用这用方式访问 <网络别名>：<服务端口号> 

1. 创建一个docker网络

```
docker network ls
docker network inspect my_bridge

#如果容器没有显示指定使用的网络，默认使用bridge
#-d 指定类型 my_bridge为名称
docker network create testnet
docker network create -d bridge my_bridge  

# 使用
docker run -d --name busybox_3 --network my_bridge ...
```

2. 运行容器连接到testnet网络

```
# 参数
docker run -it --name <容器名> ---network <bridge> --network-alias <网络别名> <镜像名>

docker run -it --rm --name centos-1 --network testnet --network-alias centos-1 docker.io/centos:latest

docker run -it --rm --name centos-2 --network testnet --network-alias centos-2 docker.io/centos:latest
```