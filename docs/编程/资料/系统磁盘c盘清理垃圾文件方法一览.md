# 系统磁盘c盘清理垃圾文件方法一览

## 1、工具清理

使用火绒安全、腾讯电脑管家或者360的垃圾清理。<br>
此方法效果还行，根据工具能力能清理掉几个G的垃圾文件。<br>

	

## 2、磁盘清理

右击C盘，选择【属性】--【常规】--【磁盘清理】。稍等片刻，选择要删除的文件，确定。<br>

![c1]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/pagefile/c1.jpg )


	
## 3、手动删除

推荐使用`Folder Size`工具，扫描磁盘，查看所有文件夹大小，自主判断垃圾文件，手动删除。<br>

![c2]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/pagefile/c2.jpg )

---

另：临时文件和日志文件可以直接删除 <br>

删除临时文件夹内的所有文件 <br>
`C:\Windows\Temp` <br>

删除日志文件夹LogFiles <br>
`C:\Windows\System32\LogFiles` <br>




## 4、清理受保护的操作系统文件
	
- [ ] 打开计算机或此电脑，点击【文件夹选项】--【查看】--【高级设置】，不勾选【隐藏受保护的操作系统文件(推荐)】。



- [ ] 清理 System Volume Information
	
- SystemVolumeInformation文件夹是一个隐藏的系统文件夹，"系统还原"工具使用该文件夹来存储它的信息和还原点。<br>
- System Volume Information怎么删除  参考 http://www.win7zhijia.cn/win10jc/win10_46768.html <br>

- 1.点击控制面板，搜索【创建还原点】。<br>
- 2.之后点击系统保护设置，选择你想删除的磁盘，点击配置。<br>
- 3.勾选禁用系统保护，删除此驱动的所有还原点。之后System Volume Information中的内容就会被自动清理了。<br>

![c3]( https://bitbucket.org/xu12345/document/raw/114a5f5c292cc412cd46304dc1d20cfda7c7a7f8/imgs/pagefile/c3.jpg )



- [ ] 清理 pagefile.sys

- pagefile.sys是虚拟内存文件，目地是使用一部分硬盘空间来充当内存使用。当一个程序请求的内存空间大于物理内存时，就需要pagefile.sys文件来提供较大的虚拟内存，从而满足程序对大内存的需求。<br>
- 一般来说，pagefile.sys是不可以删除的，但是我们可以将该文件从一个磁盘转移到另外的磁盘中。 参考	[blog.xushufa.cn]( https://blog.xushufa.cn/%E7%BC%96%E7%A8%8B/%E8%B5%84%E6%96%99/pagefile.sys%E6%9C%89%E5%87%A0%E5%8D%81%E4%B8%AAG%EF%BC%8C%E5%A4%AA%E5%A4%A7%E6%80%8E%E4%B9%88%E5%8A%9E.html ) <br>




