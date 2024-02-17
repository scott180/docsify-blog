# gitlab、github绑定自定义域名 

<br />

`网址` &ensp; [xushufa]( https://xushufa.cn ) &ensp; [blog]( https://scott180.github.io/vuepress-blog )

## github绑定自定义域名 

```
示例：
github项目 https://github.com/scott180/docsify-blog
静态页面   https://scott180.github.io/docsify-calligraphy
自定义域名 https://docsify-calligraphy.xushufa.cn
```


- 1、先将github pages 静态网页发布成功，如  https://scott180.github.io/docsify-calligraphy

- 2、在域名管理中心，添加记录，如 <br/>
github静态网页 https://scott180.github.io/docsify-calligraphy <br/>
自定义域名     https://docsify-calligraphy.xushufa.cn <br/>

```
则在DNS解析--我的域名--添加记录的参数是：
主机记录：docsify-calligraphy
记录类型：CNAME
线路类型：默认
记录值：scott180.github.io
TTL: 600
```

- 3、在github Setting - GitHub Pages - Custom domain 输入 docsify-blog.xushufa.cn

```
稍后即可访问 https://docsify-calligraphy.xushufa.cn 
浏览器地址栏可能会提示“不安全” ， 勾选  Enforce HTTPS ，稍后再访问。
```

![1]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/other/custom_domain1.png )

---

![2]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/other/custom_domain2.png )

---



## gitlab绑定自定义域名 

gitlab绑定自定义域名，比github复杂一点。需要申请SSL证书且修改域名记录。
官方参考 [custom_domains]( https://docs.gitlab.com/ee/user/project/pages/custom_domains_ssl_tls_certification/index.html#4-verify-the-domains-ownership )

```
示例：
gitlab项目 https://gitlab.com/xuyq123/mkdocs-blog
静态页面   https://xuyq123.gitlab.io/mkdocs-blog
自定义域名 https://mkdocs-blog.xushufa.cn
```


- 1、先将gitlab pages 静态网页发布成功，如  https://xuyq123.gitlab.io/mkdocs-blog

- 2、在域名管理中心申请免费SSL证书 

```	
绑定域名：mkdocs-blog.xushufa.cn
算法选择：RSA算法
```

- 3、在gitlab pages  -- New Pages Domain  <br/>
输入域名 mkdocs-blog.xushufa.cn <br/>

- 4、在域名管理中心--添加记录

```
主机记录：mkdocs-blog
记录类型：TXT
线路类型：默认
记录值：gitlab-pages-verification-code=ba841cb.....
TTL: 600

注：记录值就是 上一步Pages Domain的 Verification status 的 _gitlab-pages-verification-code.mkdocs-blog.xushufa.cn TXT 后面的值
```

![3]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/other/custom_domain3.png )

- 5、在gitlab pages  -- Pages Domain -- Verification status  <br/>
点击 Verified 如果有对号 说明域名绑定了。 <br/>
但此时还是不能访问。 <br/>


- 6、在域名管理中心--添加记录，修改数据

```
主机记录：mkdocs-blog     // 不变
记录类型：CNAME           // 从TXT 改为 CNAME
线路类型：默认
记录值：xuyq123.gitlab.io // 输入自己gitlab的静态账号   
TTL: 600
```

- 7、在gitlab pages  -- Pages Domain 会报错： <br/>
Something went wrong while obtaining the Let's Encrypt certificate <br/>
需要重试  Retry

```
稍后即可访问 https://mkdocs-blog.xushufa.cn
浏览器地址栏可能会提示“不安全” ， 勾选  Force HTTPS (requires valid certificates) ，点击“Save changes” ，稍后再访问。
```



---

