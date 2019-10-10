#### docker

> https://docs.docker.com/install/linux/docker-ce/centos/

```shell
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
  
sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
    
sudo yum install -y docker-ce docker-ce-cli containerd.io
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

> http://mirrors.ustc.edu.cn/help/dockerhub.html

```
/etc/docker/daemon.json
{
  "registry-mirrors": ["https://docker.mirrors.ustc.edu.cn/"]
}
```


