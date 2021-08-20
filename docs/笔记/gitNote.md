## git笔记


> [gitee]( https://gitee.com/xy180/MyNotes )   [github]( https://github.com/scott180 )   


------------------------

### 1、基础操作

#### 1.1、常用命令


[git知识大全]( https://gitee.com/help/articles/4122 )
```vb
提交代码
 git status -sb
 git add .
 git commit -m "fix"
 git push origin dev_20190510001

查询状态
git status

查看日志
 git log --stat
 git reflog --date=iso

建立分支
 git status -sb
 git checkout -B dev_20190510001
 git push
 git push --set-upstream origin dev_20190510001

```

```basic
展示文件  ls
删除文件  git rm test2.txt 
撤销操作  git checkout -- test.txt

添加文件到缓存：
 git add test.txt    单个文件
 git add .           当前目录全部文件

提交(包含注释)到本地仓库： 
 git commit -m '第一次版本提交' 


上传文件到远程仓库：
 git push origin [本地分支名]:[远端分支名]  
 当然如果你的本地分支名和远端分支名是一样的，那么就只需要git push origin [分支名称]就可以了。 
    git push 

恢复最近版本文件
 git fetch
 git reset --hard origin/master   //恢复到远程仓库，删除git add和commit的文件

 
git pull   拉取 从远程拉取最新版本到本地，并自动合并 merge
git fetch  获取 从远程获取最新版本到本地，不会自动合并 merge，需要手动合并
git merge  合并 使用fetch 可以在merge之前可以看清楚更新情况，再决定是否合并。
git merge --abort  中止合并



git 一个分支完全覆盖另一个分支 ，如下：dev_20210729覆盖develop
$ git checkout develop
$ git reset --hard dev_20210729
$ git push origin develop --force


不允许推送  You are not allowed to force push code to a protected branch on this project
"Settings" -> "Repository" -> scroll down to "Protected branches".

```


```haskell
git回滚之前的版本  http://www.cnblogs.com/yu-hailong/p/10681905.html
git log   查询日历记录
git reset --hard 5fa86ae3758e7e2a86825452977da40f34b6dd58   回滚到之前的版本
git push origin HEAD --force   强制提交

还原本地文件 git reset -hard 

 git reset —soft + 版本号
  回退到某个版本，只回退了commit的信息，不会改变已经修改过的代码。
  git reset --soft HEAD^
  
 git reset —hard + 版本号
     git reset --hard orgin
  彻底回退到某个版本，本地的代码也会改变上一个版本内容。

 #本地仓库回退到某个版本    git reflog --date=iso
 git reset -–hard bae168  
 
 #本地仓库回退到上一次提交  
 git reset –-hard 
 
 git reset --hard HEAD

 回退到当前版本的前一个版本
 git reset --hard HEAD^

 回退到当前版本的上上个版本
 git reset --hard HEAD^^

 回退到当前版本之前的100个版本
 git reset --hard HEAD~100
 

 丢弃本地提交，强制回到线上最新版本
 git fetch --all
 git reset --hard origin/你需要下拉的分支(默认master) 
 git fetch

 
```


#### 1.2、配置

```vb
vi /etc/ssh/sshd_config

查看版本：
 git --version

查看配置：
 git config --list

设置配置：
 git config --global pack.windowMemory 1024m

 git config --global pack.packsizelimit 1g
 
 git config --global http.postBuffer 1g

删除配置：
 git config --global --unset pack.deltacachesize
 
```


#### 1.3、分支

```sql
https://edu.aliyun.com/jiaocheng/1834?spm=5176.11182473.menu.7.k6ksTN

创建分支命令（复制当前分支下文件）：
 git branch (branchname)
 
提交分支到仓库命令：
 git push origin (branchname)

切换分支命令:
 git checkout (branchname)
 
列出分支基本命令：
 git branch -a

删除分支命令：
 git branch -d (branchname)
 
创建新分支并立即切换到该分支下
 git checkout -b newtest
  有同名分支会提示错误
  
强制创建新分支，并且会覆盖原来存在的同名分支
 git checkout -B new_branch


设置本地分支关联远程分支
 git push --set-upstream origin 
 
 
合并分支：
 git checkout master
 git merge test   // test分支合并到master分支
 git push origin master
 

查看分支      git branch -a
删除本地分支  git branch -D branch-name
删除远程分支  git push origin --delete branch-name
     
     
查看当前配置有哪些远程仓库
 git remote -v
 

彻底删除文件--会删除对应提交记录（包含正常文件的提交历史）
git filter-branch --force --index-filter 'git \
rm --cached --ignore-unmatch test彻底删除.txt' \
--prune-empty --tag-name-filter cat -- --all
git push --all --force
 
```

```vb
修改分支名称 https://www.jianshu.com/p/cc740394faf5

a. 重命名远程分支对应的本地分支
git branch -m oldName newName

b. 删除远程分支
git push --delete origin oldName

c. 上传新命名的本地分支
git push origin newName

d.把修改后的本地分支与远程分支关联
git branch --set-upstream-to origin/newName


```

------------------------

### 2、高级操作

#### 2.1、查看文件状态及日志

```vb
 
git status 命令用于查看项目的当前状态。
git status -s  查看详情

 
git diff   
git diff --stat

执行 git diff 来查看执行 git status 的结果的详细信息。

git diff 命令显示已写入缓存与已修改但尚未写入缓存的改动的区别。
git diff 有两个主要的应用场景。
 尚未缓存的改动：git diff 
 查看已缓存的改动： git diff --cached 
 查看已缓存的与未缓存的所有改动：git diff HEAD 
 显示摘要而非整个 diff：git diff --stat

退出git diff  : q


查看全部分支的已经commit但是没有push的：
 git log --branches --not --remotes
 
 git cherry -v   只能查看未传送提交的描述/说明
 git log master ^origin/master  则可以查看未传送提交的详细信息
 

查看全部分支的全部的最近的commit：
 git log --branches --not --remotes --simplify-by-decoration --decorate --online

查看日志
 git log
 git log --oneline -3    当前分支的最近三次提交记录
 
 git log --stat     https://www.cnblogs.com/fancyop/p/Git_Log.html
 用来显示每次提交的下面列出所有被修改过的文件、有多少文件被修改了以及被修改过的文件的哪些行被移除或是添加了。 在每次提交的最后还有一个总结。
 
 git reflog              所有分支的记录
 git reflog --date=iso 
```


#### 2.2、stash暂存

```vb
git stash:       备份当前的工作区的内容，从最近的一次提交中读取相关内容，让工作区保证和上次提交的内容一致。同时，将当前的工作区内容保存到Git栈中。

git stash pop:   从Git栈中读取最近一次保存的内容，恢复工作区的相关内容。由于可能存在多个Stash的内容，所以用栈来管理，pop会从最近的一个stash中读取内容并恢复。

git stash list:  显示Git栈内的所有备份，可以利用这个列表来决定从那个地方恢复。

git stash clear: 清空Git栈。此时使用gitg等图形化工具会发现，原来stash的哪些节点都消失了。


git stash apply  恢复暂存之后不删除暂存
git stash pop   恢复暂存之后删除暂存
git stash drop   从Git栈删除最旧的一个暂存


```

#### 2.3、修改注释信息

```
git使用amend选项提供了最后一次commit的反悔。但是对于历史提交呢，就必须使用rebase了。 

git rebase -i HEAD~3 
表示要修改当前版本的倒数第三次状态。 
这个命令出来之后，会出来三行东东： 

pick:******* 

pick:******* 

pick:******* 

如果你要修改哪个，就把那行的pick改成edit，然后退出。 


这时通过git log你可以发现，git的最后一次提交已经变成你选的那个了，这时再使用： 

git commit --amend 

来对commit进行修改。     :wq 

修改完了之后，要回来对不对？ 

使用git rebase --continue 
git rebase --abort

OK，一切都搞定了。 
  
https://xiewenbo.iteye.com/blog/1285693

```

```
恢复已删除分支
https://blog.csdn.net/changerzhuo_319/article/details/81133533    
git reflog --date=iso
git checkout -b reback_remove_branch ddd94a4

```

------------------------

### 3、问题

#### 3.1、github添加ssh公钥

``` vb
 
## github添加ssh公钥  git clone

打开 git bash

ssh-keygen -t rsa

cat ~/.ssh/id_rsa.pub

添加到 https://github.com/settings/keys   依次点击"Setting -> SSH Keys"->"New SSH key"

ssh -T git@github.com

git clone git@github.com:scott180/MyNotes.git


```


#### 3.2、提交及更新失败

``` java
用git pull来更新代码的时候，遇到了下面的问题：

error: Your local changes to the following files would be overwritten by merge:  
    xxx/xxx/xxx.php  
Please, commit your changes or stash them before you can merge.  
Aborting


https://www.cnblogs.com/xd502djj/p/6973477.html

解决方法：
   
方法1、暂存本地文件，下载最新代码。恢复本地修改的代码，修改冲突的文件，提交代码。      
git stash           //暂存
git pull			//下载最新代码
git stash pop		//恢复暂存文件

git status                      //查询状态
git add xxx/xxx/Test.java       //提交文件   不能使用全部提交:（git add .）
git commit -m "fix"             //注释
git push					    //提交文件


方法2、 放弃本地修改文件，下载最新代码
git reset --hard
git pull

```


```vb

GIT拉取代码的时候提示AUTHENTICATION FAILED FOR []

解决方法：
输入 git config --global credential.helper store  
然后 git pull  输入账号密码

或者清除密码
git config --system --unset credential.helper

```

```sql

在git（小乌龟）向github远程推送（push）文件是会报一个异常 no supported authentication methods avaiable         
https://blog.csdn.net/Maxiao1204/article/details/81476618

解决方法：
因为git（小乌龟）和Git的冲突我们需要把乌龟git设置改正如下。

找到TortoiseGit--》Settings--》Network

将SSH client指向 D:\git\usr\bin\ssh.exe （我的Git工具安装在d盘）

这里更改ssh 路径的时候，要把上面的“使用代理服务器” 勾打上，点击应用，再确定

```
