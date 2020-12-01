1. 定义
```plain
# http 模块
lua_shared_dict act_cache 128m;
```
2. 存取
```plain
local cache_ngx = ngx.shared.act_cache;
# 读
local actCache = cache_ngx:get(ngx_key)
if actCache == "" or actCache == nil then
    ...
end
# 写
cache_ngx:set(ngx_key, cjson.encode(result), 2*60);
```
3. 清理
```plain
location ~ /purge(/.*) {
  # 代理缓存
  proxy_cache_purge openresty_cache $host$1$is_args$args;
    # 共享内存
  content_by_lua_block {
    ngx.shared.act_cache:flush_all()
  }
}
```
