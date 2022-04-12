# mybatis笔记

> 核心：输入映射和输出映射

## 1.配置文件
```xml
新建java project 导入jar  写配置文件

SqlMapConfig.xml
	<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE configuration
	PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
	"http://mybatis.org/dtd/mybatis-3-config.dtd">
	<configuration>
		<!-- <properties resource=""></properties> -->
		<environments default="development">
			<environment id="development">
				<transactionManager type="JDBC" />
				<dataSource type="POOLED">
					<property name="driver" value="com.mysql.jdbc.Driver" />
					<property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=false" />
					<property name="username" value="root" />
					<property name="password" value="root" />
				</dataSource>
			</environment>
		</environments>
		
		<!-- 配置要加载的mapper.xml -->
		<mappers>
		 <mapper resource="sqlmap/UserMapper.xml"/>
		 
		</mappers>
	</configuration>
```

```properties
log4j.properties
	# Global logging configuration
	log4j.rootLogger=DEBUG, stdout
	# Console output...
	log4j.appender.stdout=org.apache.log4j.ConsoleAppender
	log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
	log4j.appender.stdout.layout.ConversionPattern=%5p [%t] - %m%n

	
	
		String resource="SqlMapConfig.xml";
		InputStream inputStream=Resources.getResourceAsStream(resource);
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession=sessionFactory.openSession();
		
		//查找
		User user=sqlSession.selectOne("test.FindUserById",10);
		System.out.println("user= "+user);
		
		
		//更新 要指明id
		User user_update=new User();
		user_update.setId(20);
		user_update.setUsername("宋江2");
		user_update.setAddress("梁山2");
		user_update.setBirthday(new Date());
		user_update.setDetail("首领2");
		user_update.setScore(66.7f);
		user_update.setSex("1");
		sqlSession.insert("test.insertUser", user_update);
		sqlSession.commit();
```


```xml
UserMapper.xml	
	<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE mapper
	PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
	"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

	<mapper namespace="test">

		<!-- 根据用户id查询用户信息  编写statement
		parameterType：指定传入数据参数的类型
		resultType：映射生成的java对象类型，select查询结果集的列名要和resultType指定java对象的属性名保持一致才可以映射
		#{}:表示占位符,如果要传递简单类型数据，#{}可以写任意名称
		 -->
		 <select id="FindUserById" parameterType="java.lang.Integer"
		  resultType="com.mybatis.po.User">
		  SELECT * FROM USER WHERE ID=#{VALUE}
		 </select>
		 
		  <!-- 用户插入  传用户类型的对象(user), 传入的是user对象，#{}中写user对象的属性名  -->
		 <insert id="insertUser" parameterType="com.mybatis.po.User">
			<!-- 将自增主键返回到user对象
		  keyProperty：返回到user对象中的属性名
		 order：返回主键的时机
		 LAST_INSERT_ID:通过此函数获取自增主键值
		  -->
			<selectKey keyProperty="id" order="AFTER" resultType="java.lang.Integer">
				SELECT LAST_INSERT_ID();
			</selectKey>
		 
			insert into user(username,birthday,sex,address,detail,score) values(
			#{username},#{birthday},#{sex},#{address},#{detail},#{score})
		 </insert>
		 
		 <!-- 删除 传入数据 java.lang.Integer -->
		 <delete id="deleteUserById" parameterType="java.lang.Integer">
			DELETE FROM USER WHERE ID=#{ID}
		 </delete>
		 
		 <!-- 更新  传入参数 com.mybatis.po.User -->
		 <update id="updateUser" parameterType="com.mybatis.po.User">
		 update user set username=#{username},birthday=#{birthday},sex=#{sex},address=#{address},detail=#{detail},score=#{score}
		 where id=#{id}
		 </update>
	 </mapper>
```

```xml	
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<!-- properties -->
	<properties resource="db.properties"></properties> 
	<!-- 自定义别名 -->
	<typeAliases>
	 <!--  <typeAlias type="com.mybatis.po.User" alias="user"/> -->
	 <!--批量别名定义，扫描整个包下的类 -->
	  <package name="com.mybatis.po"/>
	</typeAliases>
	
	<environments default="development">
		<environment id="development">
			<transactionManager type="JDBC" />
			<dataSource type="POOLED">
				<property name="driver" value="${jdbc.driver}" />
				<property name="url" value="${jdbc.url}" />
				<property name="username" value="${jdbc.username}" />
				<property name="password" value="${jdbc.password}" />
			</dataSource>
		</environment>
	</environments>
	
	<!-- 配置要加载的mapper.xml -->
	<mappers>
		 <mapper resource="sqlmap/UserMapper.xml"/>
		 <!-- <mapper resource="sqlmap/mapper/UserMapper.xml"/> -->
		 
		<!-- 接口与xml文件名称一致，且在同一个目录。接口即mapper命名空间 -->
		<!-- <mapper class="com.mybatis.dao.mapper.UserMapper"/> -->
		
	    <!-- 注册指定包下的所有mapper接口。mapper接口和mapper映射文件名称相同且在同一个目录 -->
		<package name="com.mybatis.dao.mapper" />
	</mappers>
</configuration>
```

## 2.SqlSession

``` 
SqlSession
	SqlSession中封装了对数据库的sql操作，如：查询、插入、更新、删除等。
	通过SqlSessionFactory创建SqlSession，而SqlSessionFactory是通过SqlSessionFactoryBuilder进行创建。

	SqlSessionFactoryBuilder
	SqlSessionFacoty是通过SqlSessionFactoryBuilder进行创建，SqlSessionFactoryBuilder只用于创建SqlSessionFactory，可以当成一个工具类，在使用时随时拿来使用不需要特殊处理为共享对象。

	SqlSessionFactory
	SqlSessionFactory是一个接口，接口中定义了openSession的不同方式，SqlSessionFactory一但创建后可以重复使用，实际应用时通常设计为单例模式。

	SqlSession
	SqlSession是一个接口，默认使用DefaultSqlSession实现类，sqlSession中定义了数据库操作。
	

namespace的作用(mapper接口地址即namespace，接口的方法即mapper的id，参数、返回值都相同)
	使用mapper接口不用写接口实现类即可完成数据库操作，简单方便，此方法为官方推荐方法。
	使用mapper接口调用必须具备如下条件：
	1、	Mapper接口方法名和mapper.xml中定义的每个sql的id相同
	2、	Mapper接口方法的输入参数类型和mapper.xml中定义的每个sql 的parameterType的类型相同
	3、	Mapper接口方法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同
	4、	Mapper.xml文件中的namespace即是mapper接口的类路径。

	至此，mybatis的mapper包括mapper.xml和mapper接口两种文件。

```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- 接口即mapper命名空间，接口方法即mapper的id。利用动态代理产生对象 -->
<mapper namespace="com.mybatis.dao.mapper.UserMapper">
<!-- sql片段，以表为单位进行抽取 -->
<sql id="query_user_where">
	<if test="ids !=null">
    		<!-- <foreach collection="ids" index="index" item="item" open="and id in(" 
    		separator="," close=")">
    	      #{item}
    		</foreach> -->
    		<foreach collection="ids" index="index" item="item" open="and (" 
    		separator="or" close=")">
    	      id=#{item}
    		</foreach>
	     	
    	</if>
      <if test="username !=null and username!=''">
    	  and username like '${username}%'
      </if>
      <if test="sex !=null and sex !=''">
      	 and sex=#{sex}
      </if>
      <if test="id >0">
      	 and id>#{id}
      </if>
</sql>
   
    <!-- 根据用户id查询用户信息  编写statement
    parameterType：指定传入数据参数的类型
    resultType：映射生成的java对象类型，select查询结果集的列名要和resultType指定java对象的属性名保持一致才可以映射
    #{}:表示占位符,如果要传递简单类型数据，#{}可以写任意名称
     -->
     <select id="findUserById" parameterType="java.lang.Integer"
      resultType="user">
      SELECT * FROM USER WHERE ID=#{VALUE}
     </select>
     
      <!-- 查询用户列表,根据用户名称模糊查询
     ${}:将传参数不加任何修饰将传的值拼接在sql中
      -->
     <select id="findUserList" parameterType="String" resultType="user">
    select * from user where username like '${value}%' 

     </select>
     
     <select id="findUserByUser" parameterType="user" resultType="user">
     <!-- 	select * from user where username like '${username}%' and sex=#{sex}  -->
     	select * from user
	     <where>
	     	<include refid="query_user_where"></include>
	     </where>
     </select>
     
       <!--   返回resultMap -->
     <select id="findUserResultMap" parameterType="user" resultMap="userListMap">
     	select username username_,sex sex_,birthday from user
     	<where>
     		<include refid="query_user_where"></include>
     	</where>
     </select>
     
     <!-- 定义resultMap -->
     <resultMap type="user" id="userListMap">
     	<id column="username_" property="username"/>
     	<result column="sex_" property="sex"/>
     	<result column="birthday_" property="birthday"/>
     </resultMap>
     
     <select id="findUserByMap" parameterType="hashMap" resultType="user">
     	select * from user where username like '${username}%' and sex=#{sex} 
     </select>
     
     <select id="selectUserCount" parameterType="user" resultType="int">
     <!-- 	select count(*) from user where username like '${username}%' and sex=#{sex}  -->
     select count(*) from user 
     <where>
     	<include refid="query_user_where"></include>
     </where>
     </select>
     
     <!-- 传入user，返回map -->
     <select id="findUserByUserReturnMap" parameterType="user" resultType="hashMap">
     	select * from user where username like '${username}%' and sex=#{sex}
     </select>
      <!-- 用户插入  传用户类型的对象(user), 传入的是user对象，#{}中写user对象的属性名  -->
     <insert id="insertUser" parameterType="com.mybatis.po.User">
        <!-- 将自增主键返回到user对象
      keyProperty：返回到user对象中的属性名
     order：返回主键的时机
     LAST_INSERT_ID:通过此函数获取自增主键值
      -->
     	<selectKey keyProperty="id" order="AFTER" resultType="java.lang.Integer">
     		SELECT LAST_INSERT_ID();
     	</selectKey>
     
     	insert into user(username,birthday,sex,address,detail,score) values(
     	#{username},#{birthday},#{sex},#{address},#{detail},#{score})
     </insert>
     
     <!-- 删除 传入数据 java.lang.Integer -->
     <delete id="deleteUserById" parameterType="java.lang.Integer">
     	DELETE FROM USER WHERE ID=#{ID}
     </delete>
     
     <!-- 更新  传入参数 com.mybatis.po.User -->
     <update id="updateUser" parameterType="com.mybatis.po.User">
     update user set username=#{username},birthday=#{birthday},sex=#{sex},address=#{address},detail=#{detail},score=#{score}
     where id=#{id}
     </update>
 </mapper>
```

## 3.一对多查询

```xml
 <!-- user-orders 一对一查询，association -->
<resultMap type="com.mybatis.po.Orders" id="ordersUserMap">
	 <!-- id 是主键，不会重复。
	 即sql语句一对多会查询出id重复的语句
	 但是mybatis优化后，一对多的一当中的orderdetails的不会重复-->
	<id column="id" property="id"/>
	<result column="user_id" property="user_id"/>
	<result column="order_number" property="order_number"/>
	<!-- 一对一 association 使用javaType -->
	<association property="user" javaType="com.mybatis.po.User">
		<id column="user_id" property="id"/>
		<result column="username" property="username"/>
		<result column="sex" property="sex"/>
		<result column="birthday" property="birthday"/>
	</association>
</resultMap>

<!-- orders-orderdetail 一对多查询 -->
<resultMap type="com.mybatis.po.Orders" id="ordersUserOrderdetailResultMap" extends="ordersUserMap"> 
<!-- <resultMap type="java.util.LinkedHashMap" id="ordersUserOrderdetailResultMap">  -->
	<!-- 	
		一对多查询 。使用collection ofType
       property="orderdetails"：关联查询的结果集存储在Orders上哪个属性。
	ofType="com.mybatis.po.Orderdetail"：指定关联查询的结果集中的对象类型即List中的对象类型。 
	-->
	<collection property="orderdetails" ofType="com.mybatis.po.Orderdetail">
		<id column="orderdetail_id" property="id"/>
		<result column="item_id" property="item_id"/>
		<result column="item_num" property="item_num"/>
		<result column="item_price" property="item_price"/>
	</collection>
	
</resultMap>

<!-- Orders-Items 多对多 -->
<resultMap type="com.mybatis.po.Orders" id="ordersItemsResultMap"  extends="ordersUserMap"> 
	<!-- 	
		一对多查询 。使用collection ofType
       property="orderdetails"：关联查询的结果集存储在Orders上哪个属性。
	ofType="com.mybatis.po.Orderdetail"：指定关联查询的结果集中的对象类型即List中的对象类型。 
	-->
	<collection property="orderdetails" ofType="com.mybatis.po.Orderdetail">
		<id column="orderdetail_id" property="id"/>
		<result column="item_id" property="item_id"/>
		<result column="item_num" property="item_num"/>
		<result column="item_price" property="item_price"/>
		
		<!-- Orderdetail-Items 一对一 -->
		<association property="items" javaType="com.mybatis.po.Items">
		<id column="item_id" property="id"/>
		<result column="item_price_" property="item_price"/>
		<result column="item_name" property="item_name"/>
		<result column="item_detail" property="item_detail"/>
		</association>
	</collection>
</resultMap>
```

## 4.缓存

```
<!-- 延迟加载 -->
<resultMap type="orders" id="oredersMap">
	<id column="id" property="id"/>
	<result column="user_id" property="user_id"/>
	<result column="order_number" property="order_number"/>
	<association property="user" javaType="user"
	select="com.mybatis.dao.mapper.UserMapper.findUserById" column="user_id"></association>
</resultMap>


	<!-- properties -->
	<properties resource="db.properties"></properties> 
	<!-- 延迟加载 -->
	<settings>
		<setting name="lazyLoadingEnabled" value="true"/>
		<setting name="aggressiveLazyLoading" value="false"/>
	</settings>
	
	
二级缓存：
	二级缓存范围可以跨越session，在mapper.xml中同一个namespace范围使用二级缓存。

	配置：
	通过配置开启二级缓存。

	<!-- 开启二级缓存 -->
	<setting name="cacheEnabled" value="true"/>

	还需要在mapper.xml配置，表示要对该mapper.xml进行二级缓存。
	在mapper.xml配置：
	 <cache />

	对映射的pojo，实现序列化接口。
	应用场景（重点）：	1、针对复杂的查询或统计的功能，用户不要求每次都查询到最新信息，使用二级缓存，通过刷新间隔flushInterval设置刷新间隔时间，由mybatis自动刷新。
	比如：实现用户分类统计sql，该查询非常耗费时间。
	将用户分类统计sql查询结果使用二级缓存，同时设置刷新间隔时间：flushInterval（一般设置时间较长，比如30分钟，60分钟，24小时，根据需求而定）
 
	2、针对信息变化频率高，需要显示最新的信息，使用二级缓存。
    将信息查询的statement与信息的增、删、改定义在一个mapper.xml中，此mapper实现二级缓存，当执行增、删、修改时，由mybatis及时刷新缓存，满足用户从缓存查询到最新的数据。
	比如：新闻列表显示前10条，该查询非常快，但并发大对数据也有压力。 
	将新闻列表查询前10条的sql进行二级缓存，这里不用刷新间隔时间，当执行新闻添加、删除、修改时及时刷新缓存。
```


​	