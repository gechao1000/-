选择文件 Dialog

```
主进程
const{ dialog } = require('electron')

渲染进程
const { remote } = window.require('electron')
remote.dialog.showOpenDialog(...)
```



上下文菜单（主进程）

```
const { Menu, MenuItem } = remote

const menu = new Menu();
menu.append(new MenuItem(...))

// window: remote.getCurrentWindow()
window.addEventListener('contextmenu', ()=> menu.popup(...))
```

