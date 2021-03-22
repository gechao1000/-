官网

https://square.github.io/retrofit/

https://www.baeldung.com/retrofit



案例

https://www.jianshu.com/p/308f3c54abdd

https://stackoverflow.com/questions/39953457/how-to-upload-image-file-in-retrofit-2



参考

https://my.oschina.net/ldhy/blog/813122

https://www.oschina.net/p/retrofit?hmsr=aladdin1e1

https://github.com/LianjiaTech/retrofit-spring-boot-starter



---

依赖（序列化工具只需要一个）

```xml
<!-- https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit -->
<dependency>
    <groupId>com.squareup.retrofit2</groupId>
    <artifactId>retrofit</artifactId>
    <version>2.9.0</version>
</dependency>
<!-- https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-gson -->
<dependency>
    <groupId>com.squareup.retrofit2</groupId>
    <artifactId>converter-gson</artifactId>
    <version>2.9.0</version>
</dependency>
<!-- https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-jackson -->
<dependency>
    <groupId>com.squareup.retrofit2</groupId>
    <artifactId>converter-jackson</artifactId>
    <version>2.9.0</version>
</dependency>
```

接口 DemoApi

```java
@GET("demo")
Call<Dict> index();	// 需要json序列化
  
@GET("demo")
Call<ResponseBody> index(); 
```

测试

```java
Retrofit retrofit = new Retrofit.Builder()
  .baseUrl("http://vm:8080/")
  .addConverterFactory(GsonConverterFactory.create())
  .build();

DemoApi demoApi = retrofit.create(DemoApi.class);

Response<Dict> response = demoApi.index().execute();
System.out.println(response.body());
```

