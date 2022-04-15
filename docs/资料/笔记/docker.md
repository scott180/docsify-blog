# docker
  
## 1、常用命令

```sql
* 查看镜像信息
docker images 

* 查看正在运行的容器
docker ps  

* 查看所有容器
docker ps -a


* 进入容器
docker exec -it 51b9527f1d88 /bin/bash

* 进入数据库
mysql -uroot -pSaiwen.web123
 
* 创建并进入容器
docker run -it --entrypoint /bin/bash registry.saiwentech.com:35000/library/tomcat:7.0_1.7

```

```java
- 导出镜像
docker save -o ucp1.1.7.tar.gz ucp:1.1.7
docker save -o /mnt/home/mobile/ucp1.1.7_docker_image.tar.gz 303d0cc15269

- 导入镜像
docker load -i xxx.tar.gz

- 下载镜像
docker pull 170.18.10.40/ucpplus-b/ucp:1.1.7


查看容器日志
docker logs --tail 50 --follow --timestamps 79921b85086b
docker logs --tail 350 --follow --timestamps mobile_ucp_db_1 


- 查看容器
docker run -ti <your_Container_image>

- 查看镜像详细信息
docker inspect d49f922a0111

- 镜像改名
docker tag imageid name:tag
```

```
停止容器
docker stop mobile_ucp_1 mobile_ucp_db_1 mobile_ucp_mongo_db_1
docker start :启动一个或多少已经被停止的容器
docker stop :停止一个运行中的容器
docker restart :重启容器
http://www.runoob.com/docker/docker-run-command.html

```

```
* 建立镜像  （ucp:1.1.7 .   后面有个点）
cd /mnt/home/mobile/ucp1.1.7
docker build -f docker/Dockerfile  -t ucp:1.1.7 .


* 初始化设置（建立容器）
docker run -it -v /mnt/opt/data/ucp1.1.7/config/ucp/:/opt/saiwentech/autoconfig/conf/ ucp:1.1.7 config -d


* 启动容器
cd /mnt/home/mobile
docker-compose -f docker-compose-ucp1.1.7.yml up -d



* 请注意控制台输出，如果有错误，请按照错误信息修改配置，重新制作镜像
镜像删除操作
docker rmi ucp:1.1.7
如果发现有关联的容器未删除，请先停止容器后删除容器后，再次尝试删除镜像
docker rm 【容器id】

* 删除镜像后，重新制作镜像时，要把原来的mongo、mysql数据删掉
/mnt/opt/data/ucp1.1.7/mysql/data
/mnt/opt/data/ucp1.1.7/mongo/data

```

```
提交镜像
docker commit 678ae48535b1 ucp:1.1.7
docker commit  -m "ucp1.1.7--20180428" -a "xyq" f54f18474f15 ucp:1.1.7
	-a :提交的镜像作者；
	-c :使用Dockerfile指令来创建镜像；
	-m :提交时的说明文字；
	-p :在commit时，将容器暂停。

```

```
上传到170.18.10.40镜像仓库
	登陆 docker login http://170.18.10.40 
		 docker login http://registry.saiwentech.com:35000

	标记 docker tag ucp:1.1.7 170.18.10.40/ucpplus-b/ucp:1.1.7       （170.18.10.120 开发）
		 docker tag ucp:1.1.7 170.18.10.40/mobile/ucp:1.1.7          （170.18.10.162 测试）

	上传 docker push 170.18.10.40/ucpplus-b/ucp:1.1.7
	     docker push 170.18.10.40/mobile/ucp:1.1.7
	
	账号/密码
	徐 yqxu Yqxu123456
	
	
	docker login http://registry.saiwentech.com:35000
	yqxu Yqxu123456
	
	
	现有docker仓库地址使用ip地址，导致外网无法正常下载镜像。
	现在统一修改为registry.saiwentech.com:35000

	上传方式为：
	docker push registry.saiwentech.com:35000/mobile/IMAGE[:TAG]
	下载方式：
	docker pull registry.saiwentech.com:35000/mobile/IMAGE[:TAG]
	docker pull registry.saiwentech.com:35000/library/mysql:5.7.7_saiwen2
```

* * *
```
参数设置
/mnt/opt/data/ucp1.1.7/config/ucp/antx.properties

docker参数配置
/mnt/home/mobile/ucp1.1.7/install/ROOT/META-INF/autoconf/auto-config.xml

源文件位置
/mnt/home/mobile/ucp1.1.7



初始化时可能不能创建数据库，需要手动创建  
创建数据库
CREATE DATABASE UCPPLUS DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


mysql 添加配置文件 /mnt/opt/data/ucp1.1.7/mysql/conf/add.cnf

[mysqld]
sql_mode='NO_ENGINE_SUBSTITUTION' 
```


## 2、docker安装问题



###  2.1、使用 yum 安装

```
http://www.runoob.com/docker/centos-docker-install.html
Docker 要求 CentOS 系统的内核版本高于 3.10 ，查看本页面的前提条件来验证你的CentOS 版本是否支持 Docker 。
通过 uname -r 命令查看你当前的内核版本
[root@runoob ~]# uname -r 3.10.0-327.el7.x86_64

[root@runoob ~]# yum -y install docker-io

[root@runoob ~]# service docker start
```

```
镜像加速

 /etc/docker/daemon.json
{
  "registry-mirrors": ["http://hub-mirror.c.163.com"]
}

镜像加速 阿里云 针对Docker客户端版本大于1.10.0的用户
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://eo6mflje.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker


docker --version
docker info

Dockers 服务开机启动：
sudo systemctl enable docker.service

```

```
安装docker，启动不成功，报错信息  
https://www.2cto.com/net/201803/730799.html

Error starting daemon: SELinux is not supported with the overlay2 graph driver on this kernel. Either boot into a newer kernel or disable selinux in docker (--selinux-enabled=false)

意思是说：此linux的内核中的SELinux不支持 overlay2 graph driver ，解决方法有两个，要么启动一个新内核，要么就在docker里禁用selinux，--selinux-enabled=false

重新编辑docker配置文件：

vi /etc/sysconfig/docker

设置 --selinux-enabled=false

然后systemctl start docker


登陆仓库失败时：
[root@localhost ~]# docker login http://registry.saiwentech.com:35000
Username: yqxu
Password: 
Error response from daemon: Get https://registry.saiwentech.com:35000/v1/users/: http: server gave HTTP response to HTTPS client

需要修改   
	vi /lib/systemd/system/docker.service
在ExecStart后添加     
	ExecStart=/usr/bin/dockerd  --insecure-registry=registry.saiwentech.com:35000 --insecure-registry=registry:35000
再重启
systemctl daemon-reload
sudo systemctl restart docker

```

```

Ubuntu 16.04（LTS）安装dockerI

$ sudo apt-get update

$ sudo apt-get install docker

查看docker服务是否启动：

$ systemctl status docker

若未启动，则启动docker服务：

$ sudo systemctl start docker

sudo apt-get update && sudo apt-get upgrade

```

---

 
###  2.2、安装mongo

```
http://www.runoob.com/docker/docker-install-mongodb.html
docker search mongo    查找Docker Hub上的mongo镜像

docker pull mongo:3.2

docker pull mysql:5.7.7
```

 
### 2.3、安装docker-compose

```
http://www.cnblogs.com/52fhy/p/5991344.html

curl -L https://github.com/docker/compose/releases/download/1.8.0/run.sh > /usr/local/bin/docker-compose

chmod +x /usr/local/bin/docker-compose

docker-compose -version
```

```

卸载docker-compose
https://yeasy.gitbooks.io/docker_practice/content/compose/install.html#%E5%8D%B8%E8%BD%BD
如果是二进制包方式安装的，删除二进制文件即可。
rm /usr/local/bin/docker-compose
```


```

* 报错 Unsupported config option for services service: 'ucp_db'  。docker-compose版本低了。


https://stackoverflow.com/questions/36724948/docker-compose-unsupported-config-option-for-services-service-web

Support for the version 2 compose file format was introduced in docker-compose version 1.6, released around February of this year.

You are using 1.3.3, from July 2015.

You need to upgrade to a more recent version to use the version 2 format configuration files.
```

 
### 2.4、阿里云镜像push

```

* 镜像上传阿里云 cr.console.aliyun.com


  $ sudo docker login --username=明明之明夜 registry.cn-hangzhou.aliyuncs.com
  $ sudo docker tag [ImageId] registry.cn-hangzhou.aliyuncs.com/saiwen/ucp:[镜像版本号]
  $ sudo docker push registry.cn-hangzhou.aliyuncs.com/saiwen/ucp:[镜像版本号]

  sudo docker push registry.cn-hangzhou.aliyuncs.com/saiwen/ucp:1.1.7
  sudo docker push registry.cn-hangzhou.aliyuncs.com/saiwen/ucp:mysql_5.7.7
  sodu docker push registry.cn-hangzhou.aliyuncs.com/saiwen/ucp:mongo_3.2.9
```  


### 2.5、测试 run 运行容器

```
docker run --name base -tid 170.18.10.40/library/baseenv:base /bin/bash

dockcer pull tomcat:6.0.53

docker run --name tomcat6.0.53 -p 8081:8080 -v $PWD/test:/usr/local/tomcat/webapps/test -d docker.io/tomcat:6.0.53

docker run --name some-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag

docker run -p 3306:3306 --name mymysql -v

/mnt/opt/data/ucp1.1.7/mysql/conf:/etc/mysql/conf.d -v

/mnt/opt/data/ucp1.1.7/mysql/logs:/logs -v

/mnt/opt/data/ucp1.1.7/mysql/data:/mysql_data -e
MYSQL_ROOT_PASSWORD=Saiwen.web123 -d registry.cn-hangzhou.aliyuncs.com/saiwen/ucp:mysql_5.7.7
```


## 3、其他

```
删除镜像
root@SZX1000041894:/home# docker tag centos 10.229.43.217:4000/xcb/centos
root@SZX1000041894:/home# docker push 10.229.43.217:4000/xcb/centos
Thepushrefersto a repository [10.229.43.217:4000/xcb/centos]
5f70bf18a086: Pushed 
4012bfb3d628: Pushed
latest: digest: sha256:5b367dbc03f141bb5246b0dff6d5fc9c83d8b8d363d0962f3b7d344340e458f6 size: 1331


curl -I -X DELETE http://10.229.43.217:4000/v2/xcb/centos/manifests/sha256:5b367dbc03f141bb5246b0dff6d5fc9c83d8b8d363d0962f3b7d344340e458f6


[root@localhost ~]# docker push 170.18.10.40/mobile/ucp:1.1.7
The push refers to a repository [170.18.10.40/mobile/ucp]
1f3c218e7747: Pushed 
1aa8eb5ec174: Pushed 
e2d49ccaa5a2: Layer already exists 
9d90c60e9085: Layer already exists 
a4bfb611c9db: Layer already exists 
d73c31c5daa6: Layer already exists 
1.1.7: digest: sha256:4868e041e23b987836a92e86bcc1620845fb0a81bcb0f6bf53f23f2bc58acbae size: 1588


curl -I -X DELETE http://170.18.10.40/v2/mobile/ucp/manifests/sha256:4868e041e23b987836a92e86bcc1620845fb0a81bcb0f6bf53f23f2bc58acbae

curl -I -X DELETE http://170.18.10.40/v2/ucpplus-b/ucp/manifests/sha256:2ddac3e05213f1f42f8e9cbe6780cad526a43ac8b3fc7deff5f8321400288e9e

```

```
  923  cd /mnt/home/mobile/ucp2.0/install/
  924  tar -xzvf coreplus.tar.gz 
  925  docker images
  926  docker pull 170.18.10.40/library/mysql:5.7.7_saiwen2
  927  docker images
  928  docker rmi 02db488f8684
  929  docker images
  930  exit
  931  场地
  932  cd /mnt/home/mobile/ucp2.0/
  933  docker build -f docker/Dockerfile  -t ucp:2.0 .
  934  docker run -it -v /mnt/opt/data/ucp2.0/config/ucp/:/opt/saiwentech/autoconfig/conf/ ucp:2.0 config -d
  935  docker images
  936  cd ..
  937  docker ps
  938  docker-compose -f docker-compose-ucp2.yml up -d
  939  docker ps
  940  docker exec -it 859b4b980ab6 /bin/bash
  941  docker run -it -v /mnt/opt/data/ucp2.0/config/ucp/:/opt/saiwentech/autoconfig/conf/ ucp:2.0 config -d
  942  docker exec -it 859b4b980ab6 /bin/bash
  943  docker commit 859b4b980ab6 ucp:2.0
  944  docker run -it -v /mnt/opt/data/ucp2.0/config/ucp/:/opt/saiwentech/autoconfig/conf/ ucp:2.0 config -d
  945  docker ps
  946  docker exec -it 859b4b980ab6 /bin/bash
  947  docker commit 859b4b980ab6 ucp:2.0
  948  docker run -it -v /mnt/opt/data/ucp2.0/config/ucp/:/opt/saiwentech/autoconfig/conf/ ucp:2.0 config -d
  949  docker exec -it 859b4b980ab6 /bin/bash
  950  docker run -it -v /mnt/opt/data/ucp2.0/config/ucp/:/opt/saiwentech/autoconfig/conf/ ucp:2.0 config -d
  951  docker exec -it 859b4b980ab6 /bin/bash
  952  docker run -it -v /mnt/opt/data/ucp2.0/config/ucp/:/opt/saiwentech/autoconfig/conf/ ucp:2.0 config -d
  953  docker stop mobile_ucp_db_1 mobile_ucp_mongo_db_1 mobile_ucp_1 
  954  docker rmi ucp:2.0
  955  docker rm 8e58c63d6a20
  956  docker rmi ucp:2.0
  957  docker rm fa369ec30a49
  958  docker rmi ucp:2.0
  959  docker rm d1c87aa7a49a
  960  docker rmi ucp:2.0
  961  cd ucp2.0/
  962  docker build -f docker/Dockerfile  -t ucp:2.0 .
  963  docker run -it -v /mnt/opt/data/ucp2.0/config/ucp/:/opt/saiwentech/autoconfig/conf/ ucp:2.0 config -d
  964  docker rmi ucp:2.0
  965  docker rm e9c4c426b598
  966  docker rmi ucp:2.0
  967  docker build -f docker/Dockerfile  -t ucp:2.0 .
  968  docker run -it -v /mnt/opt/data/ucp2.0/config/ucp/:/opt/saiwentech/autoconfig/conf/ ucp:2.0 config -d
  969  cd ..
  970  docker-compose -f docker-compose-ucp2.yml up -d
  971  docker stop mobile_ucp_db_1 mobile_ucp_mongo_db_1 mobile_ucp_1 
  972  docker-compose -f docker-compose-ucp2.yml up -d
  973  docker stop mobile_ucp_db_1 mobile_ucp_mongo_db_1 mobile_ucp_1 
  974  docker-compose -f docker-compose-ucp2.yml up -d
  975  docker ps
  976  docker exec it 15f0e8ed94d3 /bin/bash
  977  docker exec -it 15f0e8ed94d3 /bin/bash
  978  docker exec -it dbdde7107159 /bin/bash
  979  docker exec -it 15f0e8ed94d3 /bin/bash
  980  docker stop mobile_ucp_db_1 mobile_ucp_mongo_db_1 mobile_ucp_1 
  981  docker-compose -f docker-compose-ucp2.yml up -d
  982  docker ps
  983  docker exec -it  15f0e8ed94d3  /bin/bash
  984  docker ps
  985  docker exec -it  3bc7d7b7ee35  /bin/bash
  986  docker ps
  987  docker exec -it 15f0e8ed94d3  /bin/bash
  988  docker stop mobile_ucp_1
  989  docker-compose -f docker-compose-ucp2.yml up -d
  990  docker ps
  991  docker exec -it 3bc7d7b7ee35  /bin/bash
  992  docker stop mobile_ucp_db_1 mobile_ucp_mongo_db_1 mobile_ucp_1 
  993  docker-compose -f docker-compose-ucp2.yml up -d
  994  docker stop mobile_ucp_1
  995  docker-compose -f docker-compose-ucp2.yml up -d
  996  docker ps
  997  docker exec -it 15f0e8ed94d3 /bin/bash
  998  rm -rf /mnt/opt/data/ucp2.0/mysql/data
  999  docker ps
 1000  docker exec -it 15f0e8ed94d3 /bin/bas
``` 
 
 
 
 
``` 
 停止容器
 docker stop mobile_ucp_db_1 mobile_ucp_mongo_db_1 mobile_ucp_1
 
 启动容器
 cd /mnt/home/mobile
 docker-compose -f docker-compose-ucp2.yml up -d
 
 重启之后提交镜像
 docker commit 15f0e8ed94d3 ucp:2.0
 
 导出镜像
 docker save -o ucp2.0.tar ucp:2.0
 
 进入容器
 docker exec -it 15f0e8ed94d3 /bin/bash
 
 进入数据库
 mysql -uroot -pSaiwen.web123

``` 