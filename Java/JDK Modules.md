https://jdk.java.net/archive/

https://zhuanlan.zhihu.com/p/142697835

```
class: 属性、方法集合
package: class集合
module: package集合

java --list-modules
java --describe-module java.base

基础模块 java.base
```

module-info.class（放在 java 目录）

```
module java.base {
    exports java.io;
    exports java.lang;
    exports java.lang.annotation;
    ...
```

