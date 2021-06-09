https://www.sitepoint.com/build-a-desktop-application-with-electron-and-angular/?utm_source=rss

https://www.section.io/engineering-education/desktop-application-with-react/



```
create-react-app初始化项目
cnpm i -D electron electron-is-dev
cnpm i -D concurrently wait-on cross-env

# public/electron.js
  win.loadURL(
    isDev
      ? 'http://localhost:3000'
      : `file://${path.join(__dirname, './build/index.html')}`
  );

# package.json
"dev": "concurrently \"wait-on http://localhost:3000/ && electron .\"  \"cross-env BROWSER=none npm run start\""
```



---

Electron-builder  https://www.electron.build/

```
npm i electron-builder -D

---配置文件package.json
build: {
	appId: "cloud-doc",
  productName: "中文",
  copyRight: "",
  mac: {...},
  extends: null,
  files: [
  	"build/**/*
  ],
  extraMetaData: { main: "./build/main.js" }
},
homepage: './'  相对路径
scripts: {
	pack: "electron-builder --dir" // 安装之后的文件
  dist: "electron-builder"	//安装包
  prepack: "npm run build" // 打包之前执行build
}
```

减小体积，node-modules文件

```
默认不打包devDepenceny

webpack.config.js
导出：electron-main
```



---

electron-forge  https://www.electronforge.io/



```
yarn create electron-app my-app

yarn start

yarn make

# Ubuntu构建，依赖 dpkg，fakeroot
make --platform linux --targets deb

# Mac构建
make --platform darwin
```

