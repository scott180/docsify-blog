# nginx笔记


## 一、安装

### 1.1、本地安装nginx

```sh
本地安装nginx 参考
https://nginx.org/en/download.html
https://blog.csdn.net/mao0523/article/details/122448599


C:\Users\Administrator>e:

E:\>cd ProgramFiles\nginx-1.16.1

// 启动
E:\ProgramFiles\nginx-1.16.1>start nginx

// 刷新
E:\ProgramFiles\nginx-1.16.1>nginx -s reload

// 停止
E:\ProgramFiles\nginx-1.16.1>nginx -s stop

E:\ProgramFiles\nginx-1.16.1>


```


```
报错 403 Forbidden
https://www.toutiao.com/article/6613280321607565832/

方法1、缺少index.html页面
方法2、将第一行的user www-data改为user root

```

---

### 1.2、常用命令

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

```

```

查询状态
sudo /usr/local/lighthouse/softwares/nginx/sbin/nginx -t
ps -ef | grep nginx

$ sudo systemctl status nginx #systemd
或
$ sudo service nginx status   #sysvinit



端口在使用
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


## 二、配置

### 2.1、基础配置


```sh
    # 监听端口、缓存
	server {
		listen 8081;
		server_name localhost;
		root /home/lighthouse/vuepress-calligraphy;
		
		location ~* \.(css|js|png|jpg|jpeg|gif|gz|svg|mp4|ogg|ogv|webm|htc|xml|woff)$
		{
			add_header Cache-Control no-cache;
			add_header Pragma no-cache; 
		}
	}


	# xushufa.cn   /home/lighthouse/vuepress-calligraphy
	server {
        listen 80;
        server_name xushufa.cn;
		root /home/lighthouse/vuepress-calligraphy;
	    
		#把http的域名请求转成https
		return 301 https://$host$request_uri; 
  		
		location ~* \.(css|js|png|jpg|jpeg|gif|gz|svg|mp4|ogg|ogv|webm|htc|xml|woff)$
        {
			add_header Cache-Control no-cache;
			add_header Pragma no-cache; 
        }
    }
	
	server {
        #SSL 访问端口号为 443
        listen 443 ssl; 
        #填写绑定证书的域名
        server_name xushufa.cn; 
        #证书文件名称
        ssl_certificate /usr/local/lighthouse/softwares/nginx/conf/xushufa.cn_nginx/xushufa.cn_bundle.crt; 
        #私钥文件名称
        ssl_certificate_key /usr/local/lighthouse/softwares/nginx/conf/xushufa.cn_nginx/xushufa.cn.key; 
        ssl_session_timeout 5m;
        #请按照以下协议配置
        ssl_protocols TLSv1.2 TLSv1.3; 
        #请按照以下套件配置，配置加密套件，写法遵循 openssl 标准。
        ssl_ciphers ECDHE-RSA-AES128-GCM-SHA256:HIGH:!aNULL:!MD5:!RC4:!DHE; 
        ssl_prefer_server_ciphers on;
		
        root /home/lighthouse/vuepress-calligraphy;
	      		
		location ~* \.(css|js|png|jpg|jpeg|gif|gz|svg|mp4|ogg|ogv|webm|htc|xml|woff)$
        {
			add_header Cache-Control no-cache;
			add_header Pragma no-cache; 
        }
    }

```

---

> 日志格式化

```
日志格式化参考  https://blog.csdn.net/weixin_48981270/article/details/117720037

log_format json '{"timestamp":"$time_iso8601",'
			'"host": "$server_addr",'
			'"client": "$remote_addr",'
			'"size": $body_bytes_sent,'
			'"responsetime": $request_time,'
			'"domain": "$host",'
			'"url":"$request_uri",'
			'"referer": "$http_referer",'
			'"agent": "$http_user_agent",'
			'"status": "$status",'
			'"x_forwarded_for": "$http_x_forwarded_for"}';


```

---

### 2.2、日志按天保存


```sh
#!/bin/bash
# 日志按天保存
# 测试 /bin/sh /usr/local/lighthouse/softwares/nginx/sbin/cut_nginx_logs.sh

#access.log日志存放路径
log_dir="/usr/local/lighthouse/softwares/nginx/logs/"
log_name="access"

name_current_log=${log_dir}${log_name}.log
name_yesterday_log=${log_dir}${log_name}-$(date -d "yesterday" +"%Y-%m-%d").log
	
#复制新日志
cp $name_current_log $name_yesterday_log

#清空原日志
cat /dev/null > $name_current_log

#nginx的sbin存放路径
nginx_sbin="/usr/local/lighthouse/softwares/nginx/sbin/nginx"   

echo $name_yesterday_log

#restart nginx
$nginx_sbin -s reload

```

```		
/bin/sh^M: bad interpreter:没有那个文件或目录解决
因为操作系统是windows，我在windows下编辑的脚本，所以有可能有不可见字符。
脚本文件是DOS格式的, 即每一行的行尾以\n\r来标识, 其ASCII码分别是0x0D, 0x0A.

vim filename
然后用命令
:set ff? #可以看到dos或unix的字样. 如果的确是dos格式的。
 
然后用
:set ff=unix #把它强制为unix格式的, 然后存盘退出。
再次运行脚本。
	
https://blog.csdn.net/ywb201314/article/details/51909976


```

---

```c
#打开定时任务
crontab -e

#添加定时任务
00 00 * * * /bin/sh  /usr/local/lighthouse/softwares/nginx/sbin/cut_nginx_logs.sh
#":wq"保存并退出

#查看定时任务,就会看到你添加的内容了
crontab -l

```

---

```

crontab说明：

分钟   小时   日   月   星期   命令
*        *      *    *     *       *

第1列表示分钟1～59 每分钟用*或者 */1表示
第2列表示小时1～23（0表示0点）
第3列表示日期1～31
第4列 表示月份1～12
第5列标识号星期0～6（0表示星期天）
第6列要运行的命令

https://blog.csdn.net/sukangshen/article/details/78900147
https://blog.csdn.net/fangyuying128825/article/details/71600969
http://www.cnblogs.com/peida/archive/2013/01/08/2850483.html

/etc/crontab

```
