sudo pacman -S qtcreator
sudo pacman -S djvulibre libspectre patchelf


qmake --version
QMake version 3.1
Using Qt version 5.15.2 in /usr/lib

qt5-base // qmake
/usr/lib/qt

# not work
export PLUGIN_PATH=/usr/lib/qt/plugins:$PLUGIN_PATH

# 没有qmake
export PATH=/home/georgeca/Qt/5.14.2/gcc_64/bin/:$PATH

# 解决style问题
复制/usr/lib/qt/plugins 到 usr/plugins
复制plugins/{platformthemes, styles}模块

---- Project
https://launchpad.net/qpdfview


---- Source
model.h
#include <QPainterPath>



---- Build
lrelease qpdfview.pro
qmake CONFIG+="without_sql" qpdfview.pro
make
It is installed using "make install". The installation paths are defined in "qpdfview.pri".


---- AppImage
https://travis-ci.org/github/probonopd/linuxdeployqt/jobs/774436649

https://github.com/probonopd/linuxdeployqt/releases

https://github.com/AppImage/AppImageKit/releases

linuxdeployqt AppDir/usr/share/applications/qpdfview.desktop  -appimage

./AppDir/
└── usr
    ├── bin
    │   └── qpdfview
    ├── lib
    │   └── qpdfview
    └── share
        ├── appdata
        ├── applications
        ├── icons
        ├── man
        └── qpdfview

--- AppDir
qpdfview
qpdfview.png
qpdfview.desktop

./linuxdeployqt   ./AppDir/qpdfview

-appiamge 打包为AppImage
不加只组织文件夹


https://travis-ci.org/github/probonopd/linuxdeployqt/jobs/774436649

# 版本 5
https://github.com/probonopd/linuxdeployqt/releases

https://github.com/AppImage/AppImageKit/releases                                                                                                                                                                                 


Package

https://medium.com/swlh/how-to-deploy-your-qt-applications-to-linux-operating-system-with-linuxdeployqt-3c004a43c67a


https://github.com/probonopd/linuxdeployqt/issues/60        
