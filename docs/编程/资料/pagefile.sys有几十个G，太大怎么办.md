# pagefile.sys有几十个G，太大怎么办？

## 说明

pagefile.sys是虚拟内存文件，目地是使用一部分硬盘空间来充当内存使用。当一个程序请求的内存空间大于物理内存时，就需要pagefile.sys文件来提供较大的虚拟内存，从而满足程序对大内存的需求。 <br />
一般来说，pagefile.sys是不可以删除的，但是我们可以将该文件从一个磁盘转移到另外的磁盘中。 <br />

参考&ensp; [pagefile.sys怎么删除]( https://www.cnblogs.com/tianma3798/p/4846196.html ) &ensp; [pagefiles.sys文件的调整与删除]( https://blog.csdn.net/weixin_44014976/article/details/102808430 ) <br />
网址&ensp; [blog]( https://xushufa.cn ) &ensp;  [gitlab]( https://gitlab.com/xuyq123/mynotes )


## 文件移动

将pagefile.sys从C盘移到E盘。

---

1、打开控制面板的系统设置。 &ensp; [图解]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/pagefile/1.jpg )

![1]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/pagefile/1.jpg )

---

2、选中性能选项-高级-更改。 &ensp; [图解]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/pagefile/2.jpg )

![2]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/pagefile/2.jpg )

---

3、选中c盘，选择无分页文件，点击设置。注意：需要取消选中“自动管理所有驱动器的分页文件大小”。 &ensp; [图解]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/pagefile/3.jpg )

![3]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/pagefile/3.jpg )

---

4、选择E盘，选择系统管理的大小，点击设置，确定。 &ensp; [图解]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/pagefile/4.jpg )

![4]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/pagefile/4.jpg )

---

5、重启计算机。 &ensp; [图解]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/pagefile/5.jpg )

![5]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/pagefile/5.jpg )

---

## 查看文件大小

在工具-文件夹选项取消选中“隐藏受保护的操作系统文件（推荐）”，即可查看pagefile.sys文件大小。 <br />
选中“隐藏受保护的操作系统文件（推荐）”，即恢复正常。

---

1、选择工具-文件夹选项 &ensp; [图解]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/pagefile/111.jpg )

![111]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/pagefile/111.jpg )

---

2、查看-取消选中“隐藏受保护的操作系统文件（推荐）”，警告弹窗选择“是” &ensp; [图解]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/pagefile/222.jpg )

![222]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/pagefile/222.jpg )

---

3、确定 &ensp; [图解]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/pagefile/333.jpg )

![333]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/pagefile/333.jpg )

---

4、查看pagefile.sys文件大小 &ensp; [图解]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/pagefile/444.jpg )

![444]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/pagefile/444.jpg )

---



