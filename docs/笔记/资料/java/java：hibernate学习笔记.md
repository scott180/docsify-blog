hibernate 笔记 ：马士兵视频

java-hibernate笔记(讲义)-【尚学堂马士兵老师】
知识点终结：


1 新建项目
2 学习建立user-library-hibernate,并加入相应的jar包
  a项目右键-build path-configure build path-add library
  b选择user-library,在其中新建library,命命为hibernate
  c 在该library中加入hibernate所需要的jar包
	hibernate3.3.2 
/hibernate3.jar
/lib/required目录下的所有包 6个
Sl4j-nop jar
3 引入mysql的JDBC驱动包
4 在MYSQL中建数据库和相应的表student(id,name,age)
5 建立hibernate配置文件hibernate.cfg.xml
 参考文档中COPY,修改对应的数据库连接,
6 建立student类
7 建立映射文件Student.hbm.xml 参考相应文档
8 将映射文件加到hibernate-cfg.xml中

搭建日志环境并配置显示DDL语句
slf的实现:slf4j nodep ,log4j ,jdk logging api ,apache common-log.
slf4j.nop.jar是slf-api.jar其相应的接口实现
把slf的接口对到log4j的实现,在user library中hibernate,把slf的实现slf4j-nop-1.5.8.jar去掉,添加log4j的实现log4j-1.2.15.jar,再添加一个slf-api和log4j转换器slf4j-log4j12-1.5.8.jar.
把slf的接口转换成log4j的接口.最后添加一个log4j的配置文件log4j.properties




对象映射(采用Annotation注解方式)
1 建表
  Create table teacher(id int primary key,name varchar(20),title varchar(20));
2 创建teacher类,并进行注解@
import javax.persistence.Entity;
import javax.persistence.Id;
在bean中加注解
@ Entity,@Id(加在getId()上面)
3 在hibernate中加入annotation相应的jar包
	hibernate-annotations.jar
	/lib目录下加入相应的包ejb3-persistence.jar, hibernate-commons-annotations.jar
注意:annotation文档中并没有提到hibernate-annotations.jar包
4 参考annotation文档建立对应的注解
5 在hibernate.cfg.xml中建立映射
 <mapping class=”com.xx.xx”/>
6 示例



对象映射(采用配置文件方式)
1 在相应的类中建立对应的配置文件. 
例如Student类对应的配置文件Student.hbm.xml
<hibernate-mapping package="com.model">
	<class name="Student" table=" Student" >
		<id name="id" column="id"></id>
		<property name="name" column="name" />
		<property name="age" column="age" />
	</class>
</hibernate-mapping>
2 在hibernate.cfg.xml中添加该映射文件Student.hbm.xml即可
    <!-- 建立对应的配置文件关联相应的数据库表 -->
    <mapping resource="com/model/Student.hbm.xml"/>注意包名的写法
3 示例
//读取配置文件hibernate.cfg.xml
		Configuration cfg=new Configuration().configure();
//创建SessionFactory
		SessionFactory sf=cfg.configure().buildSessionFactory();
//创建session
		Session session=sf.openSession();
		session.beginTransaction();
		session.save(s);
		session.getTransaction().commit();
		session.close();
		sf.close();

hibernate.cfg.xml配置
hibernate.hbm2ddl.auto属性
取值: validate | update | create | create-drop
在sessionfactory创建时,自动检查数据结构,或者将数据库schema(表)的DDL导出到数据库,使用create-drop时,在显式关闭sessionfactory时,将drop掉数据库schema.
validate               加载hibernate时，验证创建数据库表结构 
create                  每次加载hibernate，重新创建数据库表结构 
create-drop        加载hibernate时创建，退出是删除表结构 
update                 加载hibernate自动更新数据库结构


表名和类名不同的情况时,对表名进行配置
1 在注解中
import javax.persistence.Table;
@Table(name=”TableName”)
2 在XML文件中 
<hibernate-mapping package="com.model">
	<class name="Teacher" table="Teacher" >配置对应的table属性为相应的表名
		<id name="id" column="id"></id>
	…
	</class>
</hibernate-mapping>

字段名和属性名相同,默认为
对于annotation,如果什么注解都不写的话,相当于加了注解@Basic
实体bean中所有的非static非transient的属性都可以被持久化, 除非你将其注解为@Transient.所有没有定义注解的属性等价于在其上面添加了@Basic注解. 通过 @Basic注解可以声明属性的获取策略(fetch strategy)：
对于XML文件中不用写column.

字段名和属性名不同时
Annotation:@column(name=”column_name”)加上相应的getXXX()方法上
XML:column属性

不需要persistence的字段
import javax.persistence.Transient;
@ Transient 意思是说这个属性是透明的,不进行持久化,存储的时候不存进去

映射日期和时间类型,指定时间精度
Annotation: 
import javax.persistence.Temporal;
@Temporal(TemporalType.TIME)

XML:指定type
<class name="Teacher" table="Teacher" >
		<id name="id" column="id"></id>
		<property name="name" type="time" />type=time或date或timestamp
</class>

枚举类型的转换
@Enumerated(EnumType.STRING)

字段映射的位置
推荐写在getXXX方法上



ID生成策略<generator>
1.XML方式配置
<?xml version="1.0"?>
<!DOCTYPE hibernate-mapping PUBLIC
        "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
        "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<hibernate-mapping package="com.hibernate.core">
	 <class name="Student" dynamic-update="true">
		<id name="id">
            <generator class="identity"/>
        </id>
		 <property name="name"/>
		 <property name="age"/>
		 <property name="d" type="timestamp"></property>
    </class>
</hibernate-mapping>

	identity 对DB2,MySQL, MS SQL Server, Sybase和HypersonicSQL的内置标识字段提供支持。 返回的标识符是long, short 或者int类型的。 
	sequence 在DB2,PostgreSQL, Oracle, SAP DB, McKoi中使用序列（sequence)， 而在Interbase中使用生成器(generator)。返回的标识符是long, short或者 int类型的。  
	uuid 用一个128-bit的UUID算法生成字符串类型的标识符， 这在一个网络中是唯一的（使用了IP地址）。UUID被编码为一个32位16进制数字的字符串。 	
	native 根据底层数据库的能力选择identity, sequence 或者hilo中的一个。在mysql中默认的是auto_increment,SQLSERVER中是identity.

2 annotation中配置id生成策略
使用@Id注解可以将实体bean中的某个属性定义为标识符(identifier). 该属性的值可以通过应用自身进行设置, 也可以通过Hiberante生成(推荐). 
使用 @GeneratedValue注解可以定义该标识符的生成策略: 有四种策略
	AUTO -默认值.可以是identity column类型,或者sequence类型或者table类型,取决于不同的底层数据库..对于MYSQL,是auto_increment,对于Oracle是hibernate-sequence.
	TABLE - 使用表保存id值 (了解)
	IDENTITY - identity column 
	SEQUENCE - @SequenceGenerator
@GeneratedValue(strategy=GenerationType.XXXX)
XXXX取值为Type.SEQUENCE|TABLE|AUTO|IDENTITY不同的数据库对应着不同的生成策略.

例1
实体类注解
@Entity
主键进行注解
@Id
@GeneratedValue 
默认值是@GeneratedValue(strategy=GenerationType.AUTO)

例2
使用SequenceGenerator
@Entity
@SequenceGenerator(
name="teacher_SEQUENCE",sequenceName="teacher_SEQUENCE_DB")
name是用户自定义的generator生成器的名字, sequenceName是生成到数据库后sequence对象的名字.
在实体中注解好后,就可以在id注解上写上对应的
@Id
@GeneratedValue(
strategy=GenerationType.IDENTITY,generator="teacher_SEQUENCE")

例3
表生成器(了解),这种方式会另外生成一个表.
实体类注解
@Entity
@javax.persistence.TableGenerator(//了解,更适合用于跨平台跨数据库.
	    name="TEACHER_GEN", //生成器generator的名字
	    table="GENERATOR_TABLE",//生成的表名
	    pkColumnName = "pk_key",//生成的表的字段名
	    valueColumnName = "pk_value",//生成的表的字段的值
	    pkColumnValue="teacher",// pk_key字段的值
	    allocationSize=1//自增变量
	)
主键注解
@Id
@GeneratedValue(strategy=GenerationType.TABLE,generator="TEACHER_GEN")


联合主键生成策略
一般采用这种方式,比如有一个类Student(id,name,age),为了产生联合主键,把id和name分离出来.
Student(pk,age) StudentPk(id,name)
StudentPk类必需实现序列化接口implements java.io.Serializable.
StudentPk类必需重写boolean equals() ,int hasCode()方法
@Override
	public boolean equals(Object o) {
		if(o instanceof StudentPk) {
			StudentPk pk = (StudentPk)o;
			if(this.id == pk.getId() && this.name.equals(pk.getName())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
联合主键生成策略XML配置方法
<composite-id name="pk" class="com.hibernate.StudentPk">
			<key-property name="id"></key-property>
			<key-property name="name"></key-property>
</composite-id>
联合主键生成策略annotation配置方法
定义组合主键的三种方式:
	(少用)将组件类注解为@Embeddable,并将组件的属性注解为@Id.
实体模型Teacher(teacherPK,age) TeacherPk(id,name)
在TeacherPk(id,name)中把类注解@Embeddable
在Teacher(teacherPK,age)中把组件属性teacherPK注解@Id
	将组件的属性注解为@EmbeddedId. 
实体模型Teacher(teacherPK,age) TeacherPk(id,name)
只需要在Teacher(teacherPK,age)中把组件属性teacherPK注解@EmbeddedId
	(推荐使用)将类注解为@IdClass,并将该实体中所有属于主键的属性都注解为@Id. 
实体模型Teacher(id,name,age) TeacherPk(id,name)
在Teacher(id,name,age)中把类注解@IdClass(value=”TeacherPk.Class”),在主键属性id,name上注解@Id即可.当IdClass()中只有一个属性默认写成IdClass(TeacherPk.Class).也就是说Teacher里面的组件属性id,name,合起来刚好是类TeacherPk.




对象的三种状态
三种状态的区别在于:
有没有ID,ID在数据库中有没有,在内存中有没有(session缓存)
三种状态
Transient:内存中的一个对象,没有ID,缓存中也没有
Persistent:内存中有,缓存中有,数据库中有ID
Detached:内存中有,缓存中没有,数据库有ID

核心接口开发介绍
Configuration
1 AnnotationConfiguration
2 进行配置信息的管理
3 用来产生SessionFactory:buildSessionFactory()
4 可以在configure()方法中指定hibernate配置文件

SessionFactory
1 用来产生和管理sesssion
2 通常情况下,每个应用只需要一个SessionFactory,除非要访问多个数据库的情况
3 openSession()与openSession()
	openSession()总是创建新的session,需要手动close().
	getCurrentSession()事务自动提交并且自动关闭.从上下文环境中获得session,如果当时环境中不存就创建新的.
   如果环境中存在就使用环境中的,而且每次得到的都是同一个session(在session提交之前,提交之后就是新的了).
sf.getCurrentSession();提交之后自动关闭
用途:界定事务边界.
	所谓的上下文参见配置文件
<property name="current_session_context_classs">thread</property>
取值范围 jta | thread | managed | custom.Class 



Session
管理一个数据库的任务单元,即管理数据库中的增删改查操作,提交事务.
方法CRUD:save(),delete(),update(),saveOrUpdate(),load(),get(),clear().
session.beginTransaction();
session.save(Object obj);
session.getTransaction().commit();
session.close();

get()与load()的区别
	查找时,都会优先从session的缓存中查找.
	查找不存在对应记录时,表现不一样.load方法查找不到时不会报错,get查找不到时会报错.
	Load返回的是代理对象,等到真正要用到对象的内容时才发起SQL语句.get直接发起SQL语句从数据库中取出,不会延迟.
Update()方法
1 用来更新detached对象,更新完成之后成为persistent.
2 更新transient对象会报错. 更新自己设定id(前提是id在数据库中存在)的transient对象可以.
3 持久化的对象只要设定不同字段就会发生更新
4 更新部分更改的字段(三种方法)
	XML设定property标签的update=true|false属性,
annotation设定@Column(updatable=false)属性,这种方式少用,不灵活.
	XML设定class标签的dynamic-update=”true”属性,
同一个session中可以,跨session不行.跨session时的实现方法不过可以用session的merge().merge方法会先从数据库load,将得到的和数据库中的进行对比,再update更改过的字段.
JPA1.0 Annotation没有对应的属性,Hibernate
	使用HQL(EJBQL)(建议使用)
clear()方法:
清除session中的缓存.调用clear()方法会强制清除session缓存.不会与数据库打交道.
flush()方法:
当session的事务提交后,会强制进行从内存(session缓存)到数据库的同步.默认情况下是session的事务提交时才同步.不常用.

Query接口



关系映射
这里的关系映射指的是对象之间的关系,并不是指数据库的关系. 关系映射解决的问题是,当对象处于各种关系时,数据库表该如何映射,编程时如何处理.
一对一:单向(主键,外键),双向(主键,外键)
一对多:单向,双向(和多对一双向相同)
多对一:单向,双向(一对多双向和多对一双向是一样的)
多对多:单向,双向
(一对一单/双向主键关联映射,只作了解)
集合映射:list ,map,set
继承映射(了解):单表,多表,一张主表多张子表
组件映射:@Embeddable,@Embedded

unique      单项
bidirection 双向

双向映射，需要加入mappdeBy
          需要注明双向关系

一对一单项外键
	@OneToOne
	@JoinColumn(name="wifeId")
	public Wife getWife() {
		return wife;
	}
	
一对一双向外键 必设 mappedBy
	@OneToOne(mappedBy="wife")//mappedBy="wife"  是Husband里的getWife()
	public Husband getHus() {
		return hus;
	}	

一对一单向主键	
	@OneToOne
	@PrimaryKeyJoinColumn
 
 一对一联合外键
	@OneToOne
	@JoinColumns(
			{
			@JoinColumn(name="wifeId",referencedColumnName="id"),
			@JoinColumn(name="wifeName",referencedColumnName="name")
			}
			)

组件映射  两个对象合并一张表
@Embedded
	public Wife getWife() {
		return wife;
	}
	
多对一 和 一对多单向关联映射 ：
如 一个组有多个用户（每个用户user 有组usersgroup的id字段）
在数据库里面都是在多的表里面加字段（一 的 id）
在hibernate里面 多对一：是在 多 的对象里 添加@ManyToOne，@JoinColumn(name="groupId") 且添加一的引用
				如果不加@JoinColumn(name="groupId") hibernate会认为是多对多，加入一个中间表
                一对多：是在 一 的对象里面添加@OneToMany(mappedBy="group")
					   
						
多对一单向关联映射，(many to one)
user 添加usersgroup引用
	@ManyToOne
	@JoinColumn(name="groupId")
	public UsersGroup getGroup() {
		return group;
	}

一对多单向关联映射，(one to many)
usersgroup 添加user的set
	@OneToMany
	@JoinColumn(name="groupid")
	public Set<User> getUsers() {
		return users;
	}

	
多对一、一对多双向关联映射 ：
user
	@ManyToOne
	@JoinColumn(name="groupid")
	public UsersGroup getGroup() {
		return group;
	}
	
usersgroup：以用户为主导，在用户添加映射字段，使用mappedBy。且保证没有冗余字段。
	@OneToMany(mappedBy="group")
	public Set<User> getUsers() {
		return users;
	}

	
多对多单向  teacher 对应 student
	@ManyToMany
	@JoinTable(name="ts",
			joinColumns={@JoinColumn(name="tid")},//指向teacher表
			inverseJoinColumns={@JoinColumn(name="sid")}//指向另一张表
	)
	public Set<Student> getStudents() {
		return students;
	}
XML中
<class name="com.xxx.Teacher">
		<id name="id">
			<generator class="native"/>
		</id>
		<property name="name"/>
		<set name="students" table="t_s">table定义中间表的表名
			<key column="teacher_id"></key>
			<many-to-many class="com.xxx.Student" column="student_id"/>
		</set>
</class>

	
多对多双向   必须加mappdeBy 否则会多生成一个默认的表
 teacher 对应 student
	@ManyToMany
	@JoinTable(name="ts",
			joinColumns={@JoinColumn(name="tid")},//指向teacher表
			inverseJoinColumns={@JoinColumn(name="sid")}//指向另一张表
	)
	public Set<Student> getStudents() {
		return students;
	}
	
@ManyToMany(mappedBy="students")
	public Set<Teacher> getTeachers() {
		return teachers;
	}
	
	
	
	
Annotation配置
@Entity
@Table(name="t_group")//注意表名与SQL中关键字重名
只需要在多的一端User属性group进行注解配置
@ManyToOne
@JoinColumn(name=”groupId”)
XML配置
<many-to-one name="group" column="groupId" />
标签会在”多”的一端添加外键,相当于在数据库中添加外键
生成的表为user(id,name,groupid),t_group(id,groupname)
属性cascade
<many-to-one name="group" column="groupid" cascade="all"/>
取值all,none,save-update,delete,对象间的级联操作,只对增删改起作用.
在存储时User时,设置了cascade="all"会自动存储相应的t_group.而不用管user关联的对象(通常情况下会优先存储关联的对象,然后再存储user).

一对多(one to many)单向关联映射
模型(group一对多user)
Group(id,name,users)一
User(id,name)多
设计时在一的这一端存在着多的集合,生成的数据库表通常是在多的一端生成外键.
Set<User> users=new HashSet<User>()
一对多单向外键关联映射
在一的这一端Group端users属性上进行注解配置
@OneToMany
@JoinColumn(name="groupId")
如果不指定生成的外键列@JoinColumn(name="groupId"),默认会生成多对多的关系,产生一张中间表.
XML配置中配置一的那一端Group
<class name="com.hibernate.Group" table="t_group">
		<id name="id">
			<generator class="native"/>
		</id>
		<property name="name"/>
		<set name="users">
			<key column="groupId"/>指定生成外键字段的名字
			<one-to-many class="com.hibernate.User"/>
		</set>
</class>


规律:
	单向时, 一方存在另一方的引用,数据库表中体现为外键.配置时一般只用配置一端.
	双向时,双方互持对方的引用,要进行双向两端的配置.基于annotation的双向配置中,在一端配置好后,在另一端必须用指定mappedby属性.
	关于一对多/多对一数据库设计时,总是在多的一端加外键.通常在多的一端指定生成的外键名称.

一对多/多对一双向关联
一对多与多对一的双向关联是同一种情况.
关系模型(group一对多user)
Group(id,name,users)一
User(id,name,group)多
Set<User> users=new HashSet<User>()
配置规则:一般以多的一端为主,先配置多的一端
在多的一端User端配置group
@ManyToOne
在一的一端Group端配置时,在users只需要加个mappedBy="group"
@OneToMany(mappedBy="group")
XML配置
Group中
<set name="users">
	<key column="groupId"/>			
<one-to-many class="com.hibernate.User"/>
</set>
在User中
<many-to-one name="group" column="groupId"/>
务必确保在多的一端生成的生成的外键和一的一方生成的外键的名字相同,都为groupId.
如果名字不同则会在多的一端生成多余的外键.
create table t_group (
        id integer not null auto_increment,
        name varchar(255),
        primary key (id)
    )
create table t_user (
        id integer not null auto_increment,
        name varchar(255),
        groupId integer,
        primary key (id)
    )
alter table t_user 
        add index FKCB63CCB6C3D18669 (groupId), 
        add constraint FKCB63CCB6C3D18669 
        foreign key (groupId) references t_group (id)

多对多单向关联
关系举例:老师学生,老师需要知道自己教了哪些学生,但学生不知道自己被哪些老师来教.
数据库:中间表
Annotation:@ManyToMany
XML:<many-to-many>
关系模型(Teache多对多Student),从Teacher这一端能关联到students.
Teacher(id,name,students)多
Student(id,name)多
Set<Student> students=new HashSet<Student>()
在Teacher那一端配置
@ManyToMany
如果手动指定生成的中间表的表名和字段名
@JoinTable(
name="t_s",  //表名
	joinColumns={@JoinColumn(name="teacher_id")},//指向teacher表
	inverseJoinColumns={@JoinColumn(name="student_id")}//指向另一张表
		)
生成的表为
create table Student (
        id integer not null auto_increment,
        name varchar(255),
        primary key (id)
)
create table Teacher (
        id integer not null auto_increment,
        name varchar(255),
        primary key (id)
)
create table t_s (//生成的中间表
        teacher_id integer not null,
        student_id integer not null,
        primary key (teacher_id, student_id)
    )
t_s表的两个属性分别references其它表的主键.
XML中
<class name="com.xxx.Teacher">
		<id name="id">
			<generator class="native"/>
		</id>
		<property name="name"/>
		<set name="students" table="t_s">table定义中间表的表名
			<key column="teacher_id"></key>
			<many-to-many class="com.xxx.Student" column="student_id"/>
		</set>
</class>

多对多双向关联
关系举例:老师学生,老师需要知道自己教了哪些学生,学生也知道自己有哪些老师.
数据库:中间表
Annotation:@ManyToMany
XML:<many-to-many>
多对多单向配置只需要在一端进行配置就可以了.
关系模型(Teache多对多Student)
Teacher(id,name,students)多
Student(id,name,teachers)多
Set<Student> students=new HashSet<Student>()
Set<Teacher> teachers = new HashSet<Teacher>();
Annotation配置
在Teacher这一端的students上配置
@ManyToMany
	@JoinTable(name="t_s",
		joinColumns={@JoinColumn(name="teacher_id")},
		inverseJoinColumns={@JoinColumn(name="student_id")}
		)
在Student一端的teachers只需要配置
@ManyToMany(mappedBy="students")
XML配置方式:两端配置一样,注意表名和生成的中间表的字段属性名要一致
Teacher那一端配置
<set name="students" table="t_s">
	<key column="teacher_id"/>
	<many-to-many class="com.xxx.Student" column="student_id"/>
</set>
在Student那一端配置
<set name="teachers" table="t_s">
	<key column="student_id"></key>
	<many-to-many class="com.xxx.Teacher" column="teacher_id"/>
</set>
生成的数据库表和上面是一样的.



CRUD  增删改查   一组group对应多个用户user
cascade 管理增删改：CascadeType.ALL 所有操作都会引起引用的数据库的映射
@ManyToOne(cascade={CascadeType.ALL})
	public Group getGroup() {
		return group;
	}
fetch   管理查：影响 get load 方法
fetch = FetchType.LAZY|EAGER
Hibernate Annotation的默认的FetchType在ManyToOne是EAGER的,在OneToMany上默认的是LAZY.
FetchType.EAGER会取出对方表的数据。如果双方都是EAGER会出现重复取值。




树状结构的设计(重要)
设计思想:数据库模型,面向对象模式,关系映射,CRUD
数据库模型:表(id,name,pid)
实体模型:父结点一对多子结点,一对多/多对一双向关联映射,一个子结点只有一个父结点,一个父结点有多个子结点.
Class Org
private int id;
	private String name;
	private Set<Org> children = new HashSet<Org>();
	private Org parent;
关系映射:在同一个类中使用@ManyToOne和@OneToMany
在父结点parent上
@ManyToOne   
@JoinColumn(name="parent_id")
public Org getParent() {
		return parent;
	}
在子结点children上
@OneToMany(cascade=CascadeType.ALL, mappedBy="parent")
public Set<Org> getChildren() {
		return children;
	}


基本关联关系对象的CRUD
1 想删除或者更新,先做load,除非精确知道ID.
2 Cascade属性(是一个数组)管理CUD,只是为了编程的方便,不要把它的功能看得太大.
cascade={CascadeType.ALL} 
CascadeType取值
ALL    Cascade all operations所有情况
MERGE  Cascade merge operation合并(merge=save+update)
PERSIST  Cascade persist operation存储 persist()
REFRESH  Cascade refresh operation刷新
REMOVE   Cascade remove operation删除
规律,双向关系要是程序中设定双向关联.还要设置mappedBy
3 Fetch属性,读取,管理R(Retrieve查询) 
fetch = FetchType.LAZY|EAGER
Hibernate Annotation的默认的FetchType在ManyToOne是EAGER的,在OneToMany上默认的是LAZY.
如果指定OneToOne的fetch=FetchType.LAZY,会延迟对关联对象的加载,不管是load还是get.
在XML中,在外键属性上设置inverse=”true|false”进行设置.
4 如果想消除关联关系,先设定关系为NULL就可以打破关联关系,再删除对应记录.如果不删除相关的记录,该记录就成为垃圾数据.
5 O/R Mapping编程模型
A 映射模型(映射数据库)
	1 JPA ANNOTATION
	2 hibernate annotation extension
	3 hibernate xml
	4 jpa xml
B 编程接口(增删改查)
	1 jpa
	2 hibernate
C 数据库查询语言
	1 HQL
	2 EJBQL (JPQL)
6 

集合映射(不太重要)
Set
List
@OrderBy(“属性名 ASC|DESC”)
//desc是降序，asc是升序（默认）
	@OneToMany(mappedBy="survey")
	@OrderBy("id desc")   //id是page的id
	public Set<Page> getPages() {
		return pages;
	}
Map
	@MapKey(name=”字段名”)
private Map<Integer, User> users = new HashMap<Integer, User>();

继承映射(不太重要)
三种模式:TABLE_PER_CLASS| JOINED| SINGLE_TABLE
strategy=InheritanceType.XXX
关系模式就是一种方式,在数据库的表有三种表现形式.
SINGLE_TABLE模式
Person, Student和Teacher继承Person
Person(id,name)
Student(score)
Teacher(title)
在Student实体中
@Entity
@DiscriminatorValue("student")
在Teacher中
@Entity
@DiscriminatorValue("teacher")
在Person中
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="discriminator",discriminatorType=
DiscriminatorType.STRING)
@DiscriminatorValue("person")
生成的表为
    create table Person (
        discriminator varchar(31) not null,
        id integer not null auto_increment,
        name varchar(255),
        score integer,
        title varchar(255),
        primary key (id)
    )
TABLE_PER_CLASS模式
这种方式也比较麻烦,注意id生成策略.
Person, Student和Teacher继承Person
Person(id,name)
Student(score)
Teacher(title)
在Person中
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@TableGenerator(//ID生成策略采用表生成策略
		name="t_gen",
		table="t_gen_table",
		pkColumnName="t_pk",
		valueColumnName="t_value",
		pkColumnValue="person_pk",
		initialValue=1,
		allocationSize=1
		)
主键映射
@Id
@GeneratedValue(generator="t_gen", strategy=GenerationType.TABLE)
生成了三张表+一张生成主键的临时表
create table Person (
        id integer not null,
        name varchar(255),
        primary key (id)
)
create table Student (
        id integer not null,
        name varchar(255),
        score integer not null,
        primary key (id)
    )
create table Teacher (
        id integer not null,
        name varchar(255),
        title varchar(255),
        primary key (id)
    )
create table t_gen_table (
         t_pk varchar(255),
         t_value integer 
    )
JOINED模式
这种方式最为简单.
Person, Student和Teacher继承Person
Person(id,name)
Student(score)
Teacher(title)
在Person中
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
生成的表为:
create table Person (
        id integer not null auto_increment,
        name varchar(255),
        primary key (id)
    )
create table Student (
        score integer not null,
        id integer not null,
        primary key (id)
    )
create table Teacher (
        title varchar(255),
        id integer not null,
        primary key (id)
    )
两个外键约束表Student和Teacher里面的id分别references Person(id)


Query query=se.createQuery("from Topic t where not exists (select r.id from Reply r where r.topic.id=t.id)");


Hibernate查询(HQL)
QL(Query Language)
NativeSQL>HQL>EJBQL(JP QL1.0)>QBC(Query by Criteria)>
QBE(Query by Example)
EJBQL是HQL的一个子集.
没必要把EJBQL和HQL区分得特别清楚,能够满足需求和设计.
QL和导航关系结合,共同为查询提供服务.

:占位符
Query q = session.createQuery("from Category c where c.id > :min and c.id < :max");
q.setParameter("min", 2);
q.setParameter("max", 8);
或者q.setInteger("min", 2);
q.setInteger("max", 8);
类似于JDBC里面的PreparedStatement

链式编程
Query q = session.createQuery("from Category c where c.id > :min and c.id < :max")
	.setInteger("min", 2)
	.setInteger("max", 8);

Query q = session.createQuery("from Category c where c.id > ? and c.id < ?");
q.setParameter(0, 2)
.setParameter(1, 8);

分页显示
Query q = session.createQuery("from Category c order by c.name desc");
q.setFirstResult(2);//设置起始记录位置.
q.setMaxResults(4);//设置每页显示的最大记录数

q.uniqueResult()//返回唯一的一条记录.

Is null
Is empty 测试集合是否为空

NamedQueries 命名查询
把查询语句集中放在一个位置中.
SQLQuery q = session.createSQLQuery("select * from category limit 2,4").addEntity(Category.class);


使用数据库本


性能优化
1 注意session.clear()的运用,尤其是不断分页循环的时候
  A 在一个大集合中进行遍历,取出其中含有敏感字的对象
  B 另一种形式的内存泄露.
  
2 1+N问题
取一个对象的数据，这个对象关联另一个对象，与它是many-to-one ,取many对象的数据会把one对象的数据取一遍。
因为many对象默认是FetchType.EAGER
		// 1 -N 问题是指取topic的数据，会把category的数据查询一遍。
		//因为topic-category 是多对一。topic默认是FetchType.EAGER
方法		
 LAZY ,
 @ManyToOne(fetch=FetchType.LAZY)
	public Category getCategory() {
		return category;
	}
	
 BatchSize  
@Entity
@BatchSize(size=5)
public class Category {
 ,
 

 join fetch  List<Topic> ts=(List<Topic>)se.createQuery("from Topic t left join fetch t.category c").list();
 
 Hibernate中，left join、inner join以及left join fetch区别
 Select <要查询的字段> From <Left 资料表> 
　　<Left | Right> [Outer] Join <Right 资料表> On <Join 规则> 
LEFT JOIN返回”left_table”中所有的行尽管在” right_table”中没有相匹配的数据。 
RIGHT JOIN返回”right_table”中所有的行尽管在”left_table”中没有相匹配的数据。 
INNER JOIN返回的结果集是两个表中所有相匹配的数据。 
Fetch: 
在我们查询Parent对象的时候，默认只有Parent的内容，并不包含childs的信息，如果在Parent.hbm.xml里设置lazy="false"的话才同时取出关联的所有childs内容. 
问题是我既想要hibernate默认的性能又想要临时的灵活性该怎么办？  这就是fetch的功能。我们可以把fetch与lazy="true"的关系类比为事务当中的编程式事务与声明式事务,不太准确，但是大概是这个意思。 
Query q = session.createQuery("from Parent as parent "+" left outer join fetch parent.childs " +" where parent.id = :id"); 
总之，fetch就是在代码这一层给你一个主动抓取得机会. 
可以在lazy="true"的情况下把fetch去掉，就会报异常. 当然，如果lazy="false"就不需要fetch了
 
 
3list和iterator的区别
list取所有
 iterator先取ID,等到要用的时候再根据ID取出对象.
 session中list第二次发出,仍会到数据库中查询数据.iterator第二次首先查找session级缓存.

4一级缓存和二级缓存和查询缓存
A 缓存
B 一级缓存,session级别的缓存
C 二级缓存,SessionFactory级别的缓存,可以跨越session级别存在.
适合放二级缓存：
经常被访问,改动不大不会经常访问,数量有限.如：用户权限，组织机构
D 打开二级缓存
 Hibernate.cfg.xml
<property name="cache.use_second_level_cache">true</property>
使用ehcache
JAR文件:\lib\optional ehcache-1.2.3.jar
    <!-- Second-level caching -->
	    <property name="cache.use_second_level_cache">true</property>
		<property name="cache.provider_class">org.hibernate.cache.EhCacheProvider</property>
		<property name="cache.use_query_cache">true</property>
使用配置文件ehcache.xml,将其复制到src目录下.
Annotation注解:@Cache 
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cache(
    CacheConcurrencyStrategy usage();                 (1)
    String region() default "";                       (2)
    String include() default "all";                   (3)
)
(1)	usage: 给定缓存的并发策略(NONE, READ_ONLY, NONSTRICT_READ_WRITE, READ_WRITE, TRANSACTIONAL)
(2)	region (可选的)：缓存范围(默认为类的全限定类名或是集合的全限定角色名)
(3)	include (可选的)：值为all时包括了所有的属性(proterty), 为non-lazy时仅含非延迟属性(默认值为all)
E load默认使用二级缓存,iterator默认使用二级缓存
F list默认向二级缓存添加数据,但是查询的时候不使用.

G 如果Query需要使用二级缓存,则打开查询缓存
<property name="cache.use_query_cache">true</property>
需要调用Query setCachable(true)方法指明使用二级缓存.




缓存算法
LRU,LFU, FIFO
Least Recently Used
Least Frequently Userd
First In First Out
memoryStoreEvictionPlicy=”LRU”(ehcache)

事务：要执行一起执行，不执行一起不执行。
ACID
原子性(Atomicity）、一致性（Consistency）、隔离性（Isolation）、持久性（Durability）。
  当事务处理系统创建事务时，将确保事务有某些特性。组件的开发者们假设事务的特性应该是一些不需要他们亲自管理的特性。这些特性称为ACID特性。
ACID就是：原子性(Atomicity )、一致性( Consistency )、隔离性( Isolation)和持久性(Durabilily)。

数据库的事务隔离机制
1：read-uncommitted  2：read-committed  4：repeatable read  8：serializable（数字代表对应值）

解决repeatable read的问题（依赖于数据库的锁）。并发和效率的平衡 
悲观锁  Acount a=(Acount)se.get(Acount.class,1, LockMode.UPGRADE);
乐观锁  @Version
	public int getVersion() {
		return version;
	}
