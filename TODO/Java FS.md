命名

```
StorePath {group, path}

StorageClient

/{group}/M00/02/44/adsfewrqwe.sh


group =? bucket

fullpath = 文件存储虚拟磁盘路径 + 数据文件两级目录 + 文件名
```



不能叫 FileSystem，FileStore

https://www.programcreek.com/java-api-examples/?api=java.nio.file.FileStore

```
df -T

        FileSystem fs = FileSystems.getDefault();
//        fs.getRootDirectories().forEach(p -> {
//            System.out.println(p);
//        });
        fs.getFileStores().forEach(s -> {
            System.out.println(String.format("%s, %s, %s", s.type(), s.name(), ""));
        });

        FileStore fileStore = Files.getFileStore(Path.of("/"));
        System.out.println(fileStore);
```



https://www.baeldung.com/spring-data-mongodb-gridfs

https://my.oschina.net/u/4395893/blog/3318376

https://www.baeldung.com/configuration-properties-in-spring-boot

https://docs.spring.io/spring-boot/docs/2.6.2/reference/html/configuration-metadata.html#configuration-metadata.annotation-processor