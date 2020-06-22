## AdminLTE Bootstrap Admin Dashboard Template

Best open source admin dashboard & control panel theme. Built on top of Bootstrap, AdminLTE provides a range of responsive, reusable, and commonly used components.

> 官网：https://adminlte.io/
>
> 文档：https://adminlte.io/docs/3.0/index.html
>
> 旧版本，中文：https://github.com/itheima2017/adminlte2-itheima

1. 安装

```
yarn add admin-lte

git clone https://github.com/ColorlibHQ/AdminLTE.git
```

2. 依赖

```
- Bootstrap 4
- jQuery 3.3.1+
- Popper.js 1.14.7+
- 其他Plugins

# popper.min.js 用于设置弹窗、提示、下拉菜单
# 目前 bootstrap.bundle.min.js 已经包含了 popper.min.js。
```

3. 页面引用

```html
<!-- Font Awesome Icons -->
<link rel="stylesheet" href="./static/plugins/fontawesome-free/css/all.min.css">
<!-- Theme Style -->
<link rel="stylesheet" href="./static/adminlte/css/adminlte.min.css">
<!-- SweetAlert2 -->
<link rel="stylesheet" href="./static/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">
<!-- Google Font: Source Sans Pro -->
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">

<!-- Base -->
<script src="./static/plugins/jquery/jquery.min.js"></script>
<script src="./static/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLte -->
<script src="./static/adminlte/js/adminlte.min.js"></script>
<!-- SweetAlert2 -->
<script src="./static/plugins/sweetalert2/sweetalert2.min.js"></script>
```

4. 图标 （Font Awesome）

> 官网：http://www.fontawesome.com.cn/

你可以通过设置CSS前缀`fa`和图标的具体名称，来把Font Awesome 图标放在任意位置。

Font Awesome 被设计为用于行内元素

```
<i class="fa fa-camera-retro"></i>

# 改变大小 (33% 递增)
<i class="fa fa-camera-retro fa-lg"></i> fa-lg
<i class="fa fa-camera-retro fa-2x"></i> fa-2x
<i class="fa fa-camera-retro fa-3x"></i> fa-3x
<i class="fa fa-camera-retro fa-4x"></i> fa-4x
<i class="fa fa-camera-retro fa-5x"></i> fa-5x
```

5. 表单

```html
<div class="card card-primary">
	<div class="card-header">
		<h3 class="card-title">Quick Example</h3>
	</div>
	<form role="form">
		<div class="card-body">
			<div class="form-group">
				<label for="exampleInputEmail1">Email address</label>
				<input type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">
			</div>
		</div>
		<div class="card-footer">
			<button type="submit" class="btn btn-primary">Submit</button>
		</div>
	</form>
</div>
```

```html
<div class="card card-primary">
	<div class="card-header">
		<h3 class="card-title">Quick Example</h3>
	</div>
	<form role="form" class="form-horizontal">
		<div class="card-body">
			<div class="form-group row">
				<label for="exampleInputEmail1" class="col-sm-2 col-form-label">Email address</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" id="inputEmail3" placeholder="Email">
				</div>
			</div>
		</div>
		<div class="card-footer">
			<button type="submit" class="btn btn-primary">Submit</button>
			<button type="submit" class="btn btn-default float-right">Cancel</button>
		</div>
	</form>
</div>
```
