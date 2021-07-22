#### 安装 Ubuntu

```
1.启用 ‘适用于Linux的Windows子系统’ 和 ‘虚拟机平台’
2.更新wsl2内核，https://docs.microsoft.com/zh-cn/windows/wsl/wsl2-kernel
3.应用商店安装ubuntu	

wsl -l -v
wsl --set-default-version 2  //安装ubuntu之前执行
```



#### Ubuntu 安装 sshd、编译环境

https://www.jetbrains.com/help/clion/2021.1/how-to-use-wsl-development-environment-in-product.html#wsl-tooclhain

```
sudo apt-get update
sudo apt-get install cmake gcc clang gdb build-essential

# 端口2222
wget https://raw.githubusercontent.com/JetBrains/clion-wsl/master/ubuntu_setup_env.sh && bash ubuntu_setup_env.sh

# CLion 远程开发
```

