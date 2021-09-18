## Gitea

> GitHub：https://github.com/go-gitea/gitea

部署 

```yaml
version: '2'
services:
  web:
    image: gitea/gitea:1.12.2
    volumes:
      - ./data:/data
    ports:
      - "3000:3000"
      - "22:22"
    depends_on:
      - db
    restart: always
  db:
    image: mysql:5.7
    restart: always
    environment:
      - MYSQL_ROOT_PASSWORD=123456
      - MYSQL_DATABASE=gitea
      - MYSQL_USER=gitea
      - MYSQL_PASSWORD=123456
    volumes:
      - ./db/:/var/lib/mysql
```



## Gogs

https://github.com/gogs/gogs

```
下载二进制文件
解压到 /home/pi目录
第一次启动 /home/pi/gogs/gogs start
初始化设置 
* 用户设置为pi
* 数据库选择sqllite
* 第一个注册的用户为管理员
* 仓库默认路径~/gogs-repository，没有保存代码
创建/etc/systemd/system/gogs.service（有模版）
```

lfs支持（不知道有什么用）

https://github.com/gogs/gogs/blob/main/docs/admin/lfs.md

文件大小限制（默认最大30M）

https://github.com/gogs/gogs/blob/9fd4f5562d7a59abbef5e78d44fb85a0041e0733/conf/app.ini#L289-L290

