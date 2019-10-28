#### 安装 Kubernetes 必备工具

> https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/install-kubeadm/
>
> https://opsx.alibaba.com/mirror?lang=zh-CN

```
vim /etc/yum.repos.d/kubernetes.repo
[kubernetes]
name=Kubernetes
baseurl=https://mirrors.aliyun.com/kubernetes/yum/repos/kubernetes-el7-x86_64/
enabled=1
gpgcheck=1
repo_gpgcheck=1
gpgkey=https://mirrors.aliyun.com/kubernetes/yum/doc/yum-key.gpg https://mirrors.aliyun.com/kubernetes/yum/doc/rpm-package-key.gpg

yum install -y kubelet kubeadm kubectl

# kubelet设置开机启动
systemctl enable kubelet && systemctl start kubelet
```

#### 安装特定版本的 Kubernetes

> TiDB 与 k8s 1.16 可能存在冲突

```
apt-get update && apt-get install -y kubelet=1.15.4-00 kubeadm=1.15.4-00 kubectl=1.15.4-00

yum install -y kubeadm-1.15.4
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
# kubelet版本号
kubernetesVersion: v1.16.0
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

# 报错 bridge-nf-call-iptables
echo 1 > /proc/sys/net/bridge/bridge-nf-call-iptables
## 或者
vi /etc/sysctl.conf
## 添加以下内容
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
## 最后再执行
sysctl -p
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

7. 集群扩容（增加新的节点 Node）

```shell
# 每个 Token 的有效期为 24 小时
kubeadm token list
kubeadm token create

# 查看 Kubernetes 认证的 SHA256 加密字符串
openssl x509 -pubkey -in /etc/kubernetes/pki/ca.crt | openssl rsa -pubin -outform der 2>/dev/null | openssl dgst -sha256 -hex | sed 's/^.* //'
```

#### Kubernetes 配置网络

> CNI(Container Network Interface) 是一个标准的、通用的接口，pod之间通信
>
> https://docs.projectcalico.org/v3.9/getting-started/kubernetes/

1. 下载Calico配置文件并修改

```shell
wget https://docs.projectcalico.org/v3.9/manifests/calico.yaml

- name: CALICO_IPV4POOL_CIDR
	value: "10.244.0.0/16"
```

2. 安装网络插件 Calico

```shell
kubectl apply -f calico.yaml

# 验证安装是否成功
watch kubectl get pods --all-namespaces
kubectl get node
```

