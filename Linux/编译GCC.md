报错：version GLIBC_2.28 not found

https://www.cxyzjd.com/article/quantum7/106422314

```
检查：
strings /lib/aarch64-linux-gnu/libc.so.6 | grep GLIBC

解决方法：
* 升级glibc，比如自己编译
* 在低版本上重新编译
```



下载 GCC

https://mirrors.tuna.tsinghua.edu.cn/help/gcc.git/

```
git clone https://mirrors.tuna.tsinghua.edu.cn/git/gcc.git
```



升级GCC版本

https://zhuanlan.zhihu.com/p/345928428



CentOS-6 升级GCC

https://www.huaweicloud.com/articles/e193d9c6b6ed96bae815a4482fdda2ce.html 





编译过程报错：configure: error: C++ preprocessor "/lib/cpp" fails sanity check

```
原因：缺少必要的C++库

(1) CentOS系统，运行命令：
　　yum install glibc-headers
　　yum install gcc-c++
(2) Ubuntu系统中，运行命令:
　　apt-get install build-essential
　　apt-get install g++
```



编译过程报错：缺少依赖

```
yum install gmp-devel mpfr-devel libmpc-devel
sudo apt install libgmp-dev libmpfr-dev libmpc-dev
```



开始编译

```
./configure  --prefix=/usr/local/gcc-7.1.0 --enable-checking=release --enable-languages=c,c++ --disable-multilib

make
make install
```



---

GLIBC

```
git clone https://mirrors.tuna.tsinghua.edu.cn/git/glibc.git

https://github.com/bminor/glibc

mkdir build 
cd build
../configure --prefix=/usr/local/glibc-2.27 --without-selinux
```



报错：These critical programs are missing or too old: make bison compiler python

```
yum install -y bision

```



龙芯报错

```
cnki@cnki-CX-TF830-Series:/usr/local/sunlogin/bin$ ls
oray_rundaemon  sunloginclient
cnki@cnki-CX-TF830-Series:/usr/local/sunlogin/bin$ ./sunloginclient 
./sunloginclient: /lib/aarch64-linux-gnu/libcrypt.so.1: version `XCRYPT_2.0' not found (required by ./sunloginclient)
./sunloginclient: /lib/aarch64-linux-gnu/libm.so.6: version `GLIBC_2.27' not found (required by ./sunloginclient)
./sunloginclient: /lib/aarch64-linux-gnu/libm.so.6: version `GLIBC_2.29' not found (required by ./sunloginclient)
./sunloginclient: /lib/aarch64-linux-gnu/libc.so.6: version `GLIBC_2.28' not found (required by ./sunloginclient)
./sunloginclient: /usr/lib/aarch64-linux-gnu/libstdc++.so.6: version `GLIBCXX_3.4.26' not found (required by ./sunloginclient)
cnki@cnki-CX-TF830-Series:/usr/local/sunlogin/bin$ strings /lib/aarch64-linux-gnu/libm.so.6 | grep GLIBC
GLIBC_2.17
GLIBC_2.18
GLIBC_2.23
GLIBC_PRIVAT
```

