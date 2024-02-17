# nodejs与npm笔记

Node.js / vue / npm 相关笔记教程。


## nodejs安装

简单的说 Node.js 就是运行在服务端的 JavaScript。Node.js 是一个基于 Chrome JavaScript 运行时建立的一个平台。 <br/>

1、下载软件

```sh
官方  https://nodejs.org/en/download/ 
参考  https://blog.csdn.net/qq_44894359/article/details/89283325 
教程  https://www.runoob.com/nodejs/nodejs-tutorial.html 
```


2、环境变量

```java
NODE_HOME
D:\ProgramFiles\nodejs

PATH
%NODE_HOME%\;%NODE_HOME%\node_modules;%NODE_HOME%\node_global;

查看版本
node --version
npm -v

```


3、设置全局安装及缓存目录

```
npm config set prefix "D:\\ProgramFiles\\nodejs\node_global"
npm config set cache "D:\\ProgramFiles\\nodejs\node_cache"

查看配置列表
npm config ls

查看默认配置列表
npm config ls -l 

查看配置目录
npm root -g


设置镜像源
npm config set registry https://registry.npm.taobao.org --global
npm config set disturl https://npm.taobao.org/dist --global

```

```
安装模块
npm install -g vue
npm install express -g

简写
npm i vue  // i表示install

全局安装
npm i vue -g  // g表示global


使用淘宝定制的 cnpm (gzip 压缩支持) 命令行工具代替默认的 npm
npm install -g cnpm --registry=https://registry.npm.taobao.org

全局安装vue-cli
cnpm install --global vue-cli

-------------------------------

npm命令失效
https://blog.csdn.net/qq_39085705/article/details/81134453

找到  C:\Users\Super\.npmrc  （根据个人电脑找到 .npmrc文件）
删除 这个 .npmrc文件即可

重启 explorer.exe 即时生效环境变量
1，调出任务管理器：CTRL+ALT+DEL三键同时按；
2，在进程选项卡选中explorer.exe，点击下方的结束任务，确认；
3，在应用程序选项卡，最下面点新任务，输入explorer.exe，就是重启了explorer.exe.

-------------------------------

配置全局变量变量后，每次重启电脑node命令失效。
可将 默认目录下文件 C:\Users\x\.npmrc 复制到 安装目录 D:\ProgramFiles\nodejs\node_modules\npm\
重启电脑即可


```


## vue笔记

Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。 <br/>
Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。


[vue官网]( https://cn.vuejs.org/ ) &ensp; [教程]( https://www.runoob.com/vue2/vue-tutorial.html )


| git项目                                              | 地址    | demo  | 
| --------                                             | -----   | ----  |
| vue-manage-system          | [地址]( https://github.com/lin-xin/vue-manage-system )       | [演示]( https://lin-xin.gitee.io/example/work/#/dashboard ) |
| vue2-element-touzi-admin   | [地址]( https://github.com/wdlhao/vue2-element-touzi-admin ) | [演示]( http://www.jiouai.com/permission/index/index )      |




## npm发包

npm 全称为 Node Package Manager，是一个基于 Node.js 的包管理器，也是整个 Node.js 社区最流行、支持的第三方模块最多的包管理器。 <br/>

[npm使用介绍](  https://www.runoob.com/nodejs/nodejs-npm.html ) &ensp; [Webpack 入门教程]( https://www.runoob.com/w3cnote/webpack-tutorial.html )<br />

```js
在NPM上发包
https://blog.csdn.net/weixin_43275638/article/details/84587061
https://www.cnblogs.com/s1118/p/9355315.html



1、注册npm
https://www.npmjs.com/~xushufa


2、设置镜像源
确保npm的源是本身的源 https://registry.npmjs.org

npm config set registry https://registry.npmjs.org
通过 npm config get registry 进行查询

```

```
3、终端登录账号
npm adduser

npm login


问题：
must use TLS 1.2 or higher
npm notice Beginning October 4, 2021, all connections to the npm registry - including for package installation - must use TLS 1.2 or higher. You are currently using plaintext http to connect. Please visit the GitHub blog for more information: https://github.blog/2021-08-23-npm-registry-deprecating-tls-1-0-tls-1-1/ npm notice Beginning October 4, 2021, all connections to the npm registry - including for package installation - must use TLS 1.2 or higher. You are currently using plaintext http to connect. Please visit the GitHub blog for more information: https://github.blog/2021-08-23-npm-registry-deprecating-tls-1-0-tls-1-1/ npm ERR! code E426

https://blog.csdn.net/Ricky__H/article/details/120673036
从2021年10月4日开始，所有与npm网站和npm注册表的连接（包括软件包安装）必须使用TLS 1.2或更高版本。

将npm镜像地址设为https的地址
npm config set registry https://registry.npmjs.org

运行以下命令支持TLS 1.2
npm install -g https://tls-test.npmjs.com/tls-test-1.0.0.tgz


4、发布

npm init

npm publish


问题：
-- Enter one-time password from your authenticator app
== 需要在邮箱接收验证码

-- verbose stack Error: 403 Forbidden - PUT https://registry.npmjs.org/mynotes - Package name too similar to existing package my-notes; try renaming your package to '@xushufa/mynotes' and publishing with 'npm publish --access=public' instead
== package.json文件中的 name 配置是npm依赖包名，不能和npm平台下的依赖包名重名，否则发布失败。



每次更新 package.json 里面的version的版本号。
再重新运行 npm publish 发布就可以。


```


```
删除包
npm unpublish 包名@版本号  //指定删除包
npm unpublish 包名 --force //删除整个包


查看远程包
查看最新版本：
npm view xushufa version

查看所有的版本：
npm view xushufa versions
或者：
npm info xushufa

安装
npm i xushufa

配置淘宝镜像
npm config get registry
npm config set registry http://registry.npm.taobao.org/


```


```
cdn
https://www.jsdelivr.com/
https://cdn.jsdelivr.net/npm/xushufa-shu/yan/duo1-1000/0001大.jpg
https://cdn.jsdelivr.net/npm/xushufa-imgs/imgs/mingyue/2020/IMG_20210101_141310.jpg
https://cdn.jsdelivr.net/npm/xushufa-imgs@1.0.0/imgs/mingyue/2020/IMG_20210101_141310.jpg


地址
https://www.npmjs.com/~xushufa
https://www.npmjs.com/package/x-mynotes


```


---


