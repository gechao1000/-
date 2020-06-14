### 1. 基本依赖

> 官网：https://datatables.net/

```html
-- adminlte 之前

<!-- DataTables -->
<link rel="stylesheet" href="./plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" href="./plugins/datatables-responsive/css/responsive.bootstrap4.min.css">

<!-- DataTables -->
<script src="./plugins/datatables/jquery.dataTables.min.js"></script>
<script src="./plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
<script src="./plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
<script src="./plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
```

### 2. 初始化

> 所有配置：https://datatables.net/reference/option/

```js
$('#example').DataTable();

$('#example').DataTable({
  "paging": true,
  "lengthChange": false,
  "searching": false,
  "ordering": true,
  "info": true,
  "autoWidth": false,
  "responsive": true,
});

-- 固定高度，不分页
$('#example').DataTable({
    "scrollY": "200px",
    "scrollCollapse": true,
    "paging": false
});

-- 远程数据
$('#example').DataTable({
	// ajax: './data.txt',
	data: dataSet,
	columns: [
		{ title: "Name", data: "Name" },
		{ title: "Position", data: "Position" },
	]
});
```

### 3. 全局设置

> 中文：https://datatables.net/plug-ins/i18n/Chinese

```js
// 中文
$.fn.dataTable.defaults.oLanguage = {
	"sProcessing": "处理中...",
	"sLengthMenu": "显示 _MENU_ 项结果",
	"sZeroRecords": "没有匹配结果",
	"sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
	"sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
	"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
	"sInfoPostFix": "",
	"sSearch": "搜索：",
	"sUrl": "",
	"sEmptyTable": "表中数据为空",
	"sLoadingRecords": "载入中...",
	"sInfoThousands": ",",
	"oPaginate": {
		"sFirst": "首页",
		"sPrevious": "上页",
		"sNext": "下页",
		"sLast": "末页"
	},
	"oAria": {
		"sSortAscending": ": 以升序排列此列",
		"sSortDescending": ": 以降序排列此列"
	}
};

// 其他设置、ajax设置中文
$.extend(true, $.fn.dataTable.defaults, {
    "searching": false,
    "ordering": false,
    "language": {
        "url": "//cdn.datatables.net/plug-ins/1.10.21/i18n/Chinese.json"
    }
});
```

### 4. 绑定事件

```js
var table = $('#example').DataTable();

$('#example tbody').on('click', 'tr', function () {
    var data = table.row(this).data();
    alert('You clicked on ' + data[0] + '\'s row');
});
```

### 5.列渲染

```js
columnDefs: [
    // 格式化
    {
        render: function (data, type, row) {
            return data + ' (' + row[3] + ')';
        },
        targets: 0
    },
    // 隐藏
    { visible: false, targets: [3] }
]
```

添加按钮

```js
// 最后一列
"columnDefs": [{
    "targets": -1,
    "data": null,
    "defaultContent": "<button class='btn btn-info btn-sm'>Click!</button>"
}]

// 绑定事件
 $('#example tbody').on('click', 'button', function () {
     var data = table.row($(this).parents('tr')).data();
     alert(data.name + "'s salary is: " + data.salary);
 });
```

数据嵌套

```
// record
{
    "name": "Tiger Nixon",
    "hr": {
        "position": "System Architect",
        "salary": "$3,120",
        "start_date": "2011/04/25"
    },
    "contact": [
        "Edinburgh",
        "5421"
    ],
    "fruit": [
        "apple",
        "orange"
    ]
}

// 格式化
"columns": [
    { "data": "name" },
    { "data": "hr.position" },
    { "data": "contact.0" },
    { "data": "contact.1" },
    { "data": "hr.start_date" },
    { "data": "hr.salary" },
    { "data": "fruit[, ]" },	// 用,分隔
]
```

### 6. 数据请求

可以前端分页，也可以 服务器分页（包括排序，过滤）

```
// 默认返回格式 
{
	draw: 1,	// Datatables发送的draw是多少那么服务器就返回多少
	recordsTotal: 10,	// 数据库里总共记录数
	recordsFiltered: 10,	// 过滤后的记录数
	data: [...],	// 可以通过ajax.dataSrc更改字段名称
	error: ''	// 可选
}

// 大量数据渲染
"deferRender": true

// 加载等待提示
"processing": true,

// 服务器模式
"serverSide": true,

// 请求参数
ajax: {
	url: 'xxx'
	type: 'POST',
	data:
}

// 刷新
table.ajax.reload();
```

# Extensions

### 1. Buttons

```html
<link rel="stylesheet" href="./plugins/datatables-buttons/css/buttons.bootstrap4.css">

<!-- 依赖 -->
<script src="./plugins/jszip/jszip.js"></script>
<script src="./plugins/pdfmake/pdfmake.js"></script>
<script src="./plugins/pdfmake/vfs_fonts.js"></script>

-- 内置button: copy,csv...
<script src="./plugins/datatables-buttons/js/dataTables.buttons.js"></script>
-- 样式
<script src="./plugins/datatables-buttons/js/buttons.bootstrap4.js"></script>
-- 扩展button
<script src="./plugins/datatables-buttons/js/buttons.html5.js"></script>
```

使用

```js
// 可选按钮 DataTable.ext.buttons
$('#example').DataTable({
	dom: 'Bfrtip',	// 配置 https://datatables.net/reference/option/dom
	buttons: [
		'copy',
		'excel',
		'csv',
		'pdf',
        // 自定义按钮
        {
            text: 'Reload',
            action: function (e, dt, node, config) {
                dt.ajax.reload();
            }
        }
	]
});
```

### 2. Select

```html
<link rel="stylesheet" href="./plugins/datatables-select/css/select.bootstrap4.css">

<script src="./plugins/datatables-select/js/dataTables.select.js"></script>

-- 可选，没发现有什么区别
<script src="./plugins/datatables-select/js/select.bootstrap4.js"></script>
```

使用

```js
let table = $('#example').DataTable({
	select: true
});

var rows = table.rows({ selected: true });
console.log(rows.data())
console.log(rows.count())
```

关联 buttons

```js
$('#example').DataTable({
	dom: 'Bfrtip',
	buttons: [
		'selected',
		'selectedSingle',
		'selectAll',	// 全选
		'selectNone',	// 反选
		'selectRows',
		'selectColumns',
		'selectCells'
	],
	select: true
});

// 全选、反选
table.rows().select();
table.rows().deselect();

// 可以只导出select内容
```

### 3. FixHeader

```html
<link rel="stylesheet" href="./plugins/datatables-fixedheader/css/fixedHeader.bootstrap4.css">

<script src="./plugins/datatables-fixedheader/js/dataTables.fixedHeader.js"></script>
```

使用

```js
let table = $('#example').DataTable({
    paging: false,
    fixedHeader: true
});
```

