# TypeScript

>  https://www.tslang.cn/ 		https://www.typescriptlang.org/

TypeScript 是 JavaScript 类型的超集， 它可以编译成纯 JavaScript

#### 1. 环境

```
yarn global add typescript ts-node

# 编译
tsc helloworld.ts

# 直接运行ts
ts-node helloworld.ts

# vscode 设置
引号类型：single （typescript.preferences.quoteStyle）
tabsize：2

# vscode插件 Prettier - Code formatter
选择格式化程序 "editor.defaultFormatter": "SimonSiefke.prettier-vscode"
```

#### 2. 基础类型

```typescript
let isDone: boolean = true

// ts 中所有的数字都是浮点数
let count: number = 10.12

// 可以使用模版字符串
let person: string = `asdf ${1 + 2}`

// 数组，元组
let arr: number[] = [1,2,3]
let x: [number,string] = [12, 'xiao']

// 枚举
enum Color { Red = 1, Green, Blue }
let c = Color.Green
let colorName = Color[2]
```

#### 3. 类型检查

尽量使用 interface ，不要使用 type

```typescript
interface Person {
  name: string
  age?: number	// 可选属性
  readonly gender: number	// 只读属性
  [propName: string]: any	// 额外的属性，key是string类型，value任意类型
  say(): string
}

let p: Person = {
  name: 'xiao',
  gender: 0,
  aa: true,
  bb: false,
  say() {
    return '123'
  }
}
```



## 爬虫项目

#### 1. 初始化

```
yarn init -y
tsc --init

# 依赖
yarn add cheerio superagent
yarn add -D @types/cheerio @types/superagent ts-node typescript

url = `http://www.dell-lee.com/typescript/demo.html?secret=${this.secret}`
```

#### 2. 解析html

```
import superagent from 'superagent'
import cheerio from 'cheerio'

// 获取html内容
const result = await superagent.get(this.url)

// 解析标签，使用jquery语法
const $ = cheerio.load(result.text)
$('.course-item')
$(element).find('.course-desc').eq(0).text()
```

#### 3. 保存到 json 文件

```
import fs from 'fs'
import path from 'path'

// 文件路径
const filePath = path.resolve(__dirname, './data/course.json')

// 读取
if (fs.existsSync(filePath)) {
  content = JSON.parse(fs.readFileSync(filePath, 'utf-8'))
}

// 写入
fs.writeFileSync(filePath, JSON.stringify(content))
```

