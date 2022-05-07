# vuepress构建项目

## 一、vuepress默认主题

使用vuepress构建的博客。
[github]( https://github.com/scott180/vuepress-blog ) &ensp; [vuepress-blog]( https://scott180.github.io/vuepress-blog )  &ensp; [vuepress-calligraphy]( https://scott180.github.io/vuepress-calligraphy )


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



> 注意路径配置问题：

- 1、如果要发布路径为用户名+项目名

```js
如访问地址为 `https://scott180.github.io/reco-blog` 则

`docs\.vuepress\config.js` 配置 `base` 字段为项目名 `reco-blog`

`deploy.sh` 配置 `git push -f git@github.com:scott180/reco-calligraphy.git master:gh-pages`

```

- 2、如果要绑定域名

```js
如访问地址为 `http://reco-blog.xushufa.cn/` 则

`docs\.vuepress\config.js` 配置 `base` 字段需注释掉

`deploy.sh` 配置 
echo 'reco-blog.xushufa.cn' > CNAME
`git push -f git@github.com:scott180/reco-calligraphy.git master:gh-pages`


```

---



> github Actions pages build and deployment 报错

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

> 主要命令

```sh
# 启动 
npm run docs:dev

# 打包
npm run docs:build

# 部署
npm run deploy

```


### 1.3 备案号

> vuepress 底部添加网站备案号：ICP备案号与公安备案号  [富文本 footer]( https://vuepress.vuejs.org/zh/theme/default-theme-config.html#%E5%AF%8C%E6%96%87%E6%9C%AC-footer )

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



### 1.4 阅读量

参考 [Vuepress-阅读量统计]( https://heshiyu1996.github.io/blog/tool/vuepress-stat/ ) &ensp; [valine]( https://valine.js.org/ ) &ensp; [leancloud]( https://console.leancloud.cn/apps )

项目 [vuepress-calligraphy]( https://github.com/scott180/vuepress-calligraphy )

```
yarn add leancloud-storage -S

yarn add valine -S

```

创建`Valine.vue` 及 继承默认主题，并在`Page.vue`下引入 `<Valine />`。[commit]( https://github.com/scott180/vuepress-calligraphy/commit/18f3aefd2928e5e445a200842e61f9fa888575c5 )



### 1.5 时间格式化

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

### 1.6 全局搜索


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


## 二、vuepress-theme-reco主题

使用vuepress-theme-reco构建的博客。

[github]( https://github.com/scott180/reco-blog ) &ensp; [reco-blog]( https://scott180.github.io/reco-blog )  &ensp; [reco-calligraphy]( https://scott180.github.io/reco-calligraphy )

### 2.1 安装

参考 [vuepress]( https://vuepress.vuejs.org/zh/ )  &ensp; [vuepress-theme-reco]( https://vuepress-theme-reco.recoluan.com/views/1.x/configJs.html )  &ensp; [vuepress-reco]( https://github.com/vuepress-reco/vuepress-theme-reco-1.x )  &ensp; [vuepress-theme-reco 主题优化]( https://blog.csdn.net/qq_42937522/article/details/122676915 ) &ensp; [vuepress-calligraphy]( https://github.com/scott180/vuepress-calligraphy )

```
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

```
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


---

> vuepress 底部添加网站备案号：ICP备案号与公安备案号  [备案信息]( https://vuepress-theme-reco.recoluan.com/views/1.x/configJs.html#%E5%A4%87%E6%A1%88%E4%BF%A1%E6%81%AF%E5%92%8C%E9%A1%B9%E7%9B%AE%E5%BC%80%E5%A7%8B%E6%97%B6%E9%97%B4 )

```
module.exports = {
  themeConfig: {
    record: '浙ICP备2022008289号-1',
    recordLink: 'http://beian.miit.gov.cn',
    cyberSecurityRecord: '浙公网安备 33011002016354号',
    cyberSecurityLink: 'http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=33011002016354',
  }
}

```


---


### 2.3 插件 

参考 [插件广场]( https://vuepress-theme-reco.recoluan.com/views/other/recommend.html ) &ensp; [plugin-medium-zoom]( https://v1.vuepress.vuejs.org/zh/plugin/official/plugin-medium-zoom.html#%E5%AE%89%E8%A3%85 )  &ensp; [medium-zoom]( https://vuepress-community.netlify.app/zh/plugins/medium-zoom/#%E5%AE%89%E8%A3%85 )

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

---


> 默认随机展示名人名句

参考 [vuepress-plugin-boxx]( https://github.com/zpj80231/vuepress-plugin-boxx )

```
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

```
npm install

在文档标题前一行添加 <Boxx/>


```

---

> 主要命令

```sh
# 启动 
npm run dev

# 打包
npm run build

# 部署
npm run deploy

```

---

