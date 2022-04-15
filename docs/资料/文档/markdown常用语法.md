# Markdown常用语法

## 1 Markdown简介
Markdown是一种可以使用普通文本编辑器编写的标记语言，通过简单的标记语法，它可以使普通文本内容具有一定的格式。

Markdown的语法简洁明了、学习容易，而且功能比纯文本更强，因此有很多人用它写博客。世界上最流行的博客平台WordPress和大型CMS如Joomla、Drupal都能很好的支持Markdown。
完全采用Markdown编辑器的博客平台有Ghost和Typecho。

用于编写说明文档，并且以“README.md”的文件名保存在软件的目录下面。

## 2 Markdown在线编辑

markdown在线编辑 <br/>
作业部落  https://www.zybuluo.com/mdeditor <br/>
马克飞象  https://maxiang.io/ <br/>
菜鸟工具  http://c.runoob.com/front-end/712 <br/>
Marked    https://marked.js.org/demo/ <br/>
gitlab    https://gitlab.com/-/ide/project/gitlab-org/gitlab/edit/master/-/doc/user/markdown.md <br/>


markdown软件 <br/>
typora	https://www.typora.io/ <br/>
Dillinger https://dillinger.io/ <br/>
https://blog.csdn.net/davidhzq/article/details/100815332 <br/>


## 3 Markdown常用语法

### 3.1 标题
两种形式：  
1）使用`=`和`-`标记一级和二级标题。
> 一级标题   
> `=========`   
> 二级标题    
> `---------`

效果：
> 一级标题   
> =========   
> 二级标题
> ---------  

2）使用`#`，可表示1-6级标题。
> \# 一级标题   
> \## 二级标题   
> \### 三级标题   
> \#### 四级标题   
> \##### 五级标题   
> \###### 六级标题    

效果：
> # 一级标题   
> ## 二级标题   
> ### 三级标题   
> #### 四级标题   
> ##### 五级标题   
> ###### 六级标题

### 3.2 段落
段落的前后要有空行，所谓的空行是指没有文字内容。若想在段内强制换行的方式是使用**两个以上**空格加上回车（引用中换行省略回车）。

### 3.3 区块引用
在段落的每行或者只在第一行使用符号`>`,还可使用多个嵌套引用，如：
> \> 区块引用  
> \>> 嵌套引用  

效果：
> 区块引用  
>> 嵌套引用

### 3.4 代码区块
代码区块的建立是在每行加上4个空格或者一个制表符（如同写代码一样）。如    
普通段落：

void main()    
{    
    printf("Hello, Markdown.");    
}    

代码区块：

    void main()
    {
        printf("Hello, Markdown.");
    }

**注意**:需要和普通段落之间存在空行。

### 3.5 强调
在强调内容两侧分别加上`*`或者`_`，如：
> \*斜体\*，\_斜体\_    
> \*\*粗体\*\*，\_\_粗体\_\_

效果：
> *斜体*，_斜体_    
> **粗体**，__粗体__

### 3.6 列表
使用`·`、`+`、或`-`标记无序列表，如：
> \-（+\*） 第一项
> \-（+\*） 第二项
> \- （+\*）第三项

**注意**：标记后面最少有一个_空格_或_制表符_。若不在引用区块中，必须和前方段落之间存在空行。

效果：
> + 第一项
> + 第二项
> + 第三项

有序列表的标记方式是将上述的符号换成数字,并辅以`.`，如：
> 1 . 第一项   
> 2 . 第二项    
> 3 . 第三项    

效果：
> 1. 第一项
> 2. 第二项
> 3. 第三项

### 3.7 分割线
分割线最常使用就是三个或以上`*`，还可以使用`-`和`_`。

### 3.8 链接
链接可以由两种形式生成：**行内式**和**参考式**。    
**行内式**：
> \[scott180的笔记\]\(https:://github.com/scott180/MyNotes\)。

效果：
> [scott180的笔记](https:://github.com/scott180/MyNotes)。

**参考式**：
> \[scott180的笔记1\]\[1\]    
> \[scott180的笔记2\]\[2\]    
> \[1\]:https:://github.com/scott180/MyNotes    
> \[2\]:https:://github.com/scott180/MyNotes    

效果：
> [scott180的笔记1][1]    
> [scott180的笔记2][2]

[1]: https:://github.com/scott180/MyNotes
[2]: https:://github.com/scott180/MyNotes

**注意**：上述的`[1]:https:://github.com/scott180/MyNotes`不出现在区块中。

### 3.9 图片
添加图片的形式和链接相似，只需在链接的基础上前方加一个`！`。

> \[公众号\]\(https://codechina.csdn.net/xu180/document/-/raw/master/imgs/weixin/wuweixusheng.png\)。

效果：
![公众号](https://codechina.csdn.net/xu180/document/-/raw/master/imgs/weixin/wuweixusheng.png)

### 3.10 反斜杠`\`
相当于**反转义**作用。使符号成为普通符号。

### 3.11 符号'`'
起到标记作用。如：
>\`ctrl+a\`

效果：
>`ctrl+a` 


### 3.12 空格符号

```
半角空格: &ensp;
全角空格: &emsp;
```

---

---

```
文章参考
https://www.baike.com/wikiid/5652766406012153832?prd=mobile&view_id=38rg5xc426g000#catalog_1
https://github.com/younghz/Markdown/blob/master/README.md
https://www.cnblogs.com/hnrainll/p/3514637.html

```

