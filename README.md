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

