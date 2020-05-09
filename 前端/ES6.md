#### 1. let 和 const 命令

let 声明的变量，只在当前代码块有效

```
for (let i=0; i<5; i++) {
	console.log(i)
}
console.log("global: " + i);
```

const 声明常量，不能被修改，类似java中的final关键字

#### 2. 字符串扩展

新 API

```
let s = "hello heima"
console.log(s.includes("heima"));	//true
console.log(s.startsWith("heima")); //false
console.log(s.endsWith("heima"));	//true
```

字符串模板

```
let s = `
hello
itcast
heima
`;
```

#### 3. 解构表达式

数组解构

```
let arr = [1,2,3];

let [x,y,z] = arr;
let [a] = arr; //只匹配第一个参数
```

对象解构

```
let person = {
    name: "Jack",
    age: 12,
    language: ['java', 'js', 'css']
};

let {name, age, language} = person;
let {name:n} = person; //重命名

# 函数参数
function print({name, age}) {
	console.log(name, age)
}

print(person)
```

#### 4. 函数优化

默认值

```
# 旧
function add(a, b) {
    b = b || 10;
    return a + b;
}

# 新
function add(a, b=10) {
    return a + b;
}
```

箭头函数

```
let sum = (a,b) => a + b;
或
let sum = (a,b) => { return a+b;}
```

对象的函数属性

```
let person = {
    name: 'Jack',
    eat(food) {
    	console.log(this.name, food)
    }
}
```

#### 5. map和reduce

map(): 接受1个函数，原数组所有元素处理后放入新数组

```
let arr = ['1','20','-5','3'];
let newArr = arr.map(s => parseInt(s));
```

reduce(): 从左到右依次用reduce处理，处理结果作为下次reduce的第一个参数

```
let arr = [1,20,-5,3];

arr.reduce((a,b) => a+b)
arr.reduce((a,b) => a*b)

arr.reduce((a,b) => a*b, -1) //指定初始值
```

#### 6. 扩展运算符

将数组转为逗号分隔的参数序列

```
let arr = [1,2,3];
console.log(...arr)

# 数组合并
let arr2= [...arr, ...arr];

# 解构
let [first, ...rest] = arr;
```

#### 7. Promise

异步操作的结果

```
let p = new Promise((resolve, reject) => {
    // 执行异步操作，通常是ajax
    if (true) {
    	resolve(value); // 成功
    } else {
    	reject(error); // 失败
    }
});

p.then(res => {//成功回调})
.cache(err => {//失败回调})
```

#### 8. set和map

Set集合，保存不同元素

```
let set = new Set();
let set2 = new Set([2,3,4,5,5])；

set.add(2);
set.clear();
set.delete(2);
set.has(2); //判断是否存在
set.forEach(...);
set.size; //元素个数
```

Map是<object, object>集合，Object是<string, object>集合

```
let map = new Map();
let map2 = new Map([
    ['key1', 'value1'],
    ['key2', 'value2'],
])；

map.get('key1');
map.set(key, value);
map.forEach((value,key) => {...})
map.size;
map.values(); //迭代器
map.keys(); //迭代器

for(let k of map.keys()) {
	console.log(k);
}
console.log(...map.values())
```

#### 9. class类

```
class User {
	constructor(name, age=10) {
		this.name = name;
		this.age = age;
	}

	sayHello() {
		return 'hello, ' + this.name;
	}

	// 静态函数
	static isAdult(age) {
		return age >= 18;
	}
}

let user = new User('Tom', 12);
user.sayHello();

User.isAdult(12);

# 继承
class Lisi extends User {
	constructor() {
		super('lisi', 22);
	}
}
```

#### 9. Generator函数

异步编程解决方案，每次next返回一个对象，{value: xxx, done: bool}

```
function* hello() {
	yield 'hello'; // 定义内部状态
	yield 'world';
	return 'done';
}

let h = hello();
console.log(h.next());
console.log(h.next());
console.log(h.next());
console.log(h.next());

for (le v of h) { ... }
```

#### 10. 修饰器（当前版本chrome不支持，需要转码器）

本身是一个函数，修改类的行为，ES2017

```
@T
class User {
	constructor(name, age=12) {
		this.name = name;
		this.age = age;
	}
}

function T(target) {
	console.log(target);
	target.country='CN'; //静态属性
}

console.log(User.country)
```

转码器 Babel, Traceur(google公司), UmiJs(阿里)

>  https://umijs.org/zh/ 

```
UmiJs（乌米），基于Babel，可插拔的企业级 react 应用框架

# tyarn使用npm.taobao.org
npm -i yarn tyarn -g
tyarn global add umi

# 测试
umi
# 生成 package.json
tyarn init -y 
# 添加 index.js
umi g page index
# 启动umi后台服务
umi dev
```

#### 11. 模块化

代码拆分，方便重复利用，类似java中的package

```
# Util.js
class Util {
	static sum = (a,b) => a+b;
}

// 导出该类
export default Util;


# index.js
import Util from './Util';
Util.sum(1,2);
```
