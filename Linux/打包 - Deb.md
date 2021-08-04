https://www.internalpointers.com/post/build-binary-deb-package-practical-guide



#### deb package 名称

```
<name>_<version>-<revision>_<architecture>.deb

<name> – the name of your application;
<version> – the version number of your application;
<revision> – the version number of the current deb package;
<architecture> – the hardware architecture your program will be run on.

hello_1.0-1_arm64.deb
```



#### deb package 构成

```
# DEBIAN/control
the information about the deb package and the program it installs.

# 其他文件对照linux文件系统，放在对应的目录
<.deb>/usr/local/bin/binaryfile
/usr/local/bin/binaryfile
```



#### control 格式

* 文件最后有空行

* Description属性换行，开始有1个空格

```
Package: hello
Version: 1.0
Architecture: arm64
Maintainer: Internal Pointers <info@internalpointers.com>
Description: A program that greets you.
 You can add a longer description here. Mind the space at the beginning of this paragraph.
```



#### Manjaro 打包 deb

```
dpkg-deb --build --root-owner-group <package-dir>

dpkg-deb --build --root-owner-group helloworld_1.0-1_arm64


----- 制作deb 需要 fakeroot dpkg, 制作rpm需要 rpm-tools
sudo pacman -S base-devel
sudo pacman -S dpkg 
sudo pacman -S rpm-tools
```



#### 安装，卸载

```
sudo dpkg -i <package>

sudo dpkg -r <appname>

dpkg -l | grep <appname>


# 查看Deb内容
dpkg -c <package>
dpkg -L <appname>
```



#### 钩子

```
Four files: postinst, preinst, postrm, and prerm are called maintainer scripts. 

permissions: must be between 0555 and 0775.
```





#### 问题：安装包/usr/local/bin/hello，卸载会删除bin目录

https://stackoverflow.com/questions/13021002/my-deb-file-removes-opt/58066154#58066154

```
That's just debian. Whenever it removes a package from a non-debian standard directory (such as /opt in your case) and there are no files left in that directory, dpkg will try to remove that directory.

If there are some other files in /opt at the time of removal, you'll get a message in the lines of "/opt is not empty; not removed" and that's it.

解决方法：不要使用这个目录
```



#### 问题：Qt创建demo，在ubuntu 不能运行

```
错误信息：libQt5Widgets.so.5加载失败

解决方法：sudo apt install libqt5gui5


----在kylin系统不能运行
错误信息：libQt5Core.so.5  version Qt_5.15 not found

系统默认已经安装libqt5gui5，版本5.12.8

解决方法：不知道
```



debian 目录结构

https://www.debian.org/releases/stable/amd64/apcs02.en.html