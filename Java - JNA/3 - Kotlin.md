https://www.hicode.club/articles/2020/01/19/1579404425222.html

https://discuss.kotlinlang.org/t/what-is-the-kotlin-equivalent-of-this-jna-code/13697/2

依赖

```
// https://mvnrepository.com/artifact/net.java.dev.jna/jna
implementation("net.java.dev.jna:jna:5.9.0")
// https://mvnrepository.com/artifact/org.junit-pioneer/junit-pioneer
testImplementation("org.junit-pioneer:junit-pioneer:1.4.2")
```



```kotlin
interface LibC : Library {
    fun cosh(value: Double): Double
}

object DyLib {
    val C by lazy {
        Native.load("c", LibC::class.java) as LibC
    }
}

interface LibC: Library {
    companion object {
        val INSTANCE by lazy { Native.load("c", LibC::class.java) }
    }
    // ...
}

// Native.load(if(Platform.isWindows()) "msvcrt" else "c", LibC::class.java) as LibC
```

声明 Struct

```kotlin
@Structure.FieldOrder("field1", "field2", "field3")
class FooType : Structure() {
    @JvmField var field1: Int = 0
    @JvmField var field2: Int = 0
    @JvmField var field3: String = ""
}

@Structure.FieldOrder("field1", "field2", "field3")
data class FooType(
    @JvmField var field1: Int=0,
    @JvmField var field2: Int=0,
    @JvmField var field3: String=""
) : Structure()
```



https://kotlinlang.org/docs/native-c-interop.html