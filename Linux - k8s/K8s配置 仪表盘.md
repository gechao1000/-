### Kubernetes Dashboard

> https://github.com/kubernetes/dashboard

1. 安装

```
https://raw.githubusercontent.com/kubernetes/dashboard/v2.0.0-beta4/aio/deploy/recommended.yaml
```

2. 暴露端口用来测试，正式环境使用 Ingress 

```yaml
# 省略部分代码...
# ------------------- Dashboard Service ------------------- #
kind: Service
apiVersion: v1
metadata:
  labels:
    k8s-app: kubernetes-dashboard
  name: kubernetes-dashboard
  namespace: kube-system
spec:
  # 修改类型为 NodePort 访问
  type: NodePort
  ports:
    - port: 443
      targetPort: 8443
      # 设置端口号为 30001
      nodePort: 30001
  selector:
    k8s-app: kubernetes-dashboard
```

3. 部署到集群

```shell
# 部署
kubectl create -f recommended.yaml

# 查看
kubectl -n kubernetes-dashboard get pods
kubectl -n kubernetes-dashboard get service kubernetes-dashboard
kubectl -n kubernetes-dashboard describe service kubernetes-dashboard
```

4. 创建登录账号

```yaml
apiVersion: v1
kind: ServiceAccount
metadata:
  name: admin-user
  namespace: kubernetes-dashboard
---
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: admin-user
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: cluster-admin
subjects:
- kind: ServiceAccount
  name: admin-user
  namespace: kubernetes-dashboard
```

5. 打印 Token

```shell
kubectl -n kubernetes-dashboard describe secret $(kubectl -n kubernetes-dashboard get secret | grep admin-user | awk '{print $1}')
```

### Kuboard

>  https://kuboard.cn/ 

1. 安装

```shell
kubectl apply -f https://kuboard.cn/install-script/kuboard.yaml
```

2. 您集群中任意节点的 32567 端口（http://any-of-your-node-ip:32567)

```
# 获取 Token
kubectl -n kube-system describe secret $(kubectl -n kube-system get secret | grep kuboard-user | awk '{print $1}')
```

