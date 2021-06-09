拖动文件，展示内容

```
let fs = require('fs');
 
let $area = document.getElementById('area')
let $content = document.getElementById('content')
$area.addEventListener('drop', (e) => {
    e.preventDefault()
    e.stopPropagation()
    for (let file of e.dataTransfer.files) {
        console.log(file.path)
        fs.readFile(file.path, (err, data) => {
            if (err) {
                console.dir(err);
                return;
            }
            let newEle = document.createElement('div')
            newEle.className = 'content'
            newEle.innerHTML = `
                <h3>${file.name}</h3>
                <pre>${data}</pre>
            `
            $content.appendChild(newEle)
        })
    }
})
$area.addEventListener('dragover', (e) => {
    e.preventDefault()
    e.stopPropagation()
})
```



Dialog 选择文件（main process）

```
const { dialog } = require('electron');

dialog.showOpenDialog({
  // 选择文件，允许多选
  properties: ['openFile', 'multiSelections']
}).then(result => {
  console.log(result.filePaths)
  console.log(result.canceled) //是否点击取消按钮
})
```

