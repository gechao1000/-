#### Yum安装pure-ftpd

`yum install epel-release`

`yum install pure-ftpd`

#### 添加linux用户，创建data目录

`useradd ftpd -s /sbin/nologin -M`

`chown -R ftpd:ftpd /data/ftp`

#### 配置pure-ftpd

`vi /etc/pure-ftpd/pure-ftpd.conf `

- 注释 PAMAuthentication

- 去注释 PureDB

`systemctl start pure-ftpd`

#### 添加ftp虚拟用户

`pure-pw useradd u01 -u ftpd -g ftpd -d /data/ftp`

`pure-pw mkdb`

#### 管理ftp虚拟用户

`pure-pw list`

`pure-pw userdel u01`

`pure-pw show u01`

------

[windows客户端](https://filezilla-project.org/)