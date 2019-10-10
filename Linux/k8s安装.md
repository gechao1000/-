#### 安装 Kubernetes 必备工具

> https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/install-kubeadm/

```
vim /etc/yum.repos.d/kubernetes.repo
[kubernetes]
name=Kubernetes
baseurl=https://mirrors.aliyun.com/kubernetes/yum/repos/kubernetes-el7-x86_64
enabled=1
gpgcheck=0
repo_gpgcheck=0
gpgkey=https://mirrors.aliyun.com/kubernetes/yum/doc/yum-key.gpg https://mirrors.aliyun.com/kubernetes/yum/doc/rpm-package-key.gpg

yum install -y kubelet kubeadm kubectl
```

#### Kubernetes安装集群

> kubeadm 是 kubernetes 的集群安装工具，能够快速安装 kubernetes 集群


1. 导出配置文件
```shell
kubeadm config print init-defaults --kubeconfig ClusterConfiguration > kubeadm.yml
```

2. 修改配置文件

```yaml
localAPIEndpoint:
  # 修改为主节点 IP
  advertiseAddress: 192.168.80.110
# 国内不能访问 Google，修改为阿里云
imageRepository: registry.aliyuncs.com/google_containers
networking:
  # 配置 POD 所在网段为我们虚拟机不重叠的网段（这里用的是 Flannel 默认网段）
  podSubnet: "10.244.0.0/16"
```

3. 拉取所需镜像

```shell
# 查看
kubeadm config images list --config kubeadm.yml
# 拉取
kubeadm config images pull --config kubeadm.yml
```

4. 初始化主节点

```shell
kubeadm init --config=kubeadm.yml --upload-certs | tee kubeadm-init.log

# --upload-certs 参数可以在后续执行加入节点时自动分发证书文件
# 追加的 tee kubeadm-init.log 用以输出日志

# 报错
echo 1 > /proc/sys/net/bridge/bridge-nf-call-iptables
```

5. 配置kubectl

```shell
mkdir -p $HOME/.kube
cp -i /etc/kubernetes/admin.conf $HOME/.kube/config

# 验证是否成功
kubectl get node
```

6. Node 节点加入到集群

```shell
# Node 服务器上安装 kubeadm，kubectl，kubelet 三个工具
kubeadm join ... (主节点kubeadm-init.log)

# kubeadm reset 重置配置再使用 kubeadm join 命令重新加入
# master 节点删除 node ，可以使用 kubectl delete nodes <NAME> 删除

# 回到 Master 主节点查看是否安装成功
kubectl get node
```

#### Kubernetes 配置网络

