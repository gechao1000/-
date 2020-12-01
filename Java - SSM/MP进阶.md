#### ActiveRecord

```plain
// entity 对象，封装了对数据库访问
XXX extends<XXX>
x.selectById();
x.insert(); # 返回bool
x.updateById(); # 返回bool
x.deleteById(); # 返回bool
// 条件查询
x.selectList(QueryWrapper)
```
#### Oracle 主键 Sequence

```plain
# docker 部署
# 服务名：xe，用户名密码： system/oracle
docker pull truevoly/oracle-12c
docker create --name oracle -p 1521:1521 truevoly/oracle-12c
# id策略
IdType.INPUT
@Bean //Oracle的序列生成器
public OracleKeyGenerator oracleKeyGenerator(){
return new OracleKeyGenerator();
}
// entity 类名配置
@KeySequence(value = "SEQ_USER", clazz = Long.class)
```
#### 插件（拦截器）

使用Bean 或者 mybatis-config.xml 开启

```plain
// 自定义拦截器
@Intercepts({@Signature(
type= Executor.class, // 类型：执行器，参数，结果集，sql构建
method = "update",
args = {MappedStatement.class,Object.class})})
public class MyInterceptor implements Interceptor {...}
// 默认的拦截器
public class MybatisPlusInterceptor implements Interceptor
```
分页插件
```plain
@Bean
public MybatisPlusInterceptor mybatisPlusInterceptor() {
MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
return interceptor;
}
```
乐观锁
```plain
// entity 字段
@Version
private Integer version;
// 数据库字段一般使用int，newVersion = oldVersion + 1
updateById()
// 拦截器
interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
```
#### 扩展BaseMapper（Sql 注入器）

```plain
public interface MyBaseMapper<T> extends BaseMapper<T> {
// 扩展的方法
List<T> findAll();
}
// 覆盖DefaultSqlInjector
public class MySqlInjector extends DefaultSqlInjector {
@Override
public List<AbstractMethod> getMethodList() {
List<AbstractMethod> list = new ArrayList<>();
// 获取父类中的集合
list.addAll(super.getMethodList());
// 再扩充自定义的方法
list.add(new FindAll());
return list;
}
}
public class FindAll extends AbstractMethod {...}
```
#### 自动填充 （插入、更新）

```plain
@TableField(fill = FieldFill.INSERT)
private String password;
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
@Override
public void insertFill(MetaObject metaObject) {
// 先获取到password的值，再进行判断，如果为空，就进行填充，如果不为空，就不做处理
Object password = getFieldValByName("password", metaObject);
if(null == password){
setFieldValByName("password", "888888", metaObject);
}
}
}
```
#### 逻辑删除

```plain
// 默认0代表未删除，1代表删除
查询时会自动过滤
// 字段
ADD COLUMN `deleted` int(1) NULL DEFAULT 0
// Entity
@TableLogic
private Integer deleted;
```
