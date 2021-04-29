模拟内存溢出

```
-Xms8m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError
-Xms8m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=F:\t\dump.hprof

# 手动dump内存
jmap -dump:format=b,file=生成的文件名 进程号
jmap -dump:format=b,file=/root/dump.dat 1275

visualvm可以dump
```



GC日志

https://fasterj.com/tools/gcloganalysers.shtml

```
-XX:+PrintGC 输出GC日志
-XX:+PrintGCDetails 输出GC的详细日志
-XX:+PrintGCTimeStamps 输出GC的时间戳（以基准时间的形式）
-XX:+PrintGCDateStamps 输出GC的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800）
-XX:+PrintHeapAtGC 在进行GC的前后打印出堆的信息
-Xloggc:../logs/gc.log 日志文件的输出路径


-XX:+UseG1GC -XX:MaxGCPauseMillis=100 -Xmx256m -XX:+PrintGCDetails -
XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -
Xloggc:F://test//gc.log
```

用例1

```
JAVA_OPTS="-XX:+UseParallelGC -XX:+UseParallelOldGC -Xms64m -Xmx128m -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -
Xloggc:../logs/gc.log -Dcom.sun.management.jmxremote -
Dcom.sun.management.jmxremote.port=9999 -
Dcom.sun.management.jmxremote.authenticate=false -
Dcom.sun.management.jmxremote.ssl=false"
```

用例2

```
JAVA_OPTS="-XX:+UseG1GC -Xmx1024m -XX:MetaspaceSize=64m -XX:MaxGCPauseMillis=100 -
XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -
Xloggc:../logs/gc.log -Dcom.sun.management.jmxremote -
Dcom.sun.management.jmxremote.port=9999 -
Dcom.sun.management.jmxremote.authenticate=false -
Dcom.sun.management.jmxremote.ssl=fals
```

用例3

```
-XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -Xmx6G -XX:MetaspaceSize=128m -
XX:MaxGCPauseMillis=100 -XX:G1NewSizePercent=50 -XX:G1MaxNewSizePercent=80

```



---

```
CATALINA_OPTS="-XX:+UseParallelGC -XX:+UseParallelOldGC -Xms128m -Xmx128m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=../logs/dump.hprof -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -Xloggc:../logs/gc.log -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=9999 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Djava.rmi.server.hostname=192.168.80.128 "



-XX:+UseParallelGC -XX:+UseParallelOldGC -Xms128m -Xmx128m -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -Xloggc:../logs/gc.log



-Xms8m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=../logs/dump.hprof
```

