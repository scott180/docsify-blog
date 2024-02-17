# vuepress构建项目

## 一、vuepress

使用vuepress构建的博客。
[github]( https://github.com/scott180/vuepress-blog ) &ensp; [gitlab]( https://gitlab.com/xuyq123/mynotes ) &ensp; [vuepress-blog]( https://scott180.github.io/vuepress-blog )  


### 1.1 安装

参考 [vuepress]( https://vuepress.vuejs.org/zh/ ) &ensp; [blog-vuepress]( https://github.com/codeteenager/blog-vuepress )  

在使用前请先安装VuePress

```sh
$ npm install -g vuepress
```


```sh
mkdir vuepress-starter && cd vuepress-starter
```

```sh
yarn init # npm init
```

```sh
yarn add -D vuepress # npm install -D vuepress
```


```sh
mkdir docs && echo '# Hello VuePress' > docs/README.md
```

> 在 `package.json` 中添加一些 `scripts` 

```json
{
  "scripts": {
    "docs:dev": "vuepress dev docs",
    "docs:build": "vuepress build docs"
  }
}
```

使用`npm run docs:dev`启动项目，启动后在浏览器中使用`localhost:8080`进行访问
```sh
$ npm run docs:dev
```

使用`npm run docs:build`打包项目
```sh
$ npm run docs:build
```



### 1.2 部署

根目录创建 `deploy.sh` 文件

```
#!/usr/bin/env sh

# 确保脚本抛出遇到的错误
set -e

# 生成静态文件
npm run docs:build

# 进入生成的文件夹
cd docs/.vuepress/dist

# 如果是发布到自定义域名
# echo 'www.example.com' > CNAME

git init
git add -A
git commit -m 'deploy'

# 如果发布到 https://<USERNAME>.github.io
# git push -f git@github.com:scott180/scott180.github.io.git master

# 如果发布到 https://<USERNAME>.github.io/<REPO>  注意配置 `docs\.vuepress\config.js` 的 `base`
# git push -f git@github.com:scott180/vuepress-calligraphy.git master:gh-pages

cd -

```


```c
如果要在github绑定域名，如 https://blog.xushufa.cn/ ，必须在github建立同名项目，如 https://github.com/scott180/blog 。如果子域名和项目不同名，布署时css样式会混乱，网页不能正常访问。 
而且 `.vuepress\config.js` 的 `base` 也要注释掉。

绑定教程可参看： gitlab、github绑定自定义域名  https://xushufa.cn/docs/bian-cheng/yun-wei/gitlab-githubbang-ding-zi-ding-yi-yu-ming.html  

```

---

设置 `package.json`
```
{
    "scripts": {
        "deploy": "bash deploy.sh"
      }
}

```

运行 `npm run deploy` 即可部署到github静态页面

---


> 主要命令

```sh
# 启动 
npm run docs:dev

# 打包
npm run docs:build

# 部署
npm run deploy

```

---

> 参考项目

```sh
git clone git@github.com:scott180/vuepress-blog.git

克隆本项目后运行 npm install -D vuepress 安装相关插件。
再启动 npm run docs:dev 项目，有可能会失败，因为版本不太兼容。
所以可以直接在下方链接下载原始全部项目包 vuepress-blog.20230812.zip ，将 node_modules 依赖包复制到项目，再启动即可。

```
[百度网盘]( https://pan.baidu.com/s/1NHLuagvGS5SWC-fHuHByaQ?pwd=576i ) &ensp;  [sourceforge]( https://sourceforge.net/p/xdocument/code/ci/master/tree/files/vuepress-blog.20230812.zip )

---

> 后续更新

```java
首次部署需要按照以上步骤发布项目，后续增加或更新文件只需：
在本地验证	npm run docs:dev
发布到线上	npm run deploy

```



### 1.3 问题

布署vuepress项目到git时可能会遇到一些问题，导致布署失败。现在下方列出具体问题及解决方法。

<br/>

**一、注意路径配置问题**

- 1、如果要发布路径为用户名+项目名

```js
如访问地址为 `https://scott180.github.io/reco-blog` 则

`docs\.vuepress\config.js` 配置 `base` 字段为项目名 `reco-blog`

`deploy.sh` 配置 `git push -f git@github.com:scott180/reco-blog.git master:gh-pages`

```

- 2、如果要绑定域名

```js
如访问地址为 `http://xushufa.cn` 则

`docs\.vuepress\config.js` 配置 `base` 字段需注释掉

`deploy.sh` 配置 
echo 'xushufa.cn' > CNAME
`git push -f git@github.com:scott180/reco-calligraphy.git master:gh-pages`


```

---


**二、 github Actions pages build and deployment 报错**

```
本地执行deploy正常，但是github的Actions报错了。

错误内容如下：
Deployment request failed for 5a3201f6016e6e078f0f3c46eb4132a3d9014bdd due to in progress deployment. Please cancel 7e10a83b419c464b908a13787a0b0bfe39cc1ca7 first or wait for it to

{
    "$id": "1",
    "innerException": null,
    "message": "The user 'System:PublicAccess;aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa' is not authorized to access this resource.",
    "typeName": "Microsoft.TeamFoundation.Framework.Server.UnauthorizedRequestException, Microsoft.TeamFoundation.Framework.Server",
    "typeKey": "UnauthorizedRequestException",
    "errorCode": 0,
    "eventId": 3000
}

```


```

解决方法：
1、点击 Re-run all jobs 再试一次。
2、本地vuepress缓存影响，将dist目录删除，重新deploy。
3、可能是SSH key有问题，换一下，重新deploy。

```

---


**三、npm run docs:build 本地打包项目，丢失css样式**

- 1、修改`base`字段为 `./`   （ 参考 https://blog.csdn.net/JZevin/article/details/109195652 ）<br>
修改 `/docs/.vuepress/config.js` 中配置的`base`字段  <br>
这里的资源路径不应该是绝对路径，根目录 `/` ，而应该是相对路径 `./` <br>


- 2、注释掉 `mode`  参考  https://www.jianshu.com/p/f9b9edd210f8

```c
找到 node_modules\@vuepress\core\lib\client\app.js文件， 注释掉 'mode'，让它默认哈希模式。

 const router = new Router({
    base: routerBase,
    // mode: 'history',
	
```

---


**四、本地npm run docs:dev没问题，但是npm run deploy报错**

```sh
 create mode 100644 "\347\274\226\347\250\213/\350\277\220\347\273\264/\346\211\271\351\207\217\346\216\250\351\200\201\345\270\203\347\275\262\345\276\220\344\271\246\346\263\225\351\241\271\347\233\256.html"
ssh: connect to host github.com port 22: Connection timed out
fatal: Could not read from remote repository.

Please make sure you have the correct access rights
and the repository exists.
npm ERR! code ELIFECYCLE
npm ERR! errno 128
npm ERR! vuepress-blog@1.0.0 deploy: `bash deploy.sh`
npm ERR! Exit status 128
npm ERR!
npm ERR! Failed at the vuepress-blog@1.0.0 deploy script.
npm ERR! This is probably not a problem with npm. There is likely additional logging output above.

npm ERR! A complete log of this run can be found in:
npm ERR!     E:\ProgramFiles\node-v12.22.9-win-x64\node_cache\_logs\2023-09-13T06_38_13_092Z-debug.log

```

```
exit status 128, stderr: "fatal: protocol error: bad line
exit status 128 表示在执行命令时出现了错误。
stderr: "fatal: protocol error: bad line length character: ???\n&...
这个错误的意思是在执行命令时遇到了无效的字符，可能是因为输入的命令格式不正确或者网络传输过程中出现了问题导致的。建议检查命令的格式是否正确，


markdown文件标题下一行的换行格式不符合要求。
原文：
# 旅行观测记录

<br/>

> 提瓦特旅行笔记


修改：将<br/>改成<br />，中间加一个空格。或者将<br/>去掉，加一个空行。


延伸阅读：
<br>是HTML写法。 
<br/>是XHTML1.1的写法，也是XML写法。
<br />是XHTML为兼容HTML的写法，也是XML写法。

像<br />这种写法是比较规范的！
转自：https://blog.csdn.net/czh500/article/details/107011801

```

---



### 1.4 备案号

> vuepress 底部添加网站备案号：ICP备案号与公安备案号  [富文本 footer]( https://vuepress.vuejs.org/zh/theme/default-theme-config.html#%E5%AF%8C%E6%96%87%E6%9C%AC-footer )

```
在 docs/README.md 添加备案
```

```
---
home: true
---

::: slot footer
MIT Licensed | Copyright © 2018-present [Evan You](https://github.com/yyx990803)
:::

```

---

```
---
home: true
---

::: slot footer
Copyright © 2022 · xushufa.cn · 无为徐生 <br/>  [浙ICP备2022008289号-1]( http://beian.miit.gov.cn/ ) <img src="/ba.png" width="20"> [浙公网安备 33011002016354号]( http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=33011002016354 ) 
:::

```



## 二、vuepress-theme-reco

使用vuepress-theme-reco构建的博客。

[github]( https://github.com/scott180/reco-blog ) &ensp; [reco-blog]( https://scott180.github.io/reco-blog )  &ensp; [reco-calligraphy]( https://scott180.github.io/reco-calligraphy )

### 2.1 安装

参考 [vuepress]( https://vuepress.vuejs.org/zh/ )  &ensp; [vuepress-theme-reco]( https://vuepress-theme-reco.recoluan.com/views/1.x/configJs.html )  &ensp; [vuepress-reco]( https://github.com/vuepress-reco/vuepress-theme-reco-1.x )  &ensp; [vuepress-theme-reco 主题优化]( https://blog.csdn.net/qq_42937522/article/details/122676915 ) &ensp; [vuepress-calligraphy]( https://github.com/scott180/vuepress-calligraphy )

```sh
# init
npm install @vuepress-reco/theme-cli -g
theme-cli init my-blog

# install
cd my-blog
npm install

# run
npm run dev

# build
npm run build

```


```
中文路径无效，需要安装插件

npm install -D  vuepress-plugin-permalink-pinyin

module.exports = {
  plugins: [
      // 支持中文文件名
      [
        "permalink-pinyin",
        {
          lowercase: true, // Converted into lowercase, default: true
          separator: "-", // Separator of the slug, default: '-'
        },
      ],
  ]
}

```



### 2.2 部署

根目录创建 `deploy.sh` 文件

```js
#!/usr/bin/env sh

# 确保脚本抛出遇到的错误
set -e

# 生成静态文件
npm run build

# 进入生成的文件夹
cd .vuepress/dist

# 如果是发布到自定义域名
# echo 'www.example.com' > CNAME

git init
git add -A
git commit -m 'deploy'

# 如果发布到 https://<USERNAME>.github.io
# git push -f git@github.com:scott180/scott180.github.io.git master

# 如果发布到 https://<USERNAME>.github.io/<REPO>  注意配置 `.vuepress\config.js` 的 `base`
# git push -f git@github.com:scott180/reco-calligraphy.git master:gh-pages

cd -

```

---

设置 `package.json`
```
{
    "scripts": {
        "deploy": "bash deploy.sh"
      }
}

```

运行 `npm run deploy` 即可部署到github静态页面



### 2.3 备案号

> vuepress 底部添加网站备案号：ICP备案号与公安备案号  [备案信息]( https://vuepress-theme-reco.recoluan.com/views/1.x/configJs.html#%E5%A4%87%E6%A1%88%E4%BF%A1%E6%81%AF%E5%92%8C%E9%A1%B9%E7%9B%AE%E5%BC%80%E5%A7%8B%E6%97%B6%E9%97%B4 )

```
在 .vuepress/config.js 添加备案
```

```
module.exports = {
  themeConfig: {
    record: '浙ICP备2022008289号-1',
    recordLink: 'http://beian.miit.gov.cn',
    cyberSecurityRecord: '浙公网安备 33011002016354号',
    cyberSecurityLink: 'http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=33011002016354',
    startYear: '2022'
  }
}

```




## 三、插件

[插件广场]( https://vuepress-theme-reco.recoluan.com/views/other/recommend.html ) &ensp; [plugin-medium-zoom]( https://v1.vuepress.vuejs.org/zh/plugin/official/plugin-medium-zoom.html#%E5%AE%89%E8%A3%85 )  &ensp; [medium-zoom]( https://vuepress-community.netlify.app/zh/plugins/medium-zoom/#%E5%AE%89%E8%A3%85 )


### 3.1 阅读量

参考 [Vuepress-阅读量统计]( https://heshiyu1996.github.io/blog/tool/vuepress-stat/ ) &ensp; [valine]( https://valine.js.org/ ) &ensp; [leancloud]( https://console.leancloud.cn/apps )

项目 [vuepress-calligraphy]( https://github.com/scott180/vuepress-calligraphy )

```
yarn add leancloud-storage -S

yarn add valine -S

```

创建`Valine.vue` 及 继承默认主题，并在`Page.vue`下引入 `<Valine />`。[commit]( https://github.com/scott180/vuepress-calligraphy/commit/18f3aefd2928e5e445a200842e61f9fa888575c5 )



### 3.2 时间格式化

[plugin-last-updated]( https://vuepress.vuejs.org/zh/plugin/official/plugin-last-updated.html ) &ensp; [momentjs]( http://momentjs.cn/ )

```
npm install moment --save

```

```js
plugins: [
[
  '@vuepress/last-updated',
  {
	transformer: (timestamp, lang) => {
	  // 不要忘了安装 moment
	  const moment = require('moment')
	  moment.locale(lang)
	  return moment(timestamp).format('YYYY-MM-DD HH:mm:ss');
	}
  }
]
]
```


```js
locales: {
	'/': {
	  lang: 'zh-CN',
	  title: 'VuePress',
	  description: 'Vue 驱动的静态网站生成器'
	}
}

```



### 3.3 全局搜索

```js
themeConfig: {
// algolia 全局搜索
algolia: {
  apiKey: '123',
  indexName: 'xushufa',
  appId: '456',
},

官网	
https://crawler.algolia.com/admin/crawlers/9f0f4253-4d83-44d8-9f0a-472f436581fd/overview
https://www.algolia.com/apps/ODP1ID8WCB/explorer/browse/xushufa?searchMode=search

教程
https://docsearch.algolia.com/docs/legacy/config-file/
https://www.cnblogs.com/yayujs/p/15982507.html

```

```java
搜索为空，一般是 pathsToMatch 没配置好
crawler.algolia.com -- Editor -- new Crawle -- pathsToMatch

pathsToMatch: ["https://blog.xushufa.cn/**"]

```



### 3.4 流程图

```
参考
https://vuepress-plugin-mermaidjs.efrane.com/
https://www.npmjs.com/package/vuepress-plugin-mermaidjs?activeTab=readme
https://github.com/vuejs/vuepress/issues/111
```


```js
下载   
npm install --save-dev vuepress-plugin-mermaidjs
或
yarn add -D vuepress-plugin-mermaidjs


配置
// .vuepress/config.js
module.exports = {
    // ...
    plugins: [
        'vuepress-plugin-mermaidjs'
    ]
    // ...
}

```

```js

引号格式不支持，只能用箭头格式的。


添加文件
// .vuepress/components/mermaid.vue

<template>
  <div class="mermaid">
    <slot></slot>
  </div>
</template>

<script>
export default {
  mounted() {
    import("mermaid/dist/mermaid").then(m => {
      m.initialize({
        startOnLoad: true
      });
      m.init();
    });
  }
};
</script>



使用这种格式的
### Random mermaid example

<mermaid>
graph TD
  A[Silvester] -->|Get money| B(Go shopping)
  B --> C{Let me think}
  C -->|One| D[Laptop]
  C -->|Two| E[iPhone]
  C -->|Three| F[Car]
  C -->|Four| F[Mac]
</mermaid>

```


---


### 3.5 图片放大

> 图片放大

```
yarn add -D @vuepress/plugin-medium-zoom
# OR npm install -D @vuepress/plugin-medium-zoom


module.exports = {
  plugins: {
    '@vuepress/medium-zoom': {
      selector: 'img.zoom-custom-imgs',
      // medium-zoom options here
      // See: https://github.com/francoischalifour/medium-zoom#options
      options: {
        margin: 16
      }
    }
  }
}
```


---


```
npm install -D vuepress-plugin-medium-zoom


// .vuepress/config.js
module.exports = {
  plugins: [
    [
      'vuepress-plugin-medium-zoom',
      {
        selector: '.my-wrapper .my-img',
        delay: 1000,
        options: {
          margin: 24,
          background: '#BADA55',
          scrollOffset: 0,
        },
      },
    ],
  ],
}
```



### 3.6 展示名人名句

> 默认随机展示名人名句

参考 [vuepress-plugin-boxx]( https://github.com/zpj80231/vuepress-plugin-boxx )

```js
在文件package.json中的devDependencies下加入"vuepress-plugin-boxx": "0.0.7"：


"devDependencies": {
    "vuepress-plugin-boxx": "0.0.7"
}

```

```
在 vuepress 的config.js中配置plugins：

"plugins": [
    ["vuepress-plugin-boxx"]
]

```

```sh
npm install

在文档标题前一行添加 <Boxx/>


```

---



