#### Gradle 基于 task

```
task("hello") {
	// config阶段，执行任何task都会输出
    println "hello 123"
	// exec阶段，只有执行hello task才会输出
    doLast {
        println "do last"
    }
}

--- 依赖
task hello2 {
    dependsOn hello
}

--- 关联lifecycle（钩子函数）
task hello3(type: Delete) {
	// 关联 clean task
    println "out hello3"
}
```

#### groovy 语法

```
// 省略括号
println("hello 123")
println "hello 123"

// 定义变量，自动类型推断
def i = 10

// 集合
def list = [1,2]
list << 3

def map = ['k':'v', 'k2':'v2']
map['k3'] = 'v3'
```

