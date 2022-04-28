# mongo

<br />

*   [1、说明](#introducer)
*   [2、增删改查](#crud)
*   [3、三种情况下mongo内嵌文档的查询与保存、](#innerDocument)


 <h2 id="introducer"></h2>

## 1、说明
```
SQL术语/概念 	MongoDB术语/概念 	解释/说明
database 		database 			数据库
table 			collection 			数据库表/集合
row 			document 			数据记录行/文档
column 			field 				数据字段/域
index 			index 				索引
table 			joins 	  			表连接,MongoDB不支持
primary 		key 	primary key 	主键,MongoDB自动将_id字段设置为主键
```

```
启动mongo    /opt/sudytech/mongodb           ./bin/mongod --config mongo.conf
										     ./bin/mongod --config /opt/sudytech/mongodb/conf/mongo.conf 
											 
启动mongo
  /opt/sudytech/mongodb/bin/mongod --dbpath=/opt/sudytech/mongodb/data --logpath=/opt/sudytech/mongodb/logs --logappend  --port=27017 --fork
  
登陆mongo
cd /opt/sudytech/mongodb/bin
mongo
```
```
创建数据库
	use test
	db.test.insert({"name":"111"})
	
删除数据库	
	use test
	db.dropDatabase()

显示数据库      show dbs
显示集合（表）  show collections
显示文档（行）  db.test.find()
复制数据库      db.copyDatabase(<from_dbname>, <to_dbname>, <from_hostname>);  例如：db.copyDatabase('test','test1');


按时间查询
db.getCollection('mongomessage').find({createTime:{$gt:ISODate("2018-06-28T22:00:00.000Z")}}).count

db.getCollection('mongomessage').find({fromAddress:'uc_b:(71133)一卡通余额提醒',createTime:{$gt:ISODate("2018-06-28T22:00:00.000Z")}}).count()

db.getCollection('mongomessage').find({fromAddress:'uc_b:(71135)学生课表信息提醒',createTime:{$gt:ISODate("2018-06-28")}}).count()

db.getCollection('mongomessage').find({fromAddress:'uc_b:(71134)教师课表信息提醒',createTime:{$gt:ISODate("2018-06-28T22:00:00.000Z")}}).count()
    
```  

```
导出一个表（文档）  E:\Program Files (x86)\mongo\bin>mongoexport -d ucpplus1 -c mongomsgbody -o e:\mongomsgbody.bak.json
导入一个表（文档）  E:\Program Files (x86)\mongo\bin>  mongoimport -d test -c mongo1 e:\mongomsgbody.bak.json


导出数据库（备份）  E:\Program Files (x86)\mongo\bin>  mongodump -d ucpplus1 -o e:\my_mongodb_dump
导入数据库（还原）  E:\Program Files (x86)\mongo\bin>mongorestore -d ucpplus3 e:\ucpplus1_dump\ucpplus1
（还原时的数据目录必须是原来新建的目录与备份的数据库名称结合。数据库名称与原来一样是还原数据。数据库名称不一样会新建一个数据库）
https://www.cnblogs.com/qingtianyu2015/p/5968400.html

./mongodump -d ucpplus -o /home/mongobak/ucpplus/
```

<h2 id="crud"></h2>

## 2、增删改查 
```
1 列出并选用

1.1 列出所有数据库

	
> show dbs 
local  0.000GB
myblog 0.000GB

1.2 使用某个数据库

> use myblog
switched to db myblog


查看数据库空间大小
db.stats(1073741824);//得到的是G单位的


1.3 列出所有集合

> show collections
articles
replicationColletion
sessions
users
wangduanduan

2 插入数据 insert(value)
	
// 在已经存在的集合中插入数据
> db.users.insert({name:'hh',age:23})
Inserted 1 record(s) in 43ms
 
// 在不存在的集合中插入数据,集合不存在则自动创建集合并插入
> db.students.insert({name:'hh',age:23})
Inserted 1 record(s) in 72ms

3 查询 find(option)

3.1 查询集合里所有的文档

	
> db.users.find()
/* 1 */
{
  "_id" : ObjectId("583e908453be942d0c5419dc"),
  "login_name" : "wangduanduan",
  "password" : "wrong age"
}
 
/* 2 */
{
  "_id" : ObjectId("583ed2a5cc9a937db049616d"),
  "login_name" : "hh",
  "password" : "sdfsdf"
}
 
/* 3 */
{
  "_id" : ObjectId("583fb2e9b12f8b7a7aa37572"),
  "name" : "wangduanduan",
  "age" : 34.0
}
 
/* 4 */
{
  "_id" : ObjectId("583fb707b12f8b7a7aa37573"),
  "name" : "hh",
  "age" : 23.0
}

3.2 按条件查询文档
	
> db.users.find({name:'wangduanduan'})
/* 1 */
{
  "_id" : ObjectId("583fb2e9b12f8b7a7aa37572"),
  "name" : "wangduanduan",
  "age" : 34.0
}

注意

	
// 这是错的，查不到结果
> db.users.find({_id:'583fb2e9b12f8b7a7aa37572'})
Fetched 0 record(s) in 1ms


	
// 这是正确的
> db.users.find({_id:ObjectId('583fb2e9b12f8b7a7aa37572')})
/* 1 */
{
  "_id" : ObjectId("583fb2e9b12f8b7a7aa37572"),
  "name" : "wangduanduan",
  "age" : 34.0
}

3.3 查询集合内文档的个数

	
> db.users.count()
4

3.4 比较运算符

$gt: 大于

$gte: 大于等于

$lt: 小于

$lte: 小于等于

$ne: 不等于

	
// 查询用户里年龄大于30岁的人， 其他条件以此类推
> db.user.find({age:{$gt:30}})
 
/* 1 */
{
  "_id" : ObjectId("583fb2e9b12f8b7a7aa37572"),
  "name" : "wangduanduan",
  "age" : 34.0
}

3.5 逻辑运算符

3.5.1 与
	
// 查询名字是wangduanduan,age=34的用户
> db.users.find({name:'wangduanduan',age:34})
/* 1 */
{
  "_id" : ObjectId("583fb2e9b12f8b7a7aa37572"),
  "name" : "wangduanduan",
  "age" : 34.0
}

3.5.2 $in 或

	
// 查询名字是wangduanduan,或hh的用户
> db.users.find({name:{$in:['wangduanduan','hh']}})
/* 1 */
{
  "_id" : ObjectId("583fb2e9b12f8b7a7aa37572"),
  "name" : "wangduanduan",
  "age" : 34.0
}

3.5.3 $nin 非

	
// 查询名字不是wangduanduan或者hh的用户
> db.users.find({name:{$nin:['wangduanduan','hh']}})
/* 1 */
{
  "_id" : ObjectId("583e908453be942d0c5419dc"),
  "login_name" : "wangduanduan",
  "password" : "wrong age"
}
 
/* 2 */
{
  "_id" : ObjectId("583ed2a5cc9a937db049616d"),
  "login_name" : "hh",
  "password" : "sdfsdf"
}

3.6 正则匹配

	
// 查询名字是中含有duan的用户
> db.users.find({name:/duan/})
/* 1 */
{
  "_id" : ObjectId("583fb2e9b12f8b7a7aa37572"),
  "name" : "wangduanduan",
  "age" : 34.0
}
 
/* 2 */
{
  "_id" : ObjectId("583fc919b12f8b7a7aa37575"),
  "name" : "wangduanduan",
  "age" : 45.0
}

3.7 大招$where

	
// 返回含有login_name字段的文档
db.getCollection('users').find({$where:function(){
  return !!this.login_name;
}})

4 更新 update();

4.1 整体更新

> db.users.update({login_name:'wangduanduan'},{name:'heihei',age:34})
Updated 1 existing record(s) in 116ms

4.2 $set 局部更新

// 只是将用户年龄设置成101
> db.users.update({name:'wangduanduan'},{$set:{age:101}})

4.3 $inc

// 将用户年龄增加1岁，如果文档没有age这个字段，则会增加这个字段
> db.users.update({name:'wangduanduan'},{$inc:{age:1}})

4.3 upsert操作

// 如果查不到文档，则增加文档
> db.users.update({name:'nobody'},{$inc:{age:1}},true)
Updated 1 new record(s) in 3ms

/* 6 */
{
    "_id" : ObjectId("583fd20f2cfa6a4817c4171c"),
    "name" : "nobody",
    "age" : 1.0
}

db.student.update({"name":"李四","class":/大/},{$set:{"teacher":"老师","school":"行知学校"}})

 $inc 就是专门用来增减数字的。且只能用于整型、长整型或者双精度浮点型的值。其他类型的数据会操作失败。
 $inc 键的值必须为数字”，不能使用字符串、数组或者其他非数字的值。要修改其他类型，应该使用 $set 或者数字修改器。
 

4.4 批量更新

// upadate 的第四个参数设置成true的时候，就会批量更新
> db.users.update({name:'wangduanduan'},{$set:{age:1891}},false,true)

5 删除

// 删除某些文档
db.person.remove({"name":"joe"})

// 删除整个集合
db.test1.remove({})   删除集合中的文档
db.test1.drop()       删除集合

复制数据库      db.copyDatabase(<from_dbname>, <to_dbname>, <from_hostname>);  例如：db.copyDatabase('test','test1');

导出一个表（文档）  E:\Program Files (x86)\mongo\bin>mongoexport -d ucpplus1 -c mongomsgbody -o e:\mongomsgbody.bak.json
导入一个表（文档）  E:\Program Files (x86)\mongo\bin>  mongoimport -d test -c mongo1 e:\mongomsgbody.bak.json


导出数据库（备份）  E:\Program Files (x86)\mongo\bin>  mongodump -d ucpplus1 -o e:\my_mongodb_dump
导入数据库（还原）  E:\Program Files (x86)\mongo\bin>mongorestore -d ucpplus3 e:\ucpplus1_dump\ucpplus1
（还原时的数据目录必须是原来新建的目录与备份的数据库名称结合。数据库名称与原来一样是还原数据。数据库名称不一样会新建一个数据库）
https://www.cnblogs.com/qingtianyu2015/p/5968400.html

./mongodump -d ucpplus -o /home/mongobak/ucpplus/


{ "_id" : ObjectId("4e3f42f36266b5845052c03d"), "Bpid" : [ { "$ref" : "B", "$id" : ObjectId("4e3f3de16266b5845052c036") } ], "value" : 5 }  

http://lhkzyz.iteye.com/blog/1669796
内嵌文档查询
    db.teacher.find("$elemMatch":{"students.age":"15","students.hobby":"football"})  

    db.teacher.find({"students:"{"$elemMatch":{"age":"15","hobby":"football"}}})  


	正则查询 （忽略大小写）
	db.getCollection('channelmessagestate').find({ "orignTo" : { "$regex" : ".*uc_u:\\(2\\).*" , "$options" : "i"} } )
	db.getCollection('channelmessagestate').find({"recipientBy.address":{ "$regex" : ".*uc_u:\\(2\\).*" , "$options" : "i"} } )

http://www.runoob.com/mongodb/mongodb-query.html


排序  sort()方法可以通过参数指定排序的字段，并使用 1 和 -1 来指定排序的方式，其中 1 为升序排列，而-1是用于降序排列。

db.getCollection('mongomessage').find({}).sort({"createTime":-1})   

```


 <h2 id="innerDocument"></h2>

## 3、内嵌文档

> 三种情况下mongo内嵌文档的查询与保存

```

一、内嵌文档（一个对象）
	"recipientBy" : {                                    // 发给用户时收件人类型、id、名称
			"type" : 0,
			"address" : "uc_u:(2)71070001"                   
		}
	
https://segmentfault.com/q/1010000000710641/a-1020000000711864

查询语句可以简化一下，如下：

db.demo.find({"people_id" : 1, "albums.privilege": 5})
查询结果返回的是满足匹配条件的文档，虽然可以映射返回的字段，不过还真不能只返回你说的id为2和3的文档。不过既然已经得到了满足条件的json文档，可以写程序来获取想要的内嵌子文档吧。

更新语句如下：

db.demo.update({people_id:2, "albums.id":2}, { $set : {"albums.$.name":6 }})


db.demo.update({ "_id" : { "$oid" : "5ac2de789e15ae8da0b49493"}},{ "$set" : { "recipientBy.address" : "uc_b:(12)张hezi"}})

```
```

2、内嵌文档（数组内多个对象）
"mongoChannelTargets" : [                         //收件对象              
        {
            "channelIds" : [ 
                3, 
                4
            ],
            "address" : "uc_o:(8)金智",              //收件人为机构
            "type" : 0
        }, 
        {
            "channelIds" : [ 
                3, 
                4
            ],
            "address" : "uc_u:(7)组用户1",          //收件人为用户
            "type" : 0
        }]
		

https://blog.csdn.net/drifterj/article/details/7833883
db.blogs.find({"comment":{"$elemMatch":{"author":"joe", "score":{"$gte":3}}}});  



https://blog.csdn.net/qq_20127333/article/details/51508863
操作符:$set修改数组中的元素

例句:db.blog.update({"comments.testAdd":"T"},{$set:{"comments.$.testAdd":"z"}});

这里注意第一个查询条件必须数组.字段名，否则修改失败，有人可能会问后面的{"comments.$.testAdd":"z"}中的$符是干嘛用的,他在这里面代表的相当于是数组的下表，如果我们明确的知道下标的话，我们完全可以这么写，比如下表为0：{"comments.0.testAdd":"z"}，但大多数情况下我们是不知道下标的，所以用通配符$来表示，这样只会修改匹配的第一条数据，而不是所有匹配到的数据，这点需要注意


https://blog.csdn.net/leshami/article/details/55192965 
 db.students.update(
        ... { _id: 7, "grades.grade": 85 },
        ... { $set: { "grades.$.grade1" : 6 } })

 db.demo.update(		
{ "mongoChannelTargets.address" : "uc_u:(2)组用户111" , "_id" : { "$oid" : "5ac2de779e15ae8da0b49400"}},{ "$set" : { "mongoChannelTargets.$.address" : "uc_u:(11)张name"}})

```
```

 3、内嵌文档（数组）		
to" : [                                         //收件人类型、id、名称
        "uc_o:(8)金智",                 
        "uc_g:(3)3", 
        "uc_a:(4)通讯录1", 
        "uc_u:(3)徐", 
        "uc_u:(4)花", 
        "uc_u:(6)王", 
        "uc_u:(2)彭", 
        "uc_u:(7)组用户1",
		"uc_b:(7)消息盒1"
    ]	

https://blog.csdn.net/qingyuanluofeng/article/details/49148327

db.fruitshop.find({"fruits":{"$all":["apple","banana"]}}); 
{ "boxIds" : { "$all" : [ 8]}}

http://www.cnblogs.com/ginb/p/6200721.html

```