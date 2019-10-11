### Kubernetes Ingress

> https://kubernetes.io/zh/docs/concepts/services-networking/ingress/

Ingress 事实上不是一种服务类型。相反，它处于多个服务的前端，扮演着 “智能路由” 或者集群入口的角色。你可以用 Ingress 来做许多不同的事情，各种不同类型的 Ingress 控制器也有不同的能力。它允许你基于路径或者子域名来路由流量到后端服务。

Ingress 可能是暴露服务的最强大方式，但同时也是最复杂的。Ingress 控制器有各种类型，包括 Google Cloud Load Balancer， Nginx，Contour，Istio，等等。它还有各种插件，比如 cert-manager (它可以为你的服务自动提供 SSL 证书)/

### Nginx 

1. HTTP服务器：虚拟主机

```
# ...
events {
    # ...
}
http {
    # ...
    server{
        # ...
    }
    # ...
    server{
        # ...
    }
}
```

2. 反向代理

```
# 配置一个代理即 tomcat1 服务器
upstream tomcatServer1 {
	server 192.168.80.128:8081;
}

# 配置一个虚拟主机
server {
	# ...
	location / {
		proxy_pass http://tomcatServer1;
		index index.jsp index.html index.htm;
	}
}

```

3. 负载均衡

   upstream 每个设备的状态

   * `down`：表示当前的 server 暂时不参与负载
   * `weight`：默认为 1 weight 越大，负载的权重就越大
   * `max_fails`：允许请求失败的次数默认为 1 当超过最大次数时，返回 proxy_next_upstream 模块定义的错误
   * `fail_timeout`：`max_fails` 次失败后，暂停的时间
   * `backup`：其它所有的非 backup 机器 down 或者忙的时候，请求 backup 机器。所以这台机器压力会最轻

```
upstream myapp1 {
    server 192.168.80.128:8081 weight=10;
    server 192.168.80.128:8082 weight=10;
}

# 定义负载均衡设备的 Ip及设备状态 
upstream myServer {
    server 127.0.0.1:9090 down;
    server 127.0.0.1:8080 weight=2;
    server 127.0.0.1:6060;
    server 127.0.0.1:7070 backup;
}

# 在需要使用负载的 Server 节点下添加
proxy_pass http://myServer;
```

#### 安装 Ingress

```shell
# 下载 Nginx Ingress Controller 配置文件
wget https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/static/mandatory.yaml

spec:
    serviceAccountName: ...
    # 增加 hostNetwork: true，意思是开启主机网络模式，暴露 Nginx 服务端口 80
    hostNetwork: true

quay.io不能拉取，替换为 quay.mirrors.ustc.edu.cn

# 部署
kubectl apply -f mandatory.yaml
# 查看
kubectl get pods -n ingress-nginx -o wide
```

#### 部署 Tomcat[测试用例]

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: tomcat-app
spec:
  selector:
    matchLabels:
      app: tomcat
  replicas: 2
  template:
    metadata:
      labels:
        app: tomcat
    spec:
      containers:
      - name: tomcat
        image: tomcat:8.5.43
        imagePullPolicy: IfNotPresent
        ports:
        - containerPort: 8080
---
apiVersion: v1
kind: Service
metadata:
  name: tomcat-http
spec:
  ports:
    - port: 8080
      targetPort: 8080
  # ClusterIP, NodePort, LoadBalancer
  type: ClusterIP
  selector:
    app: tomcat
```

#### 部署 Ingress

* 通过命令 `kubectl apply -f ingress.yml` 部署
* 通过命令 `kubectl get ingress` 查看
* 测试访问 `curl -v http://192.168.80.120 -H 'host: k8s.funtl.com'`

```yaml
apiVersion: networking.k8s.io/v1beta1
kind: Ingress
metadata:
  name: nginx-web
  annotations:
    # 指定 Ingress Controller 的类型
    kubernetes.io/ingress.class: "nginx"
    # 指定我们的 rules 的 path 可以使用正则表达式
    nginx.ingress.kubernetes.io/use-regex: "true"
    # 连接超时时间，默认为 5s
    nginx.ingress.kubernetes.io/proxy-connect-timeout: "600"
    # 后端服务器回转数据超时时间，默认为 60s
    nginx.ingress.kubernetes.io/proxy-send-timeout: "600"
    # 后端服务器响应超时时间，默认为 60s
    nginx.ingress.kubernetes.io/proxy-read-timeout: "600"
    # 客户端上传文件，最大大小，默认为 20m
    nginx.ingress.kubernetes.io/proxy-body-size: "10m"
    # URL 重写
    nginx.ingress.kubernetes.io/rewrite-target: /
spec:
  # 路由规则
  rules:
  # 主机名，只能是域名，修改为你自己的
  - host: k8s.funtl.com
    http:
      paths:
      - path:
        backend:
          # 后台部署的 Service Name
          serviceName: tomcat-http
          # 后台部署的 Service Port
          servicePort: 8080
```

