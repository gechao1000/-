## 教程
https://www.cnblogs.com/surpassme/p/11832438.html

## network，目录
docker network create redis-net
mkdir -p /home/redis-cluster

## 初始化配置文件
for port in $(seq 7001 7006); \
do \
  mkdir -p ./${port}/conf  \
  && PORT=${port} envsubst < ./redis-cluster.tmpl > ./${port}/conf/redis.conf \
  && mkdir -p ./${port}/data; \
done


## 容器
for port in $(seq 7001 7006); \
do \
   docker run -it -d -p ${port}:${port} -p 1${port}:1${port} \
  --privileged=true -v /home/redis-cluster/${port}/conf/redis.conf:/usr/local/etc/redis/redis.conf \
  --privileged=true -v /home/redis-cluster/${port}/data:/data \
  --restart always --name redis-${port} --net redis-net \
  --sysctl net.core.somaxconn=1024 redis redis-server /usr/local/etc/redis/redis.conf; \
done


## 移除容器
for port in $(seq 7001 7006); \
do \
  docker stop redis-${port}  \
  && docker rm redis-${port} \
  && rm -rf ${port}; \
done


## 查看ip
docker network inspect redis-net | grep -i -E "name|ipv4address"

## 进入一个容器节点, 创建集群

redis-cli --cluster create 172.18.0.2:7001 172.18.0.3:7002 172.18.0.4:7003 172.18.0.5:7004 172.18.0.6:7005 172.18.0.7:7006 --cluster-replicas 1

## 添加主节点 7007

./redis-cli --cluster add-node 192.168.32.128:7007 192.168.32.128:7001

### 重新分配hash槽，将节点7001，7002，7003中的100个哈希槽移动到7007
./redis-cli --cluster reshard 192.168.32.128:7001 --cluster-from 64448f5eb278d432a2c58603fcb29f49b9185f1e,9dc00bcbb91e151ada6f1b57c3721ad6451b4321,cb7b6522f7b70adfbacd8b8616daa94b113c5f0c  --cluster-to 39078190c222433c6410c45ce9a5a37c49d8d65d  --cluster-slots 100


## 添加从节点，7008作为7007的从节点
./redis-cli --cluster add-node 192.168.32.128:7008 192.168.32.128:7007 --cluster-slave --cluster-master-id 39078190c222433c6410c45ce9a5a37c49d8d65d 

## 移除从节点
./redis-cli --cluster del-node 192.168.32.128:7008 12059991200064c5e551d6bd855eac90ae9e0982

## 移除主节点，需要先归还哈希槽
./redis-cli --cluster reshard 192.168.32.128:7007 --cluster-from 39078190c222433c6410c45ce9a5a37c49d8d65d --cluster-to cb7b6522f7b70adfbacd8b8616daa94b113c5f0c --cluster-slots 100 --cluster-yes

./redis-cli --cluster del-node 192.168.32.128:7007 39078190c222433c6410c45ce9a5a37c49d8d65d