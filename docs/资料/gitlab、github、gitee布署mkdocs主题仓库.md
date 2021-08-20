
## gitlab、github、gitee布署mkdocs主题仓库

`原址` &ensp; [布署mkdocs]( https://gitlab.com/xuyq123/mynotes/-/blob/master/gitlab%E3%80%81github%E3%80%81gitee%E5%B8%83%E7%BD%B2mkdocs%E4%B8%BB%E9%A2%98%E4%BB%93%E5%BA%93.md ) &ensp; [calligraphy]( https://gitlab.com/xuyq123/calligraphy )

### 1、概述
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

### 2、gitlab布署mkdocs
```
gitlab mkdocs主题仓库 （推送文件自动布署）
创建方法：
   创建 mkdocs.yml、.gitlab-ci.yml文件。创建docs目录，markdown文件放在此处。推送到仓库。
   
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
	
	
------	
	 
		 
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

### 3、github布署mkdocs

```
github mkdocs主题仓库 （mkdocs gh-deploy 一键布署）
创建方法：
   1、创建 mkdocs.yml、README.md、.gitignore文件。创建docs目录，markdown文件放在此处。
   2、本地调试 mkdocs serve   访问：http://127.0.0.1:8000/  。 推送上述三个文件。
   3、一键布署 mkdocs gh-deploy  （自动生成 gh-pages分支，发布GithubPages ）
   可fork此仓库 https://github.com/scott180/plain-mkdocs 	https://scott180.github.io/plain-mkdocs/
   
本地调试：
	安装 python 及 mkdocs 
	相关命令 
		 mkdocs serve
		 mkdocs gh-deploy 
	
参考：
	https://www.cnblogs.com/paulwhw/p/12725523.html
	
```

### 4、gitee布署mkdocs
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