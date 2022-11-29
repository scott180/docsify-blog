# javaNote

## 1、java常用方法

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComboboxVO {}


MessageResult result = JSON.parseObject(text, new TypeReference<MessageResult>() {});
List<DiffRegionLogisticsDetailVO> cateList = JSON.parseObject(text, new TypeReference<List<DiffRegionLogisticsDetailVO>>() {});

String detail = JSON.toJSONStringWithDateFormat(billDO, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat);
			
				
# 格式化全局时间
spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
spring.jackson.time-zone=GMT+8


@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
private Date createTime;


Assert.isTrue(!StringUtils.isEmpty(param.getPhone()), "联系方式不能为空");


@MapKey("operatorId")
List<Map<Integer, String>> queryOperatorList();

<select id="queryOperatorList" resultType="java.util.Map">
	select operator_id operatorId,operator_name operatorName from ins_region_log
	GROUP BY operator_id
</select>

```

### 1.1、lambda表达式

```java

/*** lambda表达式 */

// 循环
AtomicInteger total = new AtomicInteger(0);
productLogisticsDAOS.stream().forEach(dao -> {
	int temp = dao.getAmount() * dao.getCount();
	total.addAndGet(temp);
});
	
List<Integer> interceptProductIdList = interceptGoodsNumDAOS.stream().map(dao -> dao.getProductId()).distinct().collect(Collectors.toList());

List<DeliveryPackageDO> mainPackageDOList = packageDOS.stream().filter(dao -> dao.getTitle().equals(DriverPackageUtil.MAIN_PACKAGE_TEXT)).collect(Collectors.toList());


// 求和
Integer sum = detailDAOS.stream().mapToInt(DeliveryPackageGoodsDetailDAO::getNum).sum()
BigDecimal paymentAmount = purchaserAmountMap.values().stream().map(SupplierBillDetailVO::getAmount).reduce(BigDecimal::add).get();


/*** list转map */

// list转map-排序
LinkedHashMap<String, List<DeliveryOrderShopDAO>> addrMap = deliveryOrderShopDAOS.stream().collect(Collectors.groupingBy(DeliveryOrderShopDAO::getAddr_hash, LinkedHashMap::new, Collectors.toList()));

// list转map-多字段分组
Map<String, List<DeliveryGoodsDO>> deliveryGoodsMap = deliveryGoodsDOS.stream().collect(Collectors.groupingBy(item -> item.getVirtualgoodsId() + "_" + item.getTitle() + "_" + item.getSpec()));

// list转map-值为单个对象
Map<String, DeliveryDO> deliveryOrderMap = deliveryDOS.stream().collect(Collectors.toMap(DeliveryDO::getDeliveryOrder, a -> a));

// list转map-值为单个对象 （如有重复，用第一个）
Map<String, DeliveryDO> deliveryOrderMap = deliveryDOS.stream().collect(Collectors.toMap(DeliveryDO::getDeliveryOrder, a-> a,(k1,k2)->k1));


// list转map-值为对象的字段
 Map<Integer, Integer> goods2ProductParam = logisticsGoodsDAOS.stream().filter(dao -> goodsIdParamList.contains(dao.getGoodsId())).
                    collect(Collectors.toMap(LogisticsGoodsDAO::getGoodsId, LogisticsGoodsDAO::getProductId, (key1, key2) -> key2));


// 取最大最小值
Student ageMax = list.stream().max(Comparator.comparing(Student::getAge)).get();
Student ageMin = list.stream().min(Comparator.comparing(Student::getAge)).get();


Optional.ofNullable(type).orElse(0).intValue();

public static final int cpuNum = Runtime.getRuntime().availableProcessors();


```

```java
/*** list对象分组求和 */

List<WarehouseLogisticsDAO> basketList = warehouseLogisticsDOMapper.queryWarehouselogisticsBasket(warehouselogisticsOrderGoodQuery);

List<WarehouseLogisticsDAO> list = new ArrayList<>();

//（同一商家的数量相加）

// 分组求和1
basketList.stream().collect(Collectors.groupingBy(item -> item.getAddrTele() + "_" + item.getAddrAddress())).forEach((key, groupList) -> {
                WarehouseLogisticsDAO dao = new WarehouseLogisticsDAO();
                dao.setAmount(groupList.stream().mapToInt(WarehouseLogisticsDAO::getAmount).sum());
                dao.setAddrAddress(key);
                list.add(dao);
            });
			
// 分组求和2
basketList.parallelStream().collect(Collectors.groupingBy(item -> item.getAddrTele() + "_" + item.getAddrAddress(), Collectors.toList()))
		.forEach((key, groupList) -> {
					groupList.stream().reduce((a, b) -> {
						WarehouseLogisticsDAO dao = new WarehouseLogisticsDAO();
						dao.setAddrAddress(key);
						dao.setAmount(a.getAmount() + b.getAmount());
						return dao;
					}).ifPresent(list::add);
				}
		);
		

```

### 1.2、Map遍历

```java

java中Map遍历的四种方式
https://www.cnblogs.com/damoblog/p/9124937.html

Map<String,String> map = new HashMap<String,String>();
map.put("熊大", "棕色");
map.put("熊二", "黄色");


for(Map.Entry<String, String> entry : map.entrySet()){
    String mapKey = entry.getKey();
    String mapValue = entry.getValue();
    System.out.println(mapKey+":"+mapValue);
}

 map.entrySet().forEach(en->{
                en.getKey();
                en.getValue();
            });

//key
for(String key : map.keySet()){
    System.out.println(key);
}
//value
for(String value : map.values()){
    System.out.println(value);
}


Iterator<Entry<String, String>> entries = map.entrySet().iterator();
while(entries.hasNext()){
    Entry<String, String> entry = entries.next();
    String key = entry.getKey();
    String value = entry.getValue();
    System.out.println(key+":"+value);
}


for(String key : map.keySet()){
    String value = map.get(key);
    System.out.println(key+":"+value);
}


```

### 1.3、java排序

```java
java排序

public static void main(String[] args) {
        // 1、数组排序
        int[] arr = {2, 3, 4, 5, 2, 1};
        Arrays.sort(arr);
        System.out.println(JSON.toJSON(arr));

        // 2、列表排序
        List<Integer> list = new ArrayList<Integer>();
        list.add(5);
        list.add(9);
        list.add(3);
        list.add(1);
        Collections.sort(list);
        System.out.println(JSON.toJSON(list));

        Random random = new Random();
        List<BuffProductNumDAO> numDAOList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BuffProductNumDAO buffProductNumDAO = new BuffProductNumDAO();
            buffProductNumDAO.setProductId(random.nextInt(10));
            numDAOList.add(buffProductNumDAO);
        }
        System.out.format("Comparator before list=%s", JSON.toJSON(numDAOList));
		
        // 3、Comparator排序
        Collections.sort(numDAOList, new Comparator<BuffProductNumDAO>() {
            @Override
            public int compare(BuffProductNumDAO p1, BuffProductNumDAO p2) {
                // 调用compare方法大于0，就把前一个数和后一个数交换，也就是把大的数放后面了，
                // 即所谓的升序了。如果第二个参数与第一个参数调换顺序，也就是降序了。
                int product = p2.getProductId() - p1.getProductId();
                return product;
            }
        });
        System.out.println();
        System.out.format("Comparator after list=%s", JSON.toJSON(numDAOList));

        Collections.sort(logisticsDAOList, Comparator.comparing(WarehouseLogisticsDAO::getSort).thenComparing(WarehouseLogisticsDAO::getDistance));
				
				
        // 4、Comparable排序
        List<Goods> goodsList = new ArrayList<>();
        Collections.sort(goodsList);

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Goods implements Comparable<Goods> { //实现Comparable接口，利用泛型限定比较的类型
        private Integer productId; //商品编号
        private String name; //商品名称
        private double price; //商品价格

        @Override
        public int compareTo(Goods o) {  //重写compareTo方法。
            //取出商品价格
            double price1 = this.getPrice();
            double price2 = o.getPrice();
            int n = new Double(price2 - price1).intValue();  //double类型的差值转为int
            return n;
        }
    }
	
	
```


```sql
Java8排序stream.sorted() 
https://blog.csdn.net/qq_34996727/article/details/94472999

System.out.println("---Natural Sorting by Name---");
List<Student> slist = list.stream().sorted().collect(Collectors.toList());
slist.forEach(e -> System.out.println("Id:" + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge()));

System.out.println("---Natural Sorting by Name in reverse order---");
slist = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
slist.forEach(e -> System.out.println("Id:" + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge()));

System.out.println("---Sorting using Comparator by Age---");
slist = list.stream().sorted(Comparator.comparing(Student::getAge)).collect(Collectors.toList());
slist.forEach(e -> System.out.println("Id:" + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge()));

System.out.println("---Sorting using Comparator by Age with reverse order---");
slist = list.stream().sorted(Comparator.comparing(Student::getAge).reversed()).collect(Collectors.toList());
slist.forEach(e -> System.out.println("Id:" + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge()));


分组排序
https://www.toutiao.com/article/7096484701099295263

TreeMap<TypeEnum, List<Customer>> treeMap4 = customers.stream().sorted(Comparator.comparing(Customer::getAge, Comparator.nullsLast(Integer::compareTo)))
		.collect(Collectors.groupingBy(Customer::getType, TreeMap::new, Collectors.toList()));
	
	
Map<String, List<LogisticsStatisticsDAO>> logisticsRegionMap = logisticsStatisticsDAOS.stream().
		collect(Collectors.groupingBy(LogisticsStatisticsDAO::getRegion, WarehouseUtil::getCustomSortTreeMap, Collectors.toList()));
		
public static TreeMap<String, List<LogisticsStatisticsDAO>> getCustomSortTreeMap() {
	// return new TreeMap<>(Comparator.comparingInt(TypeEnum::getPriority));
	TreeMap<String, List<LogisticsStatisticsDAO>> map = new TreeMap<String, List<LogisticsStatisticsDAO>>(new Comparator<String>() {
		@Override
		public int compare(String r1, String r2) {
			// 升序
			return CommonUtil.regionSort(r1, r2);
		}
	});
	return map;
}
	
```

### 1.4、flatmap,peek,newArrayList

```java
JAVA8 中的flatmap

使用flatMap方法的效果是，各个数组并不是分别映射一个流，而是映射成流的内容，所有使用map(Array::stream)时生成的单个流被合并起来，即扁平化为一个流。
https://blog.csdn.net/liyantianmin/article/details/96178586
https://blog.csdn.net/zhuwukai/article/details/82888316
https://www.jianshu.com/p/ecb8e8f77a89

 public static void main(String[] args) {
	List<User> uList = Lists.newArrayList();
	User u1 = new User();
	u1.setAddr("a1;a2;a3;a4;a5");

	User u2 = new User();
	u2.setAddr("b1;b2;b3;b4;b5");

	uList.add(u1);
	uList.add(u2);

	List<String> addrList = uList.stream().map(x -> x.getAddr()).flatMap(x-> Arrays.stream(x.split(";"))).collect(Collectors.toList());
	//或者
	List<String> ridStrList = uList.stream().map(x -> x.getAddr()).map(x -> x.split(";")).flatMap(Arrays::stream).collect(Collectors.toList());
	System.out.println(addrList);
}

@Data
@NoArgsConstructor
public class User{
	private   String addr;
}


---


public static class User {
        private String name;
        private List<String> relativeUsers;
}

List<String> strings = users.stream()
  .flatMap(user -> user.getRelativeUsers().stream())
  .collect(Collectors.toList());
	
```


```
Java 8 Stream peek 与 map的区别
原文链接：https://blog.csdn.net/tckt75433/article/details/81510743
总结：peek接收一个没有返回值的λ表达式，可以做一些输出，外部处理等。map接收一个有返回值的λ表达式，之后Stream的泛型类型将转换为map参数λ表达式返回的类型。

```

```java
// 创建数组的四种方法
int[] a1;
int[] a2 = {1, 2, 3};
int[] a3 = new int[]{1, 2, 3};

int[] a4 = new int[3];
a4[0] = 1;
a4[2] = 2;
a4[3] = 3;

```

```java
几个快速添加list的方法
1. 使用Collections.addAll()方法，前提还是需要手动 new ArrayList
ArrayList<String> s = new ArrayList();
Collections.addAll(s,"1","2","3")

2. 使用Arrays.asList(...args) 直接返回一个List
List<String> s = Arrays.asList("1","2","3")
// 可能会抛异常 UnsupportOperationException

3. 如果引入了Guava的工具包，可以使用他的Lists.newArrayList(...args)方法
List<String> list = Lists.newArrayList("1","2","3")

4. 如果是Java9，可以使用自带的List类
List<String> s = List.of("1","2","3")

```

```
使用Arrays.asList()报错 UnsupportOperationException 原因

常常使用Arrays.asLisvt()后调用add，remove这些method时出现java.lang.UnsupportedOperationException异常。这是由于：
Arrays.asLisvt() 返回java.util.Arrays$ArrayList， 而不是ArrayList。

Arrays$ArrayList和ArrayList都是继承AbstractList，remove，add等 method在AbstractList中是默认throw UnsupportedOperationException而且不作任何操作。
ArrayList override这些method来对list进行操作，但是Arrays$ArrayList没有override remove(int)，add(int)等，所以throw UnsupportedOperationException。

解决方法：
List<String> list=new ArrayList(Arrays.asList(nameList));
 
```

---

## 2、idea基本配置与快捷键

### 2.1、idea配置与插件


```java
idea基本配置  File -- Settings

1、修改快捷键
Keymap  Eclipse

2、调整字体
font  - Size

3、配置 maven  （Setting For New Projects）
Maven home directory:   D:/ProgramFiles/apache-maven-3.6.0
User setting file:      D:\ProgramFiles\apache-maven-3.6.0\conf\settings.xml
Local repository:       D:\ProgramFiles\apache-maven-localRepository

4、配置Git命令行
Terminal - Shell path
D:\ProgramFiles\git\Git\bin\bash.exe

5、自动生成作者信息
file and code Templates -- Includes -- File Header
/**
 * @author xyq
 * @date ${DATE} ${TIME}
 */

 
6、代码自动导入包
Settings→Editor→General→Auto Import
选中Optimize imports on the fly 和 Add unambiguous imports on the fly

7、代码自动定位文件
Project - Show Options Menu - Autoscroll From Source

8、显示成员变量及方法
Project - Show Options Menu - Show Members

9、初始化idea（删除所有配置及历史记录）
删除目录 C:\Users\Administrator\.IdeaIC2019.1

```

```
idea常用插件 File -- Settings -- Plugins
lombok
Free Mybatis plugin    MybatisX
GenerateAllSetter      Alt+Enter
Spring Assistant 
Translation

Alibaba Java Coding Guidelines
Markdown
swagger
```

### 2.2、idea常用快捷键

```
eclipse & idea常用快捷键

sout            打印
fori            循环
Shift+Enter     另起一行
Alt+Enter       快速命名
Ctrl+h          全局关键词搜索
Ctrl+f          当前文件查找、替换
Ctrl+shift+r    全局文件查找
Ctrl+shift+f    整理代码格式
Ctrl+shift+x    大小写
Ctrl+shift+o    整理导入包
Alt+insert      快速生成get和set方法、构造方法
Alt+shift+r     批量重命名
Alt+shift+m     提取本地变量及方法

---
    
Ctrl+Alt+↑      往上或下复制当前内容
Alt+↓           将当前行的内容往上或下移动
Alt+/           导入一个包
Ctrl+m          编辑器窗口最大化
Ctrl+o          快速outline，查找方法
Ctrl+e          快速转换编辑器
syso+Alt+/      输出

```

---

## 3、java软件

### 3.1、jdk java软件

| 软件     | 下载地址      |
| -------- | --------      |
| idea     | [idea官方下载]( https://www.jetbrains.com/idea/download/other.html ) &ensp; [idea2019]( https://www.aliyundrive.com/s/oWgxBBNqGj9 )     |
| maven    | [maven官方下载]( https://archive.apache.org/dist/maven/maven-3/ )            |
| mysql    | [mysql_5.7]( https://www.aliyundrive.com/s/pymjQca3DbY )                     |
| javaSoft | [java软件]( https://www.aliyundrive.com/s/fWXemUwcsUs )  redis/mongo/Navicat/kafka/zookeeper/git/Xshell...                              |
| tomcat   | [tomcat官网]( https://archive.apache.org/dist/tomcat/ ) &ensp; ([云盘]( https://pan.baidu.com/s/1yPhAfIcACTGkpIOYlEds1g )  密码: j9ug ) |
| eclipse  | [eclipse官方下载]( http://www.eclipse.org/downloads/packages )               |

---

> Java Development Kit (JDK) 是Sun公司（已被Oracle收购）针对Java开发员的软件开发工具包。自从Java推出以来，JDK已经成为使用最广泛的Java SDK（Software development kit）。

- [ ] jdk来自[官网]( http://www.oracle.com/technetwork/java/archive-139210.html ) ，这些软件有三种格式：tar.gz、zip、bin
- 1、zip是windoxs软件，解压后会有exe格式的jdk软件，直接安装即可。
- 2、tar.gz是linux软件，需要用 `tar -zxvf xx.tar.gz` 解压。
- 3、bin也是linux软件，需解压：
   - 添加执行权限 
    `chmod u+x jdk-6u45-linux-x64.bin`
   - 解压 
    `./jdk-6u45-linux-x64.bin`
   
- [ ] 软件名称里含有x64则是64位软件，32位为则没有。如下：
- `jdk-8u162-windows-x64.zip`  `jdk-8u162-linux-x64.tar.gz`  是64位软件
- `jdk-8u72-windows-i586.zip`  `jdk-8u72-linux-i586.tar.gz`  是32位软件


| 云盘链接                                                      | 密码 |
| --------------------------------                              | ---  |
| [jdk6]( https://pan.baidu.com/s/1z3p1DecyBVugP7cECIupyg )     | 829h |
| [jdk7]( https://pan.baidu.com/s/17ik9x-g3RkYEu6vah9CZVw )     | muvr |
| [jdk8]( https://pan.baidu.com/s/1MT8zldLnH9PuZsVR77DEAw )     | mv5i |
| [jdk9]( https://pan.baidu.com/s/1SMGJqedJKR3hULrpWn4eLA )     | hai6 |
| [jdk10]( https://pan.baidu.com/s/1SHA7XNoPxBdOkaed3cunow )    | hgyn |

---

```
java  mysql  maven  
idea  git  navicat notepad++  
postman xshell fillder typora VMware
redis mongo kafka zookeeper tomcat eclipse
python  nodejs vue 
火绒安全软件 向日葵 Everything
```

---

### 3.2、java maven环境变量

```java
java环境变量配置    注意：环境变量中都是英文符号，结尾以英文分号;结束
创建  JAVA_HOME     C:\Program Files\Java\jdk1.8.0_162
添加  Path          %JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;
创建  CLASSPATH     .;%JAVA_HOME%\lib;%JAVA_HOME%\lib\tools.jar;

验证 java -version

---

maven环境变量配置
创建  MAVEN_HOME    D:\ProgramFiles\apache-maven-3.6.0
添加  Path          %MAVEN_HOME%\bin;

验证 mvn -version

---

tomcat环境变量配置
创建  TOMCAT_HOME   E:\ProgramFiles\apache-tomcat-8.5.31
添加  CLASSPATH     %TOMCAT_HOME%\BIN;

验证 
启动 bin/startup.bat 
访问 http://localhost:8080/ （或 http://127.0.0.1:8080/ ）

```

---


### 3.3、postman配置

```js
postman环境变量配置
--manage environments
https://blog.csdn.net/mt122/article/details/104530439
https://www.jianshu.com/p/391e995881c0

--Tests
var jsonData = JSON.parse(responseBody);
postman.setGlobalVariable("webToken", jsonData.data.token);
 

 
postman 出现Error: connect ECONNREFUSED 127.0.0.1:端口
https://blog.csdn.net/weixin_45993202/article/details/109072188

Settings--Proxy
去掉勾选 Use the system proxy

```

---

## 4、springboot项目打包布署

```sh
springboot项目打包布署
https://gitlab.com/xuyq123/calligraphy-boot

方法一：maven打包jar、运行jar
前提：安装java软件、构建jar项目

Administrator@ho-xyq MINGW64 /e/Project/gitlab/calligraphy-boot (dev_2021072301)
$ mvn clean package

Administrator@ho-xyq MINGW64 /e/Project/gitlab/calligraphy-boot/calligraphy-boot-start/target (dev_2021072301)
$ java -jar calligraphy-boot-start-1.0-SNAPSHOT.jar


方法二：maven打包war、布署tomcat
前提：安装java软件、构建war项目

1、mvn clean package  生成war
2、将 calligraphy-boot.war 复制到 E:\ProgramFiles\apache-tomcat-8.5.31\webapps
3、启动tomcat   E:\ProgramFiles\apache-tomcat-8.5.31\bin\startup.bat


----

maven常用打包命令
1、mvn compile 编译,将Java 源程序编译成 class 字节码文件。
2、mvn test 测试，并生成测试报告
3、mvn clean 将以前编译得到的旧的 class 字节码文件删除
4、mvn pakage 打包,动态 web工程打 war包，Java工程打 jar 包。
5、mvn install 将项目生成 jar 包放在仓库中，以便别的模块调用
6、mvn clean install -Dmaven.test.skip=true  抛弃测试用例打包

```

---


## 5、我的网站

> **平台**

- 若有志同道合的小伙伴想联系本人，可通过以下方式发邮件或私信。路漫漫其修远兮，吾将上下而求索。共勉。

| 平台           | 链接           |
| -------------- | -------------- |
|  **项目仓库**  | [gitlab]( https://gitlab.com/xuyq123/calligraphy ) &ensp; [coding]( https://xyqin.coding.net/public/my/calligraphy/git ) &ensp; [github]( https://github.com/scott180/calligraphy )  &ensp; [bitbucket]( https://bitbucket.org/xu12345/calligraphy ) &ensp; [gitee]( https://gitee.com/xy180/calligraphy ) &ensp; [sourceforge]( https://sourceforge.net/p/calligraphy/code )  &ensp; [vuepress]( https://scott180.github.io/vuepress-calligraphy )    |
|  **资讯账号**  | [微信公众号]( https://mp.weixin.qq.com/s/HmdDsCaeumuZg_DfitIdlw ) &ensp; [头条]( https://www.toutiao.com/c/user/token/MS4wLjABAAAA2_bWhiknCbcKNu4c6VTM2B7m2vr7zBrh0x6fSyOrtGU ) &ensp;  [豆瓣]( https://www.douban.com/people/80730595/photos ) &ensp;  [知乎]( https://www.zhihu.com/people/xu-xian-sheng-72-29/posts )     |
|  **个人邮箱**  | 1021151991@qq.com   |

***

> **公众号**

- 注册了微信公众号及今日头条号：[**无为徐生**]( https://scott180.github.io/calligraphy/%E6%97%A0%E4%B8%BA%E5%BE%90%E7%94%9F )，以后会将书法练习轨迹、程序员笔记以及一些随笔感想更新在此。<br/>
- 每周一会在无为徐生**微信公众号**同步《书法练习轨迹》，持续更新，敬请关注。

| 无为徐生   | 微信公众号                                               	 |  &ensp; |  今日头条号        |
| ---------  | ------------------------------------------------------------- |  -      |  ----------        |
|  二维码    | ![w]( https://md.xushufa.cn/gitimg/imgs/other/wuweixusheng_weixin.png ) | <br/> | ![t]( https://md.xushufa.cn/gitimg/imgs/other/wuweixusheng_toutiao.png )     |

***

> **我的网站**

- 生活随笔-编程笔记-书法练习轨迹

| 徐书法 | 地址        |  备注          |
| -----  | ----------- |  ------------- |
| 1      | [xushufa.cn]( https://xushufa.cn )            | 书法练习轨迹网站。                   |
| 2      | [blog.xushufa.cn]( https://blog.xushufa.cn )  | `vuepress`构建的博客网站。           |
| 3      | [web.xushufa.cn]( https://web.xushufa.cn )    | `vuepress-theme-reco`构建的博客网站。|

***

