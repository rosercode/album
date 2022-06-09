
<!-- TOC -->
- [项目架构](#项目架构)
- [Database's Design](#databases-design)
    - [1、表名：album_db_album](#1表名album_db_album)
    - [2、表名：album_db_picture](#2表名album_db_picture)
    - [3、表名：album_db_remark](#3表名album_db_remark)
    - [4、表名：album_db_user](#4表名album_db_user)
- [功能设计](#功能设计)
<!-- /TOC -->


## 项目架构


## Database's Design

数据库这里使用 Mysql，版本为 8.0.26-0ubuntu0.20.04.2（运行在 Ubuntu20.04 上）

E-R 图

![image-20220609133016992](../images/image-20220609133016992.png)

​					

### 1、表名：album_db_album

| 注释： | 相册信息表         | 引擎： | InnoDB     |
| ------ | ------------------ | ------ | ---------- |
| 编码： | utf8mb4_0900_ai_ci | 类型： | BASE TABLE |

 

| **序号** | **字段名称** | **字段描述**                 | **字段类型** | **长度** | **允许空** | **缺省值** |
| -------- | ------------ | ---------------------------- | ------------ | -------- | ---------- | ---------- |
| 1        | albumId      | 相册编号 主键                | int          | null     | NO         | null       |
| 2        | userId       | 所属用户编号 外键            | int          | null     | YES        | null       |
| 3        | albumName    | 相册名称                     | varchar      | 100      | YES        | null       |
| 4        | albumStatue  | 相册状态、1有效，0禁用       | int          | null     | YES        | null       |
| 5        | albumRight   | 相册权限、1可访问，0不可访问 | int          | null     | YES        | null       |
| 6        | albumTime    | 相册创建时间                 | datetime     | null     | YES        | null       |
| 7        | albumFace    | 相册封面                     | varchar      | 100      | YES        | null       |

 

### 2、表名：album_db_picture

| 注释： | 照片信息表         | 引擎： | InnoDB     |
| ------ | ------------------ | ------ | ---------- |
| 编码： | utf8mb4_0900_ai_ci | 类型： | BASE TABLE |

 

| **序号** | **字段名称** | **字段描述**                 | **字段类型** | **长度** | **允许空** | **缺省值** |
| -------- | ------------ | ---------------------------- | ------------ | -------- | ---------- | ---------- |
| 1        | photoId      | 照片编号 主键                | int          | null     | NO         | null       |
| 2        | albumId      | 相册编号 外键                | int          | null     | YES        | null       |
| 3        | photoUserId  | 图片所属用户的ID 外键        | int          | null     | YES        | null       |
| 4        | photoName    | 照片名称                     | varchar      | 100      | YES        | null       |
| 5        | photoStatue  | 照片状态 1有效，0禁用        | int          | null     | YES        | null       |
| 6        | photoRight   | 照片权限 1可访问，0 不可访问 | int          | null     | YES        | null       |
| 7        | photoIntro   | 照片详情说明（图片描述）     | varchar      | 400      | YES        | null       |
| 8        | photoUrl     | 照片路径                     | varchar      | 400      | YES        | null       |
| 9        | createTime   | 图片上传时间                 | datetime     | null     | YES        | null       |

 

 

### 3、表名：album_db_remark

| 注释： | 评论信息表         | 引擎： | InnoDB     |
| ------ | ------------------ | ------ | ---------- |
| 编码： | utf8mb4_0900_ai_ci | 类型： | BASE TABLE |

 

| **序号** | **字段名称** | **字段描述**                         | **字段类型** | **长度** | **允许空** | **缺省值** |
| -------- | ------------ | ------------------------------------ | ------------ | -------- | ---------- | ---------- |
| 1        | remarkId     | 评论编号、主键                       | int          | null     | NO         | null       |
| 2        | albumId      | 相册编号 外键                        | int          | null     | YES        | null       |
| 3        | userId       | 发布评论的用户ID 外键                | int          | null     | YES        | null       |
| 4        | remarkInfo   | 评论信息                             | varchar      | 100      | YES        | null       |
| 5        | remarkStatue | 评论状态 （0 等待审核 1 审核完成  ） | int          | null     | YES        | null       |
| 6        | commentDate  | 评论发布时间                         | datetime     | null     | YES        | null       |

 

 

### 4、表名：album_db_user

| 注释： | 用户信息表：普通用户和管理员 | 引擎： | InnoDB     |
| ------ | ---------------------------- | ------ | ---------- |
| 编码： | utf8mb4_0900_ai_ci           | 类型： | BASE TABLE |

 

| **序号** | **字段名称** | **字段描述**            | **字段类型** | **长度** | **允许空** | **缺省值** |
| -------- | ------------ | ----------------------- | ------------ | -------- | ---------- | ---------- |
| 1        | userId       | 用户编号 主键           | int          | null     | NO         | null       |
| 2        | userName     | 用户昵称                | varchar      | 100      | YES        | null       |
| 3        | userStatue   | 用户状态 1有效，0禁用   | int          | null     | YES        | null       |
| 4        | userNum      | 用户帐号                | varchar      | 100      | YES        | null       |
| 5        | userPwd      | 用户密码                | varchar      | 100      | YES        | null       |
| 6        | userRight    | 用户权限 1管理员，0用户 | int          | null     | YES        | null       |
| 7        | phone        | 用户电话                | varchar      | 30       | YES        | null       |
| 8        | address      | 用户地址                | varchar      | 100      | YES        | null       |
| 9        | qqCode       | 用户QQ                  | varchar      | 30       | YES        | null       |
| 10       | remark       | 用户备注                | varchar      | 100      | YES        | null       |

 

## 功能设计

 

 