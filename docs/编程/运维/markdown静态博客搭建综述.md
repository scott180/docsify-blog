# markdown静态博客搭建综述

## 一 说明

使用markdown写文档，搭建自己的个人博客。纯前端展示，不需数据库，不需后端编程交互。<br>
选择市场上比较知名的静态博客搭建程序，对照比较，综合评价。<br>

<br>

## 二 软件

### 2.1 docsify

docsify 可以快速帮你生成文档网站。不同于GitBook、Hexo的地方是它不会生成静态的 .html 文件，所有转换工作都是在运行时。

布署文档：[git平台docsify布署markdown文件]( https://xushufa.cn/docs/bian-cheng/yun-wei/gitping-tai-docsifybu-shu-markdownwen-jian.html )


| 名称       | 仓库                                                			        |  备注            |
| ---------  | -------------------------------------------------------------        |  ----------      |
| plain-docsify    | gitlab [plain-docsify]( https://gitlab.com/xuyq123/plain-docsify )&ensp; [**网页**](  https://xuyq123.gitlab.io/plain-docsify/ ) <br/>github [plain-docsify]( https://github.com/scott180/plain-docsify )&ensp; [网页](  https://scott180.github.io/plain-docsify/ ) |  笔记docsify                        |
| myblog-docsify   | gitlab [myblog-docsify]( https://gitlab.com/xuyq123/myblog-docsify )&ensp; [**网页**](  https://xuyq123.gitlab.io/myblog-docsify/ ) <br/>github [docsify-blog]( https://github.com/scott180/myblog-docsify )&ensp; [网页](  https://scott180.github.io/docsify-blog ) |  笔记与博客docsify                  |



### 2.2 mkdocs

在git平台创建mkdocs主题仓库，自动将markdown文件生成静态网页。 

布署文档：[gitlab、github、gitee布署mkdocs主题仓库]( https://xushufa.cn/docs/bian-cheng/yun-wei/gitlab-github-giteebu-shu-mkdocszhu-ti-cang-ku.html )


| 仓库   | 地址                                               			      |  备注             			                 |
| -----  | -------------------------------------------------------------      |  -----------------------------------         |
| gitlab | [plain-mkdocs]( https://gitlab.com/xuyq123/plain-mkdocs ) &ensp; [**网页**]( https://xuyq123.gitlab.io/plain-mkdocs ) <br/>[plain-pip-mkdocs]( https://gitlab.com/xuyq123/plain-pip-mkdocs ) &ensp; [网页]( https://xuyq123.gitlab.io/plain-pip-mkdocs )  <br/>[plain-mkdocs-gitbook]( https://gitlab.com/xuyq123/plain-mkdocs-gitbook ) &ensp; [网页]( https://xuyq123.gitlab.io/plain-mkdocs-gitbook )            |  gitlab布署mkdocs：配置文件.gitlab-ci.yml。    |
| github | [plain-mkdocs]( https://github.com/scott180/plain-mkdocs ) &ensp; [网页](  https://scott180.github.io/plain-mkdocs/ ) <br/>[plain-pip-mkdocs]( https://github.com/scott180/plain-pip-mkdocs ) &ensp; [网页]( https://scott180.github.io/plain-pip-mkdocs )  <br/>[plain-mkdocs-serve]( https://github.com/scott180/plain-mkdocs-serve )  &ensp; [网页]( https://scott180.github.io/plain-mkdocs-serve )              |  github布署mkdocs：命令mkdocs gh-deploy 。           |
| gitee  |  [plain-mkdocs]( https://gitee.com/xy180/plain-mkdocs ) &ensp; [网页1]( http://xy180.gitee.io/plain-mkdocs/1/site/ ) &ensp; [网页2]( http://xy180.gitee.io/plain-mkdocs/2/site/ ) &ensp; [网页3]( http://xy180.gitee.io/plain-mkdocs/3/site/ )                                  |  gitee布署mkdocs：mkdocs build 布署目录。    |
| note-mkdocs   | gitlab [note-mkdocs]( https://gitlab.com/xuyq123/note-mkdocs )&ensp; [**网页1**](  https://xuyq123.gitlab.io/note-mkdocs/ ) &ensp; [网页2](  https://xuyq123.gitlab.io/note-pip-mkdocs/ ) <br/>github [note-mkdocs]( https://github.com/scott180/note-mkdocs )&ensp; [网页](  https://scott180.github.io/note-mkdocs/ )<br/>gitee [plain-mkdocs]( https://gitee.com/xy180/plain-mkdocs/tree/master/note )&ensp; [网页](  http://xy180.gitee.io/plain-mkdocs/note/site/ )       |  个人笔记，含书法练习及java笔记。                        |
| myblog-mkdocs   | gitlab [mkdocs-blog]( https://gitlab.com/xuyq123/mkdocs-blog )&ensp; [网页](  https://xuyq123.gitlab.io/mkdocs-blog/ ) <br/>gitee [myblog-mkdocs]( https://gitee.com/xy180/myblog-mkdocs ) |  



### 2.3 gitbook

GitBook可以看作个人的一本电子书。它用MarkDown编写，可以自动生成网页或者PDF等，生成网页可以通过插件配置目录和搜索功能等。

布署文档：[gitbook部署博客]( https://xushufa.cn/docs/bian-cheng/yun-wei/gitbookbu-shu-bo-ke.html )


应用：
https://scott180.github.io/gitbook-blog/ <br>
https://github.com/scott180/gitbook-blog



### 2.4 vuepress

Vue 驱动的静态网站生成器

官网： [vuepress]( https://vuepress.vuejs.org/zh/ ) &ensp; [vuepress-next]( https://github.com/vuepress/vuepress-next ) <br>
应用： [github]( https://github.com/scott180/vuepress-blog ) &ensp; [vuepress-blog]( https://scott180.github.io/vuepress-blog ) 

布署文档：[vuepress构建项目]( https://xushufa.cn/docs/bian-cheng/yun-wei/vuepressgou-jian-xiang-mu.html )



### 2.5 vuepress-theme-reco

一款简洁而优雅的 vuepress 博客 & 文档 主题。

官网：[vuepress-theme-reco]( https://vuepress-theme-reco.recoluan.com/views/1.x/configJs.html )  &ensp; [vuepress-reco]( https://github.com/vuepress-reco/vuepress-theme-reco-1.x ) <br>
应用：[github]( https://github.com/scott180/reco-blog ) &ensp; [reco-blog]( https://scott180.github.io/reco-blog )

布署文档：[vuepress构建项目：vuepress-theme-reco]( https://xushufa.cn/docs/bian-cheng/yun-wei/vuepressgou-jian-xiang-mu.html#%E4%BA%8C%E3%80%81vuepress-theme-reco )



### 2.6 others


**Github Pages**

> GitHub Pages 是 GitHub 提供的一项免费服务，它可以让用户轻松地创建自己的静态网站。

地址： https://github.com/scott180/calligraphy <br>
应用： https://scott180.github.io/calligraphy  <br>
使用方法：Settings -- Pages -- Branch - Master -- Save

<br/>

gitlab/gitee 也有类似的静态页面功能。 [gitlab_pages]( https://xuyq123.gitlab.io/plain-docsify/#/%E4%B9%A6%E6%B3%95%E7%BB%83%E4%B9%A0%E8%BD%A8%E8%BF%B9--%E6%98%8E%E6%9C%88%E5%87%A0%E6%97%B6%E6%9C%89 )  &ensp;  [gitee_pages]( http://xy180.gitee.io/plain-mkdocs/calligraphy/%E4%B9%A6%E6%B3%95%E7%BB%83%E4%B9%A0%E8%BD%A8%E8%BF%B9--%E6%98%8E%E6%9C%88%E5%87%A0%E6%97%B6%E6%9C%89.html )

---

**netlify**

> Netlify 是一家位于旧金山的云计算公司，为用户提供Web 应用程序和动态网站构建、部署和无服务器后端服务。

地址： [netlify]( https://app.netlify.com ) &ensp; [netlify-calligraphy-nextjs]( https://github.com/scott180/netlify-calligraphy-nextjs ) <br>
应用： [nextjs]( https://netlify-calligraphy-nextjs.netlify.app )  &ensp; [artisan]( https://netlify-calligraphy-artisan.netlify.app )

Netlify Next.js Blog Template designed by Bejamas

---

**Vercel**

> Vercel是一个专注于静态站点部署和服务器端渲染的云平台。

地址： [vercel]( https://vercel.com/xuyq ) <br>
应用： [**docusaurus2**]( https://calligraphy-docusaurus2.vercel.app )  &ensp; [portfolio]( https://calligraphy-portfolio.vercel.app )

---

另外有 [Hexo]( https://hexo.io/index.html )/[Hugo]( https://www.gohugo.org/ )/[Jekyll]( https://github.com/jekyll/jekyll ) 也可建站。

<br>

## 三 综述

**操作感受** <br>
[Hexo]( https://hexo.io/index.html )/[Hugo]( https://www.gohugo.org/ )/[Jekyll]( https://github.com/jekyll/jekyll )  功能很强大，但是操作不太简单，上手难度有点高。 <br>
[netlify]( https://app.netlify.com ) / [vercel]( https://vercel.com/xuyq ) 国外网站，不是太稳定。常常访问不了网站。 <br>
docsify/mkdocs/gitbook  这三个比较类似，布署简单，但是主题也单调简约，插件不是很多。 <br>
[vuepress]( https://vuepress.vuejs.org/zh/ )/vuepres-theme-reco 非常优秀的静态博客搭建方法，界面美观，功能齐全。就是安装布署的时候可能会有版本兼容问题。 <br>

另外在github/gitlab/gitee 的平台上搭建好静态博客，如要绑定域名，可查看文档：[gitlab、github绑定自定义域名]( https://xushufa.cn/docs/bian-cheng/yun-wei/gitlab-githubbang-ding-zi-ding-yi-yu-ming.html ) 。

---

**我的网站**  
生活随笔-编程笔记-书法练习轨迹

| 徐书法 | 地址        |  备注          |
| -----  | ----------- |  ------------- |
| 1      | [xushufa]( https://xushufa.cn )                  	    | `vuepress-theme-reco`构建的博客网站。|
| 2      | [vuepress-blog]( https://vuepress-blog.xushufa.cn )  	| `vuepress`构建的博客网站。           |
| 3      | [mkdocs-blog]( https://xuyq123.gitlab.io/mkdocs-blog )   | `mkdocs`构建的博客网站。             |

