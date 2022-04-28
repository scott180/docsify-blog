# mysql笔记

## 1、安装mysql

> 安装mysql 5.7

```js
参考
http://blog.xushufa.cn
http://blog.csdn.net/wengengeng/article/details/52013650
https://www.cnblogs.com/jyjia/archive/2019/03/07/10490013.html


下载mysql
https://www.mysql.com/
https://www.aliyundrive.com/s/pymjQca3DbY

```


```java
下载mysql

    进入官网：https://www.mysql.com/

    单击【Downloads】选项卡
    最下面有个【 MySQL Community Edition  (GPL)】，单击【Community (GPL) Downloads »】
    单击【MySQL Community Server (GPL)】下的【DOWNLOAD】
    在弹出的页面上下载【Windows (x86, 64-bit), ZIP Archive】

安装mysql
压缩包相当于免安装文件，要想使用它，需要配置正确，并通过服务来启动数据库服务。
1.把压缩包解压到你喜欢的位置
本示例解压到：D:\mysql-5.7.13-winx64，文件夹下



2.创建my.ini文件（在 D:\mysql-5.7.13-winx64 目录），内容如下：

    [mysqld]  
    port=3306  
    basedir  ="D:\\mysql-5.7.13-winx64\\"  
    datadir  ="F:\\mysqlData\\"  
      
      
    max_allowed_packet = 32M  

 注意，basedir和datadir是必须要配置的，basedir就是你解压的目录。官方文档上说，如果你喜欢用反斜杠，则要用双反斜杠，斜杠的话就不用这样。即：D:\\mysql-5.7.13-winx64\\ 或：D:/mysql-5.7.13-winx64/
由于本人喜欢把数据库的数据文件独立出来，所以就把datadir配置到其它地方，方便管理。另外，创建该目录。

```


```java
3.配置环境变量

    添加一个名叫 MYSQL_HOME 的变量。
    修改Path变量，在末尾添加 %MYSQL_HOME%\bin 

4.安装mysql服务

    以管理员身份运行cmd，进入mysql的bin目录。
    初始化数据库文件

    mysqld  --initialize  

 初始化成功后，会在datadir目录下生成一些文件，其中，xxx.err文件里说明了root账户的临时密码。那行大概长这样：
 

    2016-07-24T05:19:20.152135Z 1 [Note] A temporary password is generated for root@localhost: bL2uuwuf0H(X  


即密码是：bL2uuwuf0H(X

    注册mysql服务
    mysqld -install MySQL  

	
    启动mysql服务
    net start MySQL  


    修改root密码
	输入以下命令，回车，然后输入上面的默认密码：
    mysql -u root -p  


	进入MySQL命令行模式后，输入如下命令，命令中的 new_password 为root账号的新密码，请修改它。
    ALTER USER 'root'@'localhost' IDENTIFIED BY 'new_password';  
```


```java	
	
注意：
一、如果会弹出“无法启动此程序，因为计算机中丢失 msvcp120.dll...”等类似的提示  请安装 vcredist_x86.exe

二、配置   
	character_set_server=utf8
	lower_case_table_names=2
是为了区分大小写字母

三、配置
	sql_mode='NO_ENGINE_SUBSTITUTION'
是为了解决这个错误   1055 - Expression #1 of ORDER BY clause is not in GROUP BY clause and contains nonaggregated  on columns in GROUP BY clause; this is incompatible with sql_mode=only_full_group_by

四、解决密码不能登陆问题 ERROR 1045 (28000): Unknown error 1045
https://jingyan.baidu.com/article/7c6fb42865213f80642c90c3.html

	刚刚接触MySQL时可能会出现这样的问题：
	1
	当cmd中输入mysql -u root -p后会出现以下问题：
	 ERROR 1045 (28000): Access denied for user 'root'@'localhost' (using password）

	可以这样做，先找到mysql bin目录下的my.ini文件打开编辑，并在[mysql]下面加上skip-grant-tables，用于跳过密码问题，但是这并不能彻底解决。
	2
	重启mysql服务

	net stop mysql

	net start mysql

	MySQL登录问题 ERROR 1045 (28000)
	3
	进入数据库，重置密码。

	mysql -u root -p         Enter

	不用管password          Enter

	mysql> use mysql;      Enter

	mysql> update user set password=password("新密码") where user="root";   （密码自己设）

	mysql> update mysql.user set authentication_string=password('12344') where user='root' ;

	mysql> flush privileges;      刷新数据库

	mysql> quit;

	4
	修改my.ini ,注释掉skip-grant-tables(以防下次出问题)

	保存退出

	5
	重启MySQL服务（步骤二）。

	END
	注意事项
	mysql语句后一定要加分号，否则就会没有反应


	新安装的MySQL5.7，登录时提示密码错误，安装的时候并没有更改密码，后来通过免密码登录的方式更改密码，输入update mysql.user  set password=password('root') where user='root'时提示ERROR 1054 (42S22): Unknown column 'password' in 'field list'，原来是mysql数据库下已经没有password这个字段了，password字段改成了authentication_string 。
	所以只要将更改语句变为下面的就可以了
	update mysql.user set authentication_string=password('root') where user='root' ;


	6  报错 ERROR 1820 (HY000): Unknown error 1820
	   SET PASSWORD = PASSWORD('12344');

	   
五、win10 不能自启
https://www.cnblogs.com/jyjia/archive/2019/03/07/10490013.html
解决办法： my.ini文件保存为ANSI格式文件 ！！！

```

## 2、基础命令

```sql

-- dos命令进入数据
1.进入安装mysql的目录
cd C:\Program Files\MySQL\MySQL Server 5.7\bin
2.输入用户名、密码
mysql -uroot -proot


-- 显示数据库
show databases;

-- 使用这个数据库:与oracle的用户类似
use mydata;

-- 显示表
show tables;
show create table table_name;

-- 创建数据库
create database mydata;

-- 描述表
desc dept;


-- 插入数据
mysql> insert into dept values(21,'b','bb');

-- 执行sql脚本语句 
\. c:\\mysql_script\\mydata.sql


-- 查询 

select * from user where concat(',',name,',') like '%,admin,%';
select * from logs_2016_11 union select * from logs_2016_12 \G;

mysql> select table_name from information_schema.tables where table_schema='survey1' and table_name
like 'logs_%' and table_name<='logs_2016_12' order by table_name limit 0,3;

-- 创建表
create table if not exists t like logs;

create table dept
(
deptno int primary key,
dname varchar(14),
loc varchar(13)
);

create table emp
(
empno int primary key,
ename varchar(10),
job varchar(10),
mrg int,
hiredate datetime,
sal double,
comm double,
deptno int,
foreign key(deptno) references dept(deptno)
);

添加外键
alter table tb_OrderOut add constraint tb_OrderOut_goodsid_fk Foreign Key (goodsid)
References tb_Product (goodsid);
alter table t2 add constraint tb_foreignkey foreign  key(id) references test(id);

-- 删除表
drop table emp;
或者 DROP TABLE IF EXISTS `A`;


-- 创建数据库  create database dt1;
-- 删除数据库  drop database dt1;

-- 上下键，显示上次的命令

数据库的区别：数据类型，分页程序
-- mysql的分页程序： 倒叙从第三行后面的两个
 select * from dept order by deptno desc limit 3,2;



--  mysql的注释
mysql 服务器支持 # 到该行结束、-- 到该行结束 以及 /* 行中间或多个行 */ 的注释方格： 
mysql> SELECT 1+1;     # 这个注释直到该行结束
mysql> SELECT 1+1;     -- 这个注释直到该行结束
mysql> SELECT 1 /* 这是一个在行中间的注释 */ + 1;
mysql> SELECT 1+
/*
这是一个
多行注释的形式
*/
  注意 -- (双长划) 注释风格要求在两个长划后至少有一个空格！ 
  尽管服务器理解刚才描述的注释句法，但 MySQL 客户端的语法分析在 /* ... */ 注释方式上还有所限止： 
  单引号和双引号被用来标志一个被引用字符串的开始，即使是在一个注释中。如果注释中的引号没有另一个引号与之配对，那和语法分析程序就不会认为注释结束。如果你以交互式运行 mysql，你会产生困惑，因为提示符从 mysql> 变为 '> 或 ">。


-- mysql的自动递增auto_increment（类似oracle的序列 sequence）
create table article(
id int primary key auto_increment,
title varchar(255)
);


修改表名：  alter table t_book rename to bbb; 
添加列：    alter table 表名 add column 列名 varchar(30); 
删除列：    alter table 表名 drop column 列名; 
修改列名：  alter table bbb change nnnnn hh int; 
修改列属性：alter table t_book modify name varchar(22); 


删除一个已经确定存在的数据库
mysql> drop database drop_database;
Query OK, 0 rows affected (0.00 sec)

删除一个不确定存在的数据库
mysql> drop database drop_database;
ERROR 1008 (HY000): Can't drop database 'drop_database'; database doesn't exist
//发生错误，不能删除'drop_database'数据库，该数据库不存在。

mysql> drop database if exists drop_database;
Query OK, 0 rows affected, 1 warning (0.00 sec)//产生一个警告说明此数据库不存在
mysql> create database drop_database;
Query OK, 1 row affected (0.00 sec)
mysql> drop database if exists drop_database;//if exists 判断数据库是否存在，不存在也不产生错误
Query OK, 0 rows affected (0.00 sec) 


内连接
mysql> select orders.*,user.username,user.sex,user.birthday from orders,user where orders.user_id=user.id;

外连接
mysql> select orders.*,user.username,user.sex,user.birthday from orders join user on(orders.user_id=user.id);
左外连接
mysql> select orders.*,user.username,user.sex,user.birthday from orders left join user on(orders.user_id=user.id);
右外连接
mysql> select orders.*,user.username,user.sex,user.birthday from orders right join user on(orders.use
r_id=user.id);



1.显示MYSQL的版本
mysql> select version(); 
+-----------------------+ 
| version() | 
+-----------------------+ 
| 6.0.4-alpha-community | 
+-----------------------+ 
1 row in set (0.02 sec) 

2. 显示当前时间
mysql> select now(); 
+---------------------+ 
| now() | 
+---------------------+ 
| 2009-09-15 22:35:32 | 
+---------------------+ 
1 row in set (0.04 sec) 

3. 显示年月日
SELECT DAYOFMONTH(CURRENT_DATE); 
+--------------------------+ 
| DAYOFMONTH(CURRENT_DATE) | 
+--------------------------+ 
| 15 | 
+--------------------------+ 
1 row in set (0.01 sec) 

SELECT MONTH(CURRENT_DATE); 
+---------------------+ 
| MONTH(CURRENT_DATE) | 
+---------------------+ 
| 9 | 
+---------------------+ 
1 row in set (0.00 sec) 

SELECT YEAR(CURRENT_DATE); 
+--------------------+ 
| YEAR(CURRENT_DATE) | 
+--------------------+ 
| 2009 | 
+--------------------+ 
1 row in set (0.00 sec) 

4. 显示字符串
mysql> SELECT "welecome to my blog!"; 
+----------------------+ 
| welecome to my blog! | 
+----------------------+ 
| welecome to my blog! | 
+----------------------+ 
1 row in set (0.00 sec) 

5. 当计算器用
select ((4 * 4) / 10 ) + 25; 
+----------------------+ 
| ((4 * 4) / 10 ) + 25 | 
+----------------------+ 
| 26.60 | 
+----------------------+ 
1 row in set (0.00 sec) 




备份数据库
命令在DOS的[url=file://\\mysql\\bin]\\mysql\\bin[/url]目录下执行

1.导出整个数据库
导出文件默认是存在mysql\bin目录下
mysqldump -u 用户名 -p 数据库名 > 导出的文件名
mysqldump -u user_name -p123456 database_name > outfile_name.sql

2.导出一个表
mysqldump -u 用户名 -p 数据库名 表名> 导出的文件名
mysqldump -u user_name -p database_name table_name > outfile_name.sql

3.导出一个数据库结构
mysqldump -u user_name -p -d –add-drop-table database_name > outfile_name.sql
-d 没有数据 –add-drop-table 在每个create语句之前增加一个drop table

4.带语言参数导出
mysqldump -uroot -p –default-character-set=latin1 –set-charset=gbk –skip-opt database_name > outfile_name.sql

例如，将aaa库备份到文件back_aaa中：
　　[root@test1 root]# cd　/home/data/mysql
　　[root@test1 mysql]# mysqldump -u root -p --opt aaa > back_aaa

```

## 3、高级语法

### 3.1、linux命令

```sql

指令 ps -ef|grep mysql 得出结果
    root     17659     1  0  2011 ?        00:00:00 /bin/sh /usr/bin/mysqld_safe --datadir=/var/lib/mysql --socket=/var/lib/mysql/mysql.sock --log-error=/var/log/mysqld.log --pid-file=/var/run/mysqld/mysqld.pid   
    mysql    17719 17659  0  2011 ?        03:14:57 /usr/libexec/mysqld --basedir=/usr --datadir=/var/lib/mysql --user=mysql --pid-file=/var/run/mysqld/mysqld.pid --skip-external-locking --socket=/var/lib/mysql/mysql.sock  

usr/bin/mysql 是指：mysql的运行路径
var/lib/mysql 是指：mysql数据库文件的存放路径
usr/lib/mysql 是指：mysql的安装路径 


导出数据库 /opt/tech/mysql/bin/mysqldump -uroot -p1234 webpro > /opt/tech/20170814.sql
 
导入数据  mysql -uroot -pSudy.web123 UCPPLUS < /opt/sql/ucpplus_v4_0_5.sql

mysql导入时出现"ERROR at line : Unknown command '\''."的解决办法
		 mysql -uroot -p12344  --default-character-set=utf8 IMP_V12_1 < E:\ids-1.1.2.sql

导出查询语句 /opt/sudytech/mysql/bin/mysql -uroot -p12344 -e "use IDSPLUS;select id,loginName from T_USER where id=1\G;" >> /opt/test.txt

导出表   /opt/tech/mysql/bin/mysqldump -uroot -p1234 webpro t_user > /opt/tech/t_user.sql

创建数据库
CREATE DATABASE UCPPLUS DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use UCPPLUS;
 
登录mysql  /opt/tech/mysql/bin/mysql -uroot -p1234 
           /opt/tech/mysql/bin/mysql -uroot -padmin

创建、使用数据库   create database test;  use test;
导入sql数据 	source /root/20151010.sql


复制表数据到新表
CREATE TABLE T_MENU1 LIKE T_MENU;
INSERT INTO T_MENU1 SELECT * FROM T_MENU;

/opt/tech/apache-tomcat-6.0.45/webapps/add20170814.sql

修改max_allowed_packet  vi /etc/my.cnf

mysql -u root -p12344



navicat连接mysql失败，授权：
GRANT ALL PRIVILEGES ON `db1`.* TO 'user1'@'192.171.1.18' identified by 'pwd1' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON `IDSPLUS`.* TO 'sudy'@'192.171.1.18' identified by 'shhg12344' WITH GRANT OPTION;

grant all privileges on *.* to root@'%' identified by 'Sudy.web123' with grant option;

-- flush privileges;


查询权限
select * from mysql.user
select Host,User,password_last_changed from mysql.user

取消权限
revoke  all on *.* from 'root'@'192.168.0.197' ;

删除用户权限
Delete from mysql.user where user = "user_name" and host = "host_name" ; 


 
linux 的mysql配置文件  /etc/my.cnf

查看编码
show variables like 'character%'; 

设置编码
set character_set_server='utf8';  

查看sql_mode
SELECT @@GLOBAL.sql_mode;

识别大小写
lower_case_table_names = 2

启动mysql
  /opt/sudytech/mysql/support-files/mysql.server start
  
  /opt/sudytech/mysql/bin/mysqld_safe --user=mysql --basedir=/opt/sudytech/mysql --datadir=/opt/sudytech/mysql/data & 
  
  cd /opt/sudytech/mysql/
  ./bin/mysqld_safe &
  
  
  chmod -R 775 mysql
  
  
  cd /data/sudytech/mysql/&&./bin/mysqld_safe &
  cd /opt/sudytech/mysql/ && bin/mysqld_safe --user=root &
  
```

### 3.2、毫秒数字转换为日期


```

sql将毫秒数字转换为日期
SELECT FROM_UNIXTIME(operation_time/1000,"%Y-%m-%d %H:%i:%s") operationDate FROM ins_purchase


```

### 3.3、group_concat函数

```sql
MySQL中group_concat函数

https://www.iteye.com/blog/hchmsguo-555543

完整的语法如下：

group_concat([DISTINCT] 要连接的字段 [Order BY ASC/DESC 排序字段] [Separator '分隔符'])

 
基本查询

select * from aa;  

+------+------+
| id| name |
+------+------+
|1 | 10|
|1 | 20|
|1 | 20|
|2 | 20|
|3 | 200 |
|3 | 500 |
+------+------+
6 rows in set (0.00 sec)


以id分组，把name字段的值打印在一行，逗号分隔(默认)

select id,group_concat(name) from aa group by id;  

+------+--------------------+
| id| group_concat(name) |
+------+--------------------+
|1 | 10,20,20|
|2 | 20 |
|3 | 200,500|
+------+--------------------+
3 rows in set (0.00 sec)

 

以id分组，把name字段的值打印在一行，分号分隔


select id,group_concat(name separator ';') from aa group by id;  

+------+----------------------------------+
| id| group_concat(name separator ';') |
+------+----------------------------------+
|1 | 10;20;20 |
|2 | 20|
|3 | 200;500 |
+------+----------------------------------+
3 rows in set (0.00 sec)

 

以id分组，把去冗余的name字段的值打印在一行，

逗号分隔

select id,group_concat(distinct name) from aa group by id;  

+------+-----------------------------+
| id| group_concat(distinct name) |
+------+-----------------------------+
|1 | 10,20|
|2 | 20 |
|3 | 200,500 |
+------+-----------------------------+
3 rows in set (0.00 sec)

 

以id分组，把name字段的值打印在一行，逗号分隔，以name排倒序

select id,group_concat(name order by name desc) from aa group by id;  

+------+---------------------------------------+
| id| group_concat(name order by name desc) |
+------+---------------------------------------+
|1 | 20,20,10 |
|2 | 20|
|3 | 500,200|
+------+---------------------------------------+
3 rows in set (0.00 sec)


```

### 3.3、left join左右连接

```

sql之left join、right join、inner join的区别
https://www.cnblogs.com/pcjim/articles/799302.html

left join(左联接) 返回包括左表中的所有记录和右表中联结字段相等的记录 
right join(右联接) 返回包括右表中的所有记录和左表中联结字段相等的记录
inner join(等值连接) 只返回两个表中联结字段相等的行

举例如下： 
--------------------------------------------
表A记录如下：
aID　　　　　aNums
1　　　　　a20050111
2　　　　　a20050112
3　　　　　a20050113
4　　　　　a20050114
5　　　　　a20050115

表B记录如下:
bID　　　　　bName
1　　　　　2006032401
2　　　　　2006032402
3　　　　　2006032403
4　　　　　2006032404
8　　　　　2006032408

--------------------------------------------
1.left join
sql语句如下: 
select * from A
left join B 
on A.aID = B.bID

结果如下:
aID　　　　　aNum　　　　　bID　　　　　bName
1　　　　　a20050111　　　　1　　　　　2006032401
2　　　　　a20050112　　　　2　　　　　2006032402
3　　　　　a20050113　　　　3　　　　　2006032403
4　　　　　a20050114　　　　4　　　　　2006032404
5　　　　　a20050115　　　　NULL　　　　　NULL

（所影响的行数为 5 行）
结果说明:
left join是以A表的记录为基础的,A可以看成左表,B可以看成右表,left join是以左表为准的.
换句话说,左表(A)的记录将会全部表示出来,而右表(B)只会显示符合搜索条件的记录(例子中为: A.aID = B.bID).
B表记录不足的地方均为NULL.
--------------------------------------------
2.right join
sql语句如下: 
select * from A
right join B 
on A.aID = B.bID

结果如下:
aID　　　　　aNum　　　　　bID　　　　　bName
1　　　　　a20050111　　　　1　　　　　2006032401
2　　　　　a20050112　　　　2　　　　　2006032402
3　　　　　a20050113　　　　3　　　　　2006032403
4　　　　　a20050114　　　　4　　　　　2006032404
NULL　　　　　NULL　　　　　8　　　　　2006032408

（所影响的行数为 5 行）
结果说明:
仔细观察一下,就会发现,和left join的结果刚好相反,这次是以右表(B)为基础的,A表不足的地方用NULL填充.
--------------------------------------------
3.inner join
sql语句如下: 
select * from A
innerjoin B 
on A.aID = B.bID

结果如下:
aID　　　　　aNum　　　　　bID　　　　　bName
1　　　　　a20050111　　　　1　　　　　2006032401
2　　　　　a20050112　　　　2　　　　　2006032402
3　　　　　a20050113　　　　3　　　　　2006032403
4　　　　　a20050114　　　　4　　　　　2006032404

结果说明:
很明显,这里只显示出了 A.aID = B.bID的记录.这说明inner join并不以谁为基础,它只显示符合条件的记录.
--------------------------------------------
注: 
LEFT JOIN操作用于在任何的 FROM 子句中，组合来源表的记录。使用 LEFT JOIN 运算来创建一个左边外部联接。左边外部联接将包含了从第一个（左边）开始的两个表中的全部记录，即使在第二个（右边）表中并没有相符值的记录。

语法：FROM table1 LEFT JOIN table2 ON table1.field1 compopr table2.field2

说明：table1, table2参数用于指定要将记录组合的表的名称。
field1, field2参数指定被联接的字段的名称。且这些字段必须有相同的数据类型及包含相同类型的数据，但它们不需要有相同的名称。
compopr参数指定关系比较运算符："="， "<"， ">"， "<="， ">=" 或 "<>"。
如果在INNER JOIN操作中要联接包含Memo 数据类型或 OLE Object 数据类型数据的字段，将会发生错误. 

```


### 3.4、Case when 的使用方法

```java

Case when 的使用方法
https://www.cnblogs.com/yazdao/archive/2009/12/09/1620482.html

Case具有两种格式。简单Case函数和Case搜索函数。
--简单Case函数
CASE sex
WHEN '1' THEN '男'
WHEN '2' THEN '女'
ELSE '其他' END
--Case搜索函数
CASE WHEN sex = '1' THEN '男'
WHEN sex = '2' THEN '女'
ELSE '其他' END

这两种方式，可以实现相同的功能。简单Case函数的写法相对比较简洁，但是和Case搜索函数相比，功能方面会有些限制，比如写判断式。 
还有一个需要注意的问题，Case函数只返回第一个符合条件的值，剩下的Case部分将会被自动忽略。 
--比如说，下面这段SQL，你永远无法得到“第二类”这个结果
CASE WHEN col_1 IN ( 'a', 'b') THEN '第一类'
WHEN col_1 IN ('a')       THEN '第二类'
ELSE'其他' END

下面我们来看一下，使用Case函数都能做些什么事情。 

一，已知数据按照另外一种方式进行分组，分析。 

有如下数据:(为了看得更清楚，我并没有使用国家代码，而是直接用国家名作为Primary Key) 
国家（country）	人口（population）
中国	600
美国	100
加拿大	100
英国	200
法国	300
日本	250
德国	200
墨西哥	50
印度	250

根据这个国家人口数据，统计亚洲和北美洲的人口数量。应该得到下面这个结果。 
洲	人口
亚洲	1100
北美洲	250
其他	700

想要解决这个问题，你会怎么做？生成一个带有洲Code的View，是一个解决方法，但是这样很难动态的改变统计的方式。 
如果使用Case函数，SQL代码如下:
SELECT  SUM(population),
CASE country
WHEN '中国'     THEN '亚洲'
WHEN '印度'     THEN '亚洲'
WHEN '日本'     THEN '亚洲'
WHEN '美国'     THEN '北美洲'
WHEN '加拿大'  THEN '北美洲'
WHEN '墨西哥'  THEN '北美洲'
ELSE '其他' END
FROM    Table_A
GROUP BY CASE country
WHEN '中国'     THEN '亚洲'
WHEN '印度'     THEN '亚洲'
WHEN '日本'     THEN '亚洲'
WHEN '美国'     THEN '北美洲'
WHEN '加拿大'  THEN '北美洲'
WHEN '墨西哥'  THEN '北美洲'
ELSE '其他' END;

同样的，我们也可以用这个方法来判断工资的等级，并统计每一等级的人数。SQL代码如下； 
SELECT
CASE WHEN salary <= 500 THEN '1'
WHEN salary > 500 AND salary <= 600  THEN '2'
WHEN salary > 600 AND salary <= 800  THEN '3'
WHEN salary > 800 AND salary <= 1000 THEN '4'
ELSE NULL END salary_class,
COUNT(*)
FROM    Table_A
GROUP BY
CASE WHEN salary <= 500 THEN '1'
WHEN salary > 500 AND salary <= 600  THEN '2'
WHEN salary > 600 AND salary <= 800  THEN '3'
WHEN salary > 800 AND salary <= 1000 THEN '4'
ELSE NULL END;

二，用一个SQL语句完成不同条件的分组。 

有如下数据 
国家（country）	性别（sex）	人口（population）
中国	1	340
中国	2	260
美国	1	45
美国	2	55
加拿大	1	51
加拿大	2	49
英国	1	40
英国	2	60

按照国家和性别进行分组，得出结果如下 
国家	男	女
中国	340	260
美国	45	55
加拿大	51	49
英国	40	60

普通情况下，用UNION也可以实现用一条语句进行查询。但是那样增加消耗(两个Select部分)，而且SQL语句会比较长。 
下面是一个是用Case函数来完成这个功能的例子 
SELECT country,
SUM( CASE WHEN sex = '1' THEN
population ELSE 0 END),  --男性人口
SUM( CASE WHEN sex = '2' THEN
population ELSE 0 END)   --女性人口
FROM  Table_A
GROUP BY country;

这样我们使用Select，完成对二维表的输出形式，充分显示了Case函数的强大。 

三，在Check中使用Case函数。 

在Check中使用Case函数在很多情况下都是非常不错的解决方法。可能有很多人根本就不用Check，那么我建议你在看过下面的例子之后也尝试一下在SQL中使用Check。 
下面我们来举个例子 
公司A，这个公司有个规定，女职员的工资必须高于1000块。如果用Check和Case来表现的话，如下所示
CONSTRAINT check_salary CHECK
( CASE WHEN sex = '2'
THEN CASE WHEN salary > 1000
THEN 1 ELSE 0 END
ELSE 1 END = 1 )

如果单纯使用Check，如下所示 
CONSTRAINT check_salary CHECK
( sex = '2' AND salary > 1000 )

女职员的条件倒是符合了，男职员就无法输入了。

```

## 4、其他

### 4.1、复制表结构、表数据

```java

Mysql复制表结构、表数据的方法

1、复制表结构及数据到新表（不包含主键、索引、分区等）

CREATE TABLE 新表 SELECT * FROM 旧表

或 CREATE TABLE 新表 AS SELECT * FROM 旧表

这种方法将旧表基本结构和数据复制到新表。

不过这种方法的一个最不好的地方就是新表中没有了旧表的主键、索引、Extra（auto_increment，字符集编码及排序）、注释、分区等属性 以及触发器、外键等。

2、只复制表结构到新表

CREATE TABLE 新表 SELECT * FROM 旧表 WHERE 1=2 （只是第一种方式去除掉数据）

CREATE TABLE 新表 LIKE 旧表

这种方式的复制可以复制旧表的主键、索引、Extra（auto_increment，字符集编码及排序）、注释、分区等属性。但是不包含触发器、外键等

3、复制旧表的数据到新表

INSERT INTO 新表 SELECT * FROM 旧表

INSERT INTO 新表(字段1,字段2,.......) SELECT 字段1,字段2,...... FROM 旧表

上面两条语句的前提是新表已经存在

4、复制表结构及数据到新表（包含主键、索引、分区等）

结合上述第2、3点，即：

先 CREATE TABLE 新表 LIKE 旧表

然后 INSERT INTO 新表 SELECT * FROM 旧表

5、可以将表1结构复制到表2（mysql不支持）

SELECT * INTO 表2 FROM 表1 WHERE 1=2

6、可以将表1内容全部复制到表2（mysql不支持）

SELECT * INTO 表2 FROM 表1

7、 show create table 旧表;

这样会将旧表的创建命令列出。我们只需要将该命令拷贝出来，更改table的名字，就可以建立一个完全一样的表

```


```
sql中文排序 
SELECT id,name FROM `T_USER` ORDER BY convert(name using gbk)  ASC limit 10,100;
```


```js
	
https://www.cnblogs.com/kyosusan/p/5198934.html	
修改root密码

一、知道原来的myql数据库的root密码；

①： 在终端命令行输入 mysqladmin -u root -p password "新密码" 回车 ，Enter password: 【输入原来的旧密码】
②： 登录mysql系统修改， mysql -uroot -p 回车 Enter password: 【输入原来的密码】

mysql>use mysql;

mysql> update user set password=password("新密码") where user='root';        【密码注意大小写】

mysql> flush privileges;

mysql> exit;   
```


### 4.2、文档

- [数据库隔离级别]( https://blog.xushufa.cn )

- [mysql开启log-bin日志]( https://reco-blog.xushufa.cn )


