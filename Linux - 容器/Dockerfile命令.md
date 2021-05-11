Dockerfile命令详解（超全版本）

https://www.cnblogs.com/dazhoushuoceshi/p/7066041.html



Docker Image 相关命令

```
docker save -o aa.tar aa
docker load -i aa.tar

docker build -t aa .
```



JDK （成功）

> docker run --rm -it jdk bash

```dockerfile
FROM centos

MAINTAINER gg
WORKDIR /usr/local/jdk

ADD openjdk-11.0.2_linux-x64_bin.tar.gz /usr/local/jdk

ENV JAVA_HOME=/usr/local/jdk/jdk-11.0.2
ENV PATH=$JAVA_HOME/bin:$PATH
```



Nginx（成功）

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



KBase（失败）

```dockerfile
FROM centos

ADD kbase-12.0-33214.amd64.rpm /root
RUN rpm -ivh /root/kbase-12.0-33214.amd64.rpm

EXPOSE 4567 4568
CMD ["sh", "/kbase/server/kbase.sh", "-s"]
CMD ["tail", "-f", "/dev/null"]
```

