### 原生菜单

```
const {app, BrowserWindow, Menu} = require('electron')

app.on('ready', () => {
    ...
    const menu = Menu.buildFromTemplate([
        {
            label: '文件',
            submenu: [
                {
                    label: 'open file'
                }
            ]
        },
        {
            label: 'Help',
            submenu: [
                {
                    label: 'About'
                    click() {
                        new BrowserWindow({
                            width: 300,
                            height: 200
                        })
                    }
                }
            ]
        }
    ])
    Menu.setApplicationMenu(menu)
})
```



### 自定义顶部菜单

```
-- 无边框窗口
new BrowserWindow({
	frame: false 
})

-- 在html编辑
<div class="custom-menu">
    <ul>
        <li>File</li>
        <li id="btnHelp">Help</li>
    </ul>
</div>

<script>
const {ipcRenderer} = require('electron')
document.getElementById('btnHelp').addEventListener('click', () => {
	// 通过 IPC 调用main方法
})
</script>

<style>
    * {
        margin: 0;
        padding: 0;
    }

    .custom-menu {
        height: 50px;
        width: 100%;
        background: pink;
        /*支持拖拽*/
        -webkit-app-region: drag;
    }
    .custom-menu ul {
        list-style: none;
    }
    .custom-menu ul li {
        float: left;
        width: 50px;
        line-height: 50px;
        text-align: center;
        margin-left: 10px;
        -webkit-app-region: no-drag;
    }
</style>
```



### 默认浏览器打开 URL

```
const { shell } = require('electron')
shell.openExternal("https://github.com")
```



### 对话框读取文件（main process）

```
const { dialog } = require('electron')
const fs = require('fs')

-- 返回 fullpath
const res = dialog.showOpenDialogSync({
    title: '选择文件',
    //buttonLabel: 'AAA',
    filters: [
    	{name: 'Custom File Type', extensions: ['js']},
        {name: 'All Files', extensions: ['*']},
    ]
})

-- 返回 buffer
const contet = fs.readFileSync(res[0])
console.log(contet.toString())
```



### 对话框保存文件（main process）

```
const { dialog } = require('electron')
const fs = require('fs')

-- 返回 fullpath
const res = dialog.showSaveDialogSync({
    title: '保存文件',
    //buttonLabel: 'AAA',
    filters: [
    	{name: 'index', extensions: ['js']},
    ]
})

-- 创建并写入
fs.writeFileSync(res, "asdfasdf")
```



### 定义快捷键（main process）

```
const { globalShortcut} = require('electron')

app.on('ready', () => {
    ...
    
    globalShortcut.register('CommandOrControl+T', () => {
        console.log(1111)
    })

})

-- 最大化
win.maximize()
win.unmaximize()


-- 关闭
win.close()
```

