# [note]( https://gitlab.com/xuyq123/mynotes/-/blob/master/note.md )
*   [1、日常](#note)
*   [2、工作](#wrok)
*   [3、java](#java)
*   [4、git仓库](#gitId)


- [ ] `仓库` &ensp; [gitlab]( https://gitlab.com/xuyq123/mynotes ) &ensp; [gitee]( https://gitee.com/xy180/MyNotes ) &ensp; [github]( https://github.com/scott180/MyNotes ) &ensp; [csdn_code]( https://codechina.csdn.net/xu180/MyNotes )  &ensp; [coding]( https://xyqin.coding.net/public/my/MyNotes/git/files ) &ensp; [bitbucket]( https://bitbucket.org/xu12345/mynotes )

 <h2 id="note"></h2>

## 1、日常

### 1.1、文件相关操作

#### 1.1.1、windows常用快捷键

```java
windows常用快捷键

Ctrl+C  复制选中内容
Ctrl+V  粘贴复制内容
Ctrl+S  保存
Ctrl+A  选中全部内容
Ctrl+X  剪切
Ctrl+F  查找与替换
        
Ctrl+W  关闭程序
Ctrl+Z  撤销刚才进行的操作
Ctrl+Y  恢复刚才进行的操作
Ctrl+N  新建一个空白文档
Ctrl+P  打开"打印"对话框
Ctrl+O  打开文档

Ctrl+Shift  输入法切换
Ctrl+Home   光标快速移到文件头
Ctrl+End    光标快速移到文件尾
Alt+Tab     程序切换

Win     显示开始菜单
Win+L   锁定计算机
Win+E   启动"我的电脑"
Win+D   快速显示桌面
Win+R   打开电脑"运行"对话框

F2   文件夹改名
F5   浏览器页面刷新
F11  浏览器进入全屏状态
F12  浏览器打开调试


```


#### 1.1.2、修改文件的默认打开方式

```
修改文件的默认打开方式：

1.修改属性
右击--属性--常规--打开方式--选择默认程序

2.选择默认方式
右击--打开方式--选择默认程序

3.注册表设置
在运行里面输入regedit（注册表编辑器），HEY_CLASS_ROOT下面是所有文件的后缀名文件，找到你想取消关联的后缀名，如.ppt，单击这个文件夹，右边出现的“数据”一项，双击“（默认）”，打开对话框，将“数据”下面可以填写的部分清除，保持空白，确定。你的电脑里面所有.ppt文件就没有了任何关联和默认程序，再按自己的想法重新关联或默认就行了。
还有，要看你修改的是什么程序的默认打开方式，不一样的程序填写的数据不一样。
如果用这个办法还不行就应该不单是这个软件的问题了，可能是系统其他设置的问题。 

4.控制面板设置
控制面板-----程序----默认程序----始终使用指定的程序打开此文件类型

```

#### 1.1.3、notepadd++小知识

```java
无扩展名文件默认打开程序设置Notepad++

在命令提示符cmd以管理员身份下输入
assoc .="No Extension"
ftype "No Extension"="D:\ProgramFiles\Notepadd++\Notepad++\notepad++.exe" "%1"

```

```
notepad++常用插件：Compare、JSTool、Json Viewer、MIME Tools
将dll文件放在如下目录，重启notepad即可
D:\ProgramFiles\Notepadd++\Notepad++\plugins

```

```
notepadd++  txt,md格式 文字高亮
设置 - 语言格式设置 - 自定义扩展名

```

```
notepad竖向选择
先把鼠标光标放在起始位置，然后同时按Alt+shift键或者只按Alt键就可以，然后移动鼠标选取内容。

```

#### 1.1.4、markdown

```java
markdown在线编辑
作业部落  https://www.zybuluo.com/mdeditor
马克飞象  https://maxiang.io/
菜鸟工具  http://c.runoob.com/front-end/712
Marked    https://marked.js.org/demo/
gitlab    https://gitlab.com/-/ide/project/gitlab-org/gitlab/edit/master/-/doc/user/markdown.md


markdown软件
typora	https://www.typora.io/
Dillinger https://dillinger.io/
https://blog.csdn.net/davidhzq/article/details/100815332

```

```
将markdown文件导出为带图片的PDF 
Ⅰ.使用Typora 打开 markdown文件
Ⅱ.点击 文件-导出-HTML
Ⅲ.浏览器打开文件-打印-另存为PDF


markdown转PDF文件分页
<div STYLE="page-break-after: always;"></div>


markdown空格符号
&ensp;
&emsp;

```


```
gitlab、github、gitee布署mkdocs主题仓库
https://gitlab.com/xuyq123/mynotes/-/blob/master/gitlab%E3%80%81github%E3%80%81gitee%E5%B8%83%E7%BD%B2mkdocs%E4%B8%BB%E9%A2%98%E4%BB%93%E5%BA%93.md

gitlab mkdocs主题仓库   
	https://gitlab.com/xuyq123/plain-mkdocs   
	https://xuyq123.gitlab.io/plain-mkdocs 

	https://gitlab.com/xuyq123/myblog-mkdocs   
	https://xuyq123.gitlab.io/myblog-mkdocs	
	
---	
	
git平台docsify布署markdown文件
https://gitlab.com/xuyq123/mynotes/-/blob/master/git%E5%B9%B3%E5%8F%B0docsify%E5%B8%83%E7%BD%B2markdown%E6%96%87%E4%BB%B6.md 

gitlab docsify主题仓库 	
	https://gitlab.com/xuyq123/plain-docsify 
	https://xuyq123.gitlab.io/plain-docsify/
	
	https://gitlab.com/xuyq123/myblog-mkdocs
	https://xuyq123.gitlab.io/myblog-docsify/	
	
```



### 1.2、网络&笔记本

#### 1.2.1、远程登录

```
远程登录
win+R   输入 mstsc
Administrator/123456

远程登录复制文件
本地资源 - 本地设备和资源 - 详细信息 - 驱动器

```

#### 1.2.2、红米笔记本问题
```

红米笔记本触摸板失灵
- 按F12 


redmibook的FN键没有作用,F1～F12功能键直接按就是调节音.
- Fn+ESC锁定

```


#### 1.2.3、无线网连不上 & 代理连接失败

```

win7无线网络连接不上（未连接-连接可用）常用解决方法。
  开始 --控制面板（查看方式-小图标）--管理工具 --服务
  WLAN AutoConfig 启动此服务且启动类型改为自动

```

```
代理连接失败 （-130 ERR_PROXY_CONNECTION_FAILED ）

治本方法：
控制面板 - 网络和Internet - Internet选项 - 连接 - 局域网设置 - 代理服务器 - 为LAN使用代理服务器[这些设置不用于拨号或VPN连接][X]
去掉勾选-确定

---

治根方法：
参考 https://www.zhihu.com/question/25686082?sort=created

方法1：
网上能找到的方法都试过了，没解决。
最后忘了从哪找到的方法，把WinHttpAutoProxySvc 服务给禁用了，似乎是好了，几个小时了没有再出现问题。
忘了说了，我是用完fiddler之后出现的问题。

禁用需要编辑注册表：
Computer\HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\WinHttpAutoProxySvc
右边Start值改为4（禁止启动）。
然后重启电脑。


方法2：
进到这个目录下：HKEY_CURRENT_USER/Software/Microsoft/Windows/CurrentVersion/Internet Settings/Connections
通过目录找到Connections，把整个文件夹删除。
不放心的话可以把Connections改名。

---

进入服务：   控制面板 - 系统和安全 - 管理工具 - 服务
进入注册表： WIN+R - regedit

```

### 1.3、其他

#### 1.3.1、身高体重比例

```

标准体重是反映和衡量一个人健康状况的重要标志之一。过胖和过瘦都不利于健康，也不会给人以健美感。不同体型的大量统计材料表明，反映正常体重较理想和简单的指标，可用身高体重的关系来表示。

一、世卫计算方法
男性：(身高cm－80)×70﹪=标准体重 女性：(身高cm－70)×60﹪=标准体重
标准体重正负10﹪为正常体重
标准体重正负10﹪~ 20﹪为体重过重或过轻
标准体重正负20﹪以上为肥胖或体重不足
超重计算公式
超重%=[（实际体重－理想体重）/（理想体重）]×100%
如：（170-80）* 70%=63kg

二、BMI 法
体重指数 BMI = 体重（公斤） / 身高（米）的平方即 kg/m2
算式写法： BMI = 体重 / （身高）^2
正常体重 ： 体重指数 = 18.5 - 25 （中国体质标准:正常范围 18.5~23.9，超重24.0~27.9，肥胖≥28.0）
超重 ： 体重指数 = 25 - 30
轻度肥胖 ： 体重指数 > 30
中度肥胖 ： 体重指数 > 35
重度肥胖 ：体重指数 > 40
如：67/1.7/1.7=23.18


三、简单方法
标准体重=身高(m)×身高(m)×标准系数（女性20，男性22）
标准体重正负10﹪为正常体重
标准体重正负10﹪~ 20﹪为体重偏重或偏轻
标准体重正负20﹪以上为肥胖或体重不足
如： 1.7*1.7*22=63.58kg

四、简单方法：
标准体重（kg）=身高（cm）-105
例如，一个身高170厘米的男子，他的标准体重应该是：170（厘米）－105=65（公斤）。凡是超过标准体重10%者为偏重，超过20%以上者为肥胖；低于标准体重10%者为偏瘦，低于20%以上者为消瘦。
如： 170-105=65kg
 
注意：上述计算方法只适用于成年人。 对儿童，老年人，或者身高过于矮小的人士并不适用。


```

#### 1.3.2、眼睛心理学

`如果对方眼球处于右上方，表示正在创建视觉想象，也就是在脑海中创建一些现实中没有的事物。`

```

眼睛心理学
眼睛往左看代表：回忆；往右看代表：动用情感来创造词汇。

关于眼睛的心理学：

1. 如果对方眼球处于左上方，表示正在进行视觉回想，也就是回忆。
2. 如果对方眼球处于左下方，表示正在思考，正在与自己对话。

3. 如果对方眼球处于右上方，表示正在创建视觉想象，也就是在脑海中创建一些现实中没有的事物。
4. 如果对方眼球处于右下方，表示ta正在感受自己的身体，感受情感的触动。

5. 如果对方眼球向一侧看，同时脑袋微微向一侧倾斜，对方看上去像是在认真聆听，这个举动与声音有关，可能是在交谈中回忆起了某个声音，这时眼球也会处于中间位置。
6. 如果对方眼球向左右平视，表示ta正在试图弄懂别人的意思。
7. 如果对方眼球迅速地左右运动，表示ta正在忙碌地思考，也有可能是感受到了压力或者心怀戒备。

```


#### 1.3.3、视频会员兑换渠道

```

视频会员：腾讯视频、优酷、爱奇艺、芒果TV等视频网站会员积分兑换

微信支付-支付有优惠-兑换好礼
安徽掌上10000APP兑换币 、 积分
安徽电信公众号-个人中心-金币兑换
中国联通APP积分
电信营业厅 APP积分
微众银行APP积分
支付宝会员积分
云闪付APP签到金

```

 <h2 id="work"></h2>

## 2、工作

*************************


###  2.1、高德天气api

- 高德天气 &ensp; [api]( https://lbs.amap.com/api/webservice/guide/api/weatherinfo ) &ensp;  
- 杭州天气 &ensp; [实时]( https://restapi.amap.com/v3/weather/weatherInfo?key=0481b33d14e9830d8903940cdab327e5&city=330100 ) &ensp; [预报]( https://restapi.amap.com/v3/weather/weatherInfo?key=0481b33d14e9830d8903940cdab327e5&city=330100&extensions=all ) &ensp;  
- 安徽无为天气 &ensp; [实时](https://restapi.amap.com/v3/weather/weatherInfo?key=0481b33d14e9830d8903940cdab327e5&city=340281) &ensp; [预报](https://restapi.amap.com/v3/weather/weatherInfo?key=0481b33d14e9830d8903940cdab327e5&city=340281&extensions=all ) &ensp;  

*************************

### 2.2、mysql logbin日志 

```sql
mysql开启log-bin日志.md  https://gitlab.com/xuyq123/mynotes/-/blob/master/mysql%E5%BC%80%E5%90%AFlog-bin%E6%97%A5%E5%BF%97.md

一 、logbin日志 记录新增、更新、删除的sql 
show binary logs;
show master status;
show binlog events in 'mysql-bin.000090' from 242985028 limit 0,1000


二、general_log 记录增删改查所有日志 
select * from mysql.general_log where argument like '%select%'   ORDER BY event_time DESC limit 50;

TRUNCATE table mysql.general_log

```

--------------------

### 2.3、端点已使用

```sql
C:\Users\Administrator>netstat -ano|findstr "8080"
  TCP    0.0.0.0:8080           0.0.0.0:0              LISTENING       11492
  TCP    [::]:8080              [::]:0                 LISTENING       11492

C:\Users\Administrator>tasklist|findstr "11492"
javaw.exe                    11492 Console                    1    405,916 K

C:\Users\Administrator>taskkill /f /t /im 11492
成功: 已终止 PID 11492 (属于 PID 2596 子进程)的进程。

```

*************************

### 2.4、druid 安全配置

```vb

为Druid增加权限验证措施，建议参考 https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_StatViewServlet%E9%85%8D%E7%BD%AE
https://blog.csdn.net/my_ha_ha/article/details/86212492

http://localhost:8080/druid/datasource.json
http://localhost:8080/druid/sql.html


#Druid配置信息
    druid:
      #初始化时建立物理连接的个数。
      initial-size: 5
      #最大连接池数量
      max-active: 20
      #获取连接时最大等待时间，单位毫秒
      max-wait: 3000
      #最小连接池数量
      min-idle: 5
      #配置检测可以关闭的空闲连接间隔时间
      time-between-eviction-runs-millis: 60000
        # 配置DruidStatViewServlet
      stat-view-servlet:
          # 登录名
          login-username: hname
          # 登录密码
          login-password: pwd2020

```

*************************

### 2.5、github登录不上解决办法

```
参考 https://blog.csdn.net/ych9527/article/details/114372201

1、查询github域名
搜索DNS查询 http://tool.chinaz.com/dns/?type=1&host=github.com&ip=.   得到IP

2、修改系统hosts文件
在 C:\Windows\System32\drivers\etc\hosts 加上如下文本
13.229.188.59 github.com

```

*************************

 <h2 id="java"></h2>

## 3、java

### 3.1、java常用方法

#### 3.1.1、lambda表达式

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
Map<String, DeliveryDO> deliveryOrderMap = deliveryDOS.stream().collect(Collectors.toMap(DeliveryDO::getDeliveryOrder, (p) -> p));

// list转map-值为对象的字段
 Map<Integer, Integer> goods2ProductParam = logisticsGoodsDAOS.stream().filter(dao -> goodsIdParamList.contains(dao.getGoodsId())).
                    collect(Collectors.toMap(LogisticsGoodsDAO::getGoodsId, LogisticsGoodsDAO::getProductId, (key1, key2) -> key2));



Optional.ofNullable(type).orElse(0).intValue();

public static final int cpuNum = Runtime.getRuntime().availableProcessors();


```

#### 3.1.2、Map遍历

```java

java中Map遍历的四种方式
https://www.cnblogs.com/damoblog/p/9124937.html

Map <String,String>map = new HashMap<String,String>();
map.put("熊大", "棕色");
map.put("熊二", "黄色");


for(Map.Entry<String, String> entry : map.entrySet()){
    String mapKey = entry.getKey();
    String mapValue = entry.getValue();
    System.out.println(mapKey+":"+mapValue);
}


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

#### 3.1.3、java排序

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


```

#### 3.1.4、flatmap,peek,newArrayList

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
几个快速添加list的方法
1. 使用Collections.addAll()方法，前提还是需要手动 new ArrayList
ArrayList<String> s = new ArrayList();
Collections.addAll(s,"1","2","3")

2. 使用Arrays.asList(...args) 直接返回一个List
List<String> s = Arrays.asList("1","2","3")

3. 如果引入了Guava的工具包，可以使用他的Lists.newArrayList(...args)方法
List<String> list = Lists.newArrayList("1","2","3")

4. 如果是Java9，可以使用自带的List类
List<String> s = List.of("1","2","3")

```

---

### 3.2、idea基本配置与快捷键

#### 3.2.1、idea配置与插件


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
 * @author xu
 * @date ${DATE} ${TIME}
 */

6、代码自动导入包
Settings→Editor→General→Auto Import
选中Optimize imports on the fly和Add unambiguous imports on the fly

7、idea常用插件 Plugins
lombok
Free Mybatis plugin    MybatisX
Spring Assistant 

Alibaba Java Coding Guidelines
Markdown
swagger


8、代码自动定位文件
Project - Show Options Menu - Autoscroll From Source

9、显示成员变量及方法
Project - Show Options Menu - Show Members


10、初始化idea（删除所有配置及历史记录）
删除目录 C:\Users\Administrator\.IdeaIC2019.1

```

#### 3.2.2、idea常用快捷键

```
eclipse & idea常用快捷键

sout            打印
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

### 3.3、java软件

#### 3.3.1、java软件

| 软件                              | 下载地址   |
| --------                          | -----      |
| idea     | [idea官方下载]( https://www.jetbrains.com/idea/download/other.html ) &ensp; [idea2019]( https://www.aliyundrive.com/s/oWgxBBNqGj9 )     |
| jdk      | [Java Development Kit]( ./jdk.md )  |
| maven    | [maven官方下载]( https://archive.apache.org/dist/maven/maven-3/ )            |
| mysql    | [mysql_5.7]( https://www.aliyundrive.com/s/pymjQca3DbY )                     |
| javaSoft | [java软件]( https://www.aliyundrive.com/s/fWXemUwcsUs )  redis/mongo/Navicat/kafka/zookeeper/git/Xshell...                              |
| tomcat   | [tomcat官网]( https://archive.apache.org/dist/tomcat/ ) &ensp; ([云盘]( https://pan.baidu.com/s/1yPhAfIcACTGkpIOYlEds1g )  密码: j9ug ) |
| eclipse  | [eclipse官方下载]( http://www.eclipse.org/downloads/packages )    [版本说明]( ./eclipse.md ) |

#### 3.3.2、java maven环境变量

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


#### 3.3.3、postman配置

```

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

### 3.4、springboot项目打包布署

```
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

<h2 id="gitId"></h2>

## 4、git仓库


### 4.1、书法练习轨迹

> 若无意外，一般每周一在gitlab《**书法练习轨迹--明月几时有**》记录上周练习情况。其他渠道，不定时同步。

| 序号 | 仓库                                                			      |  备注             			                 |
| ---  | -------------------------------------------------------------        |  -----------------------------------         |
| 1    | [**gitlab**]( https://gitlab.com/xuyq123/calligraphy ) &ensp; [imgs]( https://gitlab.com/xuyq123/imgs )                 		|  国外网站，网速较慢。                            |
| 2    | [csdn_code]( https://codechina.csdn.net/xu180/calligraphy ) &ensp; [imgs]( https://codechina.csdn.net/xu180/imgs )  			|  备份，国内网站，速度快。用户较少。        	   |
| 3    | [github]( https://github.com/scott180/calligraphy ) &ensp; [imgs]( https://github.com/scott180/imgs ) 			  			    |  备份，最流行git仓库。国外网站，但有时打不开。   |
| 4    | [coding]( https://xyqin.coding.net/public/my/calligraphy/git ) &ensp; [imgs]( https://xyqin.coding.net/public/my/imgs/git )	|  备份，速度快。但仓库markdown文件不渲染。		   |
| 5    | [gitee]( https://gitee.com/xy180/calligraphy ) &ensp; [imgs]( https://gitee.com/xy180/imgs )                        			|  备份，国内网站，速度快。但可能会被[屏蔽]。      |
| 6    | [bitbucket]( https://bitbucket.org/xu12345/calligraphy ) &ensp; [imgs]( https://bitbucket.org/xu12345/imgs )                   |  备份，国外网站。markdown渲染不太完善。          |
| -    | **社区**                        |                                    |
| 7    | [csdn博客]( https://blog.csdn.net/xu180/article/details/113602103 ) &ensp; [ReadMe]( https://blog.csdn.net/xu180/article/details/118492424 )  |  程序员技术交流平台，发布文章，有删减。      |
| 8    | [博客园]( https://www.cnblogs.com/scott123/p/14729493.html ) &ensp; [ReadMe]( https://www.cnblogs.com/scott123/p/14972979.html )              |  开发者知识分享社区。                        |
| 9    | [语雀]( https://www.yuque.com/longguang123/ccgbto/cbq9u0 ) &ensp; [ReadMe]( https://www.yuque.com/longguang123/ccgbto/oby4hq )                |  文档与知识管理工具，无删减。阿里巴巴产品。  |
| 10   | [飞书]( https://nal4j8dwi0.feishu.cn/docs/doccntwAAd1yjADzHGQT0ueBkN7 ) &ensp; [ReadMe]( https://nal4j8dwi0.feishu.cn/docs/doccnpf5pWihfu3aW4psk5vO7ue )           | 办公平台。字节跳动产品。|
| -    | **云盘**                        |                                    |
| 11   | [坚果云]( https://www.jianguoyun.com/p/DTnLeQEQxP-NBhjNrfED ) &ensp; [markdown]( https://www.jianguoyun.com/p/DfYHsfUQxP-NBhjOrfED )          |  文件分享。        |
| 12   | [百度网盘]( https://pan.baidu.com/s/1dOJMgeZAyCYolEflsKIOPQ )        | 提取码: zpxu 。pdf文件分享，需要登录。       |
| 13   | [阿里云盘]( https://www.aliyundrive.com/s/dKE1SMhqdwn )              | pdf文件分享，需要登录。                      |
| 14   | [天翼云]( https://cloud.189.cn/t/RRBbumb2MB7b )                      | pdf文件分享，需要登录。中国电信网盘。        |
| 15   | [和彩云]( https://caiyun.139.com/m/i?125CmrCy7hU1y )                 | 提取码:WAmq 。pdf文件分享，需要登录。中国移动网盘。     |
| 16   | [wps云盘]( https://www.kdocs.cn/l/cpUDGjX6765H )                     | pdf文件分享，需要登录。                      |
| 17   | [微云]( https://share.weiyun.com/JKZ4ANJ5 )  &ensp; [腾讯文档]( https://docs.qq.com/pdf/DVmxKTG5YZHZBUGlx )         | pdf文件分享。                                |
| 18   | [有道云]( http://note.youdao.com/s/V7b1jHjB )                        | 笔记分享，无删减。               	         |
| 19   | [google云盘]( https://drive.google.com/file/d/1Ubx-Rz3Xwhn48PEXMx-BmWrJGyIAzNfn/view?usp=sharing )                  | 文件分享，无删减。                           |
| -    | **网页**                        |                                    |
| 20   | [作业部落]( https://www.zybuluo.com/scott180/note/1793757 ) &ensp; [ReadMe]( https://www.zybuluo.com/scott180/note/892814 )    | markdown编辑器，文件分享。        |
| 21   | [gitee_pages]( http://xy180.gitee.io/imgs/calligraphy/%E4%B9%A6%E6%B3%95%E7%BB%83%E4%B9%A0%E8%BD%A8%E8%BF%B9--%E6%98%8E%E6%9C%88%E5%87%A0%E6%97%B6%E6%9C%89.html ) &ensp; [ReadMe]( http://xy180.gitee.io/imgs/calligraphy/%E4%B9%A6%E6%B3%95%E7%BB%83%E4%B9%A0%E8%BD%A8%E8%BF%B9ReadMe.html ) | gitee静态网页，markdown转html。     |
| 22   | [github_pages]( https://scott180.github.io/calligraphy/%E4%B9%A6%E6%B3%95%E7%BB%83%E4%B9%A0%E8%BD%A8%E8%BF%B9--%E6%98%8E%E6%9C%88%E5%87%A0%E6%97%B6%E6%9C%89 ) &ensp; [ReadMe]( https://scott180.github.io/calligraphy ) | github静态网页，有时打不开。[主题1]( https://scott180.github.io/calligraphy1/%E4%B9%A6%E6%B3%95%E7%BB%83%E4%B9%A0%E8%BD%A8%E8%BF%B9--%E6%98%8E%E6%9C%88%E5%87%A0%E6%97%B6%E6%9C%89 ) &ensp; [主题2]( https://scott180.github.io/calligraphy2/%E4%B9%A6%E6%B3%95%E7%BB%83%E4%B9%A0%E8%BD%A8%E8%BF%B9--%E6%98%8E%E6%9C%88%E5%87%A0%E6%97%B6%E6%9C%89 )  |
| 23   | [**gitlab_pages**]( https://xuyq123.gitlab.io/plain/%E4%B9%A6%E6%B3%95%E7%BB%83%E4%B9%A0%E8%BD%A8%E8%BF%B9--%E6%98%8E%E6%9C%88%E5%87%A0%E6%97%B6%E6%9C%89.html )  &ensp; [ReadMe]( https://xuyq123.gitlab.io/plain/%E4%B9%A6%E6%B3%95%E7%BB%83%E4%B9%A0%E8%BD%A8%E8%BF%B9ReadMe.html )     | gitlab静态网页，markdown转html。       |



### 4.2、笔记&项目

```
个人资料一般保存在git平台及网盘，如下：
git平台：gitlab、github、gitee、csdn_code、coding、bitbucket
网盘：阿里云网盘、百度网盘、阿里云个人邮箱网盘、坚果云、天翼云、微云、wps云盘
笔记：csdn、博客园、作业部落、有道云笔记、qq邮箱记事本、语雀、腾讯文档、飞书、简书

```

> 笔记 <br/>
> [note]( https://gitlab.com/xuyq123/mynotes/-/blob/master/note.md ) &ensp; [linuxNote]( https://gitlab.com/xuyq123/mynotes/-/blob/master/linuxNote-x.md ) &ensp; [gitNote]( https://gitlab.com/xuyq123/mynotes/-/blob/master/gitNote.md ) &ensp; [java]( https://gitlab.com/xuyq123/mynotes/-/blob/master/java.md )

<br/>

> 项目 <br/>

| 名称       | 仓库                                                			        |  备注            |
| ---------  | -------------------------------------------------------------        |  ----------      |
| MyNotes    | [gitlab]( https://gitlab.com/xuyq123/mynotes ) &ensp; [gitee]( https://gitee.com/xy180/MyNotes ) &ensp; [github]( https://github.com/scott180/MyNotes ) &ensp; [csdn_code]( https://codechina.csdn.net/xu180/MyNotes )  &ensp; [coding]( https://xyqin.coding.net/public/my/MyNotes/git/files ) &ensp; [bitbucket]( https://bitbucket.org/xu12345/mynotes ) |  工作笔记  |
| java-book  | [gitlab]( https://gitlab.com/xuyq123/java-book ) &ensp; [gitee]( https://gitee.com/xy180/java-book ) &ensp; [github]( https://github.com/scott180/java-book ) &ensp; [csdn_code]( https://codechina.csdn.net/xu180/java-book )  &ensp; [coding]( https://xyqin.coding.net/public/my/java-book/git/files )   		|   java书籍 |
| calligraphy-boot    | [gitlab]( https://gitlab.com/xuyq123/calligraphy-boot ) &ensp; [gitee]( https://gitee.com/xy180/calligraphy-boot ) &ensp; [github]( https://github.com/scott180/calligraphy-boot ) &ensp; [csdn_code]( https://codechina.csdn.net/xu180/calligraphy-boot )    |   java项目 |
| calligraphy    | [gitlab]( https://gitlab.com/xuyq123/calligraphy ) &ensp; [gitee]( https://gitee.com/xy180/calligraphy ) &ensp; [github]( https://github.com/scott180/calligraphy ) &ensp; [gitlab_pages]( https://xuyq123.gitlab.io/plain/%E4%B9%A6%E6%B3%95%E7%BB%83%E4%B9%A0%E8%BD%A8%E8%BF%B9--%E6%98%8E%E6%9C%88%E5%87%A0%E6%97%B6%E6%9C%89.html ) &ensp; [gitlab_mkdocs]( https://xuyq123.gitlab.io/plain-mkdocs/ )    |   书法练习轨迹 |
| mkdocs   | gitlab [note-mkdocs]( https://gitlab.com/xuyq123/note-mkdocs )&ensp; [**网页**](  https://xuyq123.gitlab.io/note-mkdocs/ ) &ensp;&ensp; [myblog-mkdocs]( https://gitlab.com/xuyq123/myblog-mkdocs )&ensp; [网页](  https://xuyq123.gitlab.io/myblog-mkdocs/ ) <br/>github [note-mkdocs]( https://github.com/scott180/note-mkdocs )&ensp; [网页](  https://scott180.github.io/note-mkdocs/ ) &ensp;&ensp;[myblog-mkdocs]( https://github.com/scott180/myblog-mkdocs )&ensp; [网页](  https://scott180.github.io/myblog-mkdocs/ )<br/>gitee [plain-mkdocs]( https://gitee.com/xy180/plain-mkdocs/tree/master/note ) [网页](  http://xy180.gitee.io/plain-mkdocs/note/site/ ) &ensp;&ensp;[myblog-mkdocs]( https://gitee.com/xy180/myblog-mkdocs )      |  我的笔记与博客                        |
| docsify   | gitlab [plain-docsify]( https://gitlab.com/xuyq123/plain-docsify )&ensp; [**网页**](  https://xuyq123.gitlab.io/plain-docsify/ ) &ensp;&ensp; [myblog-docsify]( https://gitlab.com/xuyq123/myblog-docsify )&ensp; [网页](  https://xuyq123.gitlab.io/myblog-docsify/ ) <br/>github [plain-docsify]( https://github.com/scott180/plain-docsify )&ensp; [网页](  https://scott180.github.io/plain-docsify/ ) &ensp;&ensp;[myblog-docsify]( https://github.com/scott180/myblog-docsify )&ensp; [网页](  https://scott180.github.io/myblog-docsify/ )      |  笔记与博客docsify                        |

---

---

> 注册了微信公众号：**无为徐生**，以后会将书法练习轨迹、程序员笔记及一些随笔感想更新在此处。若有兴趣，可扫码关注。

![wuweixusheng]( https://codechina.csdn.net/xu180/document/-/raw/master/imgs/weixin/wuweixusheng.png )




