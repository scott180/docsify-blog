
# 本地安装Kafka

## 一、安装方法

前提 ： [本地安装zookeeper]( https://blog.xushufa.cn ) 

1、下载 [kafka_2.13-2.5.1.tgz]( https://download.csdn.net/download/xu180/13060538 ) 文件 （带src是源码 如：kafka-2.5.1-src.tgz ，不能安装）

2、解压文件，进入目录，创建 logs文件夹。
进入config目录，打开 server.properties 文件：
注释 
`#log.dirs=/tmp/kafka-logs`

`#zookeeper.connect=localhost:2181`

添加配置

`log.dirs=E:\ProgramFiles\kafka_2.13-2.5.1\logs`

`zookeeper.connect=127.0.0.1:2181`

3、启动 zookeeper

4、启动kafka  

`WIN+R` 输入cmd 进入命令行窗口

进入kafka目录  `E:\ProgramFiles\kafka_2.13-2.5.1`

输入  `.\bin\windows\kafka-server-start.bat .\config\server.properties`



## 二、执行kafka命令

1、创建主题

命令行进入目录

`C:\Users\Administrator>E:`

`E:\>cd E:\ProgramFiles\kafka_2.13-2.5.1\bin\windows`

创建主题

`E:\ProgramFiles\kafka_2.13-2.5.1\bin\windows>kafka-topics.bat --create --zookeeper 127.0.0.1:2181 --replication-factor 1 --partitions 1 --topic test`

Created topic test.


2、创建生产者消息

`E:\ProgramFiles\kafka_2.13-2.5.1\bin\windows>kafka-console-producer.bat --broker-list 127.0.0.1:9092 --topic test`

3、消费者获取消息

`E:\ProgramFiles\kafka_2.13-2.5.1\bin\windows>kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test --from-beginning`

kafka-console-consumer.bat –zookeeper localhost:2181 –topic test


查看所有主题

`E:\ProgramFiles\kafka_2.13-2.5.1\bin\windows>kafka-topics.bat -list -zookeeper localhost:2181`

	
---

windows下 kafka启动报错：另一个程序正在使用此文件，进程无法访问

kafka在windows平台就是有这个BUG，没办法。只能手动删除\kafka_2.12-2.5.0kafka-logs里的日志文件重启kafka

---


```
软件下载 [kafka_2.13-2.5.1.tgz]( https://download.csdn.net/download/xu180/13060538 )
文档参考 https://blog.csdn.net/lizhitao627619011/article/details/50593278

[本地安装zookeeper]( https://blog.csdn.net/xu180/article/details/105235397 ) 
[kafka_2.13-2.5.1.tgz]( https://download.csdn.net/download/xu180/13060538 )
文档参考 https://blog.csdn.net/lizhitao627619011/article/details/50593278

```
