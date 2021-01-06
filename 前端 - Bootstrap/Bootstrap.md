### 容器类

Bootstrap 4 需要一个容器元素来包裹网站的内容

* `.container` 类用于固定宽度并支持响应式布局的容器。
* `.container-fluid` 类用于 100% 宽度，占据全部视口（viewport）的容器

### 网格系统

网格类

```
.col- 针对所有设备
.col-sm- 平板 - 屏幕宽度等于或大于 576px
.col-md- 桌面显示器 - 屏幕宽度等于或大于 768px)
.col-lg- 大桌面显示器 - 屏幕宽度等于或大于 992px)
.col-xl- 超大桌面显示器 - 屏幕宽度等于或大于 1200px)
```

规则

* 每一行需要放在设置了 `.container` (固定宽度) 或 `.container-fluid` (全屏宽度) 类的容器中
* 内容需要放置在列中，并且只有列可以是行的直接子节点
* Bootstrap 4 现在使用 flexbox（弹性盒子） 而不是浮动

基本结构

```
<div class="row">
  <div class="col-*-*"></div>
</div>
```

创建相等宽度的列，Bootstrap 自动布局

```
<div class="row">
  <div class="col">.col</div>
  <div class="col">.col</div>
  <div class="col">.col</div>
</div>
```

偏移列

```
<div class="row">
  <div class="col-md-4">.col-md-4</div>
  <div class="col-md-4 offset-md-4">.col-md-4 .offset-md-4</div>
</div>
<div class="row">
  <div class="col-md-3 offset-md-3">.col-md-3 .offset-md-3</div>
  <div class="col-md-3 offset-md-3">.col-md-3 .offset-md-3</div>
</div>
<div class="row">
  <div class="col-md-6 offset-md-3">.col-md-6 .offset-md-3</div>
</div>
```

### 基础表格

结构

```
<table class="table">
    <thead>
      <tr>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Email</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>John</td>
        <td>Doe</td>
        <td>john@example.com</td>
      </tr>
      <tr>
        <td>Mary</td>
        <td>Moe</td>
        <td>mary@example.com</td>
      </tr>
      <tr>
        <td>July</td>
        <td>Dooley</td>
        <td>july@example.com</td>
      </tr>
    </tbody>
</table>
```

样式

```
# <table>
table-striped	在 <tbody> 内的行上看到条纹
table-bordered	为表格添加边框
table-hover		为表格的每一行添加鼠标悬停效果
table-dark		为表格添加黑色背景
table-sm		通过减少内边距来设置较小的表格

# <tr>
table-primary	蓝色: 指定这是一个重要的操作
table-success	绿色: 指定这是一个允许执行的操作
table-danger	红色: 指定这是可以危险的操作
table-info		浅蓝色: 表示内容已变更
...

# <thead>
thead-dark		给表头添加黑色背景
thead-light		给表头添加灰色背景
```

响应式格式

```
table-responsive	在屏幕宽度小于 992px 时会创建水平滚动条
table-responsive-sm		< 576px
table-responsive-md		< 768px
table-responsive-lg		< 992px
table-responsive-xl		< 1200px

# 示例
<div class="table-responsive">
<table class="table">
    ...
</table>
</div>
```

### 表单

控件

```html
<div class="form-group">
  <label for="usr">用户名:</label>
  <input type="text" class="form-control" id="usr">
</div>

<div class="form-group">
  <label for="comment">评论:</label>
  <textarea class="form-control" rows="5" id="comment"></textarea>
</div>

<div class="form-check">
  <label class="form-check-label">
    <input type="checkbox" class="form-check-input" value="">Option 1
  </label>
</div>

<div class="radio">
  <label><input type="radio" name="optradio">Option 1</label>
</div>

<div class="form-group">
  <label for="sel1">下拉菜单:</label>
  <select class="form-control" id="sel1">
    <option>1</option>
    <option>2</option>
    <option>3</option>
    <option>4</option>
  </select>
</div>
```

输入框组

```html
<form>
  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">@</span>
    </div>
    <input type="text" class="form-control" placeholder="Username">
  </div>
 
  <div class="input-group mb-3">
    <input type="text" class="form-control" placeholder="Your Email">
    <div class="input-group-append">
      <span class="input-group-text">@runoob.com</span>
    </div>
  </div>
</form>
```

### 模态框

```html 
<!-- 按钮：用于打开模态框 -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
  打开模态框
</button>
 
<!-- 模态框 -->
<div class="modal fade" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
 
      <!-- 模态框头部 -->
      <div class="modal-header">
        <h4 class="modal-title">模态框头部</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
 
      <!-- 模态框主体 -->
      <div class="modal-body">
        模态框内容..
      </div>
 
      <!-- 模态框底部 -->
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
      </div>
 
    </div>
  </div>
</div>
```

传值（属性）

```js
$('#exampleModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget) // Button that triggered the modal
  var recipient = button.data('whatever') // Extract info from data-* attributes
  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
  var modal = $(this)
  modal.find('.modal-title').text('New message to ' + recipient)
  modal.find('.modal-body input').val(recipient)
})
```

方法

```
$('#exampleModal').modal('hide')
```

