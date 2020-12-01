#### 安装数据库

```plain
version: '3.1'
services:
mysql:
image: mysql:8
# restart: always
environment:
- MYSQL_ROOT_PASSWORD=123456
- TZ=Asia/Shanghai
command:
--default-authentication-plugin=mysql_native_password
--character-set-server=utf8mb4
--collation-server=utf8mb4_general_ci
--explicit_defaults_for_timestamp=true
--lower_case_table_names=1
ports:
- 3306:3306
volumes:
- ./mysql_data:/var/lib/mysql
oracle:
#  服务名：xe，用户名密码： system/oracle
image: truevoly/oracle-12c
# restart: always
ports:
- 1521:1521
volumes:
- ./oracle_data:/u01/app/oracle
```
#### 表结构 - MySQL

```plain
-- 创建测试表
CREATE TABLE `tb_user` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
`user_name` varchar(20) NOT NULL COMMENT '用户名',
`password` varchar(20) NOT NULL COMMENT '密码',
`name` varchar(30) DEFAULT NULL COMMENT '姓名',
`age` int(11) DEFAULT NULL COMMENT '年龄',
`email` varchar(50) DEFAULT NULL COMMENT '邮箱',
PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARSET = utf8;-- 插入测试数据
INSERT INTO `tb_user` (`id`, `user_name`, `password`, `name`, `age` , `email`)
VALUES ('1', 'zhangsan', '123456', '张三', '18', 'test1@itcast.cn');
INSERT INTO `tb_user` (`id`, `user_name`, `password`, `name`, `age`, `email`)
VALUES ('2', 'lisi', '123456', '李四', '20', 'test2@itcast.cn');
INSERT INTO `tb_user` (`id`, `user_name`, `password`, `name`, `age`, `email`)
VALUES ('3', 'wangwu', '123456', '王五', '28', 'test3@itcast.cn');
INSERT INTO `tb_user` (`id`, `user_name`, `password`, `name`, `age`, `email`)
VALUES ('4', 'zhaoliu', '123456', '赵六', '21', 'test4@itcast.cn');
INSERT INTO `tb_user` (`id`, `user_name`, `password`, `name`, `age`, `email`)
VALUES ('5', 'sunqi', '123456', '孙七', '24', 'test5@itcast.cn');
```
#### 表结构 - Oracle

```plain
--创建表，表名以及字段名都要大写
CREATE TABLE "TB_USER" (
"ID" NUMBER(20) VISIBLE NOT NULL ,
"USER_NAME" VARCHAR2(255 BYTE) VISIBLE ,
"PASSWORD" VARCHAR2(255 BYTE) VISIBLE ,
"NAME" VARCHAR2(255 BYTE) VISIBLE ,
"AGE" NUMBER(10) VISIBLE ,
"EMAIL" VARCHAR2(255 BYTE) VISIBLE
)
--创建序列
CREATE SEQUENCE SEQ_USER START WITH 1 INCREMENT BY 1
```
#### Entity

```plain
@Data
@TableName("tb_user")
public class User {
private Long id;
private String userName;
private String password;
private String name;
private Integer age;
private String email;
}
```
