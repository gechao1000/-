Manjaro glibc版本太高，deb不能在ubuntu 运行

| 系统             | 代号    | glibc版本 | gcc版本 | ISO                                     |
| ---------------- | ------- | --------- | ------- | --------------------------------------- |
| Ubuntu 16.04 LTS | xenial  | 2.23      | 5.4.0   | ubuntu-16.04.7-desktop-amd64.iso        |
| Ubuntu 18.04 LTS | bionic  | 2.27      | 7.5.0   | ubuntu-18.04.5-desktop-amd64.iso        |
| Ubuntu 20.04 LTS | focal   | 2.31      | 9.3.0   | ubuntu-20.04.3-desktop-amd64.iso        |
| Ubuntu 21.04     | hirsute | 2.33      | 10.3.0  | ubuntu-21.04-desktop-amd64.iso          |
| Manjaro XFCE     |         | 2.33      | 11.1.0  | manjaro-xfce-21.1.1-210827-linux513.iso |
| CentOS 7         |         | 2.17      | 4.8.5   | CentOS-7-x86_64-Minimal-2003.iso        |
| CentOS8          |         | 2.28      | 8.4.1   | CentOS-8.4.2105-x86_64-dvd1.iso         |



下载 maven 镜像

```
docker pull maven
docker run -it --rm maven bash

查看环境变量
export

openjdk 17.0.1
maven 3.8.4
Fedora 8.5
libc 2.28
```



自己构建 maven 镜像，基于 ubuntu16

https://github.com/kaiwinter/docker-java8-maven/blob/master/Dockerfile



---



https://gist.github.com/msauza/6a906e879549e218c54868d81161afcb

https://hub.docker.com/_/maven

https://docs.docker.com/storage/volumes/

