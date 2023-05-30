# javaScript笔记

<br /> 

> js  javaScript  jquery 

`--20190403`


## js

```js
url中文转换   encodeURI(encodeURI(name))    decodeURI(name)


$('#passwordAnwser').textbox('textbox').validatebox('options').required = false;
$('#name').validatebox({required:false});

//$('#passwordAnwser').textbox('setValue','1');
//$('#password').passwordbox('hidePassword');


$.fn.validatebox.defaults.rules.email.message = '请输入有效的<br/>电子邮件地址';


alert回调
$.messager.alert("提示:", msg, "info",function(){
	  $('#userDeptEmailFrom').focus();
 });



一秒刷新
 setTimeout(function(){
	window.location.reload();
},1000);


定时
setTimeout(function(){
   init();
   layer.close(loading);  
},500);
		  
		  

滚动条设置在顶端
$('body').scrollTop( $('body').height() );

```

```js
字符串转函数
var bb = 'function play(){alert("getplay");}';

var ss = bb;

var ex = {getplay:eval("("+ss+")")};

ex.getplay();


JSON.parse(string) ：接受一个 JSON 字符串并将其转换成一个 JavaScript 对象。
JSON.stringify(obj) ：接受一个 JavaScript 对象并将其转换为一个 JSON 字符串。


layer.close(index);
layer.closeAll(); //疯狂模式，关闭所有层

layer.closeAll('dialog'); //关闭信息框
layer.closeAll('page'); //关闭所有页面层
layer.closeAll('iframe'); //关闭所有的iframe层
layer.closeAll('loading'); //关闭加载层
layer.closeAll('tips'); //关闭所有的tips层

```

---

```js
function hoverRowSrc(obj){
	var img = '<img class="add" data-tool="left" style="float:right;position:relative;top:8px;cursor:pointer;" src="img/add.png">';
	if(!$(obj).is(':has(img)') ){
		$(obj).append($(img));
	}
}
function outRowSrc(obj){
	if($(obj).is(':has(img)') ){
		setTimeout(function(){
			$(obj).find('img').remove();
		},50)
	}
}

function initCheckAll(){
	$('input[name="input1"]').on('ifChecked', function(event){
		$('div[name="divSrc"]').each(function(){
			$(this).addClass('checked');
		});
		if($('div[name="divSrc"]').length>0){
			$('button[data-tool="right"]').removeAttr("disabled");
		}
	});
	 $('input[name="input1"]').on('ifUnchecked', function(event){
		 $('div[name="divSrc"]').each(function(){
				$(this).removeClass('checked');
			});
		 $('button[data-tool="right"]').attr("disabled","");
	 });	
}

function selectSrc(obj){
	var st = $(obj).attr('class');
	setTimeout(function(){if(st.indexOf('checked')>-1){
			$(obj).removeClass('checked');
			
			var flag = true;
			$('div[name="divSrc"]').each(function(){
				if($(this).hasClass("checked")){
					flag = false;
				}
			})
			if(flag){
				$('button[data-tool="right"]').attr("disabled","");
			}
			
		}else{
			$(obj).addClass('checked');
			$('button[data-tool="right"]').removeAttr("disabled");
		}},20);
} 


```

---

```
$("#tt").tabs({
onSelect : function( title ){
	<%-- if(title == '短信'){
		if(!$("#smsDetail").attr("src")){
			$("#smsDetail").attr("src","<%=smsDetailPage%>");
		}
	} else if(title == '邮件'){
		if(!$("#emailDetail").attr("src")){
			$("#emailDetail").attr("src","<%=emailDetailPage%>");
		}
	} else if(title == '即时消息'){
		if(!$("#imDetail").attr("src")){
			$("#imDetail").attr("src","<%=imDetailPage%>");
		}
	} else if(title == '微信'){
		if(!$("#wxDetail").attr("src")){
			$("#wxDetail").attr("src","<%=wxSendDetail%>");
		}
	} --%>
	<%-- if(!$("#smsDetail").attr("src")){
		$("#smsDetail").attr("src","<%=smsDetailPage%>");
	} --%>
	var pp = $('#tt').tabs('getSelected');
	var ifr =  $($("iframe",pp)[0]);
	if(!ifr.attr("src")){
		var url = ifr.attr("tarsrc");
		ifr.attr("src",url);
	}
}

​	

 $(document).ready(function() {
	  $("#fm").form({
			onSubmit: function() {
				return $(this).form("validate");
			},
			success: function(result) {
				if (result == 'success') {
					$.messager.alert('提示', '保存成功！', 'info');
				} else {
					alertMsg(result);
				}
			}
		});		
	});
	
function check()
{
	$('#fm').submit();
}	

						
			
$('#loginNameInput').textbox('textbox').keyup(function(event) {
		$('#userName').textbox("setValue", '');
		$('#userId').val('0');
		var text = $('#loginNameInput').combobox("getText");
		val = text || val ;
		text = val;
	if (event.keyCode == "13") { 
		$.ajax({
			url : 'getUsersByLoginName.do?domainId=1&loginName='+text,
			type: 'get',
			dataType:'json',
			success:function(result){
				var data = [];
				data = result;
				$('#loginNameInput').combobox("loadData", data);
				$('#loginNameInput').combobox("showPanel");
				$('#loginNameInput').combobox("setText", text);
			}
		});
	}
});

```

## 选择框				

```js
//联动单选框
$('#radio21').on('ifChecked', function(event){
	$('#radio22').iCheck('check');
	$('#radio24').iCheck('uncheck');
});

$('#radio23').on('ifChecked', function(event){
	$('#radio22').iCheck('uncheck');
	$('#radio24').iCheck('check');
});

//时间选择框
timebox('#datebox'); 
//demo必填文本框：添加清空文本框信息
$('#tt').textbox().textbox('addClearBtn', 'icon-clear');

//demo文本域：添加事件
$('#textarea').textbox('textbox').on('click', function(){  
	console.log(1);  
});

//复选框事件
$(".checkbox input").each(function(i,el){
	$(this).on('ifChecked', function(event){
		switch(i)
		{
			case 0:
			   console.log('第一个按钮已选中');
			   break;
			case 1:
			   console.log('第二个按钮已选中');
			   break;
			default:
			   //console.log(event.type);
		}
	});
	$(this).on('ifUnchecked', function(event){
		switch(i)
		{
			case 0:
			   console.log('第一个按钮已移除');
			   break;
			case 1:
			   console.log('第二个按钮已移除');
			   break;
			default:
			   //console.log(event.type);
		}
	});		
});

//复选框,单选框：添加皮肤
$('input[type="checkbox"],input[type="radio"]').iCheck({
	checkboxClass: 'icheckbox_minimal-blue',
	radioClass: 'iradio_minimal-blue',
	increaseArea: '20%'
});


//单选框、复选框样式
function checkboxAndRadioStyle() {
	$('input[type="checkbox"],input[type="radio"]').iCheck({
		checkboxClass: 'icheckbox_minimal-blue',
		radioClass: 'iradio_minimal-blue',
		increaseArea: '20%'
	});
}
			
```

## css配置

```css

//超出部分感叹号表示
h3.app-name {
	display: inline-block;
	max-width: 160px;
	overflow: hidden; /*超出部分隐藏*/
	text-overflow: ellipsis; /*超出部分感叹号显示*/
	white-space: nowrap; /*一行显示*/
}

//图片圆角
.user-info.user-avatar img {
	overflow: hidden;
	border-radius: 20px;
	width: 20px;
}

//获取浏览器宽度
function getBrowserWidth(){
	alert($(window).width());
}

//获取屏幕宽度
function getScreenWidth(){
	alert(window.screen.width);
	}

//css 悬浮事件
li:hover,.s1:hover{
		opacity:0.6; //透明度
		filter:alpha(opacity=60); /* IE8 及更早版本 */
		box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
		border-radius:20px;//边框圆角
		border:1px solid #aaa;
	}
ul{
	list-style-type:none;
	margin:0;
	padding:0;
}
li{
	display:inline-block;
}

```

## radio选中

```js
$("input[name='radioName']").on('ifChecked', function(event){
 test();
});

$().ready(function(){
		$("input[name='statisticsTimeRadio']").on('click', function(){
			var type = $("input[name='statisticsTimeRadio']:checked").attr("value");
			if(type==0){
				$('#statisticsTime').hide();
			}else{
				$('#statisticsTime').show();
			}
		});
	});
	
	$("#rdo1").prop("checked","checked");
	
	$(".selector").find("option[text='pxx']").attr("selected",true);
	
	
  function initCheckBox(){
			$('input[type="radio"],input[type="checkbox"]')
			.iCheck({
				checkboxClass : 'icheckbox_minimal-blue',
				radioClass : 'iradio_minimal-blue'
				});		
			$('input[name="checkall"]').on('ifChecked', function(event){
				$('input[type="checkbox"]').iCheck('check');
			});
			 $('input[name="checkall"]').on('ifUnchecked', function(event){
				$('input[type="checkbox"]').iCheck('uncheck');
			});	 
		}  
```

--- 

```js

// radio	
 $("input[name='display']").on('ifChecked', function(event){
	 if($('#display2').prop('checked')){
		 $("#perEdit1").iCheck('unCheck');
		 $("#perEdit2").iCheck('check');

	 }
});


// checkbox
$('#field6Box').on('ifChecked', function(event){
	$('#field1Box').iCheck('check');
});


$('#field6Box').on('ifUnchecked', function(event){
	if(!$('#field7Box').prop('checked')){
		$('#field1Box').iCheck('unCheck');
	}
});

```

## 正则表达式

```js	
/*是否带有小数*/
function    isDecimal(strValue )  {  
   var  objRegExp= /^\d+\.\d+$/;
   return  objRegExp.test(strValue);  
}  

/*校验是否中文名称组成 */
function ischina(str) {
    var reg=/^[\u4E00-\u9FA5]{2,4}$/;   /*定义验证表达式*/
    return reg.test(str);     /*进行验证*/
}

/*校验是否全由8位数字组成 */
function isStudentNo(str) {
    var reg=/^[0-9]{8}$/;   /*定义验证表达式*/
    return reg.test(str);     /*进行验证*/
}

/*校验电话码格式 */
function isTelCode(str) {
    var reg= /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/;  
    return reg.test(str);
}

var reg = /^(-){1}\d+|\d+$/;

/*校验邮件地址是否合法 */
function IsEmail(str) {
    var reg=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
    return reg.test(str);
}

特殊字符正则
var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]") ;
return !pattern.exec(value); //不包含

```

## validatebox

```js
身份证验证
 var idCard = function (value) {
        //if (value.length == 15) return true;
        var number = value.toLowerCase();
        var d, sum = 0, v = '10x98765432', w = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2], a = '11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91';
        var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/);
        if (re == null || a.indexOf(re[1]) < 0) return false;
        if (re[2].length == 9) {
            number = number.substr(0, 6) + '19' + number.substr(6);
            d = ['19' + re[4], re[5], re[6]].join('-');
        } else d = [re[9], re[10], re[11]].join('-');
        if (!isDateTime.call(d, 'yyyy-MM-dd')) return false;
        for (var i = 0; i < 17; i++) sum += number.charAt(i) * w[i];
        return (re[2].length == 9 || number.charAt(17) == v.charAt(sum % 11));
    }		
	
	
<div><input  id="email" name="email" value=""  style="padding:4px;float:left;width: 250px;height:18px;border-radius:4px;border:1px solid #ccc;"
 validType="validEmail"  class="easyui-validatebox validatebox-text"  panelHeight="auto"></div>
 
validBirthDate

$.extend($.fn.validatebox.defaults.rules, {
	validEmail: {
		validator: function(value, param) {
			var regEx = /^[a-zA-Z0-9_]{1,}@(([a-zA-z0-9]){1,}\.){1,3}(com|cn|net)$/;
			var a = regEx.test(value);
			return a;
		},
		message: '请输入正确邮箱格式'
	}
});
```

```js
$.extend($.fn.validatebox.defaults.rules, {
 phoneValidType: {
	  validator: function(value, param) {
		  return /^1[3|4|5|6|7|8|9][0-9]{9}$/.test(value);
	  },
	  message: '请输入正确的手机号码'
  }
});

$.extend($.fn.validatebox.defaults.rules, {
 emailValidType: {
	  validator: function(value, param) {
		 if(value.indexOf('*')>0){
			 return false;
		 }
		 //var reg = /^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$/;
		 //return !reg.test(value);
		 return /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(value);
	  },
	  message: '请输入有效的电子邮件地址'
  }
});

$.extend($.fn.validatebox.defaults.rules, {
	 telephoneValidType: {
			validator: function (value, param) {
			   return /^((0(((\d{2,3})-(\d){7,8})|(\d{2,3}(\d){7,8}))))$/.test(value);
			},
			message: '联系电话不正确'
	 }
 });

```

