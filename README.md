#### PS：批量重命名
> 例如：aa(bb).cc --> bb.cc
```powershell
Get-ChildItem | Rename-Item -NewName { $_.name -replace '.*\((.*)\)\.', '$1.' }
```
