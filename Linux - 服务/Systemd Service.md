#### 特点

Systemd Service 是一种替代`/etc/init.d/`下脚本的更好方式

它可以灵活的控制你什么时候要启动服务，一般情况下也不会造成系统无法启动进入紧急模式

广泛应用于新版本的RHEL、SUSE Linux Enterprise、CentOS、Fedora和openSUSE中

#### 基本命令

```
systemctl command xxx.service

# 其中command可以是start、stop、restart、enable等，比如：
systemctl start httpd.service #启动Apache服务
systemctl stop httpd.service #停止Apache服务
systemctl restart httpd.service #停止Apache服务
systemctl enable mariadb.service #将MariaDB服务设为开机启动
```

#### 位置

```
/etc/systemd/system （供系统管理员和用户使用）

/usr/lib/systemd/system（供发行版打包者使用）
```

#### 服务模板

```
[Unit]
Description=Protect ARP list
Wants=network-online.target
After=network.target

[Service]
Type=oneshot
RemainAfterExit=yes
ExecStart=/sbin/arp -f /etc/ip-mac
ExecReload=/sbin/arp -f /etc/ip-mac
ExecStop=/sbin/arp -d -a

[Install]
WantedBy=multi-user.target
```

#### 定义控制单元 [Unit]

```
Description：代表整个单元的描述，可根据需要任意填写

Wants：本单元启动了，它“想要”的单元也会被启动。但是这个单元若启动不成功，对本单元没有影响

Requires: 
这个单元启动了，那么它“需要”的单元也会被启动; 它“需要”的单元被停止了，它自己也活不了。但是请注意，这个设定并不能控制启动顺序，因为它“需要”的单元启动也需要时间，若它“需要”的单元启动还未完成，就开始启动本单元，则本单元也无法启动，所以不建议使用这个字段

OnFailure: 若本单元启动失败了，那么启动这个单元作为折衷

Before/After：指定启动顺序
```

#### 定义服务本体 [service]

```
Type: 服务的类型
 - simple：默认，这是最简单的服务类型。意思就是说启动的程序就是主体程序，这个程序要是退出那么一切皆休
 - forking: 标准 Unix Daemon 使用的启动方式。启动程序后会调用 fork() 函数，把必要的通信频道都设置好之后父进程退出，留下守护精灵的子进程
 - oneshot: 适用于那些被一次性执行的任务或者命令，它运行完成后便了无痕迹。因为这类服务运行完就没有任何痕迹，我们经常会需要使用 RemainAfterExit=yes。意思是说，即使没有进程存在，Systemd 也认为该服务启动成功了。同时只有这种类型支持多条命令，命令之间用;分割，如需换行可以用\
 - dbus: 这个程序启动时需要获取一块 DBus 空间，所以需要和 BusName= 一起用。只有它成功获得了 DBus 空间，依赖它的程序才会被启动
 
ExecStart: 在输入的命令是start时候执行的命令
这里的命令启动的程序必须使用绝对路径，比如你必须用/sbin/arp而不能简单的以环境变量直接使用arp

ExecStop: 在输入的命令是stop时候执行的命令，要求同上

ExecReload: 不是必需，如果不写则你的service就不支持restart命令。
ExecStart和ExecStop是必须要有的
```

#### 安装服务 [install]

```
WantedBy：设置服务被谁装载，一般设置为multi-user.target

Alias：为service设置一个别名，可以使用多个名字来操作服务。

Also：在安装这个服务时候还需要的其他服务
```

