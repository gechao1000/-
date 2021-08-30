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


