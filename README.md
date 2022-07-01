## 项目简介

基于 springBoot 的网络相册管理系统

## 项目环境

- Maven3.3.9
- JDK8
- MySQL8
- SpringBoot + SpringMVC  + MyBatis

## 项目架构

项目整体使用前后端分离的机制，前端使用 Vue 来渲染界面，后端返回 Json 数据

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

![image-20220609133016992](images/image-20220609133016992.png)

## 功能模块

[Docs_README.md](docs/README.md) 

## 文档

[Docs_README.md](docs/README.md) 

## 部署说明

1、导入 sql 文件到 mysql 数据库

2、修改 **application.yml** 中关于数据库的配置 、用户上传文件的保存的位置

3、在终端键入命令 **mvn spring-boot:run** 运行项目

可以在这里查看项目的运行结果

在线演示：[http://121.5.167.249:8063](http://121.5.167.249:8063)

测试账号

- 管理员： superAdmin、superAdmin
- 一般用户：testUser、testUser

2022-05-09 14:47:26



