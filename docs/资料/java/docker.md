## docker
  
*   [1、常用命令](#commonCommand)
*   [2、docker安装问题](#installQuestion)
    *   [2.1、使用 yum 安装（CentOS 7下）](#yumInstallCentos7)
    *   [2.1、安装mongo](#installMongo)
    *   [2.3、安装docker-compose](#installCompose)
    *   [2.4、push镜像阿里云](#pullAliyun)
    *   [2.5、测试 run 运行容器](#testRun)
*   [3、其他](#other)


### 1、常用命令 <h2 id="commonCommand"></h2>

* 建立镜像  （ucp:1.1.7 .   后面有个点）
cd /mnt/home/mobile/ucp1.1.7
docker build -f docker/Dockerfile  -t ucp:1.1.7 .


* 初始化设置（建立容器）
docker run -it -v /mnt/opt/data/ucp1.1.7/config/ucp/:/opt/sudytech/autoconfig/conf/ ucp:1.1.7 config -d


* 启动容器
cd /mnt/home/mobile
docker-compose -f docker-compose-ucp1.1.7.yml up -d

* 查看镜像信息
docker images 

* 查看正在运行的容器
docker ps  

* 查看所有容器
docker ps -a


* 进入容器
docker exec -it 51b9527f1d88 /bin/bash

刷新sudy的nginx
/opt/sudytech/nginx/sbin/nginx -s reload

* 进入数据库
mysql -uroot -pSudy.web123
 
* 创建并进入容器
docker run -it --entrypoint /bin/bash registry.sudytech.com:35000/library/tomcat:7.0_1.7

```
停止容器
docker stop mobile_ucp_1 mobile_ucp_db_1 mobile_ucp_mongo_db_1
docker start :启动一个或多少已经被停止的容器
docker stop :停止一个运行中的容器
docker restart :重启容器
http://www.runoob.com/docker/docker-run-command.html

```

* 请注意控制台输出，如果有错误，请按照错误信息修改配置，重新制作镜像
镜像删除操作
docker rmi ucp:1.1.7
如果发现有关联的容器未删除，请先停止容器后删除容器后，再次尝试删除镜像
docker rm 【容器id】

* 删除镜像后，重新制作镜像时，要把原来的mongo、mysql数据删掉
/mnt/opt/data/ucp1.1.7/mysql/data
/mnt/opt/data/ucp1.1.7/mongo/data

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
		 docker login http://registry.sudytech.com:35000

	标记 docker tag ucp:1.1.7 170.18.10.40/ucpplus-b/ucp:1.1.7       （170.18.10.120 开发）
		 docker tag ucp:1.1.7 170.18.10.40/mobile/ucp:1.1.7          （170.18.10.162 测试）

	上传 docker push 170.18.10.40/ucpplus-b/ucp:1.1.7
	     docker push 170.18.10.40/mobile/ucp:1.1.7
	
	账号/密码
	yqxu Yqxu123456
	
	
	docker login http://registry.sudytech.com:35000
	yqxu Yqxu123456
	
	
	现有docker仓库地址使用ip地址，导致外网无法正常下载镜像。
	现在统一修改为registry.sudytech.com:35000

	上传方式为：
	docker push registry.sudytech.com:35000/mobile/IMAGE[:TAG]
	下载方式：
	docker pull registry.sudytech.com:35000/mobile/IMAGE[:TAG]
	docker pull registry.sudytech.com:35000/library/mysql:5.7.7_sudy2
```

- 导出镜像
docker save -o ucp1.1.7.tar.gz ucp:1.1.7
docker save -o /mnt/home/mobile/ucp1.1.7_docker_image.tar.gz 303d0cc15269

- 导入镜像
docker load -i xxx.tar.gz

- 下载镜像
docker pull 170.18.10.40/ucpplus-b/ucp:1.1.7

```
查看容器日志
docker logs --tail 50 --follow --timestamps 79921b85086b
docker logs --tail 350 --follow --timestamps mobile_ucp_db_1 
```

- 查看容器
docker run -ti <your_Container_image>

- 查看镜像详细信息
docker inspect d49f922a0111

- 镜像改名
docker tag imageid name:tag

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

<h2 id="installQuestion"></h2>	

### 2、docker安装问题

<h3 id="yumInstallCentos7"></h3>

####  2.1、使用 yum 安装（CentOS 7下） 
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
[root@localhost ~]# docker login http://registry.sudytech.com:35000
Username: yqxu
Password: 
Error response from daemon: Get https://registry.sudytech.com:35000/v1/users/: http: server gave HTTP response to HTTPS client

需要修改   
	vi /lib/systemd/system/docker.service
在ExecStart后添加     
	ExecStart=/usr/bin/dockerd  --insecure-registry=registry.sudytech.com:35000 --insecure-registry=registry:35000
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

 <h3 id="installMongo"></h3>
 
####  2.2、安装mongo
```
http://www.runoob.com/docker/docker-install-mongodb.html
docker search mongo    查找Docker Hub上的mongo镜像

docker pull mongo:3.2

docker pull mysql:5.7.7
```

 <h3 id="installCompose"></h3>
 
####  2.3、安装docker-compose
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


* 报错 Unsupported config option for services service: 'ucp_db'  。docker-compose版本低了。
 ```
https://stackoverflow.com/questions/36724948/docker-compose-unsupported-config-option-for-services-service-web

Support for the version 2 compose file format was introduced in docker-compose version 1.6, released around February of this year.

You're using 1.3.3, from July 2015.

You need to upgrade to a more recent version to use the version 2 format configuration files.
```

 <h3 id="pullAliyun"></h3>	
 
#### 2.4、push镜像阿里云
  * 镜像上传阿里云 cr.console.aliyun.com
```
  $ sudo docker login --username=明明之明夜 registry.cn-hangzhou.aliyuncs.com
  $ sudo docker tag [ImageId] registry.cn-hangzhou.aliyuncs.com/sudy/ucp:[镜像版本号]
  $ sudo docker push registry.cn-hangzhou.aliyuncs.com/sudy/ucp:[镜像版本号]

  sudo docker push registry.cn-hangzhou.aliyuncs.com/sudy/ucp:1.1.7
  sudo docker push registry.cn-hangzhou.aliyuncs.com/sudy/ucp:mysql_5.7.7
  sodu docker push registry.cn-hangzhou.aliyuncs.com/sudy/ucp:mongo_3.2.9
```  


<h3 id="testRun"></h3>	

#### 2.5、测试 run 运行容器
```
docker run --name base -tid 170.18.10.40/library/baseenv:base /bin/bash

dockcer pull tomcat:6.0.53

docker run --name tomcat6.0.53 -p 8081:8080 -v $PWD/test:/usr/local/tomcat/webapps/test -d docker.io/tomcat:6.0.53

docker run --name some-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag

docker run -p 3306:3306 --name mymysql -v

/mnt/opt/data/ucp1.1.7/mysql/conf:/etc/mysql/conf.d -v

/mnt/opt/data/ucp1.1.7/mysql/logs:/logs -v

/mnt/opt/data/ucp1.1.7/mysql/data:/mysql_data -e
MYSQL_ROOT_PASSWORD=Sudy.web123 -d registry.cn-hangzhou.aliyuncs.com/sudy/ucp:mysql_5.7.7
```


<h2 id="other"></h2>

### 3、其他
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
  926  docker pull 170.18.10.40/library/mysql:5.7.7_sudy2
  927  docker images
  928  docker rmi 02db488f8684
  929  docker images
  930  exit
  931  场地
  932  cd /mnt/home/mobile/ucp2.0/
  933  docker build -f docker/Dockerfile  -t ucp:2.0 .
  934  docker run -it -v /mnt/opt/data/ucp2.0/config/ucp/:/opt/sudytech/autoconfig/conf/ ucp:2.0 config -d
  935  docker images
  936  cd ..
  937  docker ps
  938  docker-compose -f docker-compose-ucp2.yml up -d
  939  docker ps
  940  docker exec -it 859b4b980ab6 /bin/bash
  941  docker run -it -v /mnt/opt/data/ucp2.0/config/ucp/:/opt/sudytech/autoconfig/conf/ ucp:2.0 config -d
  942  docker exec -it 859b4b980ab6 /bin/bash
  943  docker commit 859b4b980ab6 ucp:2.0
  944  docker run -it -v /mnt/opt/data/ucp2.0/config/ucp/:/opt/sudytech/autoconfig/conf/ ucp:2.0 config -d
  945  docker ps
  946  docker exec -it 859b4b980ab6 /bin/bash
  947  docker commit 859b4b980ab6 ucp:2.0
  948  docker run -it -v /mnt/opt/data/ucp2.0/config/ucp/:/opt/sudytech/autoconfig/conf/ ucp:2.0 config -d
  949  docker exec -it 859b4b980ab6 /bin/bash
  950  docker run -it -v /mnt/opt/data/ucp2.0/config/ucp/:/opt/sudytech/autoconfig/conf/ ucp:2.0 config -d
  951  docker exec -it 859b4b980ab6 /bin/bash
  952  docker run -it -v /mnt/opt/data/ucp2.0/config/ucp/:/opt/sudytech/autoconfig/conf/ ucp:2.0 config -d
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
  963  docker run -it -v /mnt/opt/data/ucp2.0/config/ucp/:/opt/sudytech/autoconfig/conf/ ucp:2.0 config -d
  964  docker rmi ucp:2.0
  965  docker rm e9c4c426b598
  966  docker rmi ucp:2.0
  967  docker build -f docker/Dockerfile  -t ucp:2.0 .
  968  docker run -it -v /mnt/opt/data/ucp2.0/config/ucp/:/opt/sudytech/autoconfig/conf/ ucp:2.0 config -d
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
 mysql -uroot -pSudy.web123



    1  docker ps
    2  df -h
    3  cd /opt/sudytech
    4  ll
    5  rpm -ivh container-selinux-2.9-4.el7.noarch.rpm
    6  rpm -ivh docker-ce-17.06.0.ce-1.el7.centos.x86_64.rpm
    7  docker -v
    8  systemctl start docker
    9  docker ps
   10  mv docker-compose /usr/bin/
   11  cd /usr/bin/
   12  chmod +x /usr/bin/docker-compose
   13  systemctl start docker
   14  vi /lib/systemd/system/docker.service 
   15  systemctl daemon-reload
   16  systemctl restart docker
   17  systemctl start docker.service
   18  firewall-cmd --state
   19  systemctl reset-failed docker.service
   20  systemctl start docker.service
   21  vi /lib/systemd/system/docker.service 
   22  docker ps
   23  Job for docker.service failed because the control process exited with error code. See "systemctl status docker.service" and "journalctl -xe" for details
   24  systemctl start docker
   25  cat /etc/docker/key.json 
   26  vi /lib/systemd/system/docker.service 
   27  systemctl start docker
   28  systemctl reset-failed docker.service
   29  systemctl start docker.service
   30  docker ps
   31  vi /lib/systemd/system/docker.service 
   32  cat /etc/docker/key.json 
   33  vi /etc/hosts
   34  ping 170.18.10.40
   35  cat /etc/docker/key.json
   36  vi /lib/systemd/system/docker.service
   37  systemctl daemon-reload 
   38  systemctl restart docker
   39  docker swarm init
   40  docker node ls
   41  docker swarm leave
   42  docker swarm join-token worker
   43  firewall-cmd --zone=public --add-port=2376/tcp --permanent
   44  firewall-cmd --zone=public --add-port=2377/tcp --permanent
   45  firewall-cmd --zone=public --add-port=7946/tcp --permanent
   46  firewall-cmd --zone=public --add-port=7946/udp --permanent
   47  firewall-cmd --zone=public --add-port=4789/tcp --permanent
   48  firewall-cmd --zone=public --add-port=4789/udp â€“permanent
   49  firewall-cmd --zone=public --add-port=9000/tcp --permanent
   50  firewall-cmd --zone=public --add-port=4789/udp --permanent
   51  firewall-cmd --reload
   52  cat /proc/meminfo 
   53  firewall-cmd --list-all
   54  docker swarm join-token worker
   55  docker node ls
   56  docker run -p 9000:9000 --name portainer -d --restart always    -v /opt/data/endpoints/:/endpoints    registry.sudytech.com:35000/library/portainer:1.16.5 --external-endpoints /endpoints/endpoints.json
   57  docker ps
   58  docker stop 65d1057974e0
   59  docker stop 0a9c93429de4
   60  docker pull registry.sudytech.com:35000/library/registry:2.6.2
   61  docker run -d -v /opt/sudytech/data/registry:/var/lib/registry -p 35000:5000 --restart=always --privileged=true --name registry registry.sudytech.com:35000/library/registry:2.6.2
   62  vi /etc/hosts
   63  docker tag registry:35000/idsplus:1.2_shhg registry.sudytech.com:35000/mobile/idsplus:1.2_shhg
   64  docker tag registry.sudytech.com:35000/mobile/idsplus:1.2_shhg registry:35000:/idsplus1.2_shhg
   65  docker tag registry.sudytech.com:35000/mobile/idsplus:1.2_shhg registry:35000:/idsplus:1.2_shhg
   66  docker tag registry.sudytech.com:35000/mobile/idsplus:1.2_shhg registry:35000/idsplus:1.2_shhg
   67  docker commit registry:35000/idsplus:1.2_shhg
   68  docker images
   69  docker push registry:35000/idsplus:1.2_shhg
   70  vi /lib/systemd/system/docker.service 
   71  systemctl daemon-reload 
   72  systemctl restart docker
   73  docker network ls
   74  docker network create -d overlay sudy_swarm
   75  docker node ls
   76  docker node update --label-add controller=app yzl0a33fxj3em4gm9urgr80n6
   77  docker node update --label-add controller=db 8f4eqa3b5nf9vsctzq951m4oo
   78  systemctl restart docker
   79  \
   80  cd /opt/sudytech/idsplus1.2_shhg/
   81  docker stack deploy -c docker-compose-ids.yml sudy
   82  docker stack rm sudy
   83  docker ps
   84  docker stop 65d1057974e0
   85  docker stop 0a9c93429de4
   86  docker images
   87  docker tag registry.sudytech.com:35000/library/mysql:5.7.7_sudy2 registry:35000/mysql:5.7.7_sudy2
   88  docker push registry:35000/mysql:5.7.7_sudy2
   89  docker images
   90  docker ps
   91  docker ps -a
   92  docker rm 65d1057974e0
   93  docekr rm bcfa08160aea
   94  docker rm bcfa08160aea
   95  docker rm 0a9c93429de4
   96  docker iamges
   97  docker images
   98  docker rm 971dde52f50a
   99  docker rmi 971dde52f50a
  100  docker rmi 93d174a97395
  101  docker iamges
  102  docker stack deploy -c docker-compose-ids.yml sudy
  103  docker stack rm sudy
  104  docker ps
  105  docker stack rm sudy
  106  docker stack deploy -c docker-compose-ids.yml sudy
  107  docker stack rm sudy
  108  docker ps
  109  ls
  110  route
  111  ifconfig
  112  docker ps -a
  113  docker images
  114  systemctl stop docker
  115  route
  116  ifocnfig
  117  systemctl start docker
  118  docker network ls
  119  docker ps
  120  docker ps -a
  121  docker rm portainer registry
  122  docker rm -f portainer registry
  123  docker network ls
  124  docker network rm sudy_swarm 
  125  docker network rm mobile 
  126  docker network rm ingress 
  127  docker network rm docker_gwbridge 
  128  docker swarm leave 
  129  docker node ls
  130  docker node rm 8f4eqa3b5nf9vsctzq951m4oo
  131  docker node --help
  132  docker node rm 8f4eqa3b5nf9vsctzq951m4oo
  133  docker swarm leave 
  134  docker swarm leave --force
  135  ps -a
  136  docker ps -a
  137  docker images
  138  docker ps -a
  139  docker images
  140  docker network ls
  141  docker network rm docker_gwbridge
  142  route 
  143  route add net 172.18.0.0/16 gw 170.18.10.1
  144  route add net 172.18.0.0 netmask 255.255.0.0 gw 170.18.10.1
  145  route add net 172.18.0.0 netmask 255.255.0.0 gw 170.18.10.1 dev eno16780032
  146  route add -net 172.18.0.0 netmask 255.255.0.0 gw 170.18.10.1
  147  route 
  148  docker network ls
  149  vi /etc/sysconfig/static-routes
  150  cat /etc/sysconfig/static-routes
  151  exit
  152  cd '/var/log/'
  153  route
  154  ps -ax
  155  docker ps -a
  156  systemctl status firewalld
  157  systemctl disable firewalld.service 
  158  systemctl stop firewalld.service 
  159  reboot --help
  160  reboot 
  161  docker node ls
  162  vi /lib/systemd/system/docker.service
  163  docker images
  164  df -h
  165  docker images
  166  docker node ls
  167  docker swarm init
  168  docker node ls
  169  docker images
  170  docker pull registry.sudytech.com:35000/library/mysql:5.7.7_sudy2
  171  docker images
  172  df -h
  173  cd /opt/sudytech
  174  ls 
  175  tar -zxvf idsplus1.2_shhg.tar.gz 
  176  docker images
  177  docker tag 93d174a97395  mysql:5.7.7_sudy2
  178  docker images
  179  docker pull registry.sudytech.com:35000/mobile/idsplus_source:1.2_shhg
  180  docker login http://registry.sudytech.com:35000
  181  docker pull registry.sudytech.com:35000/mobile/idsplus_source:1.2_shhg
  182  cd /opt/sudytech/idsplus1.2_shhg/
  183  docker build -f docker/Dockerfile  -t registry.sudytech.com:35000/mobile/idsplus:1.2_shhg .
  184  docker images
  185  docker run -it -v /opt/sudytech/idsplus1.2_shhg/config/:/opt/sudytech/autoconfig/conf/ registry.sudytech.com:35000/mobile/idsplus:1.2_shhg config -d
  186  cd /opt/sudytech/idsplus1.2_shhg/
  187   docker-compose -f docker-compose-idsplus.yml up -d
  188  docker network create mobile
  189   docker-compose -f docker-compose-idsplus.yml up -d
  190  docker ps
  191  docker exec -it idsplus12shhg_ids_1 /bin/bash
  192  docker images
  193  docker ps
  194  ls
  195  docker ps
  196  df -h
  197  docker ps
  198  docker images
  199  cd '/var/log/'
  200  route
  201  cat /etc/sysconfig/static-routes 
  202  reboot
  203  route
  204  ifconfig
  205  route
  206  docker swarm leave
  207  docker swarm leave --forece
  208  docker swarm leave --force
  209  docker network ls
  210  docker network rm docker_gwbridge 
  211  cat /etc/sysconfig/static-routes 
  212  cat /etc/init.d/network
  213  vi /etc/sysconfig/static-routes 
  214  cat /etc/sysconfig/static-routes
  215  docker ps
  216  systemctl enable docker.service 
  217  systemctl start docker
  218  docker ps 
  219  docker images
  220  docker swarm init 
  221  doccd '/var/log/'
  222  docker ps -a
  223  docker stack rm sudy 
  224  docker ps -a
  225  docker images
  226  systemctl stop docker
  227  reboot
  228  docker ps
  229  docke ps -a
  230  docker ps -a
  231  df -h
  232  docker ps
  233  vi /opt/data/endpoints/endpoints.json
  234  history
  235  history > /opt/sudytech/his.txt

