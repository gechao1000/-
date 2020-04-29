## TiDB 开源分布式关系型数据库

 TiDB Operator 是 TiDB 在 Kubernetes 平台上的自动化部署运维工具 

`本地卷` 做读写，`网络卷` 做备份


>  https://pingcap.com/ 
>
>  https://github.com/pingcap/tidb-operator.git 
>
>   https://pingcap.com/docs-cn/v3.0/tidb-in-kubernetes/deploy/tidb-operator/ 

#### 部署本地卷

1. 每个 Node 都要 添加新的磁盘 

```shell
# 查看新磁盘
fdisk -l

# 格式化磁盘
mkfs.xfs /dev/sdb
```

2. 挂载磁盘

```shell
# 创建挂载点文件夹
DISK_UUID=$(blkid -s UUID -o value /dev/sdb)
mkdir -p /mnt/$DISK_UUID
mount /dev/sdb /mnt/$DISK_UUID

# /etc/fstab 持久化 mount
echo UUID=`sudo blkid -s UUID -o value /dev/sdb` /mnt/$DISK_UUID xfs defaults 0 2 | sudo tee -a /etc/fstab
```

3.  创建多个目录并 `mount` 到 `discovery directory` 

```shell
for i in $(seq 1 10); do
  sudo mkdir -p /mnt/${DISK_UUID}/vol${i} /mnt/disks/${DISK_UUID}_vol${i}
  sudo mount --bind /mnt/${DISK_UUID}/vol${i} /mnt/disks/${DISK_UUID}_vol${i}
done
```

4.  `/etc/fstab` 自动挂载 

```shell
for i in $(seq 1 10); do
  echo /mnt/${DISK_UUID}/vol${i} /mnt/disks/${DISK_UUID}_vol${i} none bind 0 0 | sudo tee -a /etc/fstab
done
```

5.  为 `tidb-operator` 创建 `local-volume-provisioner` 

```shell
# quay.io 源修改为 quay.azk8s.cn
kubectl apply -f manifests/local-dind/local-volume-provisioner.yaml

kubectl get po -n kube-system -l app=local-volume-provisioner
kubectl get pv | grep local-storage
```

#### 部署 operator，方法1 (git clone)

1. 安装 crd

```shell
kubectl apply -f manifests/crd.yaml

kubectl get crd tidbclusters.pingcap.com
```

2. helm 安装 operator

```shell
# 修改 charts/tidb-operator/values.yaml 配置
kubeSchedulerImageName: registry.aliyuncs.com/google_containers/kube-scheduler

helm install charts/tidb-operator --name=tidb-operator --namespace=tidb-admin

kubectl get po -n tidb-admin -l app.kubernetes.io/name=tidb-operator
```

#### 部署 operator，方法2（官网）

> 注：当前版本不兼容最新的 kubelet，等待新版本发布

1. 安装 crd

>  iDB Operator 使用 [CRD (Custom Resource Definition)](https://kubernetes.io/docs/tasks/access-kubernetes-api/custom-resources/custom-resource-definitions/) 扩展 Kubernetes，所以要使用 TiDB Operator，必须先创建 `TidbCluster` 自定义资源类型。只需要在你的 Kubernetes 集群上创建一次即可 

```shell
kubectl apply -f https://raw.githubusercontent.com/pingcap/tidb-operator/master/manifests/crd.yaml && \
kubectl get crd tidbclusters.pingcap.com
```

2. helm 安装 operator

>  `<chart-version>` 在后续文档中代表 chart 版本，例如 `v1.0.0`，可以通过 `helm search -l tidb-operator` 查看当前支持的版本。 

```shell
# 获取你要安装的 tidb-operator chart 中的 values.yaml 文件
mkdir -p /home/tidb/tidb-operator && \
helm inspect values pingcap/tidb-operator --version=<chart-version> > /home/tidb/tidb-operator/values-tidb-operator.yaml

# 修改image地址
registry.cn-hangzhou.aliyuncs.com/google_containers/kube-scheduler

# 安装 TiDB Operator
helm install pingcap/tidb-operator --name=tidb-operator --namespace=tidb-admin --version=<chart-version> -f /home/tidb/tidb-operator/values-tidb-operator.yaml && \
kubectl get po -n tidb-admin -l app.kubernetes.io/name=tidb-operator
```

3. 删除

```shell
helm ls -a

helm delete <name> --purger
```

#### 部署 cluster，方法1

1. helm 安装 cluster

```shell
helm install charts/tidb-cluster --name=tidb-cluster --namespace=tidb
```

2. 检查状态

```shell
watch kubectl get pods --namespace tidb -l app.kubernetes.io/instance=tidb-cluster -o wide

kubectl get services --namespace tidb -l app.kubernetes.io/instance=tidb-cluster
```

#### 部署 cluster，方法2（官网）

> 注：当前版本不兼容最新的 kubelet，等待新版本发布

*  `release-name` 将会作为 Kubernetes 相关资源（例如 Pod，Service 等）的前缀名，可以起一个方便记忆的名字，要求全局唯一，通过 `helm ls -q` 可以查看集群中已经有的 `release-name`
*  `chart-version` 是 tidb-cluster chart 发布的版本，可以通过 `helm search -l tidb-cluster` 查看当前支持的版本 
*  `/home/tidb` 可以替换为你想用的目录 

1. helm 安装 cluster

```shell
mkdir -p /home/tidb/<release-name> && \
helm inspect values pingcap/tidb-cluster --version=<chart-version> > /home/tidb/<release-name>/values-<release-name>.yaml
```

2. 部署集群

```shell
helm install pingcap/tidb-cluster --name=<release-name> --namespace=<namespace> --version=<chart-version> -f /home/tidb/<release-name>/values-<release-name>.yaml
```

3. 检查状态

```shell
kubectl get po -n <namespace> -l app.kubernetes.io/instance=<release-name>
```

#### 管理 TiDB

1.   Grafana 监控面板 

```shell
kubectl port-forward svc/tidb-cluster-grafana 3000:3000 --namespace=tidb --address 0.0.0.0
```

2. 访问数据库

```shell
kubectl port-forward svc/tidb-cluster-tidb 4000:4000 --namespace=tidb --address 0.0.0.0
```

