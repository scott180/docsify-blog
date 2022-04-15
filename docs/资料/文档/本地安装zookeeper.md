# 本地安装zookeeper

## 1、 下载zookeeper    

[官网]( https://downloads.apache.org/zookeeper/ ) &ensp; [zookeeper-3.5.7]( https://download.csdn.net/download/xu180/12303347 )

```
从目前的最新版本3.5.5开始，带有bin名称的包才是我们想要的下载可以直接使用的里面有编译后的二进制的包，而之前的普通的tar.gz的包里面是只是源码的包无法直接使用。
如 apache-zookeeper-3.6.3-bin.tar.gz 是安装包。apache-zookeeper-3.6.3.tar.gz 是源码。

```

*******

## 2、 建立文件夹、改名

解压后，建立data和log目录。进入conf目录，有一个zoo_sample.cfg文件，将其重命名为zoo.cfg，然后打开，在最后添加

`dataDir=E:\ProgramFiles\apache-zookeeper-3.5.7-bin\data`

`dataDirLog=E:\ProgramFiles\apache-zookeeper-3.5.7-bin\log`

注释 `#dataDir=/tmp/zookeeper`


设置环境变量
`ZOOKEEPER_HOME=E:\ProgramFiles\apache-zookeeper-3.5.7-bin`

在path后面加上  `ZOOKEEPER_HOME%\bin;`

*******

## 3、启动
进入bin目录双击`zkServer.cmd`即可开启zookeeper本地服务


*******

```
参考
https://blog.csdn.net/nangu0673/article/details/82587014
https://blog.csdn.net/qq279862451/article/details/79083522
https://www.cnblogs.com/zhoading/p/11593972.html

```
