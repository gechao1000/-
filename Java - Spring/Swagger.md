# knife4j

knife4j是为Java MVC框架集成Swagger生成Api文档的增强解决方案

swagger-bootstrap-ui的最后一个版本是1.9.6,已更名为knife4j



#### 访问地址

```
http://${host}:${port}/doc.html
```

#### 依赖

```
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-spring-boot-starter</artifactId>
    <!--在引用时请在maven中央仓库搜索最新版本号-->
    <version>2.0.2</version>
</dependency>
<dependency>
	<groupId>com.google.guava</groupId>
	<artifactId>guava</artifactId>
	<version>29.0-jre</version>
</dependency>

# 之前版本
<dependency>
  <groupId>com.github.xiaoymin</groupId>
  <artifactId>swagger-bootstrap-ui</artifactId>
  <version>1.9.6</version>
</dependency>
```

#### 配置

```
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger-bootstrap-ui RESTful APIs")
                .description("swagger-bootstrap-ui")
                .termsOfServiceUrl("http://localhost:8999/")
                .contact("developer@mail.com")
                .version("1.0")
                .build();
    }
}
```

#### 接口分组排序

```
# 类
@Api(tags = "基础")
@ApiSort(290)
或
@Api(tags = "1.9.5版本-20190728",position = 290)

# 方法
@ApiOperation(value = "注册接口第1步")
@ApiOperationSupport(order=1)
```