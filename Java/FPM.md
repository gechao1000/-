```
rpm -qpl xxx.rpm
```

http://www.ttlsa.com/linux/how-to-use-fpm-rpm/

https://blog.csdn.net/Gushiyuta/article/details/90770681



https://github.com/jordansissel/fpm 

https://fpm.readthedocs.io/en/latest/installing.html#installing-things-fpm-needs

安装FPM

```
yum -y install ruby rubygems ruby-devel

https://mirror.tuna.tsinghua.edu.cn/help/rubygems/
gem sources --add https://mirrors.tuna.tsinghua.edu.cn/rubygems/ --remove https://rubygems.org/

gem install fpm
```

打包

```
/root/www/index.html

fpm -s dir -t rpm -n website -v 1.0.1 -C /root/www/ 
```

