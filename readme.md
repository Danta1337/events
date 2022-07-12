# events项目说明
## 文件结构

---
该项目共有三个模块，分别为`event-biz`、`event-service`与`event-web`，根据阿里巴巴分层规约，各模块的大致功能如下：
+ **event-biz**

    负责与数据库交互(`mapper`)，调用第三方交互或其他相对底层的服务(`manager`)
+ **event-service**

    负责业务模块代码，相比于biz层更加轻量，可以直接调用biz层的`mapper`或`manager`
+ **event-web**
    
    负责提供展现层的接口，`Controller`、`Interceptor`等与web相关的代码会存放在这里
  
  