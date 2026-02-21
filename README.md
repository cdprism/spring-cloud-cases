# spring-cloud-cases

`对应版本`  
`jdk 21`  
`maven 3.9.9`  
`Spring Boot 3.3.4`  
`Spring Cloud 2023.0.3`  
`Spring Cloud Alibaba 2023.0.3.2`  
`Nacos latest version : 3.1.1`  
`Sentinel latest versio ： 1.8.9`  
`Seata 2.2.0`  
`RocketMQ 5.1.4`  

## nacos 3.1.1 配置  

1. 安装MySQL 并执行conf下mysql-schema.sql脚本
2. 修改conf下的application.properties文件，配置数据库连接
```text
allowPublicKeyRetrieval=true - 允许客户端从服务器获取公钥
spring.sql.init.platform=mysql
db.num=1
db.url.0=jdbc:mysql://127.0.0.1:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
db.user=root
db.password=mysql
```
生产环境建议配置如下
```text
-- 登录MySQL
mysql -u root -p

-- 查看当前用户认证插件
SELECT user, host, plugin FROM mysql.user WHERE user = '你的用户名';

-- 修改认证插件为 mysql_native_password
ALTER USER '你的用户名'@'%' IDENTIFIED WITH mysql_native_password BY '你的密码';
ALTER USER '你的用户名'@'localhost' IDENTIFIED WITH mysql_native_password BY '你的密码';

-- 刷新权限
FLUSH PRIVILEGES;
```
3. @EnableDiscoveryClient
4. spring.cloud.nacos.server-addr=${nacos.server-addr} ***nacos.server-addr*** 在不同环境定义
