全局配置

```
git config --global user.name "gexc"
git config --global user.email "gexc@budata.com"
```

本地仓库

```
touch README.md
git init
git add README.md
git commit -m "first commit"
```

远程仓库

> 加了参数-u后，以后即可直接用git push 代替git push origin master

```
git remote add origin http://vm:8080/git/root/test.git
git push -u origin master
```

服务器

> 用户名密码：root/root

```
docker pull gitbucket/gitbucket

docker run -d -p 8080:8080 -p 29418:29418 --name git gitbucket/gitbucket
```

分支

```
# 创建
git branch dev

# 切换 
git checkout dev

# 创建并切换
git checkout -b dev

# 合并到当前分支
git merge dev

# 删除 (合并后删除)
git branch -d dev

# 删除 (不合并)
git branch -D dev
```

问题

```
# 撤销上次提交 （不产生commit）
git reset --hard HEAD^
# 撤销到某个 commit
git reset --hard 版本号
# 强制push，因为我们本地库HEAD指向的版本比远程库的要旧
git push -f

# 回滚某个 commit
git revert -n 版本号
```

