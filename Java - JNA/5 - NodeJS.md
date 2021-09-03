https://github.com/node-ffi/node-ffi  （很久没更新了）

https://github.com/node-ffi-napi/node-ffi-napi

https://github.com/nodejs/node-gyp#installation



https://www.cnblogs.com/silenzio/p/11606389.html

https://juejin.cn/post/6844903645905977357#heading-13

https://my.oschina.net/ahaoboy/blog/4564148



ffi只接受纯C函数, 确切的说, 是按照C标准编译的函数

```
npm install ffi-napi

---- 自己编译
npm install -g node-gyp
git clone git://github.com/node-ffi-napi/node-ffi-napi.git
cd node-ffi
node-gyp rebuild
```



windows测试用例

```
const ffi = require('ffi-napi');
/**
 * 先定义一个函数, 用来在窗口中显示字符
 * @param {String} text
 * @return {*} none
 */
function showText(text) {
  return new Buffer(text, 'ucs2').toString('binary');
};
// 通过ffi加载user32.dll
const myUser32 = new ffi.Library('user32', {
  'MessageBoxW': // 声明这个dll中的一个函数
    [
      'int32', ['int32', 'string', 'string', 'int32'], // 用json的格式罗列其返回类型和参数类型
    ],
});

// 调用user32.dll中的MessageBoxW()函数, 弹出一个对话框
const isOk = myUser32.MessageBoxW(
    0, showText('I am Node.JS!'), showText('Hello, World!'), 1
);
console.log(isOk);
```



linux测试用例

```
var ffi = require('ffi-napi');

var libm = ffi.Library('libm', {
  'ceil': [ 'double', [ 'double' ] ]
});
libm.ceil(1.5); // 2

// You can also access just functions in the current process by passing a null
var current = ffi.Library(null, {
  'atoi': [ 'int', [ 'string' ] ]
});
current.atoi('1234'); // 1234
```



路径设置

```
//方法一， 调用winapi SetDllDirectoryA设置目录
const ffi = require('ffi')

const kernel32 = ffi.Library("kernel32", {
'SetDllDirectoryA': ["bool", ["string"]]
})
kernel32.SetDllDirectoryA("pathToAdd")

//方法二（推荐），设置Path环境环境
process.env.PATH = `${process.env.PATH}${path.delimiter}${pathToAdd}`
```

