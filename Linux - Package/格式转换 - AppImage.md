```
# 解压 AppImage
./CAJViewer-x86_64-buildubuntu1604-210401.AppImage --appimage-extract


# 打包deb
dpkg-deb --build --root-owner-group cajviewer_1.0.4.0_amd64

ln -s ../share/cajviewer/bin/cajviewer cajviewer
```


一般格式（参考typora）

```
程序安装在 /usr/share/cajviewer

/usr/bin/cajviewer 指向 ../share/cajviewer/bin/cajviewer

# 程序入口，开始菜单点击启动，关联mimetype
/usr/share/applications/cajviewer.desktop

# 注册mimetype类型
/usr/share/mime/packages/cnki-caj.xml

# 程序图标
/usr/share/icons/*
```



文件关联

```
# 更新 mime 缓存（安装deb时会自动执行）
update-mime-database /usr/share/mime
update-desktop-database /usr/share/applications


# 查看文件mimetype
mimetype Paper.caj

# 查看默认程序
gio mime application/x-newtype
```



Linux 系统指定 lib 目录

https://unix.stackexchange.com/questions/22926/where-do-executables-look-for-shared-objects-at-runtime

```
/proc/{pid}/maps
```

