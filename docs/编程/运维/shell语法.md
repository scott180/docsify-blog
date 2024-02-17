# shell语法

<br />

> [blog]( https://xushufa.cn ) &ensp; [MyNotes]( https://gitee.com/xy180/MyNotes ) &ensp; [typora]( https://typora.io/#windows ) &ensp; [作业部落]( https://www.zybuluo.com/mdeditor ) &ensp; [菜鸟教程]( https://www.runoob.com ) 

<br />

*   [1、常用命令](#command)
*   [2、shell编程](#shell)
    *   [2.1、shell概述及优势](#shellIntroduce)
    *   [2.2、shell创建、执行](#shellExecute)
    *   [2.3、shell 变量](#shellParams)
    *   [2.4、shell 运算符](#shellOperator)
    *   [2.5、shell 常用命令](#shellCommonCommand)
    *   [2.6、shell 流程控制](#shellFlow)
    *   [2.7、shell 函数](#shellFunction)
    *   [2.8、shell 输入、输出重定向](#shellIO)
    *   [2.9、shell 文件包含](#shellFileContain)
*   [3、vi命令详解](#viCommand)


<h2 id="command"></h2>

## 1、常用命令

 
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

<h2 id="shell"></h2>

## 2、Shell编程 

<h3 id="shellIntroduce"></h3>

### 2.1、shell概述及优势

```java
  Shell是一个命令语言解释器，它拥有自己内建的Shell命令集，Shell也能被系统中其他应用程序调用。
  当普通用户成功登录后，系统将执行一个称为Shell的程序。正是Shell进程提供了命令行提示符。作为默认值，对普通用户用“$”作提示符，对超级用户(root)用“#”作提示符。
 
  Linux中的Shell有多种类型，其中最常见的是Bourne Shell (sh)、C Shell (csh)和Korn Shell (ksh)。三种Shell各有优缺点。Bourne Shell是Unix最初始的Shell，并且在每种Unix上都可以使用。Bourne Shell在Shell编程方面相当优秀，但在处理与用户的交互方面做得不如其他几种Shell。Bash(Bourne Again Shell)是Bourne Shell的扩展，与Bourne Shell完全向下兼容，并且增加了许多特性。它还包含了很多C Shell和Korn Shell中的优点，有灵活和强大的编程接口，同时又有很友好的用户界面。
  C Shell是一种比Bourne Shell更适于编程的Shell，它的语法与C语言很相似。Linux为喜欢使用C Shell的人员提供了Tcsh。Tcsh是C Shell的一个扩展版本。Tcsh包括命令行编辑、可编程单词补全、拼写矫正、历史命令替换、作业控制和类似C语言的语法，它提供比Bourne Shell更多的提示符参数。
  Korn Shell集合了C Shell和Bourne Shell的优点并且和Bourne Shell完全兼容。Linux系统提供了pdksh(ksh的扩展)，它支持任务控制，可以在命令行上挂起、后台执行、唤醒或终止程序。
```

```

	Bash是大多数Linux系统(包括红旗Linux系统)的默认Shell。Bash有以下的优点：
	(1) 补全命令。当你在Bash命令提示符下输入命令或程序名时，你不必输全命令或程序名，按【Tab】键，Bash将自动补全命令或程序名。
	(2) 通配符。在Bash下可以使用通配符“*”和“？”。“*”可以替代多个字符，而“？”则替代一个字符。
    (3) 历史命令。Bash能自动跟踪用户每次输入的命令，并把输入的命令保存在历史列表缓冲区。缓冲区的大小由HISTSIZE变量控制。当用户每次登录后，home目录下的“.bash_history”文件将初始化历史列表缓冲区。也能通过history和fc命令执行、编辑历史命令。
	(4) 别名。在Bash下，可用alias和unalias命令给命令或可执行程序起别名和清除别名，这样就可以用自己习惯的方式输入命令。

     查看所有别名   alias
     添加别名       alias test="tar -zcvf "
     删除别名       unalias test

    (5)输入/输出重定向。输入重定向用于改变命令的输入，输出重定向用于改变命令的输出。输出重定向更为常用，它经常用于将命令的结果输入到文件中，而不是屏幕上。输入重定向的命令是“<”，输出重定向的命令是“>”

    ① 输入重定向：
    [root@redflag /root]#wc</etc/passwd
    20 23 726
    ② 输出重定向:
    [root@redflag /root]#ls>dir.out                        
    上面命令将ls命令的输出保存为文件dir.out。
    [root@redflag /root]#ls>>dir1.out	 “>>”表示要将一条命令的输出结果追加到文件dir1.out的后面，该文件的原有内容不被破坏，如果使用“>”，则文件原有内容被覆盖。

    (6) 管道。管道用于将一系列的命令连接起来，也就是把前面命令的输出作为后面命令的输入。管道的命令是“|”。

    root@redflag /root]# cat dir.out|grep "test "|wc –l
    管道将cat命令(列出一个文件的内容)的输出送给grep命令，grep命令在输入里查找单词test，grep的输出则是所有包含单词test的行，这个输出又被送给wc命令，wc命令统计出输入中的行数。

    (7) 提示符。
    (8) 作业控制。
    
    最简单的方式是执行echo命令，查询系统环境变量的值：
    [root@redflag /root]# echo $SHELL
    /bin/bash


```

<h3 id="shellExecute"></h3>

### 2.2、shell创建、执行 

```
	Shell 脚本（shell script），是一种为 shell 编写的脚本程序。业界所说的 shell 通常都是指 shell 脚本。
	
一、创建 Shell 脚本
	打开文本编辑器(可以使用 vi/vim 命令来创建文件)，新建一个文件 test.sh，扩展名为 sh（sh代表shell），扩展名并不影响脚本执行，见名知意就好，如果你用 php 写 shell 脚本，扩展名就用 php 好了。
	输入一些代码，一般是这样：

    	#!/bin/bash
    	echo "Hello World !"

    #! 是一个约定的标记，它告诉系统这个脚本需要什么解释器来执行，即使用哪一种 Shell。
    ① 如果Shell脚本的第一个非空白字符不是“#”，则它会使用Bourne Shell。
    ② 如果Shell脚本的第一个非空白字符是“#”，但不是以“#！”开头时，则它会使用C Shell。
    ③ 如果Shell脚本以“#！”开头，则“#！”后面所跟的字符串就是所使用Shell的绝对路径名。Bourne Shell的路径名称为/bin/sh，而C Shell则为/bin/csh。
    echo 命令用于向窗口输出文本。



二、运行 Shell 脚本有两种方法：
​	1、作为可执行程序
​	将上面的代码保存为 test.sh，并 cd 到相应目录：

​	chmod +x ./test.sh  #使脚本具有执行权限
​	./test.sh  #执行脚本

​	注意，一定要写成 ./test.sh，而不是 test.sh，运行其它二进制的程序也一样，直接写 test.sh，linux 系统会去 PATH 里寻找有没有叫 test.sh 的，而只有 /bin, /sbin, /usr/bin，/usr/sbin 等在 PATH 里，你的当前目录通常不在 PATH 里，所以写成 test.sh 是会找不到命令的，要用 ./test.sh 告诉系统说，就在当前目录找。
​	
​	2、作为解释器参数
​	这种运行方式是，直接运行解释器，其参数就是 shell 脚本的文件名，如：

​	/bin/sh test.sh
​	/bin/php test.php

​	这种方式运行的脚本，不需要在第一行指定解释器信息，写了也没用。


```

<h3 id="shellParams"></h3>

### 2.3、shell 变量 

```
shell 变量、引号、数组、传递参数
http://www.runoob.com/linux/linux-shell-variable.html 转自菜鸟教程

test="测试引号"
echo "双引号 this is a ${test}"
echo '单引号 this is a ${test}'
echo "反引号 当前目录pwd `pwd` "

`变量：`
注意，变量名和等号之间不能有空格，这可能和你熟悉的所有编程语言都不一样。同时，变量名的命名须遵循如下规则：
首个字符必须为字母（a-z，A-Z）。
中间不能有空格，可以使用下划线（_）。
不能使用标点符号。
不能使用bash里的关键字（可用help命令查看保留关键字）。

#使用一个定义过的变量，只要在变量名前面加美元符号即可，如：
your_name="qinjx"
echo $your_name
echo ${your_name}
变量名外面的花括号是可选的，加不加都行，加花括号是为了帮助解释器识别变量的边界

`单引号:`
#单引号里的任何字符都会原样输出，单引号字符串中的变量是无效的；
单引号字串中不能出现单引号（对单引号使用转义符后也不行）

`双引号:`
	your_name='qinjx'
	str="Hello, I know your are \"$your_name\"! \n"
双引号的优点：
#双引号里可以有变量
双引号里可以出现转义字符

`数组:`
#!/bin/sh
arr=(12 '单引号' “双引号” 22)
echo "第二个值：" ${arr[1]}
echo “所有元素：” ${arr[@]}
echo "数组长度： ${#arr[@]}"

数组赋值 my_array[0]=A 

`注释`
以"#"开头的行就是注释，会被解释器忽略。

'传递参数：'
参数处理	说明
$#	传递到脚本的参数个数
$*	以一个单字符串显示所有向脚本传递的参数。
如"$*"用「"」括起来的情况、以"$1 $2 … $n"的形式输出所有参数。
$$	脚本运行的当前进程ID号
$!	后台运行的最后一个进程的ID号
$@	与$*相同，但是使用时加引号，并在引号中返回每个参数。
如"$@"用「"」括起来的情况、以"$1" "$2" … "$n" 的形式输出所有参数。
$-	显示Shell使用的当前选项，与set命令功能相同。
$?	显示最后命令的退出状态。0表示没有错误，其他任何值表明有错误。
```
|参数处理        | 说明   |
| ------   | -----  |
| `$#`  | `传递到脚本的参数个数` |
| `$*`  | `以一个单字符串显示所有向脚本传递的参数` |
| `$@`  | `与$*相同，但是使用时加引号，并在引号中返回每个参数` |
```
$* 与 $@ 区别：
相同点：都是引用所有参数。
不同点：只有在双引号中体现出来。假设在脚本运行时写了三个参数 1、2、3，，则 " * " 等价于 "1 2 3"（传递了一个参数），而 "@" 等价于 "1" "2" "3"（传递了三个参数）。
#!/bin/bash
# author:菜鸟教程
# url:www.runoob.com

echo "传递参数个数 \$# $#"
echo "-- \$* 演示 ---"
for i in "$*"; do
    echo $i
done

echo "-- \$@ 演示 ---"
for i in "$@"; do
    echo $i
done

执行脚本，输出结果如下所示：
$ chmod +x test.sh 
$ ./test.sh 1 2 3
#或/bin/sh test.sh 1 2 3
-- $* 演示 ---
1 2 3
-- $@ 演示 ---
1
2
3

```

<h3 id="shellOperator"></h3>

### 2.4、shell 运算符 

```

原生bash不支持简单的数学运算，但是可以通过其他命令来实现，例如 awk 和 expr，expr 最常用。
expr 是一款表达式计算工具，使用它能完成表达式的求值操作。
例如，两个数相加(注意使用的是反引号 ` 而不是单引号 ')：

#!/bin/bash
val=`expr 2 + 2`
echo "两数之和为 : $val"

两点注意：
表达式和运算符之间要有空格，例如 2+2 是不对的，必须写成 2 + 2，这与我们熟悉的大多数编程语言不一样。
完整的表达式要被 ` ` 包含，注意这个字符不是常用的单引号，在 Esc 键下边。


EQ 就是 EQUAL等于
NQ 就是 NOT EQUAL不等于 
GT 就是 GREATER THAN大于　 
LT 就是 LESS THAN小于 
GE 就是 GREATER THAN OR EQUAL 大于等于 
LE 就是 LESS THAN OR EQUAL 小于等于
```

#### 2.4.1、算术运算符

```
`算术运算符`
下表列出了常用的算术运算符，假定变量 a 为 10，变量 b 为 20：
运算符	说明	举例
+	加法	`expr $a + $b` 结果为 30。
-	减法	`expr $a - $b` 结果为 -10。
*	乘法	`expr $a \* $b` 结果为  200。
/	除法	`expr $b / $a` 结果为 2。
%	取余	`expr $b % $a` 结果为 0。
=	赋值	a=$b 将把变量 b 的值赋给 a。
==	相等。用于比较两个数字，相同则返回 true。	[ $a == $b ] 返回 false。
!=	不相等。用于比较两个数字，不相同则返回 true。	[ $a != $b ] 返回 true。

#注意：条件表达式要放在方括号之间，并且要有空格，例如: [$a==$b] 是错误的，必须写成 [ $a == $b ]。

算术运算符实例如下：
#!/bin/bash
# author:菜鸟教程
# url:www.runoob.com

a=10
b=20

val=`expr $a + $b`
echo "a + b : $val"

val=`expr $a - $b`
echo "a - b : $val"

val=`expr $a \* $b`
echo "a * b : $val"

val=`expr $b / $a`
echo "b / a : $val"

val=`expr $b % $a`
echo "b % a : $val"

if [ $a == $b ]
then
   echo "a 等于 b"
fi
if [ $a != $b ]
then
   echo "a 不等于 b"
fi
执行脚本，输出结果如下所示：
a + b : 30
a - b : -10
a * b : 200
b / a : 2
b % a : 0
a 不等于 b

注意：
乘号(*)前边必须加反斜杠(\)才能实现乘法运算；
if...then...fi 是条件语句，后续将会讲解。
在 MAC 中 shell 的 expr 语法是：$((表达式))，此处表达式中的 "*" 不需要转义符号 "\" 。
```

#### 2.4.2、关系运算符

```
`关系运算符`
关系运算符只支持数字，不支持字符串，除非字符串的值是数字。
下表列出了常用的关系运算符，假定变量 a 为 10，变量 b 为 20：

运算符	说明	举例
-eq	检测两个数是否相等，相等返回 true。	[ $a -eq $b ] 返回 false。
-ne	检测两个数是否相等，不相等返回 true。	[ $a -ne $b ] 返回 true。
-gt	检测左边的数是否大于右边的，如果是，则返回 true。	[ $a -gt $b ] 返回 false。
-lt	检测左边的数是否小于右边的，如果是，则返回 true。	[ $a -lt $b ] 返回 true。
-ge	检测左边的数是否大于等于右边的，如果是，则返回 true。	[ $a -ge $b ] 返回 false。
-le	检测左边的数是否小于等于右边的，如果是，则返回 true。	[ $a -le $b ] 返回 true。

关系运算符实例如下：
#!/bin/bash
# author:菜鸟教程
# url:www.runoob.com

a=10
b=20

if [ $a -eq $b ]
then
   echo "$a -eq $b : a 等于 b"
else
   echo "$a -eq $b: a 不等于 b"
fi
if [ $a -ne $b ]
then
   echo "$a -ne $b: a 不等于 b"
else
   echo "$a -ne $b : a 等于 b"
fi
if [ $a -gt $b ]
then
   echo "$a -gt $b: a 大于 b"
else
   echo "$a -gt $b: a 不大于 b"
fi
if [ $a -lt $b ]
then
   echo "$a -lt $b: a 小于 b"
else
   echo "$a -lt $b: a 不小于 b"
fi
if [ $a -ge $b ]
then
   echo "$a -ge $b: a 大于或等于 b"
else
   echo "$a -ge $b: a 小于 b"
fi
if [ $a -le $b ]
then
   echo "$a -le $b: a 小于或等于 b"
else
   echo "$a -le $b: a 大于 b"
fi
执行脚本，输出结果如下所示：
10 -eq 20: a 不等于 b
10 -ne 20: a 不等于 b
10 -gt 20: a 不大于 b
10 -lt 20: a 小于 b
10 -ge 20: a 小于 b
10 -le 20: a 小于或等于 b
```

#### 2.4.3、布尔运算符

```
`布尔运算符`
下表列出了常用的布尔运算符，假定变量 a 为 10，变量 b 为 20：
运算符	说明	举例
!	非运算，表达式为 true 则返回 false，否则返回 true。	[ ! false ] 返回 true。
-o	或运算，有一个表达式为 true 则返回 true。	[ $a -lt 20 -o $b -gt 100 ] 返回 true。
-a	与运算，两个表达式都为 true 才返回 true。	[ $a -lt 20 -a $b -gt 100 ] 返回 false。
实例
布尔运算符实例如下：
#!/bin/bash
# author:菜鸟教程
# url:www.runoob.com

a=10
b=20

if [ $a != $b ]
then
   echo "$a != $b : a 不等于 b"
else
   echo "$a != $b: a 等于 b"
fi
if [ $a -lt 100 -a $b -gt 15 ]
then
   echo "$a 小于 100 且 $b 大于 15 : 返回 true"
else
   echo "$a 小于 100 且 $b 大于 15 : 返回 false"
fi
if [ $a -lt 100 -o $b -gt 100 ]
then
   echo "$a 小于 100 或 $b 大于 100 : 返回 true"
else
   echo "$a 小于 100 或 $b 大于 100 : 返回 false"
fi
if [ $a -lt 5 -o $b -gt 100 ]
then
   echo "$a 小于 5 或 $b 大于 100 : 返回 true"
else
   echo "$a 小于 5 或 $b 大于 100 : 返回 false"
fi
执行脚本，输出结果如下所示：
10 != 20 : a 不等于 b
10 小于 100 且 20 大于 15 : 返回 true
10 小于 100 或 20 大于 100 : 返回 true
10 小于 5 或 20 大于 100 : 返回 false
逻辑运算符
以下介绍 Shell 的逻辑运算符，假定变量 a 为 10，变量 b 为 20:
运算符	说明	举例
&&	逻辑的 AND	[[ $a -lt 100 && $b -gt 100 ]] 返回 false
||	逻辑的 OR	[[ $a -lt 100 || $b -gt 100 ]] 返回 true
实例
逻辑运算符实例如下：
#!/bin/bash
# author:菜鸟教程
# url:www.runoob.com

a=10
b=20

if [[ $a -lt 100 && $b -gt 100 ]]
then
   echo "返回 true"
else
   echo "返回 false"
fi

if [[ $a -lt 100 || $b -gt 100 ]]
then
   echo "返回 true"
else
   echo "返回 false"
fi
执行脚本，输出结果如下所示：
返回 false
返回 true
```

#### 2.4.4、字符串运算符

```
`字符串运算符`
下表列出了常用的字符串运算符，假定变量 a 为 "abc"，变量 b 为 "efg"：
运算符	说明	举例
=	检测两个字符串是否相等，相等返回 true。	[ $a = $b ] 返回 false。
!=	检测两个字符串是否相等，不相等返回 true。	[ $a != $b ] 返回 true。
-z	检测字符串长度是否为0，为0返回 true。	[ -z $a ] 返回 false。
-n	检测字符串长度是否为0，不为0返回 true。	[ -n $a ] 返回 true。
str	检测字符串是否为空，不为空返回 true。	[ $a ] 返回 true。
实例
字符串运算符实例如下：
#!/bin/bash
# author:菜鸟教程
# url:www.runoob.com

a="abc"
b="efg"

if [ $a = $b ]
then
   echo "$a = $b : a 等于 b"
else
   echo "$a = $b: a 不等于 b"
fi
if [ $a != $b ]
then
   echo "$a != $b : a 不等于 b"
else
   echo "$a != $b: a 等于 b"
fi
if [ -z $a ]
then
   echo "-z $a : 字符串长度为 0"
else
   echo "-z $a : 字符串长度不为 0"
fi
if [ -n $a ]
then
   echo "-n $a : 字符串长度不为 0"
else
   echo "-n $a : 字符串长度为 0"
fi
if [ $a ]
then
   echo "$a : 字符串不为空"
else
   echo "$a : 字符串为空"
fi
执行脚本，输出结果如下所示：
abc = efg: a 不等于 b
abc != efg : a 不等于 b
-z abc : 字符串长度不为 0
-n abc : 字符串长度不为 0
abc : 字符串不为空
```

#### 2.4.5、文件测试运算符

```
`文件测试运算符`
文件测试运算符用于检测 Unix 文件的各种属性。
属性检测描述如下：

操作符	说明	举例
-b file	检测文件是否是块设备文件，如果是，则返回 true。	[ -b $file ] 返回 false。
-c file	检测文件是否是字符设备文件，如果是，则返回 true。	[ -c $file ] 返回 false。
-d file	检测文件是否是目录，如果是，则返回 true。	[ -d $file ] 返回 false。
-f file	检测文件是否是普通文件（既不是目录，也不是设备文件），如果是，则返回 true。	[ -f $file ] 返回 true。
-g file	检测文件是否设置了 SGID 位，如果是，则返回 true。	[ -g $file ] 返回 false。
-k file	检测文件是否设置了粘着位(Sticky Bit)，如果是，则返回 true。	[ -k $file ] 返回 false。
-p file	检测文件是否是有名管道，如果是，则返回 true。	[ -p $file ] 返回 false。
-u file	检测文件是否设置了 SUID 位，如果是，则返回 true。	[ -u $file ] 返回 false。
-r file	检测文件是否可读，如果是，则返回 true。	[ -r $file ] 返回 true。
-w file	检测文件是否可写，如果是，则返回 true。	[ -w $file ] 返回 true。
-x file	检测文件是否可执行，如果是，则返回 true。	[ -x $file ] 返回 true。
-s file	检测文件是否为空（文件大小是否大于0），不为空返回 true。	[ -s $file ] 返回 true。
-e file	检测文件（包括目录）是否存在，如果是，则返回 true。	[ -e $file ] 返回 true。

实例
变量 file 表示文件"/var/www/runoob/test.sh"，它的大小为100字节，具有 rwx 权限。下面的代码，将检测该文件的各种属性：
#!/bin/bash
# author:菜鸟教程
# url:www.runoob.com

file="/var/www/runoob/test.sh"
if [ -r $file ]
then
   echo "文件可读"
else
   echo "文件不可读"
fi
if [ -w $file ]
then
   echo "文件可写"
else
   echo "文件不可写"
fi
if [ -x $file ]
then
   echo "文件可执行"
else
   echo "文件不可执行"
fi
if [ -f $file ]
then
   echo "文件为普通文件"
else
   echo "文件为特殊文件"
fi
if [ -d $file ]
then
   echo "文件是个目录"
else
   echo "文件不是个目录"
fi
if [ -s $file ]
then
   echo "文件不为空"
else
   echo "文件为空"
fi
if [ -e $file ]
then
   echo "文件存在"
else
   echo "文件不存在"
fi
执行脚本，输出结果如下所示：
文件可读
文件可写
文件可执行
文件为普通文件
文件不是个目录
文件不为空
文件存在


==============================================================
```

### 2.5、shell 常用命令 <h3 id="shellCommonCommand"></h3>

#### 2.5.1、shell echo命令

```java
==============================================================
Shell echo命令
Shell 的 echo 指令与 PHP 的 echo 指令类似，都是用于字符串的输出。命令格式：
echo string
您可以使用echo实现更复杂的输出格式控制。

1.显示普通字符串:
echo "It is a test"
这里的双引号完全可以省略，以下命令与上面实例效果一致：
echo It is a test

2.显示转义字符
echo "\"It is a test\""
结果将是:
"It is a test"
同样，双引号也可以省略

3.显示变量
read 命令从标准输入中读取一行,并把输入行的每个字段的值指定给 shell 变量
#!/bin/sh
read name 
echo "$name It is a test"
以上代码保存为 test.sh，name 接收标准输入的变量，结果将是:
[root@www ~]# sh test.sh
OK                     #标准输入
OK It is a test        #输出

4.显示换行
echo -e "OK! \n" # -e 开启转义
echo "It it a test"
输出结果：
OK!

It it a test

5.显示不换行
#!/bin/sh
echo -e "OK! \c" # -e 开启转义 \c 不换行
echo "It is a test"
输出结果：
OK! It is a test

6.显示结果定向至文件
echo "It is a test" > myfile

7.原样输出字符串，不进行转义或取变量(用单引号)
echo '$name\"'
输出结果：
$name\"

8.显示命令执行结果
echo `date`
注意： 这里使用的是反引号 `, 而不是单引号 '。
结果将显示当前日期

==============================================================
```

#### 2.5.2、shell printf命令

```java
==============================================================
printf 命令模仿 C 程序库（library）里的 printf() 程序。
标准所定义，因此使用printf的脚本比使用echo移植性好。
printf 使用引用文本或空格分隔的参数，外面可以在printf中使用格式化字符串，还可以制定字符串的宽度、左右对齐方式等。默认printf不会像 echo 自动添加换行符，我们可以手动添加 \n。
printf 命令的语法：
printf  format-string  [arguments...]
参数说明：
format-string: 为格式控制字符串
arguments: 为参数列表。
实例如下：
$ echo "Hello, Shell"
Hello, Shell
$ printf "Hello, Shell\n"
Hello, Shell
$
接下来,我来用一个脚本来体现printf的强大功能：
#!/bin/bash
# author:菜鸟教程
# url:www.runoob.com
 
printf "%-10s %-8s %-4s\n" 姓名 性别 体重kg  
printf "%-10s %-8s %-4.2f\n" 郭靖 男 66.1234 
printf "%-10s %-8s %-4.2f\n" 杨过 男 48.6543 
printf "%-10s %-8s %-4.2f\n" 郭芙 女 47.9876 
执行脚本，输出结果如下所示：
姓名     性别   体重kg
郭靖     男      66.12
杨过     男      48.65
郭芙     女      47.99
%s %c %d %f都是格式替代符
%-10s 指一个宽度为10个字符（-表示左对齐，没有则表示右对齐），任何字符都会被显示在10个字符宽的字符内，如果不足则自动以空格填充，超过也会将内容全部显示出来。
%-4.2f 指格式化为小数，其中.2指保留2位小数。
更多实例：
#!/bin/bash
# author:菜鸟教程
# url:www.runoob.com
 
# format-string为双引号
printf "%d %s\n" 1 "abc"

# 单引号与双引号效果一样 
printf '%d %s\n' 1 "abc" 

# 没有引号也可以输出
printf %s abcdef

# 格式只指定了一个参数，但多出的参数仍然会按照该格式输出，format-string 被重用
printf %s abc def

printf "%s\n" abc def

printf "%s %s %s\n" a b c d e f g h i j

# 如果没有 arguments，那么 %s 用NULL代替，%d 用 0 代替
printf "%s and %d \n" 
执行脚本，输出结果如下所示：
1 abc
1 abc
abcdefabcdefabc
def
a b c
d e f
g h i
j  
 and 0
printf的转义序列
序列	说明
\a	警告字符，通常为ASCII的BEL字符
\b	后退
\c	抑制（不显示）输出结果中任何结尾的换行字符（只在%b格式指示符控制下的参数字符串中有效），而且，任何留在参数里的字符、任何接下来的参数以及任何留在格式字符串中的字符，都被忽略
\f	换页（formfeed）
\n	换行
\r	回车（Carriage return）
\t	水平制表符
\v	垂直制表符
\\	一个字面上的反斜杠字符
\ddd	表示1到3位数八进制值的字符。仅在格式字符串中有效
\0ddd	表示1到3位的八进制值字符
实例
$ printf "a string, no processing:<%s>\n" "A\nB"
a string, no processing:<A\nB>

$ printf "a string, no processing:<%b>\n" "A\nB"
a string, no processing:<A
B>

$ printf "www.runoob.com \a"
www.runoob.com $                  #不换行

笔记列表

%d %s %c %f 格式替代符详解:
d: Decimal 十进制整数 -- 对应位置参数必须是十进制整数，否则报错！
s: String 字符串 -- 对应位置参数必须是字符串或者字符型，否则报错！
c: Char 字符 -- 对应位置参数必须是字符串或者字符型，否则报错！
f: Float 浮点 -- 对应位置参数必须是数字型，否则报错！
如：其中最后一个参数是 "def"，%c 自动截取字符串的第一个字符作为结果输出。
$  printf "%d %s %c\n" 1 "abc" "def"
1 abc d
==============================================================
```

#### 2.5.3、shell test命令

```java
==============================================================
Shell中的 test 命令用于检查某个条件是否成立，它可以进行数值、字符和文件三个方面的测试。
数值测试
参数	说明
-eq	等于则为真
-ne	不等于则为真
-gt	大于则为真
-ge	大于等于则为真
-lt	小于则为真
-le	小于等于则为真
实例演示：
num1=100
num2=100
if test $[num1] -eq $[num2]
then
    echo '两个数相等！'
else
    echo '两个数不相等！'
fi
输出结果：
两个数相等！
代码中的 [] 执行基本的算数运算，如：
#!/bin/bash

a=5
b=6

result=$[a+b] # 注意等号两边不能有空格
echo "result 为： $result"
结果为:
result 为： 11

`字符串测试`
参数	说明
=	等于则为真
!=	不相等则为真
-z 字符串	字符串的长度为零则为真
-n 字符串	字符串的长度不为零则为真
实例演示：
num1="ru1noob"
num2="runoob"
if test $num1 = $num2
then
    echo '两个字符串相等!'
else
    echo '两个字符串不相等!'
fi
输出结果：
两个字符串不相等!

`文件测试`
参数	说明
-e 文件名	如果文件存在则为真
-r 文件名	如果文件存在且可读则为真
-w 文件名	如果文件存在且可写则为真
-x 文件名	如果文件存在且可执行则为真
-s 文件名	如果文件存在且至少有一个字符则为真
-d 文件名	如果文件存在且为目录则为真
-f 文件名	如果文件存在且为普通文件则为真
-c 文件名	如果文件存在且为字符型特殊文件则为真
-b 文件名	如果文件存在且为块特殊文件则为真
实例演示：
cd /bin
if test -e ./bash
then
    echo '文件已存在!'
else
    echo '文件不存在!'
fi
输出结果：
文件已存在!
另外，Shell还提供了与( -a )、或( -o )、非( ! )三个逻辑操作符用于将测试条件连接起来，其优先级为："!"最高，"-a"次之，"-o"最低。例如：
cd /bin
if test -e ./notFile -o -e ./bash
then
    echo '有一个文件存在!'
else
    echo '两个文件都不存在'
fi
输出结果：
有一个文件存在!
==============================================================
```

<h3 id="shellFlow"></h3>

### 2.6、shell 流程控制 

#### 2.6.1、if

```java
==============================================================
if elif else fi

if else
if
if 语句语法格式：
if condition
then
    command1 
    command2
    ...
    commandN 
fi
写成一行（适用于终端命令提示符）：
if [ $(ps -ef | grep -c "ssh") -gt 1 ]; then echo "true"; fi
末尾的fi就是if倒过来拼写，后面还会遇到类似的。
if else
if else 语法格式：
if condition
then
    command1 
    command2
    ...
    commandN
else
    command
fi
if else-if else
if else-if else 语法格式：
if condition1
then
    command1
elif condition2 
then 
    command2
else
    commandN
fi
以下实例判断两个变量是否相等：
a=10
b=20
if [ $a == $b ]
then
   echo "a 等于 b"
elif [ $a -gt $b ]
then
   echo "a 大于 b"
elif [ $a -lt $b ]
then
   echo "a 小于 b"
else
   echo "没有符合的条件"
fi
输出结果：
a 小于 b
if else语句经常与test命令结合使用，如下所示：
num1=$[2*3]
num2=$[1+5]
if test $[num1] -eq $[num2]
then
    echo '两个数字相等!'
else
    echo '两个数字不相等!'
fi
输出结果：
两个数字相等!
```

#### 2.6.2、for

```
for 循环
与其他编程语言类似，Shell支持for循环。
for循环一般格式为：
for var in item1 item2 ... itemN
do
    command1
    command2
    ...
    commandN
done
写成一行：
for var in item1 item2 ... itemN; do command1; command2… done;
当变量值在列表里，for循环即执行一次所有命令，使用变量名获取列表中的当前取值。命令可为任何有效的shell命令和语句。in列表可以包含替换、字符串和文件名。
in列表是可选的，如果不用它，for循环使用命令行的位置参数。
例如，顺序输出当前列表中的数字：
for loop in 1 2 3 4 5
do
    echo "The value is: $loop"
done
输出结果：
The value is: 1
The value is: 2
The value is: 3
The value is: 4
The value is: 5
顺序输出字符串中的字符：
for str in 'This is a string'
do
    echo $str
done
输出结果：
This is a string
```

#### 2.6.3、while

```
while 语句
while循环用于不断执行一系列命令，也用于从输入文件中读取数据；命令通常为测试条件。其格式为：
while condition
do
    command
done
以下是一个基本的while循环，测试条件是：如果int小于等于5，那么条件返回真。int从0开始，每次循环处理时，int加1。运行上述脚本，返回数字1到5，然后终止。
#!/bin/sh
int=1
while(( $int<=5 ))
do
    echo $int
    let "int++"
done
运行脚本，输出：
1
2
3
4
5
使用中使用了 Bash let 命令，它用于执行一个或多个表达式，变量计算中不需要加上 $ 来表示变量，具体可查阅：Bash let 命令
。
while循环可用于读取键盘信息。下面的例子中，输入信息被设置为变量FILM，按<Ctrl-D>结束循环。

echo '按下 <CTRL-D> 退出'
echo -n '输入你最喜欢的网站名: '
while read FILM
do
    echo "是的！$FILM 是一个好网站"
done
运行脚本，输出类似下面：
按下 <CTRL-D> 退出
输入你最喜欢的网站名:菜鸟教程
是的！菜鸟教程 是一个好网站
无限循环
无限循环语法格式：
while :
do
    command
done
或者
while true
do
    command
done
或者
for (( ; ; ))
```

#### 2.6.4、until

```
until 循环
until循环执行一系列命令直至条件为真时停止。
until循环与while循环在处理方式上刚好相反。
一般while循环优于until循环，但在某些时候—也只是极少数情况下，until循环更加有用。
until 语法格式:
until condition
do
    command
done
条件可为任意测试条件，测试发生在循环末尾，因此循环至少执行一次—请注意这一点。
```

#### 2.6.5、case

```
`case  esac`
Shell case语句为多选择语句。可以用case语句匹配一个值与一个模式，如果匹配成功，执行相匹配的命令。case语句格式如下：
case 值 in
模式1)
    command1
    command2
    ...
    commandN
    ;;
模式2）
    command1
    command2
    ...
    commandN
    ;;
esac
case工作方式如上所示。取值后面必须为单词in，每一模式必须以右括号结束。取值可以为变量或常数。匹配发现取值符合某一模式后，其间所有命令开始执行直至 ;;。
取值将检测匹配的每一个模式。一旦模式匹配，则执行完匹配模式相应命令后不再继续其他模式。如果无一匹配模式，使用星号 * 捕获该值，再执行后面的命令。
下面的脚本提示输入1到4，与每一种模式进行匹配：
echo '输入 1 到 4 之间的数字:'
echo '你输入的数字为:'
read aNum
case $aNum in
    1)  echo '你选择了 1'
    ;;
    2)  echo '你选择了 2'
    ;;
    3)  echo '你选择了 3'
    ;;
    4)  echo '你选择了 4'
    ;;
    *)  echo '你没有输入 1 到 4 之间的数字'
    ;;
esac
输入不同的内容，会有不同的结果，例如：
输入 1 到 4 之间的数字:
你输入的数字为:
3
你选择了 3
```

#### 2.6.6、break和continue

```

跳出循环
在循环过程中，有时候需要在未达到循环结束条件时强制跳出循环，Shell使用两个命令来实现该功能：break和continue。
break命令
break命令允许跳出所有循环（终止执行后面的所有循环）。
下面的例子中，脚本进入死循环直至用户输入数字大于5。要跳出这个循环，返回到shell提示符下，需要使用break命令。
#!/bin/bash
while :
do
    echo -n "输入 1 到 5 之间的数字:"
    read aNum
    case $aNum in
        1|2|3|4|5) echo "你输入的数字为 $aNum!"
        ;;
        *) echo "你输入的数字不是 1 到 5 之间的! 游戏结束"
            break
        ;;
    esac
done
执行以上代码，输出结果为：
输入 1 到 5 之间的数字:3
你输入的数字为 3!
输入 1 到 5 之间的数字:7
你输入的数字不是 1 到 5 之间的! 游戏结束

continue
continue命令与break命令类似，只有一点差别，它不会跳出所有循环，仅仅跳出当前循环。
对上面的例子进行修改：
#!/bin/bash
while :
do
    echo -n "输入 1 到 5 之间的数字: "
    read aNum
    case $aNum in
        1|2|3|4|5) echo "你输入的数字为 $aNum!"
        ;;
        *) echo "你输入的数字不是 1 到 5 之间的!"
            continue
            echo "游戏结束"
        ;;
    esac
done
运行代码发现，当输入大于5的数字时，该例中的循环不会结束，语句 echo "Game is over!" 永远不会被执行。
esac
case的语法和C family语言差别很大，它需要一个esac（就是case反过来）作为结束标记，每个case分支用右圆括号，用两个分号表示break。
==============================================================
```

<h3 id="shellFunction"></h3>

### 2.7、shell 函数 

```java
==============================================================
linux shell 可以用户定义函数，然后在shell脚本中可以随便调用。
shell中函数的定义格式如下：
[ function ] funname [()]

{

    action;

    [return int;]

}
说明：
1、可以带function fun() 定义，也可以直接fun() 定义,不带任何参数。
2、参数返回，可以显示加：return 返回，如果不加，将以最后一条命令运行结果，作为返回值。 return后跟数值n(0-255

下面的例子定义了一个函数并进行调用：
#!/bin/bash
# author:菜鸟教程
# url:www.runoob.com

demoFun(){
    echo "这是我的第一个 shell 函数!"
}
echo "-----函数开始执行-----"
demoFun
echo "-----函数执行完毕-----"
输出结果：
-----函数开始执行-----
这是我的第一个 shell 函数!
-----函数执行完毕-----


下面定义一个带有return语句的函数：
#!/bin/bash
# author:菜鸟教程
# url:www.runoob.com

funWithReturn(){
    echo "这个函数会对输入的两个数字进行相加运算..."
    echo "输入第一个数字: "
    read aNum
    echo "输入第二个数字: "
    read anotherNum
    echo "两个数字分别为 $aNum 和 $anotherNum !"
    return $(($aNum+$anotherNum))
}
funWithReturn
echo "输入的两个数字之和为 $? !"
输出类似下面：
这个函数会对输入的两个数字进行相加运算...
输入第一个数字: 
1
输入第二个数字: 
2
两个数字分别为 1 和 2 !
输入的两个数字之和为 3 !
函数返回值在调用该函数后通过 $? 来获得。
注意：所有函数在使用前必须定义。这意味着必须将函数放在脚本开始部分，直至shell解释器首次发现它时，才可以使用。调用函数仅使用其函数名即可。
函数参数
在Shell中，调用函数时可以向其传递参数。在函数体内部，通过 $n 的形式来获取参数的值，例如，$1表示第一个参数，$2表示第二个参数...


带参数的函数示例：
#!/bin/bash
# author:菜鸟教程
# url:www.runoob.com

funWithParam(){
    echo "第一个参数为 $1 !"
    echo "第二个参数为 $2 !"
    echo "第十个参数为 $10 !"
    echo "第十个参数为 ${10} !"
    echo "第十一个参数为 ${11} !"
    echo "参数总数有 $# 个!"
    echo "作为一个字符串输出所有参数 $* !"
}
funWithParam 1 2 3 4 5 6 7 8 9 34 73
输出结果：
第一个参数为 1 !
第二个参数为 2 !
第十个参数为 10 !
第十个参数为 34 !
第十一个参数为 73 !
参数总数有 11 个!
作为一个字符串输出所有参数 1 2 3 4 5 6 7 8 9 34 73 !
注意，$10 不能获取第十个参数，获取第十个参数需要${10}。当n>=10时，需要使用${n}来获取参数。
另外，还有几个特殊字符用来处理参数：
参数处理	说明
$#	传递到脚本的参数个数
$*	以一个单字符串显示所有向脚本传递的参数
$$	脚本运行的当前进程ID号
$!	后台运行的最后一个进程的ID号
$@	与$*相同，但是使用时加引号，并在引号中返回每个参数。
$-	显示Shell使用的当前选项，与set命令功能相同。
$?	显示最后命令的退出状态。0表示没有错误，其他任何值表明有错误。
==============================================================
```

<h3 id="shellIO"></h3>

### 2.8、shell 输入、输出重定向 

```java
==============================================================
http://www.runoob.com/linux/linux-shell-io-redirections.html
Shell 输入/输出重定向
大多数 UNIX 系统命令从你的终端接受输入并将所产生的输出发送回​​到您的终端。一个命令通常从一个叫标准输入的地方读取输入，默认情况下，这恰好是你的终端。同样，一个命令通常将其输出写入到标准输出，默认情况下，这也是你的终端。
`
重定向命令列表如下：
命令	说明
command > file	将输出重定向到 file。
command < file	将输入重定向到 file。
command >> file	将输出以追加的方式重定向到 file。
n > file	将文件描述符为 n 的文件重定向到 file。
n >> file	将文件描述符为 n 的文件以追加的方式重定向到 file。
n >& m	将输出文件 m 和 n 合并。
n <& m	将输入文件 m 和 n 合并。
<< tag	将开始标记 tag 和结束标记 tag 之间的内容作为输入。
需要注意的是文件描述符 0 通常是标准输入（STDIN），1 是标准输出（STDOUT），2 是标准错误输出（STDERR）。
`

`输出重定向`
重定向一般通过在命令间插入特定的符号来实现。特别的，这些符号的语法如下所示:
#command1 > file1 这个命令执行command1然后将输出的内容存入file1。
注意任何file1内的已经存在的内容将被新内容替代。如果要将新内容添加在文件末尾，请使用>>操作符。

执行下面的 who 命令，它将命令的完整的输出重定向在用户文件中(users):
$ who > users
执行后，并没有在终端输出信息，这是因为输出已被从默认的标准输出设备（终端）重定向到指定的文件。
你可以使用 cat 命令查看文件内容：
$ cat users
_mbsetupuser console  Oct 31 17:35 
tianqixin    console  Oct 31 17:35 
tianqixin    ttys000  Dec  1 11:33 

输出重定向会覆盖文件内容，请看下面的例子：
$ echo "菜鸟教程：www.runoob.com" > users
$ cat users
菜鸟教程：www.runoob.com

如果不希望文件内容被覆盖，可以使用 >> 追加到文件末尾，例如：
$ echo "菜鸟教程：www.runoob.com" >> users
$ cat users
菜鸟教程：www.runoob.com
菜鸟教程：www.runoob.com


`输入重定向`
和输出重定向一样，Unix 命令也可以从文件获取输入，语法为：
# command1 < file1 这样，本来需要从键盘获取输入的命令会转移到文件读取内容。
注意：输出重定向是大于号(>)，输入重定向是小于号(<)。

接着以上实例，我们需要统计 users 文件的行数,执行以下命令：
$ wc -l users
       2 users
也可以将输入重定向到 users 文件：
$  wc -l < users
       2 
注意：上面两个例子的结果不同：第一个例子，会输出文件名；第二个不会，因为它仅仅知道从标准输入读取内容。

command1 < infile > outfile
同时替换输入和输出，执行command1，从文件infile读取内容，然后将输出写入到outfile中。


`重定向深入讲解`
一般情况下，每个 Unix/Linux 命令运行时都会打开三个文件：
标准输入文件(stdin)：stdin的文件描述符为0，Unix程序默认从stdin读取数据。
标准输出文件(stdout)：stdout 的文件描述符为1，Unix程序默认向stdout输出数据。
标准错误文件(stderr)：stderr的文件描述符为2，Unix程序会向stderr流中写入错误信息。
默认情况下，command > file 将 stdout 重定向到 file，command < file 将stdin 重定向到 file。
如果希望 stderr 重定向到 file，可以这样写：
$ command 2 > file
如果希望 stderr 追加到 file 文件末尾，可以这样写：
$ command 2 >> file
2 表示标准错误文件(stderr)。
如果希望将 stdout 和 stderr 合并后重定向到 file，可以这样写：
$ command > file 2>&1

或者

$ command >> file 2>&1
如果希望对 stdin 和 stdout 都重定向，可以这样写：
$ command < file1 >file2
command 命令将 stdin 重定向到 file1，将 stdout 重定向到 file2。

Here Document
Here Document 是 Shell 中的一种特殊的重定向方式，用来将输入重定向到一个交互式 Shell 脚本或程序。
它的基本的形式如下：
command << delimiter
    document
delimiter
它的作用是将两个 delimiter 之间的内容(document) 作为输入传递给 command。
注意：
结尾的delimiter 一定要顶格写，前面不能有任何字符，后面也不能有任何字符，包括空格和 tab 缩进。
开始的delimiter前后的空格会被忽略掉。
实例
在命令行中通过 wc -l 命令计算 Here Document 的行数：
$ wc -l << EOF
    欢迎来到
    菜鸟教程
    www.runoob.com
EOF
3          # 输出结果为 3 行
$
我们也可以将 Here Document 用在脚本中，例如：
#!/bin/bash
# author:菜鸟教程
# url:www.runoob.com

cat << EOF
欢迎来到
菜鸟教程
www.runoob.com
EOF
执行以上脚本，输出结果：
欢迎来到
菜鸟教程
www.runoob.com


`/dev/null 文件`
如果希望执行某个命令，但又不希望在屏幕上显示输出结果，那么可以将输出重定向到 /dev/null：
# command > /dev/null
/dev/null 是一个特殊的文件，写入到它的内容都会被丢弃；如果尝试从该文件读取内容，那么什么也读不到。但是 /dev/null 文件非常有用，将命令的输出重定向到它，会起到"禁止输出"的效果。
如果希望屏蔽 stdout 和 stderr，可以这样写：
$ command > /dev/null 2>&1
注意：0 是标准输入（STDIN），1 是标准输出（STDOUT），2 是标准错误输出（STDERR）。

`笔记列表`

$ command > file 2>&1
$ command >> file 2>&1
这里的&没有固定的意思
放在>后面的&，表示重定向的目标不是一个文件，而是一个文件描述符，内置的文件描述符如下
1 => stdout
2 => stderr
0 => stdin
换言之 2>1 代表将stderr重定向到当前路径下文件名为1的regular file中，而2>&1代表将stderr重定向到文件描述符为1的文件(即/dev/stdout)中，这个文件就是stdout在file system中的映射
而&>file是一种特殊的用法，也可以写成>&file，二者的意思完全相同，都等价于
>file 2>&1
此处&>或者>&视作整体，分开没有单独的含义
顺序问题：
find /etc -name .bashrc > list 2>&1
# 我想问为什么不能调下顺序,比如这样
find /etc -name .bashrc 2>&1 > list
这个是从左到右有顺序的
第一种
xxx > list 2>&1
先将要输出到stdout的内容重定向到文件，此时文件list就是这个程序的stdout，再将stderr重定向到stdout，也就是文件list
第二种
xxx 2>&1 > list
先将要输出到stderr的内容重定向到stdout，此时会产生一个stdout的拷贝，作为程序的stderr，而程序原本要输出到stdout的内容，依然是对接在stdout原身上的，因此第二步重定向stdout，对stdout的拷贝不产生任何影响
==============================================================
```

<h3 id="shellFileContain"></h3> 

### 2.9、shell 文件包含 

```java
==============================================================
和其他语言一样，Shell 也可以包含外部脚本。这样可以很方便的封装一些公用的代码作为一个独立的文件。
Shell 文件包含的语法格式如下：
. filename   # 注意点号(.)和文件名中间有一空格

或

source filename
实例
创建两个 shell 脚本文件。
test1.sh 代码如下：
#!/bin/bash
# author:菜鸟教程
# url:www.runoob.com

url="http://www.runoob.com"
test2.sh 代码如下：
#!/bin/bash
# author:菜鸟教程
# url:www.runoob.com

#使用 . 号来引用test1.sh 文件
. ./test1.sh

# 或者使用以下包含文件代码
# source ./test1.sh

echo "菜鸟教程官网地址：$url"
接下来，我们为 test2.sh 添加可执行权限并执行：
$ chmod +x test2.sh 
$ ./test2.sh 
菜鸟教程官网地址：http://www.runoob.com
注：被包含的文件 test1.sh 不需要可执行权限。
==============================================================
```

<h2 id="viCommand"></h2>

## 3、vi命令详解 

```java
==============================================================
 linux vi命令详解

刚开始学着用linux，对vi命令不是很熟，在网上转接了一篇。

vi编辑器是所有Unix及Linux系统下标准的编辑器，它的强大不逊色于任何最新的文本编辑器，这里只是简单地介绍一下它的用法和一小部分指 令。由于 对Unix及Linux系统的任何版本，vi编辑器是完全相同的，因此您可以在其他任何介绍vi的地方进一步了解它。Vi也是Linux中最基本的文本编 辑器，学会它后，您将在Linux的世界里畅行无阻。
1、vi的基本概念
　　基本上vi可以分为三种状态，分别是命令模式（command mode）、插入模式（Insert mode）和底行模式（last line mode），各模式的功能区分如下：
    1) 命令行模式command mode）
　　控制屏幕光标的移动，字符、字或行的删除，移动复制某区段及进入Insert mode下，或者到 last line mode。
    2) 插入模式（Insert mode）
　　只有在Insert mode下，才可以做文字输入，按「ESC」键可回到命令行模式。
    3) 底行模式（last line mode）
　　将文件保存或退出vi，也可以设置编辑环境，如寻找字符串、列出行号……等。
 
    不过一般我们在使用时把vi简化成两个模式，就是将底行模式（last line mode）也算入命令行模式command mode）。
2、vi的基本操作 
a) 进入vi
   　在系统提示符号输入vi及文件名称后，就进入vi全屏幕编辑画面：
　　　$ vi myfile
不过有一点要特别注意，就是您进入vi之后，是处于「命令行模式（command mode）」，您要切换到「插入模式（Insert mode）」才能够输入文字。初次使用vi的人都会想先用上下左右键移动光标，结果电脑一直哔哔叫，把自己气个半死，所以进入vi后，先不要乱动，转换到 「插入模式（Insert mode）」再说吧！
 
b) 切换至插入模式（Insert mode）编辑文件
　　在「命令行模式（command mode）」下按一下字母「i」就可以进入「插入模式（Insert mode）」，这时候你就可以开始输入文字了。
 
c) Insert 的切换
　　您目前处于「插入模式（Insert mode）」，您就只能一直输入文字，如果您发现输错了字！想用光标键往回移动，将该字删除，就要先按一下「ESC」键转到「命令行模式（command mode）」再删除文字。
 
d) 退出vi及保存文件
　　在「命令行模式（command mode）」下，按一下「：」冒号键进入「Last line mode」，例如：
: w filename （输入 「w filename」将文章以指定的文件名filename保存）
: wq (输入「wq」，存盘并退出vi)
: q! (输入q!， 不存盘强制退出vi)

3、命令行模式（command mode）功能键
1）. 插入模式
       按「i」切换进入插入模式「insert mode」，按"i"进入插入模式后是从光标当前位置开始输入文件；
　　按「a」进入插入模式后，是从目前光标所在位置的下一个位置开始输入文字；
　　按「o」进入插入模式后，是插入新的一行，从行首开始输入文字。
 
2）. 从插入模式切换为命令行模式
      按「ESC」键。
 
3）. 移动光标
　　vi可以直接用键盘上的光标来上下左右移动，但正规的vi是用小写英文字母「h」、「j」、「k」、「l」，分别控制光标左、下、上、右移一格。
　　按「ctrl」+「b」：屏幕往"后"移动一页。
　　按「ctrl」+「f」：屏幕往"前"移动一页。
　　按「ctrl」+「u」：屏幕往"后"移动半页。
　　按「ctrl」+「d」：屏幕往"前"移动半页。
　　按数字「0」：移到文章的开头。
　　按「G」：移动到文章的最后。
　　按「$」：移动到光标所在行的"行尾"。
　　按「^」：移动到光标所在行的"行首"
　　按「w」：光标跳到下个字的开头
　　按「e」：光标跳到下个字的字尾
　　按「b」：光标回到上个字的开头
　　按「#l」：光标移到该行的第#个位置，如：5l,56l。
 
4）. 删除文字
　　「x」：每按一次，删除光标所在位置的"后面"一个字符。
　　「#x」：例如，「6x」表示删除光标所在位置的"后面"6个字符。
　　「X」：大写的X，每按一次，删除光标所在位置的"前面"一个字符。
　　「#X」：例如，「20X」表示删除光标所在位置的"前面"20个字符。
　　「dd」：删除光标所在行。
　　「#dd」：从光标所在行开始删除#行
 
5）. 复制
　　「yw」：将光标所在之处到字尾的字符复制到缓冲区中。
　　「#yw」：复制#个字到缓冲区
　　「yy」：复制光标所在行到缓冲区。
　　「#yy」：例如，「6yy」表示拷贝从光标所在的该行"往下数"6行文字。
　　「p」：将缓冲区内的字符贴到光标所在位置。注意：所有与"y"有关的复制命令都必须与"p"配合才能完成复制与粘贴功能。
 
6）. 替换
　　「r」：替换光标所在处的字符。
　　「R」：替换光标所到之处的字符，直到按下「ESC」键为止。
 
7）. 回复上一次操作
　　「u」：如果您误执行一个命令，可以马上按下「u」，回到上一个操作。按多次"u"可以执行多次回复。
 
8）. 更改
　　「cw」：更改光标所在处的字到字尾处
　　「c#w」：例如，「c3w」表示更改3个字
 
9）. 跳至指定的行
　　「ctrl」+「g」列出光标所在行的行号。
　　「#G」：例如，「15G」，表示移动光标至文章的第15行行首。

4、Last line mode下命令简介
在使用「last line mode」之前，请记住先按「ESC」键确定您已经处于「command mode」下后，再按「：」冒号即可进入「last line mode」。

A) 列出行号

　「set nu」：输入「set nu」后，会在文件中的每一行前面列出行号。

B) 跳到文件中的某一行

　「#」：「#」号表示一个数字，在冒号后输入一个数字，再按回车键就会跳到该行了，如输入数字15，再回车，就会跳到文章的第15行。

C) 查找字符

　「/关键字」：先按「/」键，再输入您想寻找的字符，如果第一次找的关键字不是您想要的，可以一直按「n」会往后寻找到您要的关键字为止。

　「?关键字」：先按「?」键，再输入您想寻找的字符，如果第一次找的关键字不是您想要的，可以一直按「n」会往前寻找到您要的关键字为止。

D) 保存文件

　「w」：在冒号输入字母「w」就可以将文件保存起来。

E) 离开vi

　「q」：按「q」就是退出，如果无法离开vi，可以在「q」后跟一个「!」强制离开vi。

　「qw」：一般建议离开时，搭配「w」一起使用，这样在退出的时候还可以保存文件。

5、vi命令列表
1、下表列出命令模式下的一些键的功能：

h
左移光标一个字符

l
右移光标一个字符

k
光标上移一行

j
光标下移一行

^
光标移动至行首

0
数字"0"，光标移至文章的开头

G
光标移至文章的最后

$
光标移动至行尾

Ctrl+f
向前翻屏

Ctrl+b
向后翻屏

Ctrl+d
向前翻半屏

Ctrl+u
向后翻半屏

i
在光标位置前插入字符

a
在光标所在位置的后一个字符开始增加

o
插入新的一行，从行首开始输入

ESC
从输入状态退至命令状态

x
删除光标后面的字符

#x
删除光标后的＃个字符

X
(大写X)，删除光标前面的字符

#X
删除光标前面的#个字符

dd
删除光标所在的行

#dd
删除从光标所在行数的#行

yw
复制光标所在位置的一个字

#yw
复制光标所在位置的#个字

yy
复制光标所在位置的一行

#yy
复制从光标所在行数的#行

p
粘贴

u
取消操作

cw
更改光标所在位置的一个字

#cw
更改光标所在位置的#个字


2、下表列出行命令模式下的一些指令
w filename
储存正在编辑的文件为filename

wq filename
储存正在编辑的文件为filename，并退出vi

q!
放弃所有修改，退出vi

set nu
显示行号

/或?
查找，在/后输入要查找的内容

n
与/或?一起使用，如果查找的内容不是想要找的关键字，按n或向后（与/联用）或向前（与?联用）继续查找，直到找到为止。


对于第一次用vi，有几点注意要提醒一下：
1、 用vi打开文件后，是处于「命令行模式（command mode）」，您要切换到「插入模式（Insert mode）」才能够输入文字。切换方法：在「命令行模式（command mode）」下按一下字母「i」就可以进入「插入模式（Insert mode）」，这时候你就可以开始输入文字了。
2、编辑好后，需从插入模式切换为命令行模式才能对文件进行保存，切换方法：按「ESC」键。
3、保存并退出文件：在命令模式下输入:wq即可！（别忘了wq前面的:） 

```
