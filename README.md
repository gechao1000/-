#### PS：批量重命名

```powershell
Get-ChildItem | Rename-Item -NewName {...}

# 简单使用
'test_' + $_.name
$_.name -replace '\.txt', '.pdf'

# 正则匹配：aa(bb).cc --> bb.cc
$_.name -replace '.*\((.*)\)\.', '$1.'
```
