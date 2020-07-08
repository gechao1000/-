### 1. 富文本编辑器

> 官网：https://summernote.org/


```html
-- adminlte之后

<link rel="stylesheet" href="./plugins/summernote/summernote-bs4.css">

<script src="./plugins/summernote/summernote-bs4.min.js"></script>
<script src="./plugins/summernote/lang/summernote-zh-CN.min.js"></script>
```

基本使用

```html
-- html
<div class="card-body pad">
	<div class="mb-3">
		<textarea class="textarea"  id="summernote" placeholder="Place some text here"
				  style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
	  </div>
	  <p class="text-sm mb-0">
		Editor <a href="https://github.com/summernote/summernote">Documentation and license
		information.</a>
	  </p>
</div>

-- js
$('#summernote').summernote({
	lang: 'zh-CN'
})

-- get
var markupStr = $('#summernote').summernote('code');

-- set
$('#summernote').summernote('code', markupStr);

-- enable, disable
$('#summernote').summernote('enable');
$('#summernote').summernote('disable');
```

### 2. 图片上传

> 官网：https://github.com/enyo/dropzone

```

```

### 3. 下拉框

> 官网：https://select2.org/

```html
-- adminlte之前引入

<link rel="stylesheet" href="./plugins/select2/css/select2.min.css">

<script src="./plugins/select2/js/select2.min.js"></script>
```

基本使用

```html
-- html
<div class="form-group">
	<label>Minimal</label>
	<select class="form-control select2" style="width: 100%;">
		<option selected="selected">Alabama</option>
		<option>Alaska</option>
		<option>California</option>
		<option>Delaware</option>
		<option>Tennessee</option>
		<option>Texas</option>
		<option>Washington</option>
	</select>
</div>

-- js
$('.select2').select2()
```

动态数据

```js
-- array
$('#bmcpSelect').select2({
    placeholder: '分类',
    或
    placeholder: {
        id: '-1', 
        text: '分类'
    },
    minimumResultsForSearch: -1, // 隐藏搜索框
    allowClear: true,
    language: 'zh-CN',
    theme: "bootstrap4", // 主题
	data: [
		{ "id": 1, "text": "Option 1" },
		{ "id": 2, "text": "Option 2", "selected": true },
		{ "id": 3, "text": "Option 3", "disabled": true }
	],
})

-- ajax
 $('#bmcpSelect').select2({
	ajax: {
		url: 'http://localhost:3721/api/select',
		dataType: 'json',
		// 请求参数 ?Type=1
		data: {
			'Type': 1
		},
		// 返回结果
		processResults: res => ({results: res.data})
	}
})

-- 查询、分页 "infinite scrolling"
$('#bmcpSelect').select2({
	ajax: {
		delay: 500,
		url: 'http://localhost:3721/api/select',
		dataType: 'json',
		data: (params) => ({
			search: params.term,
			page: params.page || 1    
		}),
		processResults: (res, params) => ({
			results: res.data,
			pagination: { 
				more: (params.page || 1) * 5 < res.count
			 }
		}),
	}
})
```

### 4. Lightbox 

> 官网：http://ashleydw.github.io/lightbox/

```

```

### 5. 穿梭框

> GitHub：https://github.com/istvan-ujjmeszaros/bootstrap-duallistbox

```
不能分页，不能加载大量数据

# 使用远程数据，只能手动添加 option
selectorx.append('<option value="9" selected>ops-coffee.cn</option>');
selectorx.bootstrapDualListbox('refresh');
```

