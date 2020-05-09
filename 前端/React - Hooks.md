# React Hooks

>  https://zh-hans.reactjs.org/docs/hooks-intro.html 

1.   所有的组件都可以用函数来声明
2.  使用预函数的形式管理`state` 
3.  React Hooks不能出现在条件判断语句中，因为它必须有完全一样的渲染顺序

#### 1. `useState` 

>   返回一个 state，以及更新 state 的函数 
>

```
import React, { useState } from 'react';

const [count, setCount] = useState(0);
const [age, setAge] = useState(18);

参数：状态的初始值(Initial state)

# 函数式更新 (接收先前的 state，并返回一个更新后的值)
setCount(prevCount => prevCount - 1)

# 惰性初始 state (此函数只在初始渲染时被调用)
useState(() => { ... })
```

#### 2. `useEffect`

> 副作用， 异步执行 （生命周期函数，同步执行）
>
> 默认情况下，在每轮渲染结束后执行 (componentDidMonut、componentDidUpdate)

```
import React, { useState, useEffect } from 'react';

useEffect(() => { ... })

# 解绑副作用函数 (componentWillUnmount)
# 在执行下一个 effect 之前，上一个 effect 就已被清除
useEffect(() => {
	...
	return () => { ... } 
})

# 在只有某些值改变的时候才执行 (只要有一个元素发生变化，就会执行effect函数)
useEffect(()=>{}, [x,y,z])
```

#### 3. `useContext`

>   组件之间值传递（redux统一管理状态）

```
import React, { useState , useContext } from 'react';

const XXXContext = React.createContext();
<XXXContext.Provider value={xxx}>
	...
</XXXContext.Provider>

# 子组件
const xxx = useContext(XXXContext)
```

####  4. `useReducer`

>   使用`useContext`和`useReducer`是可以实现类似`Redux`的效果 

```
import React, { useReducer } from 'react';

const [count, dispatch] = useReducer((state, action) => { ... }, count初始值)
state: 状态
action: 业务逻辑

# 调用方法
当前值：{count}
{() => dispatch('定义的action') }
```

#### 5. `useMemo`

> 在渲染期间执行 

```
import React, { useMemo } from 'react';

useMemo(() => { ... }, [a,b])
参数：'创建'函数、依赖项数组
仅会在某个依赖项改变时才重新计算 memoized 值
```

#### 6. `useCallback` 

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

------

#### 实例：管理表单状态

>  https://blog.csdn.net/EthanQ/article/details/100650253 

1. 使用 `useState` 或 `useReducer`
2.  表单输入可以是几种不同的类型，如文本，数字，日期输入 
3.  有嵌套信息，例如用户的地址信息，它具有子字段，例如address.addressLine1 
4.  根据当前状态更新表单状态，例如**toggle切换按钮** 

###### 使用 `useState`

```
const [count, setCount] = useState(0)

# 根据旧的state计算新的state
setCount(prev => prev + 1)
```

使用 `useReducer`

```
const [state, dispatch] = useReducer((state, action) => {
    const {name ,value} = action;
    return {...state, [name] : value}
}, {})
  
onChange={e => dispatch({ name: 'lastName', value: e.target.value })}
```

