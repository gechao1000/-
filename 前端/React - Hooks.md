#### 简介

>  https://zh-hans.reactjs.org/docs/hooks-intro.html 

1.   所有的组件都可以用函数来声明
2.  使用预函数的形式管理`state` 
3.  React Hooks不能出现在条件判断语句中，因为它必须有完全一样的渲染顺序

##### `useState` 函数

>  用来声明状态变量 

```
import React, { useState } from 'react';

const [count, setCount] = useState(0);
const [age, setAge] = useState(18);

参数：状态的初始值(Initial state)
0位：当前的状态值
1位：改变状态值的方法函数
```

##### `useEffect` 函数

> 代替常用的生命周期函数

```
import React, { useState, useEffect } from 'react';

useEffect(() => {
	...
	return () => {} 
})

周期：首次渲染和之后的每次渲染 (componentDidMonut、componentDidUpdate)
useEffect中定义的函数是异步执行，componentDidMonut中的代码是同步执行

# 解绑副作用函数 (实现 componentWillUnmount)
useEffect(() => {
	...
	return () => { ... } 
}, [])	// 当组件将被销毁时才进行解绑

# 只要count状态发生变化，都会执行解绑副作用函数
useEffect(()=>{}, [count])
```

##### `useEContext` 函数

>   接收一个 context 对象（`React.createContext` 的返回值）并返回该 context 的当前值 

```
import React, { useState , useContext } from 'react';

const XXXContext = React.createContext();
<XXXContext.Provider value={xxx}>
	...
</XXXContext.Provider>

# 子组件
const xxx = useContext(CountContext)
```

#####  `useReducer`  函数

>   `useState` 的替代方案 
>
>  使用`useContext`和`useReducer`是可以实现类似`Redux`的效果 

```
import React, { useReducer } from 'react';

const [count, dispatch] = useReducer((state, action) => { ... }, count初始值)
state: 状态
action: 业务逻辑

# 调用方法
当前值：{count}
{() => dispatch('定义的action') }
```

##### `useMemo` 函数

```
import React, { useMemo } from 'react';

useMemo(() => { ... }, [name])
参数匹配成功，才会执行
```

##### `useCallback` 函数

> 自定义 Hooks 函数

```
function useWinSize(){
    const [ size , setSize] = useState({
        width:document.documentElement.clientWidth,
        height:document.documentElement.clientHeight
    })

    const onResize = useCallback(()=>{
        setSize({
            width: document.documentElement.clientWidth,
            height: document.documentElement.clientHeight
        })
    },[]) 
    useEffect(()=>{
        window.addEventListener('resize',onResize)
        return ()=>{
            window.removeEventListener('resize',onResize)
        }
    },[])

    return size;
}
```

