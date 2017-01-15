## 背景
一直在找一个心仪的博客系统。标准之一就是要Java写的，不然很难自己去做修改。所以即使wp依然很成熟了，但终究不适合我这个Java码农。后来也发现了jpress,号称Java版本的wp，可是使用的技术框架是jfinal。并非jfinal不好，毕竟不是自己最熟悉最擅长的技术，也终究不是主流技术的最佳选择。于是，基于admin-manage的同样背景，开始了重新早轮子的事情，bumishi-blog也由此诞生。

既然是重新造轮子，自然希望它更加符合当下的技术潮流。

在当下云计算与微服务的背景下，bumishi-blog也很自然的迎合了这种技术趋势。

### 1.基于springboot作为核心技术框架
作为Java的绝对主流技术，个人认为spring未来也必然会是Java的主流技术。springboot的出现彻底地改变了spring配置繁琐的面貌，已极其现代化的姿态迅速扩散，成为最受欢迎的技术框架。spring-cloud项目基于spring-boot，为构建分布式微服务提供了一站式解决方案。spring生态已经足以支持构建各个领域的应用。学好spring，足以让我们在Java世界立足。

### 2.基于领域驱动设计思想
如果你还在写dao+service+controller的代码，我不知道你是否有过想吐的感觉？是否觉得那真的是一种机械式的无需任何思想的工作？更重要的是，对大型复杂的系统，无论是优雅架构，代码复用还是扩展维护都不能很好的应对。
领域驱动设计思想已经提出很多年了，可是只有真正牛的公司和人才在使用和关注，对技术有着深刻追求的你，怎么可以不去接触领域驱动设计？

### 整体结构
[![](http://static.bumishi.cn/14843237429496358blog.png)](http://static.bumishi.cn/14843237429496358blog.png)

#### 目前bumishi-blog相关技术点：目前bumishi-blog相关技术点：

- spring-boot,spring-cache,druid，领域驱动设计

- 站点导航、博客分类、博客管理、网站信息配置、gitbook管理都有admin-manage后台管理

- 支持全文搜索,关键词高亮，基于elasticsearch5.1实现

- 支持根据blog编排成gitbook

- 博客文章基于editor.md，图片上传集成七牛云存储，文章图片加水印

- 整站数据缓存，admin后台修改站点实时更新

- 集成spring-boot-admin，通过admin后台可以监控到admin-manage和bumishi-blog的详细信息

- 评论使用第三方畅言

- 集成微信消息回复，将微信信息转成博客搜索结果

- 基础图灵机器人，搜索不到博客时有机器人回答

##演示地址
[在线演示](http://bumishi.cn/)

###本地运行简介###
#### 1.依赖bumishi-toolbox项目####

[bumishi-toolbox源码](https://git.oschina.net/mvc-easy/bumishi-toolbox.git)

#### 2.配置application.yml

  1.配置admin-manage的地址，目的是能在admin-manage监控到bumishi-blog的信息
  ```
  boot:
      admin:
        #这里是admin-manage的地址，/admin是admin-manage中配置的spring.spring.boot.admin.context-path
        url: http://localhost:10000/admin
        context-path: /admin
  ```
修改localhost:10000为你自己的admin-manage地址和端口，其它不变。

2.配置允许访问博客管理api的主机,目的是博客管理的功能是在bumishi-blog中，web入口在admin-manage中，需要允许admin-manage调用博客管理api

```
blog:
  manage:
    ##允许访问博客管理api的机器
    allow: localhost,127.0.0.1,bumishi.tech
```
3.配置bumishi-blog数据库的jdbc信息.mysql脚本在bumishi_blog.sql。

4.配置微信公众号和图灵api的相关apiKey和secretKey

```
//微信信息
weixin:
  token: weiqiang
  aesKey:
  subscribe: '我是不迷失'//这里是关注公众号回复的信息

//图灵api信息
tulin:
  key:
  secret:
```

4.admin-manage的配置中需要在application-default.yaml配置中需要配置七牛的相关配置以支持图片上传到七牛，需要配置bumishi-blog的地址，因为admin-manage管理博客功能需要调用bumishi-blog的api。admin-manage本身不维护博客的任何数据。

admin-manage的详细介绍：
http://bumishi.cn/blog/1

5.由于基于elasticsearch进行博客全文索引，并且博客数据存放在elasticsearch中，需要配置elasticsearch环境，并初始化blog索引。
脚本在index_init.sh中，执行两个curl命令即可。

elasticsearch的安装及介绍等：
http://bumishi.cn/blog/3

效果预览：

![](http://static.bumishi.cn/148432610105460481.png)


![](http://static.bumishi.cn/148432606233046982.png)


![](http://static.bumishi.cn/148432611936413033.png)


![](http://static.bumishi.cn/148432613278763034.png)


![](http://static.bumishi.cn/148432615387267845.png)


![](http://static.bumishi.cn/14843262468133518sear.png)


![](http://static.bumishi.cn/148432618415978277.png)


![公众号信息回复](http://static.bumishi.cn/14844877115542157wc.png "公众号信息回复")


###交流群:[245130488](http://shang.qq.com/wpa/qunwpa?idkey=d1d7f068205e1ff5dbcc1ecda23d5195d2ce61254c0f7188741c758111e1c2f2)###


##不迷失微信公众号:javajidi_com##
###专注Java技术研究###


