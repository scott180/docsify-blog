
## redis

``` sql
windows下redis安装 
http://www.runoob.com/redis/redis-install.html
进入redis目录
启动  redis-server.exe redis.windows.conf

登录  redis-cli.exe -h 127.0.0.1 -p 6379
	  redis-cli -a sudy12344


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



[root@localhost src]# cd /opt/sudytech/custom/redis-2.8.17/
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