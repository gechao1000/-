## 简介

> https://docs.minio.io/
>
> http://www.minio.org.cn/	(中文社区)

Minio是Apache License v2.0下发布的对象存储服务器。它与Amazon S3云存储服务兼容

它最适合存储非结构化数据，如照片，视频，日志文件，备份和容器/ VM映像。对象的大小可以从几KB到最大5TB

Minio服务器足够轻，可以与应用程序堆栈捆绑在一起，类似于NodeJS，Redis和MySQL

#### 安装

```
# docker
docker pull minio/minio
docker run -p 9000:9000 minio/minio server /data
# 自定义 用户名，密码，数据目录
docker run -p 9000:9000 -e MINIO_ACCESS_KEY=admin -e MINIO_SECRET_KEY=admin -v /data:/data minio/minio server /data

# bitnami/minio
minio / miniosecret

# linux
wget https://dl.min.io/server/minio/release/linux-amd64/minio
chmod +x minio
./minio server /data

# windows
minio.exe server D:\Photos
```



## 分布式

1. 介绍

```
单机Minio服务存在单点故障

分布式Minio至少需要4个硬盘

有N块硬盘的分布式Minio,只要有N/2硬盘在线，你的数据就是安全的。不过你需要至少有N/2+1个硬盘来创建新的对象
```

2. 注意

```
所有的节点需要有同样的access秘钥和secret秘钥，这样这些节点才能建立联接。为了实现这个，你需要在执行minio server命令之前，先将access秘钥和secret秘钥export成环境变量

分布式Minio使用的磁盘里必须是干净的，里面没有数据

在Windows下运行分布式Minio处于实验阶段，请悠着点使用
```

3. docker-compose单机多实例

```
https://raw.githubusercontent.com/minio/minio/master/docs/orchestration/docker-compose/docker-compose.yaml
```

4. 单主机，单磁盘

```
minio --config-dir ~/tenant1 server --address :9001 /data/tenant1
minio --config-dir ~/tenant2 server --address :9002 /data/tenant2
minio --config-dir ~/tenant3 server --address :9003 /data/tenant3
```



## CentOS 搭建集群

1. 机器资源

```
192.168.1.101
/data1
192.168.1.102
/data2
192.168.1.103
/data3
192.168.1.104
/data4
```

2. 创建相关目录 （所有节点）

```
# 数据存储目录，启动脚本目录
mkdir -p /var/minio/bin
wget https://dl.minio.io/server/minio/release/linux-amd64/mini
chmod +x minio

# 配置文件目录
mkdir -p /etc/minio
```

3. 启动脚本  `/var/minio/bin/run.sh`

```
#!/bin/bash
export MINIO_ACCESS_KEY=minio
export MINIO_SECRET_KEY=test123456

/var/minio/bin/minio server --config-dir /etc/minio \
http://192.168.1.101/data1 http://192.168.1.102/data2 \
http://192.168.1.103/data3 http://192.168.1.104/data4  
```

4. service 脚本 （所有节点）

> $ vim /etc/systemd/system/minio.service
>
>  chmod +x minio.service

```
[Unit]
Description=Minio service
Documentation=https://docs.minio.io/

[Service]
WorkingDirectory=/var/minio/bin/
ExecStart=/var/minio/bin/run.sh

Restart=on-failure
RestartSec=5

[Install]
WantedBy=multi-user.target
```

5. 启动测试

```
systemctl daemon-reload
systemctl start minio
systemctl enable minio
```

6. nginx负载均衡

```
配置nginx 支持lb（支持集群节点；支持多集群混用）
upstream minio-server {
    server 192.168.1.101:9000 weight=25 max_fails=2 fail_timeout=30s;
    server 192.168.1.102:9000 weight=25 max_fails=2 fail_timeout=30s;
    server 192.168.1.103:9000 weight=25 max_fails=2 fail_timeout=30s;
    server 192.168.1.104:9000 weight=25 max_fails=2 fail_timeout=30s;
}
server {
    listen 80;
    server_name localhost;
    charset utf-8;
    default_type text/html;
    location /{
        proxy_set_header Host $http_host;
        proxy_set_header X-Forwarded-For $remote_addr;
        client_body_buffer_size 10M;
        client_max_body_size 10G;
        proxy_buffers 1024 4k;
        proxy_read_timeout 300;
        proxy_next_upstream error timeout http_404;
        proxy_pass http://minio-server;
    }
}
```

