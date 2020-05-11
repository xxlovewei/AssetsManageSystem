
## 测试Demo
- http://39.105.191.22:3037/dt/console
- 账户:guest 密码:oracle
- 账户:user1 密码:oracle
- 账户:user2 密码:oracle
- 账户:user3 密码:oracle
- admin oracle
- 系统包含admin 管理用户

## 所用框架
- MyBatis-Plus 3.0.7.1
- MyBatis 3.4.6
- Spring 5.0.7
- Fastjson 1.2.62
- Shiro 1.4.2
- Druid 1.1.21
- Easypoi 4.1.3
- quartz 2.3.0
- UFLO 2.1.5(流程引擎)
- k-form-design 3.x(表单)
- angular 1.x

## 联系方式
- Mail:maillank@qq.com
- QQ交流群:904754434


## 部署说明
### 步骤一
- 准备环境
```
- 操作系统:Window、推荐Linux
- 数据库:Mysql 5.7.X
- 中间件:Tomcat 9 以上
- Java版本:Java 1.8 以上
```

### 步骤二
- 初始化sql
```
- 要求Mysql的参数 lower_case_table_names=1
- SQL>CREATE DATABASE IF NOT EXISTS dt default charset utf8 COLLATE utf8_general_ci;
- SQL>set names utf8;
- SQL>use dt;
- SQL>source db.sql
```

### 步骤三
- 发布应用,注意:最新版本的war包已经发布,请直接下载（右上角发版中）,不需要下载源代码进行编译。
```
- 将war包部署到Tomcat目录的webapps下后，修改配置文件(webapps/dt/WEB-INF/classes)中数据库的数据库配置文件config.properties
- jdbc.url=jdbc:mysql://127.0.0.1:3306/dt?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC
- jdbc.username=root
- jdbc.password=root_pwd
- 修改成你所在的环境配置
```

### 步骤四
- 启动服务访问
```
- http://ip:port/dt/console
- 选择用户名方式登陆
- 账户:admin 
- 密码:1
```

## 效果图
![输入图片说明](https://images.gitee.com/uploads/images/2019/1112/130924_93070844_448530.jpeg "11.jpg")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0506/130904_339165e8_448530.png "1.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1112/124506_47c9ca08_448530.jpeg "2.jpg")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1117/211517_8ba3a822_448530.jpeg "11.jpg")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1121/222157_1ae13ef1_448530.jpeg "55.jpeg")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1117/211528_0797dbbb_448530.jpeg "22.jpg")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1117/211538_1e78d9a4_448530.jpeg "33.jpg")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1112/124530_b7e7847b_448530.jpeg "4.jpg")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1112/124540_62166efa_448530.jpeg "5.jpg")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1205/213815_ad2975a7_448530.png "lc.png")
## 后期开发计划--5月底
- 添加资产自定义属性功能,该功能开发延期(视情况而定)
- 添加工单功能
- 优化打印标签
- 完成资产盘点开发

## 后期开发计划--开发简易的ITSM相关功能
