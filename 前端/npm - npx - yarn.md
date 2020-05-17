# 依赖管理工具


###### yarn 快速入门

> https://yarn.bootcss.com/docs/usage/

```
# 安装
npm i yarn tyarn -g

# 初始化一个新项目
yarn init

# 添加依赖包
yarn add [package]
yarn add [package]@[version]
yarn add [package]@[tag]

# 将依赖项添加到不同依赖项类别中
yarn add [package] --dev		//devDependencies
yarn add [package] --peer		//peerDependencies
yarn add [package] --optional	//optionalDependencies 

# 升级依赖包
yarn upgrade [package]
yarn upgrade [package]@[version]
yarn upgrade [package]@[tag]

# 移除依赖包
yarn remove [package]

# 安装项目的全部依赖
yarn
```

###### 国内源 (tyarn不需要)

```
# NPM
npm get registry 
淘宝镜像：npm config set registry http://registry.npm.taobao.org/
官方镜像：npm config set registry https://registry.npmjs.org/

# YARN
yarn config get registry
淘宝镜像：yarn config set registry http://registry.npm.taobao.org/
官方镜像：yarn config set registry https://registry.yarnpkg.com
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