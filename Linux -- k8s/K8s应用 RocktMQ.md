## 消息队列

生产者 / 消费者模型， **先进先出** 的数据结构

有 Brocker: RocketMQ(前身ActiveMQ)

无 Brocker: ZeroMQ

* 直连，点对点
* 传输层
* 每个节点都既是生产者又是消费者

消息类型

* **普通消息：** 消息队列 MQ 中无特性的消息
* **事务消息：** 分布事务功能（两阶段提交，多方事务），能够达到事务最终一致性状态
* **延时消息：** 允许消息生产者对指定消息进行定时（延时）投递
* **顺序消息：** 允许消息消费者按照消息发送的顺序对消息进行消费

## Quick Start

1. 部署 Operator

```shell
git clone https://github.com/apache/rocketmq-operator.git
cd rocketmq-operator

./install-operator.sh

# 等待 Operator 启动成功后再进行下步操作
kubectl get pods
```

2. 创建数据卷 NFS

```shell
mkdir -p /data/rocketmq
chmod a+rw /data/rocketmq

vi /etc/exports
# 底部增加
/data/rocketmq *(rw,sync,no_subtree_check,no_root_squash)
```

3. 创建 StorageClass `deploy/storage/nfs-client.yaml`
   * 修改 NFS_SERVER 和 NFS_PATH 配置
   * 默认为 quay.io 修改为 quay.azk8s.cn
   * 运行脚本 `cd deploy/storage && ./deploy-storage-class.sh`

4.  部署 NameServer `example/rocketmq_v1alpha1_nameservice_cr.yaml`
   * storageMode 改为 `NFS`
   * 集群实例数量 `size: 1`

5. 部署 Broker `example/rocketmq_v1alpha1_broker_cr.yaml`
   * storageMode 改为 `NFS`
   * 集群实例数量改为 `size: 1`，1主1从
   * nameServers 改为 name-service-x 的地址

6. 部署控制台，新建 `rocketmq-console.yaml`

```yaml
kind: Deployment
apiVersion: apps/v1
metadata:
  name: rocketmq-console
  labels:
    app: rocketmq-console
spec:
  replicas: 1
  selector:
    matchLabels:
      app: rocketmq-console
  template:
    metadata:
      labels:
        app: rocketmq-console
    spec:
      containers:
      - name: rocketmq-console
        image: styletang/rocketmq-console-ng
        env:
        - name: JAVA_OPTS
          # 修改为你自己的 NameServer 地址
          value: -Drocketmq.namesrv.addr=192.168.80.120:9876 -Dcom.rocketmq.sendMessageWithVIPChannel=false
---
apiVersion: v1
kind: Service
metadata:
  name: rocketmq-service
spec:
  type: NodePort
  ports:
    - port: 8080
      targetPort: 8080
      nodePort: 32568
  selector:
    app: rocketmq-console
```

## 集群清理流程

* 删除 Console

```
kubectl delete -f rocketmq-console.yaml
```

* 删除 Broker

```
kubectl delete -f example/rocketmq_v1alpha1_broker_cr.yaml
```

* 删除 NameServer

```
kubectl delete -f example/rocketmq_v1alpha1_nameservice_cr.yaml
```

* 删除 Operator

```
./purge-operator.sh
```

* 删除 Storage

```
cd deploy/storage
./remove-storage-class.sh
```

