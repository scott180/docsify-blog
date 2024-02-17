# note

<br />

[xushufa]( https://xushufa.cn ) &ensp;  [gitlab]( https://gitlab.com/xuyq123/mynotes ) &ensp; [gitee]( https://gitee.com/xy180/MyNotes ) &ensp; [github]( https://github.com/scott180/MyNotes ) &ensp; [blog]( https://vuepress-blog.xushufa.cn )

<br />


## 1、文件操作

### 1.1、windows常用快捷键

```java
windows常用快捷键

Ctrl+C  复制
Ctrl+V  粘贴
Ctrl+S  保存
Ctrl+A  选中全部内容
Ctrl+X  剪切
Ctrl+F  查找与替换
        
Ctrl+W  关闭程序
Ctrl+Z  撤销刚才进行的操作
Ctrl+Y  恢复刚才进行的操作
Ctrl+N  新建一个空白文档
Ctrl+P  打开打印
Ctrl+O  打开文档

Ctrl+Shift	输入法切换
Ctrl+Home	光标快速移到文件头
Ctrl+End	光标快速移到文件尾

Ctrl+Shift+End	选中当前到末尾所有内容
Ctrl+Shift+Esc	打开任务管理器
Ctrl+Shift+Del	浏览器打开清除缓存对话框

Alt+Tab     程序切换-小图标
Win+Tab     程序切换-大图标

Win     显示开始菜单
Win+L   锁定计算机
Win+E   启动"我的电脑"
Win+D   快速显示桌面

Win+R		打开运行-输入命令开启相关功能
cmd			命令行控制台
reg			注册表
mstsc			远程登录
services.msc		本地服务
	
F2   文件改名
F5   浏览器页面刷新
F11  浏览器进入全屏状态
F12  浏览器打开调试

Shift+PgUp	向上选中内容
Shift+PgDn	向下选中内容

Shift+光标+右键	 选中内容

```

<br/>

### 1.2、修改文件的默认打开方式

修改文件的默认打开方式： <br/>

**1.修改属性** <br/>
右击--属性--常规--打开方式--选择默认程序

**2.选择默认方式** <br/>
右击--打开方式--选择默认程序

**3.注册表设置** <br/>
Win+R 在运行里面输入regedit（注册表编辑器），`HEY_CLASS_ROOT`下面是所有文件的后缀名文件，找到你想取消关联的后缀名，如.ppt。 <br/>
单击这个文件夹，右边出现的“数据”一项，双击“（默认）”，打开对话框，将“数据”下面可以填写的部分清除，保持空白，确定。 <br/>

你的电脑里面所有.ppt文件就没有了任何关联和默认程序，再按自己的想法重新关联或默认就行了。 <br/>
还有，要看你修改的是什么程序的默认打开方式，不一样的程序填写的数据不一样。 <br/>
如果用这个办法还不行就应该不单是这个软件的问题了，可能是系统其他设置的问题。

**4.控制面板设置** <br/>
控制面板-----程序----默认程序----始终使用指定的程序打开此文件类型 <br/>

<br/>

### 1.3、notepadd++小知识

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

<br/>

### 1.4、markdown

```
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

```java
markdown表格宽度设置

| git仓库 | 布署方法 | <span style="white-space:nowrap;">备注&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</span> |

```

```
gitlab、github、gitee布署mkdocs主题仓库
https://gitlab.com/xuyq123/mynotes

gitlab mkdocs主题仓库   
	https://gitlab.com/xuyq123/plain-mkdocs   
	https://xuyq123.gitlab.io/plain-mkdocs 

	https://gitlab.com/xuyq123/myblog-mkdocs   
	https://xuyq123.gitlab.io/myblog-mkdocs	
	
---	
	
git平台docsify布署markdown文件
https://github.com/scott180/MyNotes

gitlab docsify主题仓库 	
	https://gitlab.com/xuyq123/plain-docsify 
	https://xuyq123.gitlab.io/plain-docsify/
	
	https://gitlab.com/xuyq123/myblog-mkdocs
	https://xuyq123.gitlab.io/myblog-docsify/	
	
```

<br/>

### 1.5、部署网站


| 序号  | 平台    | 仓库地址    |  git静态页面   | 域名  | 说明 |
| ----- | -----   | ----------- |  ------------- | ----  | ---- |
| 1     | github  | [**reco-blog**]( https://github.com/scott180/reco-blog )  	      | [github.io/reco-blog]( https://scott180.github.io/reco-blog )         | [xushufa]( https://xushufa.cn) |  vuepress-reco部署网站    |
| 2     | github  | [**vuepress-blog**]( https://github.com/scott180/vuepress-blog )  | [github.io/vuepress-blog]( https://scott180.github.io/vuepress-blog ) | [vuepress-blog.xushufa]( https://vuepress-blog.xushufa.cn) | vuepress部署网站 |
| 3     | github  | [blog]( https://github.com/scott180/blog )  	 | [github.io/blog]( https://scott180.github.io/blog )   | [blog.xushufa]( https://blog.xushufa.cn) | vuepress部署网站     |
| 4     | github  | [tool]( https://github.com/scott180/tool )  	 | [github.io/tool]( https://scott180.github.io/tool )   | [tool.xushufa]( https://tool.xushufa.cn) | 工具网站             |
| 5     | github  | [docsify-blog]( https://github.com/scott180/docsify-blog )  	  | [github.io/docsify]( https://scott180.github.io/docsify-blog/ )   | [docsify-blog.xushufa]( https://docsify-blog.xushufa.cn)       | docsify部署网站  |
| 6     | **github**  | [gitbook-blog]( https://github.com/scott180/gitbook-blog )    | [github.io/gitbook]( https://scott180.github.io/gitbook-blog )    | -- | gitbook部署网站 |
| 7     | gitlab      | [mkdocs-blog]( https://gitlab.com/xuyq123/mkdocs-blog ) | [gitlab.io/mkdocs]( https://xuyq123.gitlab.io/mkdocs-blog )             | -- | mkdocs部署网站  |
| 8     | gitlab      | [**MyNotes**]( https://gitlab.com/xuyq123/mynotes )     | [files]( https://gitlab.com/xuyq123/mynotes/-/blob/master/%E6%96%87%E6%A1%A3%E7%9B%AE%E5%BD%95.md )              | -- | **编程笔记** |
| 9     | gitcode     | [**document**]( https://gitcode.net/xu180/document )  	| [note]( https://gitcode.net/xu180/document/-/blob/master/article/%E9%9A%8F%E7%AC%94/%E6%97%A5%E5%B8%B8Note.md )  | -- | 生活随笔     |
| 10    | npm         | [xushufa]( https://www.npmjs.com/~xushufa )             | [img]( https://cdn.jsdelivr.net/npm/xushufa-shu/yan/duo1-1000/0001大.jpg ) 	                                   | -- | 资料管理     |
| 11    | bitbucket   | [**imgs**]( https://bitbucket.org/xu12345/document/src/master/imgs/ )    | [img]( https://bitbucket.org/xu12345/document/raw/87e65566e45e063244eab491abe2dab0f43de59d/imgs/shufa/%E7%8E%8B%E7%BE%B2%E4%B9%8B-%E5%85%B0%E4%BA%AD%E9%9B%86%E5%BA%8F.jpg )  | --   | 图片仓库   |
| 12    | sourceforge | [xdocument]( https://sourceforge.net/p/xdocument/code/ci/master/tree/ )  | [readMe]( https://sourceforge.net/p/xdocument/code/ci/master/tree/README.md ) 	               | -- | 图片仓库     |



<br/>

## 2、网络&笔记本

### 2.1、远程登录

```js
远程登录
win+R   输入 mstsc
Administrator/123456

远程登录复制文件
本地资源 - 本地设备和资源 - 详细信息 - 驱动器

```

### 2.2、红米笔记本问题

```
红米笔记本触摸板失灵
- 按F12 


redmibook的FN键没有作用,F1～F12功能键直接按就是调节音.
- Fn+ESC锁定

```

### 2.3、无线网连不上 & 代理连接失败

```
win7无线网络连接不上（未连接-连接可用）常用解决方法。
  开始 --控制面板（查看方式-小图标）--管理工具 --服务
  WLAN AutoConfig 启动此服务且启动类型改为自动

```

代理连接失败 （-130 ERR_PROXY_CONNECTION_FAILED ）

**治本方法**：<br/>
控制面板 - 网络和Internet - Internet选项 - 连接 - 局域网设置 - 代理服务器 - 为LAN使用代理服务器[这些设置不用于拨号或VPN连接][X] <br/>
去掉勾选-确定

<br/>

**治根方法**：<br/>
参考 https://www.zhihu.com/question/25686082?sort=created

**方法1**：<br/>
网上能找到的方法都试过了，没解决。<br/>
最后忘了从哪找到的方法，把WinHttpAutoProxySvc 服务给禁用了，似乎是好了，几个小时了没有再出现问题。<br/>
忘了说了，我是用完fiddler之后出现的问题。

禁用需要编辑注册表：<br/>
`Computer\HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\WinHttpAutoProxySvc`<br/>
右边Start值改为4（禁止启动）。<br/>
然后重启电脑。


**方法2**：<br/>
进到这个目录下：`HKEY_CURRENT_USER/Software/Microsoft/Windows/CurrentVersion/Internet Settings/Connections`<br/>
通过目录找到Connections，把整个文件夹删除。<br/>
不放心的话可以把Connections改名。


进入服务：   控制面板 - 系统和安全 - 管理工具 - 服务<br/>
进入注册表： WIN+R - regedit

---

### 2.4、关闭Chrome浏览器更新通知

如何关闭Chrome 浏览器提示“若要接收后续 google chrome 更新,您需使用 windows 10 或更高版本”

```
方法一：新建注册表文件
原文链接：https://www.ahaoyw.com/article/827.html

1、首先在桌面或者其他文件夹内新建一个记事本文件

2、打开新建的记事本文件，复制下面这段代码，粘贴其中，保存关闭

Windows Registry Editor Version 5.00

[HKEY_CURRENT_USER\Software\Policies\Google\Chrome]
"SuppressUnsupportedOSWarning"=dword:00000001

3、将记事本的名称改为 .reg 后缀（注意连 .txt 的后缀也得删除一并改掉），
例如closeChromeUpdate.reg （名字可以自定义但是后缀一定要.reg）然后双击运行该文件，点击是

4、系统弹出包含的项和值已经成功添加到注册表中，点击确定完成

5、最后重启Chrome浏览器

```

```
方法二：编辑注册表
原文链接：https://blog.csdn.net/haobiliuxiang/article/details/128232476

1. 打开“开始”，输入 regedit.exe，选择注册表编辑器。

2. 转到 HKEY_CURRENT_USER / Software / Policies / Google / Chrome。
如果其中一个键不存在，在前面的键上点击右键，选择新建 > 键。给它起个相应的名字。

3. 右键点击 Chrome，选择新建 > Dword（32 位）值。

4. 将其命名为 SuppressUnsupportedOSWarning。

5. 双击该名称并将其值设为 1。

6. 重新启动浏览器。

```


### 2.5、shell批量推送git

```sh
#!/bin/bash
#sh push-ca.sh
copyPush(){
	cp -r /e/Project/gitlab/calligraphy/*.md $data_dir
	cp -r /e/Project/gitlab/calligraphy/书法字帖 $data_dir
	cd $data_dir
	echo $PWD 'begin...'
	git add .
	git commit -m "fix"
	git push
	echo '.........end.........'
}

# *// calligraphy git 

#agit 1
data_dir=/e/Project/gitlab/agit/calligraphy
copyPush

#bitbucket 2
data_dir=/e/Project/gitlab/bitbucket/calligraphy
copyPush

#codeberg 3
data_dir=/e/Project/gitlab/codeberg/calligraphy
copyPush

#coding 4
data_dir=/e/Project/gitlab/coding/calligraphy
copyPush

#gitcode 5
data_dir=/e/Project/gitlab/gitcode/calligraphy
copyPush

#gitea 6
data_dir=/e/Project/gitlab/gitea/calligraphy
copyPush

#sourceforge 7  need password

#gitee 8
data_dir=/e/Project/gitee/calligraphy
copyPush

#github 9
data_dir=/e/Project/github/calligraphy
copyPush


```

### 2.6、github访问不了，配置DNS与IP


问题：github网站访问不了，不能推送文件，不能获取项目						<br/>
方法：本机hosts文件配置DNS与IP                                              <br/>
步骤：                                                                      <br/>
1、工具网站DNS查询与IP归属地查询                                            <br/>
https://tool.chinaz.com/dns/www.github.com                                  <br/>
https://tool.lu/ip/index.html                                               <br/>
https://www.itdog.cn/dns/github.com                                         <br/>
                                                                            <br/>
输入github.com查到对应IP：                                                  <br/>
20.27.177.113                                                               <br/>
20.205.243.166                                                              <br/>
20.248.137.48                                                               <br/>
...                                                                         <br/>
 
2、本机hosts文件配置解析                                                    <br/>
在 `C:\Windows\System32\drivers\etc\hosts` 文件添加一行对应解析映射         <br/>
`20.27.177.113 github.com`                                                  <br/>
                                                                            <br/>
注意：**只需添加一行**，这个ip要是访问不了的话要删掉，换另一个。            <br/>
如果配置多行的话，域名可能会访问不能解析的ip，因此只配置一行能解析的ip即可。<br/>




---

## 3、杂学

### 3.1、身高体重比例


标准体重是反映和衡量一个人健康状况的重要标志之一。过胖和过瘦都不利于健康，也不会给人以健美感。不同体型的大量统计材料表明，反映正常体重较理想和简单的指标，可用身高体重的关系来表示。

**一、世卫计算方法** <br/>
男性：(身高cm－80)×70﹪=标准体重 女性：(身高cm－70)×60﹪=标准体重 <br/>
标准体重正负10﹪为正常体重 <br/>
标准体重正负10﹪~ 20﹪为体重过重或过轻 <br/>
标准体重正负20﹪以上为肥胖或体重不足 <br/>
超重计算公式 <br/>
超重%=[（实际体重－理想体重）/（理想体重）]×100% <br/>
如：（170-80）* 70%=63kg

**二、BMI 法** <br/>
体重指数 BMI = 体重（公斤） / 身高（米）的平方即 kg/m2 <br/>
算式写法： BMI = 体重 / （身高）^2 <br/>
正常体重 ： 体重指数 = 18.5 - 25 （中国体质标准:正常范围 18.5~23.9，超重24.0~27.9，肥胖≥28.0） <br/>
超重 ： 体重指数 = 25 - 30 <br/>
轻度肥胖 ： 体重指数 > 30 <br/>
中度肥胖 ： 体重指数 > 35 <br/>
重度肥胖 ：体重指数 > 40 <br/>
如：67/1.7/1.7=23.18


**三、简单方法** <br/>
标准体重=身高(m)×身高(m)×标准系数（女性20，男性22） <br/>
标准体重正负10﹪为正常体重 <br/>
标准体重正负10﹪~ 20﹪为体重偏重或偏轻 <br/>
标准体重正负20﹪以上为肥胖或体重不足 <br/>
如： 1.7 * 1.7 * 22=63.58kg


**四、简单方法**： <br/>
标准体重（kg）=身高（cm）-105 <br/>
例如，一个身高170厘米的男子，他的标准体重应该是：170（厘米）－105=65（公斤）。凡是超过标准体重10%者为偏重，超过20%以上者为肥胖；低于标准体重10%者为偏瘦，低于20%以上者为消瘦。 <br/>
如： 170-105=65kg <br/>
 
注意：上述计算方法只适用于成年人。 对儿童，老年人，或者身高过于矮小的人士并不适用。

<br/>

### 3.2、眼睛心理学

如果对方眼球处于**右上方**，表示正在创建视觉想象，也就是在脑海中创建一些现实中没有的事物。说明在撒谎，在想象。


眼睛心理学。眼睛往左看代表：回忆；往右看代表：动用情感来创造词汇。 <br/>
1、如果对方眼球处于左上方，表示正在进行视觉回想，也就是回忆。 <br/>
2、如果对方眼球处于左下方，表示正在思考，正在与自己对话。 <br/>

3、如果对方眼球处于右上方，表示正在创建视觉想象，也就是在脑海中创建一些现实中没有的事物。 <br/>
4、如果对方眼球处于右下方，表示ta正在感受自己的身体，感受情感的触动。 <br/>

5.、如果对方眼球向一侧看，同时脑袋微微向一侧倾斜，对方看上去像是在认真聆听，这个举动与声音有关，可能是在交谈中回忆起了某个声音，这时眼球也会处于中间位置。 <br/>
6、如果对方眼球向左右平视，表示ta正在试图弄懂别人的意思。 <br/>
7、如果对方眼球迅速地左右运动，表示ta正在忙碌地思考，也有可能是感受到了压力或者心怀戒备。

<br/>

### 3.3、春联上联和下联怎么区分


在以往，我国春节贴的春联，大多是请村子里学问高的先生到家里来写，然后在先生的指导下贴上的。那过年春联上联和下联怎么区分如何贴？上联贴在左边还是右边？

上联下联的区分：

1、按字调平仄分。
对联比较讲究平仄，这是对联的特点。具体来说，上联的最后一个字一般是仄声，下联的最后一个字一般是平声，否则，读起来就会感到非常别扭。 <br/>
2、按左右方位分。
贴对联时应将上联贴在右边，下联贴在左边，左与右则以面对大门或壁柱来分。之所以这样张贴，是因为直行书写都是从右到左，所以念对联也是从右向左念。 <br/>
3、按时序先后分。
就是时间在前的为上联，时间在后的则为下联。如“门迎春夏秋冬福，户纳东西南北祥”。 <br/>

4、按语言习惯分。
比如“福如东海长流水，寿比南山不老松”。 <br/>
5、按因果关系分。
就是“因”为上联，“果”为下联。 <br/>
6、按场面范围分。
在时间、空间、具体事物上一般是从左到右、从大到小，比如“年年过年年年好，月月赏月月月圆”。 <br/>


最简办法，看最后一个字：三或四声为上，一二声为下。位置：面对门右上左下。古人写字即右向左这是沿承下来的。

<br/>

### 3.4、视频会员和电信流量兑换

```js
视频会员：腾讯视频、优酷、爱奇艺、芒果TV等视频网站会员积分兑换

微信支付-支付有优惠-兑换好礼
安徽掌上10000APP兑换币、积分
安徽电信公众号-个人中心-金币兑换
中国联通APP积分
电信营业厅 APP积分
微众银行APP积分
支付宝会员积分
云闪付APP签到金
--2021


招商银行 腾讯超V联名卡 腾讯权益六选一
平安悦享白金卡 百变好礼月月享
--2022


```

---


各位小伙伴，现在主要有以下几个方法正规免费获取流量：

1、安徽电信公众号--福利专区--个人中心--#签到有礼 。每日连续签到，使用金币兑换流量，一个月大概有4G多免费流量。活动到2023年12月31结束。

2、安徽电信公众号--福利专区--个人中心--#夏日送福利 （顶部动态活动）。每日签到，有几率获取流量、翼支付权益金、视频会员等等。活动到2023年8月31结束。

3、安徽电信公众号--福利专区--个人中心-#福利大放送 （顶部动态活动）。每月参与一次抽奖，有几率获取金币、流量、翼支付权益金、视频会员等等。活动到2023年12月31结束。

4、安徽电信掌上10000--福利中心--#玩酷大转盘。消耗兑换币、分享活动等方式获取抽奖机会，有几率获取金币、流量、翼支付权益金、视频会员等等。活动到2023年9月30结束。

--2023.08.01

<br/>

### 3.5、天干地支

十天干指的是：甲、乙、丙、丁、戊、己、庚、辛、壬、癸。                                                 <br/>
十二地支指的是：子、丑、寅、卯、辰、巳、午、未、申、酉、戌、亥。                                       <br/>
十二地支与十二生肖对应:子鼠、丑牛、寅虎、卯兔、辰龙、巳蛇、午马、未羊、申猴、酉鸡、戌狗、亥猪。        <br/>


天干地支时间对照表

1、子时：夜半，又名子夜、中夜，十二时辰的第一个时辰。对应23时至01时。												 <br/>
2、丑时：鸡鸣，又名荒鸡，十二时辰的第二个时辰。对应01时至03时。                                                      <br/>
3、寅时：平旦，又称黎明、早晨、日旦等，是夜与日的交替之际。对应03时至05时。                                          <br/>
4、卯时：日出，又名日始、破晓、旭日等，指太阳刚刚露脸，冉冉初升的那段时间。对应05时至07时。                          <br/>
5、辰时：食时，又名早食等，古人“朝食”之时也就是吃早饭时间，对应07时至09时。                                          <br/>
6、巳时：隅中，又名日禺等，临近中午的时候称为隅中。对应09 时至11时。                                                 <br/>
7、午时：日中，又名日正、中午等，对应11时至13时。                                                                    <br/>
8、未时：日昳，又名日跌、日央等，太阳偏西为日跌。对应13时至15时。                                                    <br/>
9、申时：哺时，又名日铺、夕食等，对应15时至17时。                                                                    <br/>
10、酉时：日入，又名日落、日沉、傍晚，意为太阳落山的时候。对应17时至19时。                                           <br/>
11、戌时：黄昏，又名日夕、日暮、日晚等，此时太阳已经落山，天将黑未黑。天地昏黄，万物朦胧，故称黄昏。对应19时至21时。 <br/>
12、亥时：人定，又名定昏等，代表此时夜色已深，人们也已经停止活动，安歇睡眠了。人定也就是人静。对应21时至23时。       <br/>

<br/>

### 3.6、二十四节气

节气歌  <br/>
春雨惊春清谷天，夏满芒夏暑相连。 <br/>
秋处露秋寒霜降，冬雪雪冬小大寒。 <br/>


| 春季  | 日 期	     |  夏季  | 日　期	     |  秋季 | 日　期	    | 冬季	| 日　期      | 
| ----- | ---------- |  ------| -----------  | ----- | ------------ | ----- | ----------- |
| 立春	| 2月3-5日	 |  立夏  | 5月5-7日	 |  立秋 | 8月7-9日	    | 立冬	| 11月7-8日   | 
| 雨水	| 2月18-20日 | 	小满  | 5月20-22日	 |  处暑 | 8月22-24日   | 小雪	| 11月22-23日 | 
| 惊蛰	| 3月5-7日	 |  芒种  | 6月5-7日	 |  白露 | 9月7-9日	    | 大雪	| 12月6-8日   | 
| 春分	| 3月20-22日 | 	夏至  | 6月21-22日	 |  秋分 | 9月22-24日	| 冬至	| 12月21-23日 | 
| 清明	| 4月4-6日	 |  小暑  | 7月6-8日	 |  寒露 | 10月8-9日	| 小寒	| 1月5-7日    | 
| 谷雨	| 4月19-21日 | 	大暑  | 7月22日-24日 | 	霜降 | 10月23-24日	| 大寒	| 1月20-21日  | 

<br/>

## 4、工作

### 4.1、善事利器

```js
工欲善其事必先利其器

git ：gitlab、github、gitee、gitcode、coding、bitbucket
网盘：阿里云网盘、百度网盘、阿里云个人邮箱网盘、坚果云、天翼云、微云、wps云盘
社区：SegmentFault 思否、csdn、stackoverflow、博客园、稀土掘金、知乎、豆瓣、简书 
笔记：有道云笔记、作业部落、金山文档、语雀、腾讯文档、飞书、qq邮箱记事本、notion、flomo
搜索：谷歌、百度、头条、必应
工具：idea、notepadd++、navicat、postman、fiddler、typora、eclipse、vscode

```


### 4.2、mysql logbin日志 

```
mysql开启log-bin日志.md  https://gitlab.com/xuyq123/mynotes

一 、logbin日志 记录新增、更新、删除的sql 
show binary logs;
show master status;
show binlog events in 'mysql-bin.000090' from 242985028 limit 0,1000


二、general_log 记录增删改查所有日志 
select * from mysql.general_log where argument like '%select%'   ORDER BY event_time DESC limit 50;

TRUNCATE table mysql.general_log

```


### 4.3、端点已使用

```sh
C:\Users\Administrator>netstat -ano|findstr "8080"
  TCP    0.0.0.0:8080           0.0.0.0:0              LISTENING       11492
  TCP    [::]:8080              [::]:0                 LISTENING       11492

C:\Users\Administrator>tasklist|findstr "11492"
javaw.exe                    11492 Console                    1    405,916 K

C:\Users\Administrator>taskkill /f /t /im 11492
成功: 已终止 PID 11492 (属于 PID 2596 子进程)的进程。

```


### 4.4、druid 安全配置

```
为Druid增加权限验证措施，建议参考 
https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_StatViewServlet%E9%85%8D%E7%BD%AE
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

### 4.5、小工具

| 序号   | 地址        |  备注          |
| -----  | ----------- |  ------------- |
| 1      | [json]( https://tool.xushufa.cn/json )                     | json 格式化工具             |
| 2      | [yaml解析]( https://tool.xushufa.cn/yaml-parse )           | yaml解析为json              |
| 3      | [Json2Excel]( https://tool.xushufa.cn/Json2Excel )         | Json转Excel文件             |
| 4      | [时间毫秒值转换]( https://tool.xushufa.cn/time-format )    | 时间毫秒值转换              |
|        | 解码编码                                                   |                             |
| 5      | [url解码]( https://tool.xushufa.cn/url-encode )            | url解码编码                 |
| 6      | [unicode解码]( https://tool.xushufa.cn/unicode-encode )    | unicode解码编码             |
| 7      | [文字转二维码]( https://tool.xushufa.cn/words-QRcode )     | 文字转二维码                |
| 8      | [二维码生成扫描识别]( https://tool.xushufa.cn/HtmlQRCode ) | 二维码生成扫描识别          |
|        | markdown                                                   |                             |
| 9      | [md2wx]( https://tool.xushufa.cn/md2wx/docs )              | 在线Markdown微信格式化工具            |
| 10     | [editormd]( https://tool.xushufa.cn/markdown-editormd )    | 开源的、可嵌入的 Markdown 在线编辑器  |
| 11     | [markthing]( https://tool.xushufa.cn/markdown-markthing )  | 一个MarkDown编辑器                    |
| 12     | [mpeditor]( https://tool.xushufa.cn/markdown-mpeditor )    | 专注于微信公众号的编辑阅读器          |
|        | 项目布署                                                   |                                       |
| 13     | [tool]( https://github.com/scott180/tool )                 | github仓库                            |
| 14     | [xushufa]( https://xushufa.cn )                            | 个人网站                              |


*************************

