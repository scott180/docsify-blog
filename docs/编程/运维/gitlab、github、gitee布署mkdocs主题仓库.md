# gitlab、github、gitee布署mkdocs主题仓库

<br />

`网址` &ensp; [xushufa]( https://xushufa.cn ) &ensp; [blog]( https://vuepress-blog.xushufa.cn )

## 1、概述
```
mkdocs作用
在git平台创建mkdocs主题仓库，自动将markdown文件生成静态网页。 

官网  https://www.mkdocs.org/
简介&主题   https://www.jianshu.com/p/c005c45abe85
gitlab-mkdocs  https://gitlab.com/pages/mkdocs
github-mkdocs-theme  https://github.com/mkdocs/mkdocs/wiki/MkDocs-Themes

```

```
python安装及下载  https://gitlab.com/xuyq123/mynotes/-/blob/master/Python.md
mkdocs安装     pip install mkdocs

```

## 2、gitlab布署mkdocs
```
gitlab mkdocs主题仓库 （推送文件自动布署）
创建方法：
   1、创建 mkdocs.yml、.gitlab-ci.yml文件。
   2、创建docs目录，markdown文件放在此处。
   3、推送到仓库。
   
   可fork此仓库 https://gitlab.com/xuyq123/plain-mkdocs    https://xuyq123.gitlab.io/plain-mkdocs
   注意：若部署失败，可在 CI/CD --> Jobs查看原因，修改 .gitlab-ci.yml 文件。
   
本地调试：
	安装 python 及 mkdocs 
	相关命令 
		 pip install mkdocs      安装mkdocs
		 python -m pip install --upgrade pip     更新Python
		 
		 mkdocs new my-project
		 mkdocs serve
		 mkdocs build

参考：
	https://cloud.tencent.com/developer/article/1662592
	https://blog.csdn.net/qq_32332433/article/details/106148965
	
	
```

``` 
		 
主题安装
	pip install -i https://pypi.tuna.tsinghua.edu.cn/simple mkdocs-Bootswatch      安装多个主题
	pip install --trusted-host pypi.douban.com -i http://pypi.douban.com/simple/ mkdocs mkdocs-material   安装material主题
	pip install --trusted-host pypi.douban.com -i http://pypi.douban.com/simple/ mkdocs mkdocs-gitbook    安装gitbook主题
	
	pip install mkdocs-cluster   
	pip install mkdocs-cinder      
	pip install mkdocs-bootstrap4
		
		
主题种类					
The available installed themes are: readthedocs, cluster, lumen, flatly, yeti, material, cosmo, litera, sandstone, darkly, lux, minty, superhero, 
gitbook, spacelab, cinder, pulse, mkdocs, cerulean, bootstrap, solar, journal, simplex, slate, materia, bootstrap4, ivory, cyborg, united



主题总结
	mkdocs      默认主题，速度极快。菜单横向。蓝边白底。不够美观。     评级四星。
	material    速度一般。菜单竖向，左侧整体文件结构，右侧文件目录。紫边白底。   评级四星。
	readthedocs 速度一般。菜单竖向。展示markdown文件目录结构。黑边白底。  评级四星。
	sandstone   加载挺快。菜单横向。黑边白底。    评级四星。
	litera      加载挺快。菜单横向。天蓝边白底。  评级四星。
	lumen       加载挺快。菜单横向。海蓝边白底。  评级四星。
	spacelab    加载挺快。菜单横向。深蓝边白底。  评级四星。
	cluster	    加载挺快。菜单横向。灰边白底。    评级四星。
	slate	    加载挺快。菜单横向。黑边黑底。    评级四星。
	simplex	    加载挺快。菜单横向。深红边白底。  评级四星。
	united	    加载挺快。菜单横向。橙红边白底。  评级四星。
	cinder	    速度一般，不显示文件夹中的下拉文档。展示markdown文件目录结构。菜单横向。黑边白底。适合单级目录文件。   评级三星。
	bootstrap4  速度相当慢。菜单横向。黑边白底。  评级二星。
	gitbook     速度一般。菜单竖向，灰边白底。有版权广告去不掉。   评级二星。
	ivory       速度相当慢。菜单竖向，黑边白底。  评级二星。

	
```

## 3、github布署mkdocs

```
github mkdocs主题仓库 （mkdocs gh-deploy 一键布署）
创建方法：
   1、创建 mkdocs.yml、README.md、.gitignore文件。创建docs目录，markdown文件放在此处。
   2、本地调试 mkdocs serve   访问：http://127.0.0.1:8000/  。 推送上述三个文件。
   3、一键布署 mkdocs gh-deploy  （自动生成 gh-pages分支，发布GithubPages ）
   可fork此仓库 https://github.com/scott180/plain-mkdocs 	https://scott180.github.io/plain-mkdocs/

后期更新文件，只需执行 mkdocs gh-deploy 。
   
本地调试：
	安装 python 及 mkdocs 
	相关命令 
		 mkdocs serve
		 mkdocs gh-deploy 
	
参考：
	https://www.cnblogs.com/paulwhw/p/12725523.html
	
```

## 4、gitee布署mkdocs
```
gitee mkdocs主题仓库 （GiteePages服务布署目录）
创建方法：
   1、创建 mkdocs.yml。创建docs目录，markdown文件放在此处。
   2、本地调试 mkdocs serve   访问：http://127.0.0.1:8000/  。
   3、mkdocs build  生成静态文件，于 site 文件夹中。
   4、推送 mkdocs.yml 、docs、site到仓库。
   5、开启GiteePages服务，选择对应分支。
   可参考此仓库 https://gitee.com/xy180/plain-mkdocs 	http://xy180.gitee.io/plain-mkdocs/1/site
   
本地调试：
	安装 python 及 mkdocs 
	相关命令 
		 mkdocs serve
		 mkdocs build
		 
参考：
	https://www.cnblogs.com/yywBlogW/p/11362889.html


```

---

## 5、示例

| 仓库   | 地址                                               			      |  备注             			                 |
| -----  | -------------------------------------------------------------      |  -----------------------------------         |
| gitlab | [plain-mkdocs]( https://gitlab.com/xuyq123/plain-mkdocs ) &ensp; [**网页**]( https://xuyq123.gitlab.io/plain-mkdocs ) <br/>[plain-pip-mkdocs]( https://gitlab.com/xuyq123/plain-pip-mkdocs ) &ensp; [网页]( https://xuyq123.gitlab.io/plain-pip-mkdocs )  <br/>[plain-mkdocs-gitbook]( https://gitlab.com/xuyq123/plain-mkdocs-gitbook ) &ensp; [网页]( https://xuyq123.gitlab.io/plain-mkdocs-gitbook )            |  gitlab布署mkdocs：配置文件.gitlab-ci.yml。    |
| github | [plain-mkdocs]( https://github.com/scott180/plain-mkdocs ) &ensp; [网页](  https://scott180.github.io/plain-mkdocs/ ) <br/>[plain-pip-mkdocs]( https://github.com/scott180/plain-pip-mkdocs ) &ensp; [网页]( https://scott180.github.io/plain-pip-mkdocs )  <br/>[plain-mkdocs-serve]( https://github.com/scott180/plain-mkdocs-serve )  &ensp; [网页]( https://scott180.github.io/plain-mkdocs-serve )              |  github布署mkdocs：命令mkdocs gh-deploy 。           |
| gitee  |  [plain-mkdocs]( https://gitee.com/xy180/plain-mkdocs ) &ensp; [网页1]( http://xy180.gitee.io/plain-mkdocs/1/site/ ) &ensp; [网页2]( http://xy180.gitee.io/plain-mkdocs/2/site/ ) &ensp; [网页3]( http://xy180.gitee.io/plain-mkdocs/3/site/ )                                  |  gitee布署mkdocs：mkdocs build 布署目录。    |
| note-mkdocs   | gitlab [note-mkdocs]( https://gitlab.com/xuyq123/note-mkdocs )&ensp; [**网页1**](  https://xuyq123.gitlab.io/note-mkdocs/ ) &ensp; [网页2](  https://xuyq123.gitlab.io/note-pip-mkdocs/ ) <br/>github [note-mkdocs]( https://github.com/scott180/note-mkdocs )&ensp; [网页](  https://scott180.github.io/note-mkdocs/ )<br/>gitee [plain-mkdocs]( https://gitee.com/xy180/plain-mkdocs/tree/master/note )&ensp; [网页](  http://xy180.gitee.io/plain-mkdocs/note/site/ )       |  个人笔记，含书法练习及java笔记。                        |
| myblog-mkdocs   | gitlab [mkdocs-blog]( https://gitlab.com/xuyq123/mkdocs-blog )&ensp; [网页](  https://xuyq123.gitlab.io/mkdocs-blog/ ) <br/>github [myblog-mkdocs]( https://github.com/scott180/myblog-mkdocs )&ensp; [网页](  https://scott180.github.io/myblog-mkdocs/ )<br/>gitee [myblog-mkdocs]( https://gitee.com/xy180/myblog-mkdocs ) |  我的博客                        |

---

