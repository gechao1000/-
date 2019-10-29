minikube

>  https://github.com/kubernetes/minikube/releases 

### 概念

* 负载均衡：轮询
* 集群：数据同步
* 高可用：崩溃恢复

奇数部署，3 Master

### 配置 IPVS

> IP 虚拟服务器

1. 安装系统工具

```bash
yum install -y ipset ipvsadm
```

2. 配置并加载 IPVS 模块

```
vi /etc/sysconfig/modules/ipvs.modules

#!/bin/bash
modprobe -- ip_vs
modprobe -- ip_vs_rr
modprobe -- ip_vs_wrr
modprobe -- ip_vs_sh
modprobe -- nf_conntrack_ipv4
```

3. 执行脚本，注意：如果重启则需要重新运行该脚本

```shell
chmod 755 /etc/sysconfig/modules/ipvs.modules && bash /etc/sysconfig/modules/ipvs.modules && lsmod | grep -e ip_vs -e nf_conntrack_ipv4
```

### 配置内核参数

```
# 配置参数
vi /etc/sysctl.d/k8s.conf

# 输入如下内容
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
net.ipv4.ip_nonlocal_bind = 1
net.ipv4.ip_forward = 1
vm.swappiness=0

# 应用参数
sysctl --system
```

### 安装 HAProxy + Keepalived

>  特别注意：Keepalived 会对 HAProxy 监听的 6444 端口进行检测，如果检测失败即认定本机 HAProxy 进程异常，会将 VIP 漂移到其他节点，所以无论本机 Keepalived 容器异常或 HAProxy 容器异常都会导致 VIP 漂移到其他节点 

1. HAProxy 启动脚本

```
mkdir -p /usr/local/kubernetes/lb
vi /usr/local/kubernetes/lb/start-haproxy.sh

# 输入内容如下
#!/bin/bash
# 修改为你自己的 Master 地址
MasterIP1=192.168.80.110
MasterIP2=192.168.80.111
MasterIP3=192.168.80.112
# 这是 kube-apiserver 默认端口，不用修改
MasterPort=6443

# 容器将 HAProxy 的 6444 端口暴露出去
docker run -d --restart=always --name HAProxy-K8S -p 6444:6444 \
        -e MasterIP1=$MasterIP1 \
        -e MasterIP2=$MasterIP2 \
        -e MasterIP3=$MasterIP3 \
        -e MasterPort=$MasterPort \
        wise2c/haproxy-k8s

# 设置权限
chmod +x start-haproxy.sh
```

2. Keepalived 启动脚本

```
mkdir -p /usr/local/kubernetes/lb
vi /usr/local/kubernetes/lb/start-keepalived.sh

# 输入内容如下
#!/bin/bash
# 修改为你自己的虚拟 IP 地址
VIRTUAL_IP=192.168.80.200
# 虚拟网卡设备名
INTERFACE=ens33
# 虚拟网卡的子网掩码
NETMASK_BIT=24
# HAProxy 暴露端口，内部指向 kube-apiserver 的 6443 端口
CHECK_PORT=6444
# 路由标识符
RID=10
# 虚拟路由标识符
VRID=160
# IPV4 多播地址，默认 224.0.0.18
MCAST_GROUP=224.0.0.18

docker run -itd --restart=always --name=Keepalived-K8S \
        --net=host --cap-add=NET_ADMIN \
        -e VIRTUAL_IP=$VIRTUAL_IP \
        -e INTERFACE=$INTERFACE \
        -e CHECK_PORT=$CHECK_PORT \
        -e RID=$RID \
        -e VRID=$VRID \
        -e NETMASK_BIT=$NETMASK_BIT \
        -e MCAST_GROUP=$MCAST_GROUP \
        wise2c/keepalived-k8s

# 设置权限
chmod +x start-keepalived.sh
```

3. 复制脚本到其它 Master

```shell
# 分别在 master-02 和 master-03 执行创建工作目录命令
mkdir -p /usr/local/kubernetes/lb

scp start-haproxy.sh start-keepalived.sh 192.168.80.111:/usr/local/kubernetes/lb
scp start-haproxy.sh start-keepalived.sh 192.168.80.112:/usr/local/kubernetes/lb

# 分别在 3 个 Master 中启动容器
sh /usr/local/kubernetes/lb/start-haproxy.sh && sh /usr/local/kubernetes/lb/start-keepalived.sh
```

4. 验证是否成功

```
# 查看容器
docker ps

# 查看网卡绑定的虚拟 IP
ip a | grep ens33
```

### 安装 K8s

1. 修改配置kubeadm.yml

```yaml
apiVersion: kubeadm.k8s.io/v1beta1
bootstrapTokens:
- groups:
  - system:bootstrappers:kubeadm:default-node-token
  token: abcdef.0123456789abcdef
  ttl: 24h0m0s
  usages:
  - signing
  - authentication
kind: InitConfiguration
localAPIEndpoint:
  # 修改为主节点 IP
  advertiseAddress: 192.168.141.110
  bindPort: 6443
nodeRegistration:
  criSocket: /var/run/dockershim.sock
  name: kubernetes-master
  taints:
  - effect: NoSchedule
    key: node-role.kubernetes.io/master
---
apiServer:
  timeoutForControlPlane: 4m0s
apiVersion: kubeadm.k8s.io/v1beta1
certificatesDir: /etc/kubernetes/pki
clusterName: kubernetes
# 配置 Keepalived 地址和 HAProxy 端口
controlPlaneEndpoint: "192.168.80.200:6444"
controllerManager: {}
dns:
  type: CoreDNS
etcd:
  local:
    dataDir: /var/lib/etcd
# 国内不能访问 Google，修改为阿里云
imageRepository: registry.aliyuncs.com/google_containers
kind: ClusterConfiguration
# 修改版本号
kubernetesVersion: v1.16.2
networking:
  dnsDomain: cluster.local
  # 配置成 Calico 的默认网段
  podSubnet: "10.244.0.0/16"
  serviceSubnet: 10.96.0.0/12
scheduler: {}
---
# 开启 IPVS 模式
apiVersion: kubeproxy.config.k8s.io/v1alpha1
kind: KubeProxyConfiguration
featureGates:
  SupportIPVSProxyMode: true
mode: ipvs
```

2. 加入 Master 节点

```shell
kubeadm join 192.168.80.200:6444 --token abcdef.0123456789abcdef \
    --discovery-token-ca-cert-hash sha256:8c67503d49003e90bdc8471bee821e3aaeeafaa7bd64136aa253645a326c5785 \
    --control-plane --certificate-key 686c0693568517c1405e7db29be6b02d18c240901877bf1ee1950d47c6dbf30d
```

3. 加入 Node 节点

```shell
kubeadm join 192.168.80.200:6444 --token abcdef.0123456789abcdef \
    --discovery-token-ca-cert-hash sha256:8c67503d49003e90bdc8471bee821e3aaeeafaa7bd64136aa253645a326c5785
```

4. 验证集群状态

```
kubectl get nodes -o wide
kubectl -n kube-system get pod -o wide
kubectl -n kube-system get svc

# 验证 IPVS，查看 kube-proxy 日志
kubectl -n kube-system logs -f <kube-proxy 容器名>

# 查看代理规则
ipvsadm -ln

# 查看生效的配置
kubectl -n kube-system get cm kubeadm-config -oyaml

# 查看 etcd 集群
kubectl -n kube-system exec etcd-kubernetes-master-1 -- etcdctl \
	--endpoints=https://192.168.80.110:2379 \
	--ca-file=/etc/kubernetes/pki/etcd/ca.crt \
	--cert-file=/etc/kubernetes/pki/etcd/server.crt \
	--key-file=/etc/kubernetes/pki/etcd/server.key cluster-health
```

