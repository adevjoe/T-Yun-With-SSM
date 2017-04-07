# T-Yun
我的Java Web 练手项目——T-Yun（云盘）

>此版本是重构版本，使用了Spring + Spring MVC + MyBatis

Demo:[T-Yun](http://pan.90play.cn)

保持最新版本

测试账户：
    用户名： `thet`
    密码： `123`

## 下载
[最新版本](https://github.com/JoeC95/T-Yun-With-SSM/releases)

## 环境
Tomcat + mysql + 七牛云

## 搭建步骤
1.下载最新版本

2.修改配置文件。<code>/src/resources/config.yml</code> and <code>/src/resources/db.properties</code>

3.丢到tomcat工作目录，运行tomcat，ok!

## 配置文件

````yml
# 存放一些web配置信息
Version: 1.0

# 七牛云
# 设置密钥和空间名称，默认空间为tyun，请先在七牛云创建好空间
# Chain domain 设置空间的外链域名，格式：https(http)://xxx.yourdomain.xxx
ACCESS_KEY: zVeMUFmiaTut7lySrqpKi0zsliUmWJ9hLElCxOha
SECRET_KEY: 1RXm4QajxRDLIDEOLXxjAqrT_VMDPK1Cy4s0lv5c
# 文件存储空间
disk_bucket: tyun
# 移动U盘存储空间
u_disk_bucket: tyun
# 图床存储空间
img_bucket: img
# 文件外链地址
chain_domain: http://download.90play.cn
# 移动U盘外链地址
u_disk_domain: http://download.90play.cn
# 图床外链地址
img_domain: https://img.90play.cn

# 开启本地存储
# 本地存储可能占大量空间，谨慎设置
# 设置use ，启用和停止本地存储,true or false,默认为 false，不保存到本地
# 设置本地存储路径，修改folder，默认为uploadFile 文件夹
LocalStorage:
  use: true
  folder: uploadFile
````

数据库
````
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/tyun?useUnicode=true&characterEncoding=utf-8
jdbc.username=thet
jdbc.password=thet
````

sql脚本

用户
````sql
CREATE TABLE `ty_user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
````

文件
````sql
CREATE TABLE `ty_folder` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `file_name` text,
  `is_dir` int(1) NOT NULL DEFAULT '1',
  `parent_path` text NOT NULL,
  `level` varchar(255) DEFAULT '1',
  `comment` text,
  `create_time` bigint(14) NOT NULL DEFAULT '0',
  `update_time` bigint(14) NOT NULL DEFAULT '0',
  `user_id` int(11) DEFAULT NULL,
  `size` bigint(50) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;
````
