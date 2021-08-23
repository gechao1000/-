https://github.com/probonopd/linuxdeployqt

https://blog.csdn.net/zyhse/article/details/106381937/



```
pacman linuxdeployqt
```



ubuntu 搭建 Qt 开发环境

https://vitux.com/compiling-your-first-qt-program-in-ubuntu/

```
sudo apt update
sudo apt install build-essential
sudo apt install qtcreator
sudo apt install qt5-default
sudo apt install qt5-doc qtbase5-examples qtbase5-doc-html

qmake SampleProject.pro
make
```



deepin 搭建 Qt 开发环境

https://blog.csdn.net/qq_33154343/article/details/103733327

```
应用商店安装 qtcreator cmake

sudo apt install libdtkwidget-dev  qt5-default qtcreator
sudo apt install qtcreator-template-dtk
```



对开发环境有要求，不能使用太新的系统

https://github.com/probonopd/linuxdeployqt/issues/340

```
---GitHub下载的linuxdeployqt.AppImage
Ubuntu Bionic 18.04
glibc 2.27


---Manjaro安装的linuxdeployqt
Ubuntu xenial 16.04
glibc 2.23
```



查看glibc版本

```
ldd --version
```

x86 系统

| 系统         | glibc版本 | gcc版本 | ISO                                       |
| ------------ | --------- | ------- | ----------------------------------------- |
| Deepin       | 2.28      | 8.3.0   | deepin-desktop-community-20.2.3-amd64.iso |
| Manjaro      | 2.33      | 11.1.0  | manjaro-xfce-21.1.0-210817-linux513.iso   |
| Ubuntu 18.04 | 2.27      | 7.5.0   | ubuntu-18.04.5-desktop-amd64.iso          |
| Ubuntu 21.04 | 2.33      |         | ubuntu-21.04-desktop-amd64.iso            |

国产化系统

| CPU         | 系统                        | glibc版本 | 架构    |
| ----------- | --------------------------- | --------- | ------- |
| FT-2000     | Kylin V10 SP1               | 2.31      | aarch64 |
| Kunpeng 920 | UnionTech OS Desktop 20 Pro | 2.28      | aarch64 |
| Loongson-3A | UnionTech OS Desktop 20 Pro | 2.28      | mips64  |



Kylin-Desktop-V10-Professional-Release-Build1-210203-arm64

| 架构 | glibc版本 | gcc版本 |
| ---- | --------- | ------- |
| x86  | 2.31      | 9.3.0   |
|      |           |         |
|      |           |         |

