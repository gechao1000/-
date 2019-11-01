#### PS：批量重命名

```powershell
Get-ChildItem | Rename-Item -NewName {...}

# 简单使用
'test_' + $_.name
$_.name -replace '\.txt', '.pdf'

# 正则匹配：aa(bb).cc --> bb.cc
$_.name -replace '.*\((.*)\)\.', '$1.'
```

#### CMD：查杀进程

```powershell
# 查看端口占用
netstat - ano | findstr 8888

tasklist | findstr 6244
taskkill /F /PID 6244

PS: Stop-Process -ID 6244 -Force
```

#### CMD：Idea连接android模拟器

```
D:\dev\Nox\bin\nox_adb.exe connect 127.0.0.1:62001
```

#### Spring Cloud Alibaba Dubbo

```
<!-- https://mvnrepository.com/artifact/com.alibaba.cloud/spring-cloud-alibaba-dubbo -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-alibaba-dubbo</artifactId>
    <version>2.1.0.RELEASE</version>
</dependency>

```

