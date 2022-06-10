# window安装consul

<br />


- Consul 是 HashiCorp 公司推出的开源工具，用于实现分布式系统的服务发现与配置。与其它分布式服务注册与发现的方案，Consul 的方案更“一站式”，内置了服务注册与发现框 架、分布一致性协议实现、健康检查、Key/Value 存储、多数据中心方案，不再需要依赖其它工具（比如 ZooKeeper 等）。使用起来也较为简单。

- Consul 使用 Go 语言编写，因此具有天然可移植性(支持Linux、windows和Mac OS X)；安装包仅包含一个可执行文件，方便部署，与 Docker 等轻量级容器可无缝配合。

- 在分布式服务发现的软件中，Euerka 和 Consul 使用最为广泛。


`--20210514`

---

- 1、启动

cd 到对应的目录下，使用 `cmd` 启动 `Consul`

```sh
cd D:\ProgramFiles\consul_1.7.2_windows_amd64
#cmd启动：
consul agent -dev        # -dev表示开发模式运行，另外还有-server表示服务模式运行
```

为了方便期间，可以在同级目录下创建一个 run.bat 脚本来启动，脚本内容如下：

```sh
consul agent -dev
pause
```

- 2、验证

启动成功之后访问：http://localhost:8500 ，可以看到 Consul 的管理界面


```
参考 
http://www.ityouknow.com/springcloud/2018/07/20/spring-cloud-consul.html

```