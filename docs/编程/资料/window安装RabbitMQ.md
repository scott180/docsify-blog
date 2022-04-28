# window安装RabbitMQ

<br />

> [RabbitMQ_3.8.2--ErLang_otp_win64_22.2下载]( https://download.csdn.net/download/xu180/12305633 )  

`--20210515`

## 1、安装 ErLang

> 安装 ErLang_otp_win64_22.2.exe 

- 设置环境变量，新建 `ERLANG_HOME`
- 修改环境变量path，增加`Erlang`变量至`path`，`%ERLANG_HOME%\bin`;
- 打开`cmd`命令框，输入 `erl -version`  验证



## 2、安装 RabbitMQ

> 安装 rabbitmq-server-3.8.2.exe 

- 设置环境变量，新建 `RABBITMQ_SERVER`
- 修改环境变量`path`，增加`rabbitmq`变量至`path`，`%RABBITMQ_SERVER%\sbin`;
- 打开cmd命令框，切换至`...\sbin`目录下，输入 `rabbitmqctl status` 验证
- 创建`RabbitMQ`服务并启动 `rabbitmq-plugins.bat enable rabbitmq_management`
- 登陆 http://127.0.0.1:15672/    账号密码 `guest，guest`

--------------

```sh
D:\>cd D:\ProgramFiles\rabbitmq-server-3.8.2\rabbitmq_server-3.8.2\sbin

D:\ProgramFiles\rabbitmq-server-3.8.2\rabbitmq_server-3.8.2\sbin>rabbitmqctl status

创建RabbitMQ服务并启动
D:\ProgramFiles\rabbitmq-server-3.8.2\rabbitmq_server-3.8.2\sbin>rabbitmq-plugins.bat enable rabbitmq_management
	停止：net stop RabbitMQ
	启动：net start RabbitMQ

	
启动命令	
D:\ProgramFiles\rabbitmq-server-3.8.2\rabbitmq_server-3.8.2\sbin>rabbitmq-server.bat

```

---

```
参考
https://blog.csdn.net/lihua5419/article/details/93006834
https://blog.csdn.net/zhm3023/article/details/82217222

RabbitMQ教程 https://blog.csdn.net/hellozpc/article/details/81436980
erlang官网  https://www.erlang.org/downloads
rabbitmq官网 https://www.rabbitmq.com/download.html
```

