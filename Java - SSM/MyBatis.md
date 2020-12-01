1. 依赖
```plain
org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.3
```
2. 配置
```plain
mybatis:
type-aliases-package: com.example.demo.entity
mapper-locations: classpath:mapper/*.xml
configuration:
log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
# 启动类
@MapperScan("com.example.demo.mapper")
```
3. 原理
```plain
org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration

# 核心
SqlSessionFactoryBean
MapperScannerConfigurer
```
4. 动态数据源
```plain
# 动态数据源实现类
public class DynamicDataSource extends AbstractRoutingDataSource {
@Override
protected Object determineCurrentLookupKey() {
return DataSourceContextHolder.getDataSourceKey();
}
@Override
public void setTargetDataSources(Map<Object, Object> targetDataSources) {
super.setTargetDataSources(targetDataSources);
DataSourceContextHolder.addDataSourceKeys(targetDataSources.keySet());
}
}
# 动态数据源上下文
public class DataSourceContextHolder {
public static final String DB_LOCAL = "local";
public static final String DB_VM = "vm";
private static final ThreadLocal<String> contextHolder = ThreadLocal.withInitial(() -> DB_LOCAL);
public static List<Object> dataSourceKeys = new ArrayList<>();
public static void setDataSourceKey(String key) {
contextHolder.set(key);
}
public static String getDataSourceKey() {
return contextHolder.get();
}
public static void clearDataSourceKey() {
contextHolder.remove();
}
public static boolean containDataSourceKey(String key) {
return dataSourceKeys.contains(key);
}
public static boolean addDataSourceKeys(Collection<? extends Object> keys) {
return dataSourceKeys.addAll(keys);
}
}
```
5. 动态数据源 - Spring整合
```plain
@Bean
@Primary
public DataSource dynamicDataSource(@Qualifier("vmDataSource") DataSource vmDataSource,
@Qualifier("localDataSource") DataSource localDataSource) {
DynamicDataSource ds = new DynamicDataSource();
HashMap<Object, Object> targetDataSources = new HashMap<>();
targetDataSources.put(DataSourceContextHolder.DB_VM, vmDataSource);
targetDataSources.put(DataSourceContextHolder.DB_LOCAL, localDataSource);
ds.setTargetDataSources(targetDataSources);
ds.setDefaultTargetDataSource(localDataSource);
return ds;
}
```
6. 动态数据源 - 切换
```plain
# 手动切换
DynamicDataSourceContextHolder.setDataSourceKey(DynamicDataSourceContextHolder.DB_VM);
# AOP注解
org.springframework.boot:spring-boot-starter-aop
```
