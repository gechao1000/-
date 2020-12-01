docker 管理，运维工具

```plain
docker pull rancher/server
docker run --name=rancher -p 9090:8080 -d rancher/server
```
配置
```plain
# 添加环境：Default -> 环境管理
dev, test, release
# 添加主机：基础架构 -> 主机
docker主机执行脚本，代理容器
# 添加应用：应用 -> 用户
一组服务
```
部署MySQL
```plain
添加服务
取消勾选 ‘创建时总是拉取镜像’
定义名称，image, port, 环境变量
```
