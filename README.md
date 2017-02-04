#T-Yun
我的Java Web 练手项目——T-Yun（云盘）

>此版本是重构版本，使用了Spring + Spring MVC + MyBatis

Demo:[T-Yun](http://pan.90play.cn)

保持最新版本

测试账户：
    用户名： `thet`
    密码： `123`

##下载
[最新版本](https://github.com/JoeC95/T-Yun-With-SSM/releases)

##环境
Tomcat + mysql + 七牛云

##搭建步骤
1.下载最新版本

2.修改配置文件。<code>/src/resources/config.yml</code> and <code>/src/resources/db.properties</code>

3.导入sql脚本，sql脚本在<code>/src/resources/config.yml</code>

4.丢到tomcat工作目录，运行tomcat，ok!

##配置文件

````yml
#存放一些web配置信息
Version: 1.0

#七牛云
#设置密钥和空间名称，默认空间为tyun，请先在七牛云创建好空间
#Chain domain 设置空间的外链域名，格式：xxx.yourdomain.xxx
ACCESS_KEY: xxx
SECRET_KEY: xxx
bucket: tyun
chain_domain: download.90play.cn

#开启本地存储
#设置use ，启用和停止本地存储,true or false
#设置本地存储路径，修改folder，默认为uploadFile 文件夹
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