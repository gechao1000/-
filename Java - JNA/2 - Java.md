https://www.baeldung.com/java-jna-dynamic-libraries

https://github.com/ly3too/java-jna-jni

https://www.eshayne.com/jnaex/index.html



### JNA（Java Native Access）

```
 <dependency>
	<groupId>net.java.dev.jna</groupId>
	<artifactId>jna-platform</artifactId>
	<version>5.8.0</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.junit-pioneer/junit-pioneer -->
<dependency>
    <groupId>org.junit-pioneer</groupId>
    <artifactId>junit-pioneer</artifactId>
    <version>1.4.2</version>
    <scope>test</scope>
</dependency>
```

在接口中定义 methods 和 types，由 jna 创建实例，进行映射

```
-------------- LibC.java --------------
public interface LibC extends Library {
    LibC INSTANCE = (LibC)
            Native.load((Platform.isWindows() ? "msvcrt" : "c"),
                    LibC.class);

    void printf(String format, Object... args);
}

-------------- LibH.java --------------
public interface LibH extends Library {
    LibH INSTANCE = (LibH) Native.load("h1", LibH.class);

    int add(int a, int b);
}
```

测试调用

```
System.setProperty("jna.library.path", "C:\\jna");
System.setProperty("jna.debug_load", "true");
LibH.INSTANCE.add(1, 2)
```

junit测试

```
@SetSystemProperty(key = "jna.debug_load", value = "true")
//@SetSystemProperty(key = "jna.library.path", value = "C:\\jna")
public class AppTest {

    @Test
    @DisplayName("10+20=30")
    public void test() {
        Assertions.assertEquals(30, 10 + 20);
    }

    @Test
    @DisplayName("call cosh(0)=1.0")
    public void testLibC() {
        LibC lib = Native.load(Platform.isWindows() ? "msvcrt" : "c", LibC.class);
        Assertions.assertEquals(1.0, lib.cosh(0));
    }
}
```



### Java native方法

> https://github.com/astonbitecode/j4rs-java-call-rust







-Djna.nosys=true

https://github.com/java-native-access/jna/issues/384



### 加载顺序

```
 System.setProperty("jna.library.path", "C:\\jna");
 System.setProperty("jna.debug_load", "true");
 
 
 --- 首先加载jnidispatch
 com/sun/jna/win32-x86-64/jnidispatch.dll
 
 --- LibH测试：加载h1.dll
 寻找jna.library.path：h1.dll
 寻找system path：h1.dll
 寻找lib- prefix：libh1.dll
 寻找classpath：kbase-lib/target/classes/win32-x86-64/h1.dll
 
 --- LibC测试：加载msvcrt.dll
 寻找jna.library.path
 寻找system path: C:\Windows\System32\msvcrt.dll
```



### 总结

* jni 是 java 和 cpp 混合开发，需要编译，代码复杂
* jna 进行了解耦，代码简单
* jni 比 jna 效率高





JNA-so路径

```
jna.library.path
jna.boot.library.path
jna.platform.library.path
```

GUI框架：javafx

依赖管理工具：gradle

openjdk11 + kotlin + junit5