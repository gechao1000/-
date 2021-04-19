## SSH 连接远程服务器，执行交互式 shell 脚本



SpringBoot排除 slf4j-log4j12，log4j

```xml
<!-- https://mvnrepository.com/artifact/net.itransformers/expect4java -->
<dependency>
    <groupId>net.itransformers</groupId>
    <artifactId>expect4java</artifactId>
    <version>1.0.6</version>
</dependency>
```

SSH Example

```java
CLIConnection sshConn = new LoggableCLIConnection(
        new SshCLIConnection(),
        msg -> System.out.println(">>> "+msg),
        msg -> System.out.println("<<< "+msg)
);
Map<String, Object> connParams = new HashMap<>();
Properties config = new Properties();
config.put("StrictHostKeyChecking", "no");
config.put("PreferredAuthentications", "keyboard-interactive,password");

connParams.put("username", "root");
connParams.put("password", "123456");
connParams.put("address", "192.168.80.128");
connParams.put("port", 22);
connParams.put("timeout", 1000);
connParams.put("config", config);

sshConn.connect(connParams);

Expect4j e4j = new Expect4jImpl(sshConn);
e4j.send("uname -a\n");
e4j.expect( new RegExpMatch("Linux ([^ ]*) .*",
        (ExpectContext context) -> System.out.println("### Found: "+context.getMatch(1)))
);
e4j.send("uname -a\n");
e4j.expect( new RegExpMatch("Linux ([^ ]*) .*",
        (ExpectContext context) -> System.out.println("### Found: "+context.getMatch(1)))
);
e4j.send("exit\n");
e4j.expect(new EofMatch());
e4j.close();
sshConn.disconnect();
```

KBase 注册

```java
CLIConnection sshConn = new SshCLIConnection();
...
e4j.send("sh /kbase/client/kbase_cmd.sh\n");
e4j.expect( new RegExpMatch("welcome to kbase",
        (ExpectContext context) -> System.out.println("### KBase Client Start"))
);

final AtomicBoolean isConnected = new AtomicBoolean(false);
final AtomicInteger errid = new AtomicInteger(0);
final AtomicBoolean register = new AtomicBoolean(true);
e4j.send("connect\n");
e4j.expect(new Match[] {
        new RegExpMatch("connect ok", (ExpectContext it) -> {
            System.out.println("### KBase Client Connect OK");
            isConnected.set(true);
        }),
        new RegExpMatch("FTS_OpenConn error, errid = (-\\d+)", (ExpectContext it) -> {
            System.out.println("### KBase Client Connect Failed");
            errid.set(Integer.valueOf(it.getMatch(1)));
        }),
});

if (isConnected.get()) {
    System.err.println("-----------------Register---------------");
    e4j.send("register " + code + "\n");
    e4j.expect( new RegExpMatch("register error, errid = (-\\d+)",
            (ExpectContext it) -> {
                System.out.println("### KBase Client Register Failed: " + it.getMatch());
                System.out.println("### KBase Client Register Failed: " + it.getMatch(1));
                register.set(false);
                errid.set(Integer.valueOf(it.getMatch(1)));
            })
    );


    e4j.send("disconnect\n");
    e4j.expect( new RegExpMatch("disconnect ok",
            (ExpectContext context) -> System.out.println("### KBase Client Disconnect"))
    );
}


e4j.send("quit\n");
e4j.expect( new RegExpMatch("Exiting kbase",
        (ExpectContext context) -> System.out.println("### KBase Client Quit"))
);
e4j.send("exit\n");
e4j.expect(new EofMatch());
e4j.close();
sshConn.disconnect();

if (isConnected.get() && register.get()) {
    return BaseResponse.success();
} else {
    return BaseResponse.failed(TPI_ENUM.getLabel(TPI_RESULT_MSG.class, errid.get()));
}
```

