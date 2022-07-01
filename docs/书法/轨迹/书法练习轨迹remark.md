# 书法练习轨迹remark

<br />

> [**地址**]( https://xushufa.cn ) &ensp; [gitlab]( https://gitlab.com/xuyq123/calligraphy ) &ensp; [coding]( https://xyqin.coding.net/public/my/calligraphy/git ) &ensp; [github]( https://github.com/scott180/calligraphy )  &ensp; [blog]( https://blog.xushufa.cn ) 


> 书法练习轨迹附录


## 1、markdown转PDF 

```sh
如何将markdown文件导出为带图片的PDF （《书法练习轨迹--明月几时有》是markdown文件）

① markdown转PDF
Ⅰ.使用Typora 打开 markdown文件
Ⅱ.点击 文件-导出-HTML
Ⅲ.浏览器打开文件-打印-另存为PDF


② markdown转PDF文件分页
<div STYLE="page-break-after: always;"></div>

```


```
markdown表格宽度设置

| git仓库 | 布署方法 | <span style="white-space:nowrap;">备注&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</span> |

```

---

## 2、github、gitee 仓库小问题

```java
github、gitee 仓库小问题

Ⅰ.在gitee仓库于2021-01-12 、2021-01-26删除了一些大文件，但是这些文件还在提交记录中，仍然占空间，所以仓库显得比较大（290M）。


Ⅱ.GitHub 图片加载不出来怎么办
打开路径 C:\Windows\System32\drivers\etc 下的 hosts 文件,添加下面的语句

# GitHub Start 
192.30.253.112 Build software better, together 
192.30.253.119 gist.github.com
151.101.184.133 assets-cdn.github.com
151.101.184.133 raw.githubusercontent.com
151.101.184.133 gist.githubusercontent.com
151.101.184.133 cloud.githubusercontent.com
151.101.184.133 camo.githubusercontent.com
151.101.184.133 avatars0.githubusercontent.com
151.101.184.133 avatars1.githubusercontent.com
151.101.184.133 avatars2.githubusercontent.com
151.101.184.133 avatars3.githubusercontent.com
151.101.184.133 Build software better, together
151.101.184.133 avatars5.githubusercontent.com
151.101.184.133 avatars6.githubusercontent.comⅢ
151.101.184.133 avatars7.githubusercontent.com
151.101.184.133 Build software better, together


Ⅲ.github.io（github pages）访问不了
参考 https://blog.csdn.net/nima1994/article/details/107985198

1、修改系统的hosts （C:\Windows\System32\drivers\etc），添加

# GitHub Start 
185.199.110.153 catalyst-team.github.io
185.199.111.153 raw.githubusercontent.com

2、修改本地连接 IPv4属性
控制面板--网络和 Internet--网络和共享中心--查看活动网络--本地连接--属性--Internet 协议版本4（TCP/IPv4）--属性--使用下面的DNS服务器地址
首选DNS服务器设置为 223.5.5.5
备用DNS服务器设置为 223.6.6.6

```

---

## 3、git静态网页与自定义域名

> [gitlab、github、gitee布署mkdocs主题仓库]( 
https://blog.xushufa.cn ) &ensp;  [git平台docsify布署markdown文件]( https://reco-blog.xushufa.cn )

> [gitlab、github绑定自定义域名]( https://mkdocs-blog.xushufa.cn ) &ensp; [vuepress构建项目]( https://docsify-blog.xushufa.cn )

```js
github、gitee、gitlab静态网页发布 

github pages 
位置：Setting - GitHub Pages - Save
优点：有十种主题可以选择；提交代码自动发布最新文件。
不足：主题偏少。外网，选择主题时速度慢，但是发布后的文件访问较快。
备注：删除目录下_config.yml文件即选择默认的markdown主题。


gitee pages   
位置：服务 - Gitee Pages - 启动
优点：速度快。
不足：无主题可选；markdown会还原成纯文本，需要转成html文件；提交新代码后每次需手动更新才能发布新文件。
备注：有时服务改造，无法启动。


------ 


gitlab pages   
gitlab布署网页与gitee、github不一样，无需启动。只要添加文件即可。
1、添加  .gitlab-ci.yml 文件 。
2、创建public文件夹，并将html文件上传到此处。
3、推送文件。在gitlab项目 CI/CD --> Jobs 查看是否布署成功。
4、访问页面。如 ：  https://xuyq123.gitlab.io/plain/index.html

参考项目： https://gitlab.com/xuyq123/plain

--- 

.gitlab-ci.yml 文件

pages:
  stage: deploy
  script:
  - echo 'Nothing to do...'
  artifacts:
    paths:
    - public
  only:
  - master
   
   
```

---

## 4、名帖    

```
高山仰止，景行行止。虽不能至，心向往之。
   
王羲之-兰亭集序、颜真卿-多宝塔碑、文徵明-小楷赤壁赋。所谓王颜文是也。

```

> 王羲之-兰亭集序 <br/>
![王羲之-兰亭集序]( https://xyqin.coding.net/p/my/d/imgs/git/raw/master/other/王羲之-兰亭集序.jpg )

> 颜真卿-多宝塔碑 <br/>
![颜真卿-多宝塔碑]( https://xyqin.coding.net/p/my/d/imgs/git/raw/master/other/颜真卿-多宝塔碑.jpg )

> 文徵明-小楷赤壁赋 <br/>
![文徵明-小楷赤壁赋]( https://xyqin.coding.net/p/my/d/imgs/git/raw/master/other/文徵明-小楷赤壁赋.jpg )

---
