# tomcat笔记

<br />

> tomcat myeclipse 

`--20170329`

## 安装配置

| 软件                              | 下载地址   |
| --------                          | -----      |
| tomcat   | [tomcat官网]( https://archive.apache.org/dist/tomcat/ ) &ensp; ([云盘]( https://pan.baidu.com/s/1yPhAfIcACTGkpIOYlEds1g )  密码: j9ug ) |
| eclipse  | [eclipse官方下载]( http://www.eclipse.org/downloads/packages )    [版本说明]( ./eclipse.md ) |

```
tomcat环境变量配置
创建  TOMCAT_HOME   E:\ProgramFiles\apache-tomcat-8.5.31
添加  CLASSPATH     %TOMCAT_HOME%\BIN;

验证 
启动 bin/startup.bat 
访问 http://localhost:8080/ （或 http://127.0.0.1:8080/ ）

```


```java
http://blog.csdn.net/al_assad/article/details/76383107
tomcat配置JAVA_HOME

编辑 Tomcat根目录/bin/setclasspath.sh ，在文件的开头添加以下

export JAVA_HOME=/usr/lib/jvm/java-openjdk  
export JRE_HOME=/usr/lib/jvm/java-openjdk/jre  


其中JAVA_HOME,JRE_HOME的值添加为实际的路径；

之后再重新启动Tomcat，返回信息正确：

    Using CATALINA_BASE:   /apache-tomcat-8.5.16  
    Using CATALINA_HOME:   /apache-tomcat-8.5.16  
    Using CATALINA_TMPDIR: /apache-tomcat-8.5.16/temp  
    Using JRE_HOME:        /usr/lib/java-1.8.0-opendjk/jre  
    Using CLASSPATH:       /apache-tomcat-8.5.16/bin/bootstrap.jar:/apache-tomcat-8.5.16/bin/tomcat-juli.jar  
    Tomcat started.  
	
```


```java

================== 服务器布署多个tomcat ===================
	1.HTTP端口，默认8080，如下改为8081
	[html] view plain copy

		<Connector port="8081" protocol="HTTP/1.1"   
					   connectionTimeout="60000"   
					   redirectPort="8443" disableUploadTimeout="false"  executor="tomcatThreadPool"  URIEncoding="UTF-8"/>  


	2.远程停服务端口，默认8005，如下改为8006
	[html] view plain copy

		<Server port="8006" shutdown="SHUTDOWN">......  


	3.AJP端口，默认8009，如下改,8010
	[html] view plain copy

		<Connector port="8010" protocol="AJP/1.3" redirectPort="8443" />  
```		
		
---


```		

myeclipse中tomcat常见问题及解决方法：


-----  问题1：在myeclipse中布署tomcat 及 修改tomcat端口
-----  问题2：copy当前myeclippse项目要注意的问题
-----  问题3：The Tomcat... Server at localhost-config is missing  --servers丢失
-----  问题4：Several ports (8005，8080, 8009) ....are already in use  ---端口被使用
-----  问题5：Could not publish server.....     Two or more Web modules defined in the 			configuration have  不能发布服务器
-----  问题6：myeclipse复制项目后引用的url还是原来的项目名称
-----  问题7：exploded archive packaged archive 区别
-----  问题8：警告: Unable to load server configuration from [H:\workspace\.metadata\.plugin....
-----  问题9：xml无法自动提示
-----  问题10：myeclipse里面有许多tomcat的localhost命名，请问怎么删除
-----  问题11：myeclipse中xml 默认使用myeclipse editor打开
-----  问题12：Setting property 'IEncoding' to 'utf-8' did not find a matching property.
-----  问题13：A context path must either be an empty string or start with a '/' and do not end with a '/'.
-----  问题14：run as on server 和 add deployment的区别
-----  问题15：Setting property 'source' to 'org.eclipse.jst.jee.server:ext3.0' did not find a matching property.

```	

---
	
## myeclipse布署tomcat		

```
====================================================================================
问题1： 在myeclipse中布署tomcat 及 修改tomcat端口

在myeclipse中布署tomcat：
window-perference--输入tomcat--进入tomcat7.0，然后将自己安装目录添加进去.选择Enable.
我们找到Myeclipse自带的tomcat项，将自带的tomcat设为禁用(disable).
点击tomcat7.x，就是我们自己添加的tomcat，我们将tomcat7.x的jdk设置为自己安装的jdk，为了统一。
点击apply，然后点击ok.

修改tomcat端口：
在安装目录G:\tomcat-7\conf中，打开server.xml文件（备份一个）
找到    <Connector port="8080" protocol="HTTP/1.1"
将8080修改掉，如 <Connector port="8888" protocol="HTTP/1.1" 即可

```
	
## 复制myeclippse项目		

```java

====================================================================================
问题2：copy当前myeclippse项目要注意的问题

方法：
注意 1.run as 选择server时需要removeAll清除server里原来的项目，否则会出现问题无法发布服务器

注意 2.myeclipse复制项目后引用的url如果还是原来的项目名称。
若 myeclipse复制项目后引用的url还是原来的项目名称
方法：
Properties------Web Project Setting-------（Server.xml）
1--如果新闻网站项目一个用的servlet(showNews)，另一个是用框架修改的(showNews_framework)，但是在运行后者的jsp文件的时候路径确是showNews/***

2--这时右键复制过的项目(此处为showNews_framework),选择Properties，或者选中该项目点击Alt+Enter，进入属性设置界面

3--找到下面的Web Project Setting，注意看这里的Context root是不是你的项目名称，比如我的就还是之前的项目名：showNews

4--如果不是，就把这里的Context root改为你复制后自己起的项目名称，这里为
showNews_framework

5--该过之后注意会弹出确认修改配置文件的框，注意这里需要选择是，然后重启服务器即可

6--注意事项
如果还不行请查看Server项目中的Server.xml文件最下面有没有一个Context标签，把其中docBase="showNews_framework" path="/showNews_framework"的值改为相同

http://jingyan.baidu.com/article/642c9d34ca8cba644a46f71b.html

```
	
## localhost-config is missing		

```js


====================================================================================
问题3： The Tomcat server configuration at \Servers\Tomcat v5.5 Server at localhost-config is missing
-- Server丢失

方法：
删除Tomcat Server,然后在Servers视图里边，再New一个Server.

```
	
## already in use	

```java

====================================================================================
问题4：Several ports (8005，8080, 8009) required by Tomcat v6.0 Server at localhost are already in use. The server may already be running in another process, or a system process may be using the port. To start this server you will need to stop the other process or change the port number(s).   
-- 端口被使用

方法：
------- 启动任务管理器---停掉javaw.exe

```
	
## Could not publish	

```npm

====================================================================================
问题5：
Could not publish server configuration for Tomcat v7.0 Server at localhost.
无法发布服务器配置Tomcat v7.0服务器在本地

Two or more Web modules defined in the configuration have the same context root (/Strust2_020_Introduction). To start this server you will need to remove the duplicate(s).
两个或两个以上的Web模块中定义的配置有相同背景的根（/ strust2_020_introduction）。要启动此服务器，您将需要删除重复的副本（S）。

-- 因为tomcat为项目建立服务器的配置出现错误，一个tomcat中有两个项目，发生冲突。

方法：
一：项目布署在本地的tomcat中，不能用右键--Run as 启动，使用servers 里的Run server启动。

二：
重新为项目配置tomcat以清空tomcat中其他项目。
步骤：将项目改名----右键----Run As---- Run On Server ---- Choose an existing server(Always use this server...) --- Next--- Remove All ---- Finish

三：
1、将D:\Test\Servers\Tomcat v5.5 Server at localhost-config\server.xml当中新加入的错误Context删除，同时将D:\Test\test\.settings\org.eclipse.wst.common.component当中的deploy-name和property name的键值改为新项目的名称，重新加入tomcat的服务，问题解决。
2、如果你使用的是eclipse启动tomcat，在自己下载的 tomcat中找不到上面的这种情况，那么就到你项目的eclipse中的workspace中去找，如：....\项目名\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\conf文件下面找到server.xml文件，然后向第一种方法一样删除重复的 
<Context docBase="项目名" path="/项目名"reloadable="true" source="org.eclipse.jst.j2ee.server:项目名"/>

```
	
## myeclipse复制项目后引用		

```html

====================================================================================
问题6：myeclipse复制项目后引用的url还是原来的项目名称

方法：
Properties------Web Project Setting-------（Server.xml）
1--如果新闻网站项目一个用的servlet(showNews)，另一个是用框架修改的(showNews_framework)，但是在运行后者的jsp文件的时候路径确是showNews/***

2--这时右键复制过的项目(此处为showNews_framework),选择Properties，或者选中该项目点击Alt+Enter，进入属性设置界面

3--找到下面的Web Project Setting，注意看这里的Context root是不是你的项目名称，比如我的就还是之前的项目名：showNews

4--如果不是，就把这里的Context root改为你复制后自己起的项目名称，这里为
showNews_framework

5--该过之后注意会弹出确认修改配置文件的框，注意这里需要选择是，然后重启服务器即可

6--注意事项
如果还不行请查看Server项目中的Server.xml文件最下面有没有一个Context标签，把其中docBase="showNews_framework" path="/showNews_framework"的值改为相同

http://jingyan.baidu.com/article/642c9d34ca8cba644a46f71b.html

```
	
## exploded archive packaged archive		

```shell

====================================================================================
问题7：exploded archive packaged archive 区别
1. exploded archive (development mode) 
这种叫开发模式这是直接把文件夹,jsp页面 ,classes等等移到tomcat 部署文件夹里面,进行加载部署 
2. Packaged Archive(production mode) 
这种叫发布模式,这是先打成war包,再发布

```
	
## Unable to load server configuration 		

```

====================================================================================
问题8：警告: Unable to load server configuration from [H:\workspace\.metadata\.plugin....
 Unable to load server configuration from [H:\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\conf\server.xml]
--jar包有问题，重新导入jar包
--或者清空项目，Project--Clean--Clean all projects 。重新运行tomcat。
--重启myeclipse

http://bbs.csdn.net/topics/340035300

```
	
## xml无法自动提示		

```xml

====================================================================================
问题9：xml无法自动提示

方法：
--选择xml文件---Open With----Default Editor---(左下角）Source
--进入H:\软件\struts-2.3.31-all\struts-2.3.31\lib 解压struts2-core-2.3.31.jar，找到struts-2.3.dtd这个文件，Window>>Preferences，输入catalog，找到XML Catalog，选择add
Location--File System 
如下
Location：H:\MaShiBingJava\struts2\struts2-core-2.3.31\struts-2.3.dtd
Key type :URL
Key: http://struts.apache.org/dtds/struts-2.3.dtd
（注意：struts2-core-2.3.31.jar 解压的目录不能有中文）

```
	
## 删除localhost命名		

```sql

====================================================================================
问题10：myeclipse里面有许多tomcat的localhost命名，请问怎么删除
window-show View - others-- 搜索servers-- 选择WTP servers  ----delete

```
	
## xml 默认使用myeclipse editor打开		

```php

====================================================================================
问题11：myeclipse中xml 默认使用myeclipse editor打开
window-->preferences-->general-->editors-->file association-->*.xml
在下面选择一种打开方式 设置DEFAULT

```
	
## Setting property 'IEncoding' to 'utf-8'		

```

====================================================================================
问题12：Setting property 'IEncoding' to 'utf-8' did not find a matching property.
原因：Tomcat的 server.xml端口有两个8080端口设置
<Connector port="8080" protocol="HTTP/1.1" .....
打开Tomcat目录/conf/server.xml  删掉一个

```
	
## A context path must either be an empty string

```c

====================================================================================
问题13：A context path must either be an empty string or start with a '/' and do not end with a '/'
上下文路径必须是一个空字符串，或者以“/”开头，不要以“/”结尾。

原因：tomcat的server.xml中添加了多余的无效的context标签，将其删除即可。
举例：
我出现的问题是：
十一月 23, 2016 5:17:05 下午 org.apache.catalina.core.StandardContext setPath
警告: A context path must either be an empty string or start with a '/' and do not end with a '/'. The path [Struts2_071_ActionParamInput] does not meet these criteria and has been changed to [/Struts2_071_ActionParamInput]

原来我的tomcat的server.xml文件中出现了这一段（之前手动加入的，没有删除）
	<Context path="Struts2_071_ActionParamInput" docBase="G:\tomcat-7\webapps\Struts2_071_ActionParamInput\WebRoot" reloadable="true"/>
将其删除即可。
提示：server.xml在tomcat的config目录下。

```
	
## run as on server 和 add deployment的区别

```java

问题14：run as on server 和 add deployment的区别
--- run as on server是在myeclipse中建立临时的tomcat，配置信息在
H:\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp3\conf目录server.xml中。
（tmp3是随机名称，也许是temp0、temp1、tem2等等）
项目在H:\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp3\wtpwebapps目录。
下一次启动run as on server 时之前存在的项目会被删除。当前只存在一个项目。

--- add deployment是把项目复制到本地tomcat文件中，
配置信息在本地tomcat的安装目录G:\tomcat-7\conf目录的server.xml中。
项目在G:\tomcat-7\webapps目录,一直存在。


====================================================================================

Setting property 'source' to 'org.eclipse.jst.jee.server:ext3.0' did not find a matching property.
环境：Tomcat 7.0.69 + myeclipse 10
原因：设置属性“source” 没有找到匹配的属性。
方法：删除临时的tomcat，重新发布。
步骤：
-----1. 找到此项目发布的临时的tomcat目录，一般在\workspace\.metadata\.plugins\org.eclipse.wst.server.core目录
-----2. 删除...core目录下所有文件
-----3. 找到tomcat目录里的conf目录里的Catalina目录里有个localhost文件夹，把文件夹里面的文件全删除掉。
-----4. 清空项目，Project--Clean--Clean all projects 。
-----5. Run as on server 启动tomcat
	若出现Unable to load server configuration from [H:\workspace\.metadata\.plugin.... 
           则重启myeclipse 再Run as on server 启动tomcat。
参考自http://blog.csdn.net/z69183787/article/details/19911935

```