https://lovesoo.org/2017/01/19/tomcat-process-fails-to-stop-problem-solving/



bin/setenv.sh

```
#!/bin/bash
CATALINA_PID=$CATALINA_HOME/bin/CATALINA_PID
JAVA_OPTS="$JAVA_OPTS -Djna.debug_load=true"
#JAVA_OPTS="$JAVA_OPTS -Djna.debug_load=true -Djna.library.path=/webx/linux"
```



bin/shutdown.sh

```
# 修改最后一行
exec "$PRGDIR"/"$EXECUTABLE" stop -force "$@"
```

