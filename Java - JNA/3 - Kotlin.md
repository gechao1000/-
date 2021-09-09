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



## HFS实例

依赖

```
// https://mvnrepository.com/artifact/net.java.dev.jna/jna
implementation("net.java.dev.jna:jna:5.9.0")
// https://mvnrepository.com/artifact/org.junit-pioneer/junit-pioneer
testImplementation("org.junit-pioneer:junit-pioneer:1.4.2")
// https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-reflect
implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.30")
```

结构体

```
class HFS_FILE_INFO: Structure() {
    @JvmField var tsDirName = ByteArray(MAX_FILE_ID)
    @JvmField var tsTableName = ByteArray(MAX_TABLE_LENGTH)
    @JvmField var tsFileName = ByteArray(MAX_FILE_ID)
    @JvmField var tsFileType = ByteArray(MAX_FILE_EXT)
    @JvmField var uiFileSize = 0L
    @JvmField var tsCreateDate = ByteArray(20)
    @JvmField var tsModifyDate = ByteArray(20)
    @JvmField var MD5 = ByteArray(33)
    @JvmField var btFlag = Byte.MAX_VALUE
    @JvmField var bIsDir = Byte.MAX_VALUE
    @JvmField var NodeID = 0
    @JvmField var ulPos = 0L
    @JvmField var ulSize = 0L
    @JvmField var tsVirtualName = ByteArray(MAX_FILE_ID + MAX_FILE_EXT)
    @JvmField var uiFileCount = 0L
    @JvmField var uiDirCount = 0L
    @JvmField var pData = ByteArray(NET_DATA_SIZE_T)

    companion object {
        const val MAX_FILE_PATH = 260
        const val MAX_FILE_ID = 255
        const val MAX_FILE_EXT = 32
        const val MAX_TABLE_LENGTH = 256
        const val NET_DATA_SIZE_T = 8
    }
}

---- 字段顺序1
@FieldOrder(
    "tsDirName", "tsTableName", "tsFileName", "tsFileType",
    "uiFileSize", "tsCreateDate", "tsModifyDate", "MD5",
    "btFlag", "bIsDir", "NodeID", "ulPos", "ulSize",
    "tsVirtualName", "uiFileCount", "uiDirCount", "pData",
)

---- 字段顺序2
override fun getFieldOrder(): MutableList<String> {
	val props = HFS_FILE_INFO::class.declaredMemberProperties.map { it.name }.toSet()
	return HFS_FILE_INFO::class.java.declaredFields.map { it.name }.filter { props.contains(it) }.toMutableList()
}
```

结构体2

```
----struct/Constants.kt
const val MAX_FILE_PATH = 260
const val MAX_FILE_ID = 255
const val MAX_FILE_EXT = 32
const val MAX_TABLE_LENGTH = 256
const val NET_DATA_SIZE_T = 8

----struct/HFS_FILE_INFO.kt
class HFS_FILE_INFO: Structure() {
    @JvmField var tsDirName = ByteArray(MAX_FILE_ID)
    @JvmField var tsTableName = ByteArray(MAX_TABLE_LENGTH)
    @JvmField var tsFileName = ByteArray(MAX_FILE_ID)
    @JvmField var tsFileType = ByteArray(MAX_FILE_EXT)
    @JvmField var uiFileSize = 0L
    @JvmField var tsCreateDate = ByteArray(20)
    @JvmField var tsModifyDate = ByteArray(20)
    @JvmField var MD5 = ByteArray(33)
    @JvmField var btFlag = Byte.MAX_VALUE
    @JvmField var bIsDir = Byte.MAX_VALUE
    @JvmField var NodeID = 0
    @JvmField var ulPos = 0L
    @JvmField var ulSize = 0L
    @JvmField var tsVirtualName = ByteArray(MAX_FILE_ID + MAX_FILE_EXT)
    @JvmField var uiFileCount = 0L
    @JvmField var uiDirCount = 0L
    @JvmField var pData = ByteArray(NET_DATA_SIZE_T)

    override fun getFieldOrder(): MutableList<String> =
        HFS_FILE_INFO::class.java.declaredFields.map { it.name }.toMutableList()
    
}
```



接口

```
interface LibHFS : Library {

    companion object {
        val INSTANCE by lazy {
            Native.load("hfsclient", LibHFS::class.java) as LibHFS
        }
    }

    fun CheckNetWork(ip: String, port: Int = 8810): Boolean

    fun InitApplication(
        ip: String,
        port: Int = 8810,
        appId: Int = 1024,
        appName: String = "HFMS",
        appKey: String = "f4b871d85cd746021b451487849e3cdf"
    ): Boolean

    fun OpenStream(filename: String, mode: String): Pointer

    fun CloseStream(fp: Pointer): Long

    fun ReadStream(fp: Pointer, buf: ByteArray, size: NativeLong): Long

    fun EofStream(fp: Pointer): NativeLong

    fun GetStreamInfo(fp: Pointer, info: HFS_FILE_INFO): Boolean

```

测试下载

```
LibHFS.INSTANCE.InitApplication(ip)

val fp = LibHFS.INSTANCE.OpenStream(filename, "rb")
val bos = FileOutputStream("demo.caj").buffered()

val buf = ByteArray(1024 * 8)
while (LibHFS.INSTANCE.EofStream(fp).toInt() != -1) {
	val size = LibHFS.INSTANCE.ReadStream(fp, buf, NativeLong(1024 * 8))
	bos.write(buf, 0, size.toInt())
}

bos.close()
LibHFS.INSTANCE.CloseStream(fp)
```

测试获取文件信息

```
LibHFS.INSTANCE.InitApplication(ip)

val fp = LibHFS.INSTANCE.OpenStream(filename, "rb")
val info = HFS_FILE_INFO()
LibHFS.INSTANCE.GetStreamInfo(fp, info)
println(info)

LibHFS.INSTANCE.CloseStream(fp)
```



ByteArray 转 String 问题

```
ByteArray(32)大小固定，导致这种结果[99, 97, 106, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

直接使用 String(bytes) 结果length=32，结尾有乱码
应该用 String(bytes, 0, bytes.indexOf(0)) 结果length=3

fun ByteArray.trimString() = String(this, 0, this.indexOf(0))
```

