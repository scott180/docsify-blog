# linux笔记-xyq

<br />

- [x]  2017.08.22--now
`xyq` `linux` `note`

>  [blog]( https://blog.xushufa.cn ) &ensp; [gitlab]( https://gitlab.com/xuyq123/mynotes ) &ensp; [gitee]( https://gitee.com/xy180/MyNotes ) 


## 1 常用命令

说明                | 命令
-----------------   | -----------------------------------------------------------------  
进入目录       		| cd dir1
创建目录       		| mkdir dir1   
创建多级目录  		| mkdir -p  d1/d2/d3   
查看当前目录		| pwd
复制文件       		| cp srcname  targetname
复制目录 			| cp -r dir1/ dir2/
修改名称(移动文件)  | mv readme.txt readme.doc
跨服务器复制        | scp /data/ROOT.tar.gz root@192.168.239.35:/opt/saiwen/db_backup
删除普通文件a.txt   | rm a.txt (-f:表示强制)
目录a删除           | rm -rf a       (-f:表示强制; -r:表示目录)
建立新文件  		| touch test.txt
清空文件            | cat /dev/null >json.log
`-----------------` | `--------------------------------------------------------------`  
查看磁盘空间     	| df -h
查看文件大小        | du -h filepath
显示文件或目录类型	| file test
查询程序的位置	    | which test
统计文件信息	    | wc testfile
3 92 598 testfile   | testfile文件的行数为3、单词数92、字节数598 
`-----------------` | `--------------------------------------------------------------`  
压缩tar   | tar -zcvf /home/love.tar.gz /home/yx/love
解压tar   | tar -zxvf /home/love.tar.gz
压缩zip   | zip  test.zip  test
解压zip   | unzip test.zip
压缩gz    | gzip -c test.log > /root/test.gz
解压gz    | gunzip -c debug.2020-07-02.log.gz > ./0702.log
`-----------------` | `--------------------------------------------------------------`  
模糊查找当前目录文件   	 | find *txt
从根目录查找文件         | find / -name test 
查找文件               	 | find /home -name 'test.log' -type f -print
查找目录                 | find / -name 'tech' -type d -print
查找当前目录及子目录文件 | find . -name "*root*" -maxdepth 1  （maxdepth指层数）
查找大文件               | find / -type f -size +400M | xargs ls -hlrt
`-----------------` | `--------------------------------------------------------------`  
从旧到新并显示大小 | ls -hlrt （ls -lrt 从旧到新） 
从新到旧并显示大小 | ls -hlt  （ls -lt  从新到旧 ）
按大小升序		   | ls -hSlr
按大小降序		   | ls -hSl
模糊查找文件	   | ls name*  (ls /etc/rc.d/init.d/my*)
显示当前目录文件   | ls
`-----------------` | `--------------------------------------------------------------`  
查看linux版本      	| cat /proc/version      lsb_release -a
查看linux内核版本   | uname -a
查看centos版本 	    | cat /etc/redhat-release
查看java版本        | java -version
查看进程			| ps 
查看tomcat进程      | ps -ef | grep tomcat
`-----------------` | `--------------------------------------------------------------` 
上传				| sz filename   (安装上传下载 yum install lrzsz)
下载    			| sz
显示10行历史记录	| history 10
查看ip             	| ifconfig
清楚屏幕           	| clear
查看时间           	| date
时间格式化          | date "+%Y-%m-%d %H:%M:%S"
查看指定年月日历   	| cal 3 2013
建立链接 			| ln -fs /opt/tech/mysql/bin/mysql /usr/local/bin/mysql
删除链接 			| rm -rf name
查看所有别名   		| alias
添加别名       		| alias test="tar -zcvf "
删除别名       		| unalias test
显示所有分区的信息	| fdisk -l 
帮助				| help
查看命令手册 		| man ls  
树状结构展示目录	| tree   (安装tree命令 yum  install tree)  
输出重定向(保存文件)| ls > dir.txt
追加文件            | ls >> dir.txt 
`-----------------` | `--------------------------------------------------------------` 
关机				| halt              
重启       			| reboot  
关机重启        	| shutdown -r
关机不重启        	| shutdown -h
立刻关机        	| shutdown now


********************************************************


## 2 命令说明

### 2.1 vi操作

```java
:wq (输入「wq」，存盘并退出vi)
:q! (输入q!， 不存盘强制退出vi)
:set fileencoding  查看文件编码
:set nu 显示行数

查找  /关键字
按「G」：移动到文章的最后。
按「g」：移动到文章的开头。
dd  : 删除当前行
x   : 删除光标位置字符
u   ：后退 返回 上一步

/string 向前搜索指定字符串
?string 向后搜索指定字符串
n 搜索指定字符串的下一个出现位置
N 搜索指定字符串的上一个出现位置
:%s/old/new/g 全文替换指定字符串 
```

********************************************************

### 2.2 环境变量export

```python
查看所有环境变量    export
查看环境变量 	    echo $PATH
				    export $hello
					
设置临时环境变量    export PATH=$PATH:/usr/local/mysql/bin
					export hello="hello world"

设置系统环境变量	
	vi /etc/profile

		export PATH=$PATH:/usr/local/mysql/bin  # 在配置文件中加入此行配置
		export hello="hello world"  # 在配置文件中加入此行配置

	需要注意的是：修改完这个文件必须要使用 以下命令在不用重启系统的情况下使修改的内容生效。

	source /etc/profile

	或者是用 ‘.’：

	. /etc/profile

	查看：
	echo $PATH
		/usr/kerberos/sbin:/usr/kerberos/bin:/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin:/root/bin:/usr/local/mysql/bin
	
	echo $hello
		hello world
	
	配置已经生效
	
```

********************************************************

### 2.3  head tail less more 

``` haskell
1. 如果你只想看文件的前100行，可以使用head命令，如
head -100  filename
2. 如果你想查看文件的后100行，可以使用tail命令，如：
tail -100  filename 或 tail -n 100  filename
3. 查看文件中间一段，你可以使用sed命令，如：
sed -n '100,200p' filename 
这样你就可以只查看文件的第100行到第200行。

截取的文件可以用重定向输入到新的文件中：
head -100  filename >a.txt

tail -f cata.log  打印日志

cat  test.txt        显示文件开头
tac  test.txt        显示文件结尾
more test.txt        逐页显示文件 
less test.txt        逐页显示文件（优化more）
head -n 20 test.txt  显示文件前20行  head -100  filename
tail -n 20 test.txt  显示文件后20行  tail -100  filename 


如果文件太长，用cat命令只能看到文件的最后一页，而用more命令时可以一页一页地显示。执行more命令后，进入more状态，用【Enter】键可以向后移动一行；用【Space】键可以向后移动一页；用“q”键可以退出。在more状态下还有许多功能，可用man more命令获得。

less实际上是more的改进版，其命令的直接含义是more的反义。less的功能比more更灵活。例如：用【Pgup】键可以向前移动一页，用【Pgdn】键可以向后移动一页，用向上光标键可以向前移动一行，用向下光标键可以向后移动一行。“q”键、【Enter】键、【Space】键的功能和more类似。

用【G】键可以移动文件到结尾，用【g】键可以移动到文件开头。
```

********************************************************************

### 2.4 cat详解

```java
cat命令是linux下的一个文本输出命令，通常是用于观看某个文件的内容。
cat主要有三大功能：
	1.一次显示整个文件。
		$ cat   filename
		
	2.从键盘创建一个文件。
		$ cat  >  filename                                     
		只能创建新文件,不能编辑已有文件
		
	3.将几个文件合并为一个文件。
		$cat   file1   file2  > file


	cat具体命令格式为 : cat [-AbeEnstTuv] [--help] [--version] fileName
		说明：把档案串连接后传到基本输出(屏幕或加 > fileName 到另一个档案)
		参数：
		-n 或 –number 由 1 开始对所有输出的行数编号
		-b 或 –number-nonblank 和 -n 相似，只不过对于空白行不编号
		-s 或 –squeeze-blank 当遇到有连续两行以上的空白行，就代换为一行的空白行
		-v 或 –show-nonprinting
		范例：
		cat -n linuxfile1 > linuxfile2 把 linuxfile1 的档案内容加上行号后输入 linuxfile2 这个档案里
		cat -b linuxfile1 linuxfile2 >> linuxfile3 把 linuxfile1 和 linuxfile2 的档案内容加上行号(空白行不加)之后将内容附加到linuxfile3 里。
		范例：
		把 linuxfile1 的档案内容加上行号后输入 linuxfile2 这个档案里
		cat -n linuxfile1 > linuxfile2
		把 linuxfile1 和 linuxfile2 的档案内容加上行号(空白行不加)之后将内容附加到 linuxfile3 里。
		cat -b linuxfile1 linuxfile2 >> linuxfile3
	
	
	cat /dev/null > /etc/test.txt 此为清空/etc/test.txt档案内容
		
----------------------------------------------------------

cat << EOF的语句说明
	EOF是“end of file”，表示文本结束符。EOF在这里没有特殊的含义，你可以使用FOE或OOO等（当然也不限制在三个字符或大写字符）。

简单描述一下常见的使用方式及其作用：
	1、cat<<EOF，以EOF输入字符为标准输入结束：
	2、cat>filename，创建文件，并把标准输入输出到filename文件中，以ctrl+d作为输入结束：
	注意：输入时是没有'>'的。
	3、cat>filename<<EOF，以EOF作为输入结束，和ctrl+d的作用一样。

命令：
	$ cat > test.txt << EOF
	> 说明cat及EOF
	> 测试
	> EOF
	
	$ cat test.txt
	说明cat及EOF
	测试

其他写法：
	cat >> test.txt << EOF 在test.txt里追加内容，不会覆盖原有文件。

	$ cat >> test.txt << END     //这里的“END”就代替了“EOF”的功能。结果是相同的。
	> 追加内容
	> END

	$ cat test.txt
	说明cat及EOF
	测试
	追加内容
```

********************************************************************

### 2.5 grep查询 

[grep命令]( http://www.cnblogs.com/end/archive/2012/02/21/2360965.html )
 
``` vb
查询文件中内容并保存
	more linux.txt | grep mysql > test.txt      // > 创建新文件
	cat linux.txt | grep 软件 >> test.txt      // >> 是在文件中追加内容
	
	grep mysql linux.txt > test.txt
	
查询文件内容总行数
	grep "add" -c gitNote.md
	cat gitNote.md | grep -c "add"
	
	
查询文件内容显示行
	grep "add" -n gitNote.md
	cat gitNote.md | grep -n "add"
	

	
查询运行的进程
	ps -ef | grep mysql
	ps -ef | grep java
		-e   显示所有进程。
		-f    全格式。
		
		
grep '^root' /etc/group 匹配正则表达式的开始行 
grep 'root$' /etc/group 匹配正则表达式的结束行 

RE（正则表达式） 
\ 忽略正则表达式中特殊字符的原有含义 
^ 匹配正则表达式的开始行 
$ 匹配正则表达式的结束行 
\< 从匹配正则表达式的行开始 
\> 到匹配正则表达式的行结束 
[ ] 单个字符；如[A] 即A符合要求 
[ - ] 范围 ；如[A-Z]即A，B，C一直到Z都符合要求 
. 所有的单个字符 
* 所有字符，长度可以为0 
```

********************************************************************

### 2.6 查看内存

``` haskell
top
free -m  
cat /proc/meminfo 机器的内存使用信息
cat /proc/pid/maps pid为进程号，显示当前进程所占用的虚拟地址。
cat /proc/pid/statm 进程所占用的内存

[root@localhost webapps]# free 
             total       used       free     shared    buffers     cached
Mem:       4043716    3783532     260184          0      31424     340132
-/+ buffers/cache:    3411976     631740
Swap:      2096472     993204    1103268

输入：free

total:总计物理内存的大小

used:已使用多大

free:可用有多少

Shared:多个进程共享的内存总额

Buffers/cached:磁盘缓存的大小

第三行(-/+ buffers/cached)

used:已使用多大

free:可用有多少

第四行就不多解释了。

区别：第二行(mem)的used/free与第三行(-/+ buffers/cache) used/free的区别。 这两个的区别在于使用的角度来看，第一行是从OS的角度来看，因为对于OS，buffers/cached 都是属于被使用，所以他的可用内存是260184KB,已用内存是3783532KB,第三行所指的是从应用程序角度来看，对于应用程序来说，buffers/cached 是等于可用的，因为buffer/cached是为了提高文件读取的性能，当应用程序需在用到内存的时候，buffer/cached会很快地被回收。所以从应用程序的角度来说，可用内存=系统free memory+buffers+cached。

 如上例：631740=260184+31424+340132
```

********************************************************************

## 3 软件操作及安装  

### 3.1  mysql

```java
指令 ps -ef|grep mysql 得出结果
    root     17659     1  0  2011 ?        00:00:00 /bin/sh /usr/bin/mysqld_safe --datadir=/var/lib/mysql --socket=/var/lib/mysql/mysql.sock --log-error=/var/log/mysqld.log --pid-file=/var/run/mysqld/mysqld.pid   
    mysql    17719 17659  0  2011 ?        03:14:57 /usr/libexec/mysqld --basedir=/usr --datadir=/var/lib/mysql --user=mysql --pid-file=/var/run/mysqld/mysqld.pid --skip-external-locking --socket=/var/lib/mysql/mysql.sock  

usr/bin/mysql 是指：mysql的运行路径
var/lib/mysql 是指：mysql数据库文件的存放路径
usr/lib/mysql 是指：mysql的安装路径 


导出数据库 /opt/tech/mysql/bin/mysqldump -uroot -p1234 webpro > /opt/tech/20170814.sql
 
导入数据  mysql -uroot -pSai.web123 YCKPLUS < /opt/sql/uccpplus_v4_0_5.sql

mysql导入时出现"ERROR at line : Unknown command '\''."的解决办法
		 mysql -uroot -p12344  --default-character-set=utf8 IMP_V12_1 < E:\isds-1.1.2.sql

导出查询语句 /opt/saiwen/mysql/bin/mysql -uroot -p12344 -e "use YCKPLUS;select id,loginName from T_USER where id=1\G;" >> /opt/test.txt

导出表   /opt/tech/mysql/bin/mysqldump -uroot -p1234 webpro t_user > /opt/tech/t_user.sql

创建数据库
CREATE DATABASE YCKPLUS DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use YCKPLUS;
 
登录mysql  /opt/tech/mysql/bin/mysql -uroot -p1234 
           /opt/tech/mysql/bin/mysql -uroot -padmin

创建、使用数据库   create database test;  use test;
导入sql数据 	source /root/20151010.sql


复制表数据到新表
CREATE TABLE T_MENU1 LIKE T_MENU;
INSERT INTO T_MENU1 SELECT * FROM T_MENU;

/opt/tech/apache-tomcat-6.0.45/webapps/add20170814.sql

修改max_allowed_packet  vi /etc/my.cnf

58.246.98.94
mysql -u root -p12344



navicat连接mysql失败，授权：
GRANT ALL PRIVILEGES ON `db1`.* TO 'user1'@'192.171.1.18' identified by 'pwd1' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON `YCKPLUS`.* TO 'saiwen'@'192.171.1.18' identified by 'test12344' WITH GRANT OPTION;

grant all privileges on *.* to root@'%' identified by 'saiwen.web123' with grant option;

-- flush privileges;


查询权限
select * from mysql.user
select Host,User,password_last_changed from mysql.user

取消权限
revoke  all on *.* from 'root'@'192.168.0.197' ;

删除用户权限
Delete from mysql.user where user = "user_name" and host = "host_name" ; 


 
linux 的mysql配置文件  /etc/my.cnf

查看编码
show variables like 'character%'; 

设置编码
set character_set_server='utf8';  

查看sql_mode
SELECT @@GLOBAL.sql_mode;

识别大小写
lower_case_table_names = 2

启动mysql
  /opt/saiwen/mysql/support-files/mysql.server start
  
  /opt/saiwen/mysql/bin/mysqld_safe --user=mysql --basedir=/opt/saiwen/mysql --datadir=/opt/saiwen/mysql/data & 
  
  cd /opt/saiwen/mysql/
  ./bin/mysqld_safe &
  
  
  chmod -R 775 mysql
  
  
  cd /data/saiwen/mysql/&&./bin/mysqld_safe &
  cd /opt/saiwen/mysql/ && bin/mysqld_safe --user=root &
  
```

********************************************************************

### 3.2 mongo  

```
配置文件启动
cd /opt/saiwen/mongodb           
./bin/mongod --config /opt/mongodb/conf/mongo.conf 
	
自定义路径启动										 
  /opt/saiwen/mongodb/bin/mongod --dbpath=/opt/saiwen/mongodb/data --logpath=/opt/saiwen/mongodb/logs --logappend  --port=27017 --fork
  
登陆mongo
cd /opt/saiwen/mongodb/bin
mongo
```

********************************************************************

### 3.3 redis

``` 
windows下redis安装 
http://www.runoob.com/redis/redis-install.html
进入redis目录
启动  redis-server.exe redis.windows.conf

登录  redis-cli.exe -h 127.0.0.1 -p 6379
	  redis-cli -a saiwen12344


Redis 设置密码登录 
修改redis.windows.conf
#requirepass foobared。设置密码的方法就是去掉注释的#，把foobared替换成自己的密码即可，例如将密码设置为123456


服务自启动
redis-server --service-install redis.windows.conf --loglevel verbose 


问题 Invalid argument during startup: unknown conf file parameter : requirepass
redis 设置来密码，出现来这个错误，
把配置文件那一行的空格去掉
# requirepass foobared
改成
requirepass 123456


启动redis
./redis/src/redis-server ./redis/redis.conf &

关闭redis
./redis/src/redis-cli -h host -p port -a password shutdown
host值在./redis/redis.conf文件中搜索bind
port值在./redis/redis.conf文件中搜索port
password值在/redis/redis.conf文件中搜索requirepass

进入redis命令行
./redis/src/redis-cli -h host -p port -a password



[root@localhost src]# cd /opt/saiwen/custom/redis-2.8.17/
[root@localhost redis-2.8.17]# redis-server redis.conf              //启动redis                 
[root@localhost ~]# redis-cli       //进入redis客户端
127.0.0.1:6379> keys *              //取出所有的key 
(empty list or set)
127.0.0.1:6379> quit                //退出客户端   

127.0.0.1:6379> set key1 value1		//set
OK
127.0.0.1:6379> get key1			//get
"value1"
127.0.0.1:6379> del k1				//删除一个key
(integer) 1
127.0.0.1:6379> flushdb				//清空所有数据



Exception in thread "main" redis.clients.jedis.exceptions.JedisDataException: DENIED Redis is running in protected mode because protected mode is enabled。。。。

进入客户端设置模式：
redis-cli
config set protected-mode "no"

```

********************************************************

### 3.4 tomcat命令 

```vb
Linux下Tomcat的启动、关闭、杀死进程   （进入tomcat的bin目录 启动 sh startup.sh）
	打开终端
	进入tomcat目录
	cd /Java/tomcat
	#执行
	bin/startup.sh #启动tomcat
	bin/shutdown.sh #停止tomcat
	tail -f logs/catalina.out #看tomcat的控制台输出；

	#看是否已经有tomcat在运行了
	ps -ef |grep tomcat 
	#如果有，用kill;
	kill -9 pid #pid 为相应的进程号

	例如 ps -ef |grep tomcat 输出如下

	sun 5144 1 0 10:21 pts/1 00:00:06 /java/jdk/bin/java -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager -Djava.endorsed.dirs=/java/tomcat/common/endorsed -classpath :/java/tomcat/bin/bootstrap.jar:/java/tomcat/bin/commons-logging-api.jar -Dcatalina.base=/java/tomcat -Dcatalina.home=/java/tomcat -Djava.io.tmpdir=/java/tomcat/temp org.apache.catalina.startup.Bootstrap start

	则 5144 就为进程号 pid = 5144
	kill -9 5144 就可以彻底杀死tomcat
	
查看tomcat
	ps -ef |grep tomcat 
杀死tomcat 
	kill -9 pid        //pid 为相应的进程号
启动tomcat
    /opt/tech/appStore/apache-tomcat-6.0.35/bin/startup.sh

```

********************************************************************

### 3.5 为每个tomcat配置单独的jdk

``` 
一、安装jdk，如jdk-6u45-linux-x64.bin
1、添加执行权限 
	chmod u+x jdk-6u45-linux-x64.bin
2、解压 
	./jdk-6u45-linux-x64.bin

二、配置tomcat的 ../bin/setclasspath.sh	在文件的开头添加以下
export JAVA_HOME=/opt/saiwen/jdk1.6.0_45  
export JRE_HOME=/opt/saiwen/jdk1.6.0_45/jre

三、重启tomcat



部署多个tomcat主要修改三个端口：

1.HTTP端口，默认8080，如下改为8081
<Connector port="8081" protocol="HTTP/1.1" 
               connectionTimeout="60000" 
               redirectPort="8443" disableUploadTimeout="false"  executor="tomcatThreadPool"  URIEncoding="UTF-8"/>


2.远程停服务端口，默认8005，如下改为8006
<Server port="8006" shutdown="SHUTDOWN">......


3.AJP端口，默认8009，如下改,8010
<Connector port="8010" protocol="AJP/1.3" redirectPort="8443" />

```

********************************************************************


### 3.6 yum rpm安装卸载软件 

```vb
yum安装：
       # yum install 包名
yum卸载：
       # yum -y remove 包名
	   
查询所有安装软件     rpm -qa
查看是否安装软件     rpm -qa | grep java	  
卸载软件			 rpm -e    如果提示有依赖，可以加上 --nodeps 

[root@localhost openldap]# rpm -qa | grep openldap
openldap-2.4.44-15.el7_5.x86_64
openldap-servers-2.4.44-15.el7_5.x86_64
compat-openldap-2.3.43-5.el7.x86_64

[root@localhost openldap]# rpm -e --nodeps openldap-2.4.44-15.el7_5.x86_64
[root@localhost openldap]# rpm -e --nodeps openldap-servers-2.4.44-15.el7_5.x86_64
[root@localhost openldap]# rpm -e --nodeps compat-openldap-2.3.43-5.el7.x86_64
```

********************************************************************

### 3.7 make安装卸载软件

``` 
用于linux源码安装软件，一般下载源码包得到文件：xxxx.tgz

1、解包软件
tar zxf xxxx.tgz

2、配置
cd xxxx
./configure ....

3、编译
make

4、安装
make install

5、卸载
make uninstall

```

********************************************************************

### 3.8 nginx命令

```
启动
sudo /usr/local/lighthouse/softwares/nginx/sbin/nginx
或
$ sudo systemctl start nginx #systemd
OR
$ sudo service nginx start   #sysvinit


重载 Nginx 服务
sudo /usr/local/lighthouse/softwares/nginx/sbin/nginx -s reload
$ sudo systemctl reload nginx #systemd
或
$ sudo service nginx reload   #sysvinit


停止
sudo /usr/local/lighthouse/softwares/nginx/sbin/nginx -s stop
$ sudo systemctl stop nginx #systemd
OR
$ sudo service nginx stop   #sysvinit


开机自启动
$ sudo systemctl enable nginx #systemd
或
$ sudo service nginx enable   #sysv init




查询状态
sudo /usr/local/lighthouse/softwares/nginx/sbin/nginx -t
ps -ef | grep nginx

$ sudo systemctl status nginx #systemd
或
$ sudo service nginx status   #sysvinit




nginx: [emerg] bind() to 0.0.0.0:80 failed (98: Address already in use)
killall -9 nginx

```

```
docsify 使用nginx布署在私有服务器
// 安装docsify
npm i docsify-cli -g  
 
docsify --version
docsify serve

注意问题：

将第一行的user www-data;,不管你那里是什么，统一改为user root，否则后面会出现403 forbidden.


server {
	listen 8080;
	server_name localhost;

	location / {
			root /home/lighthouse/shufaguiji/;
			index index.html;
	}

	error_page   500 502 503 504  /50x.html;
	location = /50x.html {
		root   html;
	}
}

```

---


## 4 IP及防火墙

### 4.1 查看ip及端口是否可以访问、开放端口

```basic
	wget http://127.0.0.1:8080
 
	ping + ip： 查看某一个ip地址是否能够连通，如： ping 114.80.67.193
 
	telnet ip port ： 查看某一个机器上的某一个端口是否可以访问，如：telnet 114.80.67.193 8080
 
	netstat -nal  查看网络通信情况
 

查看端口占用
	netstat -ntlp   //查看当前所有tcp端口·
	netstat -ntulp |grep 80   //查看所有80端口使用情况·
	netstat -an | grep 3306   //查看所有3306端口使用情况·
	
	
查看开放的端口	
firewall-cmd --list-all

开放端口
firewall-cmd --zone=public --add-port=4789/udp --permanent
firewall-cmd --zone=public --add-port=80/tcp --permanent  
firewall-cmd --zone=public --add-port=80/udp –permanen
firewall-cmd --zone=public --add-port=8060/tcp --permanent

刷新
firewall-cmd --reload
```

********************************************************************


### 4.2 关闭centos的防火墙

``` vb
一、firewall方式
	firewall-cmd --state    				查看防火墙状态
	systemctl stop firewalld.service     	关闭防火墙，centos7下
	systemctl disable firewalld.service     关闭开机启动

二、service方式
	查看防火墙状态： 
	[root@centos6 ~]# service iptables status
	iptables：未运行防火墙。
	开启防火墙：
	[root@centos6 ~]# service iptables start
	关闭防火墙：
	[root@centos6 ~]# service iptables stop
	
	 /etc/sysconfig/iptables

三、iptables方式
	先进入init.d目录，命令如下：
	[root@centos6 ~]# cd /etc/init.d/
	[root@centos6 init.d]# 

	然后
	查看防火墙状态：
	[root@centos6 init.d]# /etc/init.d/iptables status
	暂时关闭防火墙：
	[root@centos6 init.d]# /etc/init.d/iptables stop
	重启iptables：
	[root@centos6 init.d]# /etc/init.d/iptables restart


```

********************************************************************

## 5  输入输出

### 5.1  linux标准输入输出2>&1

```java
linux标准输入输出2>&1

 /home/share/timerSearch/timerSearchDB.sh >> /home/share/timerSearch/record.txt 2>&1
 
linux中有三种标准输入输出，分别是STDIN，STDOUT，STDERR，对应的数字是0，1，2。
     STDIN是标准输入，默认从键盘读取信息；STDOUT是标准输出，默认将输出结果输出至终端；STDERR是标准错误，默认将输出结果输出至终端。
     由于STDOUT与STDERR都会默认显示在终端上，为了区分二者的信息，就有了编号的0，1，2的定义，用1表示STDOUT，2表示STDERR。
     2>&1，指将标准输出、标准错误指定为同一输出路径

eg1:cat >>filetest 2>&1 <<END      -------建立filetest文件，当输入遇到END时，退出

eg2:
     1、以普通用户执行 find /etc -name passwd 命令，默认会将命令的执行结果（STDOUT）与错误信息（STDERR）都输出至终端显示器。
     2、执行find /etc -name passwd >find.out 2>find.err，会将STDOUT与STDERR分别存放至find.out和find.err中。该命令也可以写成下面三种形式
          find /etc -name passwd 1>find.out 2>find.err
          find /etc -name passwd 2>find.err >find.out
          find /etc -name passwd 2>find.err 1>find.out
     3、若要将所有标准输出及标准错误都输出至文件，可用&表示全部1和2的信息，eg：
          find /etc -name passwd &>find.all 或 find /etc -name passwd >find.all 2>&1
     4、2>&1 ---标准错误重新定向到标准输出
     5、用法：find /etc -name passwd &2>&1 |less
          可分解成
          find /etc -name passwd & 表示前面的命令放到后台执行。
          2>&1 |less 表示将标准错误重定向至标准输出，并用less进行分页显示
		  
https://www.cnblogs.com/jacob-tian/p/6110606.html

```

********************************************************************


### 5.2  定时执行脚本

```sh
#!/bin/bash
#定时查询docker的mysql数据库并保存
#file=/home/share/timerSearch/record.txt
#crontab -uroot -e
#*/1 * * * * /bin/bash /home/share/timerSearch/timerSearchDB.sh >> /home/share/timerSearch/record.txt 2>&1
echo "===============begin=========================" 
echo "定时查询脚本启动了。。。" 
date "+%Y-%m-%d %H:%M:%S" 

docker_name=mobile_uccp_db_1
sql="use YCKPLUS;select id,loginName,name,password,idcard,field29 from T_USER where loginName='admin'\G;"
#docker exec -it ${docker_name} mysql -uroot -psaiwen.web123 -e $sql  >> $file
docker exec -i mobile_uccp_db_1 mysql -uroot -psaiwen.web123 -e "select now();use YCKPLUS;select id,loginName,name,password,idcard,field29 from T_USER where loginName='admin'\G;"  >> /home/share/timerSearch/recordDB.txt

echo "" 
echo "" 




#crontab -uroot -e
0 3 * * 5 /data/saiwen/shell/copyCasSecret.sh

说明：
分钟   小时   日   月   星期   命令
*        *      *    *     *       *

第1列表示分钟1～59 每分钟用*或者 */1表示
第2列表示小时1～23（0表示0点）
第3列表示日期1～31
第4列 表示月份1～12
第5列标识号星期0～6（0表示星期天）
第6列要运行的命令

```


```sh
#!/bin/bash
#sh push-ca.sh
copyPush(){
	cp -r /e/Project/gitlab/calligraphy/*.md $data_dir
	cp -r /e/Project/gitlab/calligraphy/书法字帖 $data_dir
	cd $data_dir
	echo $PWD 'begin...'
	git add .
	git commit -m "fix"
	git push
	echo '.........end.........'
}

#agit 1
data_dir=/e/Project/gitlab/agit/calligraphy
copyPush

#github 9
data_dir=/e/Project/github/calligraphy
copyPush

```

## 6  用户及权限

### 6.1 用户和组的管理

```basic
su         切换root
su user1   切换用户

groups         查看当前用户所在组
groups  user1  查看user1用户所在的组
whoami         查看当前登录用户名

/etc/group  文件包含所有组及用户
/etc/shadow和/etc/passwd系统存在的所有用户名

创建用户      useradd  user1;
设置用户密码  passwd user1;
删除用户      userdel user1;
删除用户（包括主目录文件 /home/user1）  userdel -r user1;

groupadd testgroup  组的添加
groupdel testgroup  组的删除

只有root和组管理员能够改变组的成员：
gpasswd –a user1 users    把 user1加入users组
gpasswd –d user1 users    把 user1退出users组
groupmod –n user users    修改组名user为users

锁定用户  passwd -l user1
解锁用户  passwd -u user1

用户添加到组
usermod -a -G groupA user
-a 代表 append， 也就是 将自己添加到 用户组groupA 中，而不必离开 其他用户组
 
```

********************************************************************

### 6.2 文件权限

```java
查看文件属性 	ls -l test.sh
赋予执行权限    chmod +x test.sh
赋予全部权限    chmod 777 test.sh

改变文件属性（二进制）      chmod #chmod 664 chap1.txt	       110110100  
改变文件属性（字母）        chmod u=rw,g=rw,o=r chap1.txt	   r w x
改变文件所属用户		    chown user1 chap1.txt
改变文件所属用户及所属组    chown user1:root chap1.txt
改变文件所属组              chgrp root chap1.txt


Linux系统是个多用户系统，应该能做到不同的用户能同时访问不同的文件，因此一定要有文件权限控制机制。Linux系统的权限控制机制和Windows的权限控制机制有着很大的差别。Linux的文件或目录都被一个用户拥有时，这个用户称为文件的拥有者(或所有者)，同时文件还被指定的用户组所拥有，这个用户组称为文件所属组。 

用户 u --所属组 g --其他用户 o -- 所有用户 a
 user - group - other - all

要说明的是，一个用户可以是不同组的成员，这可以由管理员控制，我们将在用户管理这一章介绍如何控制的问题。文件的权限由权限标志来决定，权限标志决定了文件的拥有者、文件的所属组、其他用户对文件访问的能力。可以使用“ls –l”命令来显示权限标志。例如：
[test @redflag test]$ls -l
-rw-rw-r-- 1 longkey root 16 20A 24 22:23 chap1.txt
本例中，文件chap1.txt的拥有者是longkey，所属组是root。这里我们特别关心的是输出行前面的第1～10个字符。第1个字符代表文件类别，第2～4个字符“rw-”是文件拥有者的权限，第5～7个字符“rw-”是文件所属组的权限，第8～10个字符“r--”是其他用户(即除了root用户和longkey用户组里的用户之外的用户)文件拥有者的权限。而权限均用三个字符表示，依次为读(r)、写(w)、执行(x)，如果某一位为“-”，则表示没有相应的权限，例如：“rw-”表示有读、写的权限，没有执行的权限。在本例中，文件拥有者longkey用户对文件有读、写的权限，root组的所有用户对文件也有读、写的权限，而其他用户对文件只有读的权限。

设定文件权限时，在模式中常用以下的字母代表用户或用户组：
u——文件的拥有者；
g——文件的所属组；
o——其他用户；
a——代表所有用户(即u+g+o)。
权限用以下字符表示：
r ——读权限；
w——写权限；
x——执行权限；
最后要指明是增加(+)还是减少(-)权限，或是绝对权限(=)。

 [root @redflag /root]chmod o+w chap1.txt
chap1.txt的权限由原来的“rw-rw-r--”变为“rw-rw-rw-”，表示增加其他用户对文件的写权限。
 
 [root @redflag /root]chmod u=rw,g=rw,o=r chap1.txt  chap1.txt的权限变为“rwxrw-r---”，不论原来的权限是什么，这表示拥有者对文件有读、写的权限，所属组的用户对文件也有读、写的权限，而其他用户只有读的权限。

我们在以上设置权限时，用字符表示权限和用户，实际上我们也经常使用八进制来表示。读、写、执行依次各自对应一个二进制位“???”，如果某位为“0”，则表示无权限；如果某位为“1”，则表示有权限。例如：文件权限为r---w---x时，用二进制表示为100010001，用十进制可以表示为421。例如：
         [root @redflag /root]#chmod 664 chap1.txt 
 等同于：
  [root @redflag /root]#chmod u=rw,g=rw,o=r chap1.txt
		

chown 用户名 文件或目录名——改变文件(或目录)的拥有者或所属组
       例如：
     [root @redflag /root]#chown longkey chap1.txt
把文件chap1.txt的拥有者改为longkey用户。
     [root @redflag /root]#chown longkey:root chap1.txt
把文件的拥有者改为longkey用户，同时文件的所属组改为root组。


chgrp组 文件或目录——改变文件或目录的所属组
	 chown可以同时改变文件拥有者和所属者，chgrp只具有改变所属组的功能。例如：
	[root @redflag /root]#chgrp root chap1.txt
		文件chap1.txt的所属组设为root组。
		
```

********************************************************************


## 7 小技巧

### 7.1 java进程高CPU占用故障排查

```java

生产环境下JAVA进程高CPU占用故障排查 
解决过程：
1，根据top命令，发现PID为2633的Java进程占用CPU高达300%，出现故障。

2，找到该进程后，如何定位具体线程或代码呢，首先显示线程列表,并按照CPU占用高的线程排序：
[root@localhost logs]# ps -mp 2633 -o THREAD,tid,time | sort -rn

显示结果如下：
USER     %CPU PRI SCNT WCHAN  USER SYSTEM   TID     TIME
root     10.5  19    - -         -      -  3626 00:12:48
root     10.1  19    - -         -      -  3593 00:12:16

找到了耗时最高的线程3626，占用CPU时间有12分钟了！

将需要的线程ID转换为16进制格式：
[root@localhost logs]# printf "%x\n" 3626
e18

最后打印线程的堆栈信息：
[root@localhost logs]# jstack 2633 |grep e18 -A 30

总结：
1. 先使用top命令查询java占用cpu高的进程
2. 使用 ps -mp 查找出该进程下里面耗用时间长的线程
3. 使用printf "%x\n" 3626 将线程转换为16进制
4. 使用 jstack 2633 |grep e18 -A 30 使用此命令打印出该进程下面的此线程的堆栈信息
 
```

### 7.2 修改linux时间 

```java

http://blog.chinaunix.net/uid-20672257-id-3013282.html
调整linux系统时间和时区与Internet时间同步
一、修改时区：
# cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
修改为中国的东八区
# vi /etc/sysconfig/clock
ZONE="Asia/Shanghai"
UTC=false
ARC=false
二、配置新的时间
日期设定：
# date -s 2008/05/06
时间设定：
# date -s 18:40:00
查看硬件时间（BIOS的）：
    hwclock [-rw] 
    -r:查看现有BIOS时间，默认为－r参数
    -w:将现在的linux系统时间写入BIOS中
    当我们进行完 Linux 时间的校时后，还需要以 hwclock -w 来更新 BIOS 的时间，因为每次开机的时候，系统会重新由 BIOS 将时间读出来，所以， BIOS 才是重要的时间依据。
# hwclock
Tue 06 May 2008 03:49:37 PM CST  -0.039646 seconds
同步BIOS时钟，强制把系统时间写入CMOS：
# clock -w


先使用 date -s 10/17/2008 修改日期
然后 date -s 10:12:13 修改时间
clock -w    写入bios

```

----------------------------------------

### 7.3 修改句柄

```sh
查看句柄
	    ulimit -n  
	    
修改句柄  有三种办法: 
https://blog.csdn.net/skieske/article/details/79261469

①:ulimit -n 65536 
这种是一次性的,当服务器重启或者重新登录服务器就会变成1024

②:vi /etc/security/limits.conf 
修改linux系统参数。添加

*　　soft　　nofile　　65536
*　　hard　　nofile　　65536

这种办法需要重启服务器 

③:vim /etc/profile 
添加/或者修改: 
ulimit -SHn 65536 

然后使修改生效: 
source /etc/profile 

然后再ulimit -a


建议三种方法都要试一下

```

### 7.4 历史记录显示时间 

```basic
history 历史记录显示时间        
在/etc/profile 中增加  export HISTTIMEFORMAT="%Y-%m-%d %H:%M:%S "
写入环境变量		   source /etc/profile
```

### 7.5 虚拟机不能上网

```js
centos7虚拟机不能上网    
	在存储linux目录的CentOS.vmx文件加入  ethernet0.virtualDev = "e1000"  再重启虚拟机
	
linux虚拟机上传下载文件  
	打开虚拟机linux，使用命令 ifconfig 查看ip，再使用xshell连接。
```

### 7.6 连接超时

```bash
微信企业号连接超时： connect timed out
  wget https://qyapi.weixin.qq.com/cgi-bin/gettoken
  ping qyapi.weixin.qq.com
  
  1、可能是防火墙打开了
  2、/etc/hosts  策略配置错了
  
```

### 7.7 中文乱码

```sh
中文乱码问题
[root]# vi /etc/profile
[root]# source /etc/profile
[root]# echo $LANG

# export LC_ALL="zh_CN.GBK"
# export LANG="zh_CN.GBK"

LANG=zh_CN.UTF-8
LC_ALL=en_US.UTF-8


```


### 7.8 星期月份英语 

```java
星期一： Mon.=Monday        Monday
星期二： Tues.=Tuesday      Tuesday 
星期三： Wed.=Wednesday     Wednesday
星期四： Thur.=Thursday     Thurday
星期五： Fri.=Friday        Friday
星期六： Sat.=Saturday      Saturday 
星期天： Sun.=Sunday        Sunday


一月份＝JAN. Jan.=January   January
二月份＝FEB. Feb.=February  February
三月份＝MAR. Mar.=March     March
四月份＝APR. Apr.=April     April
五月份＝MAY May=May         May
六月份＝JUN. Jun.=June      June 
七月份＝JUL. Jul.=July      July
八月份＝AUG. Aug.=August    August
九月份＝SEP. Sept.=September  September
十月份＝OCT. Oct.=October   October
十一月份＝NOV. Nov.=November  November
十二月份＝DEC. Dec.=December  December


东西南北  East West South North  ===  e w s n

```


## 8 我的网站

生活随笔-编程笔记-书法练习轨迹

| 徐书法 | 地址        |  备注          |
| -----  | ----------- |  ------------- |
| 1      | [xushufa.cn]( https://xushufa.cn )            | 书法练习网站。 |
| 2      | [blog.xushufa.cn]( https://blog.xushufa.cn )  | `vuepress`构建的博客网站。 |
| 3      | [web.xushufa.cn]( https://web.xushufa.cn )    | `vuepress-theme-reco`构建的博客网站。|

***

