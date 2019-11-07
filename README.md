## 更新日志
 
## 所用框架
1. SpringBoot 1.5.3.RELEASE
2. MyBatis-Plus 2.0.8
3. MyBatis 3.4.4
4. Spring 4.3.8.RELEASE
5. hibernate-validator 5.3.5.Final
6. Ehcache 3.3.1
7. Kaptcha 2.3.2
8. Fastjson 1.2.31
9. Shiro 1.4.0
10.Druid 1.0.31

## 联系方式
maillank@qq.com

## 效果图


![输入图片说明](https://images.gitee.com/uploads/images/2019/1102/172610_6018f0eb_448530.png "1.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1102/172619_331b99ae_448530.png "2.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1102/172629_80f2db34_448530.png "3.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1102/172638_0d2d0225_448530.png "4.png")

## 部署说明
步骤一

准备环境
中间件:Tomcat
数据库:Mysql 5.7

步骤二

初始化sql
SQL>CREATE DATABASE IF NOT EXISTS dt default charset utf8 COLLATE utf8_general_ci;
SQL>set names utf8;
SQL>source db.sql

步骤三

发布营业
部署到Tomcat目录的webapps下,修改配置文件(webapps/dt/WEB-INF/classes)中数据库的连接信息
jdbc.url=jdbc:mysql://ip:port/dt?useUnicode=true&characterEncoding=utf8&useSSL=false
jdbc.username=root
jdbc.password=root_pwd

步骤四
启动服务,访问地址
http://ip:port/dt/console