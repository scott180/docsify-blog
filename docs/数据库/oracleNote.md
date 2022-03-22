
# oracle笔记

## 常用命令

```

登录	
	select * from dba_directories;
		SYS	DATA_PUMP_DIR	/opt/oracle/admin/orcl/dpdump/
	sqlplus saiwen_imp/saiwen_imp	
		

将 idsweb-2.0.oracle.dmp 文件上传到 /opt/saiwentech/imp_oracle/data目录，进入容器（不需要登录oralc）执行导入导出命令。

导入idsweb-2.0.oracle.dmp
	cp /opt/dbdata/local/idsweb-2.0.oracle.dmp /opt/oracle/admin/orcl/dpdump/
	impdp saiwen_imp/saiwen_imp DIRECTORY=DATA_PUMP_DIR DUMPFILE=idsweb-2.0.oracle.dmp REMAP_SCHEMA=saiwen_imp:saiwen_imp
//  impdp account/password DIRECTORY=DATA_PUMP_DIR DUMPFILE=idsweb-2.0.oracle.dmp REMAP_SCHEMA=form:to

导出idsweb-2.0.oracle.dmp
	expdp saiwen_imp/saiwen_imp  schemas=saiwen_imp dumpfile=idsweb-2.0.oracle.dmp directory=DATA_PUMP_DIR;
	cp /opt/oracle/admin/orcl/dpdump/idsweb-2.0.oracle.dmp /opt/dbdata/local/


导入 Iframework_V4.2_scott_exp.dmp
	cp /opt/dbdata/local/Iframework_V4.2_scott_exp.dmp /opt/oracle/admin/orcl/dpdump/
	imp saiwen_imp/saiwen_imp BUFFER=64000 FILE=/opt/oracle/admin/orcl/dpdump/Iframework_V4.2_scott_exp.dmp FROMUSER=SCOTT TOUSER=saiwen_imp


说明： DATA_PUMP_DIR 为oralce创建的目录，可用如下命令查询：
	 select * from dba_directories;
	 
登录
	sqlplus /nolog
	conn / as sysdba
	sqlplus saiwen_imp/password
	
```


```
mysql --->  oracle 全部要大写

int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT  
"Id" int(11) NOT NULL AUTO_INCREMENT           ---->    "ID" NUMBER(11) NOT NULL      PRIMARY KEY ("ID")  创建序列及触发器 自增ID

varchar ---->   NVARCHAR2

int  ---->  NUMBER

text  ---->   NVARCHAR2(2000)   CLOB 

datetime ----> DATE

时间比较
	to_date('2018-10-01 00:00:00','yyyy-mm-dd hh24:mi:ss')
	
```


```

添加字段
	ALTER TABLE T_IDS_APPAUTH ADD (DYNAMICPASSLOGINENABLE NUMBER(1) DEFAULT '0' );
	ALTER TABLE T_IDS_APPSYNCDETAIL ADD OPERCOUNT NUMBER(11) DEFAULT 1;
	alter table T_USER add WeChat NVARCHAR2(100);
	insert into T_IDSCONFIG(ID,IDSKEY,VALUE) values (20,'Second login','{"displayName":"二次登录","enabled":0}');

设置主键
	ALTER TABLE test_tab ADD CONSTRAINT pk_test_tab PRIMARY key(id);

修改字段名：
　　alter table Student rename name to StuName;

修改数据类型：
　　alter table Student modify (id varchar2(64));
	ALTER TABLE T_ORG MODIFY FIRSTLETTERS VARCHAR2(255) DEFAULT NULL;
	
	在oracle中，如果已经存在的数据的某些列，假如要更换字段类型的话，有的时候会出现

	错误：ORA-01439: column to be modified must be empty to change datatype 

    解决方法：把列数据复制出来，列置空后修改再恢复数据

    例如：

    alter table tablename add tempcolumn varchar2(100);--添加临时字段

    tempcolumn update tablename set tempcolumn=colname;--将原字段数据复制到临时字段中

    update tablename set colname=null;--将原字段数据清空

    alter table tablename modify colname xxx ;--修改原字段类型为xxx

    update tablename set colname= tempcolumn;--将临时字段数据复制到修改后的原字段

    alter table tablename drop column tempcolumn;--删除临时字段
	
oracle创建外键约束有两种方法：

1、创建表时直接创建外键约束
create table books(
    bookid number(10) not null primary key,
    bookName varchar2(20) not null,
    price number(10,2),
    categoryId number(10) not null references Category(id)  --外键约束
);


2、先创建表，表创建成功后，单独添加外键约束
create table books(
    bookid number(10) not null primary key,
    bookName varchar2(20) not null,
    price number(10,2),
    categoryId number(10) not null
);
ALTER TABLE  books ADD CONSTRAINT FK_Book_categoryid FOREIGN KEY(categoryId ) REFERENCES Category(id);


三种外键约束的建立语法如下：

例如有两张表 父表T_INVOICE主键ID。子表T_INVOICE_DETAIL外键字段INVOICE_ID

1、普通外键约束：

ALTER TABLE T_INVOICE_DETAIL ADD CONSTRAINT FK_INVOICE_ID FOREIGN KEY(INVOICE_ID ) REFERENCES T_INVOICE(ID);
2、级联外键约束：
ALTER TABLE T_INVOICE_DETAIL ADD CONSTRAINT FK_INVOICE_ID FOREIGN KEY(INVOICE_ID ) REFERENCES T_INVOICE(ID) ON DELETE CASCADE;
3、置空外键约束：
ALTER TABLE T_INVOICE_DETAIL ADD CONSTRAINT FK_INVOICE_ID FOREIGN KEY(INVOICE_ID ) REFERENCES T_INVOICE(ID) ON DELETE SET NULL;

alter table unique_test add constraint email_unique unique(email);

```


----------------------------------------------------------------------------------------------------------------

## 序列与触发器

```

#创建序列
create sequence t_user_id_seq start with 1 increment by 1;

#查看序列
select * from user_sequences;
select * from user_sequences WHERE SEQUENCE_NAME='T_IDS_APPAUTH_SEQ';

#删除序列
DROP SEQUENCE T_IDS_APPAUTH_SEQ;


#创建触发器
create or replace trigger t_user_trigger 
before insert on t_user
for each row
when(new.id is null)
begin select t_user_id_seq.nextval into:NEW.ID from dual; end;

#查看触发器
select * from user_triggers;
select * from user_triggers where TRIGGER_NAME='T_IDS_APPAUTH_TRIG';

#删除触发器
drop trigger T_IDS_APPAUTH_TRIG;


创建序列及触发器 自增ID
CREATE SEQUENCE T_TABLE_SEQ START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER T_TABLE_TRIG 
BEFORE INSERT ON T_TABLE
FOR EACH ROW
WHEN(NEW.ID IS NULL)
BEGIN SELECT T_TABLE_SEQ.NEXTVAL INTO:NEW.ID FROM DUAL; END


查看表的约束条件有三个视图：dba_constraints、all_constraints、user_constraints
其中：dba_constraints视图需要DBA权限才能查询；
all_constraints、user_constraints普通用户查询。
例：select * from user_constraints; 
select * from user_constraints where constraint_name = 'SYS_C00185187';


禁用约束
ALTER TABLE T_ORGUSER DISABLE CONSTRAINT constraint_SYS_C00185187; 
启用约束
ALTER TABLE table_name ENABLE CONSTRAINT constraint_name;  

https://www.cnblogs.com/bingo1717/p/7792134.html



分页排序
　select rownum rn ,a.* from USER_INFO a order by A.USERAGE desc;

	SELECT * FROM  
	(  
	SELECT A.*, ROWNUM RN  
	FROM (SELECT * FROM TABLE_NAME) A  
	WHERE ROWNUM <= 40  
	)  
	WHERE RN >= 21
	
oracle   dt = session.query(sql, new Integer[]{rowBeginIndex+rows,rowBeginIndex});	
mysql    dt = session.query(sql, new Integer[]{rowBeginIndex,rows});


create sequence HIBERNATE_SEQUENCE start with 1 increment by 1;




进程报错  maximum number of processes (150) exceeded ：
	sqlplus /nolog
	conn /as sysdba;

	show parameter processes;

	alter system set processes = 2000  scope = spfile;

	shutdown immediate;

	startup;


```


## 调整字段顺序

```

一、调整oracle表中字段显示顺序：用系统用户
调整oracle表中字段显示顺序	此操作要在系统用户下执行，否则未授权错误 [Err] ORA-01031: insufficient privileges
1、查询出指定用户下的指定表的object_id

select object_id from all_objects where owner='test' and object_name='表名'

2、根据object_id查询出表字段实际的顺序

select obj#,col#,name from sys.col$ where obj#=79119 ;

3、通过update更改字段的实际顺序。

update sys.col$ set col#=7 where obj#=79119 and name='字段名'

update sys.col$ set col#=4 where obj#=(select object_id from all_objects where owner='ZS12_IMP' and object_name='T_IDS_APPAUTH' ) and name='RESPONSEIMPL';


二、调整oracle表中字段显示顺序：删除原表

如果要修改字段顺序，一般情况可以使用以下步骤（注意外键）：
--（1）备份目标表数据
create table T_IDS_APPAUTH2 as select * from T_IDS_APPAUTH;
--（2）drop 目标表
drop table 目标表;
--（3）再重新按照要求的字段顺序建表;
create table 临时表 (col1,................coln);
--（4）之后用select将数据从临时表导回。

create table T_USER_EXTRAINFO_OLD as select * from T_USER_EXTRAINFO;
drop table T_USER_EXTRAINFO;
CREATE TABLE "T_USER_EXTRAINFO" (
  "USERID" NUMBER(11) REFERENCES T_USER(ID) ON DELETE CASCADE,
  "FIELD1" NVARCHAR2(2000) DEFAULT NULL,
  "FIELD2" NVARCHAR2(100) DEFAULT NULL,
  "FIELD3" NVARCHAR2(100) DEFAULT NULL,
  "FIELD4" NVARCHAR2(100) DEFAULT NULL,
  "FIELD5" NVARCHAR2(2000) DEFAULT NULL,
  "FIELD6" NVARCHAR2(100) DEFAULT NULL,
  "FIELD7" NVARCHAR2(100) DEFAULT NULL,
  "FIELD8" NVARCHAR2(100) DEFAULT NULL,
  "FIELD9" NVARCHAR2(100) DEFAULT NULL,
  "FIELD10" NVARCHAR2(100) DEFAULT NULL,
  CONSTRAINT T_USER_EXTRAINFO_UNIQUE UNIQUE (USERID)
) 
;
insert into T_USER_EXTRAINFO("USERID","FIELD1", "FIELD2", "FIELD3", "FIELD4", "FIELD5","FIELD6", "FIELD7", "FIELD8", "FIELD9", "FIELD10") select "USERID","FIELD1", "FIELD2", "FIELD3", "FIELD4", "FIELD5","FIELD6", "FIELD7", "FIELD8", "FIELD9", "FIELD10" from T_USER_EXTRAINFO_OLD;


三、调整oracle表中字段显示顺序：删除原字段

 * 由于oracle 不能调整字段顺序，也不能改变有数据的表的字段长度和类型。因此是采用如下方法来插入字段。
 * 	   1、创建备份表；  2、删除多余字段(注意外键)；   3、按顺序添加字段；  4、从备份表中复制原数据  （5、删除备份表）
 *     （如果表中没有数据，可以将表删除，然后按照需要的顺序创建新表）
 *     某些日志文件数据比较大，如果采用上述方法，升级脚本速度可能相当慢。如果不需要日志文件，
 *     可以在升级前将日志文件删除，涉及升级日志表有：认证日志（ T_IDS_LOGINLOG ）。

-- T_IDS_DATASOURCE 在 DbPassword 字段后添加 BaseDb
create table T_IDS_DATASOURCE_OLD as select * from T_IDS_DATASOURCE;
ALTER TABLE T_IDS_DATASOURCE DROP COLUMN MONITOR;
ALTER TABLE T_IDS_DATASOURCE DROP COLUMN MONITORNOTICEUSER;
ALTER TABLE T_IDS_DATASOURCE ADD "BASEDB" NUMBER(1) DEFAULT 0;
ALTER TABLE T_IDS_DATASOURCE ADD "MONITOR" NUMBER(1) DEFAULT 0;
ALTER TABLE T_IDS_DATASOURCE ADD "MONITORNOTICEUSER" NVARCHAR2(100) DEFAULT NULL;
ALTER TABLE T_IDS_DATASOURCE ADD "REMARK" NVARCHAR2(2000) DEFAULT NULL;

merge into T_IDS_DATASOURCE A  using T_IDS_DATASOURCE_OLD B
on(A.id=B.id)  
when matched then  
update set A.MONITOR = B.MONITOR,A.MONITORNOTICEUSER = B.MONITORNOTICEUSER;


ORA-02270：no matching unique or primary key for this column-list(此列列表的唯一或主键不匹配)
错误说明：外键的定义必须是另外一张表的主键，否则就会报这个错

```

----------------------------------------------------------------------------------------------------------------

