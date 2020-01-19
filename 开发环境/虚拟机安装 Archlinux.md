###### 虚拟机安装 Archlinux 

```
# 镜像
https://www.archlinux.org/download/ 
# 官方文档
https://wiki.archlinux.org/index.php/Installation_guide_(简体中文)

# 教程
https://www.viseator.com/2017/05/17/arch_install/
https://www.jianshu.com/p/7c78dc4c53e5
```

###### 前期准备

```
# 检查网络连接
ping -c 4 www.baidu.com

# 和NTP服务器同步时间
timedatectl set-ntp true

# 修改软件源
vim /etc/pacman.d/mirrorlist
Server = http://mirrors.aliyun.com/archlinux/$repo/os/$arch
```

###### 分区

```
# 检查引导方式
# 前的引导方式主要分为EFI引导+GPT分区表与BIOS(LEGACY)引导+MBR分区表两种
ls /sys/firmware/efi/efivars  (如果有东西输出则是UEFI)

# 查看存储设备
fdisk -l 或 lsblk

# 分区 （可选cfdisk）
fdisk /dev/sda
- o 创建一个全新的MBR分区表
- g 创建一个全新的gpt分区表

# 格式化分区
mkfs.ext4 /dev/sda1

# 挂载swap (如果有swap)
mkswap /dev/sda3
swapon /dev/sda3

# 挂载根分区
mount /dev/sda1 /mnt
```

###### 安装

```
# packge group (https://www.archlinux.org/groups/)
pacstrap /mnt base linux linux-firmware
- 可选: base-devel, vim-plugins

# 生成自动挂载分区的fstab文件
genfstab -U /mnt >> /mnt/etc/fstab

# 切换系统
arch-chroot /mnt
```

###### 系统设置

```
# 时区
ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

# 硬件时间
hwclock --systohc

# 设置 Locale
> vim /etc/locale.gen （取消注释）
en_US.UTF-8 UTF-8
zh_CN.UTF-8 UTF-8
zh_TW.UTF-8 UTF-8
> locale-gen
> vim /etc/locale.conf
LANG=en_US.UTF-8

# 网络
> vim /etc/hostname
myhostname
> vim /etc/hosts
127.0.0.1	localhost
::1		localhost
127.0.1.1	myhostname.localdomain	myhostname

# dns (aliyun)
> vim /etc/resolv.conf
nameserver 223.5.5.5
nameserver 223.6.6.6

# root密码
passwd

# 添加普通用户 (可选)
useradd -m gexc
passwd gexc

# 安装GRUB引导
pacman -S grub 
- 可选： intel-ucode (intel CPU)，os-prober(当前电脑存在其他系统)
grub-install --target=i386-pc /dev/sda
grub-mkconfig -o /boot/grub/grub.cfg
```

###### 图形界面 （可选）

```
pacman -S xorg

# gnome
pacman -S gnome gnome-extra

# xfce
pacman -S xfce4 xfce4-goodies

# kde
pacman -S plasma kde-applications

# 桌面管理器
systemctl enable sddm (适合 kde)
systemctl enable gdm (适合 gnome)
```

###### 重启

```
exit

umount /mnt

reboot
```

###### 设置静态 ip

```
# systemd-networkd （内置DHCP）
> systemctl enable systemd-networkd

> vim /etc/systemd/network/ens33.network
[Match]
Name=ens33  # 匹配名为 ens33 的网卡

[Network]
#DHCP=none    # 关闭 DHCP 客户端，
Address=10.1.10.9/24
Gateway=10.1.10.1
DNS=10.1.10.1
#DNS=8.8.8.8
```

###### 配置 sshd

```
> pacman -S openssh

# 允许 root 远程登录
> vim /etc/ssh/sshd_config
PermitRootLogin yes

# 保存公钥
> vim ~/.ssh/authorized_keys
```

