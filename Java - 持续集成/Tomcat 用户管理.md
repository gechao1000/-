首页 -> 点击manager webapp

[http://192.168.115.141:8080/manager/html](http://192.168.115.141:8080/manager/html)

```plain
vim conf/tomcat-users.xml
<tomcat-users>
<role rolename="tomcat"/>
<role rolename="role1"/>
<role rolename="manager-script"/>
<role rolename="manager-gui"/>
<role rolename="manager-status"/>
<role rolename="admin-gui"/>
<role rolename="admin-script"/>
<user username="tomcat" password="tomcat" roles="manager-gui,manager-script,tomcat,admin-gui,admin-script"/>
</tomcat-users>

vim /root/apache-tomcat-8.5.59/webapps/manager/META-INF/context.xml
注释掉<Valve className="org.apache.catalina.valves.RemoteAddrValve"
重启tomcat
```

