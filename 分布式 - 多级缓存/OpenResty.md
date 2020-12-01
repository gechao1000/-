1. 安装
>[http://openresty.org/cn/linux-packages.html](http://openresty.org/cn/linux-packages.html)
```plain
# add the yum repo:
wget -P /etc/yum.repos.d/ https://openresty.org/package/centos/openresty.repo
# 可选工具 openresty-resty  openresty-opm  openresty-doc
yum install -y openresty
# 安装目录
/usr/local/openresty
# 启动
openresty
openresty -s stop
```
1. 组件
```plain
# 核心组件
Nginx
LuaJit  :Lua解释器
ngx_lua(http_lua)       :处理http协议
stream_lua  :处理TCP/UDP协议
# Nginx组件，集成在Nginx内部，C模块
ngx_ehco
ngx_headers_more
# Lua组件，lua源码(*.lua)，动态链接(*.so)
lua_cjson
lua_redis
lua_mysql
```
2. 组件管理工具 opm
>[https://opm.openresty.org/](https://opm.openresty.org/)
```plain
# 默认目录openresty/site
opm search xxx
opm get xxx # 安装
opm remote xxx # 卸载
opm list
```
1. 命令行工具 resty
```plain
# 执行Lua代码
resty -e "print('hello')"
# 执行Lua脚本
#!/usr/bin/resty
print('hello')
```
2. 加载 lua 脚本
```plain
# 脚本位置(http模块)
lua_package_path "/usr/local/openresty/nginx/lua/?.lua;;";
# 跨域配置
add_header Access-Control-Allow-Origin *;
add_header Access-Control-Allow-Methods 'GET,POST';
add_header Access-Control-Allow-Headers 'DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Authorization';

# 根据ID实现活动查询
location /act {
content_by_lua_file /usr/local/openresty/nginx/lua/activity.lua;
}
```
