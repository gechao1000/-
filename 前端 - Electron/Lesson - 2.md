electron-vue 脚手架

https://simulatedgreg.gitbooks.io/electron-vue/content/cn/getting_started.html

https://cli.vuejs.org/zh/guide/installation.html

```
yarn global add @vue/cli

vue init simulatedgreg/electron-vue my-project

--- 引入图片 src/render/assets目录
<img id="logo" src="~@/assets/logo.png" />

--- 打开链接
this.$electron.shell.openExternal('https://electron.atom.io/docs/')
```





整合 ant design

https://antdv.com/docs/vue/getting-started-cn/

```
yarn add ant-design-vue

--- 全局引入 main.js
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
Vue.use(Antd)

--- 使用
<a-button type="primary">Primary</a-button>
```



整合axios 

```
----全局引入 main.js
Vue.prototype.$http = axios

---跨域问题 index.js
webPreferences.webSecurity: false


---异步请求
methods: {
	onSearch: async function(val) {
		let result = await this.$http.get(...);
	}
}

---下载功能，axios流 + fs模块
this.$http({
	method: 'get',
	url,
	responseType: 'strem'
}).then(res => res.data.pipe(fs.createWriteStream('aa.jpg')))


---前端？后端？
```



样式

```
.btn-list {
	display: flex;
	justify-content: center;
}
.btn-list btn { margin:0 5px }

---文本太长，多余的显示省略号
.title {
	word-break: break-all;
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: no-wrap;
}

---图片base64，二进制buffer
imgUrl = `data:${mimeType};base64,${img.toString('base64')}`

---强制渲染
$this.forceUpdate()
```



cheerio 库

```
类似jquery，解析html标签
```

