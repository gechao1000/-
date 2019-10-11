#### 基本命令

```shell
# 检查组件运行状态
kubectl get cs

# 检查 Master 状态
kubectl cluster-info

# 检查 Nodes 状态
kubectl get nodes

# 检查 Pods 的状态
kubectl get pods

# 查看已部署的服务
kubectl get deployment

# 查看已发布的服务
kubectl get services
```

#### 运行一个容器实例

```shell
# 使用 kubectl 命令创建两个监听 80 端口的 Nginx Pod（Kubernetes 运行容器的最小单元）
kubectl run nginx --image=nginx --replicas=2 --port=80

# 使用负载均衡模式发布服务，让用户可以访问
kubectl expose deployment nginx --port=80 --type=LoadBalancer

# 查看服务详情
kubectl describe service nginx

# 停止服务
kubectl delete deployment nginx
kubectl delete service nginx
```

#### 通过yaml运行容器

```shell
# 部署 Deployment
kubectl create -f nginx-deployment.yml
kubectl delete -f nginx-deployment.yml

# 发布 Service
kubectl create -f nginx-service.yml
kubectl delete -f nginx-service.yml

# 一次性部署 Deployment 和 Service
kubectl create -f nginx.yml
kubectl delete -f nginx.yml
```

1. `nginx-deployment.yml`

```yaml
# API 版本号
apiVersion: apps/v1
# 类型，如：Pod/ReplicationController/Deployment/Service/Ingress
kind: Deployment
# 元数据
metadata:
  # Kind 的名称
  name: nginx-app
spec:
  selector:
    matchLabels:
      app: nginx
  # 部署的实例数量
  replicas: 2
  template:
    metadata:
      labels:
        # 容器标签的名字，发布 Service 时，selector 需要和这里对应
        app: nginx
    spec:
      # 配置容器，数组类型，说明可以配置多个容器
      containers:
      # 容器名称
      - name: nginx
        # 容器镜像
        image: nginx:1.17
        # 只有镜像不存在时，才会进行镜像拉取
        imagePullPolicy: IfNotPresent
        # 暴露端口
        ports:
        # Pod 端口
        - containerPort: 80
```

2. `nginx-service.yml`

```yaml
# API 版本号
apiVersion: v1
# 类型，如：Pod/ReplicationController/Deployment/Service/Ingress
kind: Service
# 元数据
metadata:
  # Kind 的名称
  name: nginx-http
spec:
  # 暴露端口
  ports:
    ## Service 暴露的端口
    - port: 80
      ## Pod 上的端口，这里是将 Service 暴露的端口转发到 Pod 端口上
      targetPort: 80
  # 类型
  type: LoadBalancer
  # 标签选择器
  selector:
    # 需要和上面部署的 Deployment 标签名对应
    app: nginx
```

3. `nginx.yml`

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx-app
spec:
  selector:
    matchLabels:
      app: nginx
  replicas: 2
  template:
    metadata:
      labels:
        app: nginx
    spec:
      containers:
      - name: nginx
        image: nginx
        ports:
        - containerPort: 80
---
apiVersion: v1
kind: Service
metadata:
  name: nginx-http
spec:
  ports:
    - port: 80
      targetPort: 80
      # 可以指定 NodePort 端口，默认范围是：30000-32767
      # nodePort: 30080
  type: LoadBalancer
  selector:
    app: nginx
```

4. 镜像拉取策略`ImagePullPolicy`
   * **Always：** 不管镜像是否存在都会进行一次拉取，`:latest` 标签的镜像默认为 `Always`
   * **Never：** 不管镜像是否存在都不会进行拉取
   * **IfNotPresent(默认)：** 只有镜像不存在时，才会进行镜像拉取
