#### 查看Docker版本和系统信息

`docker version`

`docker info`

`docker help`

#### 搜索下载Docker镜像

`docker search nginx`

`docker pull nginx`

`docker images`

#### 运行镜像的Docker容器

`docker run -d -p 8080:80 --name server-nginx nginx`  创建容器

- `-d` 	detach(分离模式)，在后台运行
- `-p`  port(映射端口)，localhost:docker容器
- `--name`  为容器命名，ID和NAME是容器标识

`docker ps`   正在运行的容器

- `-a`  所有容器，包括已暂停和已停止

#### 启动，停止，重启和杀死容器

`docker start|stop|restart|kill server-nginx`

#### 向容器发出命令，检查容器，复制本地文件

`docker exec -it server-nginx bash`

`docker inspect server-nginx`

`docker cp aaa.txt server-nginx:/path`

#### 当前容器保存为镜像

`docker commit 容器ID或NAME 镜像名称`

#### 删除容器和镜像

> https://www.cnblogs.com/jackadam/p/8567846.html

```shell
docker rm 容器
docker rmi 镜像

# 杀死所有正在运行的容器
docker kill $(docker ps -a -q)

# 删除所有已经停止的容器
docker rm $(docker ps -a -q)

# 删除所有镜像
docker rmi $(docker images -q)
```

