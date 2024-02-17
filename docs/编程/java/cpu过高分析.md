# cpu过高分析

<br />

> cpu  java  linux

`--20190325`

## 命令

```shell
[root@localhost ~]# top         					--查看cpu	 
[root@localhost ~]# top -p 6734 -H        			--观察该进程中所有线程的CPU占用
[root@localhost ~]# printf "%x\n" 6759    			--找出CPU消耗较多的线程id，转16进制
1a67
[root@localhost ~]# jstack 6734|grep 1a67 -A 30     --打印堆栈信息，提交开发分析处理


```


```shell
 [root@localhost ~]# top         --查看cpu	                                                    
	 6734 root      20   0 4557896 1.823g  10900 S 373.4 11.7 728:54.78 java                                                       
	 3207 root      20   0 3005436 858680   8156 S  24.9  5.3 350:39.55 mysqld                                                     
		1 root      20   0   57640   7524   2644 S   0.0  0.0   0:02.75 systemd                                                    
		2 root      20   0       0      0      0 S   0.0  0.0   0:00.08 kthreadd                                                   
		3 root      20   0       0      0      0 S   0.0  0.0   0:02.27 ksoftirqd/0                                                
		5 root       0 -20       0      0      0 S   0.0  0.0   0:00.00 kworker/0:0H                                               
		7 root      rt   0       0      0      0 S   0.0  0.0   0:00.02 migration/0                                                
		8 root      20   0       0      0      0 S   0.0  0.0   0:00.00 rcu_bh                                                     
		9 root      20   0       0      0      0 S   0.0  0.0   0:00.00 rcuob/0                                                    
	   10 root      20   0       0      0      0 S   0.0  0.0   0:00.00 rcuob/1                                                    
	   11 root      20   0       0      0      0 S   0.0  0.0   0:00.00 rcuob/2                                                    
	   12 root      20   0       0      0      0 S   0.0  0.0   0:00.00 rcuob/3                                                    
	   13 root      20   0       0      0      0 S   0.0  0.0   0:12.53 rcu_sched                                                  
	   14 root      20   0       0      0      0 S   0.0  0.0   0:03.63 rcuos/0                                                    
	   15 root      20   0       0      0      0 S   0.0  0.0   0:04.65 rcuos/1                                                    
	   16 root      20   0       0      0      0 S   0.0  0.0   0:04.78 rcuos/2                                                    
	   17 root      20   0       0      0      0 R   0.0  0.0   0:04.42 rcuos/3                                                    
	   18 root      rt   0       0      0      0 S   0.0  0.0   0:00.57 watchdog/0
	   
 
 [root@localhost ~]# top -p 6734 -H        --观察该进程中所有线程的CPU占用
	 6759 root      20   0 4557896 1.812g  10876 R 41.5 11.7  67:40.75 java                                                        
	 6764 root      20   0 4557896 1.812g  10876 R 39.2 11.7  67:57.70 java                                                        
	 6761 root      20   0 4557896 1.812g  10876 R 38.9 11.7  69:24.02 java                                                        
	 6760 root      20   0 4557896 1.812g  10876 R 37.9 11.7  67:31.72 java                                                        
	 6756 root      20   0 4557896 1.812g  10876 R 36.5 11.7  67:53.37 java                                                        
	 6758 root      20   0 4557896 1.812g  10876 R 36.5 11.7  68:01.22 java                                                        
	 6763 root      20   0 4557896 1.812g  10876 R 36.5 11.7  68:14.78 java                                                        
	 6762 root      20   0 4557896 1.812g  10876 R 35.2 11.7  67:48.78 java                                                        
	 6757 root      20   0 4557896 1.812g  10876 R 33.2 11.7  67:32.28 java                                                        
	 6755 root      20   0 4557896 1.812g  10876 R 30.9 11.7  70:03.97 java

```

--------------------------------------

```shell
[root@localhost ~]# printf "%x\n" 6759    				--找出CPU消耗较多的线程id，转16进制
1a67
 
[root@localhost ~]# jstack 6734|grep 1a67 -A 30			--打印堆栈信息，提交开发分析处理

"startJob_Worker-5" prio=10 tid=0x00007f15c4c07800 nid=0x1a67 runnable [0x00007f159cc74000]
   java.lang.Thread.State: RUNNABLE
	at java.lang.Class.getInterfaces(Native Method)
	at org.hibernate.intercept.FieldInterceptionHelper.extractFieldInterceptor(FieldInterceptionHelper.java:45)
	at org.hibernate.intercept.FieldInterceptionHelper.clearDirty(FieldInterceptionHelper.java:81)
	at org.hibernate.event.def.DefaultFlushEntityEventListener.isUpdateNecessary(DefaultFlushEntityEventListener.java:209)
	at org.hibernate.event.def.DefaultFlushEntityEventListener.onFlushEntity(DefaultFlushEntityEventListener.java:127)
	at org.hibernate.event.def.AbstractFlushingEventListener.flushEntities(AbstractFlushingEventListener.java:196)
	at org.hibernate.event.def.AbstractFlushingEventListener.flushEverythingToExecutions(AbstractFlushingEventListener.java:76)
	at org.hibernate.event.def.DefaultAutoFlushEventListener.onAutoFlush(DefaultAutoFlushEventListener.java:35)
	at org.hibernate.impl.SessionImpl.autoFlushIfRequired(SessionImpl.java:969)
	at org.hibernate.impl.SessionImpl.list(SessionImpl.java:1114)
	at org.hibernate.impl.QueryImpl.list(QueryImpl.java:79)
	at com.stech.jgy.impl.local.rich.local.JgyManager.getStudentAppointByTeacherAppointId(JgyManager.java:1929)
	at com.stech.webplus.jgy.action.Util.AppointThread.correctStudentAppointError(AppointThread.java:410)
	at com.stech.webplus.jgy.action.Util.AppointThread.checkThreadByHours(AppointThread.java:148)
	at sun.reflect.GeneratedMethodAccessor672.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:597)
	at org.springframework.util.MethodInvoker.invoke(MethodInvoker.java:276)
	at org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean$MethodInvokingJob.executeInternal(MethodInvokingJobDetailFactoryBean.java:260)
	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:86)
	at org.quartz.core.JobRunShell.run(JobRunShell.java:216)
	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:549)

"startJob_Worker-4" prio=10 tid=0x00007f15c47bf000 nid=0x1a66 runnable [0x00007f159cd75000]
   java.lang.Thread.State: RUNNABLE
	at java.lang.Class.getInterfaces(Native Method)
	at org.hibernate.intercept.FieldInterceptionHelper.extractFieldInterceptor(FieldInterceptionHelper.java:45)
	at org.hibernate.intercept.FieldInterceptionHelper.clearDirty(FieldInterceptionHelper.java:81)
	at org.hibernate.event.def.DefaultFlushEntityEventListener.isUpdateNecessary(DefaultFlushEntityEventListener.java:209)
[root@localhost ~]# 
```


## 实例 

```shell
================================================================================ 
[root@localhost ~]# jstack 6734|grep la4e -A 30
[root@localhost ~]# printf "%x\n" 6764
1a6c
[root@localhost ~]# jstack 6734|grep 1a6c -A 30
"startJob_Worker-10" prio=10 tid=0x00007f15c45c7000 nid=0x1a6c runnable [0x00007f159c76e000]
   java.lang.Thread.State: RUNNABLE
	at org.hibernate.property.BasicPropertyAccessor$BasicGetter.get(BasicPropertyAccessor.java:145)
	at org.hibernate.tuple.entity.AbstractEntityTuplizer.getPropertyValues(AbstractEntityTuplizer.java:256)
	at org.hibernate.tuple.entity.PojoEntityTuplizer.getPropertyValues(PojoEntityTuplizer.java:209)
	at org.hibernate.persister.entity.AbstractEntityPersister.getPropertyValues(AbstractEntityPersister.java:3581)
	at org.hibernate.event.def.DefaultFlushEntityEventListener.getValues(DefaultFlushEntityEventListener.java:167)
	at org.hibernate.event.def.DefaultFlushEntityEventListener.onFlushEntity(DefaultFlushEntityEventListener.java:120)
	at org.hibernate.event.def.AbstractFlushingEventListener.flushEntities(AbstractFlushingEventListener.java:196)
	at org.hibernate.event.def.AbstractFlushingEventListener.flushEverythingToExecutions(AbstractFlushingEventListener.java:76)
	at org.hibernate.event.def.DefaultAutoFlushEventListener.onAutoFlush(DefaultAutoFlushEventListener.java:35)
	at org.hibernate.impl.SessionImpl.autoFlushIfRequired(SessionImpl.java:969)
	at org.hibernate.impl.SessionImpl.list(SessionImpl.java:1114)
	at org.hibernate.impl.QueryImpl.list(QueryImpl.java:79)
	at com.stech.jgy.impl.local.rich.local.JgyManager.getStudentAppointByTeacherAppointId(JgyManager.java:1929)
	at com.stech.webplus.jgy.action.Util.AppointThread.correctStudentAppointError(AppointThread.java:410)
	at com.stech.webplus.jgy.action.Util.AppointThread.checkThreadByHours(AppointThread.java:148)
	at sun.reflect.GeneratedMethodAccessor672.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:597)
	at org.springframework.util.MethodInvoker.invoke(MethodInvoker.java:276)
	at org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean$MethodInvokingJob.executeInternal(MethodInvokingJobDetailFactoryBean.java:260)
	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:86)
	at org.quartz.core.JobRunShell.run(JobRunShell.java:216)
	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:549)

"startJob_Worker-9" prio=10 tid=0x00007f15c4b84000 nid=0x1a6b runnable [0x00007f159c870000]
   java.lang.Thread.State: RUNNABLE
	at java.lang.Class.getInterfaces(Native Method)
	at org.hibernate.intercept.FieldInterceptionHelper.extractFieldInterceptor(FieldInterceptionHelper.java:45)
	at org.hibernate.intercept.FieldInterceptionHelper.clearDirty(FieldInterceptionHelper.java:81)
[root@localhost ~]# 
```

---

```shell 
[root@localhost ~]# printf "%x\n" 6761
1a69
[root@localhost ~]# jstack 6734|grep 1a69 -A 30
"startJob_Worker-7" prio=10 tid=0x00007f15c4aa5800 nid=0x1a69 runnable [0x00007f159ca71000]
   java.lang.Thread.State: RUNNABLE
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:597)
	at org.hibernate.property.BasicPropertyAccessor$BasicGetter.get(BasicPropertyAccessor.java:145)
	at org.hibernate.tuple.entity.AbstractEntityTuplizer.getPropertyValues(AbstractEntityTuplizer.java:256)
	at org.hibernate.tuple.entity.PojoEntityTuplizer.getPropertyValues(PojoEntityTuplizer.java:209)
	at org.hibernate.persister.entity.AbstractEntityPersister.getPropertyValues(AbstractEntityPersister.java:3581)
	at org.hibernate.event.def.DefaultFlushEntityEventListener.getValues(DefaultFlushEntityEventListener.java:167)
	at org.hibernate.event.def.DefaultFlushEntityEventListener.onFlushEntity(DefaultFlushEntityEventListener.java:120)
	at org.hibernate.event.def.AbstractFlushingEventListener.flushEntities(AbstractFlushingEventListener.java:196)
	at org.hibernate.event.def.AbstractFlushingEventListener.flushEverythingToExecutions(AbstractFlushingEventListener.java:76)
	at org.hibernate.event.def.DefaultAutoFlushEventListener.onAutoFlush(DefaultAutoFlushEventListener.java:35)
	at org.hibernate.impl.SessionImpl.autoFlushIfRequired(SessionImpl.java:969)
	at org.hibernate.impl.SessionImpl.list(SessionImpl.java:1114)
	at org.hibernate.impl.QueryImpl.list(QueryImpl.java:79)
	at com.stech.jgy.impl.local.rich.local.JgyManager.getStudentAppointByTeacherAppointId(JgyManager.java:1929)
	at com.stech.webplus.jgy.action.Util.AppointThread.correctStudentAppointError(AppointThread.java:410)
	at com.stech.webplus.jgy.action.Util.AppointThread.checkThreadByHours(AppointThread.java:148)
	at sun.reflect.GeneratedMethodAccessor672.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:597)
	at org.springframework.util.MethodInvoker.invoke(MethodInvoker.java:276)
	at org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean$MethodInvokingJob.executeInternal(MethodInvokingJobDetailFactoryBean.java:260)
	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:86)
	at org.quartz.core.JobRunShell.run(JobRunShell.java:216)
	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:549)

"startJob_Worker-6" prio=10 tid=0x00007f15c4249000 nid=0x1a68 runnable [0x00007f159cb73000]
   java.lang.Thread.State: RUNNABLE
	at java.lang.Class.getInterfaces(Native Method)
[root@localhost ~]#
```

---

```java
[root@localhost ~]# printf "%x\n" 6760
1a68
[root@localhost ~]# jstack 6734|grep 1a68 -A 30
"startJob_Worker-6" prio=10 tid=0x00007f15c4249000 nid=0x1a68 runnable [0x00007f159cb73000]
   java.lang.Thread.State: RUNNABLE
	at java.lang.Class.getInterfaces(Native Method)
	at org.hibernate.intercept.FieldInterceptionHelper.extractFieldInterceptor(FieldInterceptionHelper.java:45)
	at org.hibernate.intercept.FieldInterceptionHelper.clearDirty(FieldInterceptionHelper.java:81)
	at org.hibernate.event.def.DefaultFlushEntityEventListener.isUpdateNecessary(DefaultFlushEntityEventListener.java:209)
	at org.hibernate.event.def.DefaultFlushEntityEventListener.onFlushEntity(DefaultFlushEntityEventListener.java:127)
	at org.hibernate.event.def.AbstractFlushingEventListener.flushEntities(AbstractFlushingEventListener.java:196)
	at org.hibernate.event.def.AbstractFlushingEventListener.flushEverythingToExecutions(AbstractFlushingEventListener.java:76)
	at org.hibernate.event.def.DefaultAutoFlushEventListener.onAutoFlush(DefaultAutoFlushEventListener.java:35)
	at org.hibernate.impl.SessionImpl.autoFlushIfRequired(SessionImpl.java:969)
	at org.hibernate.impl.SessionImpl.list(SessionImpl.java:1114)
	at org.hibernate.impl.QueryImpl.list(QueryImpl.java:79)
	at com.stech.jgy.impl.local.rich.local.JgyManager.getStudentAppointByTeacherAppointId(JgyManager.java:1929)
	at com.stech.webplus.jgy.action.Util.AppointThread.correctStudentAppointError(AppointThread.java:410)
	at com.stech.webplus.jgy.action.Util.AppointThread.checkThreadByHours(AppointThread.java:148)
	at sun.reflect.GeneratedMethodAccessor672.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:597)
	at org.springframework.util.MethodInvoker.invoke(MethodInvoker.java:276)
	at org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean$MethodInvokingJob.executeInternal(MethodInvokingJobDetailFactoryBean.java:260)
	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:86)
	at org.quartz.core.JobRunShell.run(JobRunShell.java:216)
	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:549)

"startJob_Worker-5" prio=10 tid=0x00007f15c4c07800 nid=0x1a67 runnable [0x00007f159cc74000]
   java.lang.Thread.State: RUNNABLE
	at java.lang.Class.getInterfaces(Native Method)
	at org.hibernate.intercept.FieldInterceptionHelper.extractFieldInterceptor(FieldInterceptionHelper.java:45)
	at org.hibernate.intercept.FieldInterceptionHelper.clearDirty(FieldInterceptionHelper.java:81)
	at org.hibernate.event.def.DefaultFlushEntityEventListener.isUpdateNecessary(DefaultFlushEntityEventListener.java:209)
[root@localhost ~]# 



[root@localhost ~]# printf "%x\n" 6756
1a64
[root@localhost ~]# jstack 6734|grep 1a64 -A 30
"startJob_Worker-2" prio=10 tid=0x00007f15c45f7800 nid=0x1a64 runnable [0x00007f159cf76000]
   java.lang.Thread.State: RUNNABLE
	at java.util.HashMap.get(HashMap.java:303)
	at org.hibernate.util.FastHashMap.get(FastHashMap.java:254)
	at org.hibernate.tuple.EntityModeToTuplizerMapping.getTuplizerOrNull(EntityModeToTuplizerMapping.java:59)
	at org.hibernate.tuple.EntityModeToTuplizerMapping.getTuplizer(EntityModeToTuplizerMapping.java:72)
	at org.hibernate.tuple.entity.EntityMetamodel.getTuplizer(EntityMetamodel.java:106)
	at org.hibernate.persister.entity.AbstractEntityPersister.getTuplizer(AbstractEntityPersister.java:3198)
	at org.hibernate.persister.entity.AbstractEntityPersister.getIdentifier(AbstractEntityPersister.java:3596)
	at org.hibernate.event.def.DefaultFlushEntityEventListener.checkId(DefaultFlushEntityEventListener.java:53)
	at org.hibernate.event.def.DefaultFlushEntityEventListener.getValues(DefaultFlushEntityEventListener.java:164)
	at org.hibernate.event.def.DefaultFlushEntityEventListener.onFlushEntity(DefaultFlushEntityEventListener.java:120)
	at org.hibernate.event.def.AbstractFlushingEventListener.flushEntities(AbstractFlushingEventListener.java:196)
	at org.hibernate.event.def.AbstractFlushingEventListener.flushEverythingToExecutions(AbstractFlushingEventListener.java:76)
	at org.hibernate.event.def.DefaultAutoFlushEventListener.onAutoFlush(DefaultAutoFlushEventListener.java:35)
	at org.hibernate.impl.SessionImpl.autoFlushIfRequired(SessionImpl.java:969)
	at org.hibernate.impl.SessionImpl.list(SessionImpl.java:1114)
	at org.hibernate.impl.QueryImpl.list(QueryImpl.java:79)
	at com.stech.jgy.impl.local.rich.local.JgyManager.getStudentAppointByTeacherAppointId(JgyManager.java:1929)
	at com.stech.webplus.jgy.action.Util.AppointThread.correctStudentAppointError(AppointThread.java:410)
	at com.stech.webplus.jgy.action.Util.AppointThread.checkThreadByHours(AppointThread.java:148)
	at sun.reflect.GeneratedMethodAccessor672.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:597)
	at org.springframework.util.MethodInvoker.invoke(MethodInvoker.java:276)
	at org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean$MethodInvokingJob.executeInternal(MethodInvokingJobDetailFactoryBean.java:260)
	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:86)
	at org.quartz.core.JobRunShell.run(JobRunShell.java:216)
	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:549)

"startJob_Worker-1" prio=10 tid=0x00007f15c4aff800 nid=0x1a63 runnable [0x00007f159d077000]

```



## polarDB

```
20210430 项目经常内存过载原因查询。
使用java自带的jvisualvm以及jpofiler分析内存，查无所得。
根据polarDB数据库的记录查到sql的执行情况，原来是条件为空时没判断好，查到了一千万条包裹商品的记录，占用了内存，导致项目挂掉。

```