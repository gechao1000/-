```
脚手架 https://github.com/facebook/create-react-app
yarn create react-app myapp
全局安装非常浪费，只会用一次
```

https://usehooks.com/



Hooks 调用规则

- 只能在函数组件中使用
- 只能在最顶层

```
---- 创建组件（快捷键 rsf ）
import React, { useState } from 'react';

function App(props) {
  const [num, setNum] = useState(0)
  return (
    <div>
      <div>{num}</div>
      <button onClick={() => setNum(num + 1)}>add</button>
    </div>
  );
}

export default App;
```



useEffect 副作用，不是纯函数

```
import { useEffect } from 'react';

useEffect(() => {
  document.title = `点击了${num}次`
  console.log('lll')
}, [num])


---- 绑定dom事件，需要清除
useEffect(() => {
  const updateMouse = (event) => {
    console.log('trigger')
    setPos({x: event.clientX,y: event.clientY})
  }

  console.log('add listener')
  document.addEventListener("click", updateMouse)

  return () => {
    console.log('remove listener')
    document.removeEventListener('click',updateMouse)
  }
})
```



自定义Hooks（文件名 useXXX.js ）

```
import React, { useState, useEffect } from 'react';

function useMouse(props) {
    const [pos, setPos] = useState({ x: 0, y: 0 })

    useEffect(() => {
        const updateMouse = (event) => {
            console.log('trigger')
            setPos({ x: event.clientX, y: event.clientY })
        }

        console.log('add listener')
        document.addEventListener("click", updateMouse)

        return () => {
            console.log('remove listener')
            document.removeEventListener('click', updateMouse)
        }
    })

    return pos;
}

export default useMouse;


--- 调用
import useMouse from './useMouse';
const pos = useMouse();
```

