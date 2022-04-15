# servlet-jsp学习

```java
servlet、jsp学习教程：myeclipse 10 + tomcat 7  --2016

********** 一、安装tomcat后测试方法
********** 二、修改tomcat端口
********** 三、servlet的HelloWord
********** 四、如何引入tomcat的servlet的jar包
********** 五、把servlet布署到tomcat中（--重要--）
********** 六、cookie
********** 七. session
********** 八、application
********** 九、包中的类的配置方式
********** 十、javabean 
********** 十一、连接数据库时导入jar包
********** 十二、jsp学习
********** 十三、jsp的taglib 标签库

http 代码含义  200 ok    404 找不到网页    403 禁止访问    500 服务器内部错误
myeclipse tomcat port already use in ： myeclipse的tomcat正在使用：
启启动任务管理器--进程--javaw.exe结束进程

```

## Ⅰ、tomcat

### 一、安装tomcat后测试方法

```
cmd命令行中进入安装目录如
C:\Users\x>g:
G:\>cd tomcat-7\bin
G:\tomcat-7\bin>startup 

startup                                      --打开tomcat
浏览器中输入网址http://localhost:8080        --出现tomcat官网及其版本
shutdown                                     --关闭tomcat
```

### 二、修改tomcat端口

```
在安装目录G:\tomcat-7\conf中，打开server.xml文件（备份一个）
找到    <Connector port="8080" protocol="HTTP/1.1"
将8080修改掉，如 <Connector port="8888" protocol="HTTP/1.1" 即可
```

### 三、servlet的HelloWord

```
1.建立Dynamic Web Project项目
      Target runtime是安装好的tomcat
2.在Package Explorer窗口下，src目录下新建Servlet文件
3.在WebContent目录新建jsp文件
（servlet类文件要放在tomcat的classes目录下 如：G:\tomcat-7\webapps\test1\WEB-INF\classes，需要布署；
jsp源文件放在tomcat的根目录下 如：G:\tomcat-7\webapps\test1，不需布署。）
```

### 四、如何引入tomcat的servlet的jar包

```
右键项目--Build Path-- Add External Archives --tomcat目录lib目录下的servlet-api.jar
```

### 五、把servlet布署到tomcat中

```
把servlet布署到tomcat中（通过浏览器地址访问myeclipse编译的servlet文件）：
(布署servlet步骤：复制类文件到tomcat目录，配置web.xml,reloading tomcat。具体如下三步）

····一.建立webapplication（即通过tomcat的server打开网页）
1.在tomcat安装包下的webapps目录建立测试目录，如test1，此目录下建立WEB-INF目录，此目录下建立web.xml文件，符合标准（可在tomcat其他目录复制再删除不要的）如：

<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" version="2.5">
</web-app>

2.在tomcat的bin目录下打开tomcat，双击startup打开tomcat。
3.在测试目录test在添加html文件。如divTest.html。或者文件夹。
4.在浏览器中输入http://localhost:8080/test1/divTest.html即可通过web访问网页
```

```
····二.建立servlet文件

1.在建立的Dynamic Web Project项目src目录下建立class文件
2.继承HttpServlet
3.重写doGet(),doPost()方法

如下：
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//doGet是客户化用get方式访问servlet的调用此方法，在地址栏敲地址就是get方式。表单提交方式是post调用doPost
		//HttpServletRequest 封装客户端到服务端的请求信息。
		//HttpServletResponse 封装服务端发送到客户端的信息。
		System.out.println("doGet");
	}	
}
```

```
····三.把servlet放到tomcat中（通过浏览器地址访问）

先在tomcat的bin目录下双击startup打开tomcat
1.建立classes目录
参考 建立webapplication（即通过tomcat的server打开网页）在test1目录下的WEB-INF目录下建立classes目录以放置编译好的类文件

2.复制类文件
在myeclipse中菜单栏window--show view打开navigator窗口查看编译好的类文件，在项目--WebContent--WEB-INF--classes,将类文件HelloWorldServlet.class复制到第一步的test1下的classe目录

3.编写映射（配置，布署）并查看tomcat后台有无Reloading
打开WEB-INF下的web.xml添加映射信息
如下：

<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" version="2.5">
 <servlet>
    <servlet-name>HW</servlet-name>
    <servlet-class>HelloWorldServlet</servlet-class>
  </servlet>

  <servlet-mapping>
    <servlet-name>HW</servlet-name>
    <url-pattern>/HWS</url-pattern>
  </servlet-mapping>
</web-app>

映射信息说明：
<servlet>  
    <servlet-name>HW</servlet-name>        
		//servlet名字，随便取
    <servlet-class>HelloWorldServlet</servlet-class>  
		//对应classes目录的类文件，必须相同
  </servlet>

  <servlet-mapping>
    <servlet-name>HW</servlet-name>
		//映射，与<servlet-name>的名字相同
    <url-pattern>/HWS</url-pattern>
		//相对于webapplication根目录的地址，此处的根目录是test1
		//名字前必须有反斜杠/，指根目录下的文件
  </servlet-mapping>

4.验证
地址栏输入：http://localhost:8080/test1/HWS 查看tomcat有无打印doGet
```

```java
5.修改servlet文件重新放到tomcat中：
修改文件，如下：
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//doGet是客户化用get方式访问servlet的调用此方法，在地址栏敲地址就是get方式。表单提交方式是post调用doPost
		//HttpServletRequest 封装客户端到服务端的请求信息。
		//HttpServletResponse 封装服务端发送到客户端的信息。
		System.out.println("doGet");
		resp.getWriter().write("<p> servlet file </>");
		//发送类获取输出流，打印文件到客户端
	}	
}

重复2，3，4步骤：复制类文件将原来的覆盖掉，查看Reloading,输入查看
浏览器出现：servlet file 
成功。

注意：
1.修改servlet文件后需要保存后再把类文件复制到tomcat的classes目录。
2.复制类文件后，一定要等tomcat重新Reloading，如果速度慢可以在web.xml按
Ctrl+s重新保存。或者重启tomcat，shutdown，startup。
```

```
G:\apache-tomcat-8.0.35\bin 
<font color='red' size='5'>
	<a href="../Hydrangeas.jpg">上层目录&nbsp;&nbsp;  ../Hydrangeas.jpg</a><br/>
	<a href="../../3.html">上上层目录&nbsp;&nbsp;  ../../3.html</a><br/>
	</font>

	<a href="./3.html">当前目录&nbsp;&nbsp;  ./3.html</a><br/>
	<a href="3.html">当前目录&nbsp;&nbsp;  3.html</a><br/>
	<hr size=8/ color='black'>
	<a href="h/4.html">下层目录&nbsp;&nbsp; h/4.html</a><br/>
	<a href="href/4.html">下层目录&nbsp;&nbsp;href/4.html</a><br/>
```

```

安装配置好tomcat
在tomcat的webapps目录下建立测试目录test
在test下建立WEB-INP目录，新建符合标准的web.xml文件
则test目录下的文件可以做到远程访问的结果
如http://localhost:8080/test/table.html

```

```
测试servlet方法：(每次修改servlet文件都要观察tomcat后台有没有刷新，每次测试最好删除浏览器的cookie）
	1.编程servlet程序（修饰符要写为public。不能是默认的。）
	2.在tomcat的安装目录webapps目录下新建test目录
	3.在test下建立WEB-INP目录，新建符合标准的web.xml文件
	4.将程序的class文件(可通过myeclipse的Navigator视图查找)放在tomcat的WEB-INF目录的classes目录下
	5.在WEB-INF的目录下web.xml配置参数(观察tomcat有没有刷新)
	如
	<servlet>
        <servlet-name>hw1</servlet-name>
        <servlet-class>HWServlet</servlet-class>
    	</servlet>
    
    	<servlet-mapping>
        <servlet-name>hw1</servlet-name>
        <url-pattern>/hw</url-pattern>
    	</servlet-mapping>
	
	<!-- mapping是映射 -->
	6.打开浏览器输入http://localhost:8080/test/hw

```


## Ⅱ、cookie session

### 六、cookie

```js
cookie  小文本-------------------------------------------
记录状态，是服务端写在客户端的小文本文件,是键值对。
Cookie是一个class，要new出来。
Response添加Cookie，可以设置生存周期；Resquest获取Cookie。
--创建cookie的java语句：
Cookie cookie=new Cookie("cookie_key_"+i,"cookie_value_"+i);
resp.addCookie(cookie);

cookie分为两种，设置生存周期，保存在文本；未设置生存周期，保存在内存。
保存在文本的cookie新窗口可以看到；保存在内存的cookie只能在当前窗口及其子窗口（Ctrl+n）可以看到,其他的窗口都看不到。
实验一次后最好清除浏览器的cookie否则会影响效果，因为cookie已保存在客户端。

一个servlet设置的cookie只能被同一个路径下面或者子路劲下面的servlet读到
（路劲指url）。如：
http://localhost:8080/test1/CookieSet   能被
http://localhost:8080/test1/servlet/CookieShow  读取
································
http://localhost:8080/test1/servlet/CookieSet 不能
http://localhost:8080/test1/CookieShow     读取
```

### 七、 session

```
Session  会议-------------------------------------------
记录在服务器
保存当前窗口的数据 Session  键值对，在服务端创建，传递给客户端。
1.通过cookie创建，如果浏览器支持cookie，会把session的id保存在cookie
2.如果不支持cookie，通过重写URL创建 response.encodeURL()：转码，URL后面加上SessionId。

--创建session的java语句：
HttpSession session=req.getSession(true);
getSession()相当于getSession(true);
参数为true时，若存在会话，则返回该会话，否则新建一个会话；
参数为false时，如存在会话，则返回该会话，否则返回NULL；

session是针对客户端的窗口（窗口变化，session变化）。同一个application（指test1目录下servlet文件）共享session，前提是同一个窗口。
```


### 八、application

```
Application -------------------------------------------
保存整个webApplication生命周期的数据（窗口变化，application不变）
在api中对应ServletContext
ServletContext con=this.getServletContext();
```

### 九、包中的类的配置方式

```
包中的类的配置方式 -------------------------------------------
<servlet>
    <servlet-name>packageMapping</servlet-name>
    <servlet-class>com.servlettest.packageMapping</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>packageMapping</servlet-name>
    <url-pattern>/packageMapping</url-pattern>
  </servlet-mapping>
同时将类文件packageMapping.class放在servlettest目录下，如下
G:\tomcat-7\webapps\test1\WEB-INF\classes\com\servlettest
```

### 十、javabean 

```
javabean  java豆 ------------------------------------------
广义上 指使用java的类；
狭义上 指符合标准的java的类：
1.属性名称第一个字母必须小写，如private priduceId；
2.一般具有 getters  setters。
bean不具有gui性质，一般是用来实现某种逻辑或取得特定效果


### 十一、连接数据库时导入jar包

连接数据库时需要在-------------------------------------------
tomcat的WEB-IN目录下建立lib目录放置数据库的jar包
G:\tomcat-7\webapps\test1\WEB-INF\lib

import--File System
```


## Ⅲ、jsp

### 十二、jsp学习

************************************************************************

```
jsp  java server pages----特殊的servlet。直接在程序中编写html。在web服务器如tomcat上运行。
myeclipse是在项目的WebContent目录新建jsp文件

四种语法：jsp、jstl、jsf、其他taglib

Declaration   定义 ；
Scriptlet     小程序 ；
Expression    表达式 ；
Comment       注释 ；
Directives    指令 ；
Action        动作指令 ； 
 内置对象


------基本语法：
<%! .....%>     成员变量    --Declaration      
<% ..... %>     局部变量    --Scriptlet
<%=… …%>      输出        -- Expression 
=后面必须是字符串变量或者可以被转换成字符串的表达式
如 <%=“hello world”%>  
<%=i+1%>   
<%=request.getParameter(“name”)%>

------注释：
<!-- 客户端可以看见-查看代码 -->
<%//注释 客户端看不到 %>
<% /*多行注释 客户端看不到  */ %>


------Directive(编译指令)：
相当于在编译期间的命令
格式；

<%@Directive 属性=“属性值”%>

常见的Directive：
page   
include   
taglib

<%@page language=“script language”|	
        extends=“className”|
     --继承	
	import=“importList”|
	   --引入包、类		
	buffer=“none|kb size”|   --none:不缓冲，默认8k
			
	session=“true|false”|    --是否可以使用session，默认true
	
	autoFlush=“true|false”   --缓冲器是否自动清除，默认true
		
	isThreadSafe=“true|false”|  --默认false(永远不要设成true)
		
	info=“infoText”|            --描述信息			
	errorPage=“errorPageUrl”|
			
	isErrorPage=“true|false”|
			
	contentType=“contentTyepInfo”|		
	pageEncoding=“gb2312”
%>

--常用Directive page指令
<%@ page contentType="text/html;charset=gb2312"%>
<%@ page import="java.util.*"%>
<%@ page errorPage="errorPage"%>
<%@ page isErrorPage="true"%>

<%@ include file="Demo.jsp" %>   
包含file文件，编译时包含。不能传参数即不能这样写 Demo.jsp?user=aa

错误提示:
Multiple annotations found at this line:
- Duplicate local variable path
- Duplicate local variable 
basePath

重复变量,
因为<%@include%>引进的是代码,把代码包含进来,而新进JSP时,会默认生成
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<base href="<%=basePath%>">
这二句代码,所以用<%@include%>引进页面是就报重复变量 basePath 
解决方法,把要引进页面这句去掉就行,

不想删掉，因为要用到“path”这个变量，用来指定CSS路径，怎么办？？？？？？？？？？？？ 收起
.搞定了，有2个方法
 
重命名path和basePath变量，，要不不引用path，直接用request.getContextPath();将其删除。
```

```
------action 运行期间的指令
常见的:
jsp:useBean
	jsp:setProperty
	jsp:getProperty
jsp:include
jsp:forward
	jsp:param
jsp:plugin
```

```
include 程序中包含文件，运行时执行
	<jsp:include page="Multiplier.jsp" flush="true">
    	<jsp:param name="n1" value="<%= v1 %>"/>
    	<jsp:param name="n2" value="<%= v2 %>"/>
    	</jsp:include>


forward 页面跳转，是同一个request。url不变
response.sendRedirect()  页面跳转，不同的request。url改变
 <jsp:forward page="ForRequest.jsp">
    <jsp:param name="n1" value="22"/>
    <jsp:param name="n2" value="2"/>
    <jsp:param name="n3" value='<%=request.getParameter("name") %>'/>
    </jsp:forward>

jsp Bean 不要使用裸体类，要放在包里面
要把Bean的class文件放在tomcat的classes目录（根据包创建子目录）

<jsp:useBean id="bc" class="bean.BeanCount"scope="request"></jsp:useBean>
<jsp:setProperty property="count" name="bc" value="20" />
<jsp:getProperty property="count" name="bc"/>

相当于
bean.BeanCount bc=(bean.BeanCount)request.getAttribute("bcAttri");
  	if(bc==null){
  		bc=new bean.BeanCount();
  		request.setAttribute("bcAttrin", bc);
  	}
bc.setCount(20);
bc.getCount();

Scope各项参数的意义：
page:仅涵盖使用JavaBean的页面

request:有效范围仅限于使用JavaBean的请求

session:有效范围在用户整个连接过程中（整个会话阶段均有效）

application:有效范围涵盖整个应用程序。也就是对整个网站均有效


<jsp:setProperty property="count" name="bc" param="param1" />
相当于
<jsp:setProperty property="count" name="bc" value="<%= request.getParameter("param1") %>"/>


<% request.setCharacterEncoding("gb2312"); %>
  <jsp:useBean id="he" class="jspdemo.Hello" scope="request"></jsp:useBean>
  <jsp:setProperty property="*" name="he"/>
  <ol>
  <li>name=<jsp:getProperty property="name" name="he"/></li>
  <li>password=<jsp:getProperty property="password" name="he"/></li>
  <li>sex=<jsp:getProperty property="sex" name="he"/></li>
  <li>interest=<jsp:getProperty property="interest" name="he"/></li>
  <li>technology=<jsp:getProperty property="tech" name="he"/></li>

说明：
  <jsp:setProperty property="*" name="he"/>可以把request得到的值全部调用bean里面的set方法赋值
<jsp:useBean id="he" class="jspdemo.Hello" scope="request"></jsp:useBean>
  <jsp:setProperty property="*" name="he"/>


-----参数itemID是String，可以使用value获取属性值
 <jsp:setProperty property="itemID" name="it" value='<%=request.getParameter("itemID") %>'/>
------参数discount是float，不能使用value。可以使用param
 <jsp:setProperty property="discount" name="it" value='<%=request.getParameter("discount") %>'/> 错误
 <jsp:setProperty property="discount" name="it" param="discount"/>
```

---

```

JSP的内置对象

out

request
response
pageContext  用的很少

session
application

config  用的很少
exception
page 用的很少


request.getScheme() http 
request.getServerName() localhost 
request.getServerPort() 8080 
request.getContextPath() /Struts2_040_Path 

   String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   
basePath::  http://localhost:8080/Struts2_040_Path/ 


页面跳转 FromServletToJsp
getServletConfig().getServletContext().getRequestDispatcher("/jspfile/HelloBean.jsp").forward(request, response);
或者是response的sendRedirect()
```

### 十三、jsp的taglib 标签库

```java
在包中新建Java类；在WEB-INF下新建tld文件；修改index.jsp页面代码；运行

在MyEclipse中新建一Web工程，取名为TestTagLib。新建一包，包名为mytag
（1）在mytag下，新建一Java类，内容如下：
//继承import javax.servlet.jsp.tagext.SimpleTagSupport；重写doTag()方法

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TestTagLib extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		this.getJspContext().getOut().write("HelloWorld");
	}

}

（2）在WEB-INF下新建一tld文件，命名为hello.tld，内容如下：
<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE taglib
          PUBLIC "-//Sun Microsystems, Inc.//DTD JSP Tag Library 1.1//EN"
      "http://java.sun.com/j2ee/dtds/web-jsptaglibrary_1_1.dtd">
<taglib>
    <tlibversion>1.0</tlibversion>
    <jspversion>1.1</jspversion>
    <shortname>mytag</shortname>  
      <tag>
          <name>helloworld</name>
          <tagclass>mytag.TestTagLib</tagclass> 
         <bodycontent>empty</bodycontent>
     </tag>
</taglib>


--说明：
<tlibversion>1.0</tlibversion>
    <jspversion>1.1</jspversion>
    <shortname>mytag</shortname>  //是标签的前缀  <mytag:helloworld/>
    <uri>/hello</uri>      //是地址。与jsp文件中的uri对应，以此查找tld文件（也可不写）
      <tag>
          <name>helloworld</name> //是标签名
          <tagclass>mytag.TestTagLib</tagclass>   //class文件的位置
         <bodycontent>empty</bodycontent>  //标签之间为空。指以  /> 结束
     </tag>


（3）修改index.jsp页面代码，修改内容如下：
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/helloworld.tld" prefix="mytag" %>

  <head>
    <title>My JSP 'TestTag.jsp' starting page</title>
  </head>
  <body>
  taglib 标签库<br/>
    <mytag:helloworld/>
  </body>
</html>

--说明：
<!--  uri 是tld文件的位置，prefix 是前缀
tld文件若有uri则写对应的uri。如：
<%@taglib uri="/hello" prefix="mytag" %>
-->

（4）运行：http://localhost:8080/Shopping/TestTag.jsp
输出：
taglib 标签库
HelloWorld 
```


