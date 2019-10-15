#### YUM 安装

> https://docs.docker.com/install/linux/docker-ce/centos/

```shell
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
  
sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
    
sudo yum install -y docker-ce docker-ce-cli containerd.io
```

#### APT 安装

```
sudo apt-get remove docker docker-engine docker.io containerd runc

sudo apt-get update

sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg-agent \
    software-properties-common

curl -fsSL http://mirrors.aliyun.com/docker-ce/linux/ubuntu/gpg | sudo apt-key add -

sudo add-apt-repository \
   "deb [arch=amd64] https://mirrors.aliyun.com/docker-ce/linux/ubuntu \
   $(lsb_release -cs) \
   stable"

apt-get update && apt-get install docker-ce docker-ce-cli containerd.io
```

#### docker-compose

> https://docs.docker.com/compose/install/

```shell
sudo curl -L "https://github.com/docker/compose/releases/download/1.24.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

sudo chmod +x /usr/local/bin/docker-compose
```

#### docker镜像站

> https://www.daocloud.io/mirror

```shell
curl -sSL https://get.daocloud.io/daotools/set_mirror.sh | sh -s http://f1361db2.m.daocloud.io
```

> aliyun

```
{
  "exec-opts": ["native.cgroupdriver=systemd"],
  "log-driver": "json-file",
  "log-opts": {
    "max-size": "100m"
  },
  "registry-mirrors": [
    "https://3d2yd7x1.mirror.aliyuncs.com",
    "https://dockerhub.azk8s.cn",
    "https://registry.docker-cn.com"
  ],
  "storage-driver": "overlay2"
}
```


