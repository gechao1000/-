## Helm 是 Kubernetes 生态系统中的一个软件包管理工具

> https://helm.sh/

应用发布者：打包应用、管理应用依赖关系、管理应用版本并发布应用到软件仓库

使用者：以简单的方式在 Kubernetes 上查找、安装、升级、回滚、卸载应用程序

#### 组件及相关术语

* **Helm：** 是一个命令行下的客户端工具。主要用于 Kubernetes 应用程序 Chart 的创建、打包、发布以及创建和管理本地和远程的 Chart 仓库
* **Tiller：** 是 Helm 的服务端，部署在 Kubernetes 集群中。Tiller 用于接收 Helm 的请求，并根据 Chart 生成 Kubernetes 的部署文件（ Helm 称为 Release ），然后提交给 Kubernetes 创建应用。Tiller 还提供了 Release 的升级、删除、回滚等一系列功能
* **Chart：** Helm 的软件包，采用 TAR 格式。类似于 APT 的 DEB 包或者 YUM 的 RPM 包，其包含了一组定义 Kubernetes 资源相关的 YAML 文件
* **Repoistory：** Helm 的软件仓库，Repository 本质上是一个 Web 服务器，该服务器保存了一系列的 Chart 软件包以供用户下载，并且提供了一个该 Repository 的 Chart 包的清单文件以供查询。Helm 可以同时管理多个不同的 Repository
* **Release：** 使用 `helm install` 命令在 Kubernetes 集群中部署的 Chart 称为 Release，可以理解为 Helm 使用 Chart 包部署的一个应用实例

#### 安装 helm 客户端

* 脚本安装

```shell
curl https://raw.githubusercontent.com/kubernetes/helm/master/scripts/get | bash
```

* 安装包

```shell
# 下载二进制包
https://github.com/helm/helm/releases

# 解压
cp linux-amd64/helm /usr/local/bin/
```

#### 安装 helm 服务端(Tiller)

1. 创建 ServiceAccount

```shell
kubectl apply -f https://raw.githubusercontent.com/pingcap/tidb-operator/master/manifests/tiller-rbac.yaml
```

2. 初始化

```shell
helm init --service-account tiller --tiller-image registry.cn-hangzhou.aliyuncs.com/google_containers/tiller:v2.15.2 
```

3. 查看状态

```shell
helm version

kubectl get pod -n kube-system -l name=tiller
```

4. 添加 TiDB repo

```shell
helm repo add pingcap https://charts.pingcap.org/

helm search pingcap -l

helm repo update
```

## 参考

* 生成配置文件

```
helm init --service-account tiller --output yaml > tiller.yaml

# 修改 yaml
apiVersion: apps/v1
gcr.io 改为 gcr.azk8s.cn
spec:
  replicas: 1
  # Deployment添加 selector
  selector:
    matchLabels:
      app: helm
      name: tiller

kubectl apply -f tiller.yaml
```
* 不生成配置文件(不可用)

```shell
helm init --upgrade --tiller-image gcr.azk8s.cn/google_containers/tiller:v2.14.3 --stable-repo-url http://mirror.azure.cn/kubernetes/charts/ --service-account tiller --override spec.selector.matchLabels.'name'='tiller',spec.selector.matchLabels.'app'='helm' --output yaml | sed 's@apiVersion: extensions/v1beta1@apiVersion: apps/v1@' | kubectl apply -f -
```

* `tiller-rbac.yaml`， 为 Tiller 创建服务帐号和绑定角色

```
apiVersion: v1
kind: ServiceAccount
metadata:
  name: tiller
  namespace: kube-system
---
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: tiller-cluster-rule
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: cluster-admin
subjects:
- kind: ServiceAccount
  name: tiller
  namespace: kube-system
```

* 为 Tiller 设置账号

```shell
kubectl patch deploy --namespace kube-system tiller-deploy -p '{"spec":{"template":{"spec":{"serviceAccount":"tiller"}}}}'
```

* 卸载 Tiller

```shell
helm reset
```

