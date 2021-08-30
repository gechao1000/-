查看glibc版本

```
ldd --version
```

x86 系统

> https://releases.ubuntu.com/

| 系统             | 代号    | glibc版本 | gcc版本 | ISO                                     |
| ---------------- | ------- | --------- | ------- | --------------------------------------- |
| Ubuntu 16.04 LTS | xenial  | 2.23      | 5.4.0   | ubuntu-16.04.7-desktop-amd64.iso        |
| Ubuntu 18.04 LTS | bionic  | 2.27      | 7.5.0   | ubuntu-18.04.5-desktop-amd64.iso        |
| Ubuntu 20.04 LTS | focal   | 2.31      | 9.3.0   | ubuntu-20.04.3-desktop-amd64.iso        |
| Ubuntu 21.04     | hirsute | 2.33      | 10.3.0  | ubuntu-21.04-desktop-amd64.iso          |
| Manjaro XFCE     |         | 2.33      | 11.1.0  | manjaro-xfce-21.1.1-210827-linux513.iso |

UnionTech OS Desktop 20 Pro

| CPU         | glibc版本 | gcc版本 | 架构  |
| ----------- | --------- | ------- | ----- |
| Kunpeng 920 | 2.28      | 8.3.0   | arm64 |
| Loongson-3A | 2.28      | 8.3.0   | mips  |

Kylin-Desktop-V10-Professional-Release-Build1-210203（V10SP1）

| 架构  | glibc版本 | gcc版本 |
| ----- | --------- | ------- |
| x86   | 2.31      | 9.3.0   |
| arm64 | 2.31      | 9.3.0   |
| mips  | 2.31      | 9.3.0   |

Kylin-Desktop-V10-Release-Build1-2101（V10）

| 架构  | glibc版本 | gcc版本 |
| ----- | --------- | ------- |
| x86   | 2.23      | 5.4.0   |
| arm64 | 2.23      | 5.4.0   |
| mips  | 2.23      | 5.4.0   |



树莓派 4B

```
$ cat /etc/debian_version
10.8

$ cat /os-release
Raspbian GNU/Linux 10 (buster)

$ lscpu
Architecture: armv7l
Model name: Cortex-A72

$ uname -a
Linux raspberrypi 5.10.11-v7l+ #1399 SMP Thu Jan 28 12:09:48 GMT 2021 armv7l GNU/Linux

$ gcc --version
gcc (Raspbian 8.3.0-6+rpi1) 8.3.0

$ ldd --version
ldd (Debian GLIBC 2.28-10+rpi1) 2.28

$ dpkg --print-architecture
armhf
```



Docker

> https://docs.docker.com/engine/install/ubuntu/

```
Docker Engine is supported on x86_64 (or amd64), armhf, arm64, and s390x architectures.

Ubuntu 16.04 LTS “Xenial Xerus” end-of-life


树莓派4B：armhf
飞腾：arm64
```



求教ARMv7/ARMv8/ARM64三个版本的区别

> https://tieba.baidu.com/p/6224138641

```
ARMv7是32位的ARM构架，ARMv8则是64位的ARM构架，ARMv8也叫ARM64。


ARMv7 含16位和32位两个指令集，ARMv8 含32位和64位两个指令集，
所以，ARM64 其实只是 ARMv8的一半。A53 开始之后，都是ARM64。
```



kylin os

http://archive.kylinos.cn/kylin/KYLIN-ALL/dists/10.0/main/

```
binary-amd64/                                      24-Aug-2021 07:45                   -
binary-arm64/                                      24-Aug-2021 07:45                   -
binary-armhf/                                      24-Aug-2021 07:45                   -
binary-i386/                                       24-Aug-2021 07:45                   -
binary-mips64el/                                   24-Aug-2021 07:45                   -
binary-sw64/
```



```
可以用ubuntu做标准
kylin v10 相当于 ubuntu16.04
kylin v10 sp1 相当于 Ubntun20.04
UOS略高于ubuntu18.04
```

