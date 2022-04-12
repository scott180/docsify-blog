# springmvc笔记

```
springmvc(笔记—传播智客 苗润土)
1.前端处理器  dispathcherServlet
2.处理器映射器  handlerMapping
3.处理器适配器  handlerAdapter
4.视图解析器    viewResoler
```

## 1、配置

```xml
新建web项目，导入jar包
web.xml配置
	  <!-- springmvc前端控制器 dispathcherServlet -->
	  <servlet>
		<servlet-name>springmvc</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>classpath:springmvc.xml</param-value>
		</init-param>
	  </servlet>
	  
  
springmvc.xml配置
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:mvc="http://www.springframework.org/schema/mvc"
		xmlns:context="http://www.springframework.org/schema/context"
		xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
		xsi:schemaLocation="http://www.springframework.org/schema/beans 
			http://www.springframework.org/schema/beans/spring-beans-3.1.xsd 
			http://www.springframework.org/schema/mvc 
			http://www.springframework.org/schema/mvc/spring-mvc-3.1.xsd 
			http://www.springframework.org/schema/context/spring-context-3.1.xsd 
			http://www.springframework.org/schema/aop 
			http://www.springframework.org/schema/aop/spring-aop-3.1.xsd 
			http://www.springframework.org/schema/tx 
			http://www.springframework.org/schema/tx/spring-tx-3.1.xsd ">
			

	<!-- 处理器映射器 handlerMapping：根据url找到请求的handler（内部只包装一个method）。 多个处理器映射器可以共存 -->
		<!-- BeanNameUrlHandlerMapping 将bean的name当成action的url，根据url找到请求的handler及handler方法 -->
		<bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"></bean>
		
		<!-- SimpleUrlHandlerMapping  将所有的url集中配置在处 -->
		<bean class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
			<property name="mappings">
				<props>
				<!-- prop的值 hello_controller是bean的id  -->
					<prop key="/hello1.action">hello_controller</prop>
					<prop key="/hello2.action">hello_controller2</prop>
				</props>
			</property>
		</bean>


	<!-- 处理器适配器handlerAdapter:根据handler的规则来执行。实现的接口不一样，执行不一样 -->
		<!-- 凡实现Controller接口的类都当成SimpleControllerHandlerAdapter执行的对象 -->
		<bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"></bean>
		
		<!-- 另 一个处理适配置器 所有实现HttpRequestHandler接口的由该适配置器执行  -->
		<bean class="org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter"/>

	<!-- 视图解析器 viewResolvew -->
	<!-- 解析jsp视图，默认支持jstl标签-->
		<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
			<!-- jsq文件前缀 -->
			<property name="prefix" value="/WEB-INF/jsp/"></property>
			<!-- jsq文件后缀缀 -->
			<property name="suffix" value=".jsp"></property>
		</bean>

	<!-- 编写action的配置 -->
	<bean name="/hello.action" id="hello_controller" class="com.test.springmvc.action.HelloWorld"></bean>
	<bean id="hello_controller2" class="com.test.springmvc.action.HelloWorld2"></bean>

	</beans>
```

```	
handler
	public class HelloWorld implements Controller{

		@Override
		public ModelAndView handleRequest(HttpServletRequest arg0,
				HttpServletResponse arg1) throws Exception {
			// TODO Auto-generated method stub
			ModelAndView modelAndView=new ModelAndView();
			String s="Controller ModelAndView";
			modelAndView.addObject("message", s);
			
			//传到对应的jsp页面.springmvc.xml需要配置前缀，后缀
			modelAndView.setViewName("helloWorld");
			
			return modelAndView;
		}	
jsp
  ${message}
	

命令控制器AbstractCommandController:一个请求只有一个方法。且只能定义一个命令对象。
	需要指定class。重写日期格式。	
	public class AddSubmitStudent extends AbstractCommandController{

	public AddSubmitStudent(){
		this.setCommandClass(Student.class);
	}
	@Override
	protected ModelAndView handle(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
		// TODO Auto-generated method stub
		ModelAndView modelAndView=new ModelAndView();
		Student s=(Student)arg2;
		modelAndView.addObject("student_name", s.getName());
		modelAndView.addObject("student_id", s.getId());
		modelAndView.addObject("student_birthday", s.getBirthday());
		
		System.out.println(s);
		modelAndView.setViewName("success");
		return modelAndView;
	}
	
	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		// TODO Auto-generated method stub
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

}

```

```
web.xml
	   <!-- post乱码过虑器 -->
	 <filter>
		<filter-name>CharacterEncodingFilter</filter-name>
		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
		<init-param>
		  <param-name>encoding</param-name>
		  <param-value>utf-8</param-value>
		</init-param>
	  </filter>
	  <filter-mapping>
		<filter-name>CharacterEncodingFilter</filter-name>
		<url-pattern>/*</url-pattern>
	  </filter-mapping>

	  
```

## 2、说明

```	  
1、处理器映射器

  第一种：BeanNameUrlHandlerMapping，将bean的name当成action的url
  第二种：SimpleUrlHandlerMapping，将bean的url集成在一处进行配置

2、处理器适配置器
  第一种：SimpleControllerHandlerAdapter，凡是实现controller接口的bean由此适配器进行执行
  第二种：HttpRequestHandlerAdapter，凡是实现HttpRequestHandler接口的bean，由此适配器执行

3、命令控制器AbstractCommandController
框架提供一个命令控制器的类
自定义action时需要继承抽象的命令控制器的类
  缺点：一个请求在此类只有一方法去处理，且只能定义一个命令对象。


问题：
  日期数据的格式化

//日期格式数据在绑定
@Override
protected void initBinder(HttpServletRequest request,
ServletRequestDataBinder binder) throws Exception {
//指定日期类型及日期数据的格式
//日期类型要和student类的birthday一致
binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
}
   post乱码问题
在web.xml中添加过虑器.
```  
   
```   
springmvc注解

1、使用eclipse创建web工程
2、加入springmvc的jar包（spring的jar及springmvc模块的jar加入）
3、配置前端 控制器
需要在web.xml中配置

4、配置处理器映射器
<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping" />
5、配置处理器适配器
<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter"/>

6、组件扫描
扫描的标记@Controller注解的类，通过组件扫描将Controller注解的类发现，注入到spring容器。
可以省略spring配置文件配置bean

7.handler
	@Controller
	public class StudentAction {
		
		@RequestMapping(value="/queryStudent")
		public String queryStudent(Model model){
			Student s1=new Student();
			s1.setName("张三");
			s1.setId(11);
			s1.setBirthday(new Date());
			Student s2=new Student();
			s2.setName("李四");
			s2.setId(22);
			s2.setBirthday(new Date());
			
			List<Student> list=new ArrayList<Student>();
			list.add(s1);
			list.add(s2);
			model.addAttribute("studentList", list);
			
			//返回的是queryStudent.jsp页面
			return "queryStudent";
		}
		
8.jsp
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	  springmvc注解 <br>
    
	<c:forEach items="${studentList}" var="s">
    ${s.name}--${s.id }--${s.birthday } <br>
    </c:forEach>
	
http://localhost:8080/springmvc_04_Annotation/queryStudent.action


根路径
	handler
		@Controller
		@RequestMapping("/user") //根路径
		public class StudentAction {
		
		@RequestMapping(value="/queryStudent")
		public String queryStudent(Model model){
	jsp
		<form action="${pageContext.request.contextPath }/user/editStudentSubmit.action" method="post">
		
http://localhost:8080/springmvc_04_Annotation/user/queryStudent.action	
	
url占位符
	handler
		@RequestMapping("/editStudent/{groupid}/{id}")
		public String editStudent(Model model,@PathVariable String groupid,@PathVariable Integer id){
	
	jsp 
		<a href="user/editStudent/${s.groupid }/${s.id }.action">修改</a>
	   	<a href="${pageContext.request.contextPath }/user/editStudent/${s.groupid }/${s.id }.action">修改url</a>

/springmvc_04_Annotation/WebContent/WEB-INF/jsp/editStudent.jsp		
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<form action="${pageContext.request.contextPath }/user/editStudentSubmit.action" method="post">
	   <table  width="60%" border="1px">
		<tr>
		<td>id</td>
		<td><input type="text" value="${student.id }" name="id" readonly="readonly"></td>
		</tr>
		
		<tr>
		<td>姓名</td>
		<td><input type="text" value="${student.name }" name="name"></td>
		</tr>
		
		<tr>
	   <td>性别</td>
		<td>
			男<input type="radio" name="sex" value="1" checked='<c:if test="${ student.sex=='1'}">checked</c:if>' />
			女<input type="radio" name="sex" value="2" checked='<c:if test="${ student.sex=='2'}">checked</c:if>' />
		</td>
		</tr>
		
		<tr>
		<td>类型</td>
		<td>
		<select name="groupid">
			<option value="1" <c:if test="${student.groupid=='1'}"> selected</c:if> >三好学生 </option>
			<option value="2" <c:if test="${student.groupid=='2'}"> selected</c:if> >一般学生 </option>
		</select>
		</td>
		</tr>
		
		<tr>
	   <td>出生日期</td>
		<td><input type="text" value="<fmt:formatDate value="${student.birthday }" pattern="yyyy-MM-dd"/>" name="birthday"></td>
		</tr>
	   
	   </table>
	   <input type="submit" value="修改">
	   </form>
```

## 3、Pojo对象

```
限定GET方法
@RequestMapping(method = RequestMethod.GET)
如果通过Post访问则报错：
HTTP Status 405 - Request method 'POST' not supported
例如：
@RequestMapping(value="/useredit/{userid}",method=RequestMethod.GET)
限定POST方法
@RequestMapping(method = RequestMethod.POST)
	   
GET和POST都可以
@RequestMapping(method={RequestMethod.GET,RequestMethod.POST})


Pojo对象
	页面上以pojo对象中属性名称命名：
		将pojo对象中的属性名于传递进来的属性名对应，如果传进来的参数名称和对象中的属性名称一致则将参数值设置在pojo对象中

		页面定义如下;

		<input type="text" name="age"/>
		<input type="text" name="birthday"/>

		Contrller方法定义如下：
		public String useraddsubmit(Model model,User user)throws Exception{
			System.out.println(user);
		}

	页面上以pojo对象名点属性名命名：
		如果采用类似struts中对象.属性的方式命名，需要将pojo对象作为一个包装对象的属性，action中以该包装对象作为形参。
		包装对象定义如下：
		Public class UserVo {
		private User user;

		public User getUser() {
			return user;
		}
		Public void setUser(User user) {
			this.user = user;
		}
		}

		页面定义：

		<input type="text" name="user.age" />
		<input type="text" name="user.birthday" />

		Controller方法定义如下：

		public String useraddsubmit(Model model,UserVo userVo)throws Exception{
		System.out.println(userVo.getUser());
		}
		
Map
	在包装类中定义Map对象，并添加get/set方法，action使用包装对象接收。
	包装类中定义Map对象如下：
	Public class UserVo {
	private Map<String, Object>studentinfo = new HashMap<String, Object>();
	  //get/set方法..
	}

	页面定义如下：

	<tr>
	<td>学生信息：</td>
	<td>
	姓名：<inputtype="text"name="studentinfo['name']"/>
	年龄：<inputtype="text"name="studentinfo['age']"/>
	.. .. ..
	</td>
	</tr>

	Contrller方法定义如下：

	public String useraddsubmit(Model model,UserVo userVo)throws Exception{
	System.out.println(userVo.getStudentinfo());
	}
	
List  
	与map类似，使用包装对象
	public class UserVo {
	
	private Student student;
	private Map<String,Object> studentinfo=new HashMap<String,Object>();
	private List<StudentScore> scores=new ArrayList<StudentScore>();
	
   	<tr>
   	<td>课程名称：<input type="text" name="scores[0].courename"></td>
   	<td>课程分数：<input type="text" name="scores[0].score" ></td>
   	</tr>
   	
   	 <tr>
    <td>课程名称：<input type="text" name="scores[1].courename"></td>
   	<td>课程分数：<input type="text" name="scores[1].score" ></td>
   	</tr>
   	
	
字符串数组接收数据
	@RequestMapping("/deleteStudents")
	public String deleteStudents(String[] deleteIds){
		System.out.println("使用字符串数组接收数据：");
		for(String id : deleteIds){
			System.out.print(id+" ");
		}
		return "success";
	}
	
  <form action="${pageContext.request.contextPath }/user/deleteStudents.action" method="post">
  <input type="submit" value="批量删除">	
	
	<c:forEach items="${studentList}" var="s">
	<tr>
	   	<td>
	   	<input type="checkbox" name="deleteIds" value="${s.id }">
	   	</td>
```

```
@RequestParam绑定单个请求参数
	value：参数名字，即入参的请求参数名字，如value=“studentid”表示请求的参数区中的名字为studentid的参数的值将传入；
	required：是否必须，默认是true，表示请求中一定要有相应的参数，否则将报400错误码；
	defaultValue：默认值，表示如果请求中没有同名参数时的默认值

	定义如下：
	public String userlist(			
	@RequestParam(defaultValue="2",value="group",required=true) String groupid) {

	}

	形参名称为groupid，但是这里使用value="group"限定参数名为group，所以页面传递参数的名必须为group。
	这里通过required=true限定groupid参数为必需传递，如果不传递则报400错误，由于使用了defaultvalue=”2”默认值即使不传group参数它的值为”2”，所以页面不传递group也不会报错，如果去掉defaultvalue=”2”且定义required=true则如果页面不传递group则会报错。

redirect forward	
	return "redirect:queryStudent.action";  //重新产生request。URL变化
	return "forward:queryStudent.action";	//原来的request，url不变

	
	
json请求与响应	
	
@Controller
public class JsonTest {

	@RequestMapping("/jsonRequest")
	public @ResponseBody Student jsonRequest(@RequestBody Student student){
		//请求json响应json
		//@RequestBody  json数据转换为对象
		System.out.println(student);
		
		//由于使用@ResponseBody注解，所以student对象自动转换为json输出
		return student;
	}
	
	@RequestMapping("/jsonResponse")
	public @ResponseBody Student jsonResponse(Student student){
		//请求key/value响应json
		System.out.println(student);
		return student;
	}
	
	
	
		function requestJson(){
		//请求为json数据
		$.ajax({
			url:"jsonRequest.action",
			type:"post",
			data:'{"name":"张三","id":"11"}',
			contentType:'application/json;charset=utf-8',
			success:function(data){
				alert(data.name);
			}
		});
	}
	function requestKV(){
		//请求为key/value数据
		$.ajax({
			url:"jsonResponse.action",
			type:"post",
			data:"name=李四&id=22",
			success:function(data){
				alert(data.name);
			}
		});
	}
	
```	
	
## 4、拦截器	
	
```	
preHandle按拦截器定义顺序调用
postHandler按拦截器定义逆序调用
afterCompletion按拦截器定义逆序调用

postHandler在拦截器链内所有拦截器返成功调用
afterCompletion只有preHandle返回true才调用


用户登陆拦截

		<!-- 拦截器 -->
	<mvc:interceptors>
		<mvc:interceptor>
			<mvc:mapping path="/**" />
			<bean class="com.test.springmvc.interceptor.LoginInterceptor"></bean>
		</mvc:interceptor>
	</mvc:interceptors>

	public class LoginInterceptor implements HandlerInterceptor {

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session=request.getSession();
		Student s=(Student) session.getAttribute("userinfo");
		String u=request.getRequestURI();
		if(u.indexOf("loginSubmit.action")>=0){
			return true;
		}
		if(s!=null){
			return true;
		}
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		return false;
	}

		
```
		
```java
package cn.itcast.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.springmvc.pojo.Student;

public class LoginInterceptor implements HandlerInterceptor {

	/**
	 * 此方法是在进行handler方法之前执行
	 * arg2：要执行的handler
	 * 场景：用户权限的拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		
		HttpSession session = request.getSession();
		//判断session中是否存在用户，存储表示用户已登录
		Student student = (Student)session.getAttribute("userinfo");
		if(student!=null){
			//表示session存在，用户已登录
			//放行
			return true;
		}
		
		//判断请求的地址是否公开地址(不需要登录就可以访问的地址)
		String url = request.getRequestURI();
		if(url.indexOf("loginsubmit.action")>=0){
			//是公开的地址，放行
			return true;
		}
		
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		//判断用户的session是否存在，如果不存在跳转到登录页面
		
		//如果存在就放行
		
		return false;
	}
	/**
	 * 此方法是在执行handler完毕，返回modenAndview之前执行
	 * 场景：对ModelAndView进行修改，比如统一添加页面导航（页面所需要的公用数据）
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
		
	}
	/**
	 * 此方法是在执行handler完毕，且返回ModelAndView之后执行
	 * 场景：在这里记录执行的时间，并且在preHandle方法中也记录执行的开始时间，统一action执行时间，完成执行性能监视
	 * 场景：统一在这里进行日志记录
	 * 场景：统一进行异常处理
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		

	}
	

}		

```
