####  **安装后的软件版本为** 

*  Kubernetes v1.16.2 
  *  calico 3.9 
  *  nginx-ingress 1.5.5 
*  Docker 18.09.7 

------

#### 修改 hostname

```shell
# 修改 hostname
hostnamectl set-hostname your-new-host-name
# 查看修改结果
hostnamectl status
# 设置 hostname 解析
echo "127.0.0.1   $(hostname)" >> /etc/hosts
```

#### 安装 docker / kubelet

```shell
curl -sSL https://kuboard.cn/install-script/v1.16.2/install_kubelet.sh | sh
```

####  初始化 master 节点

1. 只在 master 节点执行

```shell
# 替换 x.x.x.x 为 master 节点实际 IP（请使用内网 IP）
# export 命令只在当前 shell 会话中有效，开启新的 shell 窗口后，如果要继续安装过程，请重新执行此处的 export 命令
export MASTER_IP=x.x.x.x
# 替换 apiserver.demo 为 您想要的 dnsName
export APISERVER_NAME=apiserver.demo
# Kubernetes 容器组所在的网段，该网段安装完成后，由 kubernetes 创建，事先并不存在于您的物理网络中
export POD_SUBNET=10.100.0.1/16
echo "${MASTER_IP}    ${APISERVER_NAME}" >> /etc/hosts
curl -sSL https://kuboard.cn/install-script/v1.16.2/init_master.sh | sh
```

 2. 检查 master 初始化结果

```shell
# 只在 master 节点执行

# 执行如下命令，等待 3-10 分钟，直到所有的容器组处于 Running 状态
watch kubectl get pod -n kube-system -o wide

# 查看 master 节点初始化结果
kubectl get nodes -o wide
```

#### 初始化 node 节点

1.  获得 join命令参数

```shell
# 只在 master 节点执行
kubeadm token create --print-join-command
```

2.  所有的 node节点执行

```shell
# 替换 x.x.x.x 为 master 节点实际 IP（请使用内网 IP）
export MASTER_IP=x.x.x.x
# 替换 apiserver.demo 为初始化 master 节点时所使用的 APISERVER_NAME
export APISERVER_NAME=apiserver.demo
echo "${MASTER_IP}    ${APISERVER_NAME}" >> /etc/hosts

# 替换为 master 节点上 kubeadm token create 命令的输出
kubeadm join apiserver.demo:6443 --token mpfjma.4vjjg8flqihor4vt     --discovery-token-ca-cert-hash sha256:6f7a8e40a810323672de5eee6f4d19aa2dbdb38411845a1bf5dd63485c43d303
 
```

#### 移除 node 节点

1.  在准备移除的 node节点上执行 

```shell
# 只在 worker 节点执行
kubeadm reset
```

2.  在 master 节点 demo-master-a-1 上执行 

```shell
# 只在 master 节点执行
kubectl delete node demo-worker-x-x
```

#### 配置 Ingress Controller

1. 安装

```shell
# 只在 master 节点执行
kubectl apply -f https://kuboard.cn/install-script/v1.16.2/nginx-ingress.yaml
```

2. 卸载

```shell
# 只在 master 节点执行
kubectl delete -f https://kuboard.cn/install-script/v1.16.2/nginx-ingress.yaml
```

