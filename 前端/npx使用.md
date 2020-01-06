###### 初始化项目 ( 老方法 )

```
npm install -g create-react-app
create-react-app my-app
```

###### 初始化项目 (npx方式)

```
# https://www.electronforge.io/
npx create-electron-app my-app

# react
npx create-react-app my-app

# react native
npx react-native init MyTestApp

# taro
npx @tarojs/cli init myApp
```

##### 临时安装可执行依赖包，不用全局安装，不用担心长期的污染 

*  这条命令会临时安装 `create-react-app` 包，命令完成后`create-react-app` 会删掉，不会出现在 global 中。下次再执行，还是会重新临时安装 

*  非常灵活，你可以使用 vue-cli@2.x，也可以使用 vue-cli@3.x 