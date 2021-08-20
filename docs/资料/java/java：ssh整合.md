

ssh整合：
导入jar；
修改web.xml；
关键在struts.xml中，action的class不再是实际的包中的class，而是spring中bean的映射，且scope="prototype"，否则在多线程中数据出问题。
struts.xml  ---  <action name="userAction" class="userAction">
bean.xml    ---  <bean name="userAction" class="com.test.register.action.UserAction"  scope="prototype">

如下：
struts.xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE struts PUBLIC
	"-//Apache Software Foundation//DTD Struts Configuration 2.3//EN"
	"http://struts.apache.org/dtds/struts-2.3.dtd">

<struts>
     <constant name="struts.devMode" value="true"></constant>

     <package name="register" namespace="/" extends="struts-default">
			<!-- <action name="userAction" class="com.test.register.action.UserAction"> -->
			<action name="userAction" class="userAction">
				<result name="success">/registerSuccess.jsp</result>
				<result name="fail">/registerFail.jsp</result>
			</action>
		
     </package>
    
</struts>

beans.xml
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans 
           http://www.springframework.org/schema/beans/spring-beans-2.5.xsd
           http://www.springframework.org/schema/context
           http://www.springframework.org/schema/context/spring-context-2.5.xsd
           http://www.springframework.org/schema/aop
           http://www.springframework.org/schema/aop/spring-aop-2.5.xsd
           http://www.springframework.org/schema/tx 
           http://www.springframework.org/schema/tx/spring-tx-2.5.xsd">
           
     <!--spring 注释配置  annotation_configuration(@compoment & @resouce)  -->
	<!--  <context:annotation-config></context:annotation-config>
	<context:component-scan base-package="com.register"></context:component-scan>  -->
	
	<!--spring xml配置 xml_configuration -->
		<!-- hibernateTemplate -->	
		<bean id="hibernateTemplate" class="org.springframework.orm.hibernate3.HibernateTemplate">
  			<property name="sessionFactory" ref="sessionFactory"></property>
  		</bean>
  		
		<bean name="userDaoImpl" class="com.test.register.userdao.impl.UserDaoImpl">
			<property name="hibernateTemplate" ref="hibernateTemplate"></property>
		</bean>	
			
		<bean name="userServiceImpl" class="com.test.register.userservice.impl.UserServiceImpl">
			<property name="userDao" ref="userDaoImpl"></property>
		</bean>
		
		<bean name="userAction" class="com.test.register.action.UserAction"  scope="prototype">
			<property name="us" ref="userServiceImpl"></property>
		</bean> 
		
	<!-- aop对象 aspect_object -->
	<!--  <bean id="logUser" class="com.spring.aspect.LogUser"></bean> -->
	 
	<!-- aop 注释配置 aop_annotation_configuration(@aspect_@pointcut_@before_@around) -->
	 <!--   <aop:aspectj-autoproxy></aop:aspectj-autoproxy>    -->
	 
	<!--aop xml配置 aop_xml_configuration -->
	<!-- <aop:config>
		<aop:aspect id="LogUserAspect" ref="logUser">
		<aop:pointcut expression="execution (public * com.spring.dao..*.*(..))" id="daoSave"/>
			<aop:before method="beforeDaoImpl1Save" pointcut="execution(public * com.spring.dao..*.save(..))"/>
			<aop:around method="aroudDaoSave" pointcut-ref="daoSave"/>
		</aop:aspect>
	</aop:config> -->
		
	<!-- spring dataSouce 数据库连接-->
	<bean id="ds" class="org.apache.commons.dbcp.BasicDataSource"
		destroy-method="close">
		<property name="driverClassName" value="com.mysql.jdbc.Driver" />
		<property name="url" value="jdbc:mysql://localhost:3306/spring?useSSL=false" />
		<property name="username" value="root" />
		<property name="password" value="root" />
	</bean>
	
	<!-- spring & hibernate -->
	 <bean id="sessionFactory" class="org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean">
	  <property name="dataSource" ref="ds" />
	  <property name="packagesToScan">
	  	<list><value>com.test.register.model</value></list>
	  </property>
	  
	  <property name="hibernateProperties"> 
		<props>
			<prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
			<prop key="hibernate.show_sql">true</prop>
			<prop key="hibernate.hbm2ddl.auto">update</prop>
		</props>
	  </property>
	</bean> 

		<!--  事务管理 transaction sessionFactory -->
		<bean id="txManager" class="org.springframework.orm.hibernate3.HibernateTransactionManager">
  			<property name="sessionFactory" ref="sessionFactory" />
		</bean>
		
		 <!-- 事务管理 注释配置 (@Transactional) -->
		<!--	<tx:annotation-driven transaction-manager="txManager"/> -->
		
		<!-- 事务管理 xml配置 -->
		 <tx:advice id="txAdvice" transaction-manager="txManager">
			  <tx:attributes>
			    <tx:method name="exists" read-only="true"/>
			    <tx:method name="add*" propagation="REQUIRED"/>
			  </tx:attributes>
		  </tx:advice>
		  <aop:config>
			  <aop:pointcut id="serviceAddTransaction" expression="execution(public * com.test.register.service..*.*(..))"/>
			  <aop:advisor advice-ref="txAdvice" pointcut-ref="serviceAddTransaction"/>
		  </aop:config> 
		
  
</beans>


web.xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" version="3.0">
  <display-name></display-name>
  <welcome-file-list>
    <welcome-file>index.jsp</welcome-file>
  </welcome-file-list>
  
  	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
		<!-- default: /WEB-INF/applicationContext.xml -->
	</listener>

	<context-param>
		<param-name>contextConfigLocation</param-name>
		<!-- <param-value>/WEB-INF/applicationContext-*.xml,classpath*:applicationContext-*.xml</param-value>  -->
		<param-value>classpath:beans.xml</param-value>
	</context-param>
	
  <filter>
    <filter-name>struts2</filter-name>
    <filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>struts2</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
</web-app>