### 多租户

```plain
在多用户的环境下，共有同一套系统，并且要注意数据之间的隔离性
1. 一个租户使用一个数据库
2. 同一个数据裤，但是每个租户对应一个Schema(数据库user)
3. 同一个数据库，同一个Schema，但是在表中增加了租户ID的字段
```
1. 配置
```plain
public class BuShopHandler implements TenantLineHandler {
@Override
public Expression getTenantId() {
return new StringValue("01");
}
@Override
public boolean ignoreTable(String tableName) {
// 默认返回 false 表示所有表都需要拼多租户条件
return false;
}
}
// 配置类 MybatisPlusInterceptor
interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(buShopHandler));
// 运行结果
select * from tb_xxx where tenant_id = '01'
```
### 动态表名

```plain
public class BuShopTableNameHandler implements TableNameHandler {
@Override
public String dynamicTableName(String sql, String tableName) {
return tableName + "_01";
}
}
// 配置类 MybatisPlusInterceptor
DynamicTableNameInnerInterceptor tableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
HashMap<String, TableNameHandler> tables = new HashMap<>(2);
tables.put("tb_user", buShopTableNameHandler);  // 每一个table需要单独配置
tableNameInnerInterceptor.setTableNameHandlerMap(tables);
interceptor.addInnerInterceptor(tableNameInnerInterceptor);
// 运行结果
select * from tb_xxx_01
```
### 全局 Properties

```plain
mybatis-plus:
configuration:
variables:
AAA: "01"
# 或
#  configuration-properties:
#    AAA: "01"
// mybatis 原生配置
mybatis.configuration-properties.AAA=abc
mybatis.configuration.variables.AAA=abc
// mybatis-config.xml配置
<properties>
<property name="dbname" value="mydb"/>
</properties>

// 使用方法
@TableName(value = "tb_user_${AAA}")

// 更新
configuration.getVariables().put("global_param", "123");
```
### 
