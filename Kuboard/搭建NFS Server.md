 为了使 Pod 在任何节点上都能够使用同一份持久化存储数据，我们需要使用网络存储的解决方案为 Pod 提供 [数据卷](https://kuboard.cn/learning/k8s-intermediate/persistent/volume.html) 

## 配置NFS服务器

1.  安装 nfs 服务器所需的软件包 

```shell
yum install -y nfs-utils
```

2.  执行命令 `vim /etc/exports`，创建 exports 文件 

```
/root/nfs_root/ *(insecure,rw,sync,no_root_squash)
```

3.  启动 nfs 服务 

```shell
# 创建共享目录，如果要使用自己的目录，请替换本文档中所有的 /root/nfs_root/
mkdir /root/nfs_root

systemctl enable rpcbind && systemctl start rpcbind
systemctl enable nfs-server && systemctl start nfs-server

exportfs -r
```

4.  检查配置是否生效 

```shell
exportfs
```

## 在客户端测试nfs

1.  安装 nfs 客户端所需的软件包 
2.  检查 nfs 服务器端是否有设置共享目录 

```shell
# showmount -e $(nfs服务器的IP)
showmount -e 192.168.80.130
```

3.  挂载 nfs 服务器上的共享目录到本机路径 `/root/nfsmount` 

```shell
mkdir /root/nfsmount
# mount -t nfs $(nfs服务器的IP):/root/nfs_root /root/nfsmount
mount -t nfs 192.168.80.130:/root/nfs_root /root/nfsmount
# 写入一个测试文件
echo "hello nfs server" > /root/nfsmount/test.txt
```

4.  检查 nfs 服务器上 测试文件
5.  取消挂载

```shell
umount -fr /root/nfsmount
```

## 在Kuboard中创建 NFS 存储类

1.  打开 Kuboard 的集群概览页面，点击 **创建存储类** 按钮 