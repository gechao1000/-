## CentOS8 安装 Podman

> https://podman.io/
>
> https://github.com/containers/libpod

安装

```
yum install -y podman

podman version
```

镜像

```
# vim /etc/containers/registries.conf

unqualified-search-registries = ["docker.io"]

[[registry]]
prefix = "docker.io"
location = "3d2yd7x1.mirror.aliyuncs.com"
```

运行实例

```
# nginx
podman run -dt -p 80:80 --name nginx -v /data:/data -e NGINX_VERSION=1.16 nginx:1.16.0

# mysql
podman run --name mysql -p 3306:3306 -e TZ=Asia/Shanghai -e MYSQL_ROOT_PASSWORD=123456 -d percona:5.7 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci --explicit_defaults_for_timestamp=true --lower_case_table_names=1 --default-authentication-plugin=mysql_native_password
```

基本命令 (类似 docker)

> https://podman.readthedocs.io/en/latest/Commands.html

```
podman pull redis

podman images

podman exec -it redis /bin/bash

podman stop redis

podman rm redis
podman rmi redis

# 列出当前所有的容器
podman ps -a

# 查看容器运行的日志
podman logs nginx

# 查看运行中容器资源使用情况
podman  top nginx
podman  stats nginx
```

开机启动

```
$ vim /etc/systemd/system/nginx_podman.service

[Unit]
Description=Podman Nginx Service
After=network.target
After=network-online.target

[Service]
Type=simple
ExecStart=/usr/bin/podman start -a nginx
ExecStop=/usr/bin/podman stop -t 10 nginx
Restart=always

[Install]
WantedBy=multi-user.target
```

启动服务

```
systemctl daemon-reload
systemctl enable nginx_podman.service
systemctl start nginx_podman.service
```

设置别名 (不推荐)

```
## docker使用习惯的，可配置别名
> echo "alias docker=podman" >> .bashrc
> source .bashrc
> docker ps -a
```

## K8S / kocompose

> https://kompose.io/

安装 

```
curl -L https://github.com/kubernetes/kompose/releases/download/v1.21.0/kompose-linux-amd64 -o kompose
```

