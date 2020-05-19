> https://www.centos.bz/2018/06/centos-7设置samba共享目录/
```
yum install -y samba
mkdir /home/samba
chown -R nobody:nobody /opt/shares

systemctl status smb
```



```
vim /etc/samba/smb.conf

[global]
        workgroup = SAMBA
        security = user
        map to guest = Bad User
        log file = /var/log/samba/log.%m


[public]
        comment = Public Stuff
        path = /home/samba
        public = yes
        read only = No
```
