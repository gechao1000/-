#### 安装 

创建虚拟机：高级 => 15.X => 1CPU2核，内存4G，网络NAT，磁盘20G

阿里云镜像地址：`http://mirrors.aliyun.com/ubuntu`

LVM磁盘扩容：ubunbu-lv Size改为最大值

#### 配置 IP

```
vi /etc/netplan/50-cloud-init.yaml

network:
    ethernets:
        ens33:
          addresses: [192.168.80.128/24]
          gateway4: 192.168.80.2
          nameservers:
            addresses: [192.168.80.2]
    version: 2
```

#### 使用 Root 用户

1. 设置 root 账户密码 `sudo passwd root`
2. 切换到 root `su`
3. 允许 root 远程登录 

```
vim /etc/ssh/sshd_config
PermitRootLogin yes

# 重启服务
service ssh restart
```

#### 环境变量

```
系统环境变量：/etc/environment
用户环境变量：/etc/profile

# 配置 jdk
export JAVA_HOME=/usr/local/java/jdk1.8.0_152
export JRE_HOME=/usr/local/java/jdk1.8.0_152/jre
export CLASSPATH=$CLASSPATH:$JAVA_HOME/lib:$JAVA_HOME/jre/lib
export PATH=$JAVA_HOME/bin:$JAVA_HOME/jre/bin:$PATH:$HOME/bin

# 用户环境变量生效
source /etc/profile
```

LVM 磁盘扩容

1. 基本概念
   * **物理卷 Physical Volume (PV)：** 可以在上面建立卷组的媒介，可以是硬盘分区，也可以是硬盘本身或者回环文件（loopback file）。物理卷包括一个特殊的 header，其余部分被切割为一块块物理区域（physical extents）
   * **卷组 Volume group (VG)：** 将一组物理卷收集为一个管理单元
   * **逻辑卷 Logical volume (LV)：** 虚拟分区，由物理区域（physical extents）组成
   * **物理区域 Physical extent (PE)：** 硬盘可供指派给逻辑卷的最小单位（通常为 4MB）

2. 虚拟机添加硬盘
   * 磁盘空间使用情况：`df -h`
   * 显示当前的 logical volume：`lvdisplay`
   * 显示当前的 volume group：`vgdisplay`
   * 显示当前的 physical volume：`pvdisplay`
   * 查看所有储存设备：`fdisk -l`

3. 格式化

```
# 创建 sdb 分区
fdisk /dev/sdb
## n：新建分区
## w：写入磁盘

# 格式化磁盘
mkfs -t ext4 /dev/sdb1
```

4. 创建 PV：`pvcreate /dev/sdb1`
5. 扩容 VG：`vgextend ubuntu-vg /dev/sdb1`
6. 扩容 LV

```
# 按固定大小追加
lvextend -L +10G /dev/ubuntu-vg/ubuntu-lv
# 按百分比追加
lvextend -l +100%FREE /dev/ubuntu-vg/ubuntu-lv

# 刷新分区
resize2fs /dev/ubuntu-vg/ubuntu-lv
```

