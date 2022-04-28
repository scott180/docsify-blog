# oracle笔记

## 1、学习教程

### 1.1、基础

```sql
第一课：客户端 
        1. Sql Plus(客户端），命令行直接输入：sqlplus，然后按提示输入用户名，密码。  登陆   sqlplus scott/tiger;
        2. 从开始程序运行:sqlplus，是图形版的sqlplus.
        3. http://localhost:5560/isqlplus
        
        Toad：管理， PlSql Developer:


第二课：更改用户
        1. sqlplus sys/bjsxt as sysdba
        2. alter user scott account unlock;(解锁)

第三课：table structure      
        1. 描述某一张表：desc 表名
        2. select * from 表名
		
第四课：select  查看
       1.计算数据可以用空表：比如：.select 2*3 from dual

       2.select ename,sal*12 annual_sal from emp;与select ename,sal*12 "annual sal" from emp;区别，加双引号保持原大小写。不加全变大写。
       
       3. select ename || "abcd" 如果连接字符串中含有单引号，用两个单引号代替一个单引号。

第五课：distinct  去重
        select deptno from emp;
        select distinct deptno from emp;

        select distinct deptno from emp;
        select distinct deptno ,job from emp
        去掉deptno,job两者组合的重复。更多的项，就是这么多项的组合的不重复组合。

```

---

```sql		
第六课：Where   判断
        select * from emp where deptno =10;
        select * from emp where deptno <>10;不等于10        
        select * from emp where ename ='bike';
        select ename,sal from emp where sal between 800 and 1500 (>=800 and <=1500)
        空值处理:
        select ename,sal,comm from emp where comm is (not) null;
        select ename,sal,comm from emp where ename ( not)in ('smith','king','abc');
        select ename from emp where ename like '_A%';_代表一个字母,%代表0个或多个字母. 如果查询%
        可用转义字符.\%. 还可以用escape '$'比如:select ename from emp where ename like '%$a%' escape '$';

第七课: order by  排序  desc-倒序
        
         select * from dept; 
         select * from dept order by dept desc;(默认:asc)
         select ename,sal,deptno from emp order by deptno asc,ename desc;

第八课: sql function1:  函数  
        select ename,sal*12 annual_sal from emp
        where ename not like '_A%' and sal>800
        order by sal desc;

        select lower(ename) from emp;

        select ename from emp 
        where lower(ename) like '_a%';等同于
        select ename from emp where ename like '_a%' or ename like '_A%';

        select substr(ename,2,3) from emp;从第二字符截,一共截三个字符.
        select chr(65) from dual 结果为:A
        select ascii('a') from dual 结果为:65
        select round(23.652,1) from dual; 结果为: 23.7
        select round(23.652,-1) from dual; 20
  
        select to_char(sal,'$99_999_999') from emp;
        select to_char(sal,'L99_999_999') from emp;人民币符号,L:代表本地符号

        这个需要掌握牢:
        select hiredate from emp;
        显示为:
        BIRTHDATE
        ----------------
        17-12月-80
        ----------------

        改为:
        select to_char(hiredate,'YYYY-MM-DD HH:MI:SS') from emp;
        
        显示:
         
        BIRTHDATE
        -------------------
        1980-12-17 12:00:00
        -------------------
        
        select to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') from dual; //也可以改为:HH12
        TO_CHAR(SYSDATE,'YY
        -------------------
        2007-02-25 14:46:14
        

        to_date函数:
        select ename,hiredate from emp where hiredate > to_date('1981-2-20 12:34:56','YYYY-MM-DD HH24:MI:SS');
        如果直接写 hiredate>'1981-2-20 12:34:56'会出现格式不匹配,因为表中的格式为: DD-MM月-YY.
  
        
        select sal from emp where sal>888.88 无错.但
        select sal from emp where sal>$1,250,00;
        会出现无效字符错误. 
        改为:
        select sal from emp where sal>to_number('$1.250.00','$9,999,99');
        
        把空值改为0
        select ename,sal*12+nvl(comm,0) from emp;  //过滤是NVL
        这样可以防止comm为空时,sal*12相加也为空的情况.


第九课: Group function 组函数
        max,min,avg ,count,sum函数
        
        select to_char(avg(sal),'99999999,99') from emp;
       
        select round(avg(sal),2) from emp;
        结果:2073.21
          
        select count(*) from emp where deptno=10;
        select count(ename) from emp where deptno=10; count某个字段,如果这个字段不为空就算一个.
        select count(distinct deptno) from emp;
        select sum(sal) from emp;

第十课: Group by语句
        
        需求:现在想求,求每个部门的平均薪水.
        select avg(sal) from emp group by deptno;
        select deptno,job,max(sal) from emp group by deptno,job;
        ---Group by语句应注意, 出现在select中的字段,如果没出现在组函数中,必须出现在Group by语句中.

       求薪水值最高的人的名字.
       select ename,max(sal) from emp;出错,因为max只有一个值,但等于max值的人可能好几个,不能匹配.
       应如下求:
       select ename from emp where sal=(select max(sal) from emp);

```

---

```sql
 
    
  第十一课: Having 对分组结果筛选
       
       Where是对单条纪录进行筛选,Having是对分组结果进行筛选.
      
       select avg(sal),deptno from emp 
       group by deptno 
       having avg(sal)>2000;
       
       查询工资大于1200雇员,按部门编号进行分组,分组后平均薪水大于1500,按工薪倒充排列.
       select avg(sal) from emp 
       where sal>1200
       group by deptno
       having avg(sal)>1500
       order by avg(sal) desc;
        
   第十二课:自查询
       
       谁挣的钱最多(谁:这个人的名字,  钱最多)
       
       select 语句中嵌套select 语句,可以在where,from后.
       
             
       问那些人工资,在平均工资之上.
       
       select ename,sal from emp where sal>(select avg(sal) from emp);


-------多表查询

       查找每个部门挣钱最多的那个人的名字.
       select ename ,deptno from emp where sal in(select max(sal) from ename group by deptno) 查询会多值.

       应该如下:
       
       select  max(sal),deptno from emp group by deptno;当成一个表.语句如下:
       select ename, sal from emp join(select  max(sal) max_sal,deptno from emp group
       by deptno) t on(emp.sal=t.max_sal and emp.deptno=t.deptno); 
       
       每个部门的平均薪水的等级. 
       分析:首先求平均薪水(当成表),把平均薪水和另外一张表连接.
       join是连接两个表，而on是表示这两个表通过某种条件连接 
       
第十四课:self_table_connection
       
       把某个人的名字以及他的经理人的名字求出来(经理人及这个人在表中同处一行)
       
       分析:首先求出这个人的名字,取他的编号,然后从另一张表与其相对应编号,然后找到经理的名字.
       
       select e1.ename ,e2.ename from emp e1,emp e2 where e1.mgr= e2.empno.
       
       empno编号和MGR都是编号.


第十五课: SQL1999_table_connections    
         
      select ename,dname,grade from emp e,dept d, sqlgrade s
      where e.deptno = d.deptno and e.sql between s.losal and s.hisal and
      job<>'CLERK';
      
      有没有办法把过滤条件和连接条件分开来? 出于这样考虑,Sql1999标准推出来了.有许多人用的还是
      旧的语法,所以得看懂这种语句.
      
      
      
      select ename,dname from emp,dept;(旧标准).
      select ename,dname from emp cross join dept;(1999标准)
       
      select ename,dname from emp,dept where emp.deptno=dept.deptno (旧) 
      select ename,dname from emp join dept on(emp.deptno = dept.deptno); 1999标准.没有Where语句.
      select ename,dname from emp join dept using(deptno);等同上句,但不推荐使用.
      
      select ename,grade from emp e join salgrade s on(e.sal between s.losal and s.hisal);
      join 连接语句, on过滤条件。连接，条件一眼分开。如果用Where语句较长时，连接语句和过滤语句混在一起。
      
      三张表连接：
      slect ename,dname, grade from 
      emp e join dept d on(e.deptno=d.deptno)
      join salgrade s on(e.sal between s.losal and s.hisal)
      where ename not like '_A%';
      把每张表连接 条件不混在一起，然后数据过滤条件全部区分开来。读起来更清晰，更容易懂一点。
      
      select e1.ename,e2.ename from emp e1 join emp e2 on(e1.mgr = e2.emptno);

      左外连接：会把左边这张表多余数据显示出来。
      select e1.ename,e2,ename from emp e1 left join emp e2 on(e1.mgr =e2.empno);left 后可加outer
      右外连接：
    select ename,dname from emp e right outer join dept d on(e.deptno =d.deptno); outer可以取掉。
        
      即把左边多余数据，也把右边多余数据拿出来，全外连接。
      select ename,dname from emp e full join dept d on(e.deptno =d.deptno); 

```

### 1.2、习题

```	

16-23 课：求部门平均薪水的等级

       A.求部门平均薪水的等级。

       select deptno,avg_sal,grade from 
       (select deptno,avg(sal) avg_sal from emp group by deptno)t
       join salgrade s on(t.avg_sal between s.losal and s.hisal)
       
       B.求部门平均的薪水等级
       select deptno,avg(grade) from 
       (select deptno,ename, grade from emp join salgrade s on(emp.sal between s.losal and
       s.hisal)) t
       group by deptno

       C.那些人是经理
       select ename from emp where empno in(select mgr from emp);
       select ename from emp where empno in(select distinct mgr from emp);
       
       D.不准用组函数，求薪水的最高值（面试题）
       
       select distinct sal from emp where sal not in(
       select distinct e1.sal from emp e1 join emp e2 on (e1.sal<e2.sal));
       
       E.平均薪水最高的部门编号
       
       select deptno,avg_sal from
       (select avg(sal)avg_sal,deptno from emp group by deptno)
       where avg_sal=
       (select max(avg_sal)from 
       (select avg(sal) avg_sal,deptno from emp group by deptno)
       )
      
       F.平均薪水最高的部门名称
       select dname from dept where deptno=
      ( 
        select deptno from
        (select avg(sal)avg_sal,deptno from emp group by deptno)
        where avg_sal=
        (select max(avg_sal)from 
        (select avg(sal) avg_sal,deptno from emp group by deptno)
        )
       )
      
      G.求平均薪水的等级最低的部门的部门名称
        
        组函数嵌套
        如：平均薪水最高的部门编号，可以E.更简单的方法如下：
        select deptno,avg_sal from 
        (select avg(sal) avg_sal,deptno from emp group by deptno)
        where avg_sal =
        (select max(avg(sal)) from emp group by deptno)
        
        组函数最多嵌套两层
        
        分析：
        首先求
        1.平均薪水： select avg(sal) from group by deptno;

        2.平均薪水等级：  把平均薪水当做一张表，需要和另外一张表连接salgrade
        select  deptno,grade avg_sal from 
          ( select deptno,avg(sal) avg_sal from emp group by deptno) t
        join salgrade s on(t.avg_sal between s.losal and s.hisal)
        
        上面结果又可当成一张表。
        
        DEPTNO    GRADE    AVG_SAL
      --------  -------  ----------
        30           3   1566.66667
        20           4   2175
        10           4   2916.66667

        3.求上表平均等级最低值
        
        select min(grade) from
        (
          select deptno,grade,avg_sal from
           (select deptno,avg(sal) avg_sal from emp group by deptno)t
          join salgrade s on(t.avg_sal between s.losal and s.hisa)
         )

        4.把最低值对应的2结果的那张表的对应那张表的deptno, 然后把2对应的表和另外一张表做连接。
          
          select dname ,deptno,grade,avg_sal from
            (
  	      select deptno,grade,avg_sal from
              (select deptno,avg(sal) avg_sal from emp group by deptno)t
             join salgrade s on(t.avg_sal between s.losal and s.hisal)
             ) t1
            join dept on (t1.deptno = dept.deptno)
            where t1.grade =
            ( 
              select deptno,grade,avg_sal from
               (select deptno,avg(sal) avg_sal from emp group by deptno) t
                join salgrade s on(t.avg_sal between s.losal and s.hisal)
               )
            )
         结果如下：
         
        DNAME    DEPTNO     GRADE    AVG_SAL
      --------  -------  --------   --------
        SALES        30        3    1566.6667 
     
         
```

```sql
有3个表S，C，SC 
S（SNO，SNAME）代表（学号，姓名） 
C（CNO，CNAME，CTEACHER）代表（课号，课名，教师） 
SC（SNO，CNO，SCGRADE）代表（学号，课号，成绩） 
问题： 
1，找出没选过“黎明”老师的所有学生姓名。 
2，列出2门以上（含2门）不及格学生姓名及平均成绩。 
3，即学过1号课程有学过2号课所有学生的姓名。 
请用标准SQL语言写出答案，方言也行（请说明是使用什么方言）。

1.
select sname from s where sno in ( select sno from sc where cno not in (select cno from c where cteacher='黎明'))

select sname from s join sc on(s.sno=sc.sno) join c on(c.cno=sc.cno) where c.cteacher<>'黎明'

2.
select s.sname,scc.a from s join (select sno,count(sno) c,avg(scgrade) a from sc where scgrade<
60 group by sno) scc on(scc.sno=s.sno and scc.c>=2);

3.
select sname from s where s.sno in(select sno from sc where cno=101) and  s.sno in(select distinct sno from sc where cno=102);


数据库有3个表 teacher表 student表 tea_stu关系表 teacher表 teaID name age student表 stuID name age teacher_student表 teaID stuID 要求用一条sql查询出这样的结果: 1.显示的字段要有老师id age 每个老师所带的学生人数 2.只列出老师age为40以下 学生age为12以上的记录。


select t.teaID,t.age,tc.co from teacher t join(
select tID,count(*) co from (
select tc.teaID tID,t.stuID from teacher_student tc join student s where s.age>12
on(tc.stuID=t.stuID) join teacher t where t.age<40 on(tc.teaID=t.teaID) )
group by tID ) scc  on(t.teaID=scc.tID)


select a.teaID,a.age count(*)
from teacher a,student b,teacher_student c
where a.teaID=c.teaID
and b.stuID=c.stuID
and a.age>40
and b.age>12
group by a.teaID,a.age;


建表  增删改查  plsql 存储过程 

```

### 1.3、数据定义语言 DDL

```

数据定义语言（DDL）:create,alter,drop     （需要加table）
数据操纵语言（DML）:insert,delete,update,select (增删改查）
数据控制语言（DCL）:grant,revoke
事务控制语言（TCL）:commit,savepoint,rollback



数据定义语言（DDL）:create,alter,drop  （需要加table，主要对表起作用）
----创建表create table emp2 as select * from emp;
创建表的约束条件  not null 非空   unique 唯一   primary key 主键-唯一非空(不可删除）  check 检查
reference  外键约束被参考的键必须是主键primary key （如emp的deptno参考dept的deptno，则dept的deptno必须是主键）

sex varchar2(10) check (sex in ('男','女'));  check约束

create table te(
emono number(4) primary key,
ename varchar2(10) not null,
job varchar2(12),
sal number(7,2),
id number(6) unique);


-----创建表级约束constraint stu_sno_cno_uni unique(sno,cno)  
create table sc(
sno number(20) references s(sno),
cno number(10) references c(cno),
scgrade number(2),
constraint stu_sno_cno_uni unique(sno,cno)
)

----删除表drop table emp2；
drop table emp2 purge;  彻底删除表   purge recyclebin；清空回收站

----修改表结构  alter
alter table emp2 add(loc varchar(10));
alter table emp2 modify (loc varchar(20));
ALTER TABLE old_table_name RENAME TO new_table_name;(大写为系统命令)
alter table emp rename column empno to eo;



数据操纵语言（DML）:insert,delete,update,select (增删改查，主要对列起作用）
--插入数据 insert into dept2 values (50,'game','hf');
           insert into dept2(deptno,dname) values(60,'producer');
	   insert into dept2 select * from dept;

--删除数据 delete from dept2 where deptno>30;

--更新数据 update dept2 set deptno=deptno+1;

commit;  提交          rollback; 回退

```

---

### 1.4、索引视图

```

数据字典表 dictionary
select table_name from user_tables;
select table_name from dictionary where table_name like 'USER%' order by table_name;


索引index --读取数据速度快，但是更改速度慢,主键也是索引
查看索引  select index_name from user_indexes;
创建索引  create index idx_stu_email on stu(email);

视图view --就是子查询，使用方便，保护隐私。增加维护成本
查看视图  select view_name from user_views;
创建视图  create view v$_emp_deptno_avg_sal as select deptno,avg(sal) avg_sal from emp group by deptno;


序列 sequence --number 自动递增
创建序列  create sequence seq；
使用序列  select seq.nextval from dual;


  H: 视图（视图就是一张表，一个字查询）
        
       G中语句有重复，可以用视图来简化。
       conn sys/bjsxt as sysdba;
       grant create table,create view to scott;
       conn scott/tiger
       创建视图：
       create view v$_dept_avg-sal_info as
       select deptno,grade,avg_sal from
        ( select deptno,avg(sal) avg_sal from emp group by deptno)t
       join salgrade s on 9t.avg_sal between s.losal and s.hisal)
      
       然后 
       select * from v$_dept_avg-sal_info
       
       结果如下：
       DEPTNO      GRADE    AVG_SAL
      --------  -------  ----------
        30           3   1566.66667
        20           4   2175
        10           4   2916.66667

       然后G中查询可以简化成：
       select  dname,t1.deptno,grade,avg_sal from
       v$_dept_avg-sal_info t1
       join dept on9t1.deptno =dept.deptno)
       where t1.grade=
       (
	select min(grade) from v$_dept_avg-sal_info t1
       ) 


三范式--消除冗余数据 
第一范式：表要有主键，列不可分（唯一非空，不可重复；字段不可再分割）。
第二范式：多个字段组合成主键，其他字段不能依赖部分的主键。不能部分依赖。实体的属性完全依赖于主关键字。
第三范式：不能存在传递依赖。要求一个数据库表中不包含已在其它表中已包含的非主关键字信息。


```

---

### 1.5、声明 declare

```

plsql    过程化SQL语言（Procedural Language/SQL）
dbms     database mangement system 数据库管理系统
declare  声明

--打开输出 set serveroutput on;

--输出
begin
dbms_output.put_line('hello_world');
end;
/


--赋值 := 
 declare
 v_name varchar(20);
 begin
 v_name := 'hello';
 dbms_output.put_line(v_name);
 end;
 /


--异常 exception  when others then
declare
v_num number(10) :=2;
begin
v_num := v_num/0;
dbms_output.put_line(v_num);
exception
when others then
dbms_output.put_line('error');
end;
/


oracle的数据类型  binary_integer:整数，主要用来计数    long  长字符串，最长2G
constant  相当于java的final
oracle的boolean有三个值 true，false，null   但是不可打印
oracle的字符串连接符是 || ,相当于java的 +  
oracle的双引号是用两个单引号表示
赋值是 := 



declare
v_name varchar2(10);
v_id number := 20;
v_empno emp.empno%type;
v_empno1 v_empno%type;
v_pi constant number(7,2) :=3.14;
begin
v_empno := 3113;
dbms_output.put_line('v_empno'||v_empno);
end;



--变量声明，使用%type属性
 	declare
	  v_empno number(4);
	  v_empno2 emp.empno%type;
	  v_empno3 v_empno2%type;
	begin
	  dbms_output.put_line('test');
	end;

--table变量类型(数组)
declare
   type type_table_emp_empno is table of emp.empno%type index by binary_integer;
      v_empnos type_table_emp_empno;
begin
   v_empnos(0) := 7369;
    v_empnos(2) := 7839;
    v_empnos(-1) := 9999;
    dbms_output.put_line(v_empnos(-1));
end;


--record变量类型（近似java中的类）
declare
  type type_record_dept is record
      (
        deptno dept.deptno%type,
        dname dept.dname%type,
        loc dept.loc%type
      );
    v_temp type_record_dept;
begin
  v_temp.deptno := 50;
  v_temp.dname := 'aaa';
  v_temp.loc := 'bj';
  dbms_output.put_line(v_temp.deptno || ' ' || v_temp.dname);
end;


--使用%rowtype声明record变量   -------------------------------------------
declare
  v_temp dept%rowtype;
begin
    v_temp.deptno := 50;
    v_temp.dname := 'aaa';
    v_temp.loc := 'bj';
   dbms_output.put_line(v_temp.deptno || ' ' || v_temp.dname);
end;


--SQL语句的运用
--select语句  --有且只有一条返回语句   into  --------------------------------
declare
     v_name emp.ename%type;
     v_sal emp.sal%type;
begin
   select ename, sal into v_name, v_sal from emp where empno = 7369;
   dbms_output.put_line(v_name || ' ' || v_sal);
end;


declare
    v_emp emp%rowtype;
begin
  select * into v_emp from emp where empno = 7369;
  dbms_output.put_line(v_emp.ename);
end;

--insert语句

declare
    v_deptno dept.deptno%type := 50;
    v_dname dept.dname%type := 'aaa';
    v_loc dept.loc%type := 'bj';
begin
  insert into dept2 values(v_deptno, v_dname, v_loc);
 commit;
end;

--sql%rowcount  sql是指刚刚执行的语句，rowcount是有多少条语句被影响，一般与update，delete同时使用
declare
   v_deptno emp2.deptno%type := 10;
   v_count number;
begin
  --update emp2 set sal = sal/2 where deptno = v_deptno;
  --select deptno into v_deptno from emp2 where empno = 7369;
  select count(*) into v_count from emp2;
  dbms_output.put_line(sql%rowcount || '条记录被影响');
 commit;
end;

DDL语句 ''aaa'' 双引号用两个单引号表示
begin
     execute immediate 'create table t (nnn varchar2(20) default ''aaa'')';
end;


--if语句   if() then  ;elsif() then  ;else  ;end if;   注意是 elsif
取出7369的薪水，如果<1200，输出'low'，如果<2000输出'middle'，否则'high'

declare
    v_sal emp.sal%type;
begin
   select sal into v_sal from emp
          where empno = 7369;
   if (v_sal < 1200) then
         dbms_output.put_line('low');
   elsif (v_sal < 2000) then
         dbms_output.put_line('middle');
   else
         dbms_output.put_line('high');
   end if;
end;

--练习



--循环
declare
   i binary_integer := 1;
begin
   loop
      dbms_output.put_line(i);
           i := i + 1;
           exit when (i >= 11);
   end loop;
end;
---------

declare
   j binary_integer := 1;
begin
  while j < 11 loop
      dbms_output.put_line(j);
          j := j + 1;
  end loop;
end;

-----------
begin
    for k in 1..10 loop
       dbms_output.put_line(k);
    end loop;

    for k in reverse 1..10 loop
        dbms_output.put_line(k);
     end loop;
end;

--错误处理
declare
   v_temp number(4);
begin
   select empno into v_temp from emp where empno = 10;
exception
   when too_many_rows then
      dbms_output.put_line('太多纪录了');
   when others then
      dbms_output.put_line('error');
end;

----------

declare
   v_temp number(4);
begin
   select empno into v_temp from emp where empno = 2222;
exception
   when no_data_found then
      dbms_output.put_line('没有数据');
end;

---------
--创建事件日志表
create table errorlog
(
id number primary key,
errcode number,
errmsg varchar2(1024),
errdate date
)
--创建序列
create sequence seq_errorlog_id start with 1 increment by 1 
--实验
declare
   v_deptno dept.deptno%type := 10;
   v_errcode number;
   v_errmsg varchar2(1024);
begin
   delete from dept where deptno = v_deptno;
 commit;
exception
   when others then
      rollback;
         v_errcode := SQLCODE;
         v_errmsg := SQLERRM;
      insert into errorlog values (seq_errorlog_id.nextval, v_errcode, v_errmsg, sysdate);
      commit;
end;

--游标   类似java中的迭代器
declare
   cursor c is
            select * from emp;
   v_temp c%rowtype;
begin
    open c;
    fetch c into v_temp;
    dbms_output.put_line(v_temp.ename);
    close c;
end;


------------------
declare
    cursor c is
       select * from emp;
    v_emp c%rowtype;
begin
    open c;
    loop
      fetch c into v_emp;
      exit when (c%notfound);
      dbms_output.put_line(v_emp.ename);
    end loop;
    close c;
end;


----------------------while循环  两个fetch c into v_emp
declare
    cursor c is
       select * from emp;
    v_emp c%rowtype;
begin
    open c;
    fetch c into v_emp;
    while (c%found) loop
      dbms_output.put_line(v_emp.ename);
      fetch c into v_emp;
    end loop;
    close c;
end;


-----------------for 循环：没有打开，关闭cursor，也没有fetch 。默认 v_emp c%rowtype;
declare
    cursor c is
       select * from emp;
begin
   for v_emp in c loop
        dbms_output.put_line(v_emp.ename);
    end loop;
end;


--带参数的游标
declare
   cursor c (v_deptno emp.deptno%type, v_job emp.job%type)
   is
     select ename, sal from emp where deptno = v_deptno and job = v_job;
begin
   for v_temp in c(30,'CLERK') loop
      dbms_output.put_line(v_temp.ename);
   end loop;
end;


--可更新的游标
declare
  cursor c
  is
    select * from emp2 for update;
begin
   for v_temp in c loop
      if (v_temp.sal < 2000) then
         update emp2 set sal = sal * 2 where current of c;
      elsif (v_temp.sal = 5000) then
         delete from emp2 where current of c;
      end if;
    end loop;
    commit;
end;
----------------

```

### 1.6、存储过程

```
--存储过程
create or replace procedure p
is
  cursor c
  is
    select * from emp2 for update;
begin
   for v_temp in c loop
      if (v_temp.deptno = 10) then
         update emp2 set sal = sal + 10 where current of c;
      elsif (v_temp.deptno = 20) then
         update emp2 set sal = sal + 20 where current of c;
      else
         update emp2 set sal = sal + 50 where current of c;
      end if;
    end loop;
    commit;
end;

--显示错误  
show error
--执行 
exec p;
或
begin;
 p;
end;


--带参数的存储过程  in 输入  out 输出
create or replace procedure p
     (v_a in number, v_b number, v_ret out number, v_temp in out number)
is
begin
   if (v_a > v_b) then
      v_ret := v_a;
   else
      v_ret := v_b;
   end if;
   v_temp := v_temp + 1;
end;

--实验
declare
 v_a number := 3;
 v_b number := 4;
 v_ret number;
 v_temp number := 5;
begin
 p(v_a, v_b, v_ret, v_temp);
 dbms_output.put_line(v_ret);
 dbms_output.put_line(v_temp);
end;
-------------------


 create or replace procedure getNo(name in emp2.ename%type,no out emp2.empno%type) as
 eno emp2.empno%type;
 begin
 select empno into eno from emp2 where ename=name;
 dbms_output.put_line(eno);
 no:=eno;
 end;
 /


 declare
 name emp2.ename%type :='SMITH';
 no emp2.empno%type;
 begin
 getNo(name,no);
 dbms_output.put_line('---'||no);
 end;
 /


```

### 1.7、函数与触发器

```sql

--函数
create or replace function sal_tax
  (v_sal number)
  return number
is
begin
   if (v_sal < 2000) then
      return 0.10;
   elsif (v_sal < 2750) then
      return 0.15;
   else
      return 0.20;
   end if;
end；

select sal_tax(sal) from emp;



--触发器
create table emp2_log
(
uname varchar2(20),
action varchar(10),
atime date
)
-----------
create or replace trigger trig
  after insert or update or delete on emp2
begin
  if inserting then
     insert into emp2_log values (USER, 'insert', sysdate);
  elsif updating then
     insert into emp2_log values (USER, 'update', sysdate);
  elsif deleting then
     insert into emp2_log values (USER, 'delete', sysdate);
  end if;
end;

----------
update emp2 set sal = sal * 2 where deptno = 30;
--------
create or replace trigger trig
  after insert or update or delete on emp2 for each row
begin
  if inserting then
     insert into emp2_log values (USER, 'insert', sysdate);
  elsif updating then
     insert into emp2_log values (USER, 'update', sysdate);
  elsif deleting then
     insert into emp2_log values (USER, 'delete', sysdate);
  end if;
end;
-------------
--不提倡使用   改变被参考的主键
create or replace trigger trig
 after update on dept for each row
begin
 update emp2 set deptno = :NEW.deptno where deptno = :OLD.deptno;
end;
----------------------



--树状结构的存储与展现
drop table article;

create table article
(
id number primary key,
cont varchar2(4000),
pid number,
isleaf number(1), --0代表非叶子节点，1代表叶子节点
alevel number(2)
)
-------------
insert into article values (1, '蚂蚁大战大象', 0, 0, 0);
insert into article values (2, '大象被打趴下了', 1, 0, 1);
insert into article values (3, '蚂蚁也不好过', 2, 1, 2);
insert into article values (4, '瞎说', 2, 0, 2);
insert into article values (5, '没有瞎说', 4, 1, 3);
insert into article values (6, '怎么可能', 1, 0, 1);
insert into article values (7, '怎么没可能', 6, 1, 2);
insert into article values (8, '可能性是很大的', 6, 1, 2);
insert into article values (9, '大象进医院了', 2, 0, 2);
insert into article values (10, '护士是蚂蚁', 9, 1, 3);
commit;
---------
蚂蚁大战大象
   大象被打趴下了
      蚂蚁也不好过
      瞎说
         没有瞎说
      大象进医院了
         护士是蚂蚁
   怎么可能
         怎么不可能
         可能性是很大的
--------------------------
create or replace procedure p (v_pid article.pid%type, v_level binary_integer) is
  cursor c is select * from article where pid = v_pid;
  v_preStr varchar2(1024) := '';
begin
  for i in 1..v_level loop
    v_preStr := v_preStr || '****';
  end loop;
  for v_article in c loop
    dbms_output.put_line(v_preStr || v_article.cont);
  if (v_article.isleaf = 0)
then
    p (v_article.id, v_level + 1);
  end if;
  end loop;
end;


exec p(0,0);

```

---


> 序列与触发器

```sql

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

```


## 2、基础操作

### 2.1、登录

```sql

登陆 oracle账号    
cmd-sqlplus-scott-tiger; sqlplus scott/tiger;   //登陆oracle的普通用户账号
sqlplus sys/JAVA123o as sysdba;                 //登陆oracle的sys账号
sqlplus / as sysdba;				//以OS身份连接  
sqlplus /nolog;					//直接进入SQLPLUS命令提示符 
conn scott/tiger;				//切换用户，conn同connect 
conn sys/sys as sysdba;                         //以DBA的身份登录


用户修改密码
conn sys as sysdba                                    //登陆sys账号
alter user scott account unlock identified by tiger;  //解锁，改密


安装oracle蓝屏或者关机速度慢可以在管理服务里把oracle的相关服务改为延迟启动或者手动


sqlplus /nolog只是登录进了SQLPLUS,这个参数表示是不用no login的意思。此时并没有登录数据库。
所以需要再用connect username/password连接上数据库
  
 
ORA-01034: ORACLE not available ORA-27101: shared memory realm does not exist
输入sqlplus /nolog,回车， 
这时出现了SQL>,然后再输入connect / as sysdba;回车, 
再输入startup，回车，等待一会，等这个命令运行完之后，再连接数据库，就能进行查询、插入等正常操作


oracle登录sqlplus时，英文提示信息都是问号
alter system set nls_language=american scope=spfile;


ORA-01017: invalid username/password; logon denied  登陆被拒绝（用户未解锁）
解决：
（1）conn sys/sys as sysdba;//以DBA的身份登录
（2）alter user scott account unlock;// 然后解锁
（3）conn scott/tiger //弹出一个修改密码的对话框，修改一下密码就可以了
 
具体操作步骤如下：
C:> sqlplus
请输入用户名：sys
输入口令：sys as sysdba //注意：在口令这里输入的密码后面必须要跟上 as sysdba 才可以。
SQL> alter user scott account unlock;
     用户已更改.
SQL> commit;
     提交完成.
SQL> conn scott/tiger
更改scott口令
新口令：tiger
重新键入新口令：tiger
口令已更改
已连接。

另一种方法：你打开命令提示符，不要登录直接输入下面：
sqlplus sys/tiger as sysdba
以dba方式进入sys帐户；
alter user scott account unlock;
给scott用户解锁；


登陆显示协议适配器错误
在控制面板--管理工具--服务中启动：（可以改为自动-延迟启动）
OracleServiceORCL
OracleOraDb11g_home1TNSListener

```

---

```sql

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


### 2.2、基础命令

```
ed                 重新编辑  
/                  运行上一个
spool h:/1.txt     创建文件h:/1.txt来保存文件
spool of           保存文件
col mgr for 9999   设置列的宽度(在emp表，mgr为number）
col ename for a10  设置列的宽度(在emp表，ename为varchar）
set pagesize 20    设置页面长度
select * from tab; 查看当前用户所有的table
desc emp ;         查看emp的描述
show user;	   显示当前用户
--  /**/           单行和多行注释
purge recyclebin;  清空回收站
commit             提交
exit 		       退出


备份账户数据
1.导出数据   
cmd  exp     输入scott/tiger
2.创建用户、密码分配表空间、配额容量
create user xyq identified by xyq default tablespace users quota 10M on users;
3.分配权限 登陆权限、建表权限，建立视图权限
grant create session,create table,create view to xyq;
4.导入数据  首先输入新的用户名/密码   后面的输入scott
cmd imp


```

```
三范式--消除冗余数据 
第一范式：表要有主键，列不可分（唯一非空，不可重复；字段不可再分割）。
第二范式：多个字段组合成主键，其他字段不能依赖部分的主键。不能部分依赖。实体的属性完全依赖于主关键字。
第三范式：不能存在传递依赖。要求一个数据库表中不包含已在其它表中已包含的非主关键字信息。

```

### 2.3、添加字段

```sql

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
	
```

### 2.4、分页排序

```

分页排序
　select rownum rn ,a.* from USER_INFO a order by A.USERAGE desc;

	SELECT * FROM  
	(  
	SELECT A.*, ROWNUM RN  
	FROM (SELECT * FROM TABLE_NAME) A  
	WHERE ROWNUM <= 40  
	)  
	WHERE RN >= 21
	

	
oracle查询:分组查询，取出每组中的第一条记录			
https://blog.csdn.net/yatou5211/article/details/53764676			
SELECT * FROM(
SELECT z.type , z.code ,ROW_NUMBER()
OVER(PARTITION BY z.type ORDER BY z.code) AS code_id
FROM group_info z
)
WHERE code_id =1;

	
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

### 2.5、oracle的数据类型

```java
oracle的数据类型

VARCHAR2(size)   
可变长度的字符串,其最大长度为size个字节;size的最大值是4000,而最小值是1;你必须指定一个VARCHAR2的size;   

NVARCHAR2(size)   
可变长度的字符串,依据所选的国家字符集,其最大长度为size个字符或字节;size的最大值取决于储存每个字符所需的字节数,其上限为4000;你必须指定一个NVARCHAR2的size;   

NUMBER(p,s)   
精度为p并且数值范围为s的数值;精度p的范围从1到38;数值范围s的范围是从-84到127; 
例如:NUMBER(5,2)   表示整数部分最大3位，小数部分为2位； 
NUMBER(5,-2)   表示数的整数部分最大为7其中对整数的倒数2位为0,前面的取整。
 
NUMBER   
表示使用默认值,即等同于NUMBER(5);   

LONG   
可变长度的字符数据,其长度可达2G个字节; 
  
DATE   
有效日期范围从公元前4712年1月1日到公元后4712年12月31日 
  
RAW(size)   
长度为size字节的原始二进制数据,size的最大值为2000字节；你必须为RAW指定一个size;   

LONG   RAW   
可变长度的原始二进制数据，其最长可达2G字节;
   
CHAR(size)   
固定长度的字符数据,其长度为size个字节;size的最大值是2000字节,而最小值和默认值是1; 
  
NCHAR(size)   
也是固定长度。根据Unicode标准定义 
  
CLOB   
一个字符大型对象,可容纳单字节的字符;不支持宽度不等的字符集;最大为4G字节 
  
NCLOB   
一个字符大型对象,可容纳单字节的字符;不支持宽度不等的字符集;最大为4G字节;储存国家字符集
   
BLOB   
一个二进制大型对象;最大4G字节
   
BFILE   
包含一个大型二进制文件的定位器,其储存在数据库的外面；使得可以以字符流I/O访问存在数据库服务器上的外部LOB;最大大小为4G字节.


```

## 3、其他问题

### 3.1、mysql转oracle

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


### 3.2、创建外键约束
	
```	
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

```

```

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


### 3.3、调整字段顺序

```sql

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

