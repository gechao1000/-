```
 /*
  步骤：
  1、创建表空间
  2、创建用户
  3、用户授权
  */
  
  /*创建表空间*/
  create tablespace QCJ_TABLESPACE
  /*表空间物理文件名称*/
  datafile 'QCJ_TABLESPACE.dbf' 
  -- 这种方式指定表空间物理文件位置
  -- datafile 'F:\APP\QIUCHANGJIN\ORADATA\ORCL\QCJ_TABLESPACE.dbf' 
  -- 大小 500M，每次 5M 自动增大，最大不限制
  size 500M autoextend on next 5M maxsize unlimited; 
  
   /* 创建用户*/
  create user qiuchangjin 
  IDENTIFIED BY root --用户密码
  default tablespace QCJ_TABLESPACE-- 表空间是上面创建的
  temporary tablespace TEMP -- 临时表空间默认 TEMP
  profile DEFAULT;
  --password expire;
   /*密码过期需要重设密码,意思是当你用这个新建立的密码过期用户首次登录后，系统会提示你重新输入新密码，不然会拒绝你登陆，重设新密码后就可以登录，该用户的密码就是你新设的密码，相当于首次登录修改密码这样的选项。*/
    
   /* 用户授权_1*/
  grant connect,resource,dba to qiuchangjin;
  
   /* 用户授权_2*/
  grant connect to qiuchangjin with admin option;
  grant dba to qiuchangjin with admin option;
  grant resource to qiuchangjin with admin option;
  
  /*查询所有表空间物理位置*/
  select name from v$datafile;
  /*查询当前用户的表空间*/
  select username,default_tablespace from user_users;
  /*修改用户的默认表空间*/
  alter user 用户名 default tablespace 新表空间; 
  /*查询所有的表空间*/
  select * from user_tablespaces; 
  
  /* 删除表空间*/
  alter tablespace QCJ_TABLESPACE offline;
  drop tablespace QCJ_TABLESPACE including contents and datafiles;

```

### 表空间

```sql
-- 创建
create tablespace 表间名 datafile '数据文件名' size 表空间大小

create tablespace data_test datafile 'e:\oracle\oradata\test\data_1.dbf' size 
2000M;

-- 查看使用情况
select df.tablespace_name "表空间名",totalspace "总空间M",freespace "剩余空间M",(totalspace-freespace) "已用空间M",round((1-freespace/totalspace)*100,2) "使用率%" 
from 
(select tablespace_name,round(sum(bytes)/1024/1024) totalspace 
from dba_data_files 
group by tablespace_name) df, 
(select tablespace_name,round(sum(bytes)/1024/1024) freespace 
from dba_free_space 
group by tablespace_name) fs 
where df.tablespace_name=fs.tablespace_name;

-- 扩展
1.增加数据文件

ALTER TABLESPACE game

ADD DATAFILE '/oracle/oradata/db/GAME02.dbf' SIZE 1000M;

2.手动增加数据文件尺寸

ALTER DATABASE DATAFILE '/oracle/oradata/db/GAME.dbf'

RESIZE 4000M;

3.设定数据文件自动扩展

ALTER DATABASE DATAFILE '/oracle/oradata/db/GAME.dbf

AUTOEXTEND ON NEXT 100M

MAXSIZE 10000M;
```

### 创建用户

```sql
create user 用户名 identified by 密码 default tablespace 表空间表;

create user study identified by study default tablespace data_test;
```

### 授权给用户

```sql
--把 connect,resource权限授予study用户
grant connect,resource to study; 

-- 把 dba权限授予给 study
grant dba to study;

-- 撤权
revoke   权限...   from  用户名;
```

### 创建表和约束

```sql
-- 创建表格语法:     
create table 表名(       

字段名1  字段类型(长度)   是否为空,        

字段名2  字段类型        是否为空 );

-- 增加主键     
alter table 表名 add constraint 主键名 primary key (字段名1);

-- 增加外键:     
alter table 表名 add constraint 外键名 foreign key (字段名1) references 关联表 (字段名2);

-- 创建索引
create index 索引名 on 表名 (字段名1，字段名1);

-- 在建立表格时就指定主键和外键
create table T_SCORE(

 EXAM_SCORE             number(5,2), 

 EXAM_DATE              date,

 AUTOID                 number(10) not null,

 STU_ID                 char(5),

 SUB_ID                 char(3),

 constraint PK_T_SCORE primary key (AUTOID),

 constraint FK_T_SCORE_REFE foreign key (STU_ID) references T_STU (STU_ID));
```

### 索引表空间

```sql
-- 创建一个PK的时候，是自动创建一个与之对应的唯一索引的。
-- 如果不特别指定，那么这个索引的表空间和表格的空间是一样的，但是我们不建议放在一起。
alter table 表名 add constraint 主键名 primary key (字段名1) using index tablespace 索引表空间;

```

