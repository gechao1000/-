允许远程访问

```plain
vim /lib/systemd/system/docker.service
ExecStart 后面添加  -H tcp://0.0.0.0:2375 -H unix:///var/run/docker.sock
# 重启
systemctl daemon-reload
systemctl restart docker
```
Registry2私服
```plain
docker pull registry:2
docker run -d -p 5000:5000 --restart always --name registry registry:2
访问 http://192.168.115.128:5000/v2/_catalog
# 修改docker 配置文件, daemon.json
"insecure-registries": ["宿主机IP:5000"]
```
