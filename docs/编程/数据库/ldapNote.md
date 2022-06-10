# ldapNote

<br />

> ldap笔记 --20190403

<br />

## 1、介绍

```java
LADP基本介绍
LDAP（轻量级目录访问协议）以目录的形式来管理资源（域用户，用户组，地址簿，邮件用户，打印机等等）。
https://www.cnblogs.com/itzxy/p/9566548.html

特点：
1. LDAP是一种网略协议而不是数据库，而且LDAP的目录不是关系型的，没有RDBMS那么复杂，LDAP不支持数据库的Transaction机制，纯粹的无状态，请求-响应的工作模式。
2. LDAP不能存储BOLB,LDAP的读写操作是非对称的，读的方便，写的比较麻烦，LDAP支持复杂的查询过滤器。
3. LDAP使用树状结构，接近于公司组织结构，文件目录结构，域名结构等。
4. LDAP使用简单，接口标准，并支持SSL访问。

用途：
信息安全类：数字证书管理，授权管理，单点登陆
科学计算类：DCE(分布式计算环境)，UDDI(统一描述，发现和集成协议)
网络资源管理类：MAIL系统，DNS系统，网络用户管理，电话号码簿。
电子政府资源管理类：内网组织信息服务，电子政务目录体系，人口基础库，法人基础库。

结构：
一颗目录信息树有若干条目（Entry）组成，每一个条目有唯一的标识名DN(Distingushed Name),条目可以用来描述用户账号，打印机和计算机等对象。一个条目是一个对象，每个条目有多个属性（Attribute）组成，每一属性有一个类型和一个到多个值组成，每个属性可以对应一个或多个值（Value）。

```

## 2、安装
 
> 安装ldap和berkeley-db

```sh
可以使用 db-5.3.21.tar.gz 和 openldap-2.4.33.tgz

安装ldap参考   https://blog.csdn.net/qiang359503893/article/details/44858491

1、安装Berkeley DB
wget http://download.oracle.com/berkeley-db/db-4.6.21.tar.gz
解压缩
tar zxvf berkeley-db-4.6.21.tar.gz
配置，编译，安装.
1、
cd db-4.6.21/build_unix/
2、
../dist/configure --prefix=/usr/local/berkeleyDB
3、
make
4、
make install

注意：安装完成后把执行
 #  cp /usr/local/berkeleyDB/include/* /usr/include/
 #  cp /usr/local/berkeleyDB/lib/* /usr/lib/
```

```java
2、配置berkeleyDB 环境变量
vi ~/.bash_profile
添加：
CPPFLAGS="-I/usr/local/berkeleyDB/include"
export CPPFLAGS
LDFLAGS="-L/usr/local/lib -L/usr/local/berkeleyDB/lib -R/usr/local/berkeleyDB/lib"
export LDFLAGS
LD_LIBRARY_PATH="/usr/local/berkeleyDB/lib"
export LD_LIBRARY_PATH

3、安装openLDAP
 wget ftp://ftp.openldap.org/pub/OpenLDAP/openldap-release/openldap-2.4.40.tgz

# tar -xzvf  openldap-2.4.15.tgz
# cd openldap-2.4.15/
# ./configure --prefix=/usr/local/openldap
# make depend
# make && make install
```

## 3、配置

> 设置lapd配置文件 及导入初始化数据

```c
安装完成后配置slapd.conf
注：Slapd.conf 位于/usr/local/openldap/etc/openldap
1)插入下面六条记录
/usr/local/openldap/etc/openldap/schema/cosine.schema 
/usr/local/openldap/etc/openldap/schema/inetorgperson.schema 
/usr/local/openldap/etc/openldap/schema/nis.schema
/usr/local/openldap/etc/openldap/schema/org.schema
/usr/local/openldap/etc/openldap/schema/user.schema
/usr/local/openldap/etc/openldap/schema/group.schema

以及

# Indices to maintain
index objectClass                       eq,pres 
index ou,cn,mail,surname,givenname      eq,pres,sub 
index uidNumber,gidNumber,loginShell    eq,pres 
index uid,memberUid                     eq,pres,sub 
index nisMapName,nisMapEntry            eq,pres,sub

2)将schema文件夹下的文件拖入路径为：
/usr/local/openldap/etc/openldap/schema下的user，group，org三个schema
```

```sql
3)设置密码 root / pwd12344

vi /usr/local/openldap/etc/openldap/slapd.conf

database         bdb
suffix          "dc=stech,dc=com"
rootdn          "cn=root,dc=stech,dc=com"
# Cleartext passwords, especially for the rootdn, should
# be avoid.  See slappasswd(8) and slapd.conf(5) for details.
# Use of strong authentication encouraged.
rootpw          {MD5}5jnSmZCVnBZyaTmptpc73w==
```

```
产生加密密码散列的方法是使用slappasswd命令，用-h选项指明加密时使用的方式。示例如下：
[root@localhost openldap]# /usr/local/openldap/sbin/slappasswd -h {MD5}
New password: 
Re-enter new password: 
{MD5}5jnSmZCVnBZyaTmptpc73w==


设置读写权限
http://blog.sina.com.cn/s/blog_4152a9f50100qw9w.html
http://www.cnitblog.com/tacimoto/archive/2006/12/19/20773.html

access to * 
		by anonymous auth
		by dn.subtree="ou=aclUsers,dc=stech,dc=com"  write
		by dn.subtree="ou=aclDisabledUsers,dc=stech,dc=com"  none
		by * read

access to dn.subtree="dc=stech,dc=com" 
		by anonymous  auth
        by dn.subtree="ou=aclUsers,dc=stech,dc=com"  write
		by dn.subtree="ou=aclDisabledUsers,dc=stech,dc=com"  none	
```

```
权限控制
	普通用户 ou=people
		能通过登录验证，但是进入软件时:LDAP error:无此对象
	
	禁用账号 ou=aclDisabledUsers  
		能通过登录验证，但是进入软件时:LDAP error:无此对象

	只读账号 ou=aclReadUsers  
		能登录，能查看所有用户，不能维护（增删改都没有权限）

	读写账号 ou=aclUsers
		登录，增删改查都可以
		
	指定账户授权
	
	隐藏密码
	
```

**************************************************************************************************************

```sh
启动LDAP
　	/usr/local/openldap/libexec/slapd  -d256 

/usr/local/etc/libexec/slapd

关闭LDAP
	kill -INT `cat /usr/local/openldap/var/run/slapd.pid`
	（或者 ps -ef|grep ldap    再  kill -9 id）

查看ldap是否运行
	/usr/local/openldap/bin/ldapsearch -x -b '' -s base'(objectclass=*)'

通过配置文件导入数据
 
	/usr/local/openldap/bin/ldapadd -x -W -D "cn=root,dc=stech,dc=com" -f  /usr/local/openldap/bin/ldap-initData.ldif
 
	/usr/local/openldap/bin/ldapadd -x -D "cn=root,dc=stech,dc=com" -w pwd12344 -f /usr/local/openldap/bin/ldap-initData.ldif

	/usr/local/openldap/bin/ldapadd -x -W -D "cn=root,dc=stech,dc=com"

备份
	/usr/local/openldap/sbin/slapcat > backup.ldif
	/usr/local/openldap/sbin/slapcat > /usr/local/openldap/bin/backup.ldif

查询条目
	/usr/local/openldap/bin/ldapsearch -x -b "dc=stech,dc=com"
	/usr/local/openldap/bin/ldapsearch -x -b "uid=test2,ou=people,dc=stech,dc=com"
	/usr/local/openldap/bin/ldapsearch -x -b "uid=0121140114,ou=People,dc=shcc,dc=edu,dc=cn"

删除
	/usr/local/openldap/bin/ldapdelete -x -D 'cn=root,dc=stech,dc=com' -w pwd12344 'uid=test2,ou=people,dc=stech,dc=com' 
 
导入数据（如果是备份的数据，需要过滤掉系统信息）
	1、新建过滤正则表达式slapcat.regex
		cd /usr/local/openldap/sbin/
		
		cat >slapcat.regex <<EOF
		/^creatorsName: /d
		/^createTimestamp: /d
		/^modifiersName: /d
		/^modifyTimestamp: /d
		/^structuralObjectClass: /d
		/^entryUUID: /d
		/^entryCSN: /d
		EOF
	
	2、过滤系统信息
		cat backup.ldif | sed -f slapcat.regex > backup1.ldif
 
	3、导入数据
		/usr/local/openldap/bin/ldapadd -x -D "cn=root,dc=stech,dc=com" -w pwd12344 -f backup1.ldif
```		
		
## 4、报错

```js
启动LDAP报错
[root@localhost bin]# /usr/local/openldap/libexec/slapd  -d256
5b62c60d @(#) $OpenLDAP: slapd 2.4.33 (Aug  2 2018 14:28:14) $
	root@localhost.localdomain:/usr/local/src/openldap-2.4.33/servers/slapd
5b62c60d bdb_db_open: warning - no DB_CONFIG file found in directory /usr/local/openldap/var/openldap-data: (2).
Expect poor performance for suffix "dc=stech,dc=com".
5b62c60d bdb(dc=stech,dc=com): BDB1539 Build signature doesn't match environment
5b62c60d bdb_db_open: database "dc=stech,dc=com" cannot be opened, err -30969. Restore from backup!
5b62c60d bdb(dc=stech,dc=com): BDB1566 txn_checkpoint interface requires an environment configured for the transaction subsystem
5b62c60d bdb_db_close: database "dc=stech,dc=com": txn_checkpoint failed: Invalid argument (22).
5b62c60d backend_startup_one (type=bdb, suffix="dc=stech,dc=com"): bi_db_open failed! (-30969)
5b62c60d bdb_db_close: database "dc=stech,dc=com": alock_close failed
5b62c60d slapd stopped.

缺少配置文件
cd /usr/local/openldap/var/openldap-data
cp DB_CONFIG.example DB_CONFIG

或者删除/usr/local/openldap/var/openldap-data目录下
 alock  __db.001  __db.002  __db.003  __db.004  __db.005  __db.006 等等
 
  =================================== 
 
 [root@localhost bin]# /usr/local/openldap/libexec/slapd -d256
5b63bd23 @(#) $OpenLDAP: slapd 2.4.33 (Aug  3 2018 10:08:03) $
	root@localhost.localdomain:/usr/local/src/openldap-2.4.33/servers/slapd
5b63bd23 daemon: bind(7) failed errno=98 (Address already in use)
5b63bd23 daemon: bind(7) failed errno=98 (Address already in use)
5b63bd23 slapd stopped.
5b63bd23 connections_destroy: nothing to destroy.

ps -ef|grep ldap
kill -9 
```

---

```java
导入备份的ldif数据报错
[root@localhost sbin]# /usr/local/openldap/bin/ldapadd -x -D "cn=root,dc=stech,dc=com" -w pwd12344 -f test2.ldif 
adding new entry "uid=test2,ou=people,dc=stech,dc=com"
ldap_add: Constraint violation (19)
	additional info: structuralObjectClass: no user modification allowed

https://www.cnblogs.com/ccdc/p/3356518.html
分析原因：slapcat备份出来的ldapback.ldif中有系统自动生成的系统信息不能导入需要清除

解决方案：清除ldapback.ldif中的系统信息

步骤：

1、新建过滤正则表达式slapcat.regex

cat >slapcat.regex <<EOF
/^creatorsName: /d
/^createTimestamp: /d
/^modifiersName: /d
/^modifyTimestamp: /d
/^structuralObjectClass: /d
/^entryUUID: /d
/^entryCSN: /d
EOF


2、过滤掉系统信息

cat ldapback.ldif | sed -f slapcat.regex > slapdata.ldif


3、使用ldapadd导入

ldapadd -H ldap://127.0.0.1 -x -D "用户" -f slapdata.ldif -w 密码
#或 服务器程序导入 初始使用可能失败
#slapadd -l slapdate.ldif

```



