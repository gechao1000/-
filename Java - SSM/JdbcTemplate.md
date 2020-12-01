1. 依赖
```plain
org.springframework.boot:spring-boot-starter-jdbc
```
2. 数据源配置
```plain
spring:
datasource:
driver-class-name: com.mysql.cj.jdbc.Driver
url: jdbc:mysql://192.168.115.128:3306/heima?useUnicode=true&characterEncoding=utf8
username: root
password: 123456
```
3. 基本操作
```plain
# 删除
jdbcTemplate.update("delete from tb_user where id = ?", 1);
# 查询
List<String> nameList = jdbcTemplate.query("select * from tb_user", (rs, i) -> {
String name = rs.getString("name");
return name;
});
```
4. 原理
```plain
org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration
# 核心
DataSourceAutoConfiguration
JdbcTemplateConfiguration
```
5. 多数据源配置
```plain
配置多个 DataSource
配置多个 JdbcTemplate
@Bean
public DataSource localDataSource() {
DataSource ds = DataSourceBuilder.create()
.driverClassName("com.mysql.cj.jdbc.Driver")
.url("jdbc:mysql://localhost:3306/heima?useUnicode=true&characterEncoding=utf8")
.username("root")
.password("123456")
.build();
return ds;
}
@Bean
public JdbcTemplate localJdbcTemplate(@Qualifier("localDataSource") DataSource dataSource) {
return new JdbcTemplate(dataSource);
}
```
6. 多数据源使用
```plain
@Resource(name = "localJdbcTemplate")
private JdbcTemplate jdbcTemplate;
或
@Resource
private JdbcTemplate localJdbcTemplate;
```
