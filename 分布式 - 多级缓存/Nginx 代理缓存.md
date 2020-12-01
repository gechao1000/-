### 声明

```plain
#脚本位置
lua_package_path "/usr/local/openresty/nginx/lua/?.lua;/usr/local/openresty/lua-resty-http-master/lib/?.lua;/usr/local/openresty/lua-resty-jwt-master/lib/resty?.lua;;";
# 代理缓存配置
proxy_cache_path /usr/local/openresty/nginx/cache levels=1:2 keys_zone=openresty_cache:10m max_size=10g inactive=60m use_temp_path=off;
# 配置Nginx缓存
location / {
#启用缓存openresty_cache
proxy_cache openresty_cache;
#针对指定请求缓存
#proxy_cache_methods GET;
#设置指定请求会缓存
proxy_cache_valid 200 304 10s;
#最少请求1次才会缓存
proxy_cache_min_uses 3;
#如果并发请求，只有第1个请求会去服务器获取数据
#proxy_cache_lock on;
#唯一的key
proxy_cache_key $host$uri$is_args$args;
proxy_pass http://192.168.115.1:8080;
}
```
### 主动清理（第三方模块）

1. 编译 nginx时添加模块
```plain
# 下载插件
http://labs.frickle.com/nginx_ngx_cache_purge/
# 编译 openresty
./configure --add-module=/usr/local/openresty/modules/ngx_cache_purge-2.3
```
2. 清理缓存
```plain
location ~ /purge(/.*) {
proxy_cache_purge openresty_cache $host$1$is_args$args;
}
```


