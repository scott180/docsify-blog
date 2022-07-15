# Mysql开启log-bin日志  

<br />

> 一 、logbin日志 记录新增、更新、删除的sql [参考1]( https://www.cnblogs.com/kerrycode/p/6610874.html ) [参考2]( https://www.cnblogs.com/handle/p/9761702.html )

> 二、general_log 记录增删改查所有日志 [general log]( http://www.zsythink.net/archives/1246 )


## 一 、logbin日志

```sql
show binary logs;

show master status;

show binlog events in 'mysql-bin.000090' from 242985028 limit 0,1000

```

---------------------------------------------------------

- 1、检查是否开启

```sql
检查是否开启
show global variables like '%log_bin%';
log_bin 为 on 则已开启，为 off则未开启。

查询所有日志文件
show binary logs;

查询最近日志文件
show master status;
```

<br>

- 2、开启log-bin日志记录

```js
找到my.cnf 中 [mysqld]  添加如下
-- # binlog 配置
log-bin = D:\\ProgramFiles\\mysql-5.7.20-winx64\\logs\\mysql-bin.log
expire-logs-days = 14
max-binlog-size = 500M
server-id = 1
```

<br>

- 3、查询 

```sql
SHOW BINLOG EVENTS [IN 'log_name'] [FROM pos] [LIMIT [offset,] row_count]

mysql> show binlog events in 'mysql-bin.000001' from 4 limit 0,5;
+------------------+-----+----------------+-----------+-------------+---------------------------------------+
| Log_name         | Pos | Event_type     | Server_id | End_log_pos | Info                                  |
+------------------+-----+----------------+-----------+-------------+---------------------------------------+
| mysql-bin.000001 |   4 | Format_desc    |         1 |         123 | Server ver: 5.7.20-log, Binlog ver: 4 |
| mysql-bin.000001 | 123 | Previous_gtids |         1 |         154 |                                       |
| mysql-bin.000001 | 154 | Anonymous_Gtid |         1 |         219 | SET @@SESSION.GTID_NEXT= 'ANONYMOUS'  |
| mysql-bin.000001 | 219 | Query          |         1 |         292 | BEGIN                                 |
| mysql-bin.000001 | 292 | Table_map      |         1 |         375 | table_id: 125 (test.ins_car_team)    |
+------------------+-----+----------------+-----------+-------------+---------------------------------------+
5 rows in set (0.00 sec)

```

![m]( https://gitcode.net/xu180/imgs/-/raw/master/other/mysql-bin.png )

<br>

4、导出文件

```sql
mysqlbinlog /var/lib/mysql/mysql-bin.000001 > test.sql;

```


```
表更新时间（mysql 5.7.2以下版本无效 ）
select TABLE_NAME,UPDATE_TIME from information_schema.TABLES where TABLE_SCHEMA='databaseTest' order by UPDATE_TIME desc limit 10;

```


## 二、general_log日志

```sh
是否开启 general_log日志
show variables where variable_name like "%general_log%" or variable_name="log_output";

开启
set global general_log = on;
set global log_output = "table";


查看sql
select * from mysql.general_log where argument like '%select%'   ORDER BY event_time DESC limit 100;

```

![g]( https://gitcode.net/xu180/imgs/-/raw/master/other/mysql.general_log.jpg )

---


