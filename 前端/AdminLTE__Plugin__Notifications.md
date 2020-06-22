### 1. Layer

> 官网：https://layer.layui.com/
>
> 缺点：没有文档，17年就不更新了

```html
-- 依赖 jquery, （不要拆分结构）
<script src="./static/layer/layer.js"></script>
```

使用

```js
layer.alert('内容')
layer.alert('见到你真的很高兴', {icon: 6});

//询问框
layer.confirm('您是如何看待前端开发？', {
	btn: ['重要', '奇葩'] //按钮
}, function () {
	layer.msg('的确很重要', { icon: 1 });
}, function () {
	layer.msg('也可以这样', {
		time: 20000, //20s后自动关闭
		btn: ['明白了', '知道了']
	});
});

//prompt层
layer.prompt({ title: '输入任何口令，并确认', formType: 1 }, function (pass, index) {
	layer.close(index);
	layer.prompt({ title: '随便写点啥，并确认', formType: 2 }, function (text, index) {
		layer.close(index);
		layer.msg('演示完毕！您的口令：' + pass + '<br>您最后写下了：' + text);
	});
});
```

### 2. Toastr

> GitHub：https://github.com/CodeSeven/toastr

```html
-- adminlte之前引入
<link rel="stylesheet" href="./static/plugins/toastr/toastr.min.css">
<script src="./static/plugins/toastr/toastr.min.js"></script>
```

使用

```
// no title
toastr.warning('My name is Inigo Montoya. You killed my father, prepare to die!')
// title
toastr.success('Have fun storming the castle!', 'Miracle Max Says')
toastr.error('xxx', 'Inconceivable!')

// 配置
toastr.success('We do have the Kapua suite available.', 'Turtle Bay Resort', {timeOut: 5000})
```

### 3. Bootbox

> 官网：http://bootboxjs.com/
>
> 不依赖 adminlte

```html
-- 包含locale
<script src="./plugins/bootbox/bootbox.all.js"></script>
```

使用

```js
bootbox.alert("Hello world!");

bootbox.alert("Hello world!", function() {
    console.log("Alert Callback");
});

bootbox.confirm("Are you sure?", function (result) {
    /* your callback code */
})

bootbox.prompt({
    locale: 'zh_CN',	// 中文
	// size: "small",
	title: "What is your name?",
	// value: 'aaa',
	inputType: 'date',
	callback: function (result) {
		console.log(result)
	}
});
```

设置中文

```
bootbox.setDefaults({
	locale: 'zh_CN'
})

bootbox.setLocale('zh_CN')
```

### 4. SweetAlert2

> 官网：https://sweetalert2.github.io/
>
> 主题：https://github.com/sweetalert2/sweetalert2-themes

```html
-- all.js包含默认样式
<link rel="stylesheet" href="./static/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">

<script src="./static/plugins/sweetalert2/sweetalert2.min.js"></script>
```

使用

```js
Swal.fire({
	title: '注销帐号?',
	text: "注销后将无法恢复，请谨慎操作！",
	icon: 'warning',
	showCancelButton: true,
	confirmButtonText: '确定',
	cancelButtonText: '取消',
}).then((isConfirm) => {
	if (isConfirm.value) {
		Swal.fire("成功", "点击了确定", "success");
	} else {
		Swal.fire("取消", "点击了取消", "error");
	}
})


-- 标题（第一个参数）
title，titleText

-- 内容（第二个参数）
html，text

-- icon（第三个参数）
warning, error, success, info，question
```

输入框

```js
-- input
Swal.fire({
	title: 'Enter your IP address',
	input: 'text',
	inputValue: '123',
	showCancelButton: true,
	inputValidator: (value) => {
		if (!value) {
			return 'You need to write something!'
		}
	}
}).then(result => {
	console.log(result.value)
})

-- textarea
Swal.fire({
	input: 'textarea',
	inputPlaceholder: 'Type your message here...',
	inputAttributes: {
		'aria-label': 'Type your message here'
	},
	showCancelButton: true,
	inputValidator: (value) => {
		if (!value) {
			return 'You need to write something!'
		}
	}
}).then(result => {
	console.log(result.value)
})

-- radio
Swal.fire({
	title: 'Select color',
	input: 'radio',
	inputOptions: {
		'#ff0000': 'Red',
		'#00ff00': 'Green',
		'#0000ff': 'Blue'
	},
	inputValidator: (value) => {
		if (!value) {
			return 'You need to choose something!'
		}
	}
}).then(result => {
	console.log(result.value)
})
```