https://appimage-builder.readthedocs.io/en/latest/intro/overview.html



#### 依赖 appimage-tool

https://github.com/AppImage/AppImageKit/releases

```
sudo pacman -Sy python-pip python-setuptools binutils patchelf desktop-file-utils gdk-pixbuf2 wget fakeroot strace


pacman -S appimagetool-bin
```



#### 安装 appimage-builder

```
sudo pip3 install appimage-builder
```



#### 示例

```
# install build dependencies
sudo apt-get install git cmake zlib1g-dev qtdeclarative5-dev qml-module-qtquick2 qml-module-qtquick-window2 qml-module-qtquick-layouts qml-module-qtquick-layouts qml-module-qtquick-controls2

# get the application source code
git clone https://www.opencode.net/azubieta/qt-appimage-template.git

# step into the application source code dir
cd qt-appimage-template

# configure the build in 'release' mode using '/usr' as prefix
cmake . -DCMAKE_BUILD_TYPE=Release -DCMAKE_INSTALL_PREFIX=/usr

# compile the application
make
```

```
make install DESTDIR=AppDir

# execute the application
AppDir/usr/bin/appimage-demo-qt5
```



#### 构建 AppImage

```
# run recipe generation assistant
appimage-builder --generate

# create the AppImage
appimage-builder --recipe AppImageBuilder.yml
```

