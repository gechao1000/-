#### 安装gcc


```
yum install -y gcc automake autoconf libtool make
# pid锁定
rm -f /var/run/yum.pid
# centos 
yum groupinstall "Development Tools"
# ubuntu
apt-get install build-essential
```

#### 安装redis

```
wget http://download.redis.io/releases/redis-5.0.5.tar.gz
tar xzf redis-5.0.5.tar.gz
cd redis-5.0.5
make MALLOC=libc
```

```
make PREFIX=/usr/local/redis install
cp redis.conf /usr/local/redis
ln -s /usr/local/redis/bin/redis-server /usr/local/bin
ln -s /usr/local/redis/bin/redis-cli /usr/local/bin

redis-server
redis-cli -h 地址 -p 端口 -a 密码
```

#### 安装RedisSearch

###### <https://github.com/RediSearch/JRediSearch>

```
# 安装cmake
sh cmake-3.14.5-Linux-x86_64.sh --prefix=/usr/local --exclude-subdir

git clone https://github.com/RediSearch/RediSearch.git
cd RediSearch
mkdir build
cd build
cmake .. -DCMAKE_BUILD_TYPE=RelWithDebInfo
make

redis-server --loadmodule ./redisearch.so

git clone https://github.com/RediSearch/RediSearch.git
cd RediSearch
make
redis-server --loadmodule ./src/redisearch.so
```

#### 安装RediSQL

##### <https://github.com/RedBeardLab/rediSQL>

```
redis-server redis.conf --loadmodule ./redisql.so

redis-server --loadmodule ./redisql.so
```

#### 安装GLIBC_2.18(放弃)

```
strings /lib64/libc.so.6 | grep GLIBC_

curl -O http://ftp.gnu.org/gnu/glibc/glibc-2.18.tar.gz
tar zxf glibc-2.18.tar.gz 
cd glibc-2.18/
mkdir build
cd build/

# 方案1
../configure --prefix=/usr
make -j2
make install
# 方案2
../configure --prefix=/usr --disable-profile --enable-add-ons --with-headers=/usr/include --with-binutils=/usr/bin
make -j 8
make install

```

