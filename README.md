
## 所用框架
- MyBatis-Plus 3.0.7
- MyBatis 3.4.6
- Spring 5.0.7
- Fastjson 1.2.61
- Shiro 1.4.1
- Druid 1.1.15
- Easypoi 4.1.2
- quartz 2.3.0

## 联系方式
- Mail:maillank@qq.com

## 效果图
![输入图片说明](https://images.gitee.com/uploads/images/2019/1102/172610_6018f0eb_448530.png "1.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1102/172619_331b99ae_448530.png "2.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1102/172629_80f2db34_448530.png "3.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1102/172638_0d2d0225_448530.png "4.png")

## 部署说明
### 步骤一
- 准备环境
```
- 中间件:Tomcat 8 以上
- 数据库:Mysql 5.7
```

### 步骤二
- 初始化sql
```
- SQL>CREATE DATABASE IF NOT EXISTS dt default charset utf8 COLLATE utf8_general_ci;
- SQL>set names utf8;
- SQL>source db.sql
```

### 步骤三
- 发布应用
```
- 部署到Tomcat目录的webapps下,修改配置文件(webapps/dt/WEB-INF/classes)中数据库的连接信息
- jdbc.url=jdbc:mysql://ip:port/dt?useUnicode=true&characterEncoding=utf8&useSSL=false
- jdbc.username=root
- jdbc.password=root_pwd
```

### 步骤四
- 启动服务访问
```
- http://ip:port/dt/console
- 选择用户名方式登陆
- 账户:admin 
- 密码:1
```

## 后期开发计划
- 添加资产自定义属性功能
- 添加工单流程
- 丰富资产报表
