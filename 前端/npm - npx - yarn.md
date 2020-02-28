###### 设置淘宝镜像 (tyarn最好)

```
# NPM
npm get registry 
淘宝镜像：npm config set registry http://registry.npm.taobao.org/
官方镜像：npm config set registry https://registry.npmjs.org/

# YARN
yarn config get registry
淘宝镜像：yarn config set registry http://registry.npm.taobao.org/
官方镜像：yarn config set registry https://registry.yarnpkg.com

# 未测试
需要执行以下命令更改: npm config set registry https://registry.npm.taobao.org --global npm config set disturl https://npm.taobao.org/dist --global 更改完成使用命令  npm config get registry 查看本地镜像源
```

###### 设置淘宝镜像 （使用第三方软件）

```
npm install -g yrm

# 列出当前可用的所有镜像源
yrm ls

# 使用淘宝镜像源
yrm use taobao

# 测试访问速度
yrm test taobao
```

###### 初始化项目 ( 安装脚手架 )

```
npm install -g create-react-app
或
yarn global add create-react-app (需要配置环境变量)
或
yarn global add create-react-app --prefix /usr/local

create-react-app my-app
```

###### 初始化项目 (不安装脚手架)

```
# https://www.electronforge.io/
npx create-electron-app my-app

# react
npx create-react-app my-app
或
npx create-react-app my-app --template typescript
或
yarn create react-app my-app

# react native
npx react-native init MyTestApp

# taro
npx @tarojs/cli init myApp
```

##### 临时安装可执行依赖包，不用全局安装，不用担心长期的污染 

*  这条命令会临时安装 `create-react-app` 包，命令完成后`create-react-app` 会删掉，不会出现在 global 中。下次再执行，还是会重新临时安装 

*  非常灵活，你可以使用 vue-cli@2.x，也可以使用 vue-cli@3.x 