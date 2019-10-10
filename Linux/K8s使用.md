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

