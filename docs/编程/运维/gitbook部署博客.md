# gitbook部署博客

## 基础命令 

```sh
GitBook可以看作个人的一本电子书。它用MarkDown编写，可以自动生成网页或者PDF等，生成网页可以通过插件配置目录和搜索功能等。


安装	npm install gitbook-cli -g
验证	gitbook -V
安装依赖	gitbook install

运行	gitbook serve
打包	gitbook build
卸载	npm uninstall -g gitbook

```

```
开启github pages 
1、gitbook build生成_book目录，将_book改成docs
2、位置：Setting - GitHub Pages - Save  
3、选择目录docs
发布成功https://scott180.github.io/gitbook-calligraphy/


后续更新
1、运行gitbook serve
2、修改_book为docs
3、提交文件

```


```sh
版本
$ gitbook -V
CLI version: 2.3.2
GitBook version: 3.2.3

$ node -v
v14.17.5

$ npm -v
6.14.14


配置说明
https://www.gitbook.com
https://www.cnblogs.com/zhangycun/p/15099747.html

参考案例
https://www.cnblogs.com/fenggedainifei/p/15500749.html
https://blog.windrunner.me/tool/gitbook-tutorial.html
https://suerimn-1.gitbook.io/suerimn-s-blog/css3/yuan-su-ju-zhong

```

## 插件配置

```json
插件配置
book.json

{
    "title": "无为徐生",
    "language": "zh-hans",
    "links": {
        "sidebar": {
            "github": "https://github.com/scott180/gitbook-blog",
            "xushufa": "https://xushufa.cn"
        }
    },
    "plugins": ["-sharing", "sharing-plus", "lightbox", "page-toc-button","expandable-chapters"],
    "pluginsConfig": {
        "sharing": {
            "all": []
        },
        "page-toc-button": {
            "maxTocDepth": 2,
            "minTocSize": 2
        }
    }

}


插件说明：
sharing ：右上角分享
lightbox ：点击打开图片
page-toc-button ：悬浮目录
expandable-chapters ：左侧目录折叠


```

```json
主题配置
https://www.npmjs.com/search?q=gitbook-theme

book.json
{
    "title": "书法练习",
    "language": "zh-hans",
    "links": {
        "sidebar": {
            "github": "https://github.com/scott180/calligraphy",
            "xushufa": "https://xushufa.cn"
        }
    },
    "plugins": ["theme-beauty"],
    "pluginsConfig": {
        "theme-beauty":{
            "search-placeholder":"输入关键字搜索", 
            "logo":"./logo.jpg", 
            "favicon": "./favicon.ico" 
        }
    }

}



```


## 常见错误

```java
gitbook -V 报错 
"cb.apply is not a function"
Installing GitBook 3.2.3
(node:6632) MaxListenersExceededWarning: Possible EventEmitter memory leak detected. 11 error listeners added to [TLSSocket]. Use emitter.setMaxListeners() to increase limit

参考 https://www.jianshu.com/p/6221330b36ba

解决方法
E:\ProgramFiles\node-v12.22.9-win-x64\node_global\node_modules\gitbook-cli\node_modules\npm\node_modules\graceful-fs\polyfills.js

将以下三行注释或删除
// fs.stat = statFix(fs.stat)
// fs.fstat = statFix(fs.fstat)
// fs.lstat = statFix(fs.lstat)

```

---

```
gitbook serve 报错 
Error: ENOENT: no such file or directory, stat 'E:\Project\github\gitbook-blog\_book\gitbook\gitbook-plugin-fontsettings\fontsettings.js'

Error: ENOENT: no such file or directory, stat 'D:\projects\github\test\gitbook\_book\gitbook\gitbook-plugin-livereload\plugin.js' no such file or directory, stat 'D:\projects\github\gitbook\_book\gitbook\gitbook-plugin-livereload\plugin.js'
 
 
参考 https://blog.csdn.net/yinlongfei_love/article/details/84636035

解决方法
C:\Users\Administrator\.gitbook\versions\3.2.3\lib\output\website\copyPluginAssets.js 中的112行内容
将confirm的true 需要修改为false

```

