### JUnit 4

```xml
<!-- https://mvnrepository.com/artifact/junit/junit -->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13</version>
    <scope>test</scope>
</dependency>

Junit4中的@Test是import org.junit.Test;
@BeforeAll, @BeforeEach, @AfterAll, @AfterEach, @Disabled
```



### Junit 5

```xml
<!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.7.2</version>
    <scope>test</scope>
</dependency>

Jupiter中的@Test是import org.junit.jupiter.api.Test;
@BeforeClass, @Before, @AfterClass, @After, @Ignore
```



### 断言

```
assertEquals(expected, actual)
assertTrue(booleanExpression)
assertFalse(booleanExpression)
assertNull(actual)
assertNotNull(actual)
```



### 其他注解

```
@DisplayName("It tests the length of student name should less than 10 chars")

BusinessException businessException = Assertions.assertThrows(BusinessException.class, this::buildStudentDescription);
```



### 设置环境变量

https://junit-pioneer.org/docs/system-properties/

```
<!-- https://mvnrepository.com/artifact/org.junit-pioneer/junit-pioneer -->
<dependency>
    <groupId>org.junit-pioneer</groupId>
    <artifactId>junit-pioneer</artifactId>
    <version>1.4.2</version>
    <scope>test</scope>
</dependency>

@SetSystemProperty(key = "jna.debug_load", value = "true")
@SetSystemProperty(key = "jna.library.path", value = "C:\\jna")
```

