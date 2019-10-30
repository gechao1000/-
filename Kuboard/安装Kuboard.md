 Kuboard 是基于一款基于 Kubernetes 的微服务管理面板 

#### 安装

```shell
kubectl apply -f https://kuboard.cn/install-script/kuboard.yaml
```

#### 获取token

```shell
kubectl -n kube-system describe secret $(kubectl -n kube-system get secret | grep kuboard-user | awk '{print $1}')   
```

#### 访问Kuboard

* NodePort

```
http://任意一个Worker节点的IP地址:32567/
```

