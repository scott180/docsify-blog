
## git平台docsify布署markdown文件

### 本地安装

```
安装node参考
https://gitlab.com/xuyq123/mynotes/-/blob/master/%E5%85%B6%E4%BB%96/nodejs%20&%20vue.md


// 安装docsify
npm i docsify-cli -g   

docsify --version

// 初始化docsify项目
docsify init ./test

// 运行
docsify serve docs

http://localhost:3000
 
教程
https://docsify.js.org/#/zh-cn/quickstart
https://www.jianshu.com/p/4883e95aa903
https://marked.js.org/demo/


修改主题
index.html  
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/docsify/themes/vue.css">
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/docsify/themes/buble.css">
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/docsify/themes/dark.css">
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/docsify/themes/pure.css">
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/docsify/themes/dolphin.css">


```


```

给每个页面的末尾加上 footer
window.$docsify = {
  plugins: [
    function(hook) {
      var footer = [
        '<hr/>',
        '<footer>',
        '<span><a href="https://github.com/QingWei-Li">cinwell</a> &copy;2017.</span>',
        '<span>Proudly published with <a href="https://github.com/docsifyjs/docsify" target="_blank">docsify</a>.</span>',
        '</footer>'
      ].join('');

      hook.afterEach(function(html) {
        return html + footer;
      });
    }
  ]
};

```


---

### gitlab布署docsify

```
创建 .gitlab-ci.yml
创建docs目录，将markdown文件放在此处，推送文件。

可参考此项目  https://gitlab.com/xuyq123/plain-docsify   https://xuyq123.gitlab.io/plain-docsify 
多级页面可参考  https://gitlab.com/xuyq123/myblog-docsify   https://xuyq123.gitlab.io/myblog-docsify 

```


> .gitlab-ci.yml
```
image: ruby:alpine

stages:
- deploy

pages:
  stage: deploy
  script:
  - cp -r docs/. public
  - echo "deploying to pages for note"
  environment:
    name: note
    url: https://xuyq123.gitlab.io/plain-docsify
  artifacts:
    paths:
    - public
  only:
  - master

```

---

### github布署docsify

```
本地生成 docsify项目

开启github pages 
位置：Setting - GitHub Pages - Save  
选择对应目录。


```


---

### gitee布署docsify
```
在对应的 Gitee 仓库服务中选择 Gitee Pages，选择您要部署的分支，填写您要部署的分支上的目录，
例如docs，填写完成之后点击启动即可。
```
