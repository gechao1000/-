#### 介绍

>[https://mybatis.plus/guide/](https://mybatis.plus/guide/)，[https://github.com/baomidou/mybatis-plus](https://github.com/baomidou/mybatis-plus)
>[https://gitee.com/baomidou/mybatis-plus-samples.git](https://gitee.com/baomidou/mybatis-plus-samples.git)

只做增强不做改变，单表CURD

继承 BaseMapper，根据 Entity 动态生成sql

自动开启下划线转驼峰

#### Entity 注解

```plain
// 表名
@TableName("tb_xxx")
// 数据库ID自增
@TableId(type= IdType.AUTO)
// 字段名不一致
@TableField("email")
// 该字段在数据表中不存在
@TableField(exist = false)
// 大字段不加入查询
@TableField(select = false)
```
#### 通用 CRUD

```plain
// 返回影响的行数，id回填到对象
x.insert(e)
// 更新
x.updateById(e)
x.update(e, QueryWrapper)
x.update(null, UpdateWrapper)
// 删除
x.deleteById()
x.delete(QueryWrapper)
x.deleteBatchIds(Arrays.asList(10L, 11L))
// 查询
x.selectById()
x.selectBatchIds()
x.selectOne(QueryWrapper)  // 超过一条抛出异常
x.selectCount(QueryWrapper)
x.selectList(QueryWrapper)  // null查询全部
// 分页查询，需要先定义PaginationInterceptor
Page<User> page = new Page<>(3,1);
x.selectPage(page, QueryWrapper)
```
#### 常用配置

```plain
# 基础配置
mybatis-plus:
config-location: classpath:mybatis-config.xml
mapper-locations: classpath*:/mapper/**/*.xml  # 默认配置
type-aliases-package: com.example.demo.domain

# 下划线转驼峰，和config-location冲突，他们的功能一样
mybatis-plus.configuration.map-underscore-to-camel-case=true # 默认为true
# 二级缓存
mybatis-plus.configuration.cache-enabled=true # 默认为true
# 全局策略
mybatis-plus:
global-config:
db-config:
id-type: auto # 默认主键类型
table-prefix: tb_ # 表名前缀
```
#### 条件构造器 Wrapper

```plain
QueryWrapper<User> wrapper = new QueryWrapper<>();
allEq(Map<R, V> params)
allEq(Map<R, V> params, boolean null2IsNull)  // 参数为null不加入查询条件
wrapper.eq("password", "123456")
.ge("age", 20)
.in("name", "李四", "王五", "赵六");
// 模糊查询
like("name", "五"); // '%五%'
likeLeft("name", "五"); // '%五'
// 排序
orderByAsc("id", "name")

// 逻辑
eq("id",1).or().eq("name","老王")

// 设置查询字段
select("id", "name", "age")
```
