# events项目说明

[![OSCS Status](https://www.oscs1024.com/platform/badge/Danta1337/events.svg?size=small)](https://www.oscs1024.com/project/Danta1337/events?ref=badge_small)

## 文件结构

---
该项目共有三个模块，分别为`event-biz`、`event-service`与`event-web`，根据阿里巴巴分层规约，各模块的大致功能如下：
+ **event-biz**

    负责与数据库交互，调用第三方交互或其他相对底层的服务
+ **event-service**

    负责业务模块代码，相比于biz层更加轻量，可以直接调用biz层的`mapper`或`manager`
+ **event-web**
    
    负责提供展现层的接口，`Controller`、`Interceptor`等与web相关的代码会存放在这里

## 前端开发须知

---
前端使用mdui组件进行搭建
### 加载
+ 导入`util.js`文件，用户端页面加载时需要调用`initAppbarContent`与`initDrawerContent`进行**appbar**与**左侧导航栏**的初始化，初始化appbar时可以传入**主题**与**标题**，初始化抽屉导航栏时可以选择**打开的的列表**与**激活的item**

### 操作元素
+ 可以使用`util.js`中一系列的`setElementxxx`来设置元素的`attribute`、`src`、`value`等，需要传入元素的id，不会判断元素是否存在

### 请求
+ 可以使用`util.js`中的`isNotBlank`进行元素value的判空，需要传入元素的id
+ 可以使用`util.js`中的`getGetParams`与`getPostBody`快速构建请求参数，需要传入参数的元素id列表
+ 可以使用`util.js`中的`checkResult`来判断服务端响应业务处理是否成功(`code = 0`)或响应对象是否为空