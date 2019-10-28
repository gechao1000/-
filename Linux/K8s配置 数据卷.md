### K8s 的数据卷是 docker 数据卷的扩展

* 本地存储： EmptyDir，HostPath， 网络存储：NFS，GlusterFS，PV/PVC

* NFS（Network File System），网络文件系统，是 FreeBSD 支持的文件系统中的一种

* 以部署 MySQL8 为例，采用 **NFS + PV/PVC** 网络存储方案实现我们的 Kubernetes 数据持久化

### NFS服务端

1. 安装NFS服务端

```shell
# 创建一个目录作为共享文件目录
mkdir -p /usr/local/kubernetes/volumes
chmod a+rw /usr/local/kubernetes/volumes

# centos
yum install -y nfs-utils

# ubuntu
apt-get install -y nfs-kernel-server
```

2. 配置 NFS 服务目录
   * ***：**任何 IP 都可以访问
   * **rw：** 读写权限
   * **sync：** 同步权限
   * **no_subtree_check：** 如果输出目录是一个子目录，NFS 服务器不检查其父目录的权限
   * **no_root_squash：** 客户端连接服务端时如果使用的是 root，那么也拥有对服务端分享的目录的 root 权限

```shell
vi /etc/exports
# 在尾部新增一行
/usr/local/kubernetes/volumes *(rw,sync,no_subtree_check,no_root_squash)

# 启动服务
## centos
systemctl start nfs
systemctl enable nfs-serve
systemctl enable rpcbind
## ubuntu
/etc/init.d/nfs-kernel-server restart

# 查看
rpcinfo -p 192.168.80.130
showmount -e localhost
```

### NFS 客户端

> 每个 Node 都需要安装

1. 安装客户端的目的是验证是否可以上传文件到服务端

```shell
yum install -y nfs-utils

apt-get install -y nfs-common
```

2. 挂载目录

```shell
mkdir -p /usr/local/kubernetes/volumes-mount

mount -t nfs 192.168.80.130:/usr/local/kubernetes/volumes /usr/local/kubernetes/volumes-mount

# 取消 NFS 客户端挂载
umount -fr /usr/local/kubernetes/volumes-mount
```

3. 测试文件上传

```shell
# 查看是否挂载成功
df -h

# 创建文件，nfs-server 有则表示成功
ip addr > /usr/local/kubernetes/volumes-mount/test.txt
```

### 使用数据卷

* **Persistent Volume（持久卷）**
*  **Persistent Volume Claim（持久卷消费者）**

1. 定义PV `nfs-pv-mysql.yml`

```yaml
apiVersion: v1
kind: PersistentVolume
metadata:
  name: nfs-pv-mysql
spec:
  # 设置容量
  capacity:
    storage: 5Gi
  # 访问模式
  accessModes:
    # 该卷能够以读写模式被多个节点同时加载
    - ReadWriteMany
  # 回收策略，这里是基础擦除 `rm-rf/thevolume/*`
  persistentVolumeReclaimPolicy: Recycle
  nfs:
    # NFS 服务端配置的路径
    path: "/usr/local/kubernetes/volumes"
    # NFS 服务端地址
    server: 192.168.80.130
    readOnly: false
```

2. 定义 PVC `nfs-pvc-mysql-myshop.yml`

```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: nfs-pvc-mysql-myshop
spec:
  accessModes:
  # 需要使用和 PV 一致的访问模式
  - ReadWriteMany
  # 按需分配资源
  resources:
     requests:
       storage: 1Gi
```

3. 查看

```shell
kubectl get pv
kubectl get pvc
```

### 部署 MySQL

> 每台 Node 都安装 NFS 客户端
>
> ConfigMap 是用来存储配置文件的 Kubernetes 资源对象

1. 使用 ConfigMap 配置 MySQL

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: mysql-myshop-config
data:
  # 这里是键值对数据
  mysqld.cnf: |
    [client]
    port=3306
    [mysql]
    no-auto-rehash
    [mysqld]
    skip-host-cache
    skip-name-resolve
    default-authentication-plugin=mysql_native_password
    character-set-server=utf8mb4
    collation-server=utf8mb4_general_ci
    explicit_defaults_for_timestamp=true
    lower_case_table_names=1
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mysql-myshop
spec:
  replicas: 1
  selector:
    matchLabels:
      name: mysql-myshop
  template:
    metadata:
      labels:
        name: mysql-myshop
    spec:
      containers:
        - name: mysql-myshop
          image: mysql:8.0.16
          imagePullPolicy: IfNotPresent
          ports:
            - containerPort: 3306
          env:
            - name: MYSQL_ROOT_PASSWORD
              value: "123456"
          volumeMounts:
            # 以数据卷的形式挂载 MySQL 配置文件目录
            - name: cm-vol-myshop
              mountPath: /etc/mysql/conf.d
            - name: nfs-vol-myshop
              mountPath: /var/lib/mysql
      volumes:
        # 将 ConfigMap 中的内容以文件形式挂载进数据卷
        - name: cm-vol-myshop
          configMap:
            name: mysql-myshop-config
            items:
                # ConfigMap 中的 Key
              - key: mysqld.cnf
                # ConfigMap Key 匹配的 Value 写入名为 mysqld.cnf 的文件中
                path: mysqld.cnf
        - name: nfs-vol-myshop
          persistentVolumeClaim:
            claimName: nfs-pvc-mysql-myshop
---
apiVersion: v1
kind: Service
metadata:
  name: mysql-myshop
spec:
  ports:
    - port: 3306
      targetPort: 3306
  type: LoadBalancer
  selector:
    name: mysql-myshop
```

2. 查看 ConfigMap

```shell
kubectl get cm
kubectl describe cm <ConfigMap Name>

# kubectl get ...
pod, node, service, deployment, ingress, pv, pvc, cm
```

