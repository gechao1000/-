系统命令

```
md5sum  new.txt

https://www.winmd5.com/?rid=winmd5
```

读取文件

```java
Path path = Paths.get("new.txt");
byte[] bytes = Files.readAllBytes(path);

try(InputStream inputStream = Files.newInputStream(path)) {
    ...
}
```

方式1：JDK

```java
byte[] md5s = MessageDigest.getInstance("MD5").digest(bytes);
String sign = Hex.encodeHexString(md5s);
```

方式2：apache codec

```java
String sign = DigestUtils.md5Hex(bytes);
```

方式3：spring-core

```java
String sign = org.springframework.util.DigestUtils.md5DigestAsHex(bytes);
```

