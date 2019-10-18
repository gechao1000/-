## 使用 StatefulSet 部署集群

> Helm 简易安装

解决 Pod 重启、迁移后，Pod 的 IP、主机名等网络标识会改变而带来的问题

结合 PV/PVC，可以实现稳定的持久化存储，就算 Pod 重新调度后，还是能访问到原先的持久化数据

1. 创建 NFS

```shell
# 创建 6 个节点的 Redis 集群

mkdir -p /data/redis/n1
mkdir -p /data/redis/n2
mkdir -p /data/redis/n3
mkdir -p /data/redis/n4
mkdir -p /data/redis/n5
mkdir -p /data/redis/n6

chmod a+rw /data/redis
chmod a+rw /data/redis/*

vi /etc/exports
# 底部增加
/data/redis/n1 *(rw,sync,no_subtree_check,no_root_squash)
/data/redis/n2 *(rw,sync,no_subtree_check,no_root_squash)
/data/redis/n3 *(rw,sync,no_subtree_check,no_root_squash)
/data/redis/n4 *(rw,sync,no_subtree_check,no_root_squash)
/data/redis/n5 *(rw,sync,no_subtree_check,no_root_squash)
/data/redis/n6 *(rw,sync,no_subtree_check,no_root_squash)
```

2. 创建 PV `nfs-pv-redis.yaml`

```yaml
apiVersion: v1
kind: PersistentVolume
metadata:
  name: nfs-pv-redis-n1
  labels:
    app: redis
    type: redis-nfs
    path: nfs-redis-n1
    podindex: "0"
spec:
  capacity:
    storage: 1Gi
  accessModes:
    - ReadWriteMany
  nfs:
    server: 192.168.80.128
    path: "/data/redis/n1"
---
apiVersion: v1
kind: PersistentVolume
metadata:
  name: nfs-pv-redis-n2
  labels:
    app: redis
    type: redis-nfs
    path: nfs-redis-n2
    podindex: "1"
spec:
  capacity:
    storage: 1Gi
  accessModes:
    - ReadWriteMany
  nfs:
    server: 192.168.80.128
    path: "/data/redis/n2"
---
apiVersion: v1
kind: PersistentVolume
metadata:
  name: nfs-pv-redis-n3
  labels:
    app: redis
    type: redis-nfs
    path: nfs-redis-n3
    podindex: "2"
spec:
  capacity:
    storage: 1Gi
  accessModes:
    - ReadWriteMany
  nfs:
    server: 192.168.80.128
    path: "/data/redis/n3"
---
apiVersion: v1
kind: PersistentVolume
metadata:
  name: nfs-pv-redis-n4
  labels:
    app: redis
    type: redis-nfs
    path: nfs-redis-n4
    podindex: "3"
spec:
  capacity:
    storage: 1Gi
  accessModes:
    - ReadWriteMany
  nfs:
    server: 192.168.80.128
    path: "/data/redis/n4"
---
apiVersion: v1
kind: PersistentVolume
metadata:
  name: nfs-pv-redis-n5
  labels:
    app: redis
    type: redis-nfs
    path: nfs-redis-n5
    podindex: "4"
spec:
  capacity:
    storage: 1Gi
  accessModes:
    - ReadWriteMany
  nfs:
    server: 192.168.80.128
    path: "/data/redis/n5"
---
apiVersion: v1
kind: PersistentVolume
metadata:
  name: nfs-pv-redis-n6
  labels:
    app: redis
    type: redis-nfs
    path: nfs-redis-n6
    podindex: "5"
spec:
  capacity:
    storage: 1Gi
  accessModes:
    - ReadWriteMany
  nfs:
    server: 192.168.80.128
    path: "/data/redis/n6"
```

3. 创建 PVC `nfs-pvc-redis.yml`

```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  # 这里的名称不要修改，后面会用到
  name: redis-data-redis-app-0
  labels:
    app: redis
    type: redis-nfs-claim
spec:
  accessModes:
    - ReadWriteMany
  resources:
    requests:
      storage: 1Gi
  selector:
    matchLabels:
      app: redis
      path: nfs-redis-n1
      podindex: "0"
---
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: redis-data-redis-app-1
  labels:
    app: redis
    type: redis-nfs-claim
spec:
  accessModes:
    - ReadWriteMany
  resources:
    requests:
      storage: 1Gi
  selector:
    matchLabels:
      app: redis
      path: nfs-redis-n2
      podindex: "1"
---
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: redis-data-redis-app-2
  labels:
    app: redis
    type: redis-nfs-claim
spec:
  accessModes:
    - ReadWriteMany
  resources:
    requests:
      storage: 1Gi
  selector:
    matchLabels:
      app: redis
      path: nfs-redis-n3
      podindex: "2"
---
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: redis-data-redis-app-3
  labels:
    app: redis
    type: redis-nfs-claim
spec:
  accessModes:
    - ReadWriteMany
  resources:
    requests:
      storage: 1Gi
  selector:
    matchLabels:
      app: redis
      path: nfs-redis-n4
      podindex: "3"
---
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: redis-data-redis-app-4
  labels:
    app: redis
    type: redis-nfs-claim
spec:
  accessModes:
    - ReadWriteMany
  resources:
    requests:
      storage: 1Gi
  selector:
    matchLabels:
      app: redis
      path: nfs-redis-n5
      podindex: "4"
---
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: redis-data-redis-app-5
  labels:
    app: redis
    type: redis-nfs-claim
spec:
  accessModes:
    - ReadWriteMany
  resources:
    requests:
      storage: 1Gi
  selector:
    matchLabels:
      app: redis
      path: nfs-redis-n6
      podindex: "5"
```

4. 创建 Redis ConfigMap

```
# 配置文件 redis.conf
appendonly yes
cluster-enabled yes
cluster-config-file /var/lib/redis/nodes.conf
cluster-node-timeout 5000
dir /var/lib/redis
port 6379

kubectl create configmap redis-conf --from-file=redis.conf
```

5. 创建 Redis Headless Service `headless-redis.yaml`

```yaml
apiVersion: v1
kind: Service
metadata:
  name: redis-service
  labels:
    app: redis
spec:
  ports:
  - name: redis-port
    port: 6379
  clusterIP: None
  selector:
    app: redis
    appCluster: redis-cluster
```

6. 创建 Redis StatefulSet `statefulset-redis.yaml`

```yaml
apiVersion: apps/v1
kind: StatefulSet
metadata:
  # 这里的名称不要修改
  name: redis-app
spec:
  serviceName: "redis-service"
  # 创建 6 个 Redis 实例
  replicas: 6
  selector:
    matchLabels:
      app: redis
      appCluster: redis-cluster
  template:
    metadata:
      labels:
        app: redis
        appCluster: redis-cluster
    spec:
      terminationGracePeriodSeconds: 20
      # affinity 是 k8s 中的一个重要概念，负责节点亲和性/反亲和性特性 和 Pod 亲和性/反亲和性特性
      affinity:
        # 配置反亲和特性
        # 其决定了某个 Pod 不可以和哪些 Pod 部署在同一拓扑域
        # 可以用于将一个服务的 Pod 分散在不同的主机或者拓扑域中，提高服务本身的稳定性
        podAntiAffinity:
          # 在调度期间尽量满足亲和性或者反亲和性规则
          # 如果不能满足规则，Pod 也有可能被调度到对应的主机上
          # 在之后的运行过程中，系统不会再检查这些规则是否满足
          preferredDuringSchedulingIgnoredDuringExecution:
          - weight: 100
            podAffinityTerm:
              labelSelector:
                # 规定了 Redis Pod 要尽量不要调度到包含 app 为 redis 的 Node
                # 既已经存在 Redis 的 Node 上尽量不要再分配 Redis Pod
                # 但我们有 6 个 Redis 实例，Node 只有三个，所以这里只能均匀分配了
                matchExpressions:
                - key: app
                  operator: In
                  values:
                  - redis
              topologyKey: kubernetes.io/hostname
      containers:
      - name: redis
        image: "redis:5.0.6"
        command:
          - "redis-server"
        args:
          - "/etc/redis/redis.conf"
          - "--protected-mode"
          - "no"
        resources:
          requests:
            cpu: "100m"
            memory: "100Mi"
        ports:
            - name: redis
              containerPort: 6379
              protocol: "TCP"
            - name: cluster
              containerPort: 16379
              protocol: "TCP"
        volumeMounts:
          - name: "redis-conf"
            mountPath: "/etc/redis"
          - name: "redis-data"
            mountPath: "/var/lib/redis"
      volumes:
      - name: "redis-conf"
        configMap:
          name: "redis-conf"
          items:
            - key: "redis.conf"
              path: "redis.conf"
  volumeClaimTemplates:
  - metadata:
      # 这里的名称不要修改
      name: redis-data
    spec:
      accessModes: [ "ReadWriteMany" ]
      resources:
        requests:
          storage: 1Gi
```

## 初始化 Redis 集群

> 利用 Redis Trib 工具
>

1. K8S 会为 Pod 分配域名

```shell
# $(podname).$(service name).$(namespace).svc.cluster.local
# 如：redis-app-0.redis-service.default.svc.cluster.local

# 前台运行 K8s
kubectl run -i --tty --image busybox dns-test --restart=Never --rm /bin/sh

ping redis-app-0.redis-service.default.svc.cluster.local
```


2. Dockerfile，可以手动构建，dockerhub已存在

```dockerfile
FROM ubuntu:18.04
MAINTAINER Lusifer <topsale@vip.qq.com>
# 更新软件源（这里用的是中科大的源，因为阿里云的源有个依赖无法下载）
RUN echo 'deb http://mirrors.ustc.edu.cn/ubuntu/ bionic main restricted universe multiverse' > /etc/apt/sources.list && \
    echo 'deb http://mirrors.ustc.edu.cn/ubuntu/ bionic-security main restricted universe multiverse' >> /etc/apt/sources.list && \
    echo 'deb http://mirrors.ustc.edu.cn/ubuntu/ bionic-updates main restricted universe multiverse' >> /etc/apt/sources.list && \
    echo 'deb http://mirrors.ustc.edu.cn/ubuntu/ bionic-proposed main restricted universe multiverse' >> /etc/apt/sources.list && \
    echo 'deb http://mirrors.ustc.edu.cn/ubuntu/ bionic-backports main restricted universe multiverse' >> /etc/apt/sources.list && \
    apt-get update
# 安装所需依赖
RUN apt-get install -y vim wget python2.7 python-pip redis-tools dnsutils
# 安装 Redis Trib，注意默认安装版本为 0.6.1 是有 BUG 的
RUN pip install redis-trib==0.5.1
```

3. 以前台交互式方式启动并进入容器

```shell
kubectl run -i --tty redis-tribe --image=lusiferlee/redis-tribe --restart=Never --rm /bin/bash
```

4. 创建 Master 集群

```shell
redis-trib.py create \
  `dig +short redis-app-0.redis-service.default.svc.cluster.local`:6379 \
  `dig +short redis-app-1.redis-service.default.svc.cluster.local`:6379 \
  `dig +short redis-app-2.redis-service.default.svc.cluster.local`:6379
```

5. 为每个 Master 添加 Slave

```shell
redis-trib.py replicate \
  --master-addr `dig +short redis-app-0.redis-service.default.svc.cluster.local`:6379 \
  --slave-addr `dig +short redis-app-3.redis-service.default.svc.cluster.local`:6379
  
  
redis-trib.py replicate \
  --master-addr `dig +short redis-app-1.redis-service.default.svc.cluster.local`:6379 \
  --slave-addr `dig +short redis-app-4.redis-service.default.svc.cluster.local`:6379
  
  
redis-trib.py replicate \
  --master-addr `dig +short redis-app-2.redis-service.default.svc.cluster.local`:6379 \
  --slave-addr `dig +short redis-app-5.redis-service.default.svc.cluster.local`:6379
```

4. 验证集群是否成功

```
kubectl exec -it redis-app-0 /bin/bash
/usr/local/bin/redis-cli -c

> cluster nodes
> cluster info
```

