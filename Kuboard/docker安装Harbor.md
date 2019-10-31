##  离线安装 

>  https://github.com/goharbor/harbor 

*  **账号：** admin 
*  **密码：** Harbor12345 

```shell
# 解压
tar -zxvf harbor-offline-installer-v1.8.0.tgz

# 修改
cd harbor
vi harbor.yml
hostname: 192.168.141.202

# 安装
./install.sh
```

## 实现Harbor https认证

>  https://blog.51cto.com/14163901/2420251 

官方文档（待验证）

>  https://github.com/goharbor/harbor/blob/master/docs/configure_https.md 

