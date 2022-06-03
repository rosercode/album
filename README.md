## 项目简介



## 项目架构

项目整体使用前后端分离的机制，前端使用 Vue 来渲染界面，后端返回 Json 数据

前端返回的网页并非纯静态的，而是后端使用 **thymeleaf** 对几个网页拼接而成的。

### 前端

| 库         | 说明        |                                                    |
| ---------- | ----------- | -------------------------------------------------- |
| axios      | Ajax 请求库 | [http://axios-js.com/](http://axios-js.com/)       |
| Vue        | 渲染界面    | [https://cn.vuejs.org/](https://cn.vuejs.org/)     |
| Bootstrap4 | 界面样式    | [https://v4.bootcss.com/](https://v4.bootcss.com/) |
| alertifyjs | 前端弹窗库  | [https://alertifyjs.com/](https://alertifyjs.com/) |

### 后端

后端是通用是一套通用的 SSM 项目（SpringBoot、SpringMVC、Mybatis）

后端使用 MySQL 数据库 (mysql8)

## 系统数据库设计

![image-20220518163926633](res\image-20220518163926633.png)

## 功能模块

### 游客模块

访问公开、通过管理员审核的相册、查询公开的图片

### 用户模块

1. 创建相册，添加图片
2. 发布评论

### 管理员模块

1. 管理用户
2. 管理、审核相册
3. 管理、审核图片
4. 管理、审核评论

## 部署说明

1、导入 sql 文件到 mysql 数据库

2、修改 **application.yml** 中关于数据库的配置 、用户上传文件的保存的位置

3、在终端键入命令 **mvn spring-boot:run** 运行项目

可以在这里查看项目的运行结果

## 第三方接口列表



2022-05-09 14:47:26



