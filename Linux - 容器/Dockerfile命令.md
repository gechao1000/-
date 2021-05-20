Dockerfile命令详解（超全版本）

https://www.cnblogs.com/dazhoushuoceshi/p/7066041.html



Docker Image 相关命令

```
docker save -o aa.tar aa
docker load -i aa.tar

docker build -t aa .
```



### JDK （成功）

> docker run --rm -it jdk bash
>
> docker run -d --privileged --name java jdk

```dockerfile
FROM centos:7

MAINTAINER gg
WORKDIR /usr/local/jdk

RUN yum install -y which

ADD openjdk-11.0.2_linux-x64_bin.tar.gz /usr/local/jdk

ENV JAVA_HOME=/usr/local/jdk/jdk-11.0.2
ENV PATH=$JAVA_HOME/bin:$PATH

CMD ["/usr/sbin/init"]
```



### Nginx（成功）

```dockerfile
FROM centos

RUN ping -c 1 www.baidu.com
RUN yum -y install gcc make pcre-devel zlib-devel tar zlib
ADD nginx-1.20.0.tar.gz /usr/local/src/
RUN cd /usr/local/src/nginx-1.20.0 \
    && mkdir /usr/local/nginx \
    && ./configure --prefix=/usr/local/nginx && make && make install \
    && ln -s /usr/local/nginx/sbin/nginx /usr/local/sbin/ 

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
```



### KBase（依赖systemd）

```dockerfile
FROM centos:7

MAINTAINER gg

RUN yum install -y which firewalld net-tools

ADD kbase-12.0-33214.x86_64.rpm /root

RUN rpm -ivh /root/kbase-12.0-33214.x86_64.rpm \
  && rm -rf /root/kbase-12.0-33214.x86_64.rpm


EXPOSE 4567 4568
CMD ["/usr/sbin/init"]

---
docker run --privileged --name kbase --network=host -d  kbase
# podman默认systemd
podman run --name kbase -p 4567:4567 -p 4568:4568 -d kbase
```

build报错

```
Error: DBUS_ERROR: Failed to connect to socket /run/dbus/system_bus_socket: No such file or directory
Failed to get D-Bus connection: Operation not permitted

```

docker run -p 4567:4567 报错

```
docker: Error response from daemon: driver failed programming external connectivity on endpoint kbase (598f16cfdcf60a63588e956eef31f7e9751c01c98139b445d0d16ae8d11819bf):  (iptables failed: iptables --wait -t nat -A DOCKER -p tcp -d 0/0 --dport 4568 -j DNAT --to-destination 172.17.0.2:4568 ! -i docker0: iptables: No chain/target/match by that name.
 (exit status 1)).
```

restart 之后不能连接

```
解决方法：手动关闭firewalld

Podman Image可以不安装 firewalld, docker没有实验
```

