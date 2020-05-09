# TypeScript

>  https://www.tslang.cn/ 

 JavaScript 的一个超集， 添加了可选的静态类型和基于类的面向对象编程

#### 1. 安装

```
npm install -g typescript 

# 编译
tsc helloworld.ts

# 项目中使用
yarn --init -y
tsc --init
yarn add @types/node --dev
```
#### 2. 变量类型

强类型：声明变量的时候，我们必须给他一个类型

```
number, string, boolean
let b:boolean = true

# 枚举
enum Color {Red, Green, Blue}
enum Color {Red = 1, Green, Blue}
enum Color {Red = 1, Green = 2, Blue = 4}
let c: Color = Color.Green;
let colorName: string = Color[2];

any，void
```

引用类型

```
Array, String, Date, RegExp

# 数组
let arr1:number[]
let arr2:Array<string>
arr = [1,2,3]
arr = new Array(1,2,3)

# 元组
let x: [string, number]

# 字符串
let s:String = new String('aaa')
```

#### 3. 函数

```
# 可选参数
(name:string, age?:number) => {...}

# 默认值
(name:string='xxx') => {...}

# 参数个数不确定
(...names:string[]) => {...}

# 返回值
():number => {...}
```

#### 4. 面向对象

```
class Person {
    name: string
    age: number

    constructor(name: string, age: number) {
        this.name = name;
        this.age = age;
    }

    say() {
        console.log('hello')
    }
}

let p: Person = new Person('as', 12)
p.say()
```

访问修饰符

```
public: 默认
protected
private

readonly:必须在生命时或者构造函数里被初始化
```

继承

```
class A extends B {...}

super 调用父类方法
```

接口

```
interface Person {
    name: string
    age: number
    gender?: string

}

let p: Person = {name: 'abc', age: 12}
```

命名空间

```
namespace dev {
	export class A {..}
}

let x: dev.A = new dev.A()
```

