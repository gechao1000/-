编译 linuxdeployqt

https://github.com/probonopd/linuxdeployqt

```
cd linuxdeployqt
qmake
make

sudo make install
export PATH=$PATH:/usr/lib/qt5/bin
linuxdeployqt --version
```

缺少依赖

https://www.cnblogs.com/xl2432/p/11124415.html

```
报错 ERROR: Error reading rpath with patchelf "libQt5Widgets.so" : ""
sudo apt install patchelf
```

缺少 appimagetool

https://github.com/AppImage/AppImageKit/releases



---

编译 appimagetool

https://github.com/AppImage/AppImageKit/releases

```
cd AppImageKit/
bash ci/build.sh



```

报错 ARCH参数

```
env ARCH=x86_64 bash ci/build.sh

支持
x86_64
i386  i686
armhfs  aarch64
```



依赖docker

```
下载镜像 appimage:appimagebuild
```



查看所有依赖

```
ldd cajviewer

有的提示 not found
在linuxdeployqt报错

--FT台式机
/usr/lib/aarch64-linux-gnu/qt5/
```

