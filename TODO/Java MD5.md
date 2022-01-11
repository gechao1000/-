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
MessageDigest md5 = MessageDigest.getInstance("MD5");

byte[] md5s = md5.digest(bytes);
String sign = Hex.encodeHexString(md5s);


md5.update(buf);
String sign = new BigInteger(1, md5.digest()).toString(16);
```

方式2：apache codec

```java
String sign = DigestUtils.md5Hex(bytes);
```

方式3：spring-core

```java
String sign = org.springframework.util.DigestUtils.md5DigestAsHex(bytes);
```





大文件分片计算

https://stackoverflow.com/questions/9321912/very-slow-to-generate-md5-for-large-file-using-java





文件流

https://gitee.com/sxl_db/OnJava8/blob/master/docs/book/Appendix-New-IO.md