## SheetJS

> GitHub：https://github.com/SheetJS/sheetjs

示例

```
https://stackoverflow.com/questions/8238407/how-to-parse-excel-file-in-javascript-html5

https://www.cnblogs.com/liuxianan/p/js-excel.html
```

依赖

```
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.0/jszip.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.0/xlsx.js"></script>
```

#### 解析 excel 到 json

form

```
<form enctype="multipart/form-data">
	<input id="upload" type=file name="files[]">
</form>

document.getElementById('upload').addEventListener('change', handleFileSelect, false);
```

js

```
var ExcelToJSON = function () {
  this.parseExcel = function (file) {
	var reader = new FileReader();

	reader.onload = function (e) {
	  var data = e.target.result;
	  var workbook = XLSX.read(data, {
		type: 'binary'
	  });
	  workbook.SheetNames.forEach(function (sheetName) {
		// Here is your object
		var XL_row_object = XLSX.utils.sheet_to_row_object_array(workbook.Sheets[sheetName]);
		var json_object = JSON.stringify(XL_row_object);
		console.log(JSON.parse(json_object));
		jQuery('#xlx_json').val(json_object);
	  })
	};

	reader.onerror = function (ex) {
	  console.log(ex);
	};

	reader.readAsBinaryString(file);
  };
};

function handleFileSelect(evt) {
  var files = evt.target.files; // FileList object
  var xl2json = new ExcelToJSON();
  xl2json.parseExcel(files[0]);
}
```

