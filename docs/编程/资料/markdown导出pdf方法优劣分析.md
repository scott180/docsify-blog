# markdown导出pdf方法优劣分析

<br />

`md2pdf`  `pandoc`  `Typora`  `MarkText`  `PDFPatcher`  --20220903

> [xushufa]( https://web.xushufa.cn ) &ensp; [blog]( https://blog.xushufa.cn ) &ensp; [gitlab]( https://gitlab.com/xuyq123/mynotes ) &ensp; [gitee]( https://gitee.com/xy180/MyNotes ) 



## 1 概述

### 1.1 目标

markdown导出pdf期望达到理想效果：
- **内容不丢失**，标题、正文、高亮、代码块等内容不会丢失，即使是代码块也能无损导出。
- **格式不变形**，字体、样式与markdown看到的基本一致。
- **有图片**，markdown的图片不论是网络链接还是base64都能正常导出。
- **有书签**，markdown的一二三四级标题就是pdf的书签。
- **操作简单**，一键导出，高效方便。

---

### 1.2 说明

使用了`Typora`，`作业部落`，`小书匠`，`马克飞象`，`浏览器打印`，`pandoc`，`xelatex`，`wkhtmltopdf`，`vscode`，`MarkText`等等许多方法将markdown导出pdf。有的收费，有的加水印，有的丢文字，有的丢图片，最后发现：
- 操作markdown文件，使用`Typora`最方便。
- 导出markdown为pdf，`MarkText`搭配`PDFPatcher`最优秀。

---

### 1.3 相关

markdown相关操作，格式转换与项目布署。


> html转markdown文件

- pandoc 转化  `pandoc -f html -t markdown -o 202010.md 202110.html`
- turndown `Convert HTML into Markdown with JavaScript.`  https://mixmark-io.github.io/turndown/

> markdown文件部署项目

- gitlab、github、gitee布署`mkdocs`主题仓库
- git平台`docsify`布署markdown文件
- `vuepress`构建项目 https://blog.xushufa.cn
- `gitbook`部署博客  https://scott180.github.io/gitbook-blog




## 2 工具

### 2.1 Typora

Typora可以说是markdown的最佳拍档，可是免费beta版之后，1.0版Typora开始收费。<br/>
最后一个免费版本是0.11.8，安装或使用这个版本会提示软件过期，不可使用。<br/>
但低版本可以正常使用Typora，如0.9.86版。<br/>

**Typora + pandoc 可以将markdown导出pdf，可惜导出文件会丢失图片。**<br/>
**Typora导出html，再通过浏览器导出pdf，不丢失图片，但是文档格式稍有变化，且没有书签。**<br/>

Typora + pandoc 导出pdf时，可以将图片链接换成base64编码，这样不会丢失图片。<br/>
但是这样做文档内容会特别大，一张图片的base64编码可能有几万字。<br/>
且文档的base64图片编码多了，使用Typora报错：该文件过大，因此无法在Typora中呈现。<br/>

>Typora导出pdf
![1Typora]( https://md.xushufa.cn/gitimg/document/imgs/md2pdf/1Typora.jpg )

---

### 2.2 pandoc

Pandoc 是一个由 John MacFarlane 开发的通用文档转换工具，可以支持大量标记语言之间的格式转换，例如 Markdown 、Microsoft Word、PowerPoint、 Jupyter Notebook、HTML、PDF、LaTeX、Wiki、EPUB 格式之间的相互转换。官方称之为该领域中的“瑞士军刀”。<br/>
参考：<br/>
https://blog.csdn.net/horses/article/details/108536784 <br/>
https://github.com/jgm/pandoc/releases/tag/2.19.2 <br/>

---

md->docx->PDF 

pandoc -s example.md -o target.docx --reference-doc reference.docx <br/>
https://www.trickyedecay.me/2020/08/04/use-pandoc-to-convert-markdown-to-docx/ <br/>

---

`pandoc text.md -o text.docx` <br/>
`pandoc 无为徐生.md -o 无为徐生_pandoc.docx` <br/>

https://www.jianshu.com/p/52cbee87a45a <br/>

样式相当失真，导出的pdf文件与md文件差异较大。 <br/>

---

Pandoc+TeXLive实现Markdown转PDF <br/>
https://zhuanlan.zhihu.com/p/35813989 <br/>
https://www.jianshu.com/p/1d02fc5121c2 <br/>

`$ fc-list :lang=zh`

`pandoc 无为徐生.md -o 无为徐生_xelatex.pdf --pdf-engine=xelatex -V CJKmainfont='KaiTi'`

《无为徐生_xelatex.pdf》丢失图片，没有书签，格式变化。

---

### 2.3 wkhtmltopdf

`pandoc --pdf-engine=wkhtmltopdf --metadata pagetitle="无为徐生"  无为徐生.md -o 无为徐生_wkhtmltopdf.pdf` <br/>
《无为徐生_wkhtmltopdf.pdf》样式比较失真。 <br/>

https://cloud.tencent.com/developer/article/1740818?from=article.detail.1854429 <br/>
https://cloud.tencent.com/developer/article/1854429 <br/>

---

### 2.4 vscode

在`Visual Studio Code`安装`Markdown Preview Enhanced` 插件 <br/>

vscode中markdown导出pdf <br/>
https://blog.csdn.net/weixin_43352942/article/details/89950779 <br/>
https://shd101wyy.github.io/markdown-preview-enhanced/#/zh-cn/toc <br/>

C:\Users\Administrator\AppData\Local\Google\Chrome\Application\chrome.exe <br/>

《无为徐生_vscode.pdf》丢失图片，没有书签，格式变化。 <br/>

>vscode导出pdf
![2vscode]( https://md.xushufa.cn/gitimg/document/imgs/md2pdf/2vscode.jpg )

---

### 2.5 MarkText

MarkText是一个简单开源的 Markdown 编辑器。 https://github.com/marktext/marktext <br/>
导出的pdf文件相当不错。 <br/>

《无为徐生_MarkText.pdf》文件样式内容图片基本没有变化，很好。 <br/>
就是没有书签，但可以使用`PDFPatcher PDF补丁丁` 为pdf文件加上书签。 <br/>

---

工具箱--处理PDF文件--自动生成书签 <br/>
《无为徐生_MarkText.xml》即为pdf书签 <br/>

打开文件--导入书签--保存PDF文件 <br/>
《无为徐生_MarkText_PDFPatcher.pdf》  内容不丢失 | 格式不变形 | 有图片 | 有书签 | 操作简单   good <br/>

---

>MarkText导出pdf
![3MarkText]( https://md.xushufa.cn/gitimg/document/imgs/md2pdf/3MarkText.jpg )

---

>PDFPatcher生成书签
![4PDFPatcher]( https://md.xushufa.cn/gitimg/document/imgs/md2pdf/4PDFPatcher.jpg )

>PDFPatcher导入书签
![5PDFPatcher]( https://md.xushufa.cn/gitimg/document/imgs/md2pdf/5PDFPatcher.jpg )

>PDF书签打开
![6PDFPatcher]( https://md.xushufa.cn/gitimg/document/imgs/md2pdf/6PDFPatcher.jpg )

---

### 2.6 评分

| 评分        | 内容不丢失 | 格式不变形 | 有图片 | 有书签 | 操作简单 | 得分        |
| ----------  | ---------- | ---------- | ------ | ------ | -------- | ----------- |
| Typora      |   √        |   √        |   ×    |   √    |   √      |  ★★★★   |
| pandoc      |   ○        |   ○        |   ○    |   √    |   ○      |  ★★★     |
| wkhtmltopdf |   ○        |   ○        |   ○    |   √    |   ○      |  ★★★     |
| vscode      |   √        |   ○        |   ×    |   √    |   ×      |  ★★☆     |
| MarkText    |   √        |   √        |   √    |   ○    |   √      |  ★★★★☆ |


| 网址        | 官网       | 下载       |
| ----------  | ---------- | ---------- |
| Typora      | https://typora.io/                   | https://typoraio.cn/                   |
| marktext    | https://marktext.app/                | https://github.com/marktext/marktext   |
| PDFPatcher  | https://www.cnblogs.com/pdfpatcher/  | https://github.com/wmjordan/PDFPatcher |

综上，`MarkText`四星半夺得魁首，`Typora`屈居亚席。总之： <br/>
操作markdown文件，使用`Typora`最方便。 <br/>
导出markdown为pdf，`MarkText`搭配`PDFPatcher`最优秀。 <br/>






