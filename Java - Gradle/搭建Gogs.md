https://github.com/gogs/gogs/tree/main/docker

```
# Pull image from Docker Hub.
$ docker pull gogs/gogs

# Create local directory for volume.
$ mkdir -p /var/gogs

# Use `docker run` for the first time.
$ docker run --name=gogs -p 10022:22 -p 10880:3000 -v /var/gogs:/data gogs/gogs

# Use `docker start` if you have stopped it.
$ docker start gogs
```

https://gogs.io/docs/installation/install_from_binary.html

```
解压
cd gogs

第一次启动，浏览器3000端口修改配置
./gogs web 

配置systemd
```

