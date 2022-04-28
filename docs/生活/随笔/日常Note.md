# 日常Note

<br />

- [ ] `仓库` &ensp;  [blog]( https://blog.xushufa.cn ) &ensp;  [gitlab]( https://gitlab.com/xuyq123/mynotes ) &ensp; [gitee]( https://gitee.com/xy180/MyNotes ) &ensp; [github]( https://github.com/scott180/MyNotes )

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


###  2.1、善事利器

```
工欲善其事必先利其器

git ：gitlab、github、gitee、gitcode、coding、bitbucket
网盘：阿里云网盘、百度网盘、阿里云个人邮箱网盘、坚果云、天翼云、微云、wps云盘
社区：SegmentFault 思否、csdn、stackoverflow、博客园、稀土掘金、知乎、豆瓣、简书 
笔记：有道云笔记、作业部落、金山文档、语雀、腾讯文档、飞书、qq邮箱记事本、notion、flomo
搜索：谷歌、百度、头条、必应
工具：idea、notepadd++、navicat、postman、fiddler、typora、eclipse、vscode

``` 

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

---

<h2 id="myShu"></h2>

## 3、我的

> **平台**

- 若有志同道合的小伙伴想联系本人，可通过以下方式发邮件或私信。路漫漫其修远兮，吾将上下而求索。共勉。

| 平台           | 链接           |
| -------------- | -------------- |
|  **项目仓库**  | [gitlab]( https://gitlab.com/xuyq123/calligraphy ) &ensp; [coding]( https://xyqin.coding.net/public/my/calligraphy/git ) &ensp; [github]( https://github.com/scott180/calligraphy )  &ensp; [bitbucket]( https://bitbucket.org/xu12345/calligraphy ) &ensp; [gitee]( https://gitee.com/xy180/calligraphy ) &ensp; [sourceforge]( https://sourceforge.net/p/calligraphy/code )  &ensp; [github_docsify]( https://scott180.github.io/docsify-calligraphy )    |
|  **资讯账号**  | [微信公众号]( https://mp.weixin.qq.com/s/HmdDsCaeumuZg_DfitIdlw ) &ensp; [头条]( https://www.toutiao.com/c/user/token/MS4wLjABAAAA2_bWhiknCbcKNu4c6VTM2B7m2vr7zBrh0x6fSyOrtGU ) &ensp;  [豆瓣]( https://www.douban.com/people/80730595/photos ) &ensp;  [知乎]( https://www.zhihu.com/people/xu-xian-sheng-72-29/posts )     |
|  **个人邮箱**  | 1021151991@qq.com   |

***

> **公众号**

- 注册了微信公众号及今日头条号：[**无为徐生**]( https://scott180.github.io/calligraphy/%E6%97%A0%E4%B8%BA%E5%BE%90%E7%94%9F )，以后会将书法练习轨迹、程序员笔记以及一些随笔感想更新在此。<br/>
- 每周一会在无为徐生**微信公众号**同步《书法练习轨迹》，持续更新，敬请关注。

| 无为徐生   | 微信公众号                                               	 |  &ensp; |  今日头条号        |
| ---------  | ------------------------------------------------------------- |  -      |  ----------        |
|  二维码    | ![w]( https://xyqin.coding.net/p/my/d/imgs/git/raw/master/other/wuweixusheng_weixin.png ) | <br/> | ![t]( https://xyqin.coding.net/p/my/d/imgs/git/raw/master/other/wuweixusheng_toutiao.png )     |

***

> **我的网站**

- 徐书法 `xushufa.cn` 个人书法练习网站

| 徐书法 | 我的网站    |  备注          |
| -----  | ----------- |  ------------- |
| 1      | [xushufa]( https://xushufa.cn ) &ensp; [blog]( https://blog.xushufa.cn ) | 《书法练习轨迹》及博客网站。      |
| 2      | [mkdocs-calligraphy]( https://mkdocs-calligraphy.xushufa.cn ) &ensp; [mkdocs-blog]( https://mkdocs-blog.xushufa.cn )         | `mkdocs`构建的网站。  |
| 3      | [reco-calligraphy]( https://reco-calligraphy.xushufa.cn ) &ensp; [reco-blog]( https://reco-blog.xushufa.cn ) | `vuepress-theme-reco`构建的网站。     |

***



