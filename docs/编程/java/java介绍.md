# java介绍

## 1、简介

```java
Java是一门面向对象编程语言，1990年代初由詹姆斯·高斯林等人开发出Java语言的雏形，最初被命名为Oak，后随着互联网的发展，经过对Oak的改造，1995年5月Java正式发布。

Java具有简单性、面向对象、分布式、健壮性、安全性、平台独立与可移植性、多线程、动态性等特点。Java可以编写桌面应用程序、Web应用程序、分布式系统和嵌入式系统应用程序等。
```

---

## 2、安装

> JDK下载

> Java Development Kit (JDK) 是Sun公司（已被Oracle收购）针对Java开发员的软件开发工具包。自从Java推出以来，JDK已经成为使用最广泛的Java SDK（Software development kit）。

- [ ] jdk来自[官网]( http://www.oracle.com/technetwork/java/archive-139210.html ) ，这些软件有三种格式：tar.gz、zip、bin
- 1、zip是windoxs软件，解压后会有exe格式的jdk软件，直接安装即可。
- 2、tar.gz是linux软件，需要用 `tar -zxvf xx.tar.gz` 解压。
- 3、bin也是linux软件，需解压：
   - 添加执行权限 
    `chmod u+x jdk-6u45-linux-x64.bin`
   - 解压 
    `./jdk-6u45-linux-x64.bin`
   
- [ ] 软件名称里含有x64则是64位软件，32位为则没有。如下：
- `jdk-8u162-windows-x64.zip`  `jdk-8u162-linux-x64.tar.gz`  是64位软件
- `jdk-8u72-windows-i586.zip`  `jdk-8u72-linux-i586.tar.gz`  是32位软件


| 云盘链接                                                      | 密码 |
| --------------------------------                              | ---  |
| [jdk6]( https://pan.baidu.com/s/1z3p1DecyBVugP7cECIupyg )     | 829h |
| [jdk7]( https://pan.baidu.com/s/17ik9x-g3RkYEu6vah9CZVw )     | muvr |
| [jdk8]( https://pan.baidu.com/s/1MT8zldLnH9PuZsVR77DEAw )     | mv5i |
| [jdk9]( https://pan.baidu.com/s/1SMGJqedJKR3hULrpWn4eLA )     | hai6 |
| [jdk10]( https://pan.baidu.com/s/1SHA7XNoPxBdOkaed3cunow )    | hgyn |

---

```java
java环境变量配置    注意：环境变量中都是英文符号，结尾以英文分号;结束
创建  JAVA_HOME     C:\Program Files\Java\jdk1.8.0_162
添加  Path          %JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;
创建  CLASSPATH     .;%JAVA_HOME%\lib;%JAVA_HOME%\lib\tools.jar;

验证 java -version

```
