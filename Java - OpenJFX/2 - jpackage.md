```
Desktop apps
Command Line apps

Linux: rpmutil, dpkg, fakeroot
Windows: WixToolset
macOS：XCode command line tools
```



JNLPConver





---

ImageType 参数

https://docs.oracle.com/en/java/javase/14/docs/specs/man/jpackage.html

https://libraries.io/maven/org.panteleyev:jpackage-maven-plugin

```
<type>DEB</type>

manjaro默认打包为rpm
```





实际命令

```
Command line was: /bin/sh -c /usr/jvm/jdk-17.0.1/bin/jpackage --name TreeFX --dest /app/target/dist --type app-image --app-version 1.0.0 --runtime-image /app/target/treefx --vendor org.acme --module treefx/com.acme.treefx.TreeFX --icon /app/duke.png --java-options -Dfile.encoding=UTF-8 --linux-package-name treefx --linux-menu-group Utilities --linux-app-category Utilities --linux-shortcut
```

