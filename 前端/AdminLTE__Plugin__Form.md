### 1. 序列化

> CDN：https://cdnjs.com/libraries/jquery.serializeJSON

```html
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.serializeJSON/2.9.0/jquery.serializejson.min.js"></script>

let res = $('#quickForm').serializeJSON();

-- checkbox
<input type="checkbox" name="HSHFS[]" value="0">
<input type="checkbox" name="HSHFS[]" value="1">
```

### 2. 校验

> 官网：https://jqueryvalidation.org/

```html
-- adminlte之前

-- 内置验证规则：required,email,minlength,equalTo
<script src="./static/plugins/jquery-validation/jquery.validate.min.js"></script>

-- 扩展规则
<script src="./static/plugins/jquery-validation/additional-methods.min.js"></script>

-- 中文提示
<script src="./static/plugins/jquery-validation/localization/messages_zh.min.js"></script>
```

页面

```html
<form role="form" id="quickForm">
	<div class="card-body">
		<div class="form-group">
			<label for="exampleInputEmail1">Email address</label>
			<input type="email" name="email" class="form-control" id="exampleInputEmail1"
				placeholder="Enter email">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">Password</label>
			<input type="password" name="password" class="form-control" id="exampleInputPassword1"
				placeholder="Password">
		</div>
		<div class="form-group mb-0">
			<div class="custom-control custom-checkbox">
				<input type="checkbox" name="terms" class="custom-control-input" id="exampleCheck1">
				<label class="custom-control-label" for="exampleCheck1">I agree to the <a href="#">terms of
						service</a>.</label>
			</div>
		</div>
        <div class="card-footer">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
</form>
```

校验规则

```js
$('#quickForm').validate({
	rules: {
		email: {
			required: true,
			email: true,
		},
		password: {
			required: true,
			minlength: 5
		},
		terms: {
			required: true
		},
	},
    // 自定义提示信息
	// messages: {
	//     email: {
	//         required: "Please enter a email address",
	//         email: "Please enter a vaild email address"
	//     },
	//     password: {
	//         required: "Please provide a password",
	//         minlength: "Your password must be at least 5 characters long"
	//     },
	//     terms: "Please accept our terms"
	// },
	errorElement: 'span',
	errorPlacement: function (error, element) {
		error.addClass('invalid-feedback');
		element.closest('.form-group').append(error);
	},
	highlight: function (element, errorClass, validClass) {
		$(element).addClass('is-invalid');
	},
	unhighlight: function (element, errorClass, validClass) {
		$(element).removeClass('is-invalid');
	},
    submitHandler: function (form) {
        let res = $(form).serializeJSON();
        alert(JSON.stringify(res))
    }
});
```

API

```js
// 验证结果
let result = $('#quickForm').valid();

// 全局设置
jQuery.validator.setDefaults({
    debug: true
});
```

### 3. 日期选择

> 官网：http://www.daterangepicker.com/
>
> 官网：https://tempusdominus.github.io/bootstrap-4/

```html
-- 依赖moment.js
<script src="./static/plugins/moment/moment.min.js"></script>
<script src="./static/plugins/moment/locale/zh-cn.js"></script>

<link rel="stylesheet" href="./static/plugins/daterangepicker/daterangepicker.css">
<link rel="stylesheet" href="./static/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">

<script src="./static/plugins/daterangepicker/daterangepicker.js"></script>
<script src="./static/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
```

选择日期

```html
-- html
<div class="form-group">
	<label>Date:</label>
	<div class="input-group date" id="reservationdate" data-target-input="nearest">
		<input type="text" class="form-control datetimepicker-input"
			data-target="#reservationdate" />
		<div class="input-group-append" data-target="#reservationdate" data-toggle="datetimepicker">
			<div class="input-group-text"><i class="fa fa-calendar"></i></div>
		</div>
	</div>
</div>

-- date only
$('#reservationdate').datetimepicker({
	format: 'L'
});

-- time only
$('#reservationdate').datetimepicker({
	format: 'LT'
});
```

选择日期范围

```html
-- html
<div class="form-group">
	<label>Date range:</label>
	<div class="input-group">
		<div class="input-group-prepend">
			<span class="input-group-text">
				<i class="far fa-calendar-alt"></i>
			</span>
		</div>
		<input type="text" class="form-control float-right" id="reservation">
	</div>
</div>

-- 基本使用
$('#reservation').daterangepicker()

-- 单个日期
$('#reservation').daterangepicker({
	singleDatePicker: true,
	showDropdowns: true,
})

-- 默认范围
$('#reservation').daterangepicker({
	ranges: {
		'Today': [moment(), moment()],
		'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
		'Last 7 Days': [moment().subtract(6, 'days'), moment()],
		'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		'This Month': [moment().startOf('month'), moment().endOf('month')],
		'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	},
	showDropdowns: true,
})
```

### 4. Checkbox, Radio

> GitHub：https://github.com/bantikyan/icheck-bootstrap

```html
<link rel="stylesheet" href="./static/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
```

使用

```html
<div class="form-group clearfix">
	<div class="icheck-primary d-inline">
		<input type="checkbox" id="checkboxPrimary1" checked>
		<label for="checkboxPrimary1">
		</label>
	</div>
	<div class="icheck-primary d-inline">
		<input type="checkbox" id="checkboxPrimary2">
		<label for="checkboxPrimary2">
		</label>
	</div>
	<div class="icheck-primary d-inline">
		<input type="checkbox" id="checkboxPrimary3" disabled>
		<label for="checkboxPrimary3">
			Primary checkbox
		</label>
	</div>
</div>
```

