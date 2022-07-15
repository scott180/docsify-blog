# jquery笔记  

## 1、导入jquery

```js
jQuery是一个快速、简洁的JavaScript框架，是继Prototype之后又一个优秀的JavaScript代码库（框架）于2006年1月由JohnResig发布。
jQuery设计的宗旨是“writeLess，DoMore”，即倡导写更少的代码，做更多的事情。


黑马第十期
导入类库 <script src="../js/jquery-1.4.2.js"></script>

https://cdn.jsdelivr.net/npm/jquery@3.2/dist/jquery.min.js
https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.js

window.onload  这个表示加载完页面所有东西以后才执行。
window.load    这个只是表明事件方法，但并未执行，比如click表示点击事件，但他并未执行，必须用上onclick他才会执行。

window.onload 必须等到页面内包括图片的所有元素加载完毕后才能执行，$(document).ready()是DOM结构绘制完毕后就执行，不必等到加载完毕。
window.onload 不能同时编写多个，如果有多个window.onload方法，只会执行一个，$(document).ready()可以同时编写多个，并且都可以得到执行。
window.onload 没有简化写法，$(document).ready(function(){})可以简写成$(function(){})   $().ready(function(){}) 。


//函数当做参数。函数是一个对象
$().ready(L);
function L(){
	alert(222);
}

//相当于
$().ready(function(){ //匿名函数
	alert(333);
});

```
	
```java
jquery 和 dom 的转换
<script type="text/javascript">
	window.onload = function(){
		
		//dom对象转换jquery
		var domObj = document.getElementById("username");
		var jqueryObj = $(domObj);
		//alert(jqueryObj);
		
		//jquery转换dom。jquery是数组
		var jquery1=$("#username");
		var dom1=jquery1.get(0);
		var dom2=jquery1[0];
		//alert(dom1+"-"+dom2);
	}
</script>

```

## 2、选择器

```js
基本选择器  $("#1")
	<script type="text/javascript">
		//<input type="button" id="b1" value="改变id为1的背景色">
		$("#b1").click(function(){
			$("#1").css("background-color","#ffff00");
		});
		
		//<input type="button" id="b2" value="改变div标签的背景色"><br/>
		$("#b2").click(function(){
			$("div").css("background-color","ff00dd");
			
		});
		
		$("#b3").click(function(){
			$(".mini").css("background-color","aa00dd");
		});
		
		// <input type="button" id="b4" value="改变所有的背景色"><br/> 
		$("#b4").click(function(){
			$("*").css("background-color","aa2233");
		});
	  
	  //<input type="button" id="b5" value="改变div和class为mini的背景色"><br/> 
		$("#b5").click(function(){
			$("div,.class").css("background-color","aa4433");
		});
	
   </script>
   
   
属性选择器  $("div[title='va']")
	<script type="text/javascript">
		//  <input type="button" id="b1" value="改变属性为title的div的背景色">
		$("#b1").click(function(){
			$("div[title]").css("background-color","#aaaaff");
		});
		
		//<input type="button" id="b2" value="改变属性为title的值是va的div的背景色">
		$("#b2").click(function(){
			$("div[title='va']").css("background-color","#ffaaff");
		});
		
		/**
		 * <input type="button" id="b3" value="改变属性为title的值是以te开头的div的背景色"><br/>
		 * != 不等于    ^= 开头     $= 结尾    *= 包含
		 */
		$("#b3").click(function(){
			$("div[title^=te]").css("background-color","red");  
			//$("div[title$=est]").css("background-color","red");
			//$("div[title*=es]").css("background-color","red");
			//$("div[title!=va]").css("background-color","red");
		});
		
		//<input type="button" id="b4" value="改变隐藏域，id为b2,class是mini，属性为title的值是以te开头的div的背景色"><br/> 
		$("#b4").click(function(){
			$("input[type=hidden],#b2,.mini,div[title^=te]").css("background-color","blue");
		});
  </script>
```

```js
层次选择器  子选择器 $("#one>div")
  	<script type="text/javascript">
		//<input type="button" id="b1" value="改变body内div的颜色">
		$("#b1").click(function(){
			$("body div").css("background-color","red");
		});
		
		//<input type="button" id="b2" value="改变id为one的子div的颜色">
		$("#b2").click(function(){
			$("#one>div").css("background-color","blue");
			});
		
		//<input type="button" id="b3" value="改变id为one下一个div的颜色">
		$("#b3").click(function(){
			$("#one+div").css("background-color","black");
			});
			
		//<input type="button" id="b4" value="改变id为two兄弟div的颜色">
		$("#b4").click(function(){
			$("#two").parent().children("div").css("background-color","#aaccaa");
		});
		
		//<input type="button" id="b5" value="改变id为one后面div的颜色">
		$("#b5").click(function(){
				$("#one~div").css("background-color","grey");
				});
	</script>
	
	
过滤选择器  偶数 $("div:even")
	<script type="text/javascript">
		$("#b1").click(function(){
			$("div:first").css("background-color","red");
		});
		
		$("#b11").click(function(){
			$("div:last").css("background-color","grey");
		});
		
		$("#b2").click(function(){
			$("div:not(#o2)").css("background-color","yellow");
		});
		
		//<input type="button" id="b3" value="改变索引是偶数div的颜色">
		$("#b3").click(function(){
			$("div:even").css("background-color","grey");
			//$("div:odd").css("background-color","green");//奇数
		});
		
		$("#b4").click(function(){
			$("div:gt(3)").css("background-color","red");
			//$("div:eq(3)").css("background-color","red");
			//$("div:lt(3)").css("background-color","red");
		});
		
		$("#b5").click(function(){
			//div:gt(3) 是一个数组，div:gt(3):lt(1)是以div:gt(3)为基础的数组
			//$("div:gt(3):lt(1)").css("background-color","red");
			
			$("div:lt(5):gt(2)").css("background-color","red");//大于2，小于5的索引
		});
	</script>
```

```js
table行背景色
<script src="../js/jquery-1.4.2.js"></script>
<script type="text/javascript">
	$().ready(function(){
		$("#t1 tr:even").css("background","red");
		$("#t1 tr:odd").css("background","blue");
		
		$("#t2 tr:gt(0):even").css("background","red");
	//	$("#t2 tr:gt(0):odd").css("background","blue");
		$("#t2 tr:not(:first):odd").css("background","yellow");
		
		
		$("#t3 tr:not(:first):odd").each(function(){
			//alert(this);
			var jobj =$(this);
			jobj.children("td:first").css("background","red");
		});
		
//		$("#t3 tr:not(:first):even").each(function(){
//			//alert(this);
//			var jobj =$(this);
//			jobj.children("td:first").css("background","yellow");
//		});
		
		$.each($("#t3 tr:not(:first):even"),function(){
			//alert(this);
			var jobj =$(this);
			jobj.children("td:first").css("background","yellow");
		});
	});
	
</script>	
	
	
子选择器  $("div(.one) :nth-child(2)")  $("div(.one) div:nth-child(2)")	$("div(.one):nth-child(2)")
<script src="../js/jquery-1.4.2.js"></script>
	
	<style type="text/css">
		.one{margin:20px;
		    background-color:#aabb00;
			width:200px;
			height:150px;}
		div{
		    background-color:#aabbcc;
			width:50px;
			height:30px;}
	</style>
	<script type="text/javascript">
		$().ready(function(){
			$("#b1").click(function(){
				$("div(.one) :nth-child(2)").css("background","red");
			});
		$("#b3").click(function(){
				$("div(.one) div:last-child").css("background","yellow");
				
			});	

		});
		
	</script>
	
	
内容选择器	
	<input type="button" id="b1" value="改变含有con的div背景色">
	 <input type="button" id="b2" value="改变没有文本的div背景色">
	 <input type="button" id="b3" value="改变含有class为pl的div背景色">
	 <input type="button" id="b4" value="改变含有子元素的div背景色">
  
  <script type="text/javascript">
  	$("#b1").click(function(){
		$("div:contains('cont')").css("background","red");
	});
	
	$("#b2").click(function(){
		$("div:empty").css("background","blue");
	});
	
	$("#b3").click(function(){
		$("div:has(.pl)").css("background","yellow");
	});
	
	$("#b4").click(function(){
		$("div:parent").css("background","#11aaaa");
	});
  </script>
```
 
```js
表单选择器
	  <html>
	  <head>
		<title>form_selector.html</title>
		
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="this is my page">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		
		<!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
	<script src="../js/jquery-1.4.2.js"></script>
	  </head>
	  
	  <body>
		<form>
			<input type="button" id="b1" value="改变可调属性的值"/><br/>
			<input type="button" id="b2" value="显示interet被选个数"/><br/>
			<input type="button" id="b3" value="显示interet被选option"/><br/>
			<input type="button" id="b4" value="显示隐藏域内容"/><br/>
			<hr/>
			job
			<select id="job">
				<option selected="selected">select</option>
				<option id="java">java</option>
				<option id="c">c</option>
				<option id="c++">c++</option>
				<option id="jquery">jquery</option>
			</select>
			<hr/>
			interet
			<select id="interet" multiple="multiple" size="3">
				<option id="java" selected="selected">java</option>
				<option id="c">c</option>
				<option id="c++">c++</option>
				<option id="jquery">jquery</option>
				<option id="jquery2">jquery2</option>
				<option id="jquery3">jquery3</option>
				<option id="jquery4">jquery4</option>
			</select>
			
			<input type="text" value="文本不可调" disabled="true">
			<input type="text" value="文本" disabled="true">
			<input type="text" value="文本可调">
			<hr/>
			checkbox
			<input type="checkbox" id="jobc" checked="checked">老师
			<input type="checkbox" id="jobc">学生
			<input type="checkbox" id="jobc">工人
			<input type="checkbox" id="jobc">程序员
			<hr/>
			radio
			<input type="radio" id="sex" name="sex" checked="checked">男
			<input type="radio" id="sex" name="sex">女
			
			<input type="hidden" value="hd1">
			<input type="hidden" value="hd2">
			<input type="hidden" value="hd33">
			<input type="hidden" value="hd4">
		</form>
		
		<script type="text/javascript">
			$("#b1").click(function(){
				//$("input:disabled").val("aaa");
				$("input:enabled").val("改111");
				
			});
			
			$("#b2").click(function(){
				//alert($("#interet>option").length);
				alert($("#interet>option:selected").length);
				
			});
			
			$("#b3").click(function(){
				
			//	alert($("input[type='checkbox']:checked").length);
				alert($("#interet>option:selected").text());
			});
			
			$("#b4").click(function(){
				
				$("input:hidden").each(function(){
					var $obj=$(this);
					alert($obj.val());
				});
			});
			
		</script>
	  </body>
	</html>
```

## 3、ajax

```js
/**
 * 使用ajax查询数据
 */
function queryMethod(){
	$.ajax({
		url:"../../AA",
		data:{method:'query'},
		success:function(data){
			var jsonObj=eval("("+data+")");
		//	alert(jsonObj);
			for(var i=0;i<jsonObj.length;i++){
				var name=jsonObj[i].name;
				var email = jsonObj[i].email;
				var phone = jsonObj[i].phone;
				var id = jsonObj[i].id;
			//	alert(id);
				$tr = $("<tr/>");
				$inputId = $("<input/>").attr("type","hidden").attr("name","id").attr("value",id);
				$nameTD = $("<td/>").text(name);
				$emailTD = $("<td/>").text(email);
				$phoneTD = $("<td/>").text(phone);
				$delA = $("<a/>").text("删除").css("cursor","pointer").css("text-decoration","underline");
				$delA.click(function(){
					if(window.confirm("确定删除吗？")){
						deleteUser(this);
					}
				});	
				
				$delTD = $("<td/>").append($delA);
				$updateA = $("<a/>").text("修改").css("text-decoration","underline");
				$updateTD = $("<td/>").append($updateA);
				$tr.append($nameTD).append($emailTD).append($phoneTD).append($updateTD).append($delTD).append($inputId);
				$("#user>tbody").append($tr);
			}
		},
	    error: function(XMLHttpRequest, textStatus, errorThrown){
		     alert("error");
	    }
	});
}	

	public void getPersonAll(){
		List<Person> persons=(ArrayList<Person>)this.getServletContext().getAttribute("persons");
		String str=JSONArray.fromObject(persons).toString();
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
ajax
		$("#addUser").click(function(){
					var name=$("#name").val();
					var email=$("#email").val();
					var phone=$("#phone").val();
					$.ajax({
						url:"../../AA",
						
						success:function(){
							
							//alert(name+"|"+email+"|"+phone);
							var $updateA = $("<a/>").css("cursor","pointer").text("修改");
						
							$updateA.click(function(){
								updateUser(this);
							});
							
							var $delA = $("<a/>").css("cursor","pointer").css("text-decoration","underline").text("删除");
								$delA.click(function(){
									if(window.confirm("确定删除吗？")){
										deleteUser(this);
									}
								});			
							var $nameTD = $("<td/>").text(name);
							var $emailTD = $("<td/>").text(email);
							var $phoneTD = $("<td/>").text(phone);
							var $updateTD = $("<td/>").append($updateA);
							var $delTD = $("<td/>").append($delA);
							
							var $idHidden = $("<input/>").attr("type","hidden").attr("name","id").attr("value",count);
							var $userTR = $("<tr/>")
								.append($nameTD).append($emailTD).append($phoneTD)
								.append($updateTD).append($delTD).append($idHidden);
							
							$("table(#user)>tbody").append($userTR);
							count++;
						},
						
						data:{
							method:'add',
							id:count,
							name:name,
							email:email,
							phone:phone
						},
						error:function(){
							alert("error");
						}
					});

```

## 4、function

```js
function是一个对象
Function是最顶层对象
任何对象都有可能成为任何对象的属性
		function Person(){
			
		}
		Person.id=2;
		Person.name='ass';

		function Student(){
			alert("student");
		}

		Person.student=Student;

		Student();

		function A(){}
		function b(){}
		function c(){}
		function d(){alert("dd");}
		A.B=b;
		A.B.C=c;
		A.B.C.D=d;
		A.B.C.D();


prototype
//函数是一个对象，每个函数都有prototype属性
		function Person(){
			
		}
		Person.prototype.id=11;
		Person.prototype.name="aa";//Person.prototype['name']="aa"
		var p=new Person();
		alert(p.id);
		alert("空-"+p.name);

		function Student(){}
		Student.prototype=Person.prototype;//继承
		alert(Student.prototype.name);

		
json		
	var j={
		a:'aa',
		b:'bb',
		c:function(){alert("cc");}

	};
	j.d='dd';
	j['e']='ee';

	for(var i in j){//json遍历
		if(typeof j[i] == 'string'){
			alert("string-"+j[i]);
		}else if(typeof j[i] == 'function'){
			j[i]();//函数
		}
	}

	
extend
		function extend2(json,proto){
			function F(){}
			if(typeof json == "object"){
				for(var i in json){
					F.prototype[i]=json[i];
				}
			}
			else if(typeof json == "function"){
				
				F.prototype = json.prototype;//只有一个prototype
				/*for(var i in json.protorype){//有两个prototype
					F.prototype[i]=json.prototype[i];
				}*/
				for(var i in proto){
					F.prototype[i]=proto[i];
				}
			}
			return F;
		}

			var Person=extend2({
				id:'11',
				name:'scott'
				
			});
			var p=new Person();
			//alert("p "+typeof p);
			//alert("Person "+typeof Person);
		alert(p.name);
			var Student = extend2(Person,{
				age:'22',
				sex:'man'
			});
			var s=new Student();
			Person.prototype.name="ppp";//Person.prototype和s是不同的对象
			s.name="ssss";
			alert(s.name);
			alert("p"+p.name);
			alert(Person.prototype.name);
			
delegate 给未来的元素添加事件			
		$().ready(function(){
			
			$("div").unbind("click");
			$("div").bind("click",function(){
				alert("afdsa");
			});
			
			$("body").delegate("div","click",function(){
				alert("1");
			});
			
			$("input[type='button']").bind("click",function(){
				$("body").append($("<div/>").text("aaaaaaa"));
			});
		});
```					

```js			
自定义事件					
		$().ready(function(){
			
			$("div").unbind("学好java");
			$("div").bind("学好java",function(event,a){
				alert(a);
			});
			
			$("div").unbind("click");
			$("div").bind("click",function(){
				$(this).trigger("学好java",5);
			});
			
			
		//	$("p").unbind("学jquery");
		//	$("p").bind("学jquery",function(event,a,b){
		//		alert(a);
		//		alert(b);
		//	});
		//	
		//	$("p").unbind("click");
		//	$("p").bind("click",function(){
		//		$(this).trigger("学jquery",[2,3]);
		//	});

			$("p").unbind("学jquery");
			$("p").bind("学jquery",function(event,obj){
				alert(obj.a);
				alert(obj.b);
			});
			
			$("p").unbind("click");
			$("p").bind("click",function(){
				$(this).trigger("学jquery",{
					a:'aaa',
					b:'bbb'
				});
			});
		});			


		/**
		 * 回调函数
		 */
		function ajaxFunction(){
			var xmlHttp;
			try { // Firefox, Opera 8.0+, Safari
				xmlHttp = new XMLHttpRequest();
			} 
			catch (e) {
				try {// Internet Explorer
					xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				} 
				catch (e) {
					try {
						xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
					} 
					catch (e) {
					}
				}
			}
			return xmlHttp;
		}

		var xmlHttp;

		function ajax(json){
			xmlHttp = ajaxFunction();
			xmlHttp.onreadystatechange = function(){
				//获取ajax当前的请求状态 ==4处理
				if (xmlHttp.readyState == 4) {
					if (xmlHttp.status == 200) {
						//callback就是回调函数
						json.callback(xmlHttp.responseText);
					}
				}
			};
			xmlHttp.open(json.method, json.url, true);
			xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xmlHttp.send(json.data);
		}

		window.onload = function(){
			ajax({
				url:'../AjaxServlet',
				data:null,
				method:'post',
				callback:function(data){
					alert(this);
					alert(data);
				}
			});
		};


this
		function Person(){

			alert(this);  //谁调用，this就代表谁

		}

		function Student(){
			var d=3;
		}

		Student.per=Person;
		Student.per();

		Person(); //相当于 window.Person(); this 是window
		Person.call(Student);//相当于Student.per();

		var json={
			a:Person
		};
		json.a();
```		

---

```js	
/**
 * 自调函数
 * 闭包：函数内部定义的函数，在外部使用
 * 		1.继承和封装
 * 		2.函数的共有性和私有性
 */
(function(window){
	function Person(){
		return{
			getName:getName,
			setName:setName,
			getAge:getAge,
			setAge:setAge
		};
	}
	
	//共有属性
	function getName(){
		return this.name;
	}
	function setName(name){
		this.name=name;
	}
	function getAge(){
		return this.age;
	}	
	function setAge(age){
		this.age=age;
	}
	//私有属性
	function getSex(){
		return this.sex;
	}
	function getJob(){
		return this.job;
	}
	window.Person=Person;//给window动态添加一个属性，把自调函数()()内部的Person函数赋值给它
})(window);

var p=new Person();
p.setName("aaa");
alert(p.getName());

var p2 = window.Person();
p2.setAge(22);
alert(p2.getAge());


jquery plugin
	jQuery.fn = jQuery.prototype = {
		init: function( selector, context ) {......
	window.jQuery = window.$ = jQuery;
	$ 是jQuery    fn 是prototype     ()() 闭包执行   (functio($){})($) 传入$执行函数
		(function($){
			$.fn.myextends=function(json){
				for(var i in json){
					$.fn[i]=json[i];
				}
			}
		})($)

		$().ready(function(){
			$.fn.myextends({
				a:function(){
					alert("aaaa");
				}
			})
			$("body").a();
		})
```

---

## 5、函数说明

```js
//函数两种创建方式
		var F1=function(){}
		function F2(){
			alert(1);
		}
		var f3=new F2();
		alert(typeof(F1));//函数名 funciton
		alert(typeof(F2));//函数名 function
		alert(typeof(f3));//函数引用object  
		F2();//函数名加括号表示调用函数
		
匿名函数		
	
	//闭包 ()()
	(function(i){
		alert(i);
	})(11);

	//匿名函数
	var f=function(i){
		alert(i);
	};
	f(22);//调用函数
	alert(typeof(f));//f是function

	//有参函数
	function F(i){
		alert(i);
	}
	var f2=new F(33);//函数名（）表示调用函数
	alert(typeof(f2));//f2是function
	F(55);//函数名（）表示调用函数
	
	
	
	
	
	
//例1 函数声明
function F1(){
	alert("function declaration 1");
}

//例2 函数表达式。函数是new出来的立刻执行，相当于先动态创建了函数再将其调用 。如例3
var f2=new function(){
	//alert("new function expression 2");
};

//例3
function Fun3(){//声明函数
	alert("function Fun3");
}
var f31=new Fun3();//Fun3()函数名+括号 可以直接调用函数
//alert("typeof(f31):  "+typeof(f31));//Fun3的引用赋值给f31。f31 是object
//alert("f31 "+f31);
f31;

var f32=Fun3;//将Fun3赋值给f32。f32是function
alert("typeof(f32)--"+typeof(f32));//function
f32();//函数名+括号 可以直接调用函数
alert(f32);

//例4 函数表达式
var f4=function(){
	alert("function expression 4");
};


//alert("dec  "+typeof(F1));//funciton
//alert("exp  "+typeof(f2));//function 是new 出来的，f2是object
//alert("exp  "+typeof(f4));//function




//声明函数
function F1(){
	alert("F1");
	
}

//函数表达式
var f2=function(){
	alert("F2");
};

//调用函数
//F1();
//f();

//声明函数  需要在function加括号转换为表达式在其后再加上括号才能直接调用函数
(function F3(){
	alert("F3");
	
})();

//将函数声明转换为函数表达式再加括号可以直接调用函数
!function F31(){
	alert("!!");
	
}();

+function F32(){
	alert("++");
	
}();

-function F33(){
	alert("--");
	
}();
//函数表达式 后面加括号直接调用函数
var f3=function(){
	alert("F4");
}();



函数调用的三种方式:
obj.myFunc();
myFunc.call(obj,arg);
myFunc.apply(obj,[arg1,arg2..]); 

call和apply可以用来重新定义函数的执行环境，也就是this的指向。通过一个操作DOM的例子来理解。

function changeStyle(attr, value){
    this.style[attr] = value;
}
var box = document.getElementById('box');
window.changeStyle.call(box, "height", "200px");

call中的第一个参数用于指定将要调用此函数的对象，在这里，changeStyle函数将被box对象调用，this指向了box对象，如果不用call的话，程序报错，因为window对象中没有style属性。
apply的用法:

window.changeStyle.apply(box, ['height', '200px']);


call用来调用另一个对象的方法，但调用的对象是可以作为参数传入的。
function A(name){//A 类
    this.name=name;
    this.showName=function(){
        alert(this.name);
    }
}
 
function B(name,age){ //B 类
    this.name=name;
    this.age=age;
}
var a=new A("aaa"); //a对象是有showName方法的
var b=new B('bbb',10);//b对象没有showName方法
a.showName.call(b);//但是这里showName，显示的是b的名字，也就是b调用了a的showName方法

call最经典的应用就是继承。javascript是没有对象继承概念的，我们只能用一些方法实现。
function A(name){//A 类 还是这个例子，A有showName
    this.name=name;
    this.showName=function(){
        alert(this.name);
    }
}
 
function B(name,age){ //B 类，
 
    //B类中通过A.call，相当于执行了A(name),但在A中所有this其实都是B。那么B就有name这个属性，同时也拥有showName方法了。
    A.call(this,name);
    this.age=age;
}
var b=new B('bbb',10);
b.showName();//B继承了A，所以b也有showName方法。

```
