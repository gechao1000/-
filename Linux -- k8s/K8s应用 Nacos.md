## 准备 NFS

```shell
# 创建 Nacos 所需共享目录
mkdir -p /data/nfs-share
mkdir -p /data/mysql-master
mkdir -p /data/mysql-slave

# 给目录增加读写权限
chmod a+rw /data/nfs-share
chmod a+rw /data/mysql-master
chmod a+rw /data/mysql-slave

# 配置 NFS 服务目录
vi /etc/exports
# 底部增加
/data/nfs-share *(rw,sync,no_subtree_check,no_root_squash)
/data/mysql-master *(rw,sync,no_subtree_check,no_root_squash)
/data/mysql-slave *(rw,sync,no_subtree_check,no_root_squash)
```

## 部署 NFS

> https://github.com/nacos-group/nacos-k8s.git

1. 创建角色 `kubectl create -f deploy/nfs/rbac.yaml`
2. 创建 ServiceAccount，修改 `deploy/nfs/deployment.yaml`
   * 修改 NFS 地址

3. 部署 StoreageClass `kubectl create -f deploy/nfs/class.yaml`
4. 部署主库，修改 `deploy/mysql/mysql-master-nfs.yaml`
   * 修改 NFS 地址

5. 部署从库，修改 `deploy/mysql/mysql-slave-nfs.yaml`
   * 修改 NFS 地址

## 部署 Nacos

1. 创建 nacos `kubectl create -f deploy/nacos/nacos-pvc-nfs.yaml`

2. 路由 nacos，创建 Ingress 配置文件 `nacos.yml`

```yaml
apiVersion: networking.k8s.io/v1beta1
kind: Ingress
metadata:
  name: nacos-web
  annotations:
    kubernetes.io/ingress.class: "nginx"
    nginx.ingress.kubernetes.io/use-regex: "true"
    nginx.ingress.kubernetes.io/proxy-connect-timeout: "600"
    nginx.ingress.kubernetes.io/proxy-send-timeout: "600"
    nginx.ingress.kubernetes.io/proxy-read-timeout: "600"
    nginx.ingress.kubernetes.io/proxy-body-size: "10m"
    nginx.ingress.kubernetes.io/rewrite-target: /
spec:
  rules:
  - host: nacos.funtl.com
    http:
      paths:
      - path:
        backend:
          serviceName: nacos-headless
          servicePort: 8848
```

3. 扩容

```
kubectl scale sts nacos --replicas=3

# 手动删除 Pod ，让 k8s 自动恢复
kubectl delete pods nacos-$i

# 再观察 naming-raft.log 日志
```

4. 临时暴露端口

```
kubectl port-forward svc/nacos-headless 8848:8848 --address 0.0.0.0

# 检查
http://192.168.80.110:8848/nacos/v1/ns/raft/state
```

5. 查看 Ingress 在哪个节点

```
# ingress 只有一个实例
kubectl get pod -n ingress-nginx -o wide
```

